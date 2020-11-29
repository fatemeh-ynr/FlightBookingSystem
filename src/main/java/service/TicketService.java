package service;

import org.hibernate.exception.ConstraintViolationException;
import repository.dao.TicketDao;
import repository.entity.Ticket;

import java.util.List;

public class TicketService {
    TicketDao ticketDao;
    private static TicketService instance;

    private TicketService() {
        ticketDao = TicketDao.getInstance();
    }

    public static TicketService getInstance() {
        if (instance == null) {
            instance = new TicketService();
        }
        return instance;
    }

    public Ticket read(int ticketNumber) {
        return ticketDao.read(ticketNumber);
    }

    public int bookTicket(Ticket ticket) {
        if (ticket != null) {
            if (!ticketDao.isTicketAvailableByFlightNumberPassengerName(ticket)) {
                try {
                    return ticketDao.create(ticket);
                } catch (ConstraintViolationException exception) {
                    throw new RuntimeException("The flight is not available");
                }
            }
        }
        throw new RuntimeException("Unsuccessful booking");
    }

    public Ticket getTicket(int id) {
        return ticketDao.read(id);
    }

    public Ticket updateTicket(Ticket ticket) {
        Ticket retrievedTicket = ticketDao.read(ticket.getTicketNumber());
        if (retrievedTicket != null) {
            ticket.setFlight(retrievedTicket.getFlight());
            if (!ticketDao.isTicketAvailableByFlightNumberPassengerName(ticket)) {
                return ticketDao.update(ticket);
                //return true;
            }
        }
        //return false;
        return null;
    }

    public boolean deleteTicket(int ticketNumber) {
        Ticket ticket = ticketDao.read(ticketNumber);
        if (ticket != null) {
            ticketDao.delete(ticket);
            if (ticketDao.read(ticketNumber) == null) {
                return true;
            }
        }
        return false;
    }

    public List getAll() {
        return ticketDao.selectAll();
    }

    public boolean deleteAll(){
        return ticketDao.deleteAll();
    }

}

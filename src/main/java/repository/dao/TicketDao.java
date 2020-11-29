package repository.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import repository.entity.Ticket;

import java.util.List;

public class TicketDao extends GenericDaoImpl<Ticket, Integer> {
    private static TicketDao instance;
    private SessionFactory sessionFactory;

    private TicketDao() {
        sessionFactory = DBConnection.getSessionFactory();
    }

    public static TicketDao getInstance() {
        if (instance == null) {
            instance = new TicketDao();
        }
        return instance;
    }

    public boolean isTicketAvailableByFlightNumberPassengerName(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Ticket.class);
        criteria.add(Restrictions.eq("flight.flightNumber", ticket.getFlight().getFlightNumber()));
        criteria.add(Restrictions.eq("passengerName", ticket.getPassengerName()));
        List<Ticket> availableTicket = criteria.list();
        if (availableTicket != null && availableTicket.size() != 0) {
            throw new RuntimeException("the ticket has already been booked");
        }
        transaction.commit();
        session.close();
        return false;
    }

    public boolean deleteAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Ticket");
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

}

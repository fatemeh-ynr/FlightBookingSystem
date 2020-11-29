package controller.servlets;

import repository.entity.Flight;
import repository.entity.Ticket;
import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String flightNumber = request.getParameter("flightNumber");
        String passengerName = request.getParameter("passengerName");
        Ticket ticket = Ticket.TicketBuilder.aTicket()
                .withPassengerName(passengerName)
                .withFlight(new Flight(Integer.valueOf(flightNumber)))
                .build();
        TicketService ticketService = TicketService.getInstance();
        try {
            int ticketNumber = ticketService.bookTicket(ticket);
            writer.println("Successful booking, ticketNumber: " + ticketNumber + "</br></br>");
        } catch (RuntimeException exception) {
            //writer.println(exception.getMessage());
            writer.println("Unsuccessful booking: the flight is not available or you already booked the flight");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

package controller.servlets;

import repository.entity.Ticket;
import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String passengerNameforUpdate = request.getParameter("passengerNameforUpdate");
        int ticketNumber = ticketNumber = Integer.valueOf(request.getParameter("ticketNumber"));
        Ticket newTicket = Ticket.TicketBuilder.aTicket()
                .withTicketNumber(ticketNumber)
                .withPassengerName(passengerNameforUpdate)
                .build();

        TicketService ticketService = TicketService.getInstance();
        try {
            if (ticketService.updateTicket(newTicket)!=null) {
                writer.println("Update ticket successfully.");
            } else {
                writer.println("Cannot update ticket");
            }
        } catch (RuntimeException exception) {
            writer.println(exception.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

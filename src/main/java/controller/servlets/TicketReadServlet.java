package controller.servlets;

import repository.entity.Ticket;
import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketReadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int ticketNumber = Integer.valueOf(request.getParameter("ticketNumber"));
        TicketService ticketService = TicketService.getInstance();
        Ticket ticket = ticketService.getTicket(ticketNumber);
        if (ticket != null) {
            writer.println(ticket);
        } else {
            writer.println("No ticket is available with ticket number \"" + ticketNumber + "\".");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

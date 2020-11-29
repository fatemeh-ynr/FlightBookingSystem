package controller.servlets;

import service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int ticketNumber = Integer.valueOf(request.getParameter("ticketNumber"));
        TicketService ticketService = TicketService.getInstance();
        if (ticketService.deleteTicket(ticketNumber)) {
            writer.println("Delete ticket successfully.");
        } else {
            writer.println("Cannot delete ticket");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

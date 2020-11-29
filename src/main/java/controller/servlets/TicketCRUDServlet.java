package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TicketCRUDServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String action = request.getParameter("action");

        if (action.equals("Show")) {
            request.getRequestDispatcher("TicketReadServlet").forward(request,response);
        } else if (action.equals("Delete")) {
            request.getRequestDispatcher("TicketDeleteServlet").forward(request,response);
        }
        else if (action.equals("Update")) {
            request.getRequestDispatcher("TicketUpdateServlet").forward(request,response);
        }
        else{
            writer.println("invalid action");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

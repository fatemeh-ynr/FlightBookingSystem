package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "TicketNumberFilter")
public class TicketNumberFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            int ticketNumber = Integer.parseInt(request.getParameter("ticketNumber"));
            if(ticketNumber>0) {
                chain.doFilter(request, response);
            }
            else {
                writer.println("invalid \"Ticket Number\"");
            }
        }
        catch (Exception exception){
            writer.println("invalid \"Ticket Number\"");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

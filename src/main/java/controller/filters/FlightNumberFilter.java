package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "FlightNumberFilter")
public class FlightNumberFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String flightNumber = request.getParameter("flightNumber");
        long digitCount = flightNumber.chars()
                .filter(Character::isDigit)
                .count();
        if (digitCount != 3 || flightNumber.length() > 3) {
            writer.println("invalid \"Flight Number\"");
        }
        else{
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

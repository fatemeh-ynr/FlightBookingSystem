package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "UpdateInformationFilter")
public class UpdateInformationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("UpdateInformationFilter");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String action = request.getParameter("action");

        if (action.equals("Update")) {
            String passengerNameforUpdate = request.getParameter("passengerNameforUpdate");
            if (passengerNameforUpdate == null || passengerNameforUpdate.equals("")) {
                writer.println("Passenger Name is necessary");
            }
            else {
                chain.doFilter(request, response);
            }
        }
        else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

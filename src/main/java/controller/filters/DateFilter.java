package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Pattern;

@WebFilter(filterName = "DateFilter")
public class DateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String stringDate = request.getParameter("departureDate");
        Pattern datePattern = Pattern.compile("^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$");
        if (datePattern.matcher(stringDate).matches()) {
            chain.doFilter(request, response);
        } else {
            writer.println("invalid date");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

package controller.servlets;

import repository.entity.Flight;
import service.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlightsSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        String stringDate = request.getParameter("departureDate");
        Date departureDate = null;
        try {
            departureDate = new SimpleDateFormat("yyyy/mm/dd").parse(stringDate);
            System.out.println("valid date");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Flight flight = Flight.FlightBuilder.aFlight()
                .withDepartureCity(departureCity)
                .withArrivalCity(arrivalCity)
                .withDepartureDateTime(departureDate)
                .build();
        List<Flight> availableFlights = FlightService.getInstance().findByCityAndDate(flight);
        if (availableFlights != null && availableFlights.size() != 0) {
            availableFlights.forEach(availableFlight -> writer.println(availableFlight + "</br></br>"));
        } else {
            writer.println("No flight(s) available");
        }
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

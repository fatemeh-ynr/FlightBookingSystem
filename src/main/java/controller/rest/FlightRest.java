package controller.rest;

import repository.entity.Flight;
import repository.entity.Ticket;
import service.FlightService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/flights")
public class FlightRest {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights(Flight flight) {
        System.out.println("post.....");

        Date date;
        try {
            date=new SimpleDateFormat("yyyy/MM/dd").parse(flight.getDepartureDate());
        } catch (ParseException e) {
            return null;
        }
        flight.setDepartureDateTime(date);
        List<Flight> availableFlights = FlightService.getInstance().findByCityAndDate(flight);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        availableFlights.forEach(availableFlight -> availableFlight.setDepartureDate(dateFormat.format(availableFlight.getDepartureDateTime())));
        return availableFlights;
    }



}

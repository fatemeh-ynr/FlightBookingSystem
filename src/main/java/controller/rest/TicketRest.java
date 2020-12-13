package controller.rest;

import repository.entity.Ticket;
import service.TicketService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Path("/tickets")
public class TicketRest {

    TicketService ticketService;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a");

    public TicketRest() {
        ticketService = TicketService.getInstance();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket getTicket(@PathParam("id") int ticketNumber) {
        Ticket ticket= ticketService.read(ticketNumber);
        ticket.getFlight().setDepartureDate(dateFormat.format(ticket.getFlight().getDepartureDateTime()));
        return ticket;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        tickets.forEach(availableFlight -> availableFlight.getFlight().setDepartureDate(dateFormat.format(availableFlight.getFlight().getDepartureDateTime())));
        return tickets;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket createTicket(Ticket ticket) {
        System.out.println("post: createTicket");
        int ticketNumber = ticketService.bookTicket(ticket);
        return new Ticket(ticketNumber);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket updateTicket(Ticket ticket) {
        System.out.println("hi put");
        System.out.println(ticket.getTicketNumber());
        return ticketService.updateTicket(ticket);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") int id) {
        if (ticketService.deleteTicket(id))
            return Response.status(200).entity("Ticket with ticket number " + id + " is deleted").build();
        else
            return Response.status(200).entity("Cannot delete Ticket with ticket number " + id).build();
    }

    @DELETE
    public Response deleteAllTicket() {
        if (ticketService.deleteAll()) {
            return Response.status(200).entity("all tickets are deleted").build();
        } else {
            return Response.status(200).entity("Cannot delete all tickets").build();
        }
    }
}

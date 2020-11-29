package repository.entity;


import javax.persistence.*;

@Entity
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketNumber;
    private String passengerName;
    @ManyToOne
    private Flight flight;

    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Ticket() {
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return  "Ticket Number: " + ticketNumber +
                ", passengerName: " + passengerName +
                ", flightNumber: " + flight.getFlightNumber() +
                ", departureCity: " + flight.getDepartureCity() +
                ", arrivalCity: " + flight.getArrivalCity() +
                ", departureDate: " + flight.getDepartureDateTime() ;
    }

    public static final class TicketBuilder {
        private int ticketNumber;
        private String passengerName;
        private Flight flight;

        private TicketBuilder() {
        }

        public static TicketBuilder aTicket() {
            return new TicketBuilder();
        }

        public TicketBuilder withTicketNumber(int ticketNumber) {
            this.ticketNumber = ticketNumber;
            return this;
        }

        public TicketBuilder withPassengerName(String passengerName) {
            this.passengerName = passengerName;
            return this;
        }

        public TicketBuilder withFlight(Flight flight) {
            this.flight = flight;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();
            ticket.setTicketNumber(ticketNumber);
            ticket.setPassengerName(passengerName);
            ticket.setFlight(flight);
            return ticket;
        }
    }
}

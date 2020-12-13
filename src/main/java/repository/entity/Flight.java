package repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class Flight {
    @Id
    private int flightNumber;
    private String departureCity;
    private String arrivalCity;
    private Date departureDateTime;
    @Transient
    private String departureDate;

    public Flight(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight() {
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber +
                ", Departure City: " + departureCity +
                ", Arrival City: " + arrivalCity +
                ", Departure Date/Time: " + departureDateTime ;
    }


    public static final class FlightBuilder {
        private int flightNumber;
        private String departureCity;
        private String arrivalCity;
        private Date departureDateTime;

        private FlightBuilder() {
        }

        public static FlightBuilder aFlight() {
            return new FlightBuilder();
        }

        public FlightBuilder withFlightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightBuilder withDepartureCity(String departureCity) {
            this.departureCity = departureCity;
            return this;
        }

        public FlightBuilder withArrivalCity(String arrivalCity) {
            this.arrivalCity = arrivalCity;
            return this;
        }

        public FlightBuilder withDepartureDateTime(Date departureDateTime) {
            this.departureDateTime = departureDateTime;
            return this;
        }

        public Flight build() {
            Flight flight = new Flight();
            flight.setFlightNumber(flightNumber);
            flight.setDepartureCity(departureCity);
            flight.setArrivalCity(arrivalCity);
            flight.setDepartureDateTime(departureDateTime);
            return flight;
        }
    }
}

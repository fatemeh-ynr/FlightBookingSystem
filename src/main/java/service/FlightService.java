package service;

import repository.dao.FlightDao;
import repository.entity.Flight;

import java.util.List;

public class FlightService {
    FlightDao flightDao;
    private static FlightService instance;

    private FlightService() {
        flightDao = FlightDao.getInstance();
    }

    public static FlightService getInstance() {
        if (instance == null) {
            instance = new FlightService();
        }
        return instance;
    }

    public List<Flight> findByCityAndDate(Flight flight) {
        return flightDao.findByCityAndDate(flight);
    }

}

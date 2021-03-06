package repository.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import repository.entity.Flight;

import java.io.Serializable;
import java.util.List;

public class FlightDao<T, PK extends Serializable> extends GenericDaoImpl<Flight, Integer> {
    private static FlightDao instance;
    private SessionFactory sessionFactory;

    private FlightDao() {
        sessionFactory = DBConnection.getSessionFactory();
    }

    public static FlightDao getInstance() {
        if (instance == null) {
            synchronized (FlightDao.class) {
                if (instance == null) {
                    instance = new FlightDao();
                }
            }
        }
        return instance;
    }

    public List<Flight> findByCityAndDate(Flight flight) {
        List<Flight> flights = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Flight.class);
        if (flight.getDepartureCity() != null)
            criteria.add(Restrictions.eq("departureCity", flight.getDepartureCity()));
        if (flight.getArrivalCity() != null)
            criteria.add(Restrictions.eq("arrivalCity", flight.getArrivalCity()));
        if (flight.getDepartureDateTime() != null)
            criteria.add(Restrictions.sqlRestriction("date(departureDateTime) = ? ", flight.getDepartureDateTime(), org.hibernate.type.StandardBasicTypes.DATE));
        flights = criteria.list();
        flights.forEach(System.out::println);
        transaction.commit();
        session.close();
        return flights;
    }
}

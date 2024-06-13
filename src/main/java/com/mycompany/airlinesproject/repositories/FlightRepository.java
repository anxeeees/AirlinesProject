package com.mycompany.airlinesproject.repositories;

import com.mycompany.airlinesproject.entities.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Repository class for managing Flight entities.
 * This class provides methods to interact with the database for Flight entities.
 *
 * <p>
 * It uses Hibernate for ORM (Object-Relational Mapping).
 * </p>
 *
 * <p>
 * This class includes methods to save, retrieve, delete, and update flights.
 * </p>
 *
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * {@code
 * FlightRepository flightRepository = new FlightRepository();
 * flightRepository.saveFlight(new Flight(...));
 * List<Flight> flights = flightRepository.getFlights();
 * }
 * </pre>
 *
 * @see Flight
 * @see SessionFactory
 * @see Query
 *
 * @autor
 *     Ester Stankovská
 */
public class FlightRepository {

    private SessionFactory sessionFactory;

    /**
     * Default constructor. Initializes the session factory.
     */
    public FlightRepository() {
        setUp();
    }

    /**
     * Sets up the Hibernate session factory.
     */
    private void setUp() {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                Metadata metadata = new MetadataSources(standardRegistry)
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Saves a flight to the database.
     *
     * @param flight The flight entity to be saved.
     */
    public void saveFlight(Flight flight) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(flight);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves all flights from the database.
     *
     * @return A list of all flights.
     */
    public List<Flight> getFlights() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Flight> query = session.createQuery("from Flight", Flight.class);
        List<Flight> flights = query.list();
        session.getTransaction().commit();
        session.close();
        return flights;
    }

    /**
     * Deletes a flight from the database by its ID.
     *
     * @param flightId The ID of the flight to be deleted.
     */
    public void deleteFlight(String flightId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Flight flight = session.get(Flight.class, flightId);
            if (flight == null) {
                System.out.println("Flight with ID " + flightId + " not found.");
                return;
            }

            session.delete(flight);
            session.getTransaction().commit();
        }
    }

    /**
     * Updates a flight's details in the database.
     *
     * @param flightId The id of the flight to be updated.
     * @param source The new source of the flight.
     * @param destination The new destination of the flight.
     * @param date The new date of the flight.
     * @param seats The new number of seats of the flight.
     */
    public void updateFlight(Long flightId, String source, String destination, Date date, String seats) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Flight> query = session.createQuery("from Flight where flightId = :flightId", Flight.class);
        query.setParameter("flightId", flightId);
        Flight flight = query.getSingleResult();
        flight.setSource(source);
        flight.setDestination(destination);
        flight.setDate(date);
        flight.setSeats(Integer.parseInt(seats));
        session.update(flight); // Use update instead of persist for existing entities
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves a flight from the database by its code.
     *
     * @param code The code of the flight to be retrieved.
     * @return The flight entity if found, otherwise null.
     */
    public Flight getFlightByCode(String code) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Flight> query = session.createQuery("from Flight where code = :code", Flight.class);
            query.setParameter("code", code);
            Flight flight = query.uniqueResult();
            session.getTransaction().commit();
            return flight;
        }
    }
}
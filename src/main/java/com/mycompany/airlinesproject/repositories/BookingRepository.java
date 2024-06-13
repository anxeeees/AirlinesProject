package com.mycompany.airlinesproject.repositories;

import com.mycompany.airlinesproject.entities.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Repository class for managing Booking entities.
 * This class provides methods to interact with the database for Booking entities.
 *
 * <p>
 * It uses Hibernate for ORM (Object-Relational Mapping).
 * </p>
 *
 * <p>
 * This class includes methods to save and retrieve bookings.
 * </p>
 *
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * {@code
 * BookingRepository bookingRepository = new BookingRepository();
 * bookingRepository.saveBooking(new Booking(...));
 * List<Booking> bookings = bookingRepository.getBookings();
 * }
 * </pre>
 *
 * @author
 *     Ester Stankovská
 */
public class BookingRepository {

    private SessionFactory sessionFactory;

    /**
     * Default constructor. Initializes the session factory.
     */
    public BookingRepository() {
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
     * Saves a booking to the database.
     * If the booking already exists, it will be updated.
     *
     * @param booking The booking entity to be saved.
     */
    public void saveBooking(Booking booking) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(booking);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getBookings() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Booking> query = session.createQuery("from Booking", Booking.class);
        List<Booking> bookings = query.list();
        session.getTransaction().commit();
        session.close();
        return bookings;
    }
}
package com.mycompany.airlinesproject.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.mycompany.airlinesproject.entities.Passenger;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Repository class for managing Passenger entities.
 * This class provides methods to interact with the database for Passenger entities.
 *
 * <p>
 * It uses Hibernate for ORM (Object-Relational Mapping).
 * </p>
 *
 * <p>
 * This class includes methods to save, retrieve, delete, and update passengers.
 * </p>
 *
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * {@code
 * PassengerRepository passengerRepository = new PassengerRepository();
 * passengerRepository.savePassenger(new Passenger(...));
 * List<Passenger> passengers = passengerRepository.getPassengers();
 * }
 * </pre>
 *
 * @see Passenger
 * @see SessionFactory
 * @see Query
 *
 * @autor
 *     Ester Stankovská
 */
public class PassengerRepository {
    private SessionFactory sessionFactory;

    /**
     * Default constructor. Initializes the session factory.
     */
    public PassengerRepository() {
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
     * Saves a passenger to the database.
     *
     * @param passenger The passenger entity to be saved.
     */
    public void savePassenger(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(passenger);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves all passengers from the database.
     *
     * @return A list of all passengers.
     */
    public List<Passenger> getPassengers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger", Passenger.class);
        List<Passenger> passengers = query.list();
        session.getTransaction().commit();
        session.close();
        return passengers;
    }

    /**
     * Deletes a passenger from the database by its ID.
     *
     * @param passengerId The ID of the passenger to be deleted.
     */
    public void deletePassenger(String passengerId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Passenger passenger = session.get(Passenger.class, passengerId);
            if (passenger == null) {
                System.out.println("Passenger with ID " + passengerId + " not found.");
                return;
            }

            session.delete(passenger);
            session.getTransaction().commit();
        }
    }

    /**
     * Updates a passenger's details in the database.
     *
     * @param passengerId The ID of the passenger to be updated.
     * @param name The new name of the passenger.
     * @param nationality The new nationality of the passenger.
     * @param gender The new gender of the passenger.
     * @param passport The new passport number of the passenger.
     * @param address The new address of the passenger.
     * @param phone The new phone number of the passenger.
     */
    public void updatePassenger(String passengerId, String name, String nationality, String gender, String passport, String address, String phone) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger where passengerId = :passengerId", Passenger.class);
        query.setParameter("passengerId", passengerId);
        Passenger passenger = query.getSingleResult();
        passenger.setName(name);
        passenger.setNationality(nationality);
        passenger.setGender(gender);
        passenger.setPassport(passport);
        passenger.setAddress(address);
        passenger.setPhone(phone);
        session.persist(passenger);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieves a passenger from the database by its ID.
     *
     * @param id The ID of the passenger to be retrieved.
     * @return The passenger entity if found, otherwise null.
     */
    public Passenger getPassengerById(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Passenger passenger = session.get(Passenger.class, id);
            session.getTransaction().commit();
            return passenger;
        }
    }
}
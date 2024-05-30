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

public class PassengerRepository {
    private SessionFactory sessionFactory;

    public PassengerRepository() {
        setUp();
    }

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

    public void savePassenger(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(passenger);
        session.getTransaction().commit();
        session.close();
    }

    public List<Passenger> getPassengers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Passenger");
        List<Passenger> passengers = query.list();
        return passengers;

    }

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

    public void updatePassenger(String passengerId, String name, String nationality, String gender, String passport, String address, String phone) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Passenger where passengerId =:passengerId");
        query.setParameter("passengerId", passengerId);
        Passenger passenger = (Passenger) query.getSingleResult();
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

    public Passenger getPassengerById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Passenger.class, id);
        }
    }


}
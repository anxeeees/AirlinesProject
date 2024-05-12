package com.mycompany.airlinesproject.ropositories;

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

    public List<Passenger> getPassenger(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Passenger");
        List<Passenger> passengers = query.list();
        return passengers;

    }
}
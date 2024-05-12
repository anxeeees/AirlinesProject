package com.mycompany.airlinesproject.ropositories;

import com.mycompany.airlinesproject.entities.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class BookingRepository {
    private SessionFactory sessionFactory;
    public BookingRepository()  {
        setUp();
    }
    private void setUp()  {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry standardRegistry
                        = new StandardServiceRegistryBuilder()
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

    public void saveBooking(Booking booking) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(booking);
        session.getTransaction().commit();
        session.close();

    }
}

package com.mycompany.airlinesproject.ropositories;

import com.mycompany.airlinesproject.entities.Booking;
import com.mycompany.airlinesproject.entities.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.mycompany.airlinesproject.entities.Cancellation;
import org.hibernate.query.Query;

import java.util.List;

public class CancellationRepository {
    private SessionFactory sessionFactory;

    public CancellationRepository() {
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

    public void saveCancellation(Cancellation cancellation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(cancellation);
        session.getTransaction().commit();
        session.close();
    }


    public List<Cancellation> getCancellations(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Cancellation");
        List<Cancellation> cancellations = query.list();
        return cancellations;

    }

    public Long getNextCancId() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<Long> query = session.createQuery("select max(cancellationId) from Cancellation ", Long.class);
        Long maxCancId = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return maxCancId != null ? maxCancId + 1 : 1L;
    }
}
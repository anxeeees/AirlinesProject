package com.mycompany.airlinesproject.ropositories;

import com.mycompany.airlinesproject.entities.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class FlightRepository {

    private SessionFactory sessionFactory;

    public FlightRepository()  {
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
    public void saveFlight(Flight flight) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(flight);
        session.getTransaction().commit();
        session.close();

    }

//    public void deleteFlight(String code){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Flight flight = session.
//    }

    public List<Flight> getFlights(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Flight");
        List<Flight> flights = query.list();
        return flights;

    }


}

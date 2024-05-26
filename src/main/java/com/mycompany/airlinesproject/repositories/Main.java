package com.mycompany.airlinesproject.repositories;

import com.mycompany.airlinesproject.entities.Booking;
import com.mycompany.airlinesproject.entities.Flight;
import com.mycompany.airlinesproject.entities.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Create Hibernate SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Open a session
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create a flight
            Flight flight = new Flight("FL123", "New York", "London", new Date(), 150);
            session.save(flight);

            // Create a passenger
            Passenger passenger = new Passenger("John Doe", "American", "Male", "A12345678", "123 Main St", "555-1234");
            session.save(passenger);

            // Create a booking
            Booking booking = new Booking("John Doe", "B001", "Male", "A12345678", 500.0, "American", flight, passenger);
            session.save(booking);

            session.getTransaction().commit();

            // Fetching data to verify relationships
            Booking fetchedBooking = session.get(Booking.class, booking.getBookingId());
            System.out.println("Booking ID: " + fetchedBooking.getBookingId());
            System.out.println("Passenger Name: " + fetchedBooking.getPassenger().getName());
            System.out.println("Flight Code: " + fetchedBooking.getFlight().getCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the session and the session factory
            sessionFactory.close();
        }
    }
}
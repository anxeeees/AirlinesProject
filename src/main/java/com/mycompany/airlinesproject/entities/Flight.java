package com.mycompany.airlinesproject.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity class representing a Flight in the airline system.
 * Each flight can have multiple bookings associated with it.
 *
 * <p>
 * This entity maps to the "flights" table in the database.
 * </p>
 *
 * @author
 *     Ester Stankovská
 */
@Entity
@Table(name = "flights")
public class Flight {

    /**
     * Unique identifier for the flight.
     * This is the primary key for the flights table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    /**
     * Code of the flight.
     */
    @Column(name = "code")
    private String code;

    /**
     * Source location of the flight.
     */
    @Column(name = "source")
    private String source;

    /**
     * Destination location of the flight.
     */
    @Column(name = "destination")
    private String destination;

    /**
     * Date of the flight.
     */
    @Column(name = "date")
    private Date date;

    /**
     * Number of seats available on the flight.
     */
    @Column(name = "seats")
    private int seats;

    /**
     * List of bookings associated with this flight.
     * This is a one-to-many relationship.
     */
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Flight() {
    }

    /**
     * Constructor to create a Flight with all details.
     *
     * @param code The flight code.
     * @param source The source location of the flight.
     * @param destination The destination location of the flight.
     * @param date The date of the flight.
     * @param seats The number of seats available on the flight.
     */
    public Flight(String code, String source, String destination, Date date, int seats) {
        this.code = code;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.seats = seats;
    }

    /**
     * Adds a booking to the flight.
     *
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setFlight(this);
    }

    // Getters and setters

    /**
     * Gets the unique identifier for the flight.
     *
     * @return the flight ID.
     */
    public Long getFlightId() {
        return flightId;
    }

    /**
     * Sets the unique identifier for the flight.
     *
     * @param flightId the flight ID.
     */
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    /**
     * Gets the code of the flight.
     *
     * @return the flight code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the flight.
     *
     * @param code the flight code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the source location of the flight.
     *
     * @return the source location.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source location of the flight.
     *
     * @param source the source location.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets the destination location of the flight.
     *
     * @return the destination location.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination location of the flight.
     *
     * @param destination the destination location.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the date of the flight.
     *
     * @return the flight date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the flight.
     *
     * @param date the flight date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the number of seats available on the flight.
     *
     * @return the number of seats.
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Sets the number of seats available on the flight.
     *
     * @param seats the number of seats.
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Gets the list of bookings associated with this flight.
     *
     * @return the list of bookings.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Sets the list of bookings associated with this flight.
     *
     * @param bookings the list of bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
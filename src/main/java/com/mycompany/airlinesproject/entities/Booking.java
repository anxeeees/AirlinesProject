package com.mycompany.airlinesproject.entities;

import jakarta.persistence.*;

/**
 * Entity class representing a Booking in the airline system.
 * Each booking is associated with a specific flight and passenger.
 *
 * <p>
 * This entity maps to the "booking" table in the database.
 * </p>
 *
 * @author
 *     Ester Stankovská
 */
@Entity
@Table(name = "booking")
public class Booking {

    /**
     * Unique identifier for the booking.
     * This is the primary key for the booking table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    /**
     * Name of the passenger for this booking.
     */
    @Column(name = "pass_name")
    private String passengerName;

    /**
     * Booking code associated with this booking.
     */
    @Column(name = "code")
    private String code;

    /**
     * Gender of the passenger for this booking.
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Passport number of the passenger for this booking.
     */
    @Column(name = "passport")
    private String passport;

    /**
     * Amount paid for this booking.
     */
    @Column(name = "amount")
    private double amount;

    /**
     * Nationality of the passenger for this booking.
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * Flight associated with this booking.
     * This is a many-to-one relationship.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    /**
     * Passenger associated with this booking.
     * This is a many-to-one relationship.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    /**
     * Default constructor.
     */
    public Booking() {
    }

    /**
     * Constructor to create a Booking with all details.
     *
     * @param passengerName The name of the passenger.
     * @param code The booking code.
     * @param gender The gender of the passenger.
     * @param passport The passport number of the passenger.
     * @param amount The amount paid for the booking.
     * @param nationality The nationality of the passenger.
     * @param flight The flight associated with the booking.
     * @param passenger The passenger associated with the booking.
     */
    public Booking(String passengerName, String code, String gender, String passport, double amount, String nationality, Flight flight, Passenger passenger) {
        this.passengerName = passengerName;
        this.code = code;
        this.gender = gender;
        this.passport = passport;
        this.amount = amount;
        this.nationality = nationality;
        this.flight = flight;
        this.passenger = passenger;
    }

    // Getters and setters

    /**
     * Gets the unique identifier for the booking.
     *
     * @return the booking ID.
     */
    public Long getBookingId() {
        return bookingId;
    }

    /**
     * Sets the unique identifier for the booking.
     *
     * @param bookingId the booking ID.
     */
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the name of the passenger.
     *
     * @return the passenger name.
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Sets the name of the passenger.
     *
     * @param passengerName the passenger name.
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * Gets the booking code.
     *
     * @return the booking code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the booking code.
     *
     * @param code the booking code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the gender of the passenger.
     *
     * @return the gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the passenger.
     *
     * @param gender the gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the passport number of the passenger.
     *
     * @return the passport number.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Sets the passport number of the passenger.
     *
     * @param passport the passport number.
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Gets the amount paid for the booking.
     *
     * @return the amount paid.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount paid for the booking.
     *
     * @param amount the amount paid.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the nationality of the passenger.
     *
     * @return the nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality of the passenger.
     *
     * @param nationality the nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the flight associated with this booking.
     *
     * @return the flight.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Sets the flight associated with this booking.
     *
     * @param flight the flight.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Gets the passenger associated with this booking.
     *
     * @return the passenger.
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Sets the passenger associated with this booking.
     *
     * @param passenger the passenger.
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
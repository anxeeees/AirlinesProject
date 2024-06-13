package com.mycompany.airlinesproject.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Passenger in the airline system.
 * Each passenger can have multiple bookings associated with them.
 *
 * <p>
 * This entity maps to the "passenger" table in the database.
 * </p>
 *
 * @author
 *     Ester Stankovská
 */
@Entity
@Table(name = "passenger")
public class Passenger {

    /**
     * Unique identifier for the passenger.
     * This is the primary key for the passenger table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id")
    private Long passengerId;

    /**
     * Name of the passenger.
     */
    @Column(name = "name")
    private String name;

    /**
     * Nationality of the passenger.
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * Gender of the passenger.
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Passport number of the passenger.
     */
    @Column(name = "passport")
    private String passport;

    /**
     * Address of the passenger.
     */
    @Column(name = "address")
    private String address;

    /**
     * Phone number of the passenger.
     */
    @Column(name = "phone")
    private int phone;

    /**
     * List of bookings associated with this passenger.
     * This is a one-to-many relationship.
     */
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Passenger() {
    }

    /**
     * Constructor to create a Passenger with all details.
     *
     * @param name The name of the passenger.
     * @param nationality The nationality of the passenger.
     * @param gender The gender of the passenger.
     * @param passport The passport number of the passenger.
     * @param address The address of the passenger.
     * @param phone The phone number of the passenger.
     */
    public Passenger(String name, String nationality, String gender, String passport, String address, int phone) {
        this.name = name;
        this.nationality = nationality;
        this.gender = gender;
        this.passport = passport;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Adds a booking to the passenger.
     *
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setPassenger(this);
    }

    // Getters and setters

    /**
     * Gets the unique identifier for the passenger.
     *
     * @return the passenger ID.
     */
    public Long getPassengerId() {
        return passengerId;
    }

    /**
     * Sets the unique identifier for the passenger.
     *
     * @param passengerId the passenger ID.
     */
    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * Gets the name of the passenger.
     *
     * @return the passenger name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the passenger.
     *
     * @param name the passenger name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the nationality of the passenger.
     *
     * @return the passenger nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the nationality of the passenger.
     *
     * @param nationality the passenger nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the gender of the passenger.
     *
     * @return the passenger gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the passenger.
     *
     * @param gender the passenger gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the passport number of the passenger.
     *
     * @return the passenger passport number.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Sets the passport number of the passenger.
     *
     * @param passport the passenger passport number.
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Gets the address of the passenger.
     *
     * @return the passenger address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the passenger.
     *
     * @param address the passenger address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone number of the passenger.
     *
     * @return the passenger phone number.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the passenger.
     *
     * @param phone the passenger phone number.
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Gets the list of bookings associated with this passenger.
     *
     * @return the list of bookings.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Sets the list of bookings associated with this passenger.
     *
     * @param bookings the list of bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
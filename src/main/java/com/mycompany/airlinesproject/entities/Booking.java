package com.mycompany.airlinesproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "pass_name")
    private String passengerName;

    @Column(name = "code")
    private String code;

    @Column(name = "gender")
    private String gender;

    @Column(name = "passport")
    private String passport;

    @Column(name = "amount")
    private double amount;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "fk_flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "fk_passenger_id")
    private Passenger passenger;

    public Booking() {
    }

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

    public Booking(String passengerName, String code, String gender, String passport, double amount, String nationality) {
        this.passengerName = passengerName;
        this.code = code;
        this.gender = gender;
        this.passport = passport;
        this.amount = amount;
        this.nationality = nationality;

    }

    // Getters and setters

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
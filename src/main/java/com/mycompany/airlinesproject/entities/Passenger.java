package com.mycompany.airlinesproject.entities;
import jakarta.persistence.*;


@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id")
    private Long passengerId;

    @Column(name = "name")
    private String name;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "gender")
    private String gender;

    @Column(name = "passport")
    private String passport;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    private Flight flight;
    // Constructors, getters, and setters

    public Passenger() {
    }

    public Passenger(String name, String nationality, String gender, String passport, String address, String phone) {
        this.name = name;
        this.nationality = nationality;
        this.gender = gender;
        this.passport = passport;
        this.address = address;
        this.phone = phone;
    }

    // Getters and setters

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
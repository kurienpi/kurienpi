package com.railway.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passengers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    private String name;
    private String email;
    private String mobile;
    private String destination;
    private String seatNumber;
    private String coachType;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<BookingInformation> bookings;

    // Getters and setters
    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public List<BookingInformation> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingInformation> bookings) {
        this.bookings = bookings;
    }
}

@Entity
@Table(name = "general_passengers")
public class GeneralPassenger extends Passenger {
}

@Entity
@Table(name = "senior_citizens")
public class SeniorCitizen extends Passenger {
    private Double discount;
    private String seniorCitizenId;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getSeniorCitizenId() {
        return seniorCitizenId;
    }

    public void setSeniorCitizenId(String seniorCitizenId) {
        this.seniorCitizenId = seniorCitizenId;
    }
}

@Entity
@Table(name = "physically_handicapped")
public class PhysicallyHandicapped extends Passenger {
    private String proofOfDisability;

    public String getProofOfDisability() {
        return proofOfDisability;
    }

    public void setProofOfDisability(String proofOfDisability) {
        this.proofOfDisability = proofOfDisability;
    }
}
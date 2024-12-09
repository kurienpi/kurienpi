package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "passengers")
public abstract class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "destination")
    private String destination;

    @Column(name = "seat_number")
    private String seatNumber;

    // Default constructor
    public Passenger() {}

    // Remove the multi-parameter constructor

    // Existing getters and setters
    // ... (keep all existing getters and setters)

    // Optionally, add a convenience constructor if needed
    public Passenger(String name, String email, String mobile,
                     String destination, String seatNumber) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    public Passenger(int passengerId, String name, String email, String mobile, String destination, String seatNumber) {
    }
}
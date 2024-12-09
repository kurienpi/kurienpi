package model;

import javax.persistence.*;

@Entity
@Table(name = "general_passengers")
@PrimaryKeyJoinColumn(name = "passenger_id")
public class GeneralPassenger extends Passenger {
    @Column(name = "coach_type")
    private String coachType;

    // Default constructor
    public GeneralPassenger() {}

    // Updated constructor to match your usage
    public GeneralPassenger(String name, String email, String mobile,
                            String destination, String seatNumber,
                            String coachType) {
        super(name, email, mobile, destination, seatNumber);
        this.coachType = coachType;
    }
}
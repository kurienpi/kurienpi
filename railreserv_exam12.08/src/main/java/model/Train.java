package model;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "train")
abstract class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private int trainId;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "no_of_coach")
    private int noOfCoach;

    @Column(name = "train_start_station")
    private String trainStartStation;

    @Column(name = "train_end_station")
    private String trainEndStation;

    @Column(name = "train_type")
    private String trainType;

    @Column(name = "train_weekly_days_schedule")
    private String trainWeeklyDaysSchedule;

    @Column(name = "avg_speed")
    private double avgSpeed;

    @Column(name = "fare_charges")
    private double fareCharges;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    // Constructors, getters, and setters
    public Train() {}

    public Train(String trainName, int noOfCoach, String trainStartStation,
                 String trainEndStation, String trainType,
                 String trainWeeklyDaysSchedule, double avgSpeed, double fareCharges) {
        this.trainName = trainName;
        this.noOfCoach = noOfCoach;
        this.trainStartStation = trainStartStation;
        this.trainEndStation = trainEndStation;
        this.trainType = trainType;
        this.trainWeeklyDaysSchedule = trainWeeklyDaysSchedule;
        this.avgSpeed = avgSpeed;
        this.fareCharges = fareCharges;
    }

    // Getters and setters
    public int getTrainId() { return trainId; }
    public void setTrainId(int trainId) { this.trainId = trainId; }
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

}

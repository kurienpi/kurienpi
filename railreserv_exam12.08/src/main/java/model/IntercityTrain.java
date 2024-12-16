package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "intercity_train")
public class IntercityTrain extends Train {

    @Column(name = "no_of_cities")
    private int noOfCities;

    // Default constructor
    public IntercityTrain() {}

    // Parameterized constructor
    public IntercityTrain(String trainName, int noOfCoach, String trainStartStation,
                          String trainEndStation, String trainWeeklyDaysSchedule,
                          double avgSpeed, double fareCharges, int noOfCities) {
        super(trainName, noOfCoach, trainStartStation, trainEndStation,
                "Intercity", trainWeeklyDaysSchedule, avgSpeed, fareCharges);
        this.noOfCities = noOfCities;
    }

    // Getters and setters
    public int getNoOfCities() {
        return noOfCities;
    }

    public void setNoOfCities(int noOfCities) {
        this.noOfCities = noOfCities;
    }
}

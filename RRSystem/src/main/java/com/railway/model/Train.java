package com.railway.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trains")
@Inheritance(strategy = InheritanceType.JOINED)
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainId;

    private String trainName;
    private Integer noOfCoach;
    private String trainStartStation;
    private String trainEndStation;
    private String trainType;
    private String trainWeeklyDaysSchedule;
    private Double avgSpeed;
    private Double fareCharges;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<BookingInformation> bookings;

    // Getters and setters
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getNoOfCoach() {
        return noOfCoach;
    }

    public void setNoOfCoach(Integer noOfCoach) {
        this.noOfCoach = noOfCoach;
    }

    public String getTrainStartStation() {
        return trainStartStation;
    }

    public void setTrainStartStation(String trainStartStation) {
        this.trainStartStation = trainStartStation;
    }

    public String getTrainEndStation() {
        return trainEndStation;
    }

    public void setTrainEndStation(String trainEndStation) {
        this.trainEndStation = trainEndStation;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainWeeklyDaysSchedule() {
        return trainWeeklyDaysSchedule;
    }

    public void setTrainWeeklyDaysSchedule(String trainWeeklyDaysSchedule) {
        this.trainWeeklyDaysSchedule = trainWeeklyDaysSchedule;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getFareCharges() {
        return fareCharges;
    }

    public void setFareCharges(Double fareCharges) {
        this.fareCharges = fareCharges;
    }

    public List<BookingInformation> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingInformation> bookings) {
        this.bookings = bookings;
    }
}

@Entity
@Table(name = "local_trains")
public class LocalTrain extends Train {
    private Integer frequency;

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}

@Entity
@Table(name = "intercity_trains")
public class IntercityTrain extends Train {
    private Integer noOfCities;

    public Integer getNoOfCities() {
        return noOfCities;
    }

    public void setNoOfCities(Integer noOfCities) {
        this.noOfCities = noOfCities;
    }
}

@Entity
@Table(name = "superfast_trains")
public class SuperFastTrain extends Train {
    private Double specialCharges;

    public Double getSpecialCharges() {
        return specialCharges;
    }

    public void setSpecialCharges(Double specialCharges) {
        this.specialCharges = specialCharges;
    }
}

@Entity
@Table(name = "goods_trains")
public class GoodsTrain extends Train {
    private String typesOfGoods;

    public String getTypesOfGoods() {
        return typesOfGoods;
    }

    public void setTypesOfGoods(String typesOfGoods) {
        this.typesOfGoods = typesOfGoods;
    }
}
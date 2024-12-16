package model;
@Entity
@Table(name = "super_fast_train")
class SuperFastTrain extends Train {
    @Column(name = "special_charges")
    private double specialCharges;

    public SuperFastTrain() {}

    public SuperFastTrain(String trainName, int noOfCoach, String trainStartStation,
                          String trainEndStation, String trainWeeklyDaysSchedule,
                          double avgSpeed, double fareCharges, double specialCharges) {
        super(trainName, noOfCoach, trainStartStation, trainEndStation,
                "SuperFast", trainWeeklyDaysSchedule, avgSpeed, fareCharges);
        this.specialCharges = specialCharges;
    }

    // Getters and setters
    public double getSpecialCharges() { return specialCharges; }
    public void setSpecialCharges(double specialCharges) { this.specialCharges = specialCharges; }
}
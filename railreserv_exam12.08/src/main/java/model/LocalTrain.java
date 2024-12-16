package model;
@Entity
@Table(name = "local_train")
class LocalTrain extends Train {
    @Column(name = "frequency")
    private String frequency;

    public LocalTrain() {}

    public LocalTrain(String trainName, int noOfCoach, String trainStartStation,
                      String trainEndStation, String trainWeeklyDaysSchedule,
                      double avgSpeed, double fareCharges, String frequency) {
        super(trainName, noOfCoach, trainStartStation, trainEndStation,
                "Local", trainWeeklyDaysSchedule, avgSpeed, fareCharges);
        this.frequency = frequency;
    }

    // Getters and setters
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
}

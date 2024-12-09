package model;

public class SeniorCitizenPassenger extends Passenger {
    private double discount;
    private String seniorCitizenId;

    public SeniorCitizenPassenger(int passengerId, String name, String email,
                                  String mobile, String destination,
                                  String seatNumber, double discount,
                                  String seniorCitizenId) {
        super(passengerId, name, email, mobile, destination, seatNumber);
        this.discount = discount;
        this.seniorCitizenId = seniorCitizenId;
    }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public String getSeniorCitizenId() { return seniorCitizenId; }
    public void setSeniorCitizenId(String seniorCitizenId) { this.seniorCitizenId = seniorCitizenId; }
}

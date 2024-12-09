package model;

public class PhysicallyHandicappedPassenger extends Passenger {
    private String disabilityProof;

    public PhysicallyHandicappedPassenger(int passengerId, String name, String email,
                                          String mobile, String destination,
                                          String seatNumber, String disabilityProof) {
        super(passengerId, name, email, mobile, destination, seatNumber);
        this.disabilityProof = disabilityProof;
    }

    public String getDisabilityProof() { return disabilityProof; }
    public void setDisabilityProof(String disabilityProof) { this.disabilityProof = disabilityProof; }
}
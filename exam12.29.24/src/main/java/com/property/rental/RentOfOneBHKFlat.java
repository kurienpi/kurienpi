package com.property.rental;

public class RentOfOneBHKFlat extends RentOfFlat {
    @Override
    public double calculateRentOfFlat(int noOfPerson) {
        return 250 * noOfPerson;
    }

    @Override
    public void displayInfo() {
        System.out.println("Rent for One BHK Flat: " + calculateRentOfFlat(noOfPerson));
    }
}

package com.property.rental;

public class RentOfThreeBHKFlat extends RentOfFlat {
    private boolean hasBalcony;

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    @Override
    public double calculateRentOfFlat(int noOfPerson) {
        return 600 * noOfPerson;
    }

    @Override
    public void displayInfo() {
        System.out.println("Rent for Three BHK Flat: " + calculateRentOfFlat(noOfPerson));
        System.out.println("Has Balcony: " + hasBalcony);
    }
}

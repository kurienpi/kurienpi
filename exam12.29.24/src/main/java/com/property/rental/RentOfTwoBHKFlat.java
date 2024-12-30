package com.property.rental;

import com.property.RentOfFlat;

public class RentOfTwoBHKFlat  {
    private boolean hasBalcony;

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    @Override
    public double calculateRentOfFlat(int noOfPerson) {
        return 400 * noOfPerson;
    }

    @Override
    public void displayInfo() {
        System.out.println("Rent for Two BHK Flat: " + calculateRentOfFlat(noOfPerson));
        System.out.println("Has Balcony: " + hasBalcony);
    }
}

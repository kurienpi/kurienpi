package com.property.rental;

//import org.springframework.core.convert.Property;

public abstract class RentOfFlat {
    protected int noOfPerson;

    public void setNoOfPerson(int noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public abstract double calculateRentOfFlat(int noOfPerson);
}

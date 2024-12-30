package com.property.selfowned;


import org.springframework.stereotype.Component;

@Component
public class SelfOwnedTwoBHKFlat extends SelfOwnedFlat {
    @Override
    public double calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public double finalPriceOfFlat() {
        return 30000 * calculatePlot(length, breadth, height);
    }

    @Override
    public void displayInfo() {
        System.out.println("Two BHK Self Owned Flat Price: " + finalPriceOfFlat());
    }
}

package com.property.selfowned;

import org.springframework.stereotype.Component;

@Component
public class SelfOwnedOneBHKFlat extends SelfOwnedFlat {
    @Override
    public double calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public double finalPriceOfFlat() {
        return 10000 * calculatePlot(length, breadth, height);
    }

    @Override
    public void displayInfo() {
        System.out.println("One BHK Self Owned Flat Price: " + finalPriceOfFlat());
    }
}

package com.property.selfowned;
import org.springframework.stereotype.Component;

@Component
public class SelfOwnedThreeBHKFlat extends SelfOwnedFlat {
    @Override
    public double calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    @Override
    public double finalPriceOfFlat() {
        return 50000 * calculatePlot(length, breadth, height);
    }

    @Override
    public void displayInfo() {
        System.out.println("Three BHK Self Owned Flat Price: " + finalPriceOfFlat());
    }
}
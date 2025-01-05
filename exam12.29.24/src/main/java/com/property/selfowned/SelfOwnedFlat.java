package com.property.selfowned;

import org.springframework.core.convert.Property;

public abstract class SelfOwnedFlat  {
    public float length;
    public float breadth;
    public float height;

    public abstract double calculatePlot(float length, float breadth, float height);
    public abstract double finalPriceOfFlat();
}
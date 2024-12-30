package com.property.selfowned;

import org.springframework.core.convert.Property;

public abstract class SelfOwnedFlat  {
    protected float length;
    protected float breadth;
    protected float height;

    public abstract double calculatePlot(float length, float breadth, float height);
    public abstract double finalPriceOfFlat();
}
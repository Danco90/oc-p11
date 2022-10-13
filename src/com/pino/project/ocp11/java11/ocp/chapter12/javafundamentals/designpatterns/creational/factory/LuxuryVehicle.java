package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factory;

import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.CarType;
import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.Location;

public class LuxuryVehicle extends Vehicle {

    LuxuryVehicle(Location location)
    {
        super(CarType.LUXURY, location);
        construct();
    }

    @Override
    protected void construct()
    {
        System.out.println("Connecting to Luxury Car ");
    }
}

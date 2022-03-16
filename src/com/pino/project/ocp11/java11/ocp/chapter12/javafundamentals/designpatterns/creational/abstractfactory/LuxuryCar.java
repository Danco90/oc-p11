package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

class LuxuryCar extends Car {

    LuxuryCar(Location location)
    {
        super(CarType.LUXURY, location);
        construct();
    }

    @Override
    protected void construct()
    {
        System.out.println("Connecting to luxury car");   
    }
}

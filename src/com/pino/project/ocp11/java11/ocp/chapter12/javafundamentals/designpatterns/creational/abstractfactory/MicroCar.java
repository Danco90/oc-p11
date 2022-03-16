package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

class MicroCar extends Car {

    MicroCar(Location location)
    {
        super(CarType.MICRO, location);
        construct();
    }

    @Override
    protected void construct()
    {
        System.out.println("Connecting to Micro Car ");
    }
}

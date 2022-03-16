package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

class MiniCar extends Car {

    MiniCar(Location location)
    {
        super(CarType.MINI, location);
        construct();
    }

    @Override
    protected void construct()
    {
        System.out.println("Connecting to Mini Car ");
    }
}

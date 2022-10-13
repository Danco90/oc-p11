package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

import com.pino.project.ocp11.java11.ocp.chapter15.functionalprogramming.Employee;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroCar car = (MicroCar) o;
        return model == car.model && location == car.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, location);
    }
}

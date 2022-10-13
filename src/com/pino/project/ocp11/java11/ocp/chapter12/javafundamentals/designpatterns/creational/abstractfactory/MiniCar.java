package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiniCar car = (MiniCar) o;
        return model == car.model && location == car.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, location);
    }

}

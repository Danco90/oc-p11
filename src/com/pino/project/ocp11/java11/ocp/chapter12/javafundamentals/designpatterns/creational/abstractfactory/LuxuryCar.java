package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuxuryCar car = (LuxuryCar) o;
        return model == car.model && location == car.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, location);
    }

}

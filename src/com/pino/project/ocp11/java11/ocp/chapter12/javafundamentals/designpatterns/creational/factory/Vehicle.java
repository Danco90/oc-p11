package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factory;

import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.CarType;
import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.Location;

abstract class Vehicle
{
    CarType model;
    Location location;

    Vehicle(CarType model, Location location)
    {
        this.model = model;
        this.location = location;
    }

    abstract void construct();

    CarType getModel() {
        return model;
    }

    void setModel(CarType model) {
        this.model = model;
    }

    Location getLocation() {
        return location;
    }

    void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "CarModel - "+model + " located in "+location;
    }
}

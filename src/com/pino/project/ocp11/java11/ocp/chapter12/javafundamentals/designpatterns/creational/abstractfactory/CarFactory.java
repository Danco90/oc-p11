package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

import java.nio.file.attribute.UserDefinedFileAttributeView;

class CarFactory
{
    private  CarFactory () { } //Private Constructor in order to prevent direct initialization

    public static Car buildCar(CarType type)//Builder Pattern
    {
        Car car = null;
        // We can add any GPS Function here which
        // read location property somewhere from configuration
        // and use location specific car factory
        // Currently I'm just using INDIA as Location
        Location location = Location.INDIA;

        switch(location)
        {
            case USA:
                car = USACarFactory.buildCar(type);
                break;

            case INDIA:
                car = INDIACarFactory.buildCar(type);
                break;

            default:
                car = DefaultCarFactory.buildCar(type);
        }

        return car;
    }
}

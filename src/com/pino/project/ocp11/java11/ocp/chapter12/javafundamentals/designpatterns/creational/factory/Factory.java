package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factory;

import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.CarType;
import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.Location;

public class Factory
{
    private Factory()
    {
        
    }

    public static Vehicle buildCar(Location location, CarType model)
    {
        Vehicle car = null;
        switch (model)
        {
            case MICRO:
                car = new MicroVehicle(location);
                break;

            case MINI:
                car = new MiniVehicle(location);
                break;

            case LUXURY:
                car = new LuxuryVehicle(location);
                break;

            default:
                break;

        }
        return car ;
    }

}

package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factory;

import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.CarType;
import com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory.Location;

public class DesignManager {

    public static void main(String...args)
    {
        System.out.println("Factory Pattern approach :");
        System.out.println(Factory.buildCar(Location.DEFAULT, CarType.MICRO));
        System.out.println(Factory.buildCar(Location.DEFAULT, CarType.MINI));
        System.out.println(Factory.buildCar(Location.DEFAULT, CarType.LUXURY));
    }
}

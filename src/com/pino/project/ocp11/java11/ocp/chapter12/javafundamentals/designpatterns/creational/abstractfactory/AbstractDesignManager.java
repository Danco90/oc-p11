package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

public class AbstractDesignManager {

    public static void main(String...args)
    {
        System.out.println("All the Car types : ");
        for(CarType carType: CarType.values()) {
            System.out.print(carType + " "+carType.ordinal()+", ");
            carType.printDetails();
        }

        System.out.println(CarFactory.buildCar(CarType.MICRO));
        System.out.println(CarFactory.buildCar(CarType.MINI));
        System.out.println(CarFactory.buildCar(CarType.LUXURY));
    }
}

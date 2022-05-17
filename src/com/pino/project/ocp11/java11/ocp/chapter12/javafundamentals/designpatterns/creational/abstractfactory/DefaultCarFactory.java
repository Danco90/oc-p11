package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

class DefaultCarFactory
{
    public static Car buildCar(CarType model)//Builder Pattern
    {
        Car car = null;
        switch (model)
        {
            case MICRO:
                car = new MicroCar(Location.DEFAULT);
                break;

            case MINI:
                car = new MiniCar(Location.DEFAULT);
                break;

            case LUXURY:
                car = new LuxuryCar(Location.DEFAULT);
                break;
                
            default:
                break;

        }
        return car ;
    }
}

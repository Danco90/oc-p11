package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

class USACarFactory
{
    public static Car buildCar(CarType model)//Builder Pattern
    {
        Car car = null;
        switch (model)
        {
            case MICRO:
                car = new MicroCar(Location.USA);
                break;

            case MINI:
                car = new MiniCar(Location.USA);
                break;

            case LUXURY:
                car = new LuxuryCar(Location.USA);
                break;
                
            default:
                break;

        }
        return car ;
    }
}

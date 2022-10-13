package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarFactoryUnitTest
{
    @Test
    @DisplayName("Test build of Micro Car in India")
    public void givenCarFactory_whenBuildCarForTypeMicro_thenMicroCarIsReturned()
    {
        MicroCar microCar = new MicroCar(Location.INDIA);
        MiniCar miniCar = new MiniCar(Location.INDIA);
        LuxuryCar luxCar = new LuxuryCar(Location.INDIA);
        assertAll(() -> assertEquals(microCar, CarFactory.buildCar(CarType.MICRO)),
                () -> assertEquals(miniCar, CarFactory.buildCar(CarType.MINI)),
                () -> assertEquals(luxCar, CarFactory.buildCar(CarType.LUXURY)));
    }
}

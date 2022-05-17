package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

public class FoodManager
{

    public static void main(String...args)
    {
        try
        {   Food zebraFood = FoodFactory.getFood("zebra");
            zebraFood.consumed();

            Food rabbitFood = FoodFactory.getFood("rabbit");
            rabbitFood.consumed();

            Food goatFood = FoodFactory.getFood("goat");
            goatFood.consumed();

            Food polarBearFood = FoodFactory.getFood("polar bear");
            polarBearFood.consumed();

            Food raccoonFood = FoodFactory.getFood("raccoon");
            raccoonFood.consumed();
        } catch (UnsupportedOperationException ex) {
            System.out.println("Creation of food failed due to : "+ex.getMessage());
        }
    }
}

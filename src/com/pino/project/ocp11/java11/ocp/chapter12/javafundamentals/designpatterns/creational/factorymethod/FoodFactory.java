package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

public class FoodFactory {

    private FoodFactory()
    {

    }

    public static Food getFood(String animalName)
    {
           Food food = null;
           switch(animalName)
           {
               case "zebra" :
                   food = new Hay(100);//COMPILES but BAD Habit
                   break;

               case "rabbit" :
                   food = new Pellets(5);
                   break;

               case "goat" :
                   food = new Pellets(30);
                   break;

               case "polar bear" :
                   food = new Fish(10);
                   break;
                   
               default :
                   // Good practice to throw an exception if no matching subclass could be found
                   throw new UnsupportedOperationException("Unsupported animal: "+animalName);

           }
           return food;
    }
}

package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.abstractfactory;

public enum CarType
{
    MICRO("Small"){
        //@Override
        void printDetails() { System.out.println(" Volkswagen UP or similar"); }
    },
    MINI("Medium"){
        //@Override
        void printDetails() { System.out.println(" Volkswagen Golf or similar"); }
    },
    LUXURY("Premium"){
        //@Override
        void printDetails() { System.out.println(" BMW 4 Series or similar"); }
    },
    LARGE("SUVs"){

    };

    private String category;

    //abstract void printDetails();

    void printDetails() {
        System.out.println(" Fiat 500 XL or similar");
    }

    private CarType(String category)
    {
        this.category = category;
       // System.out.println("constructing enum CarType");
    }
}

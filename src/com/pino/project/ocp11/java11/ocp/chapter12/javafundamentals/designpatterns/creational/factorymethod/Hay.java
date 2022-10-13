package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

class Hay extends Food {

    Hay(int quantity) {
        super(quantity);
    }

    @Override
    protected void consumed() {
        System.out.println("Hay eaten: "+getQuantity());
    }

}


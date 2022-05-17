package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

class Pellets extends Food {

    Pellets(int quantity) {
        super(quantity);
    }

    @Override
    protected void consumed() {
        System.out.println("Pellets eaten: "+getQuantity());
    }

}


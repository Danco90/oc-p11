package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

abstract class Food {

    private int quantity;

    Food(int quantity) {
        this.quantity = quantity;
    }

    int getQuantity() {
        return quantity;
    }

    abstract void consumed();

}

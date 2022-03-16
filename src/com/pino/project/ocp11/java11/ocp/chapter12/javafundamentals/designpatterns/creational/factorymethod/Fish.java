package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.factorymethod;

class Fish extends Food {
    
    Fish(int quantity) {
        super(quantity);
    }

    @Override
    protected void consumed() {
        System.out.println("Fish eaten: "+getQuantity());
    }

}


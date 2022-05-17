package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.builder;

import java.util.Arrays;
import java.util.List;

/**
 * Creating Animal with a poor-quality Builder pattern
 */
public class AnimalUser {

    public static void main(String[] args) {
        Animal duck = new AnimalBuilder()
                .species("duck")
                .age(4)
                .favoriteFoods(Arrays.asList("grass","fish"))
                .build();

        Animal flamingo = new AnimalBuilder()
                .species("flamingo")
                .favoriteFoods(Arrays.asList("algae","insects"))
                .build();

        List<Animal> animals = List.of(duck, flamingo);

        System.out.println("Created :" +animals);

    }
}

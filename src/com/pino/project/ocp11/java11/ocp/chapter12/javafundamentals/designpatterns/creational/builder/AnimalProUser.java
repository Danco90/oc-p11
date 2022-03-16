package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.builder;

import java.util.Arrays;
import java.util.List;

/**
 * Creating Animal with an efficient Builder pattern
 */
public class AnimalProUser {

    public static void main(String[] args) {
        AnimalPro duck = new AnimalPro.AnimalProBuilder()
                .species("duck")
                .age(4)
                .favoriteFoods(Arrays.asList("grass","fish"))
                .build();

        AnimalPro flamingo = new AnimalPro.AnimalProBuilder()
                .species("flamingo")
                .favoriteFoods(Arrays.asList("algae","insects"))
                .build();

        List<AnimalPro> animals = List.of(duck, flamingo);

        System.out.println("Created :" +animals);

    }
}

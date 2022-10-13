package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.builder;

import java.util.List;

/*
 * AnimalBuilder is mutable, whereas Animal is IMMUTABLE.
 * AnimalBuilder and Animal are TIGHTLY COUPLED, which means that they are HIGHLY Dependent
 * whereas AnimalBuilder is LOOSELY COUPLED with its caller and
 * it prevents the telescoping constructor anti-pattern (*) from forming
 *
 * (*) Problem of constructor growing too large
 */
public class AnimalBuilder {
    private String species;
    private int age;
    private List<String> favoriteFoods;

    public AnimalBuilder species(String species) {
        this.species = species;
        return this;
    }
    public AnimalBuilder age(int age) {
        this.age = age;
        return this;
    }
    public AnimalBuilder favoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }

    public Animal build() { // returns an IMMUTABLE Animal object
        return new Animal(species, age, favoriteFoods);
    }

}


package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.builder;

import java.util.List;

/**
 * Immutable class
 * - No Setters defined.
 * - All properties are set through constructor only
 * - All instance variable marked private final
 * - No referenced mutable object can be modified or accessed directly
 * - Methods can't be overridden thanks to private constructor
 *   and the application of the BUILDER PATTERN
 */
public class AnimalPro {
    private final String species;
    private final int age;
    private final List<String> favoriteFoods;

    private AnimalPro(AnimalProBuilder builder) {
        this.species = builder.species;
        this.age = builder.age;
        //this.favoriteFoods = favoriteFoods;//NOOO! IT MAKES CLASS MUTABLE The caller has the ability to change the LIST
        this.favoriteFoods = new java.util.ArrayList<String>(builder.favoriteFoods);//YES
    }

    public String getSpecies() {//THREAD-SAFE as String is already Immutable by definition
        return species;
    }

    public int getAge() {
        return age;
    }

    //public List<String> getFavoriteFoods() { // MAKES CLASS MUTABLE as its list size can be modified.
    //	return favoriteFoods;//NEVER return the List reference to the user.
    //}

    public int getFavoriteFoodCount() {
        return favoriteFoods.size();
    }

    public String getFavoriteFood(int index) {
        return favoriteFoods.get(index);
    }

    public static class AnimalProBuilder {
        private String species;
        private int age;
        private List<String> favoriteFoods;

        public AnimalProBuilder() {
           
        }

        public AnimalProBuilder species(String species) {
            this.species = species;
            return this;
        }
        public AnimalProBuilder age(int age) {
            this.age = age;
            return this;
        }
        public AnimalProBuilder favoriteFoods(List<String> favoriteFoods) {
            this.favoriteFoods = favoriteFoods;
            return this;
        }

        public AnimalPro build() { // returns an IMMUTABLE Animal object
            AnimalPro animal = new AnimalPro(this);
            validateAnimalObject(animal);
            return animal;
        }

        private void validateAnimalObject(AnimalPro animal) {//Invariants
            if(animal.age < 0)
                throw new IllegalArgumentException("Validation FAILURE: age cannot be negative");

            if(animal.favoriteFoods == null) {//Validation
                throw new RuntimeException("favoriteFoods is required");
            }
        }

    }

    @Override
    public String toString() {
        return "AnimalPro{" +
                "species='" + species + '\'' +
                ", age=" + age +
                ", favoriteFoods=" + favoriteFoods +
                '}';
    }
}


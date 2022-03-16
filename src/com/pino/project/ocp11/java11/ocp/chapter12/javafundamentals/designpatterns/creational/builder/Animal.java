package com.pino.project.ocp11.java11.ocp.chapter12.javafundamentals.designpatterns.creational.builder;

import java.util.List;

/**
 * Immutable class
 * - No Setters defined.
 * - All properties are set through constructor only
 * - All instance variable marked private final
 * - No referenced mutable object can be modified or accessed directly
 * - Methods can't be overridden thanks to final class. This DOES LIMIT the usage of the class.
 *   Therefore, making the constructor private and applying the FACTORY PATTERN might be more advisable.
 */
public final class Animal {
    private final String species;
    private final int age;
    private final List<String> favoriteFoods;

    public Animal(String species, int age, List<String> favoriteFoods) {
        this.species = species;
        this.age = age;
        if(favoriteFoods == null) {//Validation
            throw new RuntimeException("favoriteFoods is required");
        }
        //this.favoriteFoods = favoriteFoods;//NOOO! IT MAKES CLASS MUTABLE The caller has the ability to change the LIST
        this.favoriteFoods = new java.util.ArrayList<String>(favoriteFoods);//YES
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

}


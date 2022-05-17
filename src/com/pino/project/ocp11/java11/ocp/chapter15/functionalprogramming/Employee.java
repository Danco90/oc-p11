package com.pino.project.ocp11.java11.ocp.chapter15.functionalprogramming;

import java.util.Objects;

public class Employee {

    private String name, title;
    private int age;
    private double salary;

    public Employee(String name, String title, int age, double salary) {
        this.name = name;
        this.title = title;
        this.age = age;
        this.salary=salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name) && Objects.equals(title, employee.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", age=" + age +
                '}';
    }
}

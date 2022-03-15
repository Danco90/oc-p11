package com.pino.project.ocp11.java11.ocp.chapter15.functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author Matteo
 * @description Write a task capable to calculate average salary of all employee in the list
 * Make use of stream API
 */
public class StreamPracticalExercise2 {

    /**
     * @description Here is a traditional way to loop through the list and construct a data map.
     * @param employeeList
     * @return
     */
    protected double calculateAverage(List<Employee> employeeList) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            sum += employee.getSalary();
            count++;
        }
        return (double) sum / count;
    }

    /**
     * @description Approach with java.util.Stream API, which is simpler and shorter
     * @param employeeList
     * @return
     */
    protected double calculateAveragePro(List<Employee> employeeList) {
         OptionalDouble optionalDouble =
                 employeeList.stream()
                         .mapToDouble(employee -> employee.getSalary())
                         .average();

        return optionalDouble.getAsDouble();
    }

    public static void main(String...args) {
          List<Employee> employeeList = List.of(
                  new Employee("Matteo","Tech lead",31, 3500),
                  new Employee("Daniele","Manager",35, 2700),
                  new Employee("Elio","Developer",38, 3700),
                  new Employee("Andrea","Developer",35, 3000)
          );
          System.out.println("Average salary EUR : "+ new StreamPracticalExercise2().calculateAverage(employeeList));
          System.out.println("Average salary EUR : "+ new StreamPracticalExercise2().calculateAveragePro(employeeList));
    }
}

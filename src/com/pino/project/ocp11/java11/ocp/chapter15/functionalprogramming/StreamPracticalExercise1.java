package com.pino.project.ocp11.java11.ocp.chapter15.functionalprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Matteo
 * @description Write a task capable to group an array of employee records into a data map organized by job title.
 * Make use of stream API (ref. https://blog.devgenius.io/15-practical-exercises-help-you-master-java-stream-api-3f9c86b1cf82 )
 */
public class StreamPracticalExercise1 {

    /**
     * @description Here is a traditional way to loop through the list and construct a data map.
     * @param employeeList
     * @return
     */
    Map<String, List<Employee>> groupByJobTitle(List<Employee> employeeList) {
        Map<String, List<Employee>> resultMap = new HashMap<>();
        for (int i = 0; i < employeeList.size(); i++)
        {   Employee employee = employeeList.get(i);
            List<Employee> employeeSubList = resultMap.getOrDefault(employee.getTitle(), new ArrayList<Employee>());
            employeeSubList.add(employee);
            resultMap.put(employee.getTitle(), employeeSubList);
        }
        return resultMap;
    }

    /**
     * @description Approach with java.util.Stream API, which is simpler and shorter
     * @param employeeList
     * @return
     */
    Map<String, List<Employee>> groupByJobTitleWithStream(List<Employee> employeeList) {
        return employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getTitle));//Instance Method References :: on an instance to be determined at runtime
              //.collect(Collectors.groupingBy((e -> e.getTitle())));//equivalent LAMBDA expr
    }

    public static void main(String...args) {
          List<Employee> employeeList = List.of(
                  new Employee("Matteo","Tech lead",31, 3500),
                  new Employee("Daniele","Manager",35, 2700),
                  new Employee("Elio","Developer",38, 3700),
                  new Employee("Andrea","Developer",35, 3000)
          );
          System.out.println(new StreamPracticalExercise1().groupByJobTitle(employeeList));
          System.out.println(new StreamPracticalExercise1().groupByJobTitleWithStream(employeeList));
    }
}

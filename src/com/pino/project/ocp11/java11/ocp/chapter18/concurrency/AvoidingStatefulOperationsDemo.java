package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AvoidingStatefulOperationsDemo {

    public List<Integer> addValuesStatefully(IntStream source) {
        var data = Collections.synchronizedList(new
                ArrayList<Integer>());
        source.filter(s -> s% 2 == 0)
                .forEach(i ->
                {
                    data.add(i); }); // STATEFUL : DON'T DO THIS!
        return data;
    }

    public List<Integer> addValuesStatefullyAndOrdered(IntStream source) {
        var data = Collections.synchronizedList(new
                ArrayList<Integer>());
        source.filter(s -> s% 2 == 0)
                .forEachOrdered(i ->  // forces the parallel stream to be serial potentially losing cnsurrency enhancement
                {
                    data.add(i); }); // STATEFUL : DON'T DO THIS!
        return data;
    }

    public List<Integer> addValuesStateless(IntStream source) {
        return source.filter(s -> s% 2 == 0)
                .boxed()// boxes each element into an Integer because the following List cannot accept primitives
                .collect(Collectors.toList());//Collects all the results into a new list
    }

    public static void main (String[] args){
        AvoidingStatefulOperationsDemo demo = new AvoidingStatefulOperationsDemo();
        var list = demo.addValuesStatefully(IntStream.range(1, 11));
        System.out.println(list); //[2, 4, 6, 8, 10]

        list = demo.addValuesStatefully(IntStream.range(1, 11)
                .parallel());
        System.out.println(list); //[6, 8, 10, 2, 4] with parallel stream the order becomes random

        list = demo.addValuesStatefullyAndOrdered(IntStream.range(1, 11)
                .parallel());
        System.out.println(list); //[2, 4, 6, 8, 10] thanks to the foreEachOrdered but with the cost of lower performance

        list = demo.addValuesStateless(IntStream.range(1, 11));
        System.out.println(list); //[2, 4, 6, 8, 10] same result as parallel stream

        list = demo.addValuesStateless(IntStream.range(1, 11)
                .parallel());
        System.out.println(list); //[2, 4, 6, 8, 10] same result as serial stream 


    }
}

package com.pino.project.ocp11.java11.ocp.chapter14.genericsandcollections.methodreferences;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferencesDemo
{
    public static void main(String...args)
    {
        //1. STATIC Method References
        Consumer<List<Integer>> mr = Collections::sort;
        Consumer<List<Integer>> lambdaEqualsToMr = e -> Collections.sort(e);

        //2. Instance Methods referring to a specific instance
        String str = "abc";
        Predicate<String> mr2 = str::startsWith;
        Predicate<String> lambdaEqualsToMr2 = s -> str.startsWith(s);
        List<String> myList = Stream.of("abcletta","abc","def")
                .collect(Collectors.toList());
        System.out.println("Pre removal "+ myList);
        
        myList.removeIf(mr2);
        System.out.println("Post removal with Inst. method references  str::startsWith   : "+ myList);
        myList = Stream.of("abcletta","abc","def").collect(Collectors.toList());
        myList.removeIf(lambdaEqualsToMr2);
        System.out.println("Post removal with equivalent lambda s -> str.startsWith(s)   : "+ myList);


        Predicate<String> lambdaNotEqualsToMr2 = s -> s.startsWith(str);
        myList = Stream.of("abcletta","abc","def").collect(Collectors.toList());
        myList.removeIf(lambdaNotEqualsToMr2);
        System.out.println("\nPost removal with NOT equivalent lambda s -> s.startsWith(str) : "+ myList);
        System.out.println("That's because is NOT ALWAYS a method reference COMPATIBLE for each single lambda. But there is ALWAYS a Lambda equivalent for each method reference!");


        //3. Instance Methods with the instance supplied at runtime


        //4. Constructor References

    }
}

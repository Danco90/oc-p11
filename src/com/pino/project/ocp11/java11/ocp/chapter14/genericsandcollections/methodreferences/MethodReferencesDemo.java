package com.pino.project.ocp11.java11.ocp.chapter14.genericsandcollections.methodreferences;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
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
        System.out.println("Pre removal "+ myList);//[abcletta, abc, def]
        
        myList.removeIf(mr2);
        System.out.println("Post removal with Inst. method references  str::startsWith   : "+ myList);//[abcletta, def]
        myList = Stream.of("abccletta","abc","def").collect(Collectors.toList());
        myList.removeIf(lambdaEqualsToMr2);
        System.out.println("Post removal with equivalent lambda s -> str.startsWith(s)   : "+ myList);//[abcletta, def]


        Predicate<String> lambdaNotEqualsToMr2 = s -> s.startsWith(str);
        myList = Stream.of("abccletta","abc","def").collect(Collectors.toList());
        myList.removeIf(s -> s.startsWith(str));
        System.out.println("\nPost removal with NOT equivalent lambda s -> s.startsWith(str) : "+ myList);//[def]
        System.out.println("That's because is NOT ALWAYS a method reference COMPATIBLE for each single lambda. But there is ALWAYS a Lambda equivalent for each method reference!");


        //3. Instance Methods with the instance determined/supplied at runtime :
        //   Java uses the param supplied at runtime as the instance on which the isEmpty() is called
        Predicate<String> mr3 = String::isEmpty; //it is not a static method but an instance one which takes no args
        Predicate<String> lambdaEqualsToMr3 = s -> s.isEmpty();
        myList = Stream.of("abc","","cletta","").collect(Collectors.toList());
        System.out.println("\nPre removal "+ myList);//[abc, , cletta, ]

        myList.removeIf(mr3);
        System.out.println("Post removal with Inst. supplied at runtime  String::isEmpty  : "+ myList);//[abc, cletta]
        myList = Stream.of("abc","","cletta","").collect(Collectors.toList());
        myList.removeIf(lambdaEqualsToMr3);
        System.out.println("Post removal with equivalent lambda   s -> s.isEmpty()        : "+ myList);//[abc, cletta]


        //4. Constructor References
        Supplier<ArrayList> mr4 = ArrayList::new;
        Supplier<ArrayList> lambdaEqualsToMr4 = () -> new ArrayList();

        //Removing Conditionally  removeIf(Predicate<? super E> filter) : allows to specify what should be deleted from a collection
        myList = Stream.of("A","a","Ah","Hanna").collect(Collectors.toList());
        Predicate<String> lambdaNotEquivalentlsAnyMr = s -> s.startsWith("A");//It CAN'T be replaced with method reference
        //Predicate<String> whichMethodReference = ::startsWith// because startsWith takes a param that isn't s

        //Updating CONDITIONALLY
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny","Bus tour");//
        favorites.put("Jenny","Tram");//replacng the existing value UNCONDITIONALLY
        System.out.println("\nPre conditional update  : "+ favorites);//{Jenny=Tram}
        favorites.put("Jenny","Bus tour");//
        //Using putIfAbsent() you can skip the value if it is already set to a NON-null value
        favorites.putIfAbsent("Jenny","Tram");//NO EFFECT : doesn't replace the already existing value for that key
        favorites.putIfAbsent("Tom","Tram");
        System.out.println("Post conditional update putIfAbsent : "+ favorites);//{Tom=Tram, Jenny=Tram}

        //Using merge() you can determine which value should be used  . It takes in input three params merge(key, value, mapper)
        BiFunction<String, String, String>   mapper = (v1, v2) -> v1.length() > v2.length() ? v1 : v2 ; // both values !=null else mapper funct does not get called
        String jenny = favorites.merge("Jenny","Skyride", mapper);// Skyride is not > Bus Tour so it is not being updated
        System.out.println("Post conditional update (merge)  Jenny = "+ jenny);//Bus Tour
        String tom = favorites.merge("Tom","Skyride", mapper);// the value is replaced because greater than Tram
        System.out.println("Post conditional update (merge)  Tom = "+ tom);//Skyride

        //RULE A: merge() also has a logic for NULL values or Missing keys: it DOES NOT call BiFunction AT ALL and simply use new value
        favorites = new HashMap<>();
        favorites.put("Sam",null);
        System.out.println("\nPre conditional update (put) with NULL value "+favorites);//
        favorites.merge("Pino","Skyride", mapper);
        System.out.println("Post conditional update (merge) with missing key "+favorites);//
        favorites.merge("Sam","Skyride", mapper);
        System.out.println("Post conditional update (merge) with NULL value "+favorites);//

        //RULE B: When there are at least two Actual value to deciede (both !=null) then the mapping function gets called by merge

        //RULE C: when merge() calles mapping func. which return null, the key is being removed from the map and its value too
        BiFunction<String, String, String> mapper2 = (v1, v2) -> null;
        favorites = new HashMap<>();
        favorites.put("Jenny","Bus Tour");
        favorites.put("Tommy","Bus Tour");
        System.out.println("\nPre conditional update (merge) with NULL returned by mapping func. "+favorites);//{Jenny=Bus Tour, Tommy=Bus Tour}
        favorites.merge("Jenny","Skyride", mapper2);
        System.out.println("Post conditional update (merge) ith NULL returned by mapping func. "+favorites);//{Tommy=Bus Tour}
        favorites.merge("Sam","Skyride", mapper2);//Sam is added because no map func called for missing/new keys
        System.out.println("Post conditional update (merge) ith NULL returned by mapping func. "+favorites);//{Tommy=Bus Tour, Sam=Skyride}


    }
}

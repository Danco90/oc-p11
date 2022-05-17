package com.pino.project.ocp11.java11.ocp.chapter15.functionalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringOverview {

    public static void main(String[] args) {

        //Tricky example : Watch from omitting generic with functional interfaces.
        Object obj = new Object();
        Predicate predcate1 = obj::equals;// OK since it is a method ref. with instance already available
//		Predicate predcate2  = Object::equals;//DOES NOT COMPILE since equals is not static
        Predicate predcate3 = (Object obj3) -> obj3.equals(null);

        //How to create a STREAM
        //CASE 1) Factory Stream.of()
        //static<T> Stream<T> empty(T t)
        Stream<String> empty = Stream.empty();//(*)
        //static<T> Stream<T> empty()
        Stream<Integer> singleElement = Stream.of(1);//(*)
        //static<T> Stream<T> of(T... values) VARARGS
        Stream<Integer> fromArray = Stream.of(1, 2, 3);//(*)
        //(*)nb: The Stream is lazily-evaluated : declared now and evaluated later.
        System.out.println(empty);//(**)
        //(**)nb: The Stream is not used as no terminal operation is called on it yet.

        //------ TERMINAL OPERATIONS : work only with finite stream, whereas hang with infinite streams

        //long count()
        System.out.println(empty.count());//the terminal operation applied to the source is destructive, which means it ends the stream.
        System.out.println(singleElement.count());
        System.out.println(fromArray.count() + "\n");
//		System.out.println(empty.count());//the stream is no more available and it throws an Exception

        //CASE 2) Starting from a collection : applying .stream() to a collection
        List<String> list = Arrays.asList("a", "b", "c");
        //2.a) creating a SERIAL stream from a collection
        Stream<String> fromList = list.stream();
        //2.b) creating a PARALLEL stream from a collection
        Stream<String> fromListParallel = list.parallelStream();

        //CASE 3) Starting from another stream : applying .parallel() to a serial stream
        Stream<String> fromStreamParallel = fromList.parallel();

        //CASE 4) Infinite Stream
        //4.1) static<T> Stream<T> generate(Supplier<T> s)
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<String> infinity = Stream.generate(() -> "chimp");
        //4.2) static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);//It gives more control.

        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        //Optional<T> min(Comparator<? super T> comparator), where Comparator<T> is a functional interface
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());//it overrides the compare() method
        min.ifPresent(System.out::println);//ape
        s = Stream.of();
        s.min((s1, s2) -> s1.length() - s2.length())
                .ifPresent(System.out::println);//NOT PRESENT: So ifPresent DOES NOT invoke the consumer

        //Optional<T> max(Comparator<? super T> comparator)
        Optional<?> minEmpty = Stream.empty().max((s1, s2) -> 0);//Since empty Stream, The Comparator is never called
        System.out.println(minEmpty.isPresent()); // false

        //Optional<T> findAny()
        System.out.println("\nfindAny()");
        Stream<String> s1 = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        s1.findAny()//returns a not empty Optional<String>
                .ifPresent(System.out::println);// monkey (in the serial stream always the first )
        infinite.findAny()
                .ifPresent(System.out::println);// chimp is definitely the one and only one it can finds repeatedly

        //Optional<T> findFirst() in the serial stream always the first, whereas in parallel will find the one whose thread is running
        System.out.println("\nfindFirst()");
        Stream<Integer> finiteIntegers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> infiniteIntegers = Stream.iterate(1, i -> i + 1);
        Stream<Integer> infiniteParallelIntegers = Stream.iterate(1, i -> i + 1).parallel();
        finiteIntegers.findFirst().ifPresent(System.out::println);
        infiniteIntegers.findFirst().ifPresent(System.out::println);
        infiniteParallelIntegers.findFirst().ifPresent(System.out::println);//it might be !=1

        //void forEach(Consumer<? super T> action) :IT IS NOT A REDUCTION; on INFINITE stream DOES NOT TERMINATE
        Stream<String> sf = Stream.of("Monkey", "Gorilla", "Bonobo");
        //A) foreach called on a stream
        sf.forEach(System.out::print); // MonkeyGorillaBonobo
        System.out.println();
        //B) foreach called on a Collection
        List<String> myList = Arrays.asList("Monkey", "Gorilla", "Bonobo");
        myList.forEach(System.out::println);

        //NB: Streams CAN'T USE a TRADITIONAL for-each loop because they don't implement Iterable
//		Stream ss = Stream.of(1);
//		for (Integer i: s) {} // DOES NOT COMPILE

        //reduce() : It is a REDUCTION that COMBINES a stream INTO a SINGLE OBJ
        //Signature1 :  T reduce(T identity, BinaryOperator<T> accumulator)
        //NB: No need to return optional since at worst the identity "" is being returned
        System.out.println("\n-- REDUCTION --");
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream/*.peek(System.out::println).limit(2)*/
                .reduce("", (ss, c) -> ss + c);
//						.reduce("", String::concat);//this method reference as an alternative to the previous lambda
        System.out.println(word); // wolf

        Stream<Integer> stream2 = Stream.of(3, 5, 6);
        System.out.println(stream2.reduce(1, (a, b) -> a * b));// ((1*3)*5)*6

        //Signature2 :  Optional<T> reduce(BinaryOperator<T> accumulator) : when you don't specify an identity, java returns an Optional
        //The accumulator is only invoked once needed.
        stream2 = Stream.of(3, 5, 6);//rewritten example
        Optional<?> res = stream2.reduce((a, b) -> a * b);//In this case the Identity is not needed
        System.out.println(res);//The accumulator is applied
        //(*)NB : if the stream is empty, en empty Optional is returned; if the stream has one elem the accum is not even called and the only elem is returnedM;
        //Whereas if the stream has more than one element, the accumulator is applied to combine them.
        System.out.println("-- Cases When the accumulator is called for reduce() --");
        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty1 = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty1.reduce(op)
                .ifPresent(System.out::println);//no output
        System.out.println(" No output and the accum is not called");
        oneElement.reduce(op).ifPresent(System.out::print);// 3 and the accum is not called
        System.out.println(" and the accum is not called");
        threeElements.reduce(op).ifPresent(System.out::print);// 90  and the accum is called
        System.out.println(" and the accum is called");

        //Signature3 : <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator,BinaryOperator<U> combiner)
        //It's used for PARALLEL streams, as java assumes that a stream might be parallel
        stream2 = Stream.of(3, 5, 6);//rewritten example
        System.out.println(stream2.reduce(1, op, op)); // 90 (*)
        //(*) BinaryOperator<U> is a special case of BiFunction<U,U,U>

        // collect() : it is a MUTABLE reduction, which is more efficient as keep using same mutable obj while accumulating
        System.out.println("-- collect(), a mutable reduction --");
        //Signature1 : <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator,
        //BiConsumer<R, R> combiner)
        System.out.println("Signature 1:  <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator,BiConsumer<R, R> combiner");
        //Example 1.1
        Stream<String> stream01 = Stream.of("w", "o", "l", "f");
        StringBuilder word1 = stream01.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(word1);//wolf
        //The combiner is not triggered in serial
        //but just in parallel
        Stream<String> parallel01 = Stream.of("w", "o", "l", "f").parallel();
        StringBuilder wordp = parallel01.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(wordp);//wolf (unpredictable)

        //Example 1.2
        Stream<String> stream02 = Stream.of("w", "o", "l", "f", "z");
        TreeSet<String> set = stream02.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);//(*)
        System.out.println(set);// [f, l, o, w,z] sorted natural order
        //(*)NB : The combiner adds all of the elements of one TreeSet to another in case the operations were done in parallel and
        //need to be merged.

        //Signature2 : <R,A> R collect(Collector<? super T, A,R> collector)
        System.out.println("Signature 2:  <R,A> R collect(Collector<? super T, A,R> collector)");
        //Example 2.1 - SORTED in natural order
        Stream<String> stream03 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set2 = stream03.collect(Collectors.toCollection(TreeSet::new));
        System.out.println("collect(Collectors.toCollection(TreeSet::new) when sorting is important");
        System.out.println(set2); // [f, l, o, w]
        //Example 2.2 - When sorting is not important the CODE can be SHORTER
        System.out.println("\ncollect(Collectors.toSet()) when sorting is NOT important");
        Stream<String> stream04 = Stream.of("w", "o", "l", "f");
        Set<String> set4 = stream04.collect(Collectors.toSet());//Not sure what implementation of set is chosen. Probably HashSet
        System.out.println(set4); // [f, w, l, o] random

    }

}
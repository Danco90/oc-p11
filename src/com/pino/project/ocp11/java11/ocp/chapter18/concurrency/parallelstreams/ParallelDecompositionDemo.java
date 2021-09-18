package com.pino.project.ocp11.java11.ocp.chapter18.concurrency.parallelstreams;

import java.util.List;

public class ParallelDecompositionDemo {

    private static int doWork(int input)
    {     //System.out.println(Thread.currentThread()+" starts doWork()\n");
          try {
              Thread.sleep(5000);//5 sec
          }  catch (InterruptedException e) {}
          return input;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List.of(1,2,3,4,5)//static method of the List interface
            .stream()//serial
            .map(w -> doWork(w))
            .forEach(s -> System.out.print(s + " "));//1 2 3 4 5 ordered because we are using a serial stream
        System.out.println();
        var timeTaken =  (System.currentTimeMillis()-start)/1000;
        System.out.println("Time: "+timeTaken+" seconds");//Time: 25 seconds
        System.out.println();
        
        //Example with parallel stream
        start = System.currentTimeMillis();
        List.of(1,2,3,4,5)//static method of the List interface
                .parallelStream()//parallel
                .map(w -> doWork(w))// applied concurrently  and assign each item to a different thread, such as ForkJoinPool.commonPool-worker-5
                .forEach(s -> System.out.print(s + " "));//applied concurrently too.
        // Results are no longer ordered or unpredictable with parallel stream
        System.out.println();
        timeTaken =  (System.currentTimeMillis()-start)/1000;
        System.out.println("Time: "+timeTaken+" seconds");//Time: 5 seconds if the system had enough CPUs (nCore >= elements to be processed) .
        //for fewer processors, it might output 10 seconds (like my MacBook Pro 2012 with 4 cores), 15 seconds, or some other value.
        //The map() and forEach() operations on a parallel stream
        // are equivalent to submitting multiple Runnable lambda expressions
        // to a pooled executor and then waiting for the results.

        //Ordering forEach Results  with forEachOrdered()
        start = System.currentTimeMillis();
        List.of(5,2,1,4,5)//static method of the List interface
                .parallelStream()//parallel
                .map(w -> doWork(w))// still performs decomposition in 5 seconds (actually 10 in my MacBook)
                .forEachOrdered(s -> System.out.print(s + " "));//5 2 1 4 5 even though it performs the print with a single thread
        // It prints results in the order that they are defined in the stream
        //NB: forEachOrdered() forces our stream into a single threaded process.
        System.out.println();
        timeTaken =  (System.currentTimeMillis()-start)/1000;
        System.out.println("Time: "+timeTaken+" seconds");//Time: 10 seconds 
    }
}

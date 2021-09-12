package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager {
    private void removeLions(){
        System.out.println("Removing lions");
    }
    private void cleanPen(){
        System.out.println("Cleaning the pen");
    }
    private void addLions(){
        System.out.println("Adding lions");
    }
    /*
     +Good within a single thread the results are ordered
     -Bad among multiple workers the result is enterely random
     */
    public void performTask(){
        removeLions();
        cleanPen();
        addLions();
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4)  ;
            var manager = new LionPenManager();
            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
            for (int i=0; i < 4; i++)
                service.submit(() ->
                       manager.performTaskWithCyclicBarriers(c1, c2));
        } finally {
              if (service !=  null)
                  service.shutdown();
        }

    }

    /*
     *  All the results are organized.
     * 
     * Removing lions
        Removing lions
        Removing lions
        Removing lions
        Cleaning the pen
        Cleaning the pen
        Cleaning the pen
        Cleaning the pen
        *** Pen Cleaned!
        Adding lions
        Adding lions
        Adding lions
        Adding lions
     */
    public void performTaskWithCyclicBarriers(CyclicBarrier c1, CyclicBarrier c2){
        try{
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            // Handle checked exceptions here
            System.out.println("Caught and swallowed exception : "+e);
        }

    }
}

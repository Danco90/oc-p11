package com.pino.project.ocp11.java11.ocp.chapter18.concurrency.liveness;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food {}//resource
class Water {}//resource

public class FoxDeadlock {
    public void eatAndDrink(Food food, Water water) {
        synchronized(food) {
            System.out.println(Thread.currentThread().getName()+" Got Food!");//pool-1-thread-1 Got Food!
            move();
            synchronized(water) {
                System.out.println(Thread.currentThread().getName()+" Got Water!");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized(water) {
            System.out.println(Thread.currentThread().getName()+" Got Water!");//pool-1-thread-2 Got Water!
            move();
            synchronized(food) {
                System.out.println(Thread.currentThread().getName()+" Got Food!");
            }
        }
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            // Handle exception
        }
    }
    public static void main(String[] args) {
        // Create participants and resources
        FoxDeadlock foxy = new FoxDeadlock();
        FoxDeadlock tails = new FoxDeadlock();
        Food food = new Food();
        Water water = new Water();
        //Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);//10 threads
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if(service != null) service.shutdown();
        }
    }

}

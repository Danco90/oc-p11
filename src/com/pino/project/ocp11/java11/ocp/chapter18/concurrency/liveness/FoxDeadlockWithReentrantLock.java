package com.pino.project.ocp11.java11.ocp.chapter18.concurrency.liveness;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Matteo
 * @update 15-Mar-22
 */
public class FoxDeadlockWithReentrantLock {

    private Lock food = new ReentrantLock(true);
    private Lock water = new ReentrantLock(true);

    public void eatAndDrink() {
        food.lock();
        System.out.println(Thread.currentThread().getName()+" Got Food, waiting to get water.");//pool-1-thread-1 Got Food!
        move();

        water.lock();
        System.out.println(Thread.currentThread().getName()+" Got Water!");

        water.unlock();
        food.unlock();

    }

    public void drinkAndEat() {
        water.lock();
        System.out.println(Thread.currentThread().getName()+" Got Water, waiting to get food.");//pool-1-thread-2 Got Water!
        move();

        food.lock();
        System.out.println(Thread.currentThread().getName()+" Got Food!");

        food.unlock();
        water.unlock();
    }

    public void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.out.println("That custom thread was unable to move to the next resource due to swallowed exception:"+ ex.getMessage());
        }
    }
    public static void main(String[] args) {
       FoxDeadlockWithReentrantLock deadlock = new FoxDeadlockWithReentrantLock();
       //WE DO NOT NEED different objects to simulate two foxes, but one for the lambda or method reference on a particular instance.
        //The reason is the synchronized block with the object monitor is gone, and the two implementations of Lock interface will act as monitor.

        //Process data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);//10 threads
            service.submit(() -> deadlock.eatAndDrink());
            deadlock.checkLockForFood();
            deadlock.checkLockForWater();

            service.submit(deadlock::drinkAndEat);
            deadlock.checkLockForWater();
            deadlock.checkLockForFood();

        } finally {
            if(service != null) service.shutdown();
        }
    }

    private void checkLockForFood() {
        checkLock(food, "Food");
    }

    private void checkLockForWater() {
        checkLock(water, "Water");
    }

    private void checkLock(Lock lock, String lockName) {
        try {
            if (lock.tryLock(50, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " apparently obtained Lock for "+lockName+", but someone else is entering protected code"); //It'll print always the main thread since this is not a protected area
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("That custom thread was unable to acquire lock for "+lockName+", doing something else (It might have entered a DEADLOCK)");
            }
        } catch (InterruptedException e) {
            System.out.println("That custom thread was unable to acquire lock for "+lockName+", due to swallowed exception:"+e.getMessage());
        }
    }

}

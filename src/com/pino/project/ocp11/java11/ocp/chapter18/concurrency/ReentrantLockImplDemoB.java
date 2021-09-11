package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImplDemoB {
    private static int count2 = 0;
    public static void main(String[] args) throws InterruptedException {

        /*
         *Case B: 3 threads, one of which use a synch block and two use a Lock implementation
         * Possible output: random order

         main apparently obtained Lock, but someone else is entering protected code
        Thread-3, obtained lock, entered the protected area 1 and set count to :1
        Lock obtained, entering protected code
        Thread-4, obtained lock, entered the protected area 2 and set count to :2
        Lock obtained, entering protected code
        *
        * NB: One thread might never enter the protected area and increment the counter.
         */


        //Implementation #2 with a Lock
        Lock lock = new ReentrantLock();
        new Thread(() ->
        {
            try {
                printMessage1a(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " apparently obtained Lock, but someone else is entering protected code"); //It'll print always the main thread since this is not a protected area
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("That custom thread was unable to acquire lock, doing something else");
        }


        new Thread(() ->
                printMessage2a(lock)).start();
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else");
        }

        new Thread(() ->
                printMessage2a(lock)).start();
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Unable to acquire lock, doing something else");
        }

        System.out.println();


    }


    public static void printMessage1a(Object object) throws InterruptedException {
        synchronized(object) {
            //protected code
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 1 and set count2 to :"+(++count2));
        }
    }

    public static void printMessage2a(Lock lock) {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 2 and set count2 to :"+(++count2));
            Thread.sleep(1000);
            //protected code
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {//this ensure any acquired locks are properly released
            lock.unlock();
        }
    }


}

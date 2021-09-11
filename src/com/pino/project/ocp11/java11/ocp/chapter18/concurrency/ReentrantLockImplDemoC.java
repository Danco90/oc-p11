package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImplDemoC {
    private static int count3 = 0;
    public static void main(String[] args) throws InterruptedException {

        /*
         *Case C: 4 threads, two of which use synch block and two use a Lock implementation
         * Possible output: random order

         Thread-5, obtained lock, entered the protected area 2 and set count to :3
        That custom thread was unable to acquire lock, doing something else
        main attempts to obtain the Lock and enter the protected code 1
        main, obtained lock, entered the protected area 1 and set count to :4
        Thread-6, obtained lock, entered the protected area 1 and set count to :1
        Lock obtained, entering protected code
        Thread-7, obtained lock, entered the protected area 2 and set count to :2
        Lock obtained, entering protected code
        Thread-8, obtained lock, entered the protected area 2 and set count to :3
         */
        //Implementation #1 synchronized block
        Object obj = new Object();
        
        //Implementation #2 with a Lock
        Lock lock = new ReentrantLock();
        new Thread(() ->
        {
            try {
                printMessage1b(lock);
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

        System.out.println(Thread.currentThread().getName() + " attempts to obtain the Lock and enter the protected code 1");
        printMessage1b(obj);//the main thread might fail to obtain the lock.

        new Thread(() ->
                printMessage2b(lock)).start();
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
                printMessage2b(lock)).start();
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(" Unable to acquire lock, doing something else");
        }
    }
    

    public static void printMessage1b(Object object) throws InterruptedException {
        synchronized(object) {
            //protected code
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 1 and set count3 to :"+(++count3));
        }
    }

    public static void printMessage2b(Lock lock) {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 2 and set count3 to :"+(++count3));
            Thread.sleep(1000);
            //protected code
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {//this ensure any acquired locks are properly released
            lock.unlock();
        }
    }
}

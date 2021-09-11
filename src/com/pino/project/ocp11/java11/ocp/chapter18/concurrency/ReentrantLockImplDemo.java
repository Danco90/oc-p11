package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImplDemo {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        //Applying ReentrantLock Interface

        /*
         *Case A : 3 threads, 1 synch block and 1 Lock implementation
         *
         * Possible output: random order
        Thread-0, obtained lock, entered the protected area 1 and set count to :1
        Thread-1, obtained lock, entered the protected area 1 and set count to :2
        main apparently obtained Lock, but someone else is entering protected code
        Thread-2, obtained lock, entered the protected area 2 and set count to :3
        Lock obtained, entering protected code
         */


        //Implementation #1 synchronized block
        Object obj = new Object();
        new Thread (() ->
        {
            try {
                printMessage1(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //Implementation #2 with a Lock
        Lock lock = new ReentrantLock();
        new Thread (() ->
        {
            try {
                printMessage1(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        if(lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName()+" apparently obtained Lock, but someone else is entering protected code"); //It'll print always the main thread since this is not a protected area
            }  finally {
                lock.unlock();
            }
        }  else {
            System.out.println("That custom thread was unable to acquire lock, doing something else");
        }


        new Thread (() ->
                printMessage2(lock)).start();
        if(lock.tryLock(10, TimeUnit.SECONDS)) {
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
    


    public static void printMessage1(Object object) throws InterruptedException {
        synchronized(object) {
            //protected code
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 1 and set count to :"+(++count));
        }
    }

    public static void printMessage2(Lock lock) {
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+", obtained lock, entered the protected area 2 and set count to :"+(++count));
            Thread.sleep(1000);
            //protected code
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {//this ensure any acquired locks are properly released
            lock.unlock();
        }
    }
    
}

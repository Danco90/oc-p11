package com.pino.project.ocp11.java11.ocp.chapter18.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImplDemo {
    public static void main(String[] args) throws InterruptedException {
        //Applying ReentrantLock Interface
        //Implementation #1 synchronized block
        Object obj = new Object();
        new Thread (() ->
                printMessage1(obj)).start();

        //Implementation #2 with a Lock
        Lock lock = new ReentrantLock();
        new Thread (() ->
                printMessage2(lock)).start();
        if(lock.tryLock()) {
            try {
                System.out.println("Lock obtained, entering protected code");
            }  finally {
                lock.unlock();
            }
        }  else {
            System.out.println("Unable to acquire lock, doing something else");
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
    }


    public static void printMessage1(Object object) {
        synchronized(object) {
            //protected code
        }
    }

    public static void printMessage2(Lock lock) {
        try{
            lock.lock();

            //protected code
        } finally {//this ensure any acquired locks are properly released
            lock.unlock();
        }
    }
}

package com.pino.project.ocp11.java11.ocp.chapter14.genericsandcollections;

import java.util.*;

public class QueueImplementationsDemo {
    public static void main(String[] args) {
        //Queue is used when elements are added and remove in a specific order,
        //It is tipically used to sort elements prior to processing them
        //By default, a queue is assumed to be FIFO(first-in, first-out). Some queue implementations change this to use LIFO

        //Comparing Queue implementations
        //1. LinkedList : double-ended queue which stores elements in an hashtable.
        //double-ended means you can insert and remove in both the front and the back of the queue.
        //++benefit : it implements List, Queue
        //--tradeoff: it is less performant than a pure queue, such as ArrayDeque

        //2. ArrayDeque : pure double-ended RESIZABLE queue, introduced in java 6, which is more efficient than LinkedList
        //2A. Configured as a Queue (poll,offer,peek)
        /* Queue methods
         * Mathods		    |Description									|For queue | For stack | Throws exception on failure
         * boolean add(E e)	  Adds an element to the back of the queue 	 	Yes			No			Yes
         * 					  and returns true or throws an exception
         *
         *	E element()		  Returns next element or throws an exception    Yes			No			Yes
         * 					  if empty queue
         * boolean offer(E e) Adds an element to the back of the queue		Yes			No			No
         * 					  and returns whether successful
         *
         * E remove()		  Removes and returns next element or throws 	Yes			No			Yes
         * 					  an exception if empty queue
         *
         * E poll()			 Removes and returns next element or				Yes			No			No
         *					 returns null if empty queue
         * E peek()          Returns next element or returns null if         Yes			Yes			No
         * 					 empty queue
         * E pop()			Removes and returns next element or throws		No			Yes			Yes
         * 					an exception if empty queue
         *__________________________________________________________________________________
         */

        Queue<Integer> que = new LinkedList<>();
        System.out.println("1. LinkedList - Queue methods ::");
        System.out.println(que.offer(10));//true  [10]
        System.out.println(que.offer(4));//true	  [10]-[4]
        System.out.println(que.peek());//10		  [10]-[4]
        System.out.println(que.poll());//10 		  [4]	and remove it from the queue
        System.out.println(que.poll());//4 		  []    and remove it from the queue
        System.out.println(que.peek());//null
        System.out.println(que);//[]

        Queue<Integer> queue = new ArrayDeque<>();//Only Queue methods are visible, EXCEPT push()
        System.out.println("\n2. ArrayDeque - Queue methods ::");
        System.out.println(queue.offer(10));//adds 10 to the back of the queue and returns true
        System.out.println(queue);//[10]
        System.out.println(queue.offer(4));//true [10,4]
        System.out.println(queue.peek());//10
        System.out.println(queue);//[10,4]
        System.out.println(queue.poll());//10
        System.out.println(queue);//[4]
        System.out.println(queue.poll());//4
        System.out.println(queue.peek());//null
        System.out.println(queue.poll());//null
        try { System.out.println(queue.remove()); } catch(NoSuchElementException e) { System.out.println("Caught exception :"+e+" and swallowed");}

        //**TRICKY doubt: How to get an elem from the back of the queue ?

        //2B. Configured as a Stack (pop/push/peek)
        /*
         * ArrayDeque method
         *  void push()        Adds an element to the front of the queue.	Yes         Yes
         *  					That's what makes it a double-ended queue
         */
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        System.out.println("ArrayDeque method for Stack configuration :: push/pop/peek");
        stack.push(10);//void. it adds to the front
        System.out.println(stack);//[10]
        stack.push(4);
        System.out.println(stack);//[4,10]
        System.out.println(stack.peek());//4
        System.out.println(stack.poll());//4
        System.out.println(stack);//[10]
        System.out.println(stack.pop());//10
        System.out.println(stack);//[]
        System.out.println(stack.poll());//null
        try { System.out.println(stack.pop()); } catch(NoSuchElementException e) { System.out.println("Caught exception :"+e+" and swallowed");}



    }

}

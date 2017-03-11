/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicklysorting;


/******************************************************************************
 *
 *  A generic queue, implemented using a singly-linked list. Each queue
 *  element is of type T.
 *
 ******************************************************************************/

import java.util.NoSuchElementException;

public class Queue<T> {
    private int n;         // number of elements on queue
    private Node front;    // beginning of queue
    private Node rear;     // end of queue

    // helper linked list class
    private class Node {
        private T data;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        front = null;
        rear  = null;
        n = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return n;     
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
		}
        return front.data;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void enqueue(T item) {
        Node oldrear = rear;
        rear = new Node();
        rear.data = item;
        rear.next = null;
        if (isEmpty()) {
            front = rear;
		}
        else {
            oldrear.next = rear;
		}
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
		}
        T item = front.data;
        front = front.next;
        n--;
        if (isEmpty()) {
            rear = null;
		}
        return item;
    }

}

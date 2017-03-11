/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicklysorting;

import java.util.Random;

/**
 *
 * @author Ryan Boehm
 */
public class QuicklySorting {
    
    /**
     * Takes two queues that have the same elements, but in different order.
     * This function is a variation of quicksort that uses a random pivot in one queue to partition
     * both queues.  By ordering both queues, you can determine the matching elements.
     * In this case, all of the elements are integers.
     * @param Pens: a queue of integers
     * @param Caps: a queue of integers
     */
    public static void quicklySort(Queue Pens, Queue Caps) {
        // If the pens or caps queues are not equal in number or less than two, just exit.
        int n = Caps.size();
        int m = Pens.size(); 
        if (n != m || n < 2 || m < 2) {
            return;
        }
        
        // Random index for pivot
        Random rand = new Random();
        int pivotIndex = rand.nextInt(n);
        int count = 0;
        int pivotElement = 0;
        Queue temp = new Queue();
        
        // Find the random cap element for the pivot
        while (count < n) {
            pivotElement = (int) Caps.dequeue();
            temp.enqueue(pivotElement);
            if (count == pivotIndex) {
                break;
            }
            count++;
        }
        
        // Rebuild the cap queue from the temp queue
        while (!temp.isEmpty()) {
            Caps.enqueue(temp.dequeue());
        }
        
        // partition queues for caps
        Queue capsL = new Queue(); // less than pivot
        Queue capsE = new Queue(); // equal to pivot
        Queue capsG = new Queue(); // greater than pivot
        
        // partition queues for pens
        Queue pensL = new Queue(); // less than pivot
        Queue pensE = new Queue(); // equal to pivot
        Queue pensG = new Queue(); // greater than pivot
        
        // build partition queues for pens, based on pivot element in caps
        while (!Pens.isEmpty()) {
            int element = (int) Pens.dequeue();
            if (element < pivotElement) {
                pensL.enqueue(element);
            }
            else if (element == pivotElement) {
                pensE.enqueue(element);
            }
            else {
                pensG.enqueue(element);
            }
        }
        
        // build partition queues for caps, based on pivot element
        while (!Caps.isEmpty()) {
            int element = (int) Caps.dequeue();
            if (element < pivotElement) {
                capsL.enqueue(element);
            }
            else if (element == pivotElement) {
                capsE.enqueue(element);
            }
            else {
                capsG.enqueue(element);
            }
        }
        
        // recursive call to sort elements in the caps and pens queues 
        // less than and greater than the pivot.
        quicklySort(pensL, capsL);
        quicklySort(pensG, capsG);
        
        // rebuild (concatenate) the results for Pens.
        while (!pensL.isEmpty()) {
            Pens.enqueue(pensL.dequeue());
        }
        while (!pensE.isEmpty()) {
            Pens.enqueue(pensE.dequeue());
        }
        while (!pensG.isEmpty()) {
            Pens.enqueue(pensG.dequeue());
        }
        
        // rebuild (concatenate) the results for Caps.
        while (!capsL.isEmpty()) {
            Caps.enqueue(capsL.dequeue());
        }
        while (!capsE.isEmpty()) {
            Caps.enqueue(capsE.dequeue());
        }
        while (!capsG.isEmpty()) {
            Caps.enqueue(capsG.dequeue());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Build pens queue
        Queue Pens = new Queue();
        Pens.enqueue(9);
        Pens.enqueue(8);
        Pens.enqueue(62);
        Pens.enqueue(2);
        Pens.enqueue(4);
        Pens.enqueue(36);
        Pens.enqueue(45);
        Pens.enqueue(-2);
        
        // Build caps queue
        Queue Caps = new Queue();
        Caps.enqueue(62);
        Caps.enqueue(9);
        Caps.enqueue(2);
        Caps.enqueue(45);
        Caps.enqueue(4);
        Caps.enqueue(36);
        Caps.enqueue(8);
        Caps.enqueue(-2);
        
        // Run sorting function
        quicklySort(Pens, Caps);
        
        // Print out the pens after sorting
        System.out.println("Pens After Sorting: ");
        while (!Pens.isEmpty()) {
            System.out.println(Pens.dequeue().toString());
        }
        System.out.println(); // line break
        
        // Print out the caps after sorting
        System.out.println("Caps After Sorting: ");
        while (!Caps.isEmpty()) {
            System.out.println(Caps.dequeue().toString());
        }
        System.out.println();
        
        
    }
    
}

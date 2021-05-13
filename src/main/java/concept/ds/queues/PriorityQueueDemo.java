package concept.ds.queues;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

/**
 * PriorityQueue is an ADT. Queue is FIFO, whereas PriorityQueue is not FIFO, the first element is the one with highest priority.
 * Priority is decided based on the value of the element and so only the comparable objects should be stored in the PriorityQueue.
 * For example MaxPriorityQueue will give out the max element in the Queue. If two elements are equal then the one that was inserted first
 * is returned. PriorityQueue in basic form is elements in an array sorted at all times. There are various ways and data structures
 * to implement PriorityQueue. Like you can have an ArrayList and call merge sort on it after every insert() operation.
 * In Java, a PriorityQueue is implemented using Heap data structure.
 *
 */

public class PriorityQueueDemo {

  public static void main(String[] args) {
    
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(7);q.add(1);q.add(5);q.add(8);q.add(3);q.add(4);q.add(9);q.add(2);q.add(6);q.add(0);q.add(5);
    while(!q.isEmpty()) {
      System.out.print(q.poll() + " ");
    }
    System.out.println();
    
    
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); // Java internally uses heap. default is min-heap
    pq.add(7);pq.add(1);pq.add(5);pq.add(8);pq.add(3);pq.add(4);pq.add(9);pq.add(2);pq.add(6);pq.add(0);pq.add(5);
    //System.out.println();
    //System.out.println(pq); will print in the order the elements are stored in the system
    while(!pq.isEmpty()) {
      System.out.print(pq.poll() + " ");
    }
    System.out.println();
    
    TreeSet<Integer> ts = new TreeSet<Integer>();
    ts.add(7);ts.add(1);ts.add(5);ts.add(8);ts.add(3);ts.add(4);ts.add(9);ts.add(2);ts.add(6);ts.add(0);ts.add(5);
    ts.stream().forEach(e -> System.out.print(e + " "));

  }

}

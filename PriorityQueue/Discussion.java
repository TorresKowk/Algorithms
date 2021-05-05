package PriorityQueue;

import java.util.*;

public class Discussion {

    /*
        It is a heap with the same Queue interface with offer(), peek(), poll()
        But, it is not FIFO, when poll() or peek() we always look at the smallest / largest element (min heap or max heap)

        Heap: (complete tree)                                       BST: (self-balanced tree)
        - search(): O(n)                                            - search(): O(logn)
        - remove(): O(logn)                                         - remove(): O(logn)
        - remove(Object): search + remove = O(n + logn) = O(n)      - remove(Object) = O(logn)

        Heap is not a BST!!!, in heap nodes only less than its children, but left-child could larger than right-child!!!

        Order: PriorityQueue need to know how to compare the elements and determine which one is smaller / larger.
               Default: minHeap (when we new a PriorityQueue)

        Most frequently used constructors of PriorityQueue
            1. PriorityQueue<E> heap = new PriorityQueue<E>(); (E should be implements Comparable<Cell>)
            2. PriorityQueue<E> heap = new PriorityQueue<E>(new MyComparator());
                class MyComparator implements Comparator<E> ! (Java 8)

        The object inside the PQ should be comparable! Otherwise Java will throw runtime exception (don't know how to compare it)

        Heapify(): convert an array to heap in O(n) time
            ArrayList<E> list = new ArrayList<E>();
            PriorityQueue<E> heap = new PriorityQueue<E>(list); (class E must implement Comparable<Cell>!)
     */

    public static void main(String args[]) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(4);
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.offer(1);

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
    }
}

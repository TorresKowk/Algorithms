package PriorityQueue;

import java.util.*;

public class SmallestKElements {

    /*
        Question: Smallest K Elements in Unsorted Array

        Find the K smallest numbers in an unsorted integer array A. The return numbers should be in ascending order.
        eg: A = {3, 4, 1, 2, 5}; k = 3; --> return {1, 2, 3}

        Solution1: use minHeap to implement
        - Heapify array into a minHeap, then poll() k times into the result array

        Solution2: use maxHeap to implement
        - Create a K nodes maxHeap, pass the input array into the maxHeap, the last k nodes are the result
     */

    public int[] smallestKMin(int[] input, int k) {
        if (input.length == 0) return null;
        int[] result = new int[k];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < input.length; i++) {
            minHeap.offer(input[i]);
        }

        for (int j = 0; j < k; j++) {
            result[j] = minHeap.poll();
        }
        return result;
    }

    public int[] smallestKMax(int[] input, int k) {
        if (input.length == 0) return null;
        int[] result = new int[k];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MyComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.offer(input[i]);
        }
        for (int i = k; i < input.length; i++) {
            maxHeap.offer(input[i]);
            maxHeap.poll();
        }
        for (int j = k-1; j >= 0; j--) {
            result[j] = maxHeap.poll();
        }
        return result;
    }

    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1.equals(o2)) return 0;
            return o1 > o2 ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        SmallestKElements s = new SmallestKElements();
        int[] input = {3, 4, 1, 2, 5};

        int[] result1 = s.smallestKMin(input, 3);
        System.out.println(Arrays.toString(result1));

        int[] result2 = s.smallestKMax(input, 3);
        System.out.println(Arrays.toString(result2));

    }
}

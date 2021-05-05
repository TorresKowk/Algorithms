package PriorityQueue;

import java.util.*;

public class ComparableClass {

        /*
        First Implementation: The element type implementing Comparable interface.

        The element's class can implement Comparable interface and thus implement the required method compareTo(),
        PriorityQueue will use this method to compare any two elements.
     */

    interface Comparable<E> {
        int compareTo(E o1);
    }

    class Integer implements Comparable<Integer> {
        private int value;

        public Integer(int value) {
            this.value = value;
        }

        /*
            The return type of compareTo(another) method determines the order of this and another:
                0: this and another are of same priority
                -1 (< 0): this has higher priority than another
                1 (> 0): this has less priority than another
         */

        @Override
        public int compareTo(Integer another) {
            if (this.value == another.value) return 0;
            return this.value < another.value ? -1 : 1; // minHeap: smaller Integer has high priority (-1 with high priority than 1)
        }
    }

    /*
        Another Example using custom class:

        Suppose you have an integer matrix, each row is sorted by ascending order and each column is also sorted by ascending order,
        we need a class to representing each cell in the matrix, and we need to compare two cells with their value in the matrix.
     */

    static class Cell implements Comparable<Cell> {
        public int row;
        public int col;
        public int value;
        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public int compareTo(Cell another) {
            if (this.value == another.value) return 0;
            return this.value < another.value ? -1 : 1;
        }
    }

    public static void main(String args[]) {
        PriorityQueue<Cell> minHeap = new PriorityQueue<>();
        Cell c1 = new Cell(0, 0, 0);
        Cell c2 = new Cell(1, 0, 1);
        Cell c3 = new Cell(2, 1, 2);
        minHeap.offer(c3);
        minHeap.offer(c2);
        minHeap.offer(c1);

        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            System.out.println(cur.value);
        }

    }

}

package PriorityQueue;

import java.util.*;

public class ComparatorClass {

    /*
        Second solution: provide an extra Comparator object to compare the elements

        There is another interface Comparator, it is used to compare two elements with same type E
     */
    interface Comparator<E> {
        int compare(E o1, E o2);
    }

    static class Cell {
        public int row;
        public int col;
        public int value;
        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    static class MyComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell c1, Cell c2) {
            if (c1.value == c2.value) return 0;
            return c1.value < c2.value ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(); // minHeap as default
        //PriorityQueue<Cell> maxHeap = new PriorityQueue<Cell>(new MyComparator());
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

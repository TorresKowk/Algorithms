//package PriorityQueue;
//
//public class Heap {
//
////    Implementation Of Min Heap
////    Empty version:
//    public class MinHeap {
//        private int[] array;
//        private int size;
//
//        public MinHeap(int[] array) {
//            if (array == null || array.length == 0) {
//                throw new IllegalArgumentException("input array can not be null or empty");
//            }
//            this.array = array;
//            size = array.length;
//            heapify();
//        }
//
//        private void heapify() {
//
//        }
//
//        public MinHeap(int cap) {
//            if (cap <= 0) {
//                throw new IllegalArgumentException("capacity can not be <= 0");
//            }
//            array = new int[cap];
//            size = 0;
//        }
//
//        public int size() {
//            return size;
//        }
//
//        public boolean isEmpty() {
//            return size == 0;
//        }
//
//        public boolean isFull() {
//            return size == array.length;
//        }
//
//        private void percolateUp(int index) {
//
//        }
//
//        private void percolateDown(int index) {
//        }
//
//        public int peek() {
//        }
//
//        public int poll() {
//        }
//
//        public void offer(int ele) {
//        }
//
//        public int update(int index, int ele) {
//        }
//
//        private void swap(int[] array, int l, int r) {
//            int tmp = array[l];
//            array[l] = array[r];
//            array[r] = tmp;
//        }
//
//    }
//
//}

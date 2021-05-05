package Sort;

import java.util.*;

public class classical {

    /*
    Question1: Selection Sort

    Example: sort int a[i] = {-1. -3, 7, 4} to an ascending order {-3, -1, 4, 7}
    iteration1: find global min -3, {-1. -3, 7, 4} --> -3 {-1, 7, 4}
    iteration2: find global min in the rest -1, -3 {-1, 7, 4} --> -3, -1, {7, 4}
    iteration3: find global min in the rest 4, -3, -1, {7, 4} --> -3, -1, 4, {7} DONE

    TC: O(n^2); SC: O(1)
     */

    public int[] selectionSort(int[] array) {
        if (array == null || array.length == 0) return array;

        for (int i = 0; i < array.length - 1; i++) { //don't need i to array.length - 1, cuz j will outOfBound
            int globalMin = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] >= array[j]) {
                    globalMin = j;
                }
            }
            swap(array, i, globalMin);
        }
        return array;
    }

    /*
        Question2: stack sort by selection sort
        (Given an array stored in Stack1, how to sort the numbers by using additional two stacks?)

        stack1 (store input): [2, 1, 3, 4
        stack2 (buffer): [                              globalMin
        stack3 (output): [
     */

    public Deque<Integer> stackSelectionSort(Deque<Integer> stack1) {
        Deque<Integer> buffer = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        while (!stack1.isEmpty()) {
            int globalMin = stack1.peekLast();
            while (!stack1.isEmpty()) {
                if (globalMin > stack1.peekLast()) {
                    globalMin = stack1.peekLast();
                }
                buffer.offerLast(stack1.pollLast());
            }
            stack2.offerLast(globalMin);
            while (!buffer.isEmpty()) {
                if (buffer.peekLast() != globalMin) {
                    stack1.offerLast(buffer.pollLast());
                } else {
                    buffer.pollLast();
                }
            }
        }
        return stack2;
    }

    /*
        Question3: Merge Sort

        a[n]                  5,2,7,4,1,3,8,6
                                /        \
                           5,2,7,4      1,3,8,6
                           /    \       /     \
                       5,2      7,4    1,3     8,6
                       / \       / \   / \     / \
                      5   2     7  4  1   3   8   6

         Time Complexity above this line = O(n) -- cut every elements in the array along with n-lengths
     ==============================================================================
         Time Complexity below this line = O(nlogn)
                     5   2     7   4     1   3    "8"  6
                      \ /       \ /       \ /       \ /                 (谁小移谁)
                      2,5       4,7      "1,3"      6,8
                          \    /              \   /
                         "2,4,5,7"           1,3,6,8
                                 \         /
                                1,2,3,4,5,6,7,8

         Total TC: O(nlogn);
         SC:
         stack space: have called logn times stack, O(logn)
         heap space: n/2 + n/4 + ... + 2 + 1 = O(n) -- all elements with " " when the last "6" element hasn't exist.
         so total SC: O(n)

     */

    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) return null;
        else return mergeSort(array, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int left, int right) {
        //base case
        if (left == right) return new int[] {array[left]}; //remember!!! create a new array with "array[left]", not just the index left!!!

        //recursive rule
        int mid = left + (right - left) / 2;
        int[] leftResult = mergeSort(array, left, mid);
        int[] rightResult = mergeSort(array, mid+1, right);
        int[] result = merge(leftResult, rightResult);
        return result;
    }

    private int[] merge(int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        int[] output = new int[left.length + right.length];
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                output[k++] = left[i++];
            } else if (left[i] > right[j]) {
                output[k++] = right[j++];
            }
        }
        while (i == left.length && j < right.length) {
            output[k++] = right[j++];
        }
        while (j == right.length && i < left.length) {
            output[k++] = left[i++];
        }
        return output;
    }

    /*
        Question4: How to use mergesort to sort a linkedList?

        Question5: Given a string "A1B2C3D4", how to convert it to another string "ABCD1234"
     */

    /*
        Question6: Quick Sort
     */

    Random random = new Random();

    public int[] quickSort(int[] array) {
        if (array == null || array.length <= 1) return array;
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int left, int right) {
        //base case
        if (left >= right) return array;

        int pivot = left + random.nextInt(right - left + 1); //random.nextInt(n) will create a random Int number [0, n)
        swap(array, pivot, right);
        //right can not be replaced array.length - 1 here!!! inner recursion stack's right is not array.length - 1, we only use one array.
        int i = 0;
        int j = right - 1;
        while (i <= j) {
            if (array[i] <= array[right]) {
                i++;
            } else if (array[i] > array[right]) {
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, right);
        quickSort(array, left, i-1);
        quickSort(array, i+1, right);
        return array;
    }

    /*
        Question7: Rainbow Sort
        Sort the array with -1, 0, 1, in order
     */

    public int[] rainbowSort(int[] array) {
        if (array == null || array.length <= 1) return array;

        int i = 0, j = 0;
        int k = array.length - 1;
        while (j <= k) {
            if (array[j] == -1) {
                swap(array, i++, j++);
            } else if (array[j] == 0) {
                j++;
            } else {
                swap(array, j, k--);
            }
        }
        return array;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        classical c = new classical();
        //Question1
        int[] array1 = {-1, -3, 7, 4};
        int[] output1 = c.selectionSort(array1);
        System.out.println(Arrays.toString(output1));

        //Question2
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.offerLast(2);
        stack.offerLast(1);
        stack.offerLast(4);
        stack.offerLast(3);
        Deque<Integer> output2 = c.stackSelectionSort(stack);
        while (!output2.isEmpty()) {
            System.out.println(output2.pollFirst());
        }

        //Question3
        int[] array2 = {5, 2, 7, 4, 1, 3, 8, 6};
        int[] output3 = c.mergeSort(array2);
        System.out.println(Arrays.toString(output3));

        //Question6
        int[] array3 = {1, 9, 8, 3, 5};
        int[] output = c.quickSort(array3);
        System.out.println(Arrays.toString(output));

        //Question7
        int[] array4 = {-1, 1, 0, 1, -1, 0};
        int[] output4 = c.rainbowSort(array4);
        System.out.println(Arrays.toString(output4));
    }
}

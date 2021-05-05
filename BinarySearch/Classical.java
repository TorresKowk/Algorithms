package BinarySearch;

import java.util.*;

public class Classical {

    /*
        Principles of Binary Search:
        1. We must guarantee that the search space decreases over time (after each iteration)
        2. We must guarantee that the target (if exists) can not be ruled out accidentally, when we change the value of left or right
           (it's critical to define how to move the range for search)
     */

    // Question1: Classical Binary Search
    public int binarySearch(int target, int[] array) {  // return the target's index if have
        int left = 0;
        int right = array.length - 1;
        while (left <= right) { // left < right WRONG! when array.length = 1: left = right, then return -1 without entering the loop
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                left = mid + 1; // left = mid WRONG! when left = right - 1: left = mid, then dead-loop happen
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /* Question2: Classical Binary Search in 2D space
       2D Matrix, sorted on each row, and each first element in the next row is larger than the last element in the previous row
       Given a target return the position of it (x row y col), return -1, -1 if not exist.
     */
    public int[] twoDBinarySearch(int target, int[][] matrix) {
        if (matrix.length == 0 || matrix == null) return new int[]{-1, -1};

        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int r = mid / col;
            int c = mid % col;
            if (matrix[r][c] < target) {
                left = mid + 1;
            } else if (matrix[r][c] > target) {
                right = mid - 1;
            } else {
                return new int[]{r, c};
            }
        }
        return new int[]{-1, -1};
    }


    public static void main(String[] args) {
        Classical c = new Classical();

        // Question1
        int[] array = {1, 2, 4, 5, 7, 8, 9};
        int result1 = c.binarySearch(6, array);
        System.out.println(result1);

        //Question2
        int[][] matrix = {{1,3,4,6},{7,9,10,11},{14,17,19,20}};
        int[] result2 = c.twoDBinarySearch(11, matrix);
        System.out.println(Arrays.toString(result2));
    }

}

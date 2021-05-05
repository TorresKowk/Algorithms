package BinarySearch;

import java.util.*;

public class Medium {

    /* Question3: Closest Element to Target
       How to find the element in the array that closest to the target number?

       Solution: when you only have two elements left in the valid range, then you only need to check
                 whether the left choice or the right choice is the answer (if it exists).
     */

    public int findClosestElement(int value, int[] array) {
        if (array.length == 0 || array == null) return -1;

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) { // if left neighbors right --> terminate loop (to control loop end with two neighbor indexes)
            int mid = left + (right - left) / 2;
            if (array[mid] < value) {
                left = mid; // left = mid + 1 WRONG!! Not sure if mid is the closest element index, can not rule it out.
            } else if (array[mid] > value) {
                right = mid;
            } else return mid;
        }
        //Post-processing
        int leftResult = Math.abs(array[left] - value);
        int rightResult = Math.abs(array[right] - value);
        return (leftResult <= rightResult ? left : right); // if leftResult <= rightResult --> return left // else return right
    }

    /* Question4: First Target
       How to return the index of the first occurrence of an element, say 5

       index     0 1 2 3 4 5 6
       array[]   4 5 5 5 5 5 5
       return 1 in this case, because array[1] is the first occurrence of 5

       Solution: First use binary search, terminate with 2 elements.
                 return left if its index is 0, else return right (the boundary of target)
     */

    public int firstTarget(int target, int[] array) {
        if (array.length == 0 || array == null) return -1;

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                left = mid;
            } else if (array[mid] >= target) {
                right = mid;
            }
        }
        //Post-processing
        if (array[left] == target) return left;
        else if (array[right] == target) return right;
        else return -1;
    }

    //Question5: Last Target (Similar to Q4)

    public int lastTarget(int target, int[] array) {
        if (array.length == 0 || array == null) return -1;

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            }
        }
        //Post-processing
        if (array[right] == target) return right;
        else if (array[left] == target) return left;
        else return -1;
    }

    /* Question6: Closest K Elements
       How to return the index of K elements that are closest elements to the targets

       Solution: First find the closest two neighbour elements, then move the closest element to the result array
     */

     public int[] closestKElements(int target, int k, int[] array) {
         if (array.length == 0 || array == null || k > array.length) return null;

         int left = 0;
         int right = array.length - 1;
         while (left < right - 1) {
             int mid = left + (right - left) / 2;
             if (array[mid] < target) {
                 left = mid;
             } else if (array[mid] >= target) {
                 right = mid;
             }
         }
         //Post-processing
         int[] result = new int[k];
         for (int i = 0; i < k; i++) {
             if (left < 0) {
                 result[i] = right;
                 right++;
                 continue;
             } else if (right > array.length - 1) {
                 result[i]  = left;
                 left--;
                 continue;
             }
             int leftDiff = Math.abs(array[left] - target);
             int rightDiff = Math.abs(array[right] - target);
             if (leftDiff <= rightDiff) {
                 result[i] = left;
                 left--;
             } else {
                 result[i] = right;
                 right++;
             }
         }
         return result;
     }

    public static void main(String[] arg) {
        Medium m = new Medium();
        //Question3
        int[] array1 = {1, 2, 3, 4, 9};
        int result1 = m.findClosestElement(4, array1);
        System.out.println(result1);

        //Question4
        int[] array2 = {4, 5, 5, 5, 5, 5, 5};
        int result2 = m.firstTarget(5, array2);
        System.out.println(result2);

        //Question5
        int[] array3 = {4, 5, 5, 5, 5, 5, 5};
        int result3 = m.lastTarget(5, array3);
        System.out.println(result3);

        //Question6
        int[] array4 = {1, 2, 3, 8, 9};
        int[] result4 = m.closestKElements(4, 4, array4);
        System.out.println(Arrays.toString(result4));
    }
}

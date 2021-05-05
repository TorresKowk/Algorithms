package BinarySearch;

import java.util.*;

public class Hard {

    /* Question7: Smallest index of Element that is Larger than Target

       target = 1
       index 0 1 2 3 4 5 6 7 8 9
       array 0 0 0 0 1 1 1 2 2 2

       case1: if mid = 3 (array[mid] = 0) left = mid + 1
       case2: if mid = 4 - 7 (array[mid] = target) right = mid
       case3: if mid = 7 - 8 (array[mid] = 2) right = mid - 1
     */

    public int smallestIndexOfElement(int target, int[] array) {
        if (array.length == 0 || array == null) return -1;

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] >= target) {
                right = mid;
            }
        }
        //Post-processing
        if (array[left] >= target) return left;
        else if (array[right] >= target) return right;
        else return -1;
    }

    /* Question8: K-th Smallest in Two Sorted Arrays

        Example input:
        Array1: 2, 5, 7, 10, 13
        Array2: 1, 3, 4, 13, 20, 29
        k = 5
        Output: {1, 2, 3, 4, 5}

        Solution1: Move the smallest element each iteration -- TC = O(k)
        Solution2: Use Binary Search (???) -- TC = O(log(k))
     */

    public int[] kSmallestElements(int[] array1, int[] array2, int k) {
        int[] result = new int[k];
        int i = 0, j = 0, index = 0;
        while (index < k) {
            if (array1[i] <= array2[j]) {
                result[index] = array1[i++];
            } else if (array1[i] > array2[j]) {
                result[index] = array2[j++];
            }
            index++;
            if (i > array1.length - 1 || j > array2.length - 1) break;
        }
        //Post-processing
        while (i == array1.length && j < array2.length - 1) {
            result[index++] = array2[j++];
        }
        while (j == array2.length && i < array1.length - 1) {
            result[index++] = array1[i++];
        }
        return result;
    }

    /* Question10: Binary Search with Unknown Size Array
       Given a sorted array with unknown size, how to determine whether a number is in it or not.

       Solution: First Determine the size of array, using Binary Search (increase the search space)
                 if (array[index *= 2] == null) --> the size is smaller than index

                 With a sorted array with known size, we can easily find whether the given number is in it or not.
       */

    public static void main(String[] arg) {
        Hard h = new Hard();
        //Question7
        int[] array1 = {1, 3, 4, 5, 8, 9};
        int result1 = h.smallestIndexOfElement(7, array1);
        System.out.println(result1);

        //Question8
        int[] array2 = {2, 5, 7, 10, 13};
        int[] array3 = {1, 3, 4, 13, 20, 29};
        int[] result2 = h.kSmallestElements(array2, array3, 5);
        System.out.println(Arrays.toString(result2));
    }
}

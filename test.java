import java.util.*;
public class test {

    public int[] mergeSort(int[] list) {
        //base case
        if (list == null || list.length == 0) return list;

        return mergeSort(list, 0, list.length - 1);
    }

    private int[] mergeSort(int[] list, int left, int right) {
        //base case
        if (left == right) return new int[]{list[left]};

        int mid = left + (right - left) / 2;
        int[] leftResult = mergeSort(list, left, mid);
        int[] rightResult = mergeSort(list, mid+1, right);
        return merge(leftResult, rightResult);
    }

    private int[] merge(int[] array1, int[] array2) {
        int i = 0, j  = 0,k = 0;
        int[] result = new int[array1.length + array2.length];
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                result[k++] = array1[i++];
            } else {
                result[k++] = array2[j++];
            }
        }
        while (j == array2.length && i < array1.length) {
            result[k++] = array1[i++];
        }
        while (i == array1.length && j < array2.length) {
            result[k++] = array2[j++];
        }
        return result;
    }

    public int maxSumSubarray1(int[] array) {
        //base case
        if (array == null || array.length == 0) return 0;

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public int findMaxSum(int[] array) {
        //base case
        if (array == null || array.length == 0) return 0;
        int[] result = findMaxSum(array, 0, array.length - 1); // [leftIndex, rightIndex, maxSum]
        return result[2];
    }

    private int[] findMaxSum(int[] array, int leftIndex, int rightIndex) {
        //base case
        if (leftIndex == rightIndex) return new int[]{leftIndex, rightIndex, array[leftIndex]};

        int mid = leftIndex + (rightIndex - leftIndex) / 2;
        int[] leftResult = findMaxSum(array, leftIndex, mid);
        int[] rightResult = findMaxSum(array, mid+1, rightIndex);
        int[] crossResult = findMinCrossMaxSum(array, leftIndex, mid, rightIndex);
        if (leftResult[2] >= rightResult[2] && leftResult[2] >= crossResult[2]) {
            return leftResult;
        } else if (rightResult[2] > leftResult[2] && rightResult[2] >= crossResult[2]) {
            return rightResult;
        } else return crossResult;
    }

    private int[] findMinCrossMaxSum(int[] array, int leftIndex, int mid, int rightIndex) {
        // the result must include (cross) the mid (mid, mid+1)
        int i = mid;
        int j = mid + 1;
        int leftResultIndex = i;
        int rightResultIndex = j;
        int leftSum = 0;
        int rightSum = 0;
        int leftMaxSum = Integer.MIN_VALUE;
        int rightMaxSum = Integer.MIN_VALUE;
        while (i >= leftIndex) {
            leftSum += array[i];
            if (leftSum > leftMaxSum) {
                leftMaxSum = leftSum;
                leftResultIndex = i;
            }
            i--;
        }
        while (j <= rightIndex) {
            rightSum += array[j];
            if (rightSum > rightMaxSum) {
                rightMaxSum = rightSum;
                rightResultIndex = j;
            }
            j++;
        }
        return new int[]{leftResultIndex, rightResultIndex, leftMaxSum + rightMaxSum};
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int[] leftTop = new int[]{0, 0};
        int[] rightTop = new int[]{0, matrix[0].length - 1};
        int[] leftBottom = new int[]{matrix.length - 1, 0};
        return searchMatrix(matrix, leftTop, rightTop, leftBottom, target);
    }

    private boolean searchMatrix(int[][] matrix, int[] leftTop, int[] rightTop, int[] leftBottom, int target) {
        //base case
        if (leftTop[0] == leftBottom[0] && leftTop[1] ==  rightTop[1]) {
            if (matrix[leftTop[0]][leftTop[1]] == target) return true;
            else return false;
        }

        int midRow = leftTop[0] + (leftBottom[0] - leftTop[0]) / 2;
        int midCol = leftTop[1] + (rightTop[1] - leftTop[1]) / 2;
        boolean leftTopResult = searchMatrix(matrix, leftTop, new int[]{leftTop[0], midCol}, new int[]{midRow, leftTop[1]}, target);
        boolean leftBottomResult = (midRow == leftBottom[0]) ? false : searchMatrix(matrix, new int[]{midRow+1, leftTop[1]}, new int[]{midRow+1, midCol}, leftBottom, target);
        boolean rightTopResult = (midCol == rightTop[1]) ? false : searchMatrix(matrix, new int[]{leftTop[0], midCol+1}, rightTop, new int[]{midRow, midCol+1}, target);
        boolean rightBottomResult = (midRow == leftBottom[0] || midCol == rightTop[1]) ? false : searchMatrix(matrix, new int[]{midRow+1, midCol+1}, new int[]{midRow+1, rightTop[1]}, new int[]{leftBottom[0], midCol+1}, target);
        return leftTopResult || leftBottomResult || rightTopResult || rightBottomResult;
    }


    public static void main(String[] args) {
        test t = new test();
//        int[] array1 = {5, 3, 6, 1, 2, 7, 4};
//        int[] result = t.mergeSort(array1);
//        System.out.println(Arrays.toString(result));
//
//        int[] array2 = {2, -1, 3, 8, -5, -9, 10, -1};
//        int result2 = t.findMaxSum(array2);
//        System.out.println(result2);

        int[][] matrix = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10,13,14,17,24}, {18,21,23,26,30}};
        boolean result3 = t.searchMatrix(matrix, 5);
        System.out.println(result3);
    }


}

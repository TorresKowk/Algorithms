package DP;

import java.util.*;

public class OneDArrayDP {

    /*
        Question1: Longest Subarray
        Given an unsorted array, find the length of the longest sub-array in which the numbers are in ascending order.
        For Example, if the input array is {7, 2, 3, 1, 5, 8, 9, 6}, the subarray with the most numbers in ascending
        is {1, 5, 8, 9} and the expected output is 4.

        Concept:
        subarray: contiguous elements in an array.
        subsequence: not necessarily contiguous (can jump) eg: 2, 3, 6

        DP solution:

        index:  0   1   2   3   4   5   6   7
        input:  7   2   3   1   5   8   9   6
        M[]:    1   1   2   1   2   3   4   1                   int globalMax = 4

        base case: M[0] = 1
        induction rule:
            1. M[i] represents from the 0th index to the ith index, the length of the longest ascending subarray (must include ith index)
            2. iff array[i] > array[i-1] --> M[i] = M[i-1] + 1
               else M[i] = 1

         TC: O(n); SC: O(n)
     */

    public int[] longestSubArray(int[] input) {
        if (input == null || input.length == 0) return null;

        //create a M[] for the result store
        int[] M = new int[input.length];

        //base case
        M[0] = 0;
        int globalMax = 0;
        int globalMaxIndex = 0;

        //induction rule
        for (int i = 1; i < M.length; i++) {
            if (input[i] > input[i-1]) {
                M[i] = M[i-1] + 1;
            } else {
                M[i] = 1;
            }
            if (M[i] > globalMax) {
                globalMax = M[i];
                globalMaxIndex = i;
            }
        }
        return helper1(input, globalMax, globalMaxIndex);
    }

    private int[] helper1(int[] input, int globalMax, int globalMaxIndex) {
        int[] result = new int[globalMax];
        for (int i = globalMax-1; i >= 0; i--) {
            result[i] = input[globalMaxIndex--];
        }
        return result;
    }

    /*
        Question2: Maximal Product when Cutting Rope
        Given a rope with integer-length n, how to cut the rope into m integer-length parts wight length p[0], p[1], ..., p[n-1],
        in order to get the maximal product of p[0] * p[1] * ... * p[n-1]?
        m is determined by you and must be greater than 0 (at least one cut must be made)

        eg: when n is 5
        __ | ___ product is 2 * 3 = 6
        _ | _ | _ | _ | _ product is 1 * 1 * 1 * 1 * 1 = 1

        DP solution:

        index:  0   1   2   3   4   5   ... 8
        M(N):   0   0   ...

        base case: M[0] = M[1] = 0 (invalid, can not be cut)
        when size = 2, there is only one way cut: _ | _ product = max(1, M[1]) * 1
        when size = 3, there are two case to make the 1st cut:
            case1: __ | _ (left section: left big part to be cut; right section: right small part not to be cut)
            product = max(2, M[2]) * 1 (when left big part we can choose not to cut it, so compare with itself to make sure the max product)
            case2: _ | __ product = max(1, M[1]) * 2
            so M[3] = 2

        High Level: 左大段右小段思想
        左大段：继续切的部分，储存在subarray solution里的结果；右小段：不可再分部分
     */

    public int cutRope(int n) {
        if (n <= 1) return 0;

        //create array M to store subarray solution
        int[] M = new int[n + 1];

        //base case
        M[0] = 0;
        M[1] = 0;

        //induction rule
        for (int i = 2; i <= n; i++) {
            int globalMaxProduct = 0;
            for (int j = 1; j < i; j++) {
                //the left big part(0-j) can be not cut, so we compare the cut can not-cut value to make sure the product is maximum
                globalMaxProduct = Math.max(globalMaxProduct, Math.max(j, M[j]) * (i - j));
            }
            M[i] = globalMaxProduct;
        }
        return M[n];
    }

    /*
        Question3: Cut Dictionary Problem
        Given a word, can it be composed by concatenating words from a given dictionary?
        eg:
        Dictionary: bob, cat, rob
        word1: bcoabt --> return false
        word2: bobcatrob --> return true
        word3: bobbob --> return true

        所谓大段，就是不需要再重新计算，而是通过读表格的方式，直接读出M[i]的值而得到solution
        所谓小段，就是不用读M[i]表格，直接manual操作（根据题意）

        solution:
        index:  0   1   2   3   4   5   ... 9
        string:     b   bo  bob ...
        M(n):   T   F   F   T   ...

        size = 1
        M[1] = false (b)

        size = 2 --> bo; there are only one way to cut this "bo"
        case0: no cut, manual check "bo" --> false
        case1: b | o; left big section tells M[1] = false, and with manual check right small section "o" --> false
        so M[2] == case0 or case1 --> false
     */

    public boolean cutDictionary(String input, Set<String> dict) {
        //M[i] represents the substring [0, i)
        //M[0] represents " "; M[1] represents input[0] "b"; M[2] represents input[0-1] "bo"

        //first create boolean array M to store the substring result
        boolean[] M = new boolean[input.length() + 1];

        //base case
        M[0] = true;

        //induction rule
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (M[j] && dict.contains(input.substring(j, i))) {
                    /*
                        string.substring(j,i) --> [jth index, ith index)
                        index 0 1 2 3 4
                        input b o b c a
                        input.substring(2,5) --> index 2-4 "bca"
                     */
                    M[i] = true;
                    break;
                }
            }
        }
        return M[input.length()];
    }

    /*
        Question4: Jump Game
        Given an array of non-negative integers, you are initially positioned at the first index of the array. Each
        elements in the array represents your maximum jump length at that position. Determine if you are able to reach
        the last index.

        For example:
        A = [2, 3, 1, 1, 4] return true;
        B = [3, 2, 1, 0, 4] return false;

        Solution:
        Create a boolean M[], from end to front, store if array[i] and jump to the end
        M[i] represents whether we could reach the target from index i

        base case: M[n-1] = true;
        induction rule:
        if there exists a j, where j <= i + input[i], M[j] = true, and then M[i] = true
     */

    public boolean jumpGame(int[] input) {
        if (input == null || input.length == 0) return true;

        //create a M[] to store DP sub-solution
        boolean M[] = new boolean[input.length];

        //base case
        M[M.length - 1] = true;

        //induction rule
        for (int i = M.length - 2; i >= 0; i--) {
            for (int j = 1; j <= input[i]; j++) {
                if (M[i + j]) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[0];
    }

    /*
        Follow-up: Minimum Number of Jumps
        Given the same setup as the Jump Game, can you return the minimum number of jumps needed to reach the end instead
        of just whether or not it can reach the end?

        A = [2, 3, 1, 1, 4] return 2

        base case: M[n-1] = 0
        M[i] represents the minimum number of jumps from index = i to index = n-1

        induction rule:
        M[i] = min(1 + M[i+1], 1 + M[i+2] + ... + 1 + M[i+a[i])
     */

    public int numJumpGame(int[] input) {
        if (input == null || input.length == 0) return 0;

        //create M[] to store sub-solution
        int[] M = new int[input.length];

        //base case
        M[M.length - 1] = 0;

        //induction rule
        for (int i = M.length - 2; i >= 0; i--) {
            int globalMin = Integer.MAX_VALUE;
            for (int j = 1; j <= input[i]; j++) {
                globalMin = Math.min(globalMin, M[i+j] + 1);
            }
            M[i] = globalMin;
        }
        return M[0];
    }

    /*
        Question5: Largest sum of a subarray
        Given an array, find the subarray that has the greatest sum. Return the sum.

        For example:
        array = {1, 2, 4, -1, -2, 10, -1};  --> {1, 2, 4, -1, -2, 10} has the greatest sum

        From left to right for each index, we has two options:
        1. 另起炉灶：切断和之前subarray，将array[i]作为新的subarray的头部
        2. 继承遗产：继续之前subarray
        M[i] = max(option1, option2)

        base case: M[0] = input[0]
        induction rule: M[i] represents the largest sum of the subarray (including the i-th element)
        M[i] = max(M[i-1] + input[i], input[i])
     */

    public int maxSumSubarray(int[] input) {
        if (input == null || input.length == 0) return 0;

        int[] M = new int[input.length];
        int globalMax = Integer.MIN_VALUE;

        //base case
        M[0] = input[0];

        //induction rule
        for (int i = 1; i < M.length; i++) {
            M[i] = Math.max(M[i-1] + input[i], input[i]);
            globalMax = Math.max(globalMax, M[i]);
        }
        return globalMax;
    }

    /*
        Follow-up: how to return the left-right border of the solution?

        input = {1, 2, 4, -100, -1, 5, 10}

        Except globalLeft and globalRight, we also need curLeft and curRight, because globalLeft and globalRight aim to
        the current globalMax sum subarray, but if we need to renew a subarray, the left of the subarray is possible not
        the current index, but some index left than it, and we lost it without curLeft and curRight.

        If the curSum is positive (even end with negative), we can still use it as our possible subarray.

        globalLeft: the left border of the subarray that achieves globalMax sum
        globalRight: the right border of the subarray that achieves globalMax sum
        curLeft: the left border of the subarray that achieves M[i]
        curRight: the right border of the subarray that achieves M[i]

        when to change curLeft and curRight?
        when to change globalLeft and globalRight?

     */

    public int[] maxSumSubarray1(int[] input) { // return {globalMax, globalLeft, globalRight}
        if (input == null || input.length == 0) return null;

        int[] M = new int[input.length];
        int globalMax = Integer.MIN_VALUE;
        int globalLeft = 0;
        int globalRight = 0;
        int curLeft = 0;
        int curRight = 0;
        int curSum = 0;

        //base case
        M[0] = input[0];

        //induction rule
        for (int i = 0; i < M.length; i++) {
            if (curSum < 0) {
                curLeft = i;
                curRight = i;
                curSum = input[i];
            } else {
                curSum += input[i];
                curRight = i;
            }
            if (globalMax < curSum) {
                globalLeft = curLeft;
                globalRight = curRight;
                globalMax = curSum;
            }
        }
        int[] result = new int[3];
        result[0] = globalMax;
        result[1] = globalLeft;
        result[2] = globalRight;
        return result;
    }

    public static void main(String[] args) {
        OneDArrayDP one = new OneDArrayDP();

        //Question1
        int[] input1 = {7, 2, 3, 1, 5, 8, 9, 6};
        int[] result = one.longestSubArray(input1);
        System.out.println(Arrays.toString(result));

        //Question2
        System.out.println(one.cutRope(5));

        //Question4
        int[] input4 = {2, 3, 1, 1, 4};
        System.out.println(one.jumpGame(input4));
        int result4 = one.numJumpGame(input4);
        System.out.println(result4);

        //Question5
        int[] input5 = {1, 2, 4, -1, -2, 10, -1};
        System.out.println(one.maxSumSubarray(input5));
        int[] input51 = {1, 2, 4, -100, -1, 5, 10};
        int[] result51 = one.maxSumSubarray1(input51);
        System.out.println(Arrays.toString(result51));

    }

}
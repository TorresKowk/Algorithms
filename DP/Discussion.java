package DP;

public class Discussion {

    /*
        Use 1D-array M(n) to store each stack result from recursion, to save time complexity (O(n) rather that O(2^n))
        Index   0   1   2   3   4   5   ... 1000
        M(n)    0   1   1   2   3   5   ... ???
     */

    public int fibDP(int n) {
        int[] M = new int[n + 1]; //from index = 0 to n
        //base case
        M[0] = 0;
        M[1] = 1;

        for (int i = 2; i <= n; i++) {
            M[i] = M[i - 2] + M[i - 1];
        }
        return M[n];
    }

    /*
        DP的核心思想：数学归纳法
            1. 把一个大问题(size = n)的solution用比他小的问题们来解决，也就是思考从问题size = n - 1增加到size = n的时候，如何用
               小问题的solution构建出大问题的solution
            2. 和recursion的关系
                1. recursion从大到小来解决问题，不记录任何solution只要考虑base case和recursive rule (recursion tree从上到下)
                2. DP从小到大来解决问题，记录sub-solution
                   - 由size < n的solutions --> size = n的solution
                   - base case
                   - induction rule

        DP的解题常用方法：
            1. 一维的original data(such as rope, word)，求max / min cur, merge...
               eg: Longest Ascending Sub-array (when at i, look back at i-1)
                   Longest Ascending Subsequence (when at i, look back at 1 to i-1) (not contiguous)
                   Cut rope
                   Cut palindrome
             ...
    */

}

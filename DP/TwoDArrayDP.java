package DP;

import java.util.*;

public class TwoDArrayDP {

     /*
        Question1: Edit Distance
        Given two strings of alphanumeric characters, determine the minimum number of REPLACE, DELETE and INSERT operations
        needed to transform one string into the other.

        用最小的步数把第一个string变成第二个string
        Example: s1 = "asdf"; s2 = "sghj"
        option1: replace s1[0] with 's' sdf --> ghj
        option2: delete s1[0] sdf --> sghj
        option3: insert 's' before s1[0] asdf --> ghj

        in general, editDistance(String s1, String s2) =
        1. replace s1[i] with s2[i]
           editDistance(s1.substring(i), s2.substring(i)) + 1
        2. delete s1[i]
           editDistance(s1.substring(1), s2) + 1
        3. insert s2[i] before s1[i]
           editDistance(s1, s2.substring(1)) + 1

       Use 2-D array: M[i][j] represents the minimum number of actions to transform the first i letters of S1 to the first
                      j letters of S2.
       Case1: replace c1 with c2 --> M[1][1] = M[0][0] + 1 --> M[i][j] = M[i-1][j-1] + 1
       Case2: delete c1 --> M[1][1] = M[0][1] + 1
       Case3: insert a new char c2 to before s1[0] --> M[1][1] = M[1][0] + 1

       generalized to: M[i][j] = min(M[i-1][j-1], M[i-1][j], M[i][j-1]) + 1

       Solution:
       Base case: M[0][0] = 0; M[0][j] = j; M[i][0] = i
       Induction rule:
       1. M[i][j] represents the minimum number of actions to transform the first i of S1 to the first j letter of S2
          s1[0, 1, ..., i-1] --> s2[0, 1, ... j-1]
       2. M[i][j] = min(M[i-1][j-1], M[i-1][j], M[i][j-1]) + 1
     */

    public int editDistance(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        else if (s1.length() == 0) return s2.length();
        else if (s2.length() == 0) return s1.length();

        //M[][] must include index 0, which is a empty string, so total index is s.length() + 1
        int[][] M = new int[s1.length() + 1][s2.length() + 1];
        //base case
        M[0][0] = 0;
        for (int index = 0; index < s1.length(); index++) { // M[i][0] = i
            M[index][0] = index;
        }
        for (int index = 0; index < s2.length(); index++) { // M[0][j] = j
            M[0][index] = index;
        }

        //induction rule
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int replace = M[i-1][j-1];
                int delete = M[i-1][j];
                int insert = M[i][j-1];
                M[i][j] = Math.min(replace, Math.min(delete, insert)) + 1;
            }
        }
        return M[s1.length()][s2.length()];
    }

    /*
        Question2: Largest square of 1's in a binary matrix
        What is the edge length of the largest square of 1's in a given binary matrix.

        Example:
        1 1 1 1 0
        1 1 1 1 0                       return 4 in this case
        1 1 1 1 1
        1 1 1 1 1
        0 1 1 1 1

        DP solution: M[i][j] represents the max side length of all-one sub-square with (i,j) as the LOWER RIGHT CORNER
        eg: M[3][3] = 4; M[3][2] = 3; M[4][1] = 1

        base case: M[0][j] = a[0][j]; M[i][0] = a[i][0]
        induction rule: for any M[i][j] = 1 (i,j >= 1), M[i][j] = min(M[i-1][j-1], M[i][j-1], M[i-1][j]) + 1
     */

    public int largestSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] M = new int[row][col];
        int globalMax = 0;

        //base case
        for (int index = 0; index < row; index++) {
            M[index][0] = matrix[index][0]; // M[i][0] = i
        }
        for (int index = 0; index < col; index++) {
            M[0][index] = matrix[0][index];
        }

        //induction rule
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                M[i][j] = Math.min(M[i-1][j-1], Math.min(M[i-1][j], M[i][j-1])) + 1;
                globalMax = Math.max(globalMax, M[i][j]);
            }
        }
        return globalMax;
    }

    public static void main(String[] args) {
        TwoDArrayDP two = new TwoDArrayDP();

        //Question1
        String s11 = "asdf";
        String s12 = "sghj";
        int result1 = two.editDistance(s11, s12);
        System.out.println(result1);

        //Question2
        int[][] matrix2 = {{1,1,1,1,0}, {1,1,1,1,0}, {1,1,1,1,1}, {1,1,1,1,1}, {0,1,1,1,1}};
        int result2 = two.largestSquare(matrix2);
        System.out.println(result2);
    }
}

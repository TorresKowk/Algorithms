package Recursion;

import java.util.*;

public class Advanced {
    //从下往上传值题目类型

    /*
        1. Recursion与计算的结合：power(a,b)

        2. Recursion与1D or 2D Array的结合
            1D Array：二分法  mergesort / quicksort
            2D Array：逐层 row by row recursion: 8 queens --> n queens
     */

    /*
        Question1: 8 queens question
        在8*8棋盘中每个皇后可以攻击横竖以及斜路径的皇后，怎样分布8个皇后使得她们各自不会互相攻击？

        Key Insight: 每一行有且仅有一个皇后
        用一维array表示二维position: index --> i; value --> j （在已知每一行只有一个value(queen)的前提下）
        Recursion Tree:
            How many levels? -- eight levels
            How many branches? -- eight branches

                                    root = 0 queens have been put on the board
                            /       |       |       |       |       |       |       \
         level1: Q0 is put on (0,0) (0,1)       ...                                (0,7)
                 /||||||\
         level2: Q1 is put on (1,0) (1,1)       ...

         ...                                    ...

         base case: The last row is done, o row left
         recursive rule: iff position(i,j) valid --> go to the next row i+1...
     */

    public void nqueen(int n) {
        if (n <= 1) return;
        int[] solution = new int[n];
        nqueen(n, solution, 0);
    }

    private void nqueen(int n, int[] solution, int curLevel) {
        //base case
        if (curLevel == n) {
            System.out.println(Arrays.toString(solution));
            return;
        }
        for (int i = 0; i < n; i++) {
            solution[curLevel] = i;
            if (queenSave(solution, curLevel, i)) {
                nqueen(n, solution, curLevel+1);
            }
        }
    }

    private boolean queenSave(int[] solution, int curLevel, int value) {
        for (int index = 0; index < curLevel; index++) {
            if (solution[index] == solution[curLevel]) return false;
            if (Math.abs(curLevel - index) == Math.abs(value - solution[index])) return false;
        }
        return true;
    }

    /*
        Follow-up: If there are obstacles on the board?
     */

    /*
        Question2. How to print 2D array in spiral order (N*N)

        1  2  3  4  5               size1 = 5
        16 17 18 19 6               size2 = 3
        15 24 25 20 7               size3 = 1
        14 23 22 21 8
        13 12 11 10 9

        use offset to represent each circle's fill in operation

        int a[i][j] = ...
        counter++
     */

    public int[][] spiralPrint(int size) {
        if (size == 0) return null;
        int[][] matrix = new int[size][size];
        return spiralPrint(matrix, 0, matrix.length, 0);
    }

    private int[][] spiralPrint(int[][] matrix, int offset, int size, int counter) {
        //base case
        if (size <= 1) {
            if (size == 1) {
                matrix[offset][offset] = counter;
            }
            return matrix; // size == 0 or 1
        }
        int n = matrix.length;
        for (int i = 0; i < size-1; i++) {
            matrix[0 + offset][i + offset] = counter++;
        }
        for (int i = 0; i < size-1; i++) {
            matrix[i + offset][n - 1 - offset] = counter++;
        }
        for (int i = 0; i < size - 1; i++) {
            matrix[n - 1 - offset][n - 1 - i - offset] = counter++;
        }
        for (int i = 0; i < size - 1; i++) {
            matrix[n - 1 - i - offset][0 + offset] = counter++;
        }
        return spiralPrint(matrix, offset + 1, size - 2, counter);
    }

    /*
         Recursion 和 LinkedList的结合
         Question3: Reverse a LinkedList pair by pair

         1 -> 2 -> 3 -> 4 -> 5 -> null
         2 -> 1 -> 4 -> 3 -> 5 -> null

         recursion rule:
         1. cur.next --> newHead of the subProblem
         2. next.next --> cur (next becomes the new Head)
     */

    public ListNode reverse(ListNode root) {
        //base case
        if (root.next == null) return root;

        ListNode newNode = reverse(root.next);
        root.next.next = root;
        root.next = null;
        return newNode;
    }

    public ListNode reversePair(ListNode head) {
        //base case
        if (head == null || head.next == null) return head;

        ListNode next = head.next;
        head.next = reversePair(head.next.next);
        next.next = head;
        return next;
    }

    /*
        Question4: Reverse a binary tree upside down

                1 (root)                                        1 (right)
               / \                                             /
       (left) 2   3 (right)                        (sub-root) 2 -- 3 (left)
             / \                    -->                     /
            4   5                                         4 -- 5
           / \                                           /
          6   7                                  (root) 6 -- 7 (left)

        Recursion rule:
            root.left = new_Root_of_sub-problem.right
            root.left.left = root;
            root.left.right = root.right;
            root.left = null
            root.right = null
     */

    public TreeNode reverseTreeUpsideDown(TreeNode root) {
        //base case
        if (root == null || root.left == null) return root;

        TreeNode newRoot = reverseTreeUpsideDown(root.left); //store root6 as the result root
        root.left.left = root;
        root.left.right = root.right;
        root.left = null; // Remember cut the tail of root
        root.right = null;
        return newRoot; // Same as reverse LinkedList recursively
    }

    /*
        Question5: Recursion与String的结合

        Reverse a string using recursion (abcd --> dcba)
     */

    /*
        Question6: Recursion与Tree的结合

        Binary Tree往往是最常见的，和recursion结合最紧密的面试题目类型
        Reasons:
            每层的node具备的性质，传递的值和下一层的性质往往一致，比较容易定义recursive rule
            Base case(generally): null pointer under the leaf node
            Example1: int getHeight(TreeNode root)
            Example2: 统计Tree里面有多少个node？

        6.1 Tree + Recursion 第一类问题：从下往上反值 (int, boolean, etc.)
        Q6.1.1 Get height of a binary tree
     */

    public int getHeight(TreeNode root) {
        if (root == null) return 0;

        int leftResult = getHeight(root.left);  //step1
        int rightResult = getHeight(root.right);    //step2
        return Math.max(leftResult, rightResult) + 1;   //step3
    }

    /*
        Way of thinking (Tricks):
        1. What do you expect from your l-child / r-child ? (usually it is the return type of the recursion function)
            -The height of my left subtree
            -The height of my right subtree
        2. What do you want to do in the current layer? (recursive rule)
            -curHeight = max(left, right) + 1
        3. What do you want to report to your parent? (Same as step1)
            -return curHeight (to parent)

        Q6.1.2 Hwo to store how many nodes in each node's left-subtree?

        First we need to add (int leftTotal to Class TreeNode)
        1. What do you expect from your l-child / r-child ?
            (1)The total number of nodes in my left subtree.
            (2)The total number of nodes in my right subtree.
        2. What do you want to do in the current layer?
            current.leftTotal = (1)
        3. What do you want to report to your parent?
            return (1) + (2) + 1

        Q6.1.3 Find the node with the max difference in the total number of descendants in its left and right subtree

        1. What do you expect from your l-child / r-child ?
            (1)The total number of nodes in my left subtree.
            (2)The total number of nodes in my right subtree.
        2. What do you want to do in the current layer?
            check if (1)diff(2) > globalMax? update if true
            And we need to store it in the recursive function signature (globalMax)
        3. What do you want to report to your parent?
            return (1) + (2) + 1
     */

    public int maxDiffNode(TreeNode root, int[] globalMaxDiff, TreeNode[] result) {
        //every level of recursive must be same, so we return the subTotal nodes, and use globalMaxDiff and result to store the real result
        //use array to store the result so that it can change the field variable when we test it

        //base case
        if (root == null) return 0;

        int leftSubNode = maxDiffNode(root.left, globalMaxDiff, result);
        int rightSubNode = maxDiffNode(root.right, globalMaxDiff, result);

        if (Math.abs(leftSubNode - rightSubNode) > globalMaxDiff[0]) {
            globalMaxDiff[0] = Math.abs(leftSubNode - rightSubNode);
            result[0] = root;
        }

        return leftSubNode + rightSubNode + 1;
    }

    /*
        Q6.1.4 从下往上传值，最经典例题  Lowest Common Ancestor (LCA)

                                12                              node a = 5's ancestor: {5, 10, 12}
                               /  \                             node b = 9's ancestor: {9, 7, 5, 10, 12}
                             10    16                           common ancestor: {5, 10, 12}
                            /  \     \                          LCA: 5
                          5a    8     17
                         /  \
                        2    7
                               \
                                 9b

        1. What do you expect from your l-child / r-child? (Usually it is the return type of the recursion function)
            if left subtree or right subtree has a or b
        Step 2 & 3: recursive rule

        Case1: a and b 直接隶属，则 a or b 为LCA
        base case
            if cur == a || cur == b -> return cur
            if cur == null return cur
        recursive rule
            if left == null && right == null --> return null
            if either left or right are non-null --> return the non-null side to parent


        Case2: a and b 不直接隶属
        base case
            if cur == a || cur == b -> return cur
            if cur == null return cur
        recursive rule
            if left == null && right == null --> return null
            if either left or right are non-null --> return the non-null side to parent
            if both left and right are non-null --> return cur itself to parent
     */

    public TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) return root;

        TreeNode left = LCA(root.left, a, b);
        TreeNode right = LCA(root.right, a, b); //step1

        if (left == null && right == null) return null;
        else if (left != null && right != null) return root; //root is LCA
        else return (left == null) ? right : left; // keep the non-null side to its parent
    }

    /*
        Q6.1.5 Max path sum Binary Tree II (path from any node to any node)
        Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to
        any node (the start node and the end node can be the same)

        1. What do you expect from your l-child / r-child?
            (1) Left: 直上直下的path，一撇
            (2) Right: 直上直下的path，一剌

        2. What do you want to do in the current layer?
            sum of 人字形 path = Left 的一撇(1) + Right 的一剌(2) + root.value
            when (1)(2) are negative, set it to zero
            update the globalMax when necessary

        3. What do you want to report to your parent?
            root.value + Math(left, right) // not root + left + right!!!  这样传到parent的就变成了众字形了 xxx
     */

    public int maxSumPath(TreeNode root, int[] solu) { //use array to store the recursion result instead of int
        //base case
        if (root == null) return 0;

        int leftMax = maxSumPath(root.left, solu);
        int rightMax = maxSumPath(root.right, solu); // step1

        leftMax = (leftMax <= 0) ? 0 : leftMax;
        rightMax = (rightMax <= 0) ? 0 : rightMax; // if left or right are negative, set it to zero

        //Attention!!! 我们往parent传递的是一撇一剌+root的最大值，而保存在solu[](globalMax)里的是以当前root为root的人字形value
        //如果返回solu[]的值，parent则会接收来自其left and right subNode 的两个人字形path value，错误
        solu[0] = Math.max(root.value + leftMax + rightMax, solu[0]);
        return root.value + Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        Advanced ad = new Advanced();
//        ad.nqueen(8);
//
//        int[][] matrix = ad.spiralPrint(5);
//        System.out.println(Arrays.deepToString(matrix));

        //Question3
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//
//        ListNode result3 = ad.reversePair(node1);
//        ListNode cur = result3;
//        while (cur != null) {
//            System.out.println(cur.value);
//            cur = cur.next;
//        }
//

        //Question6
        TreeNode node11 = new TreeNode(1);
        TreeNode node12 = new TreeNode(2);
        TreeNode node13 = new TreeNode(3);
        TreeNode node14 = new TreeNode(4);
        TreeNode node15 = new TreeNode(5);
        TreeNode node16 = new TreeNode(6);
        TreeNode node17 = new TreeNode(7);
        node11.left = node12;
        node11.right = node13;
        node12.left = node14;
        node12.right = node15;
        node14.left = node16;
        node14.right = node17;

        int[] globalMax = new int[1];
        TreeNode[] result = new TreeNode[1];
        ad.maxDiffNode(node11, globalMax, result);
        System.out.println(result[0].value + " " + globalMax[0]);

    }

}

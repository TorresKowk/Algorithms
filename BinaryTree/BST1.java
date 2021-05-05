package BinaryTree;

/*
     Binary Search Tree: for EVERY single node in the tree, the values in its left subtree are ALL SMALLER than its
                         value, and the values in its right subtree are ALL LARGER than its value.

                                                    10 == root
                                                   /  \
                                                 5      15
                                                / \     / \
                                               2   7   12  20

     Conclusion3: If we print the value of the nodes in BST in in-order sequence, then it must form an ASCENDING order.

     What if there are duplicate values? EG: 10, 10, 5, 5, 5, 15, 2, 7, 12, 20
     Solution: we can use counter as a new member variable of TreeNode

                                                  (10,2) == root
                                                 /      \
                                             (5,3)      (15,1)
                                             /   \       /   \
                                           (2,1)(7,1)  (12,1)(20,1)
 */

public class BST1 {

    /*
        Basic Operations of BST
        - Search() - O(h), worst case O(n), best case O(logn)
        - Insert() - O(h), worst case O(n), best case O(logn)
        - Remove() - O(h), worst case O(n), best case O(logn)

        How to guarantee that all operations cost only O(logn)?
        Use Self-Balancing Binary Search Tree (AVL Tree, Red-Black Tree, etc)
      ==================================================================================================================

        Question1: Search in BST (Recursive / Iterative)

     */

    public TreeNode searchBSTRecursive(TreeNode root, int target) {
        /*
            Step1：根据当前root与target的大小不断往下查找，找到 or 没找到 后开始向上返回
            Step2：当前层不断的给父节点返回 找到的TreeNode or null

            Tail Recursion
            - The recursive call is always the last execution statement. (break point stands in last execution of current stack)
            - We can easily transfer the tail recursion to iterative solution.
         */
        //base case
        if (root == null) return null;
        if (root.value == target) return root;

        if (root.value > target) {
            return searchBSTRecursive(root.left, target); //不断的给父节点return null 或找到的 root
        } else {
            return searchBSTRecursive(root.right, target);
        }
    }

    public TreeNode searchBSTIterative(TreeNode root, int target) {
        TreeNode cur = root;
        while (cur != null && cur.value != target) { //end with (cur == null || cur.value == target)
            if (cur.value > target) {
                cur = cur.left;
            } else if (cur.value < target) {
                cur = cur.right;
            }
        }
        return cur; // cur is null (not found) or the target (found)
    }

    /*
        Question2: Insert in BST
                                                            10 == root
                                                           /  \
                                                         5      15
                                                        / \     / \
                                                       2   7   12  20
        case1: insert(6) --> to be 7's left child
        case2: insert(5) --> return directly
        case3: insert(13) --> to be 12's right child
     */

    public TreeNode insertBSTRecursive(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        //base case
        if (root == null) return newNode; // 遍历到低端返回newNode（只是返回newNode还没有挂操作）

        if (root.value > val) {
            root.left = insertBSTRecursive(root.left, val); //最后一层Stack将newNode挂上，接下来往上走一直重复连线（每层Stack返回的是当前层root）
        } else if (root.value < val) {
            root.right = insertBSTRecursive(root.right, val);
        }
        return root;
    }

    public TreeNode insertBSTIterative(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null && cur.value != val) {
            if (cur.value > val) {
                pre = cur;
                cur = cur.left;
            } else if (cur.value < val) {
                pre = cur;
                cur = cur.right;
            }
        }
        if (cur == null) {
            if (pre.value > val) {
                pre.left = newNode;
            } else if (pre.value < val) {
                pre.right = newNode;
            }
        }
        return root;
    }

    /*
        Question4: How to determine a binary tree is a BST?

        Solution
            0a: Primitive way but very bad in terms of space consumption
                step1: in-order traverse the tree and store all numbers in an arrayList
                step2: iterate over the array to determine, whether A[i] <= A[i+1]
            0b: Better way
                in-order traverse the tree and whenever we print out a new element, compare
                it with the element that was printed out previously.

            1:  Better way should use in the interview

                                       10(min = -inf, max = +inf)
                                        /                       \
                           5(min = -inf, max = +10)  AND  15(min = +10, max = +inf)
                         /                      \          /                      \
           2(min = -inf, max = +5)  8(min = +5, max = +10) 12                      20

           TC: O(n); SC: O(height)

     */

    public boolean isBST(TreeNode root) {
        if (root == null) return false;
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root, int min, int max) {
        //base case
        if (root == null) return true;
        if (root.value < min || root.value > max) return false;

        boolean leftResult = isBST(root.left, min, root.value);
        boolean rightResult = isBST(root.right, root.value, max);
        return leftResult && rightResult;
    }

    /*==================================================================================================================
        Discussion: Recursion在Tree题目的基本应用大致分为两类用法
            1. 把value从上往下传递then从下往上的题目 (isBST())
            2. 只把value从下往上传递（更为常见，必须熟练掌握）
                2.1. getHeight(root) 是经典的把integer value从下往上传递的题目
                2.2. isBalanced(root) 是把boolean value从下往上传递的题目
                2.3. isSymmetric(root1, root2) 是把boolean value从下往上传递的题目
                2.4. Assign the value of each node to be the total number of nodes that belong to its left subtree
                    （是把integer value从下往上传递的题目）
      ==================================================================================================================
     */
    /*
        Question5: Print BST keys in the given range

        Given two values k1 and k2 (k1 < k2) and a root pointer to a BST. Print all the keys of tree in range k1 to k2.
        Print all the keys in an increasing order.

                                                    10 == root
                                                   /  \
                                                 5      15
                                                / \     / \
                                               2   7   12  20

         Step1: determine whether we need to go left, only if (root.val > k1) we need to go left.
         Step2: determine whether we need to go right, only if (root.val < k2) we need to go right.

         TC: O(n) (worst case); SC: O(height)
     */

    public void printRangeBST(TreeNode root, int k1, int k2) {
        //base case
        if (root == null) return;

        //in-order to print in increasing order
        if (root.value > k1) printRangeBST(root.left, k1, k2);
        if (root.value >= k1 && root.value <= k2) System.out.println(root.value);
        if (root.value < k2) printRangeBST(root.right, k1, k2);
    }

    public static void main(String[] args) {
        BST1 bst1 = new BST1();

        /*
                        10
                       /  \
                      5    15
                     / \   / \
                    2   7 12  20
         */

        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(20);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        //Question1
        TreeNode result11 = bst1.searchBSTRecursive(node1, 12);
        TreeNode result12 = bst1.searchBSTIterative(node1,5);
        System.out.println(result11.value + " " + result12.value);

        //Question4
        boolean result4 = bst1.isBST(node1);
        System.out.println(result4);

        //Question5
        bst1.printRangeBST(node1, 6, 21);
    }

}

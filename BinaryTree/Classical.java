package BinaryTree;

public class Classical {

    /*
        Question1: How to get the height of a binary tree?

        Base case: root = null; Recursion rule: height of root = max(height of root.left, height of root.right) + 1
        TC: O(# of nodes) = O(n); SC: O(height) (not O(logn)!!!, worse case is O(n) (LinkedList))
     */

    public int getHeight(TreeNode root) {
        //base case
        if (root == null) return 0;

        int leftResult = getHeight(root.left);
        int rightResult = getHeight(root.right);
        return Math.max(leftResult, rightResult) + 1;
    }

    /*
        Question2: How to determine whether a binary tree is a balanced balanced tree?

        Recursion Tree:
                                       isBalanced(root) n nodes
                                  getHeight(left)  getHeight(right)                                 O(n)
                                        n/2       +      n/2
                                     /                        \
                 isBalanced(root) n/2 nodes                isBalanced(root) n/2 nodes
              getHeight(left)  getHeight(right)         getHeight(left)  getHeight(right)           O(n)
                   n/4       +      n/4                       n/4       +      n/4
                         ...                                           ...

         Total logn levels, each level costs O(n) TC, so TC: O(nlogn)
         SC: the longest path from up to down: O(logn)

     */

    public boolean isBalanced(TreeNode root) {
        //base case
        if (root == null) return true;

        if ((isBalanced(root.left) && isBalanced(root.right)) != true) return false;
        int leftResult = getHeight(root.left);
        int rightResult = getHeight(root.right);
        return (Math.abs(leftResult - rightResult) > 1) ? false : true;
    }

    /*
        Question3: How to judge whether a binary tree is symmetric?

                                            10
                                         /       \
                                       5a    |    5b
                                     /    \     /    \
                                   1a      3a| 3b     1b
                                  / \     / \  / \    / \
                                 2a 4a  6a 8a|8b 6b  4b 2b

         Recursion Tree:
                                        isSymmetric(5a,5b)
                                        /               \
                           isSymmetric(1a,1b)         isSymmetric(3a,3b)
                            /         \                 /           \
             isSymmetric(2a,2b) isSymmetric(4a,4b) isSymmetric(6a,6b) isSymmetric(8a,8b)

        TC: O(n); SC: O(height)
     */

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        //base case
        if (left == null && right == null) return true; //base case1
        if (left == null || right == null) return false; // base case2
        if (left.value != right.value) return false; //base case3

        boolean outResult = isSymmetric(left.left, right.right);
        boolean inResult = isSymmetric(left.right, right.left);
        return outResult && inResult;
    }

    /*
        Question3: Let's assume if we tweak the l-child with r-child of an arbitrary node in a binary tree, then the "structure"
                   of the tree are not changed. Then how to determine whether two binary trees' structure are identical.

                                                    8
                                                   /  \
                                                  4    5
                                                 /
                                                7
                   case1: 8a      AND    8b        OR     case2: 8a          AND        8b
                         /  \           /  \                    /  \                   /  \
                        4a   5a        4b   5b                 4a   5a                5b   4b
                       /              /                       /                              \
                      7              7                       7                                 7

         TC: ? ; SC: ?
     */

    public boolean isStrucid(TreeNode node1, TreeNode node2) {
        //base case
        if (node1 == null && node2 == null) return true; //case1
        if (node1 == null || node2 == null) return false; //case2
        if (node1.value != node2.value) return false; //case3

        boolean result1 = isStrucid(node1.left, node2.left) && isStrucid(node1.right, node2.right);
        boolean result2 = isStrucid(node1.left, node2.right) && isStrucid(node1.right, node2.left);
        return result1 || result2;
    }

    public static void main(String[] args) {
        Classical c = new Classical();

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
        int result1 = c.getHeight(node1);
        System.out.println(result1);

        //Question2
        boolean result2 = c.isBalanced(node1);
        System.out.println(result2);

    }

}

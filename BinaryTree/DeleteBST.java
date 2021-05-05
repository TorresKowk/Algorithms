package BinaryTree;

import java.util.*;

public class DeleteBST {

    /*
                          3                             3
                         / \                           / \
                        2   8           -->           2   10
                           / \                           /  \
                          6   10                        6    12
                                \
                                 12

        delete(root, 8)
        Step1: find the node to be deleted.
        Step2: delete it. But how many possible situations?
        (After we delete the node, tree must be still maintain as a BST)

            Simple case
            case1: the node to be deleted has no children
                    3                             3
                   / \           -->             /                 return null
                  2   8                         2

            case2: the node to be deleted has no left child
                    3                             3
                   / \           -->             / \               return root.right
                  2   8                         2   10
                       \                              \
                        10                             12
                          \
                           12

            case3: the node to be deleted has no right child
                        3                             3
                       / \           -->             / \               return root.left
                      2   8                         2   6
                         /                             /
                        6                             5
                       /
                      5

            General case
            case4: the node to be deleted has both left and right child. We need to move its right-child's most left-child
                   to the delete place.

            which node to replace 8:
                - either from left-subtree, largest of the left-subtree
                - or from right-subtree, smallest of the right-subtree
            And we always choose the right one

            case4.1: node.right doesn't have left subtree, meaning itself is the smallest node in node's right-subtree,
                     so we just move node.right up.
                        3                             3
                       / \           -->             / \               node.right.left = node.left
                      2   8                         2   10             return node.right
                         / \                           /  \
                        6   10                        6    12
                       /      \                     /
                      5        12                  5

            case4.2: node.right have left subtree. So we need to find the smallest node, and move it up.
                        3                             3
                       / \           -->             / \               11.right = 9.right; 11.left = null
                      2   8                         2   9              9.left = 8.left; 9.right = 8.right
                         / \                           / \             return 9
                        6   12                        6   12
                           /   \                          /
                         11     14                       11
                        /                                 \
                       9                                   10
                        \
                         10
     */

    public TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;

        //First find the delete node
        if (root.value > val) {
            root.left = delete(root.left, val); // Remember return root! Else the final stack will run the remain code.
            return root;
        } else if (root.value < val) {
            root.right = delete(root.right, val);
            return root;
        }

        //Now we have found the node (root.value == val), then start the delete operation.
        if (root.left == null && root.right == null) return null; // case1
        else if (root.right == null && root.left != null) return root.left; // case2
        else if (root.left == null && root.right != null) return root.right; // case3

        //The more general case4: root has both left and right subtree
        if (root.right.left == null) { //case4.1: root.right is the smallest treeNode of root's right subtree
            root.right.left = root.left;
            return root.right;
        }
        //case4.2: root.right has left subtree, we must find the smallest treeNode, then replace with root.
        TreeNode cur = root.right.left;
        TreeNode pre = root.right;
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        pre.left = cur.right; // Now cur is the smallest treeNode of root's right subtree, it has not left subtree
        cur.left = root.left;
        cur.right = root.right;
        return cur;
    }

    public static void main(String[] args) {
        DeleteBST bst = new DeleteBST();
        TreeTraversal tt = new TreeTraversal();
        /*
                        3                             3
                       / \           -->             / \               11.right = 9.right; 11.left = null
                      2   8                         2   9              9.left = 8.left; 9.right = 8.right
                         / \                           / \             return 9
                        6   12                        6   12
                           /   \                         /  \
                         11     14                      11   14
                        /                                \
                       9                                  10
                        \
                         10
         */
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(14);
        TreeNode node8 = new TreeNode(9);
        TreeNode node9 = new TreeNode(10);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node5.left = node6;
        node5.right = node7;
        node6.left = node8;
        node8.right = node9;

//        tt.levelOrder(node1);
//        TreeNode newNode = bst.delete(node1, 8);
//        System.out.println();
//        tt.levelOrder(newNode);
    }

}
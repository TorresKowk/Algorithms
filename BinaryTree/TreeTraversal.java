package BinaryTree;

import java.util.*;

public class TreeTraversal {

    //Trick: base case usually refers to the null Child-Node below the leaf node

    public void preOrder(TreeNode root) {
        //base case
        if (root == null) return;

        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        //base case
        if (root == null) return;

        inOrder(root.left);
        System.out.println(root.value);
        inOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        //base case
        if (root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.value);
    }

//    public void levelOrder(TreeNode root) {
//        if (root == null) return;
//
//        //Initialization
//        Deque<TreeNode> queue = new ArrayDeque<>(); //Only use offerLast() & pollFist()
//        queue.offerLast(root);
//
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            public int binarySearch(int target, int[] array)
//            for (int i = 0; i < size; i++) {
//                TreeNode node = queue.pollFirst(); //expand one node in the current level, then generate its child
//                if (node.left != null) {
//                    queue.offerLast(node.left); //generate node.left
//                }
//                if (node.right != null) {
//                    queue.offerLast(node.right); //generate node.right
//                }
//                System.out.print(node.value + " ");
//            }
//            System.out.println();
//        }
//    }

//    public TreeNode treeBuildTool(Integer[] array) { // assume array has at least two valid elements
//        TreeNode root = new TreeNode(array[0]);
//        //last node's parent: n/2 - 1
//        for (int i = 1; i <= array.length / 2 - 1; i++) {
//            TreeNode curRoot =
//            int leftChildIndex = 2 * 1 + 1;
//            int rightChildIndex = 2 * i + 2;
//            TreeNode leftChild = new TreeNode(array[leftChildIndex]);
//            TreeNode rightChild = new TreeNode(array[rightChildIndex]);
//        }
//    }

    public static void main(String[] args) {
        TreeTraversal t = new TreeTraversal();

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

        System.out.println("pre-order");
        t.preOrder(node1);
        System.out.println("in-order");
        t.inOrder(node1);
        System.out.println("post-order");
        t.postOrder(node1);
    }

}

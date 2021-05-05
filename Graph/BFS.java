package Graph;

import java.util.*;

public class BFS {

    /*
        How to describe a BFS's action during an interview?

        - Data Structure: Maintain a FIFO queue, put all traversed nodes that haven't been expanded
          eg: 3 and then 2 into the queue (FIFO); queue head [3, 2] tail
        - Initial state
        - Expand a node s, e.g: visit / print its value...
        - Generate s's neighbor nodes: reach out to its neighboring nodes, dequeue node s
        - Termination condition: do a loop until the queue is empty

     */
    /*
        Question1: How to print a binary tree in Level Order?
                                                1                       Output: 1 newline
                                              /   \
                                             3     2                            3 2 newline
                                            / \   /
                                           5  4  7                              5 4 7 newline
                                          / \
                                         9   11                                 9 11 newline
        Data Structure: FIFO queue
        Algorithm: BFS1 (Breath First Search)
        Initialization: queue = {root}
        For each step:
            1. Record the size (=k) of the queue
            2. k times expand + generate
     */

    public void levelOrder(TreeNode root) {
        if (root == null) return;

        //Initialization
        Deque<TreeNode> queue = new ArrayDeque<>(); //Only use offerLast() & pollFist()
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst(); //expand one node in the current level, then generate its child
                if (node.left != null) {
                     queue.offerLast(node.left); //generate node.left
                }
                if (node.right != null) {
                    queue.offerLast(node.right); //generate node.right
                }
                System.out.print(node.value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(11);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node4.left = node7;
        node4.right = node8;

        //Question1
        bfs.levelOrder(node1);
    }

}

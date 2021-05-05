package Graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode {

    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

//    public TreeNode tool(int[] array) {
//
//    }

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
}

package BinaryTree;

import java.util.*;

public class TreeTraversalIteratively {

    /*
        In Java, STACK is usually a size limited memory area. By default, a several thousands levels recursion call
        would easily eat up all the space and throw StackOverFlowError - this is something you have to keep in mind
        (especially when the solution is in recursive way)

        In TreeTraversal, the recursion is internally done by using STACK to maintain the method call levels and directions,
        we can simulate this by ourselves, so a stack will be needed.

        The difference is, we use our own stack and the space used by our own stack which is on HEAP. The space consumed
        on STACK is trivial. - We do not change the total SC, but we move out the SC of STACK to HEAP. In general HEAP
        has much more space than STACK, in our daily computer.

        Another reason: Iterators(will be covered later)

                                                         5
                                                        / \
                                                       2   8
                                                      / \
                                                     1   3
     */
    /*
        Pre-order: 5, 2, 1, 3, 8

        Solution: we implement a stack by ourselves. The root is the top element on the stack (initialization)
                  Once the root is traversed, we can print it directly and we do not need to store it in the stack any more.

                  1. We always print root first, then root can be eliminated from stack.
                  2. We traverse left-sub first, so the right-sub should be retained in the stack before the left-sub is done.
        Demo:
        Stack:[tail -> 5 <- head]                                                               Print:
        {5} -> pop 5 and push 5.right (8) then push 5.left (2)                                  5
        {8,2} -> pop 2 and push 2.right (3) then push 2.left (1)                                2
        {8,3,1} -> pop 1                                                                        1
        {8,3} -> pop 3                                                                          3
        {8} -> pop 8                                                                            8
     */

    public void preOrder(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>(); // we only use offerLast() & pollLast() to implement a stack
        stack.offerLast(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = stack.pollLast();
                System.out.print(node.value + " ");
                if (node.right != null) stack.offerLast(node.right);
                if (node.left != null) stack.offerLast(node.left);
            }
        }
    }

    /*
        In-order: 1, 2, 3, 5, 8

        The problem is, we can not throw away the root in the stack before we traversed all the nodes in the left-subtree.
        For a particularly node, how can we know we have already traversed all the nodes in its left-subtree?

        Solution: the root is the top element in the stack, use a helper node to store the next "visiting" node and subtree
            1. when helper node is not null, we should traverse the subtree, so we push helper and go left
            2. when helper is null, which means the left subtree of the root is finished, the root is the top element in
               the stack. We can print the top, and let the helper = root.right
               (traverse the left-subtree fist, then top, then right-subtree) 当前栈顶的元素是一个左子树都被打印完的根
            3. do 1 and 2 until the helper is null and there is no nodes left in the stack

        Helper的两层含义:
            1. 当helper不是null，那么helper是下一个要入栈的node
            2. 如果helper是null，意味着当前栈顶元素是一个左子树都被打印完的根，那么弹栈，打印，helper = 右节点

        Demo:
        stack: [];
        helper: (initialized with root 5)                         Stack:                      Print:
        5                                                       {}
        5 -> 5 is not null, helper = 5.left                     {5}
        2 -> 2 is not null, helper = 2.left                     {5, 2}
        1 -> 1 is not null, helper = 1.left                     {5, 2, 1}
        null, helper = 1.right                                  {5, 2}                        1
        null, helper = 2.right                                  {5}                           2
        3 -> is not null, helper = 3.left                       {5, 3}
        null -> helper = 3.right                                {5}                           3
        null -> helper = 5.right                                {}                            5
        8 -> 8 is not null, helper = 8.left                     {8}
        null -> helper = 8.right                                {}                            8
        null                                                    {}

     */
    public void inOrder(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>(); //we only use offerLast() & pollLast() to implement a stack
        TreeNode helper = root; // initialized helper to root
      //Helper的两层含义:
      //1. 当helper不是null，那么helper是下一个要入栈的node
      //2. 如果helper是null，意味着当前栈顶元素是一个左子树都被打印完的根，那么弹栈，打印，helper = 右节点

        while (helper != null || !stack.isEmpty()) { // end loop while (helper == null && stack.isEmpty())
            if (helper != null) {
                stack.offerLast(helper);
                helper = helper.left;
            } else { // if (helper == null)
                TreeNode poll = stack.pollLast();
                System.out.print(poll.value + " ");
                helper = poll.right; // !!!Don't use helper = helper.right in this branch helper == null (NPE)
            }
        }
    }

    /*
        Post-order: 1, 3, 2, 8, 5
        The problem is, we need to traverse both left and right subtrees first, then we can eliminate the root from the stack,
        we need a MECHANISM to know, when we finished visiting all subtrees' nodes.

        What we need to know?
            - The Direction!
              We are visiting Down? or returning from Left? or returning Right?
        The root is the top element in the stack
        Maintain a Previous Node, to record the previous visiting node on the traversing path, so that we know what the
        direction we are taking now and what is the direction we are taking next.

        pre: to record the previous visiting node on the traversing path, so we can easily get the current status
        - root = stack.top
        - if pre == null -> going down (left subtree has priority)
        - if pre is cur's parent -> going down (left subtree has priority)
        - if pre == cur.left -> left subtree is finished, going cur.right
        - if pre == cur.right -> right subtree is finished, pop cur, going up
     */

    public void postOrder(TreeNode root) {
        if (root == null) return;

        TreeNode pre = null;
        Deque<TreeNode> stack = new ArrayDeque<>(); // we only use offerLast() & pollLast() to implement a stack
        stack.offerLast(root);

        /*
            Three status totally:
            1. pre is parent of cur (pre is above cur)
            2. pre = cur.left -> cur's left subtree is finished, turn to cur's right subtree
            3. pre = cur.right -> cur's left & right subtree are all finished, then we can print it and pop up
         */
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekLast();
            if (pre == null || pre.left == cur || pre.right == cur) { // case1: pre is parent of cur
                if (cur.left != null) {
                    stack.offerLast(cur.left);
                } else if (cur.right != null) {
                    stack.offerLast(cur.right);
                } else {
                    System.out.print(cur.value + " ");
                    stack.pollLast();
                }
            } else if (pre == cur.left) { // cur's left-subtree is finished, turn to cur's right-subtree
                if (cur.right != null) {
                    stack.offerLast(cur.right);
                } else {
                    System.out.print(cur.value + " ");
                    stack.pollLast();
                }
            } else if (pre == cur.right) { // cur's left & right-subtree is finished, we can print it
                System.out.print(cur.value + " ");
                stack.pollLast();
            }
            pre = cur; // for every step pre = cur at last, so in next step pre can represent the previous path of cur
        }
    }

    public static void main(String[] args) {
        TreeTraversalIteratively t = new TreeTraversalIteratively();
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        //Pre-order
        t.preOrder(node1);
        System.out.println();

        //In-order
        t.inOrder(node1);
        System.out.println();

        //Post-order
        t.postOrder(node1);
    }

}
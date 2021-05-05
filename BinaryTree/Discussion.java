package BinaryTree;

public class Discussion {

    /*
        Balanced Binary Tree: is commonly defined as a binary tree in which the height of the left and right subtrees of
                              of EVERY NODE differ by 1 or less.

                                                          10 == root
                                                         /  \
                                                        15  15
                                                       / \  / \
                                                      2   n n  n
                                                     / \
                                                    n   n

        Conclusion1: If a tree has n number of nodes, and it is balanced, then the height of tree is O(log_2(n))
        Caveat: If a tree has n number of nodes, but we don't know if it is balanced, then the height = O(n) (worse case)

        ================================================================================================================

        Complete Binary Tree: is a binary tree in which every level, except the last, is completed filled, and all
                                nodes are as far as left possible.

                                                        10 == root
                                                       /  \
                                                      15   15
                                                     / \   / \
                                                    2   12|20  n

        Conclusion2: If a tree is Complete Binary Tree, then it must be a Balanced Tree.

        ================================================================================================================

        Binary Search Tree: for EVERY single node in the tree, the values in its left subtree are ALL SMALLER than its
                            value, and the values in its right subtree are ALL LARGER than its value.
                            *对于树中的每一个节点，其左子树所有node的值都小于它，其右子树所有node的值都大于它*

                                                    10 == root
                                                   /  \
                                                  5   15
                                                 / \  / \
                                                2   7|12  20

        Conclusion3: If we print the value of the nodes in BST in in-order sequence, then it must form an ASCENDING order.

        What if there are duplicate values? EG: 10, 10, 5, 5, 5, 15, 2, 7, 12, 20
        Solution: we can use counter as a new member variable of TreeNode

                                                  (10,2) == root
                                                 /      \
                                             (5,3)      (15,1)
                                             /   \       /   \
                                           (2,1)(7,1)  (12,1)(20,1)

        Discussion (High Level):
            - Binary Tree 往往是最常见的，和 recursion 结合最紧密的面试题目类型
            - Reasons
                1. 每层的 nodes 具备的性质和传递的值，和下一层的性质往往一致。比较容易定义Recursive Rule
                2. Base case (generally): null pointer under the leaf node
                3. Example1: int getHeight(Node root)
                4. Example2: 统计tree里一共有几个nodes
            - TC/SC Analysis
                通常情况下考虑两种特殊情况
                    1. LinkedList (Tree只有一边)
                    2. Complete Binary Tree
            - Fundamental Knowledge
                1. Traversal of a binary tree
                2. Definition
                    - Balanced Binary Tree
                    - Complete Binary Tree
                    - Binary Search Tree (BST)

     */

}
package Tree;

/*

   Description:
       The following program demonstrates the solution for Preorder Traversal of a Binary Tree
       using an iterative approach with a Stack instead of recursion...

   Problem Statement:
       -> Given the root of a binary tree, perform preorder traversal (Root → Left → Right)...
       -> Print the values of the nodes in the preorder sequence...
       -> Solve the problem iteratively without using recursion...

   Approach:
       > Stack-Based Iterative Method:
           i. Use a Stack to simulate recursive function calls...
           ii. Push the root node into the stack...
           iii. While the stack is not empty:
               a. Pop the top node, process (print) its value...
               b. Push the right child first, then push the left child...
               c. This ensures that the left subtree is processed before the right subtree...

   Algorithm Steps:
       -> Stack-Based Preorder Traversal:
           1. Check if the tree is empty (if root is null, return)...
           2. Initialize an empty stack and push the root node into it...
           3. While the stack is not empty:
               a. Pop the top node, print its value...
               b. If the popped node has a right child, push it into the stack...
               c. If the popped node has a left child, push it into the stack...
           4. Repeat the process until all nodes are processed...

       -> Main Method Flow:
           1. Construct a sample binary tree...
           2. Call the preorder method to print the preorder traversal sequence...
           3. Display the output...

   Key Characteristics:
       -> Efficiently performs preorder traversal in O(N) time complexity...
       -> Uses a Stack (LIFO) to eliminate recursion...
       -> Processes the nodes in Root → Left → Right order...
       -> Ensures left child is processed before right child using Stack logic...

   Implementation Details:
       -> Uses a Stack<TreeNode> to store nodes dynamically...
       -> Pushes the right child before the left child to maintain traversal order...
       -> Traverses all nodes once, making it an O(N) time complexity solution...

   Time and Space Complexity:
       -> Time Complexity: O(N), where N is the number of nodes in the tree...
       -> Space Complexity: O(N) in the worst case (skewed tree), O(log N) for balanced trees...

*/

import java.util.Stack;

public class Preorder_Traversal_Iterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (! st.isEmpty()) {

            TreeNode current = st.pop();
            System.out.print(current.val + " ");

            if (current.right != null) {
                st.push(current.right);
            }

            if (current.left != null) {
                st.push(current.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Preorder traversal of the Tree is : ");
        preorder(root);
    }
}

package Tree;

/*

   Description:
       Following program demonstrates the implementation of the "Postorder Tree Traversal" algorithm
       using an iterative approach with a stack and linked list...

   Problem Statement:
       -> Given a binary tree data structure...
       -> Postorder traversal visits nodes in the order: left subtree, right subtree, root...
       -> The task is to perform a postorder traversal iteratively and return all visited nodes in that order...
       -> While recursive postorder traversal is straightforward, an iterative implementation requires
          clever manipulation of data structures to achieve the same result without recursion...

   Approach:
       > Iterative Postorder Traversal with Stack and LinkedList:
           i. Use a stack data structure to manage nodes...
           ii. Use a LinkedList with addFirst operation to reverse the order of processing...
           iii. Follow a modified preorder traversal (root-right-left) and reverse the result:
               a. Visit nodes in root-right-left order by pushing left child after right child...
               b. Add each node's value to the front of the result list, effectively reversing the order...
               c. This clever inversion produces the desired left-right-root postorder sequence...
           iv. Collect node values in a LinkedList during traversal...

   Algorithm Steps:
       -> Iterative Postorder Implementation:
           1. Create an empty LinkedList to store results and check if tree is empty...
           2. Initialize a stack and push the root node...
           3. Enter a loop that continues while the stack is not empty:
               a. Pop a node from the stack...
               b. Add its value to the front of the result list (using addFirst)...
               c. Push left child to stack (if exists)...
               d. Push right child to stack (if exists)...
               e. Due to LIFO nature of stack, right child will be processed before left...
           4. Return the result list, which will have values in postorder sequence...
       -> Main Method Flow:
           1. Create a binary tree structure for testing...
           2. Call the iterative postorder traversal function...
           3. Print the resulting list of node values...

   Key Characteristics:
       -> Efficiently traverses the entire tree in O(N) time without recursion...
       -> Uses a clever combination of modified preorder traversal and list reversal...
       -> Achieves postorder sequence without the complexity of true iterative postorder...
       -> Can handle arbitrarily large trees without risk of stack overflow...

       > Implementation Details:
           -> Uses LinkedList instead of ArrayList to leverage O(1) insertion at front...
           -> Handles the base case of empty trees gracefully...
           -> Creates a test tree structure with 7 nodes for demonstration...
           -> Processes right before left children in the stack for proper result order...

   Time and Space Complexity:
       -> Time Complexity: O(N) where N is the number of nodes in the tree...
       -> Space Complexity: O(H) where H is the height of the tree, for storing nodes in the stack...

*/

import java.util.LinkedList;
import java.util.Stack;

public class Post_Order_Traversal_Iterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    public static LinkedList<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while (! st.isEmpty()) {

            TreeNode node = st.pop();
            result.addFirst(node.val);

            if (node.left != null) st.push(node.left);
            if (node.right != null) st.push(node.right);
        }

        return result;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        LinkedList<Integer> ans = postorderTraversal(root);

        System.out.println("Postorder traversal of the Tree is : ");
        System.out.println(ans);
    }

}

package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Inorder Tree Traversal" algorithm
        using an iterative approach with a stack...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Inorder traversal visits nodes in the order: left subtree, root, right subtree...
        -> The task is to perform an inorder traversal iteratively and return all visited nodes in that order...
        -> While recursive inorder traversal is straightforward, an iterative implementation requires
           careful state management to mimic the recursive call stack...

    Approach:
        > Iterative Inorder Traversal with Stack:
            i. Use a stack data structure to simulate the recursion stack...
            ii. Process nodes in left-root-right order...
            iii. Follow a specific traversal strategy:
                a. Push all left nodes onto the stack...
                b. When no more left nodes, pop from stack, process the node, and move to right subtree...
                c. Repeat until stack is empty and no nodes remain to process...
            iv. Collect node values in an ArrayList during traversal...

    Algorithm Steps:
        -> Iterative Inorder Implementation:
            1. Create an empty ArrayList to store results and check if tree is empty...
            2. Initialize an empty stack to manage traversal state...
            3. Start with the root node as current node (temp)...
            4. Enter a loop that continues until all nodes are processed:
                a. If current node is not null:
                   - Push it onto the stack...
                   - Move to its left child...
                b. If current node is null:
                   - Check if stack is empty (traversal complete)...
                   - If not empty, pop a node from stack...
                   - Process (add) the node's value to result list...
                   - Move to its right child...
            5. Return the populated list of node values...
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Call the inorder traversal function...
            3. Print the resulting list of node values...

    Key Characteristics:
        -> Efficiently traverses the entire tree in O(N) time without recursion...
        -> Uses a stack to explicitly manage the traversal state...
        -> Preserves the correct left-root-right ordering of inorder traversal...
        -> Can handle arbitrarily large trees without risk of stack overflow...

        > Implementation Details:
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...
            -> Uses a temporary pointer variable to track current node being processed...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(H) where H is the height of the tree, for storing nodes in the stack...

*/

import java.util.ArrayList;
import java.util.Stack;

public class Inorder_Traversal_Iterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<Integer> inOrderTraversal(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Stack<TreeNode> st = new Stack<>();
        TreeNode temp = root;

        while (true) {

            if (temp != null) {
                st.push(temp);
                temp = temp.left;
            } else {
                if (st.isEmpty()) {
                    break;
                }
                temp = st.pop();
                ans.add(temp.val);
                temp = temp.right;
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> ans = inOrderTraversal(root);

        System.out.println("Inorder traversal of the Tree is : ");
        System.out.println(ans);
    }
    
}

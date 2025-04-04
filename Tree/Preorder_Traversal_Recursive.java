package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Preorder Tree Traversal" algorithm
        using a recursive approach...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Preorder traversal visits nodes in the order: root, left subtree, right subtree...
        -> The task is to perform a preorder traversal and return all visited nodes in that order...
        -> Preorder traversal is a depth-first search strategy that prioritizes visiting the root
           before exploring any of its children...

    Approach:
        > Recursive Preorder Traversal:
            i. Use depth-first traversal methodology...
            ii. Process current node before its children...
            iii. Follow a specific visitation order:
                a. Visit the root node first...
                b. Recursively traverse the left subtree...
                c. Recursively traverse the right subtree...
            iv. Collect node values in an ArrayList during traversal...

    Algorithm Steps:
        -> Recursive Preorder Implementation:
            1. Check if current node is null (base case)...
            2. Add current node's value to the result list...
            3. Recursively call traversal on left child...
            4. Recursively call traversal on right child...
            5. Return the populated list of node values...
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Initialize an empty ArrayList to store the traversal result...
            3. Call the preorder traversal function...
            4. Print the resulting list of node values...

    Key Characteristics:
        -> Efficiently traverses the entire tree in O(N) time where N is the number of nodes...
        -> Uses recursion to naturally implement the depth-first search pattern...
        -> Preserves the hierarchical structure of the tree in the output sequence...
        -> Results in root-to-leaf oriented processing...

        > Implementation Details:
            -> Uses a helper function (traverse) that accepts and modifies a shared ArrayList...
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(H) for the recursion stack where H is the height of the tree,
           plus O(N) for storing the result...

*/

import java.util.ArrayList;

public class Preorder_Traversal_Recursive {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<Integer> traverse(TreeNode root, ArrayList<Integer> ans) {

        if (root == null) {
            return ans;
        }

        ans.add(root.val);

        traverse(root.left, ans);
        traverse(root.right, ans);

        return ans;
    }

    private static ArrayList<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        return traverse(root, ans);
        
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> ans = preorderTraversal(root);

        System.out.println("Preorder traversal of the Tree is : ");
        System.out.println(ans);
    }
}
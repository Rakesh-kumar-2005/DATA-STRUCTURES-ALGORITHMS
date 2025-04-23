package Tree;

/*

Description:
    This program performs the boundary traversal of a binary tree...
    Boundary traversal visits the nodes forming the outline (boundary) of the binary tree...
    The traversal includes three parts: left boundary (excluding leaf nodes), leaf nodes, and right boundary (in reverse order, excluding leaf nodes)...

Problem Statement:
    -> Given a binary tree, perform its boundary traversal...
    -> The boundary traversal consists of:
       - Root node...
       - Left boundary (top to bottom, excluding leaf nodes)...
       - Leaf nodes (left to right)...
       - Right boundary (bottom to top, excluding leaf nodes)...
    -> The program implements this specialized traversal using different helper functions...

Approach:
    > Divide-and-Conquer Boundary Traversal:
        1. Break down the boundary traversal into four components:
           - Root node (handled separately)...
           - Left boundary (excluding leaves)...
           - All leaf nodes...
           - Right boundary (in reverse order, excluding leaves)...
        2. Use different traversal techniques for each component...
        3. Combine the results in the correct order...

Algorithm Steps:
    -> boundaryTraversal() Method:
        1. Initialize the result ArrayList...
        2. Handle base case: If the root is null, return empty result...
        3. Handle special case: If the root is a leaf, add it to result and return...
        4. Add the root value (if not a leaf) to the result list...
        5. Call addLeftBoundary() to add the left boundary nodes...
        6. Call addLeaves() to add all leaf nodes...
        7. Call addRightBoundary() to add the right boundary nodes in reverse order...
        8. Return the final result...
        
    -> isLeaf() Method:
        1. Check if both left and right children are null...
        2. Return true if the node is a leaf, false otherwise...
        
    -> addLeftBoundary() Method:
        1. Start from the left child of the root...
        2. While current node is not null:
           - If current node is not a leaf, add its value to result...
           - Move to the left child if it exists, otherwise to the right child...
        
    -> addLeaves() Method:
        1. If current node is a leaf, add its value to result...
        2. Recursively process left subtree if it exists...
        3. Recursively process right subtree if it exists...
        
    -> addRightBoundary() Method:
        1. Start from the right child of the root...
        2. Use a stack to temporarily store values (for reverse order)...
        3. While current node is not null:
           - If current node is not a leaf, push its value to stack...
           - Move to the right child if it exists, otherwise to the left child...
        4. Pop and add values from the stack to result (achieving bottom-to-top order)...
        
    -> Main Method Flow:
        1. Create a sample binary tree with 7 nodes (complete binary tree of height 3)...
        2. Call boundaryTraversal() with the root node...
        3. Print the boundary traversal of the tree...

Key Characteristics:
    -> Divides the boundary traversal into distinct components...
    -> Uses different traversal strategies for different parts of the boundary...
    -> Handles leaf nodes separately to avoid duplication...
    -> Uses a stack to reverse the order of right boundary nodes...
    -> Handles special cases like null trees and trees with only a root node...

Implementation Details:
    -> The package is named "Tree"...
    -> Defines a static inner class TreeNode to represent each node in the binary tree...
    -> Each TreeNode contains:
       - An integer value (val)...
       - References to left and right child nodes (initialized to null)...
    -> Uses both iterative and recursive approaches for different parts of the traversal...
    -> Uses ArrayList to store the final result and Stack for right boundary reversal...
    -> Constructs a sample binary tree with 7 nodes in the main method...

Time and Space Complexity:
    -> Time Complexity: O(n) where n is the number of nodes in the tree...
       (Each node is processed at most once across all methods)...
    -> Space Complexity: O(h) where h is the height of the tree...
       (Due to the recursion stack for leaf nodes traversal and the stack for right boundary, worst case O(n) for skewed trees)...

Potential Improvement:
    -> Could implement a fully iterative approach to avoid recursion stack limitations...
    -> Could add visualization of the boundary path...
    -> Could optimize the traversal for specific tree structures...
    -> Could add validation for edge cases or strange tree configurations...

*/

import java.util.ArrayList;
import java.util.Stack;

public class Boundary_Traversal_Of_Binary_Tree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

    }

    private static void addLeftBoundary(TreeNode root, ArrayList<Integer> ans) {
        
        TreeNode currNode = root.left;

        while (currNode != null) {
            if (! isLeaf(currNode)) {
                ans.add(currNode.val);
            }

            if (currNode.left != null) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
        
    }

    private static void addLeaves(TreeNode root, ArrayList<Integer> ans) {

        if (isLeaf(root)) {
            ans.add(root.val);
        }

        if (root.left != null) {
            addLeaves(root.left, ans);
        }

        if (root.right != null) {
            addLeaves(root.right, ans);
        }

    }

    private static void addRightBoundary(TreeNode root, ArrayList<Integer> ans) {

        TreeNode currNode = root.right;
        Stack<Integer> st = new Stack<>();

        while (currNode != null) {
            
            if (! isLeaf(currNode)) {
                st.push(currNode.val);
            }

            if (currNode.right != null) {
                currNode = currNode.right;
            } else {
                currNode = currNode.left;
            }
            
        }

        while (! st.isEmpty()) {
            ans.add(st.pop());
        }

    }

    private static ArrayList<Integer> boundaryTraversal(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        if (isLeaf(root)) {
            ans.add(root.val);
        }

        addLeftBoundary(root, ans);
        addLeaves(root, ans);
        addRightBoundary(root, ans);

        return ans;

    }

    private static boolean isLeaf(TreeNode root) {

        return (root.left == null) && (root.right == null);

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> result = boundaryTraversal(root);
        System.out.println("Boundary traversal of the Binary Tree is : ");
        System.out.println(result);

    }
    
}

package Tree;

/*

Description:
    This code determines if a binary tree is height-balanced using a recursive approach...
    A height-balanced binary tree is one where the height difference between left and right subtrees of every node is not more than 1...

Problem Statement:
    -> Given a binary tree, check if it is height-balanced...
    -> A binary tree is balanced if for each node in the tree, the difference between the height of the left subtree and right subtree is at most 1...
    -> The program implements an efficient bottom-up approach to solve this problem...

Approach:
    > Bottom-up Recursion with Early Termination:
        1. Modify the standard maxDepth function to also check for balance condition...
        2. Use -1 as a special return value to indicate an unbalanced subtree...
        3. Once an unbalanced condition is detected, propagate the -1 signal up the recursion stack...
        4. This approach avoids redundant height calculations and provides early termination...

Algorithm Steps:
    -> maxDepth() Method:
        1. Base case: If the root is null (empty tree), return 0...
        2. Recursively calculate the height of the left subtree...
        3. Recursively calculate the height of the right subtree...
        4. If left subtree is unbalanced (returns -1), propagate -1 upward...
        5. If right subtree is unbalanced (returns -1), propagate -1 upward...
        6. Check if current node is balanced by comparing left and right heights:
           - If the absolute difference exceeds 1, return -1 (unbalanced)...
        7. If balanced, return the max height of subtrees + 1 (normal height calculation)...
        
    -> isBalanced() Method:
        1. Call the modified maxDepth() function with the root node...
        2. Return true if the result is not -1 (tree is balanced)...
        3. Return false if the result is -1 (tree is unbalanced)...

Key Characteristics:
    -> Uses a single traversal of the tree to check balance and calculate height...
    -> Employs a post-order traversal (left, right, node)...
    -> Uses a sentinel value (-1) to signal imbalance...
    -> Ensures early termination once an imbalance is detected...
    -> Avoids redundant recalculations that would occur in a naive approach...

Implementation Details:
    -> maxDepth() is a private helper method that:
       - Returns the height of the tree if balanced...
       - Returns -1 if the tree is unbalanced...
    -> isBalanced() is the public-facing method that:
       - Returns a boolean indicating if the tree is balanced...
       - Translates the numeric result from maxDepth into a boolean...
    -> Uses Math.abs() to check the absolute difference between subtree heights...
    -> Uses Math.max() to calculate the maximum height between subtrees...

Time and Space Complexity:
    -> Time Complexity: O(n) where n is the number of nodes in the tree...
       (Each node is processed exactly once)...
    -> Space Complexity: O(h) where h is the height of the tree...
       (Due to the recursion stack, worst case O(n) for skewed trees)...

Potential Improvement:
    -> Could implement an iterative approach to avoid recursion stack limitations...
    -> Could add a result object to track additional information about imbalances...
    -> Could be extended to also return the specific nodes where imbalance occurs...
    -> Could include validation for edge cases or null pointers...

*/

public class Balanced_Binary_Tree {

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

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        if (leftHeight == - 1) {
            return - 1;
        }

        if (rightHeight == - 1) {
            return - 1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return - 1;
        }

        int maxHeight = Math.max(rightHeight, leftHeight);

        return maxHeight + 1;

    }

    public static boolean isBalanced(TreeNode root) {

        return maxDepth(root) != - 1;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Is it a Balanced tree : " + isBalanced(root));
    }
}

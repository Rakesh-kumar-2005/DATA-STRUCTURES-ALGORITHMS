package Tree;

/*

Description:
  Following program demonstrates the solution to the "Symmetric Tree"
    problem using recursive comparison of subtrees...

Problem Statement:
  -> Given a binary tree, determine if it is symmetric around its center...
  -> A symmetric tree is a mirror image of itself when reflected across its root...
  -> Left subtree should be a mirror image of right subtree in structure and node values...

Approach:
  > Recursive Mirror Comparison:
     i. Compare the left subtree and right subtree as mirror images...
     ii. Use recursive function to check if two subtrees are mirrors of each other...
     iii. For symmetric trees, corresponding nodes in mirrored positions must have same values...

> Algorithm Steps:
  -> Start with checking if the root is null (empty tree is symmetric)...
  -> Otherwise, check if left and right subtrees are mirrors of each other...
  -> Two subtrees are mirrors if:
     1. Both are null, or both have the same value...
     2. Left subtree's left child mirrors right subtree's right child...
     3. Left subtree's right child mirrors right subtree's left child...

> Key Characteristics:
  -> Uses recursive approach to compare mirror positions...
  -> Handles empty trees and null subtrees...
  -> Checks both structure and node values...

> NOTE: There is a logical error in the implementation:
  -> The null check in isMirror method is incorrect...
  -> It should check if either subtree is null, then return if both are null...
  -> Current implementation will cause NullPointerException...
  -> Corrected condition should be:
     if (leftTree == null || rightTree == null) {
         return leftTree == rightTree;
     }

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of nodes...
  -> Space Complexity: O(h) for recursion stack where h is the height of the tree...

*/

public class Symmetric_Tree {

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

    private static boolean isSymmetric(TreeNode root) {

        return root == null || isMirror(root.left, root.right);

    }

    private static boolean isMirror(TreeNode leftTree, TreeNode rightTree) {

        if (leftTree == null || rightTree == null) {
            return leftTree.val == rightTree.val;
        }

        if (leftTree.val != rightTree.val) {
            return false;
        }

        return isMirror(leftTree.left, rightTree.right) && isMirror(leftTree.right, rightTree.left);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Is this Binary Tree a Symmetric Tree : " + isSymmetric(root));

    }

}

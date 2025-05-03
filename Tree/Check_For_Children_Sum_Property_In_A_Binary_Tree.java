package Tree;

/*

Description:
 Following program demonstrates the solution to the "Children Sum Property in Binary Tree"
   problem using a two-pass recursive approach...

Problem Statement:
 -> Given a binary tree, modify it such that each node's value equals the sum of its children's values...
 -> If a node's value is less than the sum of its children, update the node's value to the sum...
 -> If a node's value is greater than the sum of its children, update the children's values to match the parent...
 -> After transformation, every non-leaf node's value should equal the sum of its children's values...

Approach:
 > Two-Pass Recursive Traversal:
    i. First pass (top-down): Ensure parent values are at least as large as the sum of their children...
    ii. Second pass (bottom-up): Update parent values to be the sum of their children...
    iii. Use inorder traversal to verify the transformation...

> Algorithm Steps:
 -> First traversal (top-down):
    1. For each node, calculate the sum of its children's values...
    2. If children's sum is greater than or equal to node's value, update node's value to the sum...
    3. Otherwise, propagate parent's value to children (update children to match parent)...
    4. Recurse on left and right subtrees...
 -> Second traversal (bottom-up):
    1. After processing both subtrees, recalculate the sum of children's values...
    2. Update the node's value to the sum of its children (if it has any children)...
 -> Use inorder traversal to verify the transformation...

> Key Characteristics:
 -> Maintains the children sum property while modifying the tree...
 -> Works with a single traversal function but with both top-down and bottom-up actions...
 -> Preserves the structure of the tree while only changing node values...
 -> May significantly alter the original node values...
 -> Handles leaf nodes appropriately (no changes needed)...

> Implementation Details:
 -> Uses a helper function for inorder traversal to visualize the transformation...
 -> First prints the original inorder traversal...
 -> Then applies the transformation and prints the modified inorder traversal...

> Time and Space Complexity:
 -> Time Complexity: O(n) where n is the number of nodes...
 -> Space Complexity: O(h) for recursion stack where h is the height of the tree...

*/

import java.util.ArrayList;

public class Check_For_Children_Sum_Property_In_A_Binary_Tree {

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

    private static void changeTree(TreeNode root) {

        if (root == null) {
            return;
        }

        int childVal = 0;

        if (root.left != null) {
            childVal += root.left.val;
        }

        if (root.right != null) {
            childVal += root.right.val;
        }

        if (childVal >= root.val) {
            root.val = childVal;
        } else {

            if (root.left != null) {
                root.left.val = root.val;
            }

            if (root.right != null) {
                root.right.val = root.val;
            }

        }

        changeTree(root.left);
        changeTree(root.right);

        childVal = 0;
        if (root.left != null) {
            childVal += root.left.val;
        }

        if (root.right != null) {
            childVal += root.right.val;
        }

        if (root.left != null || root.right != null) {
            root.val = childVal;
        }

    }

    private static ArrayList<Integer> inOrderTraversal(TreeNode root, ArrayList<Integer> ans) {

        if (root == null) {
            return ans;
        }

        inOrderTraversal(root.left, ans);
        ans.add(root.val);
        inOrderTraversal(root.right, ans);

        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        // Level 1
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // Level 2
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Level 3
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);

        // Level 4
        root.left.left.left.left = new TreeNode(16);
        root.left.left.left.right = new TreeNode(17);
        root.left.left.right.left = new TreeNode(18);
        root.left.left.right.right = new TreeNode(19);
        root.left.right.left.left = new TreeNode(20);
        root.left.right.left.right = new TreeNode(21);
        root.left.right.right.left = new TreeNode(22);
        root.left.right.right.right = new TreeNode(23);
        root.right.left.left.left = new TreeNode(24);
        root.right.left.left.right = new TreeNode(25);
        root.right.left.right.left = new TreeNode(26);
        root.right.left.right.right = new TreeNode(27);
        root.right.right.left.left = new TreeNode(28);
        root.right.right.left.right = new TreeNode(29);
        root.right.right.right.left = new TreeNode(30);
        root.right.right.right.right = new TreeNode(31);

        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println("Inorder traversal of the original Binary Tree is : ");

        System.out.println(inOrderTraversal(root, ans));

        ans = new ArrayList<>();
        changeTree(root);

        System.out.println("Inorder traversal of the modified Binary Tree according to the children sum property is : ");
        System.out.println(inOrderTraversal(root, ans));


    }

}

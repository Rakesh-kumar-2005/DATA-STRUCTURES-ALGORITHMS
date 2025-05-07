package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Construct Binary Tree From Inorder And Preorder Traversal" problem...
    -> It reconstructs the original binary tree structure given its inorder and preorder traversal arrays...
    -> Leverages the unique properties of traversal sequences to determine the tree's exact structure...

Problem Statement:
    -> Given two integer arrays preorder and inorder...
    -> The preorder array represents the preorder traversal of a binary tree...
    -> The inorder array represents the inorder traversal of the same binary tree...
    -> The task is to construct and return the binary tree...

Approach:
    > Understanding Traversal Properties:
        i. In preorder traversal (Root-Left-Right), the first element is always the root node...
        ii. In inorder traversal (Left-Root-Right), elements to the left of root belong to left subtree...
        iii. Elements to the right of root in inorder belong to right subtree...

    > Algorithm Steps:
        -> Create a HashMap for inorder indices:
            1. Map each value to its index in the inorder array for O(1) lookup...
            2. This eliminates the need for linear search when finding root positions...

        -> Recursive Construction Process:
            1. Base case: Return null if any array segment is empty...
            2. Create the root node from the first element in current preorder segment...
            3. Find position of this root value in inorder array using the HashMap...
            4. Calculate the size of left subtree using the inorder array...
            5. Recursively build left subtree using corresponding segments of both arrays...
            6. Recursively build right subtree using remaining segments...
            7. Return the completed root node with its left and right subtrees...

    > Key Characteristics:
        -> Efficiently reconstructs the unique binary tree from traversal information...
        -> Uses HashMap to optimize the search operations...
        -> Handles empty tree cases properly...
        -> Preserves the structural relationships between nodes...
        -> Verifies the result by performing traversals on the constructed tree...

    > Implementation Details:
        -> Uses recursive helper function with array segment boundaries as parameters...
        -> Maintains proper array indexing for preorder and inorder segments...
        -> Creates test case with 5 nodes for demonstration...
        -> Provides verification by comparing original arrays with traversals of the constructed tree...
        -> Includes helper methods for tree traversal to validate the solution...

    > Time and Space Complexity:
        -> Time Complexity: O(n) where n is the number of nodes in the tree...
        -> Each node is processed exactly once during construction...
        -> Space Complexity: O(n) for the recursion stack and HashMap storage...
        -> The height of the recursion tree is bounded by the height of the binary tree...
   
*/

import java.util.Arrays;
import java.util.HashMap;

public class Construct_Binary_Tree_From_Inorder_And_Preorder_Traversal {

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

    private static TreeNode buildTree(int[] preOrder, int[] inOrder) {

        if (preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            mp.put(inOrder[i], i);
        }

        return buildTreeWithInOrderAndPreOrder(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, mp);
    }

    private static TreeNode buildTreeWithInOrderAndPreOrder(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> mp) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preStart]);

        int inRoot = mp.get(preOrder[preStart]);
        int numsLeft = inRoot - inStart;

        root.left = buildTreeWithInOrderAndPreOrder(preOrder, preStart + 1, preStart + numsLeft, inOrder, inStart, inRoot - 1, mp);
        root.right = buildTreeWithInOrderAndPreOrder(preOrder, preStart + numsLeft + 1, preEnd, inOrder, inRoot + 1, inEnd, mp);

        return root;
    }

    private static void inOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    private static void preOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");

        inOrderTraversal(root.left);
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {

        int[] preOrder = {9, 3, 15, 20, 7};
        int[] inOrder = {3, 9, 20, 15, 7};

        TreeNode root = buildTree(preOrder, inOrder);

        System.out.println("To verify our Binary Tree : \n InOrder traversal array is \n " + Arrays.toString(inOrder) + "\n PreOrder traversal array is \n " + Arrays.toString(preOrder));

        System.out.println("And the Binary Tree from our solution has the following structure : ");

        System.out.println("InOrder Traversal : ");
        inOrderTraversal(root);

        System.out.println();

        System.out.println("InOrder Traversal : ");
        preOrderTraversal(root);

    }
}

package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Construct Binary Tree From Inorder And Postorder Traversal" problem...
    -> It reconstructs the original binary tree structure given its inorder and postorder traversal arrays...
    -> Leverages the unique properties of these traversal sequences to determine the tree's exact structure...

Problem Statement:
    -> Given two integer arrays postorder and inorder...
    -> The postorder array represents the postorder traversal of a binary tree...
    -> The inorder array represents the inorder traversal of the same binary tree...
    -> The task is to construct and return the binary tree with the original structure...

Approach:
    > Understanding Traversal Properties:
        i. In postorder traversal (Left-Right-Root), the last element is always the root node...
        ii. In inorder traversal (Left-Root-Right), elements to the left of root belong to left subtree...
        iii. Elements to the right of root in inorder belong to right subtree...

    > Algorithm Steps:
        -> Create a HashMap for inorder indices:
            1. Map each value to its index in the inorder array for O(1) lookup...
            2. This eliminates the need for linear search when finding root positions...

        -> Recursive Construction Process:
            1. Base case: Return null if any array segment is empty...
            2. Create the root node from the last element in current postorder segment...
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
        -> Maintains proper array indexing for postorder and inorder segments...
        -> Creates test case with 7 nodes for demonstration...
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

public class Construct_Binary_Tree_From_Inorder_And_Postorder_Traversal {

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

    private static TreeNode buildTree(int[] postOrder, int[] inOrder) {

        if (postOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        int poStart = 0;
        int poEnd = postOrder.length - 1;
        int inStart = 0;
        int inEnd = inOrder.length - 1;

        return buildTreeWithInOrderAndPostOrder(postOrder, poStart, poEnd, inOrder, inStart, inEnd, map);
    }

    private static TreeNode buildTreeWithInOrderAndPostOrder(int[] postOrder, int poStart, int poEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> map) {

        if (poStart > poEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = postOrder[poEnd];
        TreeNode root = new TreeNode(rootVal);

        int inRoot = map.get(rootVal);
        int numsLeft = inRoot - inStart;

        root.left = buildTreeWithInOrderAndPostOrder(postOrder, poStart, poStart + numsLeft - 1, inOrder, inStart, inRoot - 1, map);
        root.right = buildTreeWithInOrderAndPostOrder(postOrder, poStart + numsLeft, poEnd - 1, inOrder, inRoot + 1, inEnd, map);

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

    private static void postOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");

    }

    public static void main(String[] args) {

        int[] inOrder = {4, 2, 5, 1, 6, 3, 7};
        int[] postOrder = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root = buildTree(postOrder, inOrder);

        System.out.println("To verify our Binary Tree : \n InOrder traversal array is \n " + Arrays.toString(inOrder) + "\n PreOrder traversal array is \n " + Arrays.toString(postOrder));

        System.out.println("And the Binary Tree from our solution has the following structure : ");

        System.out.println("InOrder Traversal : ");
        inOrderTraversal(root);

        System.out.println();

        System.out.println("PostOrder Traversal : ");
        postOrderTraversal(root);

    }
    
}

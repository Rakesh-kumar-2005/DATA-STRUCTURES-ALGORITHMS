package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Deserialize A Binary Tree" problem...
    -> It reconstructs a binary tree from its string representation created during serialization...
    -> The deserialization process converts the serialized string back into the original tree structure...

Problem Statement:
    -> Given a string representation of a binary tree...
    -> Design an algorithm to deserialize the string back into the binary tree structure...
    -> The string uses comma-separated values, with "null" representing absent nodes...
    -> The task is to recreate the exact structure of the original binary tree...

Approach:
    > Level Order Reconstruction:
        i. Parse the serialized string into an array of node values...
        ii. Process values in pairs to connect left and right children to their parent nodes...
        iii. Use a queue to maintain the level-order traversal during reconstruction...

    > Algorithm Steps:
        -> Handle edge case:
            1. Return null if input string is empty...
            2. Otherwise, begin the deserialization process...

        -> Parse and initialize:
            1. Split the input string by comma delimiters into an array of node values...
            2. Create the root node from the first value in the array...
            3. Add the root node to a queue for level-order processing...

        -> Reconstruct the tree:
            1. Iterate through the remaining node values in pairs...
            2. For each parent node from the queue, process its left and right children...
            3. If a value is not "null", create a new node and connect it as a child...
            4. Add new nodes to the queue to process their children later...
            5. Continue until all values are processed...

    > Key Characteristics:
        -> Reconstructs the exact binary tree structure from serialized format...
        -> Handles null nodes properly using the "null" string marker...
        -> Maintains the level-order relationship between nodes...
        -> Works with binary trees of any shape and size...
        -> Pairs with serialization to enable storage and transmission of tree structures...

    > Implementation Details:
        -> Uses String.split() to parse the comma-separated values...
        -> Queue manages the breadth-first reconstruction process...
        -> Integer.parseInt() converts string values to node values...
        -> Includes inOrderTraversal method to verify the reconstructed tree...
        -> Uses a complex asymmetric binary tree as a test case...
        -> Values in the serialized string match the level-order traversal of the tree...

    > Time and Space Complexity:
        -> Time Complexity: O(n) where n is the number of nodes in the tree...
        -> Each node value is processed exactly once...
        -> Space Complexity: O(n) for the queue and node array...
        -> Queue size is bounded by the width of the tree...
   
*/

import java.util.LinkedList;
import java.util.Queue;

public class Deserialize_A_Binary_Tree {

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

    private static TreeNode deSerialize(String data) {

        if (data.isEmpty()) {
            return null;
        }

        String[] nodeValues = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        for (int i = 1; i < nodeValues.length; i++) {

            TreeNode currNode = q.poll();

            if (! nodeValues[i].equals("null")) {
                currNode.left = new TreeNode(Integer.parseInt(nodeValues[i]));
                q.add(currNode.left);
            }

            if (! nodeValues[++ i].equals("null")) {
                currNode.right = new TreeNode(Integer.parseInt(nodeValues[i]));
                q.add(currNode.right);
            }
        }

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

    public static void main(String[] args) {

        String data = "1,2,3,4,5,6,7,8,9,10,11,null,13,14,15,16,17,18,19,20,21,22,23,26,27,28,29,30,31,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null";

        TreeNode root = deSerialize(data);

        if (root == null) {
            System.out.println("The TreeNode is null...");
        } else {
            inOrderTraversal(root);
        }

    }

}

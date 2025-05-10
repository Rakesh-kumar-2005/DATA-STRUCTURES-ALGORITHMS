package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Serialize a Binary Tree" problem...
    -> It converts a binary tree data structure into a string representation that can be easily stored or transmitted...
    -> The serialization preserves the complete structure of the tree for later reconstruction...

Problem Statement:
    -> Given the root of a binary tree...
    -> Design an algorithm to serialize the tree into a string representation...
    -> The serialized string should contain enough information to reconstruct the original tree structure...
    -> Null nodes need to be properly represented to maintain the tree structure...

Approach:
    > Level Order Traversal (Breadth-First Search):
        i. Process the tree level by level using a queue...
        ii. Include null nodes in the serialization with a special marker ("X")...
        iii. Build a string representation with space-separated node values...

    > Algorithm Steps:
        -> Handle edge case:
            1. Return empty string if root is null...
            2. Otherwise, begin the serialization process...

        -> Perform level-order traversal:
            1. Initialize a StringBuilder to accumulate the serialized string...
            2. Start with the root node in the queue...
            3. Process nodes one by one from the queue...
            4. Append node value (or "X" for null) to the result string...
            5. For non-null nodes, add both left and right children to the queue...
            6. Continue until queue is empty...

    > Key Characteristics:
        -> Captures complete binary tree structure including null nodes...
        -> Uses space-efficient representation with space separators...
        -> Works with binary trees of any shape and size...
        -> Enables exact reconstruction of the original tree...
        -> Handles special cases like empty trees and incomplete branches...

    > Implementation Details:
        -> Uses StringBuilder for efficient string construction...
        -> Queue manages breadth-first traversal ordering...
        -> "X" represents null nodes in the serialized output...
        -> Space character separates each node value...
        -> Creates a complex asymmetric binary tree for demonstration...
        -> Some nodes are intentionally missing (e.g., root.right.left.left)...

    > Time and Space Complexity:
        -> Time Complexity: O(n) where n is the number of nodes in the tree...
        -> Each node is processed exactly once...
        -> Space Complexity: O(n) for the queue and result string...
        -> Queue size is bounded by the width of the tree...
   
*/

import java.util.LinkedList;
import java.util.Queue;

public class Serialize_A_Binary_Tree {

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

    private static String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            TreeNode currNode = q.poll();

            if (currNode == null) {
                ans.append("X ");
                continue;
            }

            ans.append(currNode.val).append(" ");
            q.add(currNode.left);
            q.add(currNode.right);
        }

        return ans.toString();

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
        root.right.left.right.left = new TreeNode(26);
        root.right.left.right.right = new TreeNode(27);
        root.right.right.left.left = new TreeNode(28);
        root.right.right.left.right = new TreeNode(29);
        root.right.right.right.left = new TreeNode(30);
        root.right.right.right.right = new TreeNode(31);

        String serializedTree = serialize(root);

        System.out.println(serializedTree);
        
    }

}

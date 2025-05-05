package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Minimum Time Taken To BURN The Binary Tree From A Node" problem...
    -> It calculates how long it takes for a fire to spread and burn the entire tree starting from a target node...
    -> Fire spreads to adjacent nodes (child nodes and parent node) in unit time...

Problem Statement:
    -> Given a binary tree and a target node...
    -> The task is to find the minimum time required to burn the entire binary tree if the fire starts from the target node...
    -> Fire spreads in all possible directions at the same time...
    -> Each edge takes 1 unit of time to burn...

Approach:
    > BFS with Parent Mapping:
        i. Create parent pointers to enable traversal upward in the tree...
        ii. Use BFS starting from the target node to simulate fire spread in all directions...
        iii. Track visited nodes to avoid burning the same node twice...

    > Algorithm Steps:
        -> First pass - Create parent mapping:
            1. Perform BFS from the root to populate the parent references for each node...
            2. Store these references in a HashMap for O(1) lookup time...
            3. This allows fire to spread in all directions: up, left, and right...

        -> Second pass - Simulate fire spread:
            1. Start BFS from the target node where fire begins...
            2. Use a visited HashMap to avoid revisiting nodes...
            3. For each node in the BFS level, explore three directions: left child, right child, and parent...
            4. Track the current time (levels of BFS)...
            5. Each level of BFS represents one unit of time for fire spread...

    > Key Characteristics:
        -> Handles fire spread in all directions from the target node...
        -> Works with arbitrary tree structures and any valid target node...
        -> Efficiently calculates minimum time to burn the entire tree...
        -> Uses HashMaps for quick lookups and tracking visited nodes...
        -> Supports any valid tree configuration...

    > Implementation Details:
        -> Uses two auxiliary HashMaps: one for parent pointers and one for visited status...
        -> Creates a complete binary tree for demonstration with 31 nodes over 4 levels...
        -> Uses a Queue for the breadth-first traversal simulation...
        -> Returns an integer representing the minimum time to burn the tree...
        -> Target node in the example is the leftmost node at level 4 (node with value 16)...

    > Time and Space Complexity:
        -> Time Complexity: O(n) where n is the number of nodes in the tree...
        -> Space Complexity: O(n) for storing parent pointers and visited status...
        -> Queue size is bounded by the number of nodes in the tree...
   
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Minimum_Time_Taken_To_BURN_The_Binary_Tree_From_A_Node {

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

    private static void parent_track(TreeNode root, HashMap<TreeNode, TreeNode> parentMap) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            TreeNode currNode = q.poll();

            if (currNode.left != null) {
                q.add(currNode.left);
                parentMap.put(currNode.left, currNode);
            }

            if (currNode.right != null) {
                q.add(currNode.right);
                parentMap.put(currNode.right, currNode);
            }

        }

    }

    private static int minimumTimeToBurn(TreeNode root, TreeNode targetNode) {

        if (root == null) {
            return - 1;
        }

        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parent_track(root, parentMap);

        HashMap<TreeNode, Boolean> visited = new HashMap<>();
        visited.put(targetNode, true);

        Queue<TreeNode> q = new LinkedList<>();
        q.add(targetNode);

        int minTime = -1;

        while (! q.isEmpty()) {

            int size = q.size();
            minTime++;

            for (int i = 0; i < size; i++) {

                TreeNode currNode = q.poll();

                TreeNode leftNode = currNode.left;
                TreeNode rightNode = currNode.right;
                TreeNode parentNode = parentMap.get(currNode);

                if (leftNode != null && visited.get(leftNode) == null) {
                    q.add(leftNode);
                    visited.put(leftNode, true);
                }

                if (rightNode != null && visited.get(rightNode) == null) {
                    q.add(rightNode);
                    visited.put(rightNode, true);
                }

                if (parentNode != null && visited.get(parentNode) == null) {
                    q.add(parentNode);
                    visited.put(parentNode, true);
                }

            }
        }

        return minTime;
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

        TreeNode targetNode = root.left.left.left.left;

        System.out.println("Minimum Time Taken To Burn The Binary Tree From The Target Node is : " + minimumTimeToBurn(root, targetNode));
    }
    
}

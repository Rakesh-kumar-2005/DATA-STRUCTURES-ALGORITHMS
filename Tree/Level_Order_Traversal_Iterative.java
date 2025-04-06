package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Level Order Tree Traversal" algorithm
        using an iterative approach with a queue...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Level order traversal visits nodes level by level, from left to right...
        -> The task is to perform a level order traversal iteratively and return all 
                visited nodes grouped by their levels...
        -> This traversal pattern requires processing all nodes at the current level 
                before moving to the next level...

    Approach:
        > Iterative Level Order Traversal with Queue:
            i. Use a queue data structure to maintain the processing order of nodes...
            ii. Process nodes level by level, from left to right...
            iii. Follow a specific traversal strategy:
                a. Enqueue the root node to start...
                b. For each level, determine the number of nodes at that level (queue size)...
                c. Process exactly that many nodes, adding their children to the queue for the next level...
                d. Keep track of nodes at each level in separate lists...
            iv. Collect node values in an ArrayList of ArrayLists where each inner list represents one level...

    Algorithm Steps:
        -> Iterative Level Order Implementation:
            1. Check if tree is empty, return empty result if so...
            2. Create an ArrayList of ArrayLists to store results by level...
            3. Initialize a queue with the root node...
            4. Enter a loop that continues until the queue is empty:
                a. Determine the current level size (number of nodes in queue)...
                b. Create a new ArrayList for the current level's nodes...
                c. Process exactly 'currSize' nodes from the queue:
                   - Remove a node from the queue...
                   - Add its left child to queue if it exists...
                   - Add its right child to queue if it exists...
                   - Add the current node's value to the current level's list...
                d. Add the completed level list to the result...
            5. Return the populated list of lists containing node values by level...
            
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Call the level order traversal function...
            3. Print the resulting lists of node values by level...

    Key Characteristics:
        -> Efficiently traverses the entire tree in O(N) time without recursion...
        -> Uses a queue to maintain the correct level-by-level processing order...
        -> Preserves the correct left-to-right ordering within each level...
        -> Groups nodes by their respective levels in the result structure...

        > Implementation Details:
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...
            -> Uses a nested loop to process each level separately...
            -> Uses LinkedList as the queue implementation...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(W) where W is the maximum width of the tree (maximum number of nodes at any level)...
                           Plus O(N) for storing all node values in the result lists...

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level_Order_Traversal_Iterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            int currSize = q.size();
            ArrayList<Integer> currList = new ArrayList<>();

            for (int i = 0; i < currSize; i++) {

                TreeNode curr = q.remove();

                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }

                currList.add(curr.val);

            }

            ans.add(currList);

        }

        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> ans = levelOrderTraversal(root);

        System.out.println("Level order traversal of the Tree is : ");

        for (ArrayList<Integer> curr : ans) {
            System.out.println(curr);
        }
    }
}

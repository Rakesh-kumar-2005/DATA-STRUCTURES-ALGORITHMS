package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Level Order Tree Traversal" algorithm
        using a recursive approach without using a queue...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Level order traversal visits nodes level by level, from left to right...
        -> The task is to perform a level order traversal recursively and return all 
            visited nodes grouped by their levels...
        -> While iterative level order traversal typically uses a queue, this implementation
            uses recursion with level tracking...

    Approach:
        > Recursive Level Order Traversal with Level Tracking:
            i. Use recursion with an additional parameter to track the current level in the tree...
            ii. Process nodes by their level number rather than visiting order...
            iii. Follow a specific traversal strategy:
                a. Track the current level for each node during DFS traversal...
                b. Maintain a list of lists where each inner list corresponds to a tree level...
                c. Append each node's value to the appropriate level's list...
                d. Recursively traverse left and right subtrees while incrementing the level counter...
            iv. Collect node values in an ArrayList of ArrayLists where each inner list represents one level...

    Algorithm Steps:
    
        -> Recursive Level Order Implementation:
            1. Create an empty ArrayList of ArrayLists to store results...
            2. Define a helper function that takes a node, current level, and result list...
            3. In the helper function:
                a. Check if the node is null (base case)...
                b. If result list size equals current level, create a new list for this level...
                c. Add the current node's value to its level's list...
                d. Recursively call helper for left child with level+1...
                e. Recursively call helper for right child with level+1...
            4. Start the recursive process with the root node at level 0...
            5. Return the populated list of lists containing node values by level...
            
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Call the level order traversal function...
            3. Print the resulting lists of node values by level...

    Key Characteristics:
        -> Uses depth-first traversal pattern but organizes results in level order...
        -> Doesn't require a queue data structure like traditional level order traversal...
        -> Maintains correct level-by-level grouping of nodes...
        -> Simple recursive implementation with minimal state management...

        > Implementation Details:
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...
            -> Uses a level counter to determine which list should store each node's value...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(H) where H is the height of the tree, for the recursion stack...
                           Plus O(N) for storing all node values in the result lists...

*/

import java.util.ArrayList;

public class Level_Order_Traversal_Recursive {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }


    private static ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode root) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int currLevel = 0;

        helper(root, currLevel, ans);

        return ans;
    }

    private static void helper(TreeNode root, int currLevel, ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return;
        }

        if (ans.size() == currLevel) {
            ans.add(new ArrayList<>());
        }

        ans.get(currLevel).add(root.val);

        helper(root.left, currLevel + 1, ans);
        helper(root.right, currLevel + 1, ans);
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

        System.out.println("Level Order traversal of the Tree is : ");

        for (ArrayList<Integer> curr : ans) {
            System.out.println(curr);
        }
    }
}

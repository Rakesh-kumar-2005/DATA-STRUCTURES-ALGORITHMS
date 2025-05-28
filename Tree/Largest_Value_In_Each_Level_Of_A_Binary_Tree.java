package Tree;

/*

Description:
    -> This program identifies the largest value present at each level of a binary tree...
    -> It employs a Breadth-First Search (BFS) traversal to process nodes level by level...

Problem Statement:
    -> Given the root of a binary tree...
    -> Return a list containing the largest value in each row (level) of the tree...

Approach:
    > Breadth-First Search (BFS) Traversal:
        -> Utilize a queue to perform level order traversal of the tree...
        -> At each level, determine the maximum value among all nodes...

Algorithm Steps:
    -> Base Case Handling:
        1. If the root is null, return an empty list as there are no levels to process...

    -> Initialization:
        1. Create an empty list `result` to store the largest values of each level...
        2. Initialize a queue `q` and add the root node to it...

    -> Level Order Traversal:
        1. While the queue is not empty:
            a. Determine the number of nodes at the current level (`currSize`)...
            b. Initialize `maxValue` to the minimum possible integer value...
            c. For each node in the current level:
                i. Dequeue the node from the queue...
                ii. Update `maxValue` if the node's value is greater...
                iii. Enqueue the left child if it exists...
                iv. Enqueue the right child if it exists...
            d. After processing all nodes at the current level, add `maxValue` to `result`...

    -> Return Result:
        1. After traversing all levels, return the `result` list containing the largest values...

Key Characteristics:
    -> Efficiently processes each level of the tree using BFS...
    -> Ensures that all nodes at a given level are considered before moving to the next...
    -> Maintains a running maximum for each level to identify the largest value...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of nodes in the tree, as each node is visited once...
    -> Space Complexity: O(w), where w is the maximum width of the tree (maximum number of nodes at any level), due to the queue used in BFS...

Demonstration:
    -> Constructs a binary tree with multiple levels...
    -> Applies the `largestValues` function to determine the largest value at each level...
    -> Outputs the results in a formatted manner, indicating the level and corresponding largest value...

*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Largest_Value_In_Each_Level_Of_A_Binary_Tree {

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

    private static ArrayList<Integer> largestValues(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            int currSize = q.size();
            int maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < currSize; i++) {

                TreeNode currNode = q.poll();
                maxValue = Math.max(maxValue, currNode.val);

                if (currNode.left != null) {
                    q.add(currNode.left);
                }

                if (currNode.right != null) {
                    q.add(currNode.right);
                }

            }

            result.add(maxValue);

        }

        return result;
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

        ArrayList<Integer> ans = largestValues(root);
        System.out.println("Largest values in each level of the Binary Tree is : ");

        for (int i = 0; i < ans.size(); i++) {
            System.out.println("Level " + (i + 1) + " -> " + ans.get(i));
        }

    }

}

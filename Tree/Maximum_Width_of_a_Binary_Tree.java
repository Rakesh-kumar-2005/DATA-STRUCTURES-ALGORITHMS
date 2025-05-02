package Tree;

/*

Description:
  Following program demonstrates the solution to the "Maximum Width of Binary Tree"
    problem using level order traversal with position indexing...

Problem Statement:
  -> Given a binary tree, find the maximum width of the tree...
  -> Width is defined as the number of nodes at the same level...
  -> Maximum width is the maximum width across all levels...

Approach:
  > Level Order Traversal with Node Position Tracking:
     i. Use BFS (level order traversal) to process the tree level by level...
     ii. Assign position indices to each node based on its position in a theoretical complete binary tree...
     iii. For each level, calculate width as (last node's position - first node's position + 1)...
     iv. Track and return the maximum width found across all levels...

> Algorithm Steps:
  -> Initialize a queue with the root node and its position (0)...
  -> Process the tree level by level:
     1. For each level, find the minimum position index to normalize positions...
     2. Track the first and last node positions in the current level...
     3. Calculate width as (last position - first position + 1)...
     4. For each node, enqueue its children with calculated positions:
        - Left child position: 2 * parent_position + 1
        - Right child position: 2 * parent_position + 2
     5. Update maximum width if current level's width is larger...
  -> Return the maximum width found...

> Key Characteristics:
  -> Uses level order traversal (BFS) with custom Pair class to track node positions...
  -> Handles position normalization to prevent integer overflow for deep trees...
  -> Efficiently calculates width using only first and last node positions...
  -> Works for all tree shapes, including complete, balanced, and skewed trees...

> Node Indexing Logic:
  -> Uses standard heap array indexing for binary tree nodes...
  -> For a node at index i:
     - Left child is at index 2i + 1
     - Right child is at index 2i + 2
  -> This indexing enables width calculation based on node positions...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the number of nodes...
  -> Space Complexity: O(w) where w is the maximum number of nodes at any level...

*/

import java.util.LinkedList;
import java.util.Queue;

public class Maximum_Width_of_a_Binary_Tree {

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

    static class Pair {

        TreeNode node;
        int count;

        public Pair(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        }

    }

    private static int findMaxWidth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        int maxWidth = 0;

        while (! q.isEmpty()) {

            int size = q.size();
            int min = q.peek().count;

            int first = 0;
            int last = 0;

            for (int i = 0; i < size; i++) {

                Pair currPair = q.poll();
                
                int currCount = currPair.count - min;
                TreeNode currNode = currPair.node;

                if (i == 0) {
                    first = currCount;
                }

                if (i == size - 1) {
                    last = currCount;
                }

                if (currNode.left != null) {
                    q.add(new Pair(currNode.left, 2 * currCount + 1));
                }

                if (currNode.right != null) {
                    q.add(new Pair(currNode.right, 2 * currCount + 2));
                }

            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;

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

        int maxWidth = findMaxWidth(root);
        System.out.println("Maximum Width of the above Binary Tree is = " + maxWidth);

    }

}

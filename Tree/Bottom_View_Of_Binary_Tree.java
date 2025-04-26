package Tree;

/*

Description:
 Following program demonstrates the solution to the "Bottom View of Binary Tree"
   problem using level order traversal and vertical line tracking...

Problem Statement:
 -> Given a binary tree, print the nodes visible from the bottom view...
 -> Bottom view consists of nodes that would be visible if the tree was viewed from below...
 -> Only the last node at each vertical line is included in the bottom view...

Approach:
 > Level Order Traversal with Vertical Line Mapping:
    i. Use a TreeMap to store the last node encountered at each vertical line...
    ii. Perform level order traversal (BFS) while tracking vertical line position...
    iii. For each vertical line, continuously update the node value (keeping the last/bottommost)...
    iv. Collect and return nodes in order from leftmost to rightmost vertical line...

> Algorithm Steps:
 -> Create a TreeMap to map vertical lines to node values (last node per line)...
 -> Initialize a queue for BFS traversal with pairs of (node, line position)...
 -> Start with root node at line position 0...
 -> While queue is not empty:
    1. Dequeue a pair (current node, current line)...
    2. Update the TreeMap with this node's value for the current line...
    3. Enqueue left child with line position decremented by 1...
    4. Enqueue right child with line position incremented by 1...
 -> Collect all values from the TreeMap in order of their keys (line positions)...

> Key Characteristics:
 -> Similar to Top View but always updates the map with the latest node at each line...
 -> TreeMap ensures vertical lines are processed from leftmost to rightmost...
 -> Level-order traversal ensures bottom-most nodes are processed last...
 -> Uses custom Pair class to track both node and its vertical line position...
 -> Works for all tree shapes and sizes...

> Line Position Convention:
 -> Root starts at vertical line 0...
 -> Going left decrements the line number by 1...
 -> Going right increments the line number by 1...

> Time and Space Complexity:
 -> Time Complexity: O(n log n) where n is the number of nodes (log n for TreeMap operations)...
 -> Space Complexity: O(n) for the queue and result storage...

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Bottom_View_Of_Binary_Tree {

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
        int line;

        public Pair(TreeNode node, int line) {
            this.node = node;
            this.line = line;
        }

    }

    private static ArrayList<Integer> bottomView(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        TreeMap<Integer, Integer> mp = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (! q.isEmpty()) {

            Pair currPair = q.remove();

            TreeNode currNode = currPair.node;
            int currLine = currPair.line;

            mp.put(currLine, currNode.val);

            if (currNode.left != null) {
                q.add(new Pair(currNode.left, currLine - 1));
            }

            if (currNode.right != null) {
                q.add(new Pair(currNode.right, currLine + 1));
            }
            
        }

        ans.addAll(mp.values());

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

        ArrayList<Integer> ans = bottomView(root);
        System.out.println("Bottom view of the Binary Tree is : ");

        for (int val : ans) {
            System.out.print(val + " ");
        }

    }
    
}

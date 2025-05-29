package Tree;

/*

Description:
    -> This program performs a level order traversal (Breadth-First Search) of an N-ary tree...
    -> It collects and displays the values of nodes at each level in separate lists...

Problem Statement:
    -> Given the root of an N-ary tree...
    -> Return a list of lists, where each sublist contains all node values at a particular depth (level) of the tree...

Approach:
    > Breadth-First Search (BFS) Traversal:
        -> Utilize a queue to traverse the tree level by level...
        -> At each level, gather the values of all nodes into a temporary list...

Algorithm Steps:
    -> Base Case Handling:
        1. If the root is null, return an empty list as there are no levels to process...

    -> Initialization:
        1. Create an empty list `result` to store values of each level...
        2. Initialize a queue `q` and enqueue the root node...

    -> Level Order Traversal:
        1. While the queue is not empty:
            a. Determine the number of nodes at the current level (`size`)...
            b. Create a temporary list `currResult` to hold the values of the current level...
            c. For each node in the current level:
                i. Dequeue a node from the queue...
                ii. Add its value to `currResult`...
                iii. Enqueue all of its children (if any)...
            d. Add `currResult` to the final `result` list...

    -> Return Result:
        1. Once all levels have been traversed, return the `result` list...

Key Characteristics:
    -> Effectively handles trees where nodes can have multiple children (N-ary tree)...
    -> Ensures all nodes at each level are visited before moving to the next level...
    -> Dynamically handles varying numbers of children without fixed structure assumptions...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the total number of nodes in the tree...
    -> Space Complexity: O(w), where w is the maximum number of nodes at any level (tree width)...

Demonstration:
    -> Constructs a large N-ary tree with more than 25 nodes across multiple levels...
    -> Applies the `levelOrder` function to gather values level-by-level...
    -> Displays both the structured tree and the level order output in a clean, readable format...

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N_ary_Tree_Level_Order_Traversal {

    static class TreeNode {
        int val;
        ArrayList<TreeNode> children;

        public TreeNode() {
            this.children = new ArrayList<>();
        }

        public TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        public TreeNode(int val, ArrayList<TreeNode> children) {
            this.val = val;
            this.children = (children != null) ? children : new ArrayList<>();
        }
    }

    private static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            int size = q.size();
            ArrayList<Integer> currResult = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                TreeNode currNode = q.poll();
                currResult.add(currNode.val);

                if (currNode.children != null) {
                    for (TreeNode currChild : currNode.children) {
                        q.add(currChild);
                    }
                }

            }

            result.add(currResult);
        }

        return result;
        
    }

    private static void printTree(TreeNode node, String indent) {

        if (node == null) {
            return;
        }

        System.out.println(indent + node.val);

        for (TreeNode child : node.children) {
            printTree(child, indent + "  ");
        }

    }

    public static void main(String[] args) {

        // Level 0 (Root)
        TreeNode root = new TreeNode(1);

        // Level 1
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);

        // Level 2
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.children.add(node5);
        node2.children.add(node6);

        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node3.children.add(node7);
        node3.children.add(node8);
        node3.children.add(node9);

        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        node4.children.add(node10);
        node4.children.add(node11);

        // Level 3
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        node5.children.add(node12);
        node5.children.add(node13);

        TreeNode node14 = new TreeNode(14);
        node6.children.add(node14);

        TreeNode node15 = new TreeNode(15);
        TreeNode node16 = new TreeNode(16);
        node7.children.add(node15);
        node7.children.add(node16);

        TreeNode node17 = new TreeNode(17);
        node9.children.add(node17);

        TreeNode node18 = new TreeNode(18);
        TreeNode node19 = new TreeNode(19);
        node10.children.add(node18);
        node10.children.add(node19);

        TreeNode node20 = new TreeNode(20);
        node11.children.add(node20);

        // Level 4
        TreeNode node21 = new TreeNode(21);
        TreeNode node22 = new TreeNode(22);
        node12.children.add(node21);
        node12.children.add(node22);

        TreeNode node23 = new TreeNode(23);
        node14.children.add(node23);

        TreeNode node24 = new TreeNode(24);
        TreeNode node25 = new TreeNode(25);
        node17.children.add(node24);
        node17.children.add(node25);

        TreeNode node26 = new TreeNode(26);
        node20.children.add(node26);

        System.out.println("N-ary Tree Structure:");
        printTree(root, "");

        System.out.println();
        System.out.println("Level Order Traversal:");

        ArrayList<ArrayList<Integer>> result = levelOrder(root);

        for (ArrayList<Integer> currLevel : result) {
            System.out.println(currLevel);
        }

    }

}

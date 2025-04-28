package Tree;

/*

Description:
 Following program demonstrates the solution to the "Right View of Binary Tree"
   problem using a recursive depth-first traversal approach...

Problem Statement:
 -> Given a binary tree, print the nodes visible from the right side...
 -> Right view consists of the rightmost node at each level of the tree...
 -> Only the first node encountered at each level during a right-to-left traversal is included...

Approach:
 > Recursive Preorder Traversal with Level Tracking and Right-First Processing:
    i. Use a recursive depth-first approach to traverse the tree...
    ii. Track the current level of each node during traversal...
    iii. For each level, add the first node encountered to the result...
    iv. Process right subtree before left subtree to ensure rightmost nodes are seen first...

> Algorithm Steps:
 -> Initialize an empty list to store the right view nodes...
 -> Start recursion from the root node at level 0...
 -> During recursive traversal:
    1. If current level equals the size of result list, this is the first node at this level...
    2. Add the current node value to the result list...
    3. Recursively process the right subtree with level + 1...
    4. Recursively process the left subtree with level + 1...
 -> Return the final list containing right view nodes...

> Key Characteristics:
 -> Uses modified preorder traversal (root, right, left) with level tracking...
 -> Prioritizes right subtree processing to ensure rightmost nodes are encountered first...
 -> Recursively builds the view level by level...
 -> Simple and elegant recursive solution without additional data structures like queues...
 -> Mirror approach to the left view algorithm with reversed traversal order...

> Implementation Details:
 -> Uses ArrayList to store the result...
 -> Base case handles empty tree...
 -> Level checking ensures only first node per level is added...
 -> Function named recursionLeft despite implementing right view (naming inconsistency)...

> Time and Space Complexity:
 -> Time Complexity: O(n) where n is the number of nodes...
 -> Space Complexity: O(h) for recursion stack where h is the height of the tree...

*/

import java.util.ArrayList;

public class Right_View_Of_Binary_Tree {

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

    private static ArrayList<Integer> rightSideView(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        int currLevel = 0;
        recursionLeft(root, currLevel, ans);

        return ans;
    }

    private static void recursionLeft(TreeNode root, int currLevel, ArrayList<Integer> ans) {

        if (root == null) {
            return;
        }

        if (currLevel == ans.size()) {
            ans.add(root.val);
        }

        recursionLeft(root.right, currLevel + 1, ans);
        recursionLeft(root.left, currLevel + 1, ans);
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

        ArrayList<Integer> ans = rightSideView(root);
        System.out.println("Right side view of the Binary Tree is : ");

        for (int val : ans) {
            System.out.print(val + " ");
        }

    }
}

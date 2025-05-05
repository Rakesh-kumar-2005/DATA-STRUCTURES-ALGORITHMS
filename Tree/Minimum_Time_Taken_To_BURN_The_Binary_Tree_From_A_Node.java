package Tree;

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

        int minTime = - 1;

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
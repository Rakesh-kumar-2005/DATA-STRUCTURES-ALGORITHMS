package Tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Print_All_The_Nodes_At_A_Distance_Of_K_In_A_Binary_Tree {

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

    private static void parentTrack(TreeNode root, HashMap<TreeNode, TreeNode> parentMap) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {
            TreeNode currNode = q.poll();

            if (currNode.left != null) {
                parentMap.put(currNode.left, currNode);
                q.add(currNode.left);
            }

            if (currNode.right != null) {
                parentMap.put(currNode.right, currNode);
                q.add(currNode.right);
            }

        }
    }

    private static ArrayList<Integer> printNodesAtDistanceK(TreeNode root, TreeNode targetNode, int distance) {

        if (root == null) {
            return new ArrayList<>();
        }

        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentTrack(root, parentMap);

        HashMap<TreeNode, Boolean> visited = new HashMap<>();
        visited.put(targetNode, true);

        Queue<TreeNode> q = new LinkedList<>();
        q.add(targetNode);
        int currDistance = 0;

        while (! q.isEmpty()) {

            if (currDistance == distance) {
                break;
            }

            int size = q.size();
            currDistance++;

            for (int i = 0; i < size; i++) {

                TreeNode currNode = q.poll();

                if (currNode.left != null && visited.get(currNode.left) == null) {
                    TreeNode leftNode = currNode.left;
                    q.add(leftNode);
                    visited.put(leftNode, true);
                }

                if (currNode.right != null && visited.get(currNode.right) == null) {
                    TreeNode rightNode = currNode.right;
                    q.add(rightNode);
                    visited.put(rightNode, true);
                }

                if (parentMap.get(currNode) != null && visited.get(parentMap.get(currNode)) == null) {
                    TreeNode parentNode = parentMap.get(currNode);
                    q.add(parentNode);
                    visited.put(parentNode, true);
                }

            }

        }

        ArrayList<Integer> ans = new ArrayList<>();

        while (! q.isEmpty()) {
            ans.add(q.poll().val);
        }

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

        TreeNode targetNode = root.left.left.left;
        int distance = 5;

        ArrayList<Integer> ans = printNodesAtDistanceK(root, targetNode, distance);

        System.out.println("Nodes at distance " + distance + " from the node " + targetNode.val + " are : \n" + ans);

    }
}

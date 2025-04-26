package Tree;

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

        for (int key : mp.keySet()) {
            ans.add(mp.get(key));
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

        ArrayList<Integer> ans = bottomView(root);
        System.out.println("Bottom view of the Binary Tree is : ");

        for (int val : ans) {
            System.out.print(val + " ");
        }

    }

}
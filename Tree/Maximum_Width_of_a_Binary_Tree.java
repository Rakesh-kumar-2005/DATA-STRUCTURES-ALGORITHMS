package Tree;

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
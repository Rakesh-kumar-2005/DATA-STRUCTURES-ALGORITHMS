package Tree;

import java.util.ArrayList;

public class Root_to_Node_Path_in_Binary_Tree {

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

    private static boolean helper(TreeNode root, ArrayList<Integer> ans, int target) {

        if (root == null) {
            return false;
        }

        ans.add(root.val);

        if (root.val == target) {
            return true;
        }

        if (helper(root.left, ans, target) || helper(root.right, ans, target)) {
            return true;
        }

        ans.remove(ans.size() - 1);

        return false;
    }

    private static ArrayList<Integer> rootToNodePath(TreeNode root, int target) {

        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<Integer> ans = new ArrayList<>();

        helper(root, ans, target);

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

        int target = 27;
        ArrayList<Integer> ans = rootToNodePath(root, target);
        
        System.out.println("The path from root node to the target node is :");

        for (int i = 0; i < ans.size() - 1; i++) {
            System.out.print(i + " -> ");
        }

        System.out.println(ans.get(ans.size() - 1));

    }
}

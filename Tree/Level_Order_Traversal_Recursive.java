package Tree;


import java.util.ArrayList;

public class Level_Order_Traversal_Recursive {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }


    private static ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode root) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int currLevel = 0;

        helper(root, currLevel, ans);

        return ans;
    }

    private static void helper(TreeNode root, int currLevel, ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return;
        }

        if (ans.size() == currLevel) {
            ans.add(new ArrayList<>());
        }

        ans.get(currLevel).add(root.val);

        helper(root.left, currLevel + 1, ans);
        helper(root.right, currLevel + 1, ans);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> ans = levelOrderTraversal(root);

        System.out.println("Inorder traversal of the Tree is : ");

        for (ArrayList<Integer> curr : ans) {
            System.out.println(curr);
        }
    }

}

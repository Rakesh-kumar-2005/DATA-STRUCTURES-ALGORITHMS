package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level_Order_Traversal_Iterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<ArrayList<Integer>> levelOrderTraversal(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (! q.isEmpty()) {

            int currSize = q.size();
            ArrayList<Integer> currList = new ArrayList<>();

            for (int i = 0; i < currSize; i++) {

                TreeNode curr = q.remove();

                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }

                currList.add(curr.val);

            }

            ans.add(currList);

        }

        return ans;
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

        System.out.println("Level order traversal of the Tree is : ");

        for (ArrayList<Integer> curr : ans) {
            System.out.println(curr);
        }
    }
}

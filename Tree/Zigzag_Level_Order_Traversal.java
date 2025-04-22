package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Zigzag_Level_Order_Traversal {

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

    private static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> q = new LinkedList<>();
        boolean flag = true;
        q.add(root);

        while (! q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                TreeNode curr = q.poll();
                temp.add(curr.val);

                if (curr.left != null) {
                    q.add(curr.left);
                }

                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

            if (! flag) {
                Collections.reverse(temp);
            }
            ans.add(temp);
            flag = ! flag;
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

        ArrayList<ArrayList<Integer>> ans = zigzagLevelOrder(root);

        System.out.println("Zig Zag Level order traversal of the Binary Tree is : ");

        for (ArrayList<Integer> curr : ans) {
            System.out.println(curr);
        }
    }

}
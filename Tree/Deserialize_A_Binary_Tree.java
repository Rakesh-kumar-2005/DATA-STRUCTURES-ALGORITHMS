package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Deserialize_A_Binary_Tree {

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

    private static TreeNode deSerialize(String data) {

        if (data.isEmpty()) {
            return null;
        }

        String[] nodeValues = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        for (int i = 1; i < nodeValues.length; i++) {

            TreeNode currNode = q.poll();

            if (! nodeValues[i].equals("null")) {
                currNode.left = new TreeNode(Integer.parseInt(nodeValues[i]));
                q.add(currNode.left);
            }

            if (! nodeValues[++ i].equals("null")) {
                currNode.right = new TreeNode(Integer.parseInt(nodeValues[i]));
                q.add(currNode.right);
            }
        }

        return root;
    }

    private static void inOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {

        String data = "1,2,3,4,5,6,7,8,9,10,11,null,13,14,15,16,17,18,19,20,21,22,23,26,27,28,29,30,31,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null";

        TreeNode root = deSerialize(data);

        if (root == null) {
            System.out.println("The TreeNode is null...");
        } else {
            inOrderTraversal(root);
        }

    }

}
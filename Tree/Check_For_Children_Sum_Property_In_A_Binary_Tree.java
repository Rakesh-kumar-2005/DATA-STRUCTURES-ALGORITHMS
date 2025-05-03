package Tree;

import java.util.ArrayList;

public class Check_For_Children_Sum_Property_In_A_Binary_Tree {

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

    private static void changeTree(TreeNode root) {

        if (root == null) {
            return;
        }

        int childVal = 0;

        if (root.left != null) {
            childVal += root.left.val;
        }

        if (root.right != null) {
            childVal += root.right.val;
        }

        if (childVal >= root.val) {
            root.val = childVal;
        } else {

            if (root.left != null) {
                root.left.val = root.val;
            }

            if (root.right != null) {
                root.right.val = root.val;
            }

        }

        changeTree(root.left);
        changeTree(root.right);

        childVal = 0;
        if (root.left != null) {
            childVal += root.left.val;
        }

        if (root.right != null) {
            childVal += root.right.val;
        }

        if (root.left != null || root.right != null) {
            root.val = childVal;
        }

    }

    private static ArrayList<Integer> inOrderTraversal(TreeNode root, ArrayList<Integer> ans) {

        if (root == null) {
            return ans;
        }

        inOrderTraversal(root.left, ans);
        ans.add(root.val);
        inOrderTraversal(root.right, ans);

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

        ArrayList<Integer> ans = new ArrayList<>();
        System.out.println("Inorder traversal of the original Binary Tree is : ");

        System.out.println(inOrderTraversal(root, ans));

        ans = new ArrayList<>();
        changeTree(root);

        System.out.println("Inorder traversal of the modified Binary Tree according to the children sum property is : ");
        System.out.println(inOrderTraversal(root, ans));


    }

}
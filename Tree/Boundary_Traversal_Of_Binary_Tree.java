package Tree;

import java.util.ArrayList;
import java.util.Stack;

public class Boundary_Traversal_Of_Binary_Tree {

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

    private static void addLeftBoundary(TreeNode root, ArrayList<Integer> ans) {
        TreeNode currNode = root.left;

        while (currNode != null) {
            if (! isLeaf(currNode)) {
                ans.add(currNode.val);
            }

            if (currNode.left != null) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
    }

    private static void addLeaves(TreeNode root, ArrayList<Integer> ans) {

        if (isLeaf(root)) {
            ans.add(root.val);
        }

        if (root.left != null) {
            addLeaves(root.left, ans);
        }

        if (root.right != null) {
            addLeaves(root.right, ans);
        }

    }

    private static void addRightBoundary(TreeNode root, ArrayList<Integer> ans) {

        TreeNode currNode = root.right;
        Stack<Integer> st = new Stack<>();

        while (currNode != null) {

            if (! isLeaf(currNode)) {
                st.push(currNode.val);
            }

            if (currNode.right != null) {
                currNode = currNode.right;
            } else {
                currNode = currNode.left;
            }
        }

        while (! st.isEmpty()) {
            ans.add(st.pop());
        }

    }

    private static ArrayList<Integer> boundaryTraversal(TreeNode root) {

        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        if (isLeaf(root)) {
            ans.add(root.val);
        }

        addLeftBoundary(root, ans);
        addLeaves(root, ans);
        addRightBoundary(root, ans);

        return ans;

    }


    private static boolean isLeaf(TreeNode root) {

        return (root.left == null) && (root.right == null);

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> result = boundaryTraversal(root);
        System.out.println("Boundary traversal of the Binary Tree is : ");
        System.out.println(result);

    }
}

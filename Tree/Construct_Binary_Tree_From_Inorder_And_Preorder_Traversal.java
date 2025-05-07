package Tree;

import java.util.Arrays;
import java.util.HashMap;

public class Construct_Binary_Tree_From_Inorder_And_Preorder_Traversal {

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

    private static TreeNode buildTree(int[] preOrder, int[] inOrder) {

        if (preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            mp.put(inOrder[i], i);
        }

        return buildTreeWithInOrderAndPreOrder(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, mp);
    }

    private static TreeNode buildTreeWithInOrderAndPreOrder(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> mp) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preStart]);

        int inRoot = mp.get(preOrder[preStart]);
        int numsLeft = inRoot - inStart;

        root.left = buildTreeWithInOrderAndPreOrder(preOrder, preStart + 1, preStart + numsLeft, inOrder, inStart, inRoot - 1, mp);
        root.right = buildTreeWithInOrderAndPreOrder(preOrder, preStart + numsLeft + 1, preEnd, inOrder, inRoot + 1, inEnd, mp);

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

    private static void preOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");

        inOrderTraversal(root.left);
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {

        int[] preOrder = {9, 3, 15, 20, 7};
        int[] inOrder = {3, 9, 20, 15, 7};

        TreeNode root = buildTree(preOrder, inOrder);

        System.out.println("To verify our Binary Tree : \n InOrder traversal array is \n " + Arrays.toString(inOrder) + "\n PreOrder traversal array is \n " + Arrays.toString(preOrder));

        System.out.println("And the Binary Tree from our solution has the following structure : ");

        System.out.println("InOrder Traversal : ");
        inOrderTraversal(root);

        System.out.println();

        System.out.println("InOrder Traversal : ");
        preOrderTraversal(root);

    }
}
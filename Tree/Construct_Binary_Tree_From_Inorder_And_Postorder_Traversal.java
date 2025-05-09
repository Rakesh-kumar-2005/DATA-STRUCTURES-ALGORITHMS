package Tree;

import java.util.Arrays;
import java.util.HashMap;

public class Construct_Binary_Tree_From_Inorder_And_Postorder_Traversal {

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

    private static TreeNode buildTree(int[] postOrder, int[] inOrder) {

        if (postOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        int poStart = 0;
        int poEnd = postOrder.length - 1;
        int inStart = 0;
        int inEnd = inOrder.length - 1;

        return buildTreeWithInOrderAndPostOrder(postOrder, poStart, poEnd, inOrder, inStart, inEnd, map);
    }

    private static TreeNode buildTreeWithInOrderAndPostOrder(int[] postOrder, int poStart, int poEnd, int[] inOrder, int inStart, int inEnd, HashMap<Integer, Integer> map) {

        if (poStart > poEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = postOrder[poEnd];
        TreeNode root = new TreeNode(rootVal);

        int inRoot = map.get(rootVal);
        int numsLeft = inRoot - inStart;

        root.left = buildTreeWithInOrderAndPostOrder(postOrder, poStart, poStart + numsLeft - 1, inOrder, inStart, inRoot - 1, map);
        root.right = buildTreeWithInOrderAndPostOrder(postOrder, poStart + numsLeft, poEnd - 1, inOrder, inRoot + 1, inEnd, map);

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

    private static void postOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + " ");

    }

    public static void main(String[] args) {

        int[] inOrder = {4, 2, 5, 1, 6, 3, 7};
        int[] postOrder = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root = buildTree(postOrder, inOrder);

        System.out.println("To verify our Binary Tree : \n InOrder traversal array is \n " + Arrays.toString(inOrder) + "\n PreOrder traversal array is \n " + Arrays.toString(postOrder));

        System.out.println("And the Binary Tree from our solution has the following structure : ");

        System.out.println("InOrder Traversal : ");
        inOrderTraversal(root);

        System.out.println();

        System.out.println("PostOrder Traversal : ");
        postOrderTraversal(root);

    }
}

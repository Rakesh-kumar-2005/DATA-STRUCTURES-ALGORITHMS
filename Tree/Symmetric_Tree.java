package Tree;

public class Symmetric_Tree {

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

    private static boolean isSymmetric(TreeNode root) {

        return root == null || isMirror(root.left, root.right);

    }

    private static boolean isMirror(TreeNode leftTree, TreeNode rightTree) {

        if (leftTree == null || rightTree == null) {
            return leftTree.val == rightTree.val;
        }

        if (leftTree.val != rightTree.val) {
            return false;
        }

        return isMirror(leftTree.left, rightTree.right) && isMirror(leftTree.right, rightTree.left);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Is this Binary Tree a Symmetric Tree : " + isSymmetric(root));

    }

}
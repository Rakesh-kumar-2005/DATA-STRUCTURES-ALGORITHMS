package Tree;

public class Balanced_Binary_Tree {

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

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        if (leftHeight == - 1) {
            return - 1;
        }

        if (rightHeight == - 1) {
            return - 1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return - 1;
        }

        int maxHeight = Math.max(rightHeight, leftHeight);

        return maxHeight + 1;

    }

    public static boolean isBalanced(TreeNode root) {

        return maxDepth(root) != - 1;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Is it a Balanced tree : " + isBalanced(root));
    }
}
package Tree;

public class Diameter_of_Binary_Tree {

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

    private static int maxLength(TreeNode root, int[] arr) {
        if (root == null) {
            return 0;
        }

        int leftHeight = maxLength(root.left, arr);
        int rightHeight = maxLength(root.right, arr);

        int totalLength = rightHeight + leftHeight;
        arr[0] = Math.max(arr[0], totalLength);

        int maxHeight = Math.max(rightHeight, leftHeight);

        return 1 + maxHeight;
    }

    private static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] arr = new int[1];
        maxLength(root, arr);

        return arr[0];
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int diameter = diameterOfBinaryTree(root);

        System.out.println("Diameter of the Tree is : " + diameter);
    }
}
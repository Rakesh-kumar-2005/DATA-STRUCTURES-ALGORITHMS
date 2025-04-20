package Tree;

public class Maximum_Path_Sum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private static int maxPathSum(TreeNode root, int[] arr) {
        if (root == null) {
            return 0;
        }

        int leftSum = Math.max(0, maxPathSum(root.left, arr));
        int rightSum = Math.max(0, maxPathSum(root.right, arr));

        int totalPathSum = root.val + leftSum + rightSum;
        arr[0] = Math.max(arr[0], totalPathSum);

        return root.val + Math.max(leftSum, rightSum);

    }

    private static int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] arr = new int[1];
        arr[0] = Integer.MIN_VALUE;

        maxPathSum(root, arr);

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

        System.out.println("Maximum path sum is : " + maxPath(root));
    }
}

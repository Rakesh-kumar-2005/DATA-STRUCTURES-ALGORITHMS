package Tree;

public class Count_Complete_Binary_Tree {

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

    private static int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftCount = getLeftNodeCount(root);
        int rightCount = getRightNodeCount(root);

        if (leftCount == rightCount) {
            return (1 << leftCount) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);

    }


    private static int getLeftNodeCount(TreeNode root) {

        int count = 0;

        while (root != null) {
            count++;
            root = root.left;
        }

        return count;
    }

    private static int getRightNodeCount(TreeNode root) {

        int count = 0;

        while (root != null) {
            count++;
            root = root.right;
        }

        return count;
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

        int count = countNodes(root);
        System.out.println("Total number of nodes in the Binary Tree is : " + count);

    }

}
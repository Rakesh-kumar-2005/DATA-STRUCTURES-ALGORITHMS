package Tree;

public class Same_Tree {

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

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        boolean leftSide = isSameTree(p.left, q.left);
        boolean rightSide = isSameTree(p.right, q.right);

        boolean values = (p.val == q.val);

        return values && leftSide && rightSide;
    }

    public static void main(String[] args) {

        TreeNode tree1 = new TreeNode(1);

        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);

        tree1.left.left = new TreeNode(4);
        tree1.left.right = new TreeNode(5);

        tree1.right.left = new TreeNode(6);
        tree1.right.right = new TreeNode(7);

        TreeNode tree2 = new TreeNode(1);

        tree2.left = new TreeNode(2);
        tree2.right = new TreeNode(3);

        tree2.left.left = new TreeNode(8);
        tree2.left.right = new TreeNode(5);

        tree2.right.left = new TreeNode(6);
        tree2.right.right = new TreeNode(7);

        if (isSameTree(tree1, tree2)) {
            System.out.println("Both trees are exactly the same...");
        } else {
            System.out.println("Both trees are different from each other...");
        }
    }

}
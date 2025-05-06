package Tree;

/*

Description:
    -> Following program demonstrates the solution to the "Count Nodes in a Complete Binary Tree" problem...
    -> It efficiently calculates the total number of nodes in a complete binary tree...
    -> Leverages the special properties of complete binary trees to achieve better than O(n) time complexity...

Problem Statement:
    -> Given a complete binary tree, count the total number of nodes in it...
    -> A complete binary tree is one where all levels are fully filled except possibly the last level...
    -> In the last level, all nodes are as far left as possible...

Approach:
    > Exploiting Complete Binary Tree Properties:
        i. Use the property that a complete binary tree is perfectly balanced except possibly at the last level...
        ii. If left height equals right height, the tree is a perfect binary tree...
        iii. For perfect binary trees, the node count formula is (2^h - 1) where h is the height...

    > Algorithm Steps:
        -> Check if tree is empty:
            1. Return 0 if root is null...
            2. Continue with algorithm otherwise...

        -> Check if tree is perfect:
            1. Calculate height of leftmost path...
            2. Calculate height of rightmost path...
            3. If both heights are equal, tree is perfect, return (2^h - 1)...

        -> Handle non-perfect complete trees:
            1. If heights differ, recursively count nodes in left and right subtrees...
            2. Add 1 for the root node...
            3. Return the sum as total node count...

    > Key Characteristics:
        -> Takes advantage of complete binary tree properties for optimization...
        -> Avoids counting every node individually in perfect subtrees...
        -> Recursively processes only the minimal required subtrees...
        -> Uses bit shift operation (1 << h) for efficient power-of-2 calculation...
        -> Works efficiently for large complete binary trees...

    > Implementation Details:
        -> Uses three helper methods: main countNodes and two height calculators...
        -> getLeftNodeCount finds the height of leftmost path...
        -> getRightNodeCount finds the height of rightmost path...
        -> Creates a complete binary tree for demonstration with 31 nodes over 4 levels...
        -> Returns an integer representing the total number of nodes...

    > Time and Space Complexity:
        -> Time Complexity: O(log²n) where n is the number of nodes...
        -> Each recursive call works on a subtree with height reduced by at least 1...
        -> Height computation takes O(log n) time...
        -> Space Complexity: O(log n) for the recursion stack...
   
*/

public class Count_Total_Nodes_In_A_Complete_Binary_Tree {

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

package Tree;

/*

Description:
 Following program demonstrates the solution to the "Lowest Common Ancestor (LCA) in Binary Tree"
   problem using recursive post-order traversal...

Problem Statement:
 -> Given a binary tree and two nodes p and q...
 -> Find the lowest common ancestor of these two nodes...
 -> The lowest common ancestor is the deepest node that has both p and q as descendants...
 -> A node can be a descendant of itself (i.e., a node is its own ancestor)...

Approach:
 > Recursive Post-order Traversal:
    i. Use bottom-up recursive approach to find nodes p and q...
    ii. When either node is found or reached null, return the current node...
    iii. Use post-order traversal to process left and right subtrees before the root...
    iv. Determine LCA based on left and right subtree results...

> Algorithm Steps:
 -> Base cases:
    1. If root is null, return null (nodes not found)...
    2. If root is either p or q, return root (found one of the target nodes)...
 -> Recursive case:
    1. Recursively search for p and q in left subtree...
    2. Recursively search for p and q in right subtree...
    3. If both left and right return non-null values, current node is the LCA...
    4. If only left returns non-null, LCA is in left subtree...
    5. If only right returns non-null, LCA is in right subtree...

> Key Characteristics:
 -> Uses efficient post-order traversal...
 -> Works for all cases: nodes in same subtree, different subtrees, or one being ancestor of other...
 -> Returns null if either node is not present in the tree...
 -> Single traversal solution with O(n) time complexity...
 -> Elegant recursive implementation that handles all edge cases...

> Implementation Details:
 -> No additional data structures needed...
 -> Uses node references directly for comparison...
 -> Example tests LCA of nodes at extreme corners of the tree (16 and 31)...

> Time and Space Complexity:
 -> Time Complexity: O(n) where n is the number of nodes...
 -> Space Complexity: O(h) for recursion stack where h is the height of the tree...

*/

public class Lowest_Common_Ancestor_Of_Two_Given_Nodes {

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

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode == null) {
            return rightNode;
        } 
        else if (rightNode == null) {
            return leftNode;
        }

        return root;

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

        TreeNode ans = lowestCommonAncestor(root, root.left.left.left.left, root.right.right.right.right);

        System.out.println("Lowest Common Ancestor of the Binary Tree is : ");
        System.out.println(ans.val);

    }

}

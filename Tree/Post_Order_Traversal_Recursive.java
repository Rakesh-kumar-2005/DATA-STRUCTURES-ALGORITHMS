package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Postorder Tree Traversal" algorithm
        using a recursive approach...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Postorder traversal visits nodes in the order: left subtree, right subtree, root...
        -> The task is to perform a postorder traversal and return all visited nodes in that order...
        -> Postorder traversal is a depth-first search strategy that processes children before their parent,
           making it useful for deletion operations and mathematical expression evaluation...

    Approach:
        > Recursive Postorder Traversal:
            i. Use depth-first traversal methodology with recursion...
            ii. Process nodes in left-right-root order...
            iii. Follow a specific visitation order:
                a. Recursively traverse the left subtree first...
                b. Recursively traverse the right subtree second...
                c. Visit the root node last...
            iv. Collect node values in an ArrayList during traversal...

    Algorithm Steps:
        -> Recursive Postorder Implementation:
            1. Check if current node is null (base case)...
            2. Recursively call traversal on left child...
            3. Recursively call traversal on right child...
            4. Add current node's value to the result list...
            5. Return the populated list of node values...
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Initialize an empty ArrayList to store the traversal result...
            3. Call the postorder traversal function with helper method...
            4. Print the resulting list of node values...

    Key Characteristics:
        -> Efficiently traverses the entire tree in O(N) time where N is the number of nodes...
        -> Uses recursion to naturally implement the depth-first search pattern...
        -> Ensures parent nodes are processed only after both their children have been processed...
        -> Results in a bottom-up processing approach of the tree...

        > Implementation Details:
            -> Uses a helper function (traverse) that accepts and modifies a shared ArrayList...
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(H) for the recursion stack where H is the height of the tree,
           plus O(N) for storing the result...

*/

import java.util.ArrayList;

public class Post_Order_Traversal_Recursive {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<Integer> traverse(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            return ans;
        }
        traverse(root.left, ans);
        traverse(root.right, ans);
        ans.add(root.val);
        return ans;
    }

    public static ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        return traverse(root, ans);
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> ans = postorderTraversal(root);

        System.out.println("Postorder traversal of the Tree is : ");
        System.out.println(ans);
    }

}

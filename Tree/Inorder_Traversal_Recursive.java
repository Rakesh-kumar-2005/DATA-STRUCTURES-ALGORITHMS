package Tree;

/*

    Description:
        Following program demonstrates the implementation of the "Inorder Tree Traversal" algorithm
        using a recursive approach...

    Problem Statement:
        -> Given a binary tree data structure...
        -> Inorder traversal visits nodes in the order: left subtree, root, right subtree...
        -> The task is to perform an inorder traversal and return all visited nodes in that order...
        -> Inorder traversal is a depth-first search strategy that produces a sorted sequence
           when applied to a binary search tree...

    Approach:
        > Recursive Inorder Traversal:
            i. Use depth-first traversal methodology with recursion...
            ii. Process nodes in left-root-right order...
            iii. Follow a specific visitation order:
                a. Recursively traverse the left subtree first...
                b. Visit the root node second...
                c. Recursively traverse the right subtree last...
            iv. Collect node values in an ArrayList during traversal...

    Algorithm Steps:
        -> Recursive Inorder Implementation:
            1. Check if current node is null (base case)...
            2. Recursively call traversal on left child...
            3. Add current node's value to the result list...
            4. Recursively call traversal on right child...
            5. Return the populated list of node values...
        -> Main Method Flow:
            1. Create a binary tree structure for testing...
            2. Initialize an empty ArrayList to store the traversal result...
            3. Call the inorder traversal function...
            4. Print the resulting list of node values...

    Key Characteristics:
        -> Efficiently traverses the entire tree in O(N) time where N is the number of nodes...
        -> Uses recursion to naturally implement the depth-first search pattern...
        -> Preserves the sorting property when applied to binary search trees...
        -> Results in a left-to-right processing of nodes...

        > Implementation Details:
            -> Uses a helper function that accepts and modifies a shared ArrayList...
            -> Handles the base case of empty trees gracefully...
            -> Creates a test tree structure with 7 nodes for demonstration...

    Time and Space Complexity:
        -> Time Complexity: O(N) where N is the number of nodes in the tree...
        -> Space Complexity: O(H) for the recursion stack where H is the height of the tree,
           plus O(N) for storing the result...

*/

import java.util.ArrayList;

public class Inorder_Traversal_Recursive {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    private static ArrayList<Integer> inOrderTraversal(TreeNode root, ArrayList<Integer> ans) {

        if (root == null) {
            return ans;
        }

        inOrderTraversal(root.left, ans);
        ans.add(root.val);
        inOrderTraversal(root.right, ans);
        
        return ans;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);


        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> ans = inOrderTraversal(root, res);

        System.out.println("Inorder traversal of the Tree is : ");
        System.out.println(ans);
    }
}

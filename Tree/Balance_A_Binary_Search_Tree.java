package Tree;

import java.util.ArrayList;

public class Balance_A_Binary_Search_Tree {

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

    private static void inorder(TreeNode head, ArrayList<Integer> list) {
        if (head == null) {
            return;
        }

        inorder(head.left, list);
        list.add(head.val);
        inorder(head.right, list);
    }

    private static TreeNode buildTree(ArrayList<Integer> list, int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));

        node.left = buildTree(list, left, mid - 1);
        node.right = buildTree(list, mid + 1, right);

        return node;
    }

    private static TreeNode balanceBST(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);

        return buildTree(list, 0, list.size() - 1);

    }


    private static String getInorder(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.toString();
    }

    private static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != - 1;
    }

    private static int checkBalance(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = checkBalance(root.left);
        if (leftHeight == - 1) return - 1;

        int rightHeight = checkBalance(root.right);
        if (rightHeight == - 1) return - 1;

        if (Math.abs(leftHeight - rightHeight) > 1) return - 1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           BALANCE A BINARY SEARCH TREE                       ║");
        System.out.println("║  Convert unbalanced BST to height-balanced BST               ║");
        System.out.println("║  Height-balanced: left & right subtree heights differ ≤ 1    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Right-Skewed BST ===");
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);

        System.out.println("Original BST (right-skewed):");
        System.out.println("      1");
        System.out.println("       \\");
        System.out.println("        2");
        System.out.println("         \\");
        System.out.println("          3");
        System.out.println("           \\");
        System.out.println("            4");
        System.out.println("\nInorder traversal: " + getInorder(root1));
        System.out.println("Height: " + getHeight(root1) + " (unbalanced)\n");

        TreeNode balanced1 = balanceBST(root1);

        System.out.println("Balanced BST:");
        System.out.println("      2");
        System.out.println("     / \\");
        System.out.println("    1   3");
        System.out.println("         \\");
        System.out.println("          4");
        System.out.println("\nInorder traversal: " + getInorder(balanced1));
        System.out.println("Height: " + getHeight(balanced1) + " (balanced ✓)");
        System.out.println("Status: " + (isBalanced(balanced1) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Left-Skewed BST ===");
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(1);

        System.out.println("Original BST (left-skewed):");
        System.out.println("          4");
        System.out.println("         /");
        System.out.println("        3");
        System.out.println("       /");
        System.out.println("      2");
        System.out.println("     /");
        System.out.println("    1");
        System.out.println("\nInorder traversal: " + getInorder(root2));
        System.out.println("Height: " + getHeight(root2) + " (unbalanced)\n");

        TreeNode balanced2 = balanceBST(root2);

        System.out.println("Balanced BST:");
        System.out.println("      2");
        System.out.println("     / \\");
        System.out.println("    1   3");
        System.out.println("         \\");
        System.out.println("          4");
        System.out.println("\nInorder traversal: " + getInorder(balanced2));
        System.out.println("Height: " + getHeight(balanced2) + " (balanced ✓)");
        System.out.println("Status: " + (isBalanced(balanced2) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Partially Unbalanced BST ===");
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(5);
        root3.left.left = new TreeNode(2);
        root3.left.left.left = new TreeNode(1);
        root3.right = new TreeNode(15);
        root3.right.right = new TreeNode(20);

        System.out.println("Original BST (unbalanced left side):");
        System.out.println("          10");
        System.out.println("         /  \\");
        System.out.println("        5    15");
        System.out.println("       /       \\");
        System.out.println("      2         20");
        System.out.println("     /");
        System.out.println("    1");
        System.out.println("\nInorder: " + getInorder(root3));
        System.out.println("Height: " + getHeight(root3));
        System.out.println("Balanced: " + isBalanced(root3) + "\n");

        TreeNode balanced3 = balanceBST(root3);

        System.out.println("Balanced BST:");
        System.out.println("        5");
        System.out.println("       / \\");
        System.out.println("      2   15");
        System.out.println("     /   /  \\");
        System.out.println("    1  10    20");
        System.out.println("\nInorder: " + getInorder(balanced3));
        System.out.println("Height: " + getHeight(balanced3));
        System.out.println("Status: " + (isBalanced(balanced3) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Already Balanced BST ===");
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(7);
        root4.left.left = new TreeNode(2);
        root4.left.right = new TreeNode(4);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(8);

        System.out.println("Original BST (already balanced):");
        System.out.println("        5");
        System.out.println("       / \\");
        System.out.println("      3   7");
        System.out.println("     / \\ / \\");
        System.out.println("    2  4 6  8");
        System.out.println("\nInorder: " + getInorder(root4));
        System.out.println("Height: " + getHeight(root4));
        System.out.println("Already balanced: " + isBalanced(root4) + "\n");

        TreeNode balanced4 = balanceBST(root4);

        System.out.println("After balancing (should remain similar):");
        System.out.println("Inorder: " + getInorder(balanced4));
        System.out.println("Height: " + getHeight(balanced4));
        System.out.println("Status: " + (isBalanced(balanced4) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Node ===");
        TreeNode root5 = new TreeNode(42);

        System.out.println("Original BST (single node):");
        System.out.println("    42");
        System.out.println("\nInorder: " + getInorder(root5));
        System.out.println("Height: " + getHeight(root5) + "\n");

        TreeNode balanced5 = balanceBST(root5);

        System.out.println("After balancing:");
        System.out.println("Inorder: " + getInorder(balanced5));
        System.out.println("Status: " + (isBalanced(balanced5) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Zigzag Pattern ===");
        TreeNode root6 = new TreeNode(1);
        root6.right = new TreeNode(2);
        root6.right.left = new TreeNode(3);
        root6.right.left.right = new TreeNode(4);

        System.out.println("Original BST (zigzag pattern):");
        System.out.println("    1");
        System.out.println("     \\");
        System.out.println("      2");
        System.out.println("     /");
        System.out.println("    3");
        System.out.println("     \\");
        System.out.println("      4");
        System.out.println("\nInorder: " + getInorder(root6));
        System.out.println("Height: " + getHeight(root6) + "\n");

        TreeNode balanced6 = balanceBST(root6);

        System.out.println("Balanced BST:");
        System.out.println("      2");
        System.out.println("     / \\");
        System.out.println("    1   3");
        System.out.println("         \\");
        System.out.println("          4");
        System.out.println("\nInorder: " + getInorder(balanced6));
        System.out.println("Height: " + getHeight(balanced6));
        System.out.println("Status: " + (isBalanced(balanced6) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Complete Unbalanced Chain ===");
        TreeNode root7 = new TreeNode(1);
        root7.right = new TreeNode(2);
        root7.right.right = new TreeNode(3);
        root7.right.right.right = new TreeNode(4);
        root7.right.right.right.right = new TreeNode(5);
        root7.right.right.right.right.right = new TreeNode(6);
        root7.right.right.right.right.right.right = new TreeNode(7);

        System.out.println("Original BST (extreme right skew):");
        System.out.println("1 → 2 → 3 → 4 → 5 → 6 → 7");
        System.out.println("\nInorder: " + getInorder(root7));
        System.out.println("Height: " + getHeight(root7) + " (very unbalanced!)\n");

        TreeNode balanced7 = balanceBST(root7);

        System.out.println("Balanced BST:");
        System.out.println("        4");
        System.out.println("       / \\");
        System.out.println("      2   6");
        System.out.println("     / \\ / \\");
        System.out.println("    1  3 5  7");
        System.out.println("\nInorder: " + getInorder(balanced7));
        System.out.println("Height: " + getHeight(balanced7) + " (now balanced ✓)");
        System.out.println("Status: " + (isBalanced(balanced7) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Complex Unbalanced BST ===");
        TreeNode root8 = new TreeNode(50);
        root8.left = new TreeNode(25);
        root8.left.left = new TreeNode(12);
        root8.left.left.left = new TreeNode(6);
        root8.left.left.left.left = new TreeNode(3);
        root8.right = new TreeNode(75);
        root8.right.right = new TreeNode(90);

        System.out.println("Original BST (complex structure):");
        System.out.println("           50");
        System.out.println("          /  \\");
        System.out.println("        25    75");
        System.out.println("       /        \\");
        System.out.println("      12         90");
        System.out.println("     /");
        System.out.println("    6");
        System.out.println("   /");
        System.out.println("  3");
        System.out.println("\nInorder: " + getInorder(root8));
        System.out.println("Height: " + getHeight(root8));
        System.out.println("Balanced: " + isBalanced(root8) + "\n");

        TreeNode balanced8 = balanceBST(root8);

        System.out.println("Balanced BST:");
        System.out.println("       25");
        System.out.println("      /  \\");
        System.out.println("     6    75");
        System.out.println("    / \\   / \\");
        System.out.println("   3  12 50  90");
        System.out.println("\nInorder: " + getInorder(balanced8));
        System.out.println("Height: " + getHeight(balanced8));
        System.out.println("Status: " + (isBalanced(balanced8) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Statement:                                          ║");
        System.out.println("║    • Given: Unbalanced Binary Search Tree                    ║");
        System.out.println("║    • Goal: Convert to height-balanced BST                    ║");
        System.out.println("║    • Constraint: Maintain BST property (inorder sorted)      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Height-Balanced Definition:                                 ║");
        System.out.println("║    For every node, the heights of left and right subtrees    ║");
        System.out.println("║    differ by at most 1                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Three-Step Algorithm:                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 1: Inorder Traversal                                   ║");
        System.out.println("║    • Visit left subtree → current node → right subtree       ║");
        System.out.println("║    • Produces SORTED array from BST                          ║");
        System.out.println("║    • Time: O(n), Space: O(n) for ArrayList                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Example: BST [1 → 2 → 3 → 4]                              ║");
        System.out.println("║      Inorder result: [1, 2, 3, 4]                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 2: Find Middle Element                                 ║");
        System.out.println("║    • mid = left + (right - left) / 2                         ║");
        System.out.println("║    • Middle becomes root of balanced subtree                 ║");
        System.out.println("║    • Ensures equal distribution of nodes                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Why (right - left) / 2 instead of (left + right) / 2?     ║");
        System.out.println("║      → Prevents integer overflow for large indices           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 3: Recursive Tree Building                             ║");
        System.out.println("║    • Create node with middle element                         ║");
        System.out.println("║    • Recursively build left subtree: [left, mid-1]           ║");
        System.out.println("║    • Recursively build right subtree: [mid+1, right]         ║");
        System.out.println("║    • Base case: left > right → return null                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Detailed Example: [1, 2, 3, 4]                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Initial call: buildTree(0, 3)                             ║");
        System.out.println("║      mid = 0 + (3-0)/2 = 1                                   ║");
        System.out.println("║      Create node(2) as root                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Left subtree: buildTree(0, 0)                             ║");
        System.out.println("║      mid = 0                                                 ║");
        System.out.println("║      Create node(1)                                          ║");
        System.out.println("║      Left: buildTree(0, -1) → null                           ║");
        System.out.println("║      Right: buildTree(1, 0) → null                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Right subtree: buildTree(2, 3)                            ║");
        System.out.println("║      mid = 2 + (3-2)/2 = 2                                   ║");
        System.out.println("║      Create node(3)                                          ║");
        System.out.println("║      Left: buildTree(2, 1) → null                            ║");
        System.out.println("║      Right: buildTree(3, 3) → node(4)                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Final tree:                                               ║");
        System.out.println("║          2                                                   ║");
        System.out.println("║         / \\                                                 ║");
        System.out.println("║        1   3                                                 ║");
        System.out.println("║             \\                                               ║");
        System.out.println("║              4                                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Works:                                             ║");
        System.out.println("║    • Sorted array → picking middle ensures balance           ║");
        System.out.println("║    • Each subtree gets roughly half the elements             ║");
        System.out.println("║    • Recursion naturally maintains BST property              ║");
        System.out.println("║    • Height difference between subtrees ≤ 1                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Properties Maintained:                                  ║");
        System.out.println("║    ✓ BST property: left < root < right                       ║");
        System.out.println("║    ✓ Balance property: |height(left) - height(right)| ≤ 1    ║");
        System.out.println("║    ✓ Same inorder traversal as original                      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n) - Visit each node once               ║");
        System.out.println("║  Space Complexity: O(n) - ArrayList + recursion stack        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Applications: Database indexing, AVL tree conversion,       ║");
        System.out.println("║                optimizing search performance                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
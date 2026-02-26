package Tree;

/*

Description:
  Following program computes the sum of all root-to-leaf paths in a binary tree where each node contains a binary digit (0 or 1), interpreting each path as a binary number...

Problem Statement:
  -> Given a binary tree with node values 0 or 1...
  -> Each root-to-leaf path represents a binary number...
  -> Construct the binary number by traversing from root to leaf...
  -> Convert each binary value to decimal...
  -> Return the sum of all such values...

Core Idea:
  -> Traverse the tree using Depth-First Search (DFS)...
  -> Build the binary number while traversing...
  -> When a leaf node is reached, the accumulated value represents one complete binary number...
  -> Sum all leaf path values...

Binary Construction Logic:
  -> When moving to the next node:
       newValue = currentValue × 2 + nodeValue...
  -> Multiplying by 2 shifts bits left (binary left shift)...
  -> Adding node value appends the new bit...

Example of Binary Construction:
  -> Building binary 101:
       Start = 0...
       Add 1 → 0×2 + 1 = 1   → 1₂...
       Add 0 → 1×2 + 0 = 2   → 10₂...
       Add 1 → 2×2 + 1 = 5   → 101₂...

Approach:
  > Depth-First Search Traversal:

     i. If node is null → return 0...
     ii. Update accumulated value:
            x = x * 2 + node.val...
     iii. If node is a leaf → return accumulated value...
     iv. Recursively compute:
            leftSum + rightSum...

Leaf Detection Trick:
  -> if (node.left == node.right) identifies a leaf...
  -> Both references null → true...
  -> Otherwise → not a leaf...

Algorithm Steps:
  -> Start DFS from root with value 0...
  -> Update binary value at each node...
  -> When leaf reached → return path value...
  -> Sum values from left and right subtrees...
  -> Return total sum...

Example:
  -> Tree:

          1
         / \
        0   1
       / \ / \
      0  1 0  1

  -> Paths:
       100₂ = 4
       101₂ = 5
       110₂ = 6
       111₂ = 7

  -> Sum = 22...

Execution Flow:
  -> DFS(root,0)
       → build binary values progressively...
       → return value at leaves...
       → sum results from subtrees...

Binary to Decimal Examples:
  -> 100₂ = 4₁₀...
  -> 101₂ = 5₁₀...
  -> 110₂ = 6₁₀...
  -> 111₂ = 7₁₀...
  -> 1010₂ = 10₁₀...

Why DFS Works Well:
  -> Naturally explores root-to-leaf paths...
  -> Maintains path-specific accumulated value...
  -> Recursion aggregates leaf results automatically...

Edge Cases:
  -> Single node tree → return node value...
  -> All zeros → total sum = 0...
  -> Linear tree → single binary number...
  -> Unbalanced tree handled correctly...
  -> Different path lengths supported...

Implementation Notes:
  -> Uses integer arithmetic instead of string conversion...
  -> Efficient bit construction via multiplication...
  -> Avoids storing path strings → memory efficient...
  -> Leaf detection avoids unnecessary recursion...

Time and Space Complexity:
  -> Time Complexity: O(n), each node visited once...
  -> Space Complexity: O(h), recursion stack depth...
       where h = tree height...
       best case O(log n), worst case O(n)...

Applications:
  -> Binary tree path encoding...
  -> Digital signal interpretation in tree structures...
  -> Recursive accumulation problems...
  -> Interview problems involving DFS and bit construction...

*/

public class Sum_Of_Root_To_Leaf_Binary_Numbers {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    private static int sumRootToLeaf(TreeNode root) {
        return DFS(root, 0);
    }

    private static int DFS(TreeNode rt, int x) {

        if (rt == null) {
            return 0;
        }
        x = x * 2 + rt.val;

        if (rt.left == rt.right) {
            return x;
        }

        return DFS(rt.left, x) + DFS(rt.right, x);
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         SUM OF ROOT TO LEAF BINARY NUMBERS                   ║");
        System.out.println("║  Calculate sum of all root-to-leaf paths as binary numbers   ║");
        System.out.println("║  Each path represents a binary number from root to leaf      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Tree ===");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(0);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(1);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(1);

        System.out.println("Tree Structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      0   1");
        System.out.println("     / \\ / \\");
        System.out.println("    0  1 0  1");
        System.out.println("\nRoot-to-Leaf Paths:");
        System.out.println("  Path 1: 1 → 0 → 0 = 100₂ = 4₁₀");
        System.out.println("  Path 2: 1 → 0 → 1 = 101₂ = 5₁₀");
        System.out.println("  Path 3: 1 → 1 → 0 = 110₂ = 6₁₀");
        System.out.println("  Path 4: 1 → 1 → 1 = 111₂ = 7₁₀");
        System.out.println("\nSum: 4 + 5 + 6 + 7 = 22\n");

        int result1 = sumRootToLeaf(root1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 22");
        System.out.println("  Status: " + (result1 == 22 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Node ===");
        TreeNode root2 = new TreeNode(1);

        System.out.println("Tree Structure:");
        System.out.println("    1");
        System.out.println("\nRoot-to-Leaf Path:");
        System.out.println("  Path 1: 1 = 1₂ = 1₁₀");
        System.out.println("\nSum: 1\n");

        int result2 = sumRootToLeaf(root2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Linear Path (Left) ===");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(0);
        root3.left.left = new TreeNode(1);

        System.out.println("Tree Structure:");
        System.out.println("      1");
        System.out.println("     /");
        System.out.println("    0");
        System.out.println("   /");
        System.out.println("  1");
        System.out.println("\nRoot-to-Leaf Path:");
        System.out.println("  Path 1: 1 → 0 → 1 = 101₂ = 5₁₀");
        System.out.println("\nBinary conversion:");
        System.out.println("  1×2² + 0×2¹ + 1×2⁰ = 4 + 0 + 1 = 5\n");

        int result3 = sumRootToLeaf(root3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result3 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Linear Path (Right) ===");
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(1);
        root4.right.right = new TreeNode(0);

        System.out.println("Tree Structure:");
        System.out.println("  1");
        System.out.println("   \\");
        System.out.println("    1");
        System.out.println("     \\");
        System.out.println("      0");
        System.out.println("\nRoot-to-Leaf Path:");
        System.out.println("  Path 1: 1 → 1 → 0 = 110₂ = 6₁₀");
        System.out.println("\nBinary conversion:");
        System.out.println("  1×2² + 1×2¹ + 0×2⁰ = 4 + 2 + 0 = 6\n");

        int result4 = sumRootToLeaf(root4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Zeros ===");
        TreeNode root5 = new TreeNode(0);
        root5.left = new TreeNode(0);
        root5.right = new TreeNode(0);

        System.out.println("Tree Structure:");
        System.out.println("      0");
        System.out.println("     / \\");
        System.out.println("    0   0");
        System.out.println("\nRoot-to-Leaf Paths:");
        System.out.println("  Path 1: 0 → 0 = 00₂ = 0₁₀");
        System.out.println("  Path 2: 0 → 0 = 00₂ = 0₁₀");
        System.out.println("\nSum: 0 + 0 = 0\n");

        int result5 = sumRootToLeaf(root5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: All Ones ===");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(1);
        root6.right = new TreeNode(1);
        root6.left.left = new TreeNode(1);
        root6.left.right = new TreeNode(1);

        System.out.println("Tree Structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      1   1");
        System.out.println("     / \\");
        System.out.println("    1   1");
        System.out.println("\nRoot-to-Leaf Paths:");
        System.out.println("  Path 1: 1 → 1 → 1 = 111₂ = 7₁₀");
        System.out.println("  Path 2: 1 → 1 → 1 = 111₂ = 7₁₀");
        System.out.println("  Path 3: 1 → 1     = 11₂  = 3₁₀");
        System.out.println("\nSum: 7 + 7 + 3 = 17\n");

        int result6 = sumRootToLeaf(root6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 17");
        System.out.println("  Status: " + (result6 == 17 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Asymmetric Tree ===");
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(0);
        root7.right = new TreeNode(1);
        root7.right.left = new TreeNode(0);

        System.out.println("Tree Structure:");
        System.out.println("      1");
        System.out.println("     / \\");
        System.out.println("    0   1");
        System.out.println("       /");
        System.out.println("      0");
        System.out.println("\nRoot-to-Leaf Paths:");
        System.out.println("  Path 1: 1 → 0     = 10₂  = 2₁₀");
        System.out.println("  Path 2: 1 → 1 → 0 = 110₂ = 6₁₀");
        System.out.println("\nSum: 2 + 6 = 8\n");

        int result7 = sumRootToLeaf(root7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result7 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Deep Tree ===");
        TreeNode root8 = new TreeNode(1);
        root8.left = new TreeNode(0);
        root8.left.left = new TreeNode(1);
        root8.left.left.left = new TreeNode(0);

        System.out.println("Tree Structure:");
        System.out.println("        1");
        System.out.println("       /");
        System.out.println("      0");
        System.out.println("     /");
        System.out.println("    1");
        System.out.println("   /");
        System.out.println("  0");
        System.out.println("\nRoot-to-Leaf Path:");
        System.out.println("  Path 1: 1 → 0 → 1 → 0 = 1010₂ = 10₁₀");
        System.out.println("\nBinary conversion:");
        System.out.println("  1×2³ + 0×2² + 1×2¹ + 0×2⁰");
        System.out.println("  = 8 + 0 + 2 + 0 = 10\n");

        int result8 = sumRootToLeaf(root8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result8 == 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Larger Tree ===");
        TreeNode root9 = new TreeNode(1);
        root9.left = new TreeNode(1);
        root9.right = new TreeNode(0);
        root9.left.left = new TreeNode(0);
        root9.left.right = new TreeNode(0);
        root9.right.left = new TreeNode(1);
        root9.right.right = new TreeNode(1);

        System.out.println("Tree Structure:");
        System.out.println("        1");
        System.out.println("       / \\");
        System.out.println("      1   0");
        System.out.println("     / \\ / \\");
        System.out.println("    0  0 1  1");
        System.out.println("\nRoot-to-Leaf Paths:");
        System.out.println("  Path 1: 1 → 1 → 0 = 110₂ = 6₁₀");
        System.out.println("  Path 2: 1 → 1 → 0 = 110₂ = 6₁₀");
        System.out.println("  Path 3: 1 → 0 → 1 = 101₂ = 5₁₀");
        System.out.println("  Path 4: 1 → 0 → 1 = 101₂ = 5₁₀");
        System.out.println("\nSum: 6 + 6 + 5 + 5 = 22\n");

        int result9 = sumRootToLeaf(root9);
        System.out.println("✓ Result: " + result9);
        System.out.println("  Expected: 22");
        System.out.println("  Status: " + (result9 == 22 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Given a binary tree where each node is 0 or 1, find sum   ║");
        System.out.println("║    of all root-to-leaf paths interpreted as binary numbers   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Concept - Binary Number Construction:                   ║");
        System.out.println("║    When traversing from root to leaf, each node adds a bit   ║");
        System.out.println("║    Formula: new_value = current_value × 2 + node_value       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why × 2?                                                    ║");
        System.out.println("║    Shifting left in binary = multiplying by 2                ║");
        System.out.println("║    Example building 101₂:                                    ║");
        System.out.println("║      Start: 0                                                ║");
        System.out.println("║      Add 1: 0×2 + 1 = 1   (1₂)                               ║");
        System.out.println("║      Add 0: 1×2 + 0 = 2   (10₂)                              ║");
        System.out.println("║      Add 1: 2×2 + 1 = 5   (101₂) ✓                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DFS Algorithm Breakdown:                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Parameters:                                                 ║");
        System.out.println("║    • rt: current node                                        ║");
        System.out.println("║    • x: accumulated binary value from root to current        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case 1: Null Node                                      ║");
        System.out.println("║    if (rt == null) return 0                                  ║");
        System.out.println("║    → No path, no contribution to sum                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Update Binary Value                                 ║");
        System.out.println("║    x = x * 2 + rt.val                                        ║");
        System.out.println("║    → Shift left and add current bit                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case 2: Leaf Node                                      ║");
        System.out.println("║    if (rt.left == rt.right) return x                         ║");
        System.out.println("║    → Both children null means leaf, return path value        ║");
        System.out.println("║    → Clever check: left==right==null                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Case: Internal Node                               ║");
        System.out.println("║    return DFS(rt.left, x) + DFS(rt.right, x)                 ║");
        System.out.println("║    → Sum of all paths in left subtree + right subtree        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complete Example Walkthrough:                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Tree:      1                                              ║");
        System.out.println("║              / \\                                             ║");
        System.out.println("║             0   1                                            ║");
        System.out.println("║            /                                                 ║");
        System.out.println("║           1                                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Execution Trace:                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    DFS(root=1, x=0):                                         ║");
        System.out.println("║      x = 0*2 + 1 = 1                                         ║");
        System.out.println("║      Not leaf → recurse                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║      DFS(left=0, x=1):                                       ║");
        System.out.println("║        x = 1*2 + 0 = 2  (10₂)                                ║");
        System.out.println("║        Not leaf → recurse                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║        DFS(left=1, x=2):                                     ║");
        System.out.println("║          x = 2*2 + 1 = 5  (101₂)                             ║");
        System.out.println("║          Is leaf → return 5                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║        DFS(right=null, x=2):                                 ║");
        System.out.println("║          return 0                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║        return 5 + 0 = 5                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║      DFS(right=1, x=1):                                      ║");
        System.out.println("║        x = 1*2 + 1 = 3  (11₂)                                ║");
        System.out.println("║        Is leaf → return 3                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║      return 5 + 3 = 8                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Binary to Decimal Conversion Examples:                      ║");
        System.out.println("║    101₂ = 1×2² + 0×2¹ + 1×2⁰ = 4+0+1 = 5₁₀                   ║");
        System.out.println("║    110₂ = 1×2² + 1×2¹ + 0×2⁰ = 4+2+0 = 6₁₀                   ║");
        System.out.println("║    111₂ = 1×2² + 1×2¹ + 1×2⁰ = 4+2+1 = 7₁₀                   ║");
        System.out.println("║    1010₂ = 1×2³ + 0×2² + 1×2¹ + 0×2⁰ = 8+0+2+0 = 10₁₀        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Algorithm Works:                                   ║");
        System.out.println("║    • DFS naturally traverses all root-to-leaf paths          ║");
        System.out.println("║    • x accumulates binary value along the path               ║");
        System.out.println("║    • At each leaf, we have complete binary number            ║");
        System.out.println("║    • Recursion automatically sums all leaf values            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Clever Leaf Detection:                                      ║");
        System.out.println("║    if (rt.left == rt.right)                                  ║");
        System.out.println("║    • If both null: true (leaf node)                          ║");
        System.out.println("║    • If both non-null: false (internal node with 2 children) ║");
        System.out.println("║    • If one null: false (internal node with 1 child)         ║");
        System.out.println("║    → Only true when both are null (leaf)                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Edge Cases:                                                 ║");
        System.out.println("║    ✓ Single node (root is leaf) → return node value          ║");
        System.out.println("║    ✓ All zeros → sum is 0                                    ║");
        System.out.println("║    ✓ All ones → each path has maximum value for its length   ║");
        System.out.println("║    ✓ Linear path → single number                             ║");
        System.out.println("║    ✓ Asymmetric tree → different path lengths OK             ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n) - Visit each node once               ║");
        System.out.println("║  Space Complexity: O(h) - Recursion stack (h = tree height)  ║");
        System.out.println("║                    O(log n) best case, O(n) worst case       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

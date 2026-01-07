package Tree;

/*

Description:
  This program finds the **level of a binary tree with the maximum sum of node values**...
  It uses **Level Order Traversal (Breadth-First Search)** to process the tree level by level...
  If multiple levels have the same maximum sum, the **smallest level number** is returned...

Problem Statement:
  -> You are given the root of a binary tree...
  -> Each node contains an integer value (can be positive, zero, or negative)...
  -> Define the level sum as the sum of values of all nodes at that level...
  -> Return the **level number (1-based)** which has the maximum level sum...
  -> If there is a tie, return the **smallest level index**...

Key Observations:
  -> Level-wise processing is required → BFS is the natural choice...
  -> Each node must be visited exactly once...
  -> Negative values must be handled correctly...
  -> Tie-breaking requires checking strictly greater sum (not ≥)...

Data Structures Used:
  -> Queue<TreeNode>:
       • To perform level order traversal...
       • Stores nodes of the current level before processing children...

Algorithm Strategy (BFS):
  -> Traverse the tree level by level using a queue...
  -> For each level:
       • Compute the sum of all node values at that level...
       • Compare it with the maximum sum found so far...
       • Update the answer level only if current sum is strictly greater...

Algorithm Steps:
  -> Initialize a queue and add the root node...
  -> Initialize:
       • maxSum = -∞...
       • ansLevel = 1...
       • currentLevel = 1...
  -> While queue is not empty:
       • Determine number of nodes at current level (queue size)...
       • Initialize levelSum = 0...
       • For each node in this level:
            - Dequeue node...
            - Add its value to levelSum...
            - Enqueue left and right children if they exist...
       • If levelSum > maxSum:
            - Update maxSum = levelSum...
            - Update ansLevel = currentLevel...
       • Increment currentLevel...
  -> Return ansLevel...

Why This Works:
  -> BFS guarantees that all nodes at the same depth are processed together...
  -> Level sums are computed independently and accurately...
  -> Tracking only the maximum sum ensures optimal result...
  -> Strict comparison preserves smallest level in case of ties...

Edge Case Handling:
  -> Single node tree:
       • Only one level → return 1...
  -> All negative values:
       • Chooses the level with the least negative sum...
  -> Tie across multiple levels:
       • Smallest level index is returned automatically...
  -> Skewed trees:
       • Works correctly even when width is 1...

Complexity Analysis:
  -> Time Complexity:
       • O(n) — every node is visited exactly once...
  -> Space Complexity:
       • O(w) where w is the maximum width of the tree...
       • Worst case O(n) for a complete binary tree...

Example:
  -> Tree:
           1
          / \
         7   0
        / \
       7  -8
  -> Level sums:
       • Level 1 = 1...
       • Level 2 = 7 (maximum)...
       • Level 3 = -1...
  -> Answer = 2...

Key Insights:
  -> BFS is ideal for level-based problems...
  -> Queue size directly represents nodes at a given level...
  -> Careful tie handling avoids incorrect overwrites...

Final Recommendation:
  -> Use **Level Order Traversal (BFS)** for clarity and correctness...
  -> This approach is efficient, simple, and robust for all tree shapes...

Summary:
  -> Classic BFS-based tree problem...
  -> Clean separation of levels using queue size...
  -> Handles negatives, ties, and large values correctly...
  -> Time-efficient and easy to understand...

*/

import java.util.LinkedList;
import java.util.Queue;

public class Maximum_Level_Sum_Of_A_Binary_Tree {

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

    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int maxSum = Integer.MIN_VALUE;
        int ansLevel = 1;
        int currentLevel = 1;

        while (! q.isEmpty()) {

            int levelSum = 0;
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                levelSum += curr.val;

                if (curr.left != null) {
                    q.add(curr.left);
                }

                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

            if (maxSum < levelSum) {
                maxSum = levelSum;
                ansLevel = currentLevel;
            }

            currentLevel++;
        }

        return ansLevel;

    }

    public static void main(String[] args) {
        Maximum_Level_Sum_Of_A_Binary_Tree solution = new Maximum_Level_Sum_Of_A_Binary_Tree();

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            MAXIMUM LEVEL SUM OF A BINARY TREE                ║");
        System.out.println("║       Find the level with maximum sum of node values         ║");
        System.out.println("║           Return the smallest level number if tie            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(7);
        root1.left.right = new TreeNode(- 8);

        System.out.println("Tree structure:");
        System.out.println("       1         Level 1: sum = 1");
        System.out.println("      / \\");
        System.out.println("     7   0       Level 2: sum = 7 + 0 = 7");
        System.out.println("    / \\");
        System.out.println("   7  -8        Level 3: sum = 7 + (-8) = -1");
        System.out.println("\nLevel sums:");
        System.out.println("  Level 1: 1");
        System.out.println("  Level 2: 7 ← Maximum!");
        System.out.println("  Level 3: -1");

        int result1 = solution.maxLevelSum(root1);
        System.out.println("\n✓ Level with maximum sum: " + result1);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Negative Root ===");
        TreeNode root2 = new TreeNode(- 100);
        root2.left = new TreeNode(- 200);
        root2.right = new TreeNode(- 300);
        root2.left.left = new TreeNode(- 20);
        root2.left.right = new TreeNode(- 5);
        root2.right.left = new TreeNode(- 10);

        System.out.println("Tree structure:");
        System.out.println("      -100       Level 1: sum = -100");
        System.out.println("      /   \\");
        System.out.println("   -200  -300    Level 2: sum = -200 + (-300) = -500");
        System.out.println("   /  \\    /");
        System.out.println(" -20  -5 -10     Level 3: sum = -20 + (-5) + (-10) = -35");
        System.out.println("\nLevel sums:");
        System.out.println("  Level 1: -100");
        System.out.println("  Level 2: -500");
        System.out.println("  Level 3: -35 ← Maximum (least negative)!");

        int result2 = solution.maxLevelSum(root2);
        System.out.println("\n✓ Level with maximum sum: " + result2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Node ===");
        TreeNode root3 = new TreeNode(42);

        System.out.println("Tree structure:");
        System.out.println("      42         Level 1: sum = 42");
        System.out.println("\nOnly one level exists");

        int result3 = solution.maxLevelSum(root3);
        System.out.println("\n✓ Level with maximum sum: " + result3);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result3 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Positive Values ===");
        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(20);
        root4.right = new TreeNode(30);
        root4.left.left = new TreeNode(5);
        root4.left.right = new TreeNode(15);
        root4.right.left = new TreeNode(25);
        root4.right.right = new TreeNode(35);

        System.out.println("Tree structure:");
        System.out.println("        10           Level 1: sum = 10");
        System.out.println("       /  \\");
        System.out.println("      20  30         Level 2: sum = 20 + 30 = 50");
        System.out.println("     / \\  / \\");
        System.out.println("    5 15 25 35       Level 3: sum = 5+15+25+35 = 80");
        System.out.println("\nLevel sums:");
        System.out.println("  Level 1: 10");
        System.out.println("  Level 2: 50");
        System.out.println("  Level 3: 80 ← Maximum!");

        int result4 = solution.maxLevelSum(root4);
        System.out.println("\n✓ Level with maximum sum: " + result4);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result4 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Tie - Return Smallest Level ===");
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(3);
        root5.right = new TreeNode(2);
        root5.left.left = new TreeNode(1);
        root5.left.right = new TreeNode(4);

        System.out.println("Tree structure:");
        System.out.println("       5           Level 1: sum = 5");
        System.out.println("      / \\");
        System.out.println("     3   2         Level 2: sum = 3 + 2 = 5");
        System.out.println("    / \\");
        System.out.println("   1   4          Level 3: sum = 1 + 4 = 5");
        System.out.println("\nLevel sums:");
        System.out.println("  Level 1: 5 ← TIE! Return smallest level");
        System.out.println("  Level 2: 5");
        System.out.println("  Level 3: 5");

        int result5 = solution.maxLevelSum(root5);
        System.out.println("\n✓ Level with maximum sum: " + result5);
        System.out.println("  Expected: 1 (smallest level in tie)");
        System.out.println("  Status: " + (result5 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Skewed Tree (Left) ===");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.left.left = new TreeNode(3);
        root6.left.left.left = new TreeNode(4);

        System.out.println("Tree structure (left-skewed):");
        System.out.println("  1               Level 1: sum = 1");
        System.out.println("  /");
        System.out.println(" 2                Level 2: sum = 2");
        System.out.println(" /");
        System.out.println("3                 Level 3: sum = 3");
        System.out.println("/");
        System.out.println("4                 Level 4: sum = 4");
        System.out.println("\nLevel sums increase: 1, 2, 3, 4");
        System.out.println("Maximum at deepest level");

        int result6 = solution.maxLevelSum(root6);
        System.out.println("\n✓ Level with maximum sum: " + result6);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result6 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Mixed Positive and Negative ===");
        TreeNode root7 = new TreeNode(100);
        root7.left = new TreeNode(- 50);
        root7.right = new TreeNode(- 50);
        root7.left.left = new TreeNode(30);
        root7.left.right = new TreeNode(40);
        root7.right.left = new TreeNode(30);
        root7.right.right = new TreeNode(40);

        System.out.println("Tree structure:");
        System.out.println("        100          Level 1: sum = 100 ← Maximum!");
        System.out.println("       /   \\");
        System.out.println("     -50   -50      Level 2: sum = -50 + (-50) = -100");
        System.out.println("     / \\   / \\");
        System.out.println("   30 40 30 40      Level 3: sum = 30+40+30+40 = 140");
        System.out.println("\nLevel sums:");
        System.out.println("  Level 1: 100");
        System.out.println("  Level 2: -100");
        System.out.println("  Level 3: 140 ← Maximum!");

        int result7 = solution.maxLevelSum(root7);
        System.out.println("\n✓ Level with maximum sum: " + result7);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result7 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large Values ===");
        TreeNode root8 = new TreeNode(1000000);
        root8.left = new TreeNode(500000);
        root8.right = new TreeNode(499999);

        System.out.println("Tree structure:");
        System.out.println("     1000000       Level 1: sum = 1,000,000 ← Maximum!");
        System.out.println("      /   \\");
        System.out.println("  500000 499999    Level 2: sum = 999,999");
        System.out.println("\nLevel 1 has maximum sum");

        int result8 = solution.maxLevelSum(root8);
        System.out.println("\n✓ Level with maximum sum: " + result8);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result8 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY: Level Order Traversal (BFS)              ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  1. Use queue for level-by-level traversal                   ║");
        System.out.println("║  2. For each level, calculate sum of all nodes               ║");
        System.out.println("║  3. Track maximum sum and corresponding level                ║");
        System.out.println("║  4. Return level with maximum sum (smallest if tie)          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n) - visit each node once               ║");
        System.out.println("║  Space Complexity: O(w) - w is max width of tree             ║");
        System.out.println("║                    O(n) worst case for complete tree         ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Key Points:                                                 ║");
        System.out.println("║  • Handles negative values correctly                         ║");
        System.out.println("║  • Returns smallest level number in case of tie              ║");
        System.out.println("║  • Works for skewed, balanced, and any tree structure        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

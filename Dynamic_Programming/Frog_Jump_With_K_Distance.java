package Dynamic_Programming;

/*

Description:
  This program solves the **Frog Jump with K Distance** problem using Dynamic Programming...
  The frog starts at index 0 and wants to reach the last stone with minimum energy cost...
  Unlike the classic frog jump, here the frog can jump **up to k steps** forward in one move...

Problem Statement:
  -> You are given an array `height[]` where height[i] represents the height of the i-th stone...
  -> A frog starts at stone 0 and wants to reach stone n-1...
  -> From stone i, the frog can jump to any stone j such that:
       • 1 ≤ (j - i) ≤ k...
  -> The cost of a jump from i to j is:
       • |height[i] - height[j]|...
  -> Find the **minimum total cost** to reach the last stone...

Key Observations:
  -> The frog has multiple choices at each position (up to k jumps)...
  -> The problem exhibits **overlapping subproblems**...
  -> Optimal substructure exists:
       • Minimum cost to reach stone i depends on minimum costs of previous stones...

Dynamic Programming State Definition:
  -> dp[i] = minimum cost to reach stone i...

Transition Relation:
  -> dp[i] = min over all valid jumps j:
       dp[i] = min( dp[i - 1] + |h[i] - h[i - 1]|,
                    dp[i - 2] + |h[i] - h[i - 2]|,
                    ...
                    dp[i - k] + |h[i] - h[i - k]| )...
  -> Only consider terms where (i - j) ≥ 0...

Approach 1: Memoization (Top-Down DP):
  -> Start solving from the last index (n-1)...
  -> Recursively compute minimum cost for previous indices...
  -> Store results in dp[] to avoid recomputation...

Algorithm Steps (Memoization):
  -> Base Case:
       • If idx == 0 → cost = 0...
  -> If dp[idx] is already computed:
       • Return dp[idx]...
  -> For each jump length i from 1 to k:
       • If idx - i >= 0:
            currCost = minCost(idx - i) + |height[idx] - height[idx - i]|...
            update minimum...
  -> Store and return dp[idx]...

Complexity (Memoization):
  -> Time Complexity: O(n * k)...
  -> Space Complexity:
       • O(n) for dp array...
       • O(n) recursion stack...

Approach 2: Tabulation (Bottom-Up DP):
  -> Build the solution iteratively from index 0 to n-1...
  -> Avoid recursion and stack overhead...

Algorithm Steps (Tabulation):
  -> Initialize dp[0] = 0...
  -> For i = 1 to n-1:
       • Initialize minSteps = ∞...
       • For j = 1 to k:
            if i - j >= 0:
                cost = dp[i - j] + |height[i] - height[i - j]|...
                minSteps = min(minSteps, cost)...
       • dp[i] = minSteps...
  -> Answer = dp[n - 1]...

Complexity (Tabulation):
  -> Time Complexity: O(n * k)...
  -> Space Complexity: O(n)...

Why DP is Necessary:
  -> Brute force recursion leads to exponential time...
  -> DP ensures each state is computed only once...
  -> Handles large n and k efficiently...

Edge Case Analysis:
  -> k = 1:
       • Frog must move step-by-step (no choices)...
  -> k ≥ n - 1:
       • Frog can directly jump to the end...
  -> n = 1:
       • Already at destination → cost = 0...
  -> Flat heights:
       • All jumps cost 0...

Example:
  -> heights = [10, 50, 100, 20], k = 3...
  -> Direct jump: 0 → 3...
  -> Cost = |20 - 10| = 10 (optimal)...

Key Insights:
  -> At each stone, try all possible k jumps...
  -> Larger k gives more flexibility and possibly lower cost...
  -> Tabulation is preferred in practice due to no recursion risk...

Final Recommendation:
  -> Use **Tabulation approach** for clean, efficient, and safe implementation...
  -> Memoization is great for understanding recursive structure...

Summary:
  -> Classic DP optimization problem...
  -> Generalization of Frog Jump (k instead of 2)...
  -> Time Complexity grows linearly with k...
  -> Excellent problem to understand DP state transitions...

*/

import java.util.Arrays;

public class Frog_Jump_With_K_Distance {

    // Memoization approach
    private static int minCost(int idx, int[] height, int[] dp, int k) {

        if (idx == 0) {
            return 0;
        }

        if (dp[idx] != - 1) {
            return dp[idx];
        }

        int minSteps = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (idx - i >= 0) {
                int currJump = minCost(idx - i, height, dp, k) + Math.abs(height[idx - i] - height[idx]);
                minSteps = Math.min(currJump, minSteps);
            }
        }

        return dp[idx] = minSteps;

    }

    // Tabulation approach (bottom-up)
    private static int minCostTabulation(int[] height, int k) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;

            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(height[i - j] - height[i]);
                    minSteps = Math.min(jump, minSteps);
                }
            }

            dp[i] = minSteps;
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         FROG JUMP WITH K DISTANCE - 2 APPROACHES             ║");
        System.out.println("║           Find minimum cost to reach last stone              ║");
        System.out.println("║       Frog can jump 1, 2, 3, ..., up to k steps forward      ║");
        System.out.println("║              Cost = |height[i] - height[j]|                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: k=2 (Classic Frog Jump) ===");
        int[] heights1 = {10, 30, 40, 20};
        int k1 = 2;
        int n1 = heights1.length;
        System.out.println("Heights: " + Arrays.toString(heights1));
        System.out.println("Max jump distance (k): " + k1);
        System.out.println("\nPossible paths:");
        System.out.println("  Path 1: 0→1→2→3");
        System.out.println("    Cost: |30-10| + |40-30| + |20-40| = 20+10+20 = 50");
        System.out.println("  Path 2: 0→1→3 (jump 2 from pos 1)");
        System.out.println("    Cost: |30-10| + |20-30| = 20+10 = 30 ✓");
        System.out.println("  Path 3: 0→2→3 (jump 2 from pos 0)");
        System.out.println("    Cost: |40-10| + |20-40| = 30+20 = 50");
        System.out.println("  Minimum: 30\n");

        int[] dp1 = new int[n1];
        Arrays.fill(dp1, - 1);
        int result1_memo = minCost(n1 - 1, heights1, dp1, k1);
        int result1_tab = minCostTabulation(heights1, k1);

        System.out.println("✓ Memoization result:   " + result1_memo);
        System.out.println("✓ Tabulation result:    " + result1_tab);
        System.out.println("  Expected: 30");
        System.out.println("  Status: " + (result1_memo == 30 && result1_tab == 30 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: k=3 (More Jump Options) ===");
        int[] heights2 = {10, 20, 30, 40, 50, 60};
        int k2 = 3;
        int n2 = heights2.length;
        System.out.println("Heights: " + Arrays.toString(heights2));
        System.out.println("Max jump distance (k): " + k2);
        System.out.println("\nAnalysis:");
        System.out.println("  Can jump 1, 2, or 3 steps at a time");
        System.out.println("  Optimal path: 0→3→5 (jump 3, then jump 2)");
        System.out.println("  Cost: |40-10| + |60-40| = 30+20 = 50");
        System.out.println("  vs 0→1→2→3→4→5 = 10+10+10+10+10 = 50");
        System.out.println("  Multiple optimal paths possible!\n");

        int[] dp2 = new int[n2];
        Arrays.fill(dp2, - 1);
        int result2_memo = minCost(n2 - 1, heights2, dp2, k2);
        int result2_tab = minCostTabulation(heights2, k2);

        System.out.println("✓ Memoization result:   " + result2_memo);
        System.out.println("✓ Tabulation result:    " + result2_tab);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result2_memo == 50 && result2_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: k=4 (Large Jump Distance) ===");
        int[] heights3 = {40, 10, 20, 70, 80, 10, 20, 70, 80, 60};
        int k3 = 4;
        int n3 = heights3.length;
        System.out.println("Heights: " + Arrays.toString(heights3));
        System.out.println("Max jump distance (k): " + k3);
        System.out.println("\nAnalysis:");
        System.out.println("  With k=4, can skip many intermediate stones");
        System.out.println("  Strategy: Jump to lower heights when possible");
        System.out.println("  Avoid high peaks by jumping over them\n");

        int[] dp3 = new int[n3];
        Arrays.fill(dp3, - 1);
        int result3_memo = minCost(n3 - 1, heights3, dp3, k3);
        int result3_tab = minCostTabulation(heights3, k3);

        System.out.println("✓ Memoization result:   " + result3_memo);
        System.out.println("✓ Tabulation result:    " + result3_tab);
        System.out.println("  Both methods agree: " + (result3_memo == result3_tab ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: k = n-1 (Can Jump to End Directly) ===");
        int[] heights4 = {10, 50, 100, 20};
        int k4 = 3;
        int n4 = heights4.length;
        System.out.println("Heights: " + Arrays.toString(heights4));
        System.out.println("Max jump distance (k): " + k4);
        System.out.println("\nAnalysis:");
        System.out.println("  k=3 allows jumping from position 0 to position 3 directly!");
        System.out.println("  Direct jump: 0→3");
        System.out.println("    Cost: |20-10| = 10 ✓");
        System.out.println("  vs via peaks: 0→1→2→3 = 40+50+80 = 170");
        System.out.println("  Large k enables dramatic shortcuts!\n");

        int[] dp4 = new int[n4];
        Arrays.fill(dp4, - 1);
        int result4_memo = minCost(n4 - 1, heights4, dp4, k4);
        int result4_tab = minCostTabulation(heights4, k4);

        System.out.println("✓ Memoization result:   " + result4_memo);
        System.out.println("✓ Tabulation result:    " + result4_tab);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result4_memo == 10 && result4_tab == 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Flat Surface (k=2) ===");
        int[] heights5 = {5, 5, 5, 5, 5};
        int k5 = 2;
        int n5 = heights5.length;
        System.out.println("Heights: " + Arrays.toString(heights5));
        System.out.println("Max jump distance (k): " + k5);
        System.out.println("\nAnalysis:");
        System.out.println("  All heights equal → all jumps cost 0");
        System.out.println("  Any path works, total cost = 0\n");

        int[] dp5 = new int[n5];
        Arrays.fill(dp5, - 1);
        int result5_memo = minCost(n5 - 1, heights5, dp5, k5);
        int result5_tab = minCostTabulation(heights5, k5);

        System.out.println("✓ Memoization result:   " + result5_memo);
        System.out.println("✓ Tabulation result:    " + result5_tab);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5_memo == 0 && result5_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: k=1 (Can Only Jump 1 Step) ===");
        int[] heights6 = {10, 20, 15, 25, 30};
        int k6 = 1;
        int n6 = heights6.length;
        System.out.println("Heights: " + Arrays.toString(heights6));
        System.out.println("Max jump distance (k): " + k6);
        System.out.println("\nAnalysis:");
        System.out.println("  k=1 means must visit every stone sequentially");
        System.out.println("  No choice in path: 0→1→2→3→4");
        System.out.println("  Cost: 10+5+10+5 = 30\n");

        int[] dp6 = new int[n6];
        Arrays.fill(dp6, - 1);
        int result6_memo = minCost(n6 - 1, heights6, dp6, k6);
        int result6_tab = minCostTabulation(heights6, k6);

        System.out.println("✓ Memoization result:   " + result6_memo);
        System.out.println("✓ Tabulation result:    " + result6_tab);
        System.out.println("  Expected: 30");
        System.out.println("  Status: " + (result6_memo == 30 && result6_tab == 30 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Two Stones Only (k=5) ===");
        int[] heights7 = {100, 150};
        int k7 = 5;
        int n7 = heights7.length;
        System.out.println("Heights: " + Arrays.toString(heights7));
        System.out.println("Max jump distance (k): " + k7);
        System.out.println("\nAnalysis:");
        System.out.println("  Only one jump possible: 0→1");
        System.out.println("  Cost: |150-100| = 50");
        System.out.println("  k doesn't matter with only 2 stones\n");

        int[] dp7 = new int[n7];
        Arrays.fill(dp7, - 1);
        int result7_memo = minCost(n7 - 1, heights7, dp7, k7);
        int result7_tab = minCostTabulation(heights7, k7);

        System.out.println("✓ Memoization result:   " + result7_memo);
        System.out.println("✓ Tabulation result:    " + result7_tab);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result7_memo == 50 && result7_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Descending Heights (k=3) ===");
        int[] heights8 = {100, 90, 80, 70, 60, 50};
        int k8 = 3;
        int n8 = heights8.length;
        System.out.println("Heights: " + Arrays.toString(heights8));
        System.out.println("Max jump distance (k): " + k8);
        System.out.println("\nAnalysis:");
        System.out.println("  All downward jumps");
        System.out.println("  Jumping further saves steps but might increase cost");
        System.out.println("  Sequential: 0→1→2→3→4→5 = 10+10+10+10+10 = 50");
        System.out.println("  Skip 1: 0→2→4→5 = 20+20+10 = 50");
        System.out.println("  Skip 2: 0→3→5 = 30+20 = 50");
        System.out.println("  All paths have same cost!\n");

        int[] dp8 = new int[n8];
        Arrays.fill(dp8, - 1);
        int result8_memo = minCost(n8 - 1, heights8, dp8, k8);
        int result8_tab = minCostTabulation(heights8, k8);

        System.out.println("✓ Memoization result:   " + result8_memo);
        System.out.println("✓ Tabulation result:    " + result8_tab);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result8_memo == 50 && result8_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY COMPARISON                                       ║");
        System.out.println("║  ┌──────────────────┬────────────┬──────────────┬─────────┐  ║");
        System.out.println("║  │ Method           │ Time       │ Space        │ Best    │  ║");
        System.out.println("║  ├──────────────────┼────────────┼──────────────┼─────────┤  ║");
        System.out.println("║  │ Memoization      │ O(n*k)     │ O(n)+O(n)    │ Top-Dn  │  ║");
        System.out.println("║  │ Tabulation       │ O(n*k)     │ O(n)         │ Faster  │  ║");
        System.out.println("║  └──────────────────┴────────────┴──────────────┴─────────┘  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insights:                                               ║");
        System.out.println("║  • For each position, try all k possible jumps               ║");
        System.out.println("║  • Larger k = more options = potentially lower cost          ║");
        System.out.println("║  • When k ≥ n-1, can jump directly to end                    ║");
        System.out.println("║  • Tabulation avoids recursion overhead                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  🏆 Winner: Tabulation (cleaner, no stack overflow risk)     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    
    }

}

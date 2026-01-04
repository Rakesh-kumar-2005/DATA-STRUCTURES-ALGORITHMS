package Dynamic_Programming;

/*

Description:
  This program solves the classic **Frog Jump** dynamic programming problem...
  A frog starts at stone 0 and must reach the last stone with minimum total energy cost...
  The frog can jump either 1 step or 2 steps forward at a time...

Problem Statement:
  -> Given an array `height[]` where height[i] represents the height of the i-th stone...
  -> A frog starts at index 0 and wants to reach index n-1...
  -> The frog can jump:
       • From i → i+1 (one step)...
       • From i → i+2 (two steps)...
  -> Cost of a jump from stone i to j is |height[i] - height[j]|...
  -> Find the minimum total cost to reach the last stone...

Key Observation:
  -> The minimum cost to reach a stone depends only on:
       • The minimum cost to reach the previous stone...
       • The minimum cost to reach the stone before that...
  -> This creates an optimal substructure → perfect for Dynamic Programming...
  -> Only the last two states are required at any time...

Approach 1: Memoization (Top-Down DP):
  -> Define dp[i] = minimum cost to reach stone i...
  -> Use recursion to explore both jump choices:
       • Jump from i-1 to i...
       • Jump from i-2 to i (if possible)...
  -> Store computed results in dp[] to avoid recomputation...
  -> Base case:
       • dp[0] = 0 (starting point, no cost)...

Algorithm Steps (Memoization):
  -> If index == 0, return 0...
  -> If dp[index] already computed, return it...
  -> Compute:
       fs = cost from index-1 → index...
       ss = cost from index-2 → index (if valid)...
  -> dp[index] = min(fs, ss)...
  -> Return dp[index]...

Approach 2: Tabulation (Bottom-Up DP):
  -> Build dp[] array iteratively from 0 to n-1...
  -> dp[0] = 0...
  -> For each i from 1 to n-1:
       • fs = dp[i-1] + |height[i] - height[i-1]|...
       • ss = dp[i-2] + |height[i] - height[i-2]| (if i > 1)...
       • dp[i] = min(fs, ss)...
  -> Answer is dp[n-1]...

Approach 3: Space Optimized DP:
  -> Observe that dp[i] depends only on dp[i-1] and dp[i-2]...
  -> Replace dp[] array with two variables:
       • prev1 → dp[i-1]...
       • prev2 → dp[i-2]...
  -> Update these values iteratively...
  -> Achieves O(1) space complexity...

Why This Works:
  -> The problem exhibits:
       • Optimal substructure...
       • Overlapping subproblems...
  -> Dynamic Programming ensures:
       • All possible paths are considered...
       • Minimum cost is always chosen...
  -> Space optimization is possible because only last two states matter...

Examples:
  -> heights = [10, 20, 30, 10]
       • Optimal path: 0→1→3
       • Cost = |20-10| + |10-20| = 10 + 10 = 20...
  -> heights = [10, 10, 10, 10]
       • All jumps cost 0...
       • Minimum total cost = 0...

Edge Cases:
  -> Only one stone → cost = 0...
  -> Two stones → only one jump possible...
  -> All heights equal...
  -> Strictly increasing or decreasing heights...

Time and Space Complexity:
  -> Memoization:
       • Time: O(n)...
       • Space: O(n) (dp array + recursion stack)...
  -> Tabulation:
       • Time: O(n)...
       • Space: O(n)...
  -> Space Optimized:
       • Time: O(n)...
       • Space: O(1)...

Summary:
  -> Frog Jump is a foundational DP problem...
  -> Demonstrates progression from recursion → DP → optimization...
  -> Best practical solution: Space Optimized DP (Method 3)...
  -> Clean, efficient, and interview-friendly approach...

*/

import java.util.Arrays;

public class Frog_Jump {

    // Method 1: Memoization (Top-Down DP)
    private static int method(int idx, int[] height, int[] dp) {
        if (idx == 0) {
            return 0;
        }

        if (dp[idx] != - 1) {
            return dp[idx];
        }

        int fs = method(idx - 1, height, dp) + Math.abs(height[idx] - height[idx - 1]);
        int ss = Integer.MAX_VALUE;

        if (idx > 1) {
            ss = method(idx - 2, height, dp) + Math.abs(height[idx] - height[idx - 2]);
        }

        dp[idx] = Math.min(fs, ss);
        return dp[idx];
    }

    // Method 2: Tabulation (Bottom-Up DP)
    private static int method2(int n, int[] height) {
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int fs = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int ss = Integer.MAX_VALUE;

            if (i > 1) {
                ss = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            }

            dp[i] = Math.min(fs, ss);
        }

        return dp[n - 1];
    }

    // Method 3: Space Optimized (O(1) space)
    private static int method3(int n, int[] height) {
        if (n == 1) {
            return 0;
        }

        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = 1; i < n; i++) {
            int fs = prev1 + Math.abs(height[i] - height[i - 1]);
            int ss = Integer.MAX_VALUE;

            if (i > 1) {
                ss = prev2 + Math.abs(height[i] - height[i - 2]);
            }

            int curr = Math.min(fs, ss);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
        
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FROG JUMP - 3 APPROACHES                  ║");
        System.out.println("║           Find minimum cost to reach the last stone          ║");
        System.out.println("║           Frog can jump 1 or 2 steps forward                 ║");
        System.out.println("║           Cost = |height[i] - height[j]|                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] heights1 = {10, 20, 30, 10};
        int n1 = heights1.length;
        System.out.println("Heights: " + Arrays.toString(heights1));
        System.out.println("Positions: [0]  [1]  [2]  [3]");
        System.out.println("\nPossible paths:");
        System.out.println("  Path 1: 0→1→2→3");
        System.out.println("    Cost: |20-10| + |30-20| + |10-30| = 10+10+20 = 40");
        System.out.println("  Path 2: 0→1→3 (jump 2 from pos 1)");
        System.out.println("    Cost: |20-10| + |10-20| = 10+10 = 20");
        System.out.println("  Path 3: 0→2→3 (jump 2 from pos 0)");
        System.out.println("    Cost: |30-10| + |10-30| = 20+20 = 40");
        System.out.println("  Minimum: Path 2 = 20\n");

        int[] dp1 = new int[n1];
        Arrays.fill(dp1, - 1);
        int result1_m1 = method(n1 - 1, heights1, dp1);
        int result1_m2 = method2(n1, heights1);
        int result1_m3 = method3(n1, heights1);

        System.out.println("Method 1 (Memoization):     " + result1_m1 + " ✓");
        System.out.println("Method 2 (Tabulation):      " + result1_m2 + " ✓");
        System.out.println("Method 3 (Space Optimized): " + result1_m3 + " ✓");
        System.out.println("Expected: 20");
        System.out.println("Status: " + (result1_m1 == 20 && result1_m2 == 20 && result1_m3 == 20 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Ascending Heights ===");
        int[] heights2 = {10, 30, 40, 20};
        int n2 = heights2.length;
        System.out.println("Heights: " + Arrays.toString(heights2));
        System.out.println("\nAnalysis:");
        System.out.println("  0→1: cost 20");
        System.out.println("  1→2: cost 10, 1→3: cost 10");
        System.out.println("  0→2: cost 30");
        System.out.println("  Optimal: 0→1→3 = 20+10 = 30\n");

        int[] dp2 = new int[n2];
        Arrays.fill(dp2, - 1);
        int result2_m1 = method(n2 - 1, heights2, dp2);
        int result2_m2 = method2(n2, heights2);
        int result2_m3 = method3(n2, heights2);

        System.out.println("Method 1: " + result2_m1);
        System.out.println("Method 2: " + result2_m2);
        System.out.println("Method 3: " + result2_m3);
        System.out.println("Expected: 30");
        System.out.println("Status: " + (result2_m1 == 30 && result2_m2 == 30 && result2_m3 == 30 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Two Stones Only ===");
        int[] heights3 = {10, 50};
        int n3 = heights3.length;
        System.out.println("Heights: " + Arrays.toString(heights3));
        System.out.println("\nAnalysis:");
        System.out.println("  Only one jump possible: 0→1");
        System.out.println("  Cost: |50-10| = 40\n");

        int[] dp3 = new int[n3];
        Arrays.fill(dp3, - 1);
        int result3_m1 = method(n3 - 1, heights3, dp3);
        int result3_m2 = method2(n3, heights3);
        int result3_m3 = method3(n3, heights3);

        System.out.println("Method 1: " + result3_m1);
        System.out.println("Method 2: " + result3_m2);
        System.out.println("Method 3: " + result3_m3);
        System.out.println("Expected: 40");
        System.out.println("Status: " + (result3_m1 == 40 && result3_m2 == 40 && result3_m3 == 40 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Flat Surface ===");
        int[] heights4 = {10, 10, 10, 10, 10};
        int n4 = heights4.length;
        System.out.println("Heights: " + Arrays.toString(heights4));
        System.out.println("\nAnalysis:");
        System.out.println("  All heights equal");
        System.out.println("  Any path has 0 cost\n");

        int[] dp4 = new int[n4];
        Arrays.fill(dp4, - 1);
        int result4_m1 = method(n4 - 1, heights4, dp4);
        int result4_m2 = method2(n4, heights4);
        int result4_m3 = method3(n4, heights4);

        System.out.println("Method 1: " + result4_m1);
        System.out.println("Method 2: " + result4_m2);
        System.out.println("Method 3: " + result4_m3);
        System.out.println("Expected: 0");
        System.out.println("Status: " + (result4_m1 == 0 && result4_m2 == 0 && result4_m3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Descending Heights ===");
        int[] heights5 = {50, 40, 30, 20, 10};
        int n5 = heights5.length;
        System.out.println("Heights: " + Arrays.toString(heights5));
        System.out.println("\nAnalysis:");
        System.out.println("  All downward jumps");
        System.out.println("  Jump 2 when possible saves cost");
        System.out.println("  Optimal: 0→2→4 = 20+20 = 40\n");

        int[] dp5 = new int[n5];
        Arrays.fill(dp5, - 1);
        int result5_m1 = method(n5 - 1, heights5, dp5);
        int result5_m2 = method2(n5, heights5);
        int result5_m3 = method3(n5, heights5);

        System.out.println("Method 1: " + result5_m1);
        System.out.println("Method 2: " + result5_m2);
        System.out.println("Method 3: " + result5_m3);
        System.out.println("Expected: 40");
        System.out.println("Status: " + (result5_m1 == 40 && result5_m2 == 40 && result5_m3 == 40 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Zigzag Pattern ===");
        int[] heights6 = {5, 30, 15, 25, 10, 20};
        int n6 = heights6.length;
        System.out.println("Heights: " + Arrays.toString(heights6));
        System.out.println("\nAnalysis:");
        System.out.println("  Complex up-down pattern");
        System.out.println("  Need DP to find optimal path\n");

        int[] dp6 = new int[n6];
        Arrays.fill(dp6, - 1);
        int result6_m1 = method(n6 - 1, heights6, dp6);
        int result6_m2 = method2(n6, heights6);
        int result6_m3 = method3(n6, heights6);

        System.out.println("Method 1: " + result6_m1);
        System.out.println("Method 2: " + result6_m2);
        System.out.println("Method 3: " + result6_m3);
        System.out.println("All methods agree: " + (result6_m1 == result6_m2 && result6_m2 == result6_m3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Heights ===");
        int[] heights7 = {100, 200, 300, 400, 500, 600, 700, 800};
        int n7 = heights7.length;
        System.out.println("Heights: " + Arrays.toString(heights7));
        System.out.println("\nAnalysis:");
        System.out.println("  Always increasing by 100");
        System.out.println("  Jump 2 whenever possible");
        System.out.println("  0→2→4→6→7 optimal\n");

        int[] dp7 = new int[n7];
        Arrays.fill(dp7, - 1);
        int result7_m1 = method(n7 - 1, heights7, dp7);
        int result7_m2 = method2(n7, heights7);
        int result7_m3 = method3(n7, heights7);

        System.out.println("Method 1: " + result7_m1);
        System.out.println("Method 2: " + result7_m2);
        System.out.println("Method 3: " + result7_m3);
        System.out.println("All methods agree: " + (result7_m1 == result7_m2 && result7_m2 == result7_m3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY COMPARISON                                       ║");
        System.out.println("║  ┌─────────────────┬──────────┬────────────┬────────────┐    ║");
        System.out.println("║  │ Method          │ Time     │ Space      │ Approach   │    ║");
        System.out.println("║  ├─────────────────┼──────────┼────────────┼────────────┤    ║");
        System.out.println("║  │ Memoization     │ O(n)     │ O(n)+O(n)  │ Top-Down   │    ║");
        System.out.println("║  │ Tabulation      │ O(n)     │ O(n)       │ Bottom-Up  │    ║");
        System.out.println("║  │ Space Optimized │ O(n)     │ O(1)       │ Best!      │    ║");
        System.out.println("║  └─────────────────┴──────────┴────────────┴────────────┘    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight: Only need previous 2 states!                   ║");
        System.out.println("║  Method 3 is most optimal for production use                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

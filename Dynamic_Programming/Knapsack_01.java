package Dynamic_Programming;

import java.util.ArrayList;

public class Knapsack_01 {

    private static int recursive(ArrayList<Integer> values,
                                 ArrayList<Integer> weights, int idx, int maxWeight) {
        if (idx == 0) {
            if (maxWeight >= weights.get(0)) {
                return values.get(0);
            } else {
                return 0;
            }
        }

        int notPick = recursive(values, weights, idx - 1, maxWeight);
        int pick = Integer.MIN_VALUE;

        if (weights.get(idx) <= maxWeight) {
            pick = values.get(idx) + recursive(values, weights, idx - 1, maxWeight - weights.get(idx));
        }

        return Math.max(pick, notPick);

    }

    private static int memoization(ArrayList<Integer> values,
                                   ArrayList<Integer> weights, int idx, int maxWeight, int[][] dp) {
        if (idx == 0) {
            if (maxWeight >= weights.get(0)) {
                return values.get(0);
            } else {
                return 0;
            }
        }

        if (dp[idx][maxWeight] != - 1) {
            return dp[idx][maxWeight];
        }

        int notPick = memoization(values, weights, idx - 1, maxWeight, dp);
        int pick = Integer.MIN_VALUE;

        if (weights.get(idx) <= maxWeight) {
            pick = values.get(idx) + memoization(values, weights, idx - 1, maxWeight - weights.get(idx), dp);
        }

        return dp[idx][maxWeight] = Math.max(pick, notPick);

    }

    private static int tabulation(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];

        for (int W = weights.get(0); W <= maxWeight; W++) {
            dp[0][W] = values.get(0);
        }

        for (int idx = 1; idx < n; idx++) {
            for (int W = 0; W <= maxWeight; W++) {
                int notPick = dp[idx - 1][W];
                int pick = Integer.MIN_VALUE;

                if (weights.get(idx) <= W) {
                    pick = values.get(idx) + dp[idx - 1][W - weights.get(idx)];
                }

                dp[idx][W] = Math.max(pick, notPick);
            }
        }

        return dp[n - 1][maxWeight];
    }

    private static int ultimateSpaceOptimization(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int maxWeight) {

        int[] prev = new int[maxWeight + 1];

        for (int W = weights.get(0); W <= maxWeight; W++) {
            prev[W] = values.get(0);
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[maxWeight + 1];

            for (int W = 0; W <= maxWeight; W++) {
                int notPick = prev[W];
                int pick = Integer.MIN_VALUE;

                if (weights.get(idx) <= W) {
                    pick = values.get(idx) + prev[W - weights.get(idx)];
                }

                curr[W] = Math.max(pick, notPick);
            }
            prev = curr;
        }

        return prev[maxWeight];
    }

    private static int oneDimensionalArrayUltimateSpaceOptimization(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int maxWeight) {

        int[] prev = new int[maxWeight + 1];

        for (int W = weights.get(0); W <= maxWeight; W++) {
            prev[W] = values.get(0);
        }

        for (int idx = 1; idx < n; idx++) {

            for (int W = maxWeight; W >= 0; W--) {
                int notPick = prev[W];
                int pick = Integer.MIN_VALUE;

                if (weights.get(idx) <= W) {
                    pick = values.get(idx) + prev[W - weights.get(idx)];
                }

                prev[W] = Math.max(pick, notPick);
            }
        }

        return prev[maxWeight];
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 0/1 KNAPSACK PROBLEM                         ║");
        System.out.println("║  Maximize value of items in knapsack with weight limit       ║");
        System.out.println("║  Each item can be taken at most once                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        ArrayList<Integer> values1 = new ArrayList<>();
        values1.add(60);
        values1.add(100);
        values1.add(120);
        ArrayList<Integer> weights1 = new ArrayList<>();
        weights1.add(10);
        weights1.add(20);
        weights1.add(30);
        int maxWeight1 = 50;

        System.out.println("Items:");
        for (int i = 0; i < values1.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values1.get(i) + ", weight=" + weights1.get(i));
        }
        System.out.println("Max Weight: " + maxWeight1);
        System.out.println("\nOptimal selection:");
        System.out.println("  Item 1 (value=100, weight=20)");
        System.out.println("  Item 2 (value=120, weight=30)");
        System.out.println("  Total: value=220, weight=50\n");

        int result1_rec = recursive(values1, weights1, 2, maxWeight1);

        int[][] dp1 = new int[3][maxWeight1 + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= maxWeight1; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(values1, weights1, 2, maxWeight1, dp1);

        int result1_tab = tabulation(values1, weights1, 3, maxWeight1);
        int result1_opt = ultimateSpaceOptimization(values1, weights1, 3, maxWeight1);
        int result1_1d = oneDimensionalArrayUltimateSpaceOptimization(values1, weights1, 3, maxWeight1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized (2 arrays) Result: " + result1_opt);
        System.out.println("✓ Space Optimized (1 array) Result: " + result1_1d);
        System.out.println("  Expected: 220");
        System.out.println("  Status: " + (result1_tab == 220 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: All Items Fit ===");
        ArrayList<Integer> values2 = new ArrayList<>();
        values2.add(10);
        values2.add(20);
        values2.add(30);
        ArrayList<Integer> weights2 = new ArrayList<>();
        weights2.add(1);
        weights2.add(1);
        weights2.add(1);
        int maxWeight2 = 10;

        System.out.println("Items:");
        for (int i = 0; i < values2.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values2.get(i) + ", weight=" + weights2.get(i));
        }
        System.out.println("Max Weight: " + maxWeight2);
        System.out.println("\nAll items fit in knapsack");
        System.out.println("Total: value=60, weight=3\n");

        int result2_tab = tabulation(values2, weights2, 3, maxWeight2);
        int result2_1d = oneDimensionalArrayUltimateSpaceOptimization(values2, weights2, 3, maxWeight2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_1d);
        System.out.println("  Expected: 60");
        System.out.println("  Status: " + (result2_tab == 60 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: No Items Fit ===");
        ArrayList<Integer> values3 = new ArrayList<>();
        values3.add(100);
        values3.add(200);
        ArrayList<Integer> weights3 = new ArrayList<>();
        weights3.add(50);
        weights3.add(100);
        int maxWeight3 = 10;

        System.out.println("Items:");
        for (int i = 0; i < values3.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values3.get(i) + ", weight=" + weights3.get(i));
        }
        System.out.println("Max Weight: " + maxWeight3);
        System.out.println("\nAll items too heavy");
        System.out.println("Total: value=0, weight=0\n");

        int result3_tab = tabulation(values3, weights3, 2, maxWeight3);
        int result3_1d = oneDimensionalArrayUltimateSpaceOptimization(values3, weights3, 2, maxWeight3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_1d);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Item ===");
        ArrayList<Integer> values4 = new ArrayList<>();
        values4.add(50);
        ArrayList<Integer> weights4 = new ArrayList<>();
        weights4.add(10);
        int maxWeight4 = 15;

        System.out.println("Items:");
        System.out.println("  Item 0: value=50, weight=10");
        System.out.println("Max Weight: " + maxWeight4);
        System.out.println("\nItem fits → take it");
        System.out.println("Total: value=50\n");

        int result4_tab = tabulation(values4, weights4, 1, maxWeight4);
        int result4_1d = oneDimensionalArrayUltimateSpaceOptimization(values4, weights4, 1, maxWeight4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_1d);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result4_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Fractional Choice ===");
        ArrayList<Integer> values5 = new ArrayList<>();
        values5.add(24);
        values5.add(18);
        values5.add(18);
        values5.add(10);
        ArrayList<Integer> weights5 = new ArrayList<>();
        weights5.add(24);
        weights5.add(10);
        weights5.add(10);
        weights5.add(7);
        int maxWeight5 = 25;

        System.out.println("Items:");
        for (int i = 0; i < values5.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values5.get(i) + ", weight=" + weights5.get(i));
        }
        System.out.println("Max Weight: " + maxWeight5);
        System.out.println("\nOptimal: Items 1, 2 (value=36, weight=20)");
        System.out.println("  or Items 1, 3 (value=28, weight=17)");
        System.out.println("  or Items 2, 3 (value=28, weight=17)\n");

        int result5_tab = tabulation(values5, weights5, 4, maxWeight5);
        int result5_1d = oneDimensionalArrayUltimateSpaceOptimization(values5, weights5, 4, maxWeight5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_1d);
        System.out.println("  Expected: 36");
        System.out.println("  Status: " + (result5_tab == 36 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Maximize value with weight constraint              ║");
        System.out.println("║  Each item: take it (1) or leave it (0) → 0/1 Knapsack       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    maxValue(idx, W) = max(notPick, pick)                     ║");
        System.out.println("║    notPick = maxValue(idx-1, W)                              ║");
        System.out.println("║    pick = value[idx] + maxValue(idx-1, W - weight[idx])      ║");
        System.out.println("║           (only if weight[idx] <= W)                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case:                                                  ║");
        System.out.println("║    if (idx == 0):                                            ║");
        System.out.println("║      return W >= weight[0] ? value[0] : 0                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][w] = max value using items 0..i with capacity w     ║");
        System.out.println("║    Base: dp[0][w] = value[0] if w >= weight[0], else 0       ║");
        System.out.println("║    Transition: dp[i][w] = max(dp[i-1][w],                    ║");
        System.out.println("║                              value[i] + dp[i-1][w-weight[i]])║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimizations:                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. Two 1D Arrays (prev[], curr[]):                          ║");
        System.out.println("║     Only need previous row to compute current                ║");
        System.out.println("║     Space: O(2W) = O(W)                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. Single 1D Array (prev[]):                                ║");
        System.out.println("║     Process weights RIGHT to LEFT (W to 0)                   ║");
        System.out.println("║     Prevents overwriting needed values                       ║");
        System.out.println("║     Space: O(W)                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Right-to-Left in 1D optimization?                       ║");
        System.out.println("║    prev[w-weight[i]] must be from previous iteration         ║");
        System.out.println("║    If we go left-to-right, we'd use updated values           ║");
        System.out.println("║    Going right-to-left preserves old values on the left      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × W) where n=items, W=max weight               ║");
        System.out.println("║    Space: O(W) optimized, O(n×W) tabulation                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
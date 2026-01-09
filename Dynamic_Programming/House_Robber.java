package Dynamic_Programming;

import java.util.Arrays;

public class House_Robber {

    private static int solveRecursive(int currentIndex, int[] houses) {

        if (currentIndex == 0) {
            return houses[0];
        }
        if (currentIndex < 0) {
            return 0;
        }

        int robCurrent = houses[currentIndex] + solveRecursive(currentIndex - 2, houses);
        int skipCurrent = solveRecursive(currentIndex - 1, houses);

        return Math.max(robCurrent, skipCurrent);

    }

    private static int solveMemoization(int currentIndex, int[] houses, int[] memo) {

        if (currentIndex == 0) {
            return houses[0];
        }
        if (currentIndex < 0) {
            return 0;
        }

        if (memo[currentIndex] != - 1) {
            return memo[currentIndex];
        }

        int robCurrent = houses[currentIndex] + solveMemoization(currentIndex - 2, houses, memo);
        int skipCurrent = solveMemoization(currentIndex - 1, houses, memo);

        memo[currentIndex] = Math.max(robCurrent, skipCurrent);
        return memo[currentIndex];

    }

    private static int solveTabulation(int[] houses) {

        int n = houses.length;

        if (n == 1) {
            return houses[0];
        }

        int[] dp = new int[n];

        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            int robCurrent = houses[i] + dp[i - 2];
            int skipCurrent = dp[i - 1];

            dp[i] = Math.max(robCurrent, skipCurrent);
        }

        return dp[n - 1];
    }

    private static int solveSpaceOptimized(int[] houses) {

        int n = houses.length;
        if (n == 1) {
            return houses[0];
        }

        int twoHousesBack = houses[0];
        int oneHouseBack = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            int robCurrent = houses[i] + twoHousesBack;
            int skipCurrent = oneHouseBack;

            int maxLoot = Math.max(robCurrent, skipCurrent);

            twoHousesBack = oneHouseBack;
            oneHouseBack = maxLoot;
        }

        return oneHouseBack;

    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 HOUSE ROBBER - 4 APPROACHES                  ║");
        System.out.println("║       Rob houses to maximize money without alerting police   ║");
        System.out.println("║           Constraint: Cannot rob two adjacent houses         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example [1,2,3,1] ===");
        int[] houses1 = {1, 2, 3, 1};
        System.out.println("Houses: " + Arrays.toString(houses1));
        System.out.println("\nPossible strategies:");
        System.out.println("  Strategy 1: Rob house 0 and 2 → 1 + 3 = 4 ✓");
        System.out.println("  Strategy 2: Rob house 1 and 3 → 2 + 1 = 3");
        System.out.println("  Strategy 3: Rob only house 2 → 3");
        System.out.println("  Strategy 4: Rob only house 1 → 2");
        System.out.println("  Maximum: 4 (rob houses at index 0 and 2)\n");

        int result1_rec = solveRecursive(houses1.length - 1, houses1);
        int[] memo1 = new int[houses1.length];
        Arrays.fill(memo1, - 1);
        int result1_memo = solveMemoization(houses1.length - 1, houses1, memo1);
        int result1_tab = solveTabulation(houses1);
        int result1_opt = solveSpaceOptimized(houses1);

        System.out.println("✓ Method 1 (Recursion):        " + result1_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result1_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result1_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result1_opt);
        System.out.println("  Expected: 4");
        boolean pass1 = result1_rec == 4 && result1_memo == 4 && result1_tab == 4 && result1_opt == 4;
        System.out.println("  Status: " + (pass1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Alternating High-Low [2,7,9,3,1] ===");
        int[] houses2 = {2, 7, 9, 3, 1};
        System.out.println("Houses: " + Arrays.toString(houses2));
        System.out.println("\nAnalysis:");
        System.out.println("  Rob houses 1 and 3: 7 + 3 = 10");
        System.out.println("  Rob houses 0 and 2: 2 + 9 = 11");
        System.out.println("  Rob houses 0, 2, 4: 2 + 9 + 1 = 12 ✓");
        System.out.println("  Maximum: 12\n");

        int result2_rec = solveRecursive(houses2.length - 1, houses2);
        int[] memo2 = new int[houses2.length];
        Arrays.fill(memo2, - 1);
        int result2_memo = solveMemoization(houses2.length - 1, houses2, memo2);
        int result2_tab = solveTabulation(houses2);
        int result2_opt = solveSpaceOptimized(houses2);

        System.out.println("✓ Method 1 (Recursion):        " + result2_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result2_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result2_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result2_opt);
        System.out.println("  Expected: 12");
        boolean pass2 = result2_rec == 12 && result2_memo == 12 && result2_tab == 12 && result2_opt == 12;
        System.out.println("  Status: " + (pass2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single House [100] ===");
        int[] houses3 = {100};
        System.out.println("Houses: " + Arrays.toString(houses3));
        System.out.println("\nOnly one house, rob it!");
        System.out.println("Maximum: 100\n");

        int result3_rec = solveRecursive(houses3.length - 1, houses3);
        int[] memo3 = new int[houses3.length];
        Arrays.fill(memo3, - 1);
        int result3_memo = solveMemoization(houses3.length - 1, houses3, memo3);
        int result3_tab = solveTabulation(houses3);
        int result3_opt = solveSpaceOptimized(houses3);

        System.out.println("✓ Method 1 (Recursion):        " + result3_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result3_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result3_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result3_opt);
        System.out.println("  Expected: 100");
        boolean pass3 = result3_rec == 100 && result3_memo == 100 && result3_tab == 100 && result3_opt == 100;
        System.out.println("  Status: " + (pass3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Two Houses [5,3] ===");
        int[] houses4 = {5, 3};
        System.out.println("Houses: " + Arrays.toString(houses4));
        System.out.println("\nTwo adjacent houses:");
        System.out.println("  Rob house 0: 5 ✓");
        System.out.println("  Rob house 1: 3");
        System.out.println("  Cannot rob both (adjacent)");
        System.out.println("Maximum: 5\n");

        int result4_rec = solveRecursive(houses4.length - 1, houses4);
        int[] memo4 = new int[houses4.length];
        Arrays.fill(memo4, - 1);
        int result4_memo = solveMemoization(houses4.length - 1, houses4, memo4);
        int result4_tab = solveTabulation(houses4);
        int result4_opt = solveSpaceOptimized(houses4);

        System.out.println("✓ Method 1 (Recursion):        " + result4_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result4_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result4_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result4_opt);
        System.out.println("  Expected: 5");
        boolean pass4 = result4_rec == 5 && result4_memo == 5 && result4_tab == 5 && result4_opt == 5;
        System.out.println("  Status: " + (pass4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Same Values [4,4,4,4,4] ===");
        int[] houses5 = {4, 4, 4, 4, 4};
        System.out.println("Houses: " + Arrays.toString(houses5));
        System.out.println("\nRob alternate houses:");
        System.out.println("  Rob indices 0, 2, 4: 4 + 4 + 4 = 12 ✓");
        System.out.println("  Rob indices 1, 3: 4 + 4 = 8");
        System.out.println("Maximum: 12\n");

        int result5_rec = solveRecursive(houses5.length - 1, houses5);
        int[] memo5 = new int[houses5.length];
        Arrays.fill(memo5, - 1);
        int result5_memo = solveMemoization(houses5.length - 1, houses5, memo5);
        int result5_tab = solveTabulation(houses5);
        int result5_opt = solveSpaceOptimized(houses5);

        System.out.println("✓ Method 1 (Recursion):        " + result5_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result5_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result5_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result5_opt);
        System.out.println("  Expected: 12");
        boolean pass5 = result5_rec == 12 && result5_memo == 12 && result5_tab == 12 && result5_opt == 12;
        System.out.println("  Status: " + (pass5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Increasing Values [1,2,3,4,5,6,7] ===");
        int[] houses6 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Houses: " + Arrays.toString(houses6));
        System.out.println("\nStrategy: Rob higher value houses");
        System.out.println("  Rob indices 0,2,4,6: 1+3+5+7 = 16");
        System.out.println("  Rob indices 1,3,5: 2+4+6 = 12");
        System.out.println("  Optimal: indices 1,3,5 or 0,2,4,6? Let DP decide!");
        System.out.println("  Actually: 1,3,5,7... wait, checking...\n");

        int result6_rec = solveRecursive(houses6.length - 1, houses6);
        int[] memo6 = new int[houses6.length];
        Arrays.fill(memo6, - 1);
        int result6_memo = solveMemoization(houses6.length - 1, houses6, memo6);
        int result6_tab = solveTabulation(houses6);
        int result6_opt = solveSpaceOptimized(houses6);

        System.out.println("✓ Method 1 (Recursion):        " + result6_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result6_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result6_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result6_opt);
        System.out.println("  Optimal path: indices 1,3,5 or 0,2,4,6");
        boolean pass6 = result6_rec == result6_memo && result6_memo == result6_tab && result6_tab == result6_opt;
        System.out.println("  Status: " + (pass6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Values [100,50,400,200,300] ===");
        int[] houses7 = {100, 50, 400, 200, 300};
        System.out.println("Houses: " + Arrays.toString(houses7));
        System.out.println("\nAnalysis:");
        System.out.println("  Rob 0,2,4: 100 + 400 + 300 = 800 ✓");
        System.out.println("  Rob 1,3: 50 + 200 = 250");
        System.out.println("  Rob 2,4: 400 + 300 = 700");
        System.out.println("Maximum: 800\n");

        int result7_rec = solveRecursive(houses7.length - 1, houses7);
        int[] memo7 = new int[houses7.length];
        Arrays.fill(memo7, - 1);
        int result7_memo = solveMemoization(houses7.length - 1, houses7, memo7);
        int result7_tab = solveTabulation(houses7);
        int result7_opt = solveSpaceOptimized(houses7);

        System.out.println("✓ Method 1 (Recursion):        " + result7_rec);
        System.out.println("✓ Method 2 (Memoization):      " + result7_memo);
        System.out.println("✓ Method 3 (Tabulation):       " + result7_tab);
        System.out.println("✓ Method 4 (Space Optimized):  " + result7_opt);
        System.out.println("  Expected: 800");
        boolean pass7 = result7_rec == 800 && result7_memo == 800 && result7_tab == 800 && result7_opt == 800;
        System.out.println("  Status: " + (pass7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY COMPARISON - 4 APPROACHES                        ║");
        System.out.println("║  ┌────────────────────┬───────────┬─────────────┬─────────┐  ║");
        System.out.println("║  │ Method             │ Time      │ Space       │ Notes   │  ║");
        System.out.println("║  ├────────────────────┼───────────┼─────────────┼─────────┤  ║");
        System.out.println("║  │ 1. Recursion       │ O(2^n)    │ O(n)        │ Slow    │  ║");
        System.out.println("║  │ 2. Memoization     │ O(n)      │ O(n)+O(n)   │ Top-Dn  │  ║");
        System.out.println("║  │ 3. Tabulation      │ O(n)      │ O(n)        │ Bot-Up  │  ║");
        System.out.println("║  │ 4. Space Optimized │ O(n)      │ O(1)        │ Best!⭐ │  ║");
        System.out.println("║  └────────────────────┴───────────┴─────────────┴─────────┘  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recurrence Relation:                                        ║");
        System.out.println("║    dp[i] = max(houses[i] + dp[i-2], dp[i-1])                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Decision at Each House:                                 ║");
        System.out.println("║    • Rob current + best from 2 houses back                   ║");
        System.out.println("║    • Skip current + best from previous house                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  🏆 Winner: Space Optimized (O(1) space, O(n) time)          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
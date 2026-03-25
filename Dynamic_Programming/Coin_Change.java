package Dynamic_Programming;

public class Coin_Change {

    private static int recursive(int[] coins, int idx, int amount) {
        if (idx == 0) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return (int) 1e9;
            }
        }

        int notPick = recursive(coins, idx - 1, amount);
        int pick = Integer.MAX_VALUE;

        if (coins[idx] <= amount) {
            pick = 1 + recursive(coins, idx, amount - coins[idx]);
        }

        return Math.min(pick, notPick);
    }

    private static int memoization(int[] coins, int idx, int amount, int[][] dp) {
        if (idx == 0) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return (int) 1e9;
            }
        }

        if (dp[idx][amount] != - 1) {
            return dp[idx][amount];
        }

        int notPick = memoization(coins, idx - 1, amount, dp);
        int pick = Integer.MAX_VALUE;

        if (coins[idx] <= amount) {
            pick = 1 + memoization(coins, idx, amount - coins[idx], dp);
        }

        return dp[idx][amount] = Math.min(pick, notPick);
    }

    private static int tabulation(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int a = 0; a <= amount; a++) {
            if (a % coins[0] == 0) {
                dp[0][a] = a / coins[0];
            } else {
                dp[0][a] = (int) 1e9;
            }
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {

                int notPick = dp[idx - 1][target];
                int pick = Integer.MAX_VALUE;

                if (coins[idx] <= target) {
                    pick = 1 + dp[idx][target - coins[0]];
                }

                dp[idx][target] = Math.min(pick, notPick);
            }
        }

        return dp[n - 1][amount];

    }

    private static int ultimateSpaceOptimization(int[] coins, int amount) {

        int n = coins.length;
        int[] prev = new int[amount + 1];

        for (int a = 0; a <= amount; a++) {
            if (a % coins[0] == 0) {
                prev[a] = a / coins[0];
            } else {
                prev[a] = (int) 1e9;
            }
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[amount + 1];
            for (int target = 0; target <= amount; target++) {

                int notPick = prev[target];
                int pick = (int) 1e9;

                if (coins[idx] <= target) {
                    pick = 1 + curr[target - coins[idx]];
                }

                curr[target] = Math.min(pick, notPick);
            }
            prev = curr;
        }

        return prev[amount];

    }

    private static String arrayToString(int[] arr) {

        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    COIN CHANGE                               ║");
        System.out.println("║  Find minimum number of coins needed to make given amount    ║");
        System.out.println("║  Unlimited supply of each coin denomination                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Standard Example ===");
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Coins: " + arrayToString(coins1));
        System.out.println("Amount: " + amount1);
        System.out.println("\nOptimal solution:");
        System.out.println("  5 + 5 + 1 = 11 (3 coins)");
        System.out.println("  Alternative: 5 + 2 + 2 + 2 = 11 (4 coins)");
        System.out.println("  Minimum: 3 coins\n");

        int result1_rec = recursive(coins1, coins1.length - 1, amount1);

        int[][] dp1 = new int[coins1.length][amount1 + 1];
        for (int i = 0; i < coins1.length; i++) {
            for (int j = 0; j <= amount1; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(coins1, coins1.length - 1, amount1, dp1);

        int result1_tab = tabulation(coins1, amount1);
        int result1_opt = ultimateSpaceOptimization(coins1, amount1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1_tab == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Coin Type ===");
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Coins: " + arrayToString(coins2));
        System.out.println("Amount: " + amount2);
        System.out.println("\nCannot make 3 with only coin of value 2");
        System.out.println("Result: -1 (impossible)\n");

        int result2_tab = tabulation(coins2, amount2);
        int result2_opt = ultimateSpaceOptimization(coins2, amount2);

        System.out.println("✓ Tabulation Result: " + (result2_tab >= 1e9 ? - 1 : result2_tab));
        System.out.println("✓ Space Optimized Result: " + (result2_opt >= 1e9 ? - 1 : result2_opt));
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result2_tab >= 1e9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Amount Zero ===");
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        System.out.println("Coins: " + arrayToString(coins3));
        System.out.println("Amount: " + amount3);
        System.out.println("\nNo coins needed for amount 0\n");

        int result3_tab = tabulation(coins3, amount3);
        int result3_opt = ultimateSpaceOptimization(coins3, amount3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Exact Fit ===");
        int[] coins4 = {1, 2, 5};
        int amount4 = 5;
        System.out.println("Coins: " + arrayToString(coins4));
        System.out.println("Amount: " + amount4);
        System.out.println("\nExact match with single coin of value 5\n");

        int result4_tab = tabulation(coins4, amount4);
        int result4_opt = ultimateSpaceOptimization(coins4, amount4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Only Ones ===");
        int[] coins5 = {1};
        int amount5 = 7;
        System.out.println("Coins: " + arrayToString(coins5));
        System.out.println("Amount: " + amount5);
        System.out.println("\nNeed 7 coins of value 1\n");

        int result5_tab = tabulation(coins5, amount5);
        int result5_opt = ultimateSpaceOptimization(coins5, amount5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result5_tab == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Large Denominations ===");
        int[] coins6 = {5, 10, 25};
        int amount6 = 30;
        System.out.println("Coins: " + arrayToString(coins6));
        System.out.println("Amount: " + amount6);
        System.out.println("\nOptimal: 25 + 5 = 30 (2 coins)");
        System.out.println("Alternative: 10 + 10 + 10 = 30 (3 coins)\n");

        int result6_tab = tabulation(coins6, amount6);
        int result6_opt = ultimateSpaceOptimization(coins6, amount6);

        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("✓ Space Optimized Result: " + result6_opt);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result6_tab == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Minimum coins to make amount (unbounded knapsack)  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Difference from 0/1 Knapsack:                           ║");
        System.out.println("║    Unlimited supply → can use same coin multiple times       ║");
        System.out.println("║    After picking coin[i], stay at index i (not i-1)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    minCoins(idx, amount) = min(notPick, pick)                ║");
        System.out.println("║    notPick = minCoins(idx-1, amount)                         ║");
        System.out.println("║    pick = 1 + minCoins(idx, amount - coins[idx])             ║");
        System.out.println("║           ^^^ stay at idx (can reuse same coin)              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case:                                                  ║");
        System.out.println("║    if (idx == 0):                                            ║");
        System.out.println("║      if (amount % coins[0] == 0):                            ║");
        System.out.println("║        return amount / coins[0]                              ║");
        System.out.println("║      else:                                                   ║");
        System.out.println("║        return infinity (impossible)                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][a] = min coins using coins[0..i] to make amount a   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base initialization:                                        ║");
        System.out.println("║    dp[0][a] = a / coins[0] if divisible, else infinity       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Transition (CRITICAL):                                      ║");
        System.out.println("║    dp[i][a] = min(dp[i-1][a],                                ║");
        System.out.println("║                   1 + dp[i][a - coins[i]])                   ║");
        System.out.println("║                         ^^^ same row i, not i-1!             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use prev[] and curr[] arrays                              ║");
        System.out.println("║    When picking: curr[a] = 1 + curr[a - coins[i]]            ║");
        System.out.println("║    Use curr (same row) for pick, prev for notPick            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Handling Impossible Cases:                                  ║");
        System.out.println("║    Use large value (1e9) to represent impossible             ║");
        System.out.println("║    Final answer: if result >= 1e9, return -1                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × amount) where n = number of coin types       ║");
        System.out.println("║    Space: O(amount) optimized, O(n × amount) tabulation      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
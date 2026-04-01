package Dynamic_Programming;

public class Coin_Change_II {

    private static int recursive(int[] coins, int idx, int amount) {

        if (amount == 0) {
            return 1;
        }

        if (idx == 0) {
            return (amount % coins[0] == 0) ? 1 : 0;
        }

        int notPick = recursive(coins, idx - 1, amount);
        int pick = 0;

        if (amount >= coins[idx]) {
            pick = recursive(coins, idx, amount - coins[idx]);
        }

        return notPick + pick;
    }

    private static int memoization(int[] coins, int idx, int amount, int[][] dp) {

        if (amount == 0) {
            return 1;
        }

        if (idx == 0) {
            return (amount % coins[0] == 0) ? 1 : 0;
        }

        if (dp[idx][amount] != - 1) {
            return dp[idx][amount];
        }

        int notPick = memoization(coins, idx - 1, amount, dp);
        int pick = 0;

        if (amount >= coins[idx]) {
            pick = memoization(coins, idx, amount - coins[idx], dp);
        }

        return dp[idx][amount] = notPick + pick;
    }

    private static int tabulation(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int t = 0; t <= amount; t++) {
            dp[0][t] = (t % coins[0] == 0) ? 1 : 0;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {
                int notPick = dp[idx - 1][target];
                int pick = 0;

                if (target >= coins[idx]) {
                    pick = dp[idx][target - coins[idx]];
                }

                dp[idx][target] = notPick + pick;
            }
        }

        return dp[n - 1][amount];
    }

    private static int ultimateSpaceOptimization(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount + 1];

        for (int t = 0; t <= amount; t++) {
            prev[t] = (t % coins[0] == 0) ? 1 : 0;
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[amount + 1];

            for (int target = 0; target <= amount; target++) {
                int notPick = prev[target];
                int pick = 0;

                if (target >= coins[idx]) {
                    pick = curr[target - coins[idx]];
                }

                curr[target] = notPick + pick;
            }
            prev = curr;
        }

        return prev[amount];

    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  COIN CHANGE II                              ║");
        System.out.println("║  Count number of ways to make amount with given coins        ║");
        System.out.println("║  Unlimited supply of each coin denomination                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Coins: " + arrayToString(coins1));
        System.out.println("Amount: " + amount1);
        System.out.println("\nPossible combinations:");
        System.out.println("  5 = 5 (1 way)");
        System.out.println("  5 = 2 + 2 + 1 (1 way)");
        System.out.println("  5 = 2 + 1 + 1 + 1 (1 way)");
        System.out.println("  5 = 1 + 1 + 1 + 1 + 1 (1 way)");
        System.out.println("\nTotal: 4 ways\n");

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
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result1_tab == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: No Solution ===");
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Coins: " + arrayToString(coins2));
        System.out.println("Amount: " + amount2);
        System.out.println("\nCannot make 3 with only coin of value 2");
        System.out.println("Ways: 0\n");

        int result2_tab = tabulation(coins2, amount2);
        int result2_opt = ultimateSpaceOptimization(coins2, amount2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Amount Zero ===");
        int[] coins3 = {1, 2, 5};
        int amount3 = 0;
        System.out.println("Coins: " + arrayToString(coins3));
        System.out.println("Amount: " + amount3);
        System.out.println("\nOne way to make 0: use no coins");
        System.out.println("Ways: 1\n");

        int result3_tab = tabulation(coins3, amount3);
        int result3_opt = ultimateSpaceOptimization(coins3, amount3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result3_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Coin Type ===");
        int[] coins4 = {2};
        int amount4 = 10;
        System.out.println("Coins: " + arrayToString(coins4));
        System.out.println("Amount: " + amount4);
        System.out.println("\nOnly way: 2+2+2+2+2 = 10");
        System.out.println("Ways: 1\n");

        int result4_tab = tabulation(coins4, amount4);
        int result4_opt = ultimateSpaceOptimization(coins4, amount4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Multiple Ways ===");
        int[] coins5 = {1, 2, 5};
        int amount5 = 11;
        System.out.println("Coins: " + arrayToString(coins5));
        System.out.println("Amount: " + amount5);
        System.out.println("\nSome combinations:");
        System.out.println("  5+5+1, 5+2+2+2, 5+2+2+1+1, ...");
        System.out.println("  Many ways possible\n");

        int result5_tab = tabulation(coins5, amount5);
        int result5_opt = ultimateSpaceOptimization(coins5, amount5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 6: All Ones ===");
        int[] coins6 = {1};
        int amount6 = 5;
        System.out.println("Coins: " + arrayToString(coins6));
        System.out.println("Amount: " + amount6);
        System.out.println("\nOnly way: 1+1+1+1+1 = 5");
        System.out.println("Ways: 1\n");

        int result6_tab = tabulation(coins6, amount6);
        int result6_opt = ultimateSpaceOptimization(coins6, amount6);

        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("✓ Space Optimized Result: " + result6_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result6_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Count ways to make amount (unbounded knapsack)     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Difference from Coin Change I:                              ║");
        System.out.println("║    Coin Change I: Find MINIMUM coins                         ║");
        System.out.println("║    Coin Change II: COUNT all ways                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    ways(idx, amount) = notPick + pick                        ║");
        System.out.println("║    notPick = ways(idx-1, amount)                             ║");
        System.out.println("║    pick = ways(idx, amount - coins[idx])                     ║");
        System.out.println("║           ^^^ stay at idx (unbounded)                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Cases:                                                 ║");
        System.out.println("║    if (amount == 0): return 1 (one way: use no coins)        ║");
        System.out.println("║    if (idx == 0):                                            ║");
        System.out.println("║      return (amount % coins[0] == 0) ? 1 : 0                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][a] = ways to make amount a using coins[0..i]        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base initialization:                                        ║");
        System.out.println("║    dp[0][a] = (a % coins[0] == 0) ? 1 : 0                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Transition:                                                 ║");
        System.out.println("║    dp[i][a] = dp[i-1][a] + dp[i][a - coins[i]]               ║");
        System.out.println("║               (not pick)    (pick, stay at row i)            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: coins=[1,2,5], amount=5                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    dp table (simplified):                                    ║");
        System.out.println("║         0  1  2  3  4  5                                     ║");
        System.out.println("║    [1]  1  1  1  1  1  1  (all 1s with coin=1)               ║");
        System.out.println("║    [2]  1  1  2  2  3  3  (add coin=2)                       ║");
        System.out.println("║    [5]  1  1  2  2  3  4  (add coin=5)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    dp[2][5] = dp[1][5] + dp[2][0]                            ║");
        System.out.println("║             = 3 + 1 = 4 ways                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use prev[] and curr[] arrays                              ║");
        System.out.println("║    When picking: curr[a] = prev[a] + curr[a - coins[i]]      ║");
        System.out.println("║    Use curr (same row) for pick, prev for notPick            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Difference from 0/1 Knapsack:                           ║");
        System.out.println("║    After picking coin[i], stay at index i (not i-1)          ║");
        System.out.println("║    This allows unlimited reuse of same coin                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × amount) where n = number of coin types       ║");
        System.out.println("║    Space: O(amount) optimized, O(n × amount) tabulation      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
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

}
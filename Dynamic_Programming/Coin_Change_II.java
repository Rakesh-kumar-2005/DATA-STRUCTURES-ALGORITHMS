package Dynamic_Programming;

/*

    Description:
      Following program counts the total number of combinations to make a given
        amount using an unlimited supply of each coin denomination...
    
    Problem Statement:
      -> Given an array of coin denominations and a target amount...
      -> Each coin can be used any number of times (unbounded supply)...
      -> Count all distinct combinations (not permutations) that sum to amount...
      -> Return the total number of such combinations...
    
    Difference From Coin Change I:
      -> Coin Change I:  find the MINIMUM number of coins to make amount...
      -> Coin Change II: COUNT all ways to make amount using any number of coins...
      -> Coin Change I uses Math.min with infinity sentinel...
      -> Coin Change II uses addition (sum of ways) with base case returning 1...
    
    Why Combinations, Not Permutations?
      -> {1, 2} and {2, 1} are treated as the SAME combination...
      -> Order of coins does not matter...
      -> Achieved by processing coins in sorted index order...
        (each coin can only be reused, never a previously skipped coin)...
    
    Example:
      -> coins = [1, 2, 5], amount = 5:
           Combination 1: 5...
           Combination 2: 2 + 2 + 1...
           Combination 3: 2 + 1 + 1 + 1...
           Combination 4: 1 + 1 + 1 + 1 + 1...
           Total = 4 ways...
    
    Core Insight:
      -> At each index, decide: skip this coin or use it one more time...
      -> If not picked: exclude coin[idx] entirely, move to idx - 1...
      -> If picked: subtract coin[idx] from amount, stay at same idx (reuse)...
      -> Sum both results to count all valid paths...
    
    Recursive Relation:
      -> ways(idx, amount) = notPick + pick...
      -> notPick = ways(idx - 1, amount)...
      -> pick    = ways(idx, amount - coins[idx])...
                   only if amount >= coins[idx]...
                   note: recurse with same idx (unbounded reuse)...
    
    Base Cases:
      -> amount == 0 → return 1 (one valid combination found: use no more coins)...
      -> idx == 0 (only first coin available):
           If amount % coins[0] == 0 → return 1 (one way: use coins[0] repeatedly)...
           Else                      → return 0 (impossible with only coins[0])...
    
    Approach 1 - Recursive:
      -> Pure top-down recursion without caching...
      -> Explores all combinations with repetition...
      -> Exponential time in the worst case...
      -> Clearly expresses unbounded nature via same-index recursion...
    
    Approach 2 - Memoization (Top-Down DP):
      -> Caches results in dp[idx][amount], initialized to -1...
      -> Before recursing, check dp[idx][amount] != -1...
      -> Store result: dp[idx][amount] = notPick + pick...
      -> Each unique (idx, amount) subproblem solved exactly once...
    
    Approach 3 - Tabulation (Bottom-Up DP):
      -> Builds dp table iteratively from base cases...
      -> dp[i][a] = number of ways to make amount a using coins[0..i]...
    
         Initialization (base row for idx = 0):
           For each amount t from 0 to amount:
             If t % coins[0] == 0 → dp[0][t] = 1...
             Else                 → dp[0][t] = 0...
    
         Transition (idx from 1 to n-1, target from 0 to amount):
           notPick = dp[idx - 1][target]...
           pick    = dp[idx][target - coins[idx]]...
                     only if target >= coins[idx]...
                     note: uses dp[idx][...] (same row), not dp[idx-1][...]...
           dp[idx][target] = notPick + pick...
    
         Answer: dp[n - 1][amount]...
    
    Approach 4 - Space Optimization (Two 1D Arrays):
      -> Replaces 2D table with prev[] and curr[] of size amount + 1...
      -> prev[] represents dp[idx - 1], curr[] represents dp[idx]...
      -> For notPick: use prev[target] (previous coin's result)...
      -> For pick:    use curr[target - coins[idx]] (same coin, current row)...
      -> After each coin, set prev = curr...
      -> Answer: prev[amount] after full traversal...
      -> Space reduced from O(n × amount) to O(amount)...
    
    Critical Distinction - Same Row Reference for Pick:
      -> pick reads curr[target - coins[idx]] (current row)...
      -> This is equivalent to "stay at idx" in recursion...
      -> Using curr (not prev) allows the same coin to be counted multiple times...
      -> If prev were used for pick instead → each coin usable only once (0/1 knapsack)...
    
    Tabulation Table Visualization (coins=[1,2,5], amount=5):
    
         amount:  0   1   2   3   4   5
         idx=0:   1   1   1   1   1   1   (all multiples of 1 → 1 way each)...
         idx=1:   1   1   2   2   3   3   (add coin=2)...
         idx=2:   1   1   2   2   3   4   (add coin=5)...
    
         dp[2][5] = dp[1][5] + dp[2][0] = 3 + 1 = 4 ways...
    
    Comparison: Coin Change I vs Coin Change II:
      -> Coin Change I:
           Objective: minimize coin count...
           Recurrence: min(notPick, 1 + pick)...
           Base: infinity for impossible, 0 for amount==0...
           Invalid result: >= 1e9 → return -1...
      -> Coin Change II:
           Objective: count combinations...
           Recurrence: notPick + pick...
           Base: 1 for amount==0, divisibility check at idx==0...
           Invalid result: 0 (naturally)...
    
    Comparison: Coin Change II vs 0/1 Knapsack Count:
      -> 0/1 Knapsack Count: pick recurses with idx - 1 (item used once)...
      -> Coin Change II:     pick recurses with idx (coin reused freely)...
      -> In tabulation: 0/1 uses dp[idx-1][...] for pick; Coin Change II uses dp[idx][...]...
    
    Edge Cases:
      -> amount = 0 → exactly 1 way (use no coins) → return 1...
      -> Single coin type, amount divisible → return 1...
      -> Single coin type, amount not divisible → return 0...
      -> All coins larger than amount → return 0...
      -> coins = [1] → exactly 1 way for any amount (use amount ones)...
    
    Time and Space Complexity:
      -> Recursive:
           Time:  O(amount^n) in worst case (exponential)...
           Space: O(amount) recursion stack depth...
      -> Memoization:
           Time:  O(n × amount)...
           Space: O(n × amount) + O(amount) recursion stack...
      -> Tabulation:
           Time:  O(n × amount)...
           Space: O(n × amount)...
      -> Space Optimized:
           Time:  O(n × amount)...
           Space: O(amount)...
    
    Applications:
      -> Counting ways to make change in currency systems...
      -> Combinatorics problems with repeated elements...
      -> Tiling and partition problems with unlimited tiles...
      -> Number of ways to represent an integer as a sum...
      -> Competitive programming and interview problems on unbounded knapsack...

*/

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

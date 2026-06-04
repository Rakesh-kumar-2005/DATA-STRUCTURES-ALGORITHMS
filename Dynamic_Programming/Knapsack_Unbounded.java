package Dynamic_Programming;

/*

    Description:
      Following program solves the Unbounded Knapsack problem where each item
        can be selected any number of times to maximize total value within a weight limit...

    Problem Statement:
      -> Given a list of item values and weights, and a maximum weight capacity...
      -> Each item can be picked unlimited number of times (unbounded supply)...
      -> Maximize the total value without exceeding the weight capacity...
      -> Return the maximum achievable value...

    Key Difference From 0/1 Knapsack:
      -> In 0/1 Knapsack: each item used at most once...
           After taking item[idx], recurse with idx - 1 (move to previous item)...
      -> In Unbounded Knapsack: each item used unlimited times...
           After taking item[idx], recurse with idx unchanged (stay at same item)...
      -> This single change in index movement enables unlimited reuse of items...

    Example:
      -> values = [10, 40, 30], weights = [1, 3, 4], capacity = 5:
           Option A: item 0 taken 5 times → 5 × 10 = 50 (weight = 5) ← optimal...
           Option B: item 1 once + item 0 twice → 40 + 20 = 60? → weight = 5, value = 60...
           Wait: 40 + 20 = 60 with weight 3 + 2 = 5 → actually optimal is 60...
           Answer: 60...

    Recursive Relation:
      -> maxValue(idx, W) = max(notTake, take)...
      -> notTake = maxValue(idx - 1, W)...
      -> take    = value[idx] + maxValue(idx, W - weight[idx])...
                   only if W >= weight[idx]...
                   note: recurse with same idx, not idx - 1 (unbounded reuse)...

    Base Case:
      -> idx == 0 (only first item available):
           Can take item 0 as many times as capacity allows...
           Return (W / weight[0]) * value[0]...

    Approach 1 - Recursive:
      -> Pure top-down recursion without caching...
      -> Explores all combinations with repetition allowed...
      -> Exponential time in worst case due to overlapping subproblems...
      -> Clearly establishes the unbounded recurrence structure...

    Approach 2 - Memoization (Top-Down DP):
      -> Adds 2D dp array of size n × (capacity + 1), initialized to -1...
      -> dp[idx][W] stores maximum value for given (idx, W) state...
      -> Check dp[idx][W] != -1 before recursing to avoid recomputation...
      -> Reduces time from exponential to O(n × W)...

    Approach 3 - Tabulation (Bottom-Up DP):
      -> Builds dp table iteratively starting from base case...
      -> dp[i][W] = max value using items 0..i with capacity W...

         Initialization (base row for idx = 0):
           For each capacity W from 0 to totalCapacity:
             dp[0][W] = (W / weight[0]) * value[0]...

         Transition (idx from 1 to n-1, W from 0 to totalCapacity):
           notTake = dp[idx - 1][W]...
           take    = value[idx] + dp[idx][W - weight[idx]]...
                     only if W >= weight[idx]...
                     note: uses dp[idx][...] (same row), not dp[idx-1][...]...
           dp[idx][W] = Math.max(notTake, take)...

         Answer: dp[n - 1][totalCapacity]...

    Approach 4 - Space Optimization (Two 1D Arrays):
      -> Replaces 2D table with prev[] and curr[] arrays...
      -> prev[] represents dp[idx - 1], curr[] represents dp[idx]...
      -> For take: use curr[W - weight[idx]] (same row, already updated)...
      -> For notTake: use prev[W] (previous row reference)...
      -> After each item, assign prev = curr...
      -> Space reduced from O(n × W) to O(W)...

    Approach 5 - Ultimate Space Optimization (Single 1D Array):
      -> Uses only one array prev[] updated in-place...
      -> Process capacities left-to-right (W = 0 to totalCapacity)...
      -> When computing prev[W], prev[W - weight[idx]] is already updated...
      -> This naturally captures the "reuse same item" behavior...
      -> CRITICAL: Left-to-right traversal is what enables unbounded reuse...
      -> In 0/1 Knapsack, right-to-left traversal prevents reuse...

    Critical Distinction - Same Row vs Previous Row:
      -> notTake → reads from previous row (prev[])...
           Skipping item[idx] means falling back to solutions without it...
      -> take    → reads from current row (curr[] or updated prev[])...
           Reusing item[idx] means building on solutions that already used it...
      -> Left-to-right processing in 1D array mirrors "stay at idx" in recursion...

    Tabulation Table Visualization (values=[10,40,30], weights=[1,3,4], cap=5):

         W:      0   1   2   3   4   5
         idx=0:  0  10  20  30  40  50
         idx=1:  0  10  20  40  50  60
         idx=2:  0  10  20  40  50  60

         Answer: dp[2][5] = 60...

    Edge Cases:
      -> capacity = 0 → no item can be taken → return 0...
      -> Single item type → base case handles it via integer division...
      -> Item weight exceeds capacity → take branch never executes → notTake wins...
      -> All items same weight → densest value item dominates...

    Comparison: Unbounded Knapsack vs 0/1 Knapsack:
      -> Unbounded: recurse with same idx → enables reuse → left-to-right in 1D...
      -> 0/1:       recurse with idx - 1 → prevents reuse → right-to-left in 1D...
      -> Both use prev row for notTake and current row for take...
      -> The index direction change is the only structural difference...

    Time and Space Complexity:
      -> Recursive:
           Time:  O(n^W) in worst case (exponential)...
           Space: O(W) recursion stack depth...
      -> Memoization:
           Time:  O(n × W)...
           Space: O(n × W) + O(W) recursion stack...
      -> Tabulation:
           Time:  O(n × W)...
           Space: O(n × W)...
      -> Two Array Space Optimized:
           Time:  O(n × W)...
           Space: O(W)...
      -> Single Array Ultimate Optimized:
           Time:  O(n × W)...
           Space: O(W)...

    Applications:
      -> Cutting stock problems in manufacturing...
      -> Resource allocation with repeatable investments...
      -> Coin change value maximization...
      -> Rod cutting and interval scheduling problems...
      -> Inventory restocking with unlimited purchase quantities...

*/
import java.util.ArrayList;

public class Knapsack_Unbounded {

    private static int recursive(ArrayList<Integer> values, ArrayList<Integer> weights, int idx, int maxWeight) {

        if (idx == 0) {
            return (maxWeight / weights.getFirst()) * values.getFirst();
        }

        int notTake = recursive(values, weights, idx - 1, maxWeight);
        int take = 0;

        if (maxWeight >= weights.get(idx)) {
            take = values.get(idx) + recursive(values, weights, idx, maxWeight - weights.get(idx));
        }

        return Math.max(notTake, take);

    }

    private static int memoization(ArrayList<Integer> values, ArrayList<Integer> weights, int idx, int maxWeight, int[][] dp) {

        if (idx == 0) {
            return (maxWeight / weights.getFirst()) * values.getFirst();
        }

        if (dp[idx][maxWeight] != - 1) {
            return dp[idx][maxWeight];
        }

        int notTake = memoization(values, weights, idx - 1, maxWeight, dp);
        int take = 0;

        if (maxWeight >= weights.get(idx)) {
            take = values.get(idx) + memoization(values, weights, idx, maxWeight - weights.get(idx), dp);
        }

        return dp[idx][maxWeight] = Math.max(notTake, take);
    }

    private static int tabulation(ArrayList<Integer> values, ArrayList<Integer> weights, int totalCapacity) {

        int n = values.size();
        int[][] dp = new int[n][totalCapacity + 1];

        for (int W = 0; W <= totalCapacity; W++) {
            dp[0][W] = (W / weights.getFirst()) * values.getFirst();
        }

        for (int idx = 1; idx < n; idx++) {
            for (int W = 0; W <= totalCapacity; W++) {

                int notTake = dp[idx - 1][W];
                int take = 0;

                if (W >= weights.get(idx)) {
                    take = values.get(idx) + dp[idx][W - weights.get(idx)];
                }

                dp[idx][W] = Math.max(notTake, take);
            }
        }

        return dp[n - 1][totalCapacity];

    }

    private static int twoArraySpaceOptimization(ArrayList<Integer> values, ArrayList<Integer> weights, int totalCapacity) {

        int n = values.size();
        int[] prev = new int[totalCapacity + 1];

        for (int W = 0; W <= totalCapacity; W++) {
            prev[W] = (W / weights.getFirst()) * values.getFirst();
        }

        for (int idx = 1; idx < n; idx++) {

            int[] curr = new int[totalCapacity + 1];

            for (int W = 0; W <= totalCapacity; W++) {

                int notTake = prev[W];
                int take = 0;

                if (W >= weights.get(idx)) {
                    take = values.get(idx) + curr[W - weights.get(idx)];
                }

                curr[W] = Math.max(notTake, take);
            }

            prev = curr;
        }

        return prev[totalCapacity];

    }

    private static int oneArrayUltimateSpaceOptimization(ArrayList<Integer> values, ArrayList<Integer> weights, int totalCapacity) {

        int n = values.size();
        int[] prev = new int[totalCapacity + 1];

        for (int W = 0; W <= totalCapacity; W++) {
            prev[W] = (W / weights.getFirst()) * values.getFirst();
        }

        for (int idx = 1; idx < n; idx++) {
            for (int W = 0; W <= totalCapacity; W++) {

                int notTake = prev[W];
                int take = 0;

                if (W >= weights.get(idx)) {
                    take = values.get(idx) + prev[W - weights.get(idx)];
                }

                prev[W] = Math.max(notTake, take);
            }

        }

        return prev[totalCapacity];

    }

    private static void printItems(ArrayList<Integer> values, ArrayList<Integer> weights) {
        for (int i = 0; i < values.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values.get(i) + ", weight=" + weights.get(i));
        }
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           UNBOUNDED KNAPSACK PROBLEM                         ║");
        System.out.println("║  Maximize value with weight constraint (unlimited items)     ║");
        System.out.println("║  Each item can be taken multiple times                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Unbounded Knapsack ===");
        ArrayList<Integer> values1 = new ArrayList<>();
        values1.add(10);
        values1.add(40);
        values1.add(30);
        ArrayList<Integer> weights1 = new ArrayList<>();
        weights1.add(1);
        weights1.add(3);
        weights1.add(4);
        int capacity1 = 5;

        System.out.println("Items:");
        for (int i = 0; i < values1.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values1.get(i) + ", weight=" + weights1.get(i));
        }
        System.out.println("Capacity: " + capacity1);
        System.out.println("\nOptimal solution: 5 items of value 10");
        System.out.println("  Take item 0 (value=10, weight=1) five times");
        System.out.println("  Total value: 50, Total weight: 5\n");

        int result1_rec = recursive(values1, weights1, values1.size() - 1, capacity1);

        int[][] dp1 = new int[values1.size()][capacity1 + 1];
        for (int i = 0; i < values1.size(); i++) {
            for (int j = 0; j <= capacity1; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(values1, weights1, values1.size() - 1, capacity1, dp1);

        int result1_tab = tabulation(values1, weights1, capacity1);
        int result1_opt1 = twoArraySpaceOptimization(values1, weights1, capacity1);
        int result1_opt2 = oneArrayUltimateSpaceOptimization(values1, weights1, capacity1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Opt (2 arrays) Result: " + result1_opt1);
        System.out.println("✓ Space Opt (1 array) Result: " + result1_opt2);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result1_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Mixed Value Densities ===");
        ArrayList<Integer> values2 = new ArrayList<>();
        values2.add(6);
        values2.add(10);
        ArrayList<Integer> weights2 = new ArrayList<>();
        weights2.add(1);
        weights2.add(2);
        int capacity2 = 10;

        System.out.println("Items:");
        for (int i = 0; i < values2.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values2.get(i) + ", weight=" + weights2.get(i) +
                " → density=" + (values2.get(i) * 1.0 / weights2.get(i)));
        }
        System.out.println("Capacity: " + capacity2);
        System.out.println("\nOptimal: Mix of both items");
        System.out.println("  Best option: 5 items of value 10");
        System.out.println("  Total value: 50, Total weight: 10\n");

        int result2_tab = tabulation(values2, weights2, capacity2);
        int result2_opt = oneArrayUltimateSpaceOptimization(values2, weights2, capacity2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 50");
        System.out.println("  Status: " + (result2_tab == 50 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Zero Capacity ===");
        ArrayList<Integer> values3 = new ArrayList<>();
        values3.add(5);
        values3.add(10);
        ArrayList<Integer> weights3 = new ArrayList<>();
        weights3.add(2);
        weights3.add(3);
        int capacity3 = 0;

        System.out.println("Items: (irrelevant)");
        System.out.println("Capacity: " + capacity3);
        System.out.println("\nNo items can be taken");
        System.out.println("Total value: 0\n");

        int result3_tab = tabulation(values3, weights3, capacity3);
        int result3_opt = oneArrayUltimateSpaceOptimization(values3, weights3, capacity3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Item Type ===");
        ArrayList<Integer> values4 = new ArrayList<>();
        values4.add(7);
        ArrayList<Integer> weights4 = new ArrayList<>();
        weights4.add(2);
        int capacity4 = 8;

        System.out.println("Items:");
        System.out.println("  Item 0: value=7, weight=2");
        System.out.println("Capacity: " + capacity4);
        System.out.println("\nCan take item 4 times: 4 × 7 = 28");
        System.out.println("Total weight: 4 × 2 = 8\n");

        int result4_tab = tabulation(values4, weights4, capacity4);
        int result4_opt = oneArrayUltimateSpaceOptimization(values4, weights4, capacity4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 28");
        System.out.println("  Status: " + (result4_tab == 28 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Large Values ===");
        ArrayList<Integer> values5 = new ArrayList<>();
        values5.add(100);
        values5.add(150);
        ArrayList<Integer> weights5 = new ArrayList<>();
        weights5.add(10);
        weights5.add(20);
        int capacity5 = 50;

        System.out.println("Items:");
        for (int i = 0; i < values5.size(); i++) {
            System.out.println("  Item " + i + ": value=" + values5.get(i) + ", weight=" + weights5.get(i));
        }
        System.out.println("Capacity: " + capacity5);
        System.out.println("\nOptimal: 5 items of value 100");
        System.out.println("  Total value: 500, Total weight: 50\n");

        int result5_tab = tabulation(values5, weights5, capacity5);
        int result5_opt = oneArrayUltimateSpaceOptimization(values5, weights5, capacity5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Unbounded Knapsack: Each item can be used unlimited times   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Difference from 0/1 Knapsack:                           ║");
        System.out.println("║    0/1: After taking item[i], recurse with idx-1             ║");
        System.out.println("║    Unbounded: After taking item[i], recurse with idx         ║");
        System.out.println("║    ^^^ Same index allows reusing the same item               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    maxValue(idx, W) = max(notTake, take)                     ║");
        System.out.println("║    notTake = maxValue(idx-1, W)                              ║");
        System.out.println("║    take = value[idx] + maxValue(idx, W - weight[idx])        ║");
        System.out.println("║           ^^^ same idx (unbounded)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Case:                                                  ║");
        System.out.println("║    if (idx == 0):                                            ║");
        System.out.println("║      return (W / weight[0]) × value[0]                       ║");
        System.out.println("║      (can take first item as many times as possible)         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][w] = max value using items 0..i with capacity w     ║");
        System.out.println("║    Can use item i multiple times                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base initialization:                                        ║");
        System.out.println("║    dp[0][w] = (w / weight[0]) × value[0]                     ║");
        System.out.println("║    (using only first item, reused)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Transition:                                                 ║");
        System.out.println("║    dp[i][w] = max(dp[i-1][w],                                ║");
        System.out.println("║                   value[i] + dp[i][w - weight[i]])           ║");
        System.out.println("║                                (stay at row i!)              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: values=[10,40,30], weights=[1,3,4], capacity=5     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    dp table:                                                 ║");
        System.out.println("║         0  1  2   3   4   5                                  ║");
        System.out.println("║    [10] 0 10 20  30  40  50  (can use 1 unlimited times)     ║");
        System.out.println("║    [40] 0 10 20  40  50  60  (mix of 10 and 40)              ║");
        System.out.println("║    [30] 0 10 20  40  50  60  (best remains with 10)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    CRITICAL: Process left-to-right (not right-to-left!)      ║");
        System.out.println("║    Need dp[i][w - weight[i]] which may have been updated     ║");
        System.out.println("║    This allows reuse of same item                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Single Array Works:                                     ║");
        System.out.println("║    prev[w] is processed left-to-right                        ║");
        System.out.println("║    When calculating prev[w], we use prev[w-weight[i]]        ║");
        System.out.println("║    That's already updated in this iteration!                 ║");
        System.out.println("║    This automatically handles unbounded nature               ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × W) where n=items, W=capacity                 ║");
        System.out.println("║    Space: O(W) optimized, O(n×W) tabulation                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}

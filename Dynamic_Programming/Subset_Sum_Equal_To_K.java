package Dynamic_Programming;

/*

Description:
  Following program demonstrates the solution to the "Subset Sum Equal To K" problem using Dynamic Programming techniques 
      to determine whether a subset of the given array exists whose sum equals a specified target value K...

Problem Statement:
  -> You are given an integer array arr[] containing n elements...
  -> You are also given a target sum K...
  -> The task is to determine whether there exists a subset of the array whose elements add up exactly to K...
  -> Each element can either be included in the subset or excluded from it...
  -> Each element can be used only once (0/1 choice)...

Approach:
  > Dynamic Programming with inclusion–exclusion principle:

     i. For each element we have two choices...
          • Include the element in the subset...
          • Exclude the element from the subset...
     ii. Recursively check if either choice can lead to the target sum...
     iii. Use memoization to store overlapping subproblem results...
     iv. Convert the recursion to tabulation for efficient bottom-up computation...

> State Representation:
  -> Let dp[i][target] represent:
       whether it is possible to achieve sum = target using elements from index 0 to i...

> Recursive Relation:
  -> For each element at index idx:

       notTake = subset(idx-1, target)...
       take    = subset(idx-1, target - arr[idx]) if arr[idx] <= target...

       result = take OR notTake...

> Base Cases:
  -> If target == 0:
       return true because empty subset always satisfies sum 0...

  -> If idx == 0:
       return arr[0] == target...

> Algorithm Steps:
  -> Start recursion from the last index of the array...
  -> At each element decide whether to include or exclude it...
  -> If target becomes zero, a valid subset is found...
  -> Memoization stores computed states in dp[idx][target]...
  -> Tabulation builds a boolean DP table iteratively...

> Tabulation Logic:
  -> Create DP table dp[n][k+1]...

  -> Initialize:
       dp[i][0] = true for all i...
       because sum 0 is always achievable with empty subset...

  -> If arr[0] <= k:
       dp[0][arr[0]] = true...

  -> Fill DP table:

       for each element i from 1 to n-1...
           for each target from 1 to k...

                notTake = dp[i-1][target]...

                take = false...
                if arr[i] <= target:
                     take = dp[i-1][target-arr[i]]...

                dp[i][target] = take OR notTake...

> Example:

  Input:
       arr = [1,2,3,4]
       k = 4...

  Possible subsets:
       {4} → sum = 4...
       {1,3} → sum = 4...

  Output:
       true...

> Edge Cases:
  -> Target sum = 0 → always true (empty subset)...
  -> Single element equal to target → valid subset...
  -> Target greater than sum of all elements → false...
  -> Multiple elements may produce same target sum...

> Key Insight:
  -> The problem follows the classic 0/1 Knapsack pattern...
  -> Overlapping subproblems and optimal substructure make Dynamic Programming efficient...

> Applications:
  -> Partition Equal Subset Sum...
  -> Target Sum problems...
  -> Knapsack related optimization...
  -> Resource allocation problems...

> Time and Space Complexity:

  -> Recursive:
       Time Complexity: O(2^n) due to exploring all subsets...
       Space Complexity: O(n) recursion stack...

  -> Memoization:
       Time Complexity: O(n × k)...
       Space Complexity: O(n × k)...

  -> Tabulation:
       Time Complexity: O(n × k)...
       Space Complexity: O(n × k)...

*/

public class Subset_Sum_Equal_To_K {

    private static boolean recursive(int[] arr, int idx, int target) {

        if (target == 0) {
            return true;
        }

        if (idx == 0) {
            return arr[0] == target;
        }

        boolean notTaken = recursive(arr, idx - 1, target);
        boolean taken = arr[idx] <= target && recursive(arr, idx - 1, target - arr[idx]);

        return notTaken || taken;

    }

    private static boolean memoization(int[] arr, int idx, int target, int[][] dp) {

        if (target == 0) {
            return true;
        }

        if (idx == 0) {
            return arr[0] == target;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target] == 1;
        }

        boolean notTaken = memoization(arr, idx - 1, target, dp);
        boolean taken = arr[idx] <= target && memoization(arr, idx - 1, target - arr[idx], dp);

        dp[idx][target] = (notTaken || taken) ? 1 : 0;
        return notTaken || taken;

    }

    private static boolean tabulation(int[] arr, int n, int k) {

        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= k; target++) {

                boolean notTake = dp[idx - 1][target];

                boolean take = false;
                if (arr[idx] <= target) {
                    take = dp[idx - 1][target - arr[idx]];
                }

                dp[idx][target] = take || notTake;
            }
        }

        return dp[n - 1][k];
    }


    private static String arrayToString(int[] arr) {

        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              SUBSET SUM EQUAL TO K                           ║");
        System.out.println("║  Check if there exists a subset with sum equal to target K   ║");
        System.out.println("║  Classic 0/1 Knapsack variation problem                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Subset ===");
        int[] arr1 = {1, 2, 3, 4};
        int k1 = 4;
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("Target K: " + k1);
        System.out.println("\nPossible subsets with sum 4:");
        System.out.println("  {4} → sum = 4 ✓");
        System.out.println("  {1, 3} → sum = 4 ✓");
        System.out.println("\nSubset exists!\n");

        boolean result1_rec = recursive(arr1, arr1.length - 1, k1);

        int[][] dp1 = new int[arr1.length][k1 + 1];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j <= k1; j++) dp1[i][j] = - 1;
        }
        boolean result1_memo = memoization(arr1, arr1.length - 1, k1, dp1);

        boolean result1_tab = tabulation(arr1, arr1.length, k1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1_tab == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: No Valid Subset ===");
        int[] arr2 = {1, 2, 3, 4};
        int k2 = 11;
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("Target K: " + k2);
        System.out.println("\nMaximum possible sum: 1+2+3+4 = 10");
        System.out.println("Cannot reach 11 → No subset exists\n");

        boolean result2_tab = tabulation(arr2, arr2.length, k2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result2_tab == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Target Zero ===");
        int[] arr3 = {1, 2, 3};
        int k3 = 0;
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("Target K: " + k3);
        System.out.println("\nEmpty subset {} has sum = 0");
        System.out.println("Always possible!\n");

        boolean result3_tab = tabulation(arr3, arr3.length, k3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result3_tab == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Single Element Match ===");
        int[] arr4 = {5};
        int k4 = 5;
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("Target K: " + k4);
        System.out.println("\nSubset {5} has sum = 5 ✓\n");

        boolean result4_tab = tabulation(arr4, arr4.length, k4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result4_tab == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Element No Match ===");
        int[] arr5 = {5};
        int k5 = 3;
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("Target K: " + k5);
        System.out.println("\nOnly subset {5} has sum ≠ 3\n");

        boolean result5_tab = tabulation(arr5, arr5.length, k5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result5_tab == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: All Elements Needed ===");
        int[] arr6 = {2, 3, 5};
        int k6 = 10;
        System.out.println("Array: " + arrayToString(arr6));
        System.out.println("Target K: " + k6);
        System.out.println("\nSubset {2, 3, 5} → sum = 10 ✓\n");

        boolean result6_tab = tabulation(arr6, arr6.length, k6);

        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result6_tab == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Multiple Valid Subsets ===");
        int[] arr7 = {3, 3, 3, 3};
        int k7 = 6;
        System.out.println("Array: " + arrayToString(arr7));
        System.out.println("Target K: " + k7);
        System.out.println("\nMultiple subsets work:");
        System.out.println("  {3, 3} (indices 0,1) → sum = 6 ✓");
        System.out.println("  {3, 3} (indices 0,2) → sum = 6 ✓");
        System.out.println("  ... and more combinations\n");

        boolean result7_tab = tabulation(arr7, arr7.length, k7);

        System.out.println("✓ Tabulation Result: " + result7_tab);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result7_tab == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Check if subset exists with sum equal to K         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two Choices Per Element:                                    ║");
        System.out.println("║    1. Include element in subset (take)                       ║");
        System.out.println("║    2. Exclude element from subset (not take)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    canSum(idx, target) = notTake || take                     ║");
        System.out.println("║    notTake = canSum(idx-1, target)                           ║");
        System.out.println("║    take = canSum(idx-1, target - arr[idx])                   ║");
        System.out.println("║           (only if arr[idx] <= target)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Cases:                                                 ║");
        System.out.println("║    if (target == 0) return true (empty subset)               ║");
        System.out.println("║    if (idx == 0) return arr[0] == target                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][j] = true if subset of arr[0..i] sums to j          ║");
        System.out.println("║    Base: dp[i][0] = true (all rows, target 0)                ║");
        System.out.println("║    Base: dp[0][arr[0]] = true (first element)                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Transition:                                                 ║");
        System.out.println("║    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i]]                ║");
        System.out.println("║               (not take)    (take, if arr[i] <= j)           ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×k) - Fill dp table                             ║");
        System.out.println("║    Space: O(n×k) tabulation, can optimize to O(k)            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
    
}

package Dynamic_Programming;

public class Count_Subsets_With_Sum_K {

    private static int recursive(int[] arr, int idx, int target) {

        if (target == 0) {
            return 1;
        }

        if (idx == 0) {
            return arr[0] == target ? 1 : 0;
        }

        int notPick = recursive(arr, idx - 1, target);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = recursive(arr, idx - 1, target - arr[idx]);
        }

        return notPick + pick;

    }

    private static int memoization(int[] arr, int idx, int target, int[][] dp) {

        if (target == 0) {
            return 1;
        }

        if (idx == 0) {
            return arr[0] == target ? 1 : 0;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target];
        }

        int notPick = memoization(arr, idx - 1, target, dp);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = memoization(arr, idx - 1, target - arr[idx], dp);
        }

        return dp[idx][target] = notPick + pick;

    }

    private static int tabulation(int[] arr, int k) {

        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= k; target++) {

                int notPick = dp[idx - 1][target];
                int pick = 0;

                if (arr[idx] <= target) {
                    pick = dp[idx - 1][target - arr[idx]];
                }

                dp[idx][target] = notPick + pick;
            }
        }

        return dp[n - 1][k];
    }

    private static int ultimateSpaceOptimization(int[] arr, int k) {

        int n = arr.length;
        int[] prev = new int[k + 1];
        int[] curr = new int[k + 1];

        prev[0] = curr[0] = 1;

        if (arr[0] <= k) {
            prev[arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= k; target++) {

                int notPick = prev[target];
                int pick = 0;

                if (arr[idx] <= target) {
                    pick = prev[target - arr[idx]];
                }

                curr[target] = notPick + pick;
            }

            prev = curr;
        }

        return prev[k];

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
        System.out.println("║            COUNT SUBSETS WITH SUM K                          ║");
        System.out.println("║  Count number of subsets whose sum equals target K           ║");
        System.out.println("║  Extension of Subset Sum problem                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Multiple Subsets ===");
        int[] arr1 = {1, 2, 2, 3};
        int k1 = 3;
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("Target K: " + k1);
        System.out.println("\nSubsets with sum = 3:");
        System.out.println("  {1, 2} (using arr[0], arr[1]) → sum = 3 ✓");
        System.out.println("  {1, 2} (using arr[0], arr[2]) → sum = 3 ✓");
        System.out.println("  {3} (using arr[3]) → sum = 3 ✓");
        System.out.println("\nTotal count: 3\n");

        int result1_rec = recursive(arr1, arr1.length - 1, k1);

        int[][] dp1 = new int[arr1.length][k1 + 1];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j <= k1; j++) dp1[i][j] = - 1;
        }
        int result1_memo = memoization(arr1, arr1.length - 1, k1, dp1);

        int result1_tab = tabulation(arr1, k1);
        int result1_opt = ultimateSpaceOptimization(arr1, k1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Memoization Result: " + result1_memo);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1_tab == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: No Valid Subset ===");
        int[] arr2 = {1, 2, 3};
        int k2 = 10;
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("Target K: " + k2);
        System.out.println("\nMaximum sum: 1+2+3 = 6");
        System.out.println("Cannot reach 10");
        System.out.println("Count: 0\n");

        int result2_tab = tabulation(arr2, k2);
        int result2_opt = ultimateSpaceOptimization(arr2, k2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Target Zero ===");
        int[] arr3 = {1, 2, 3};
        int k3 = 0;
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("Target K: " + k3);
        System.out.println("\nEmpty subset {} has sum = 0");
        System.out.println("Count: 1 (only empty subset)\n");

        int result3_tab = tabulation(arr3, k3);
        int result3_opt = ultimateSpaceOptimization(arr3, k3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result3_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Same Elements ===");
        int[] arr4 = {2, 2, 2, 2};
        int k4 = 4;
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("Target K: " + k4);
        System.out.println("\nSubsets with sum = 4:");
        System.out.println("  {2, 2} → multiple combinations");
        System.out.println("  Choose 2 from 4 positions = C(4,2) = 6\n");

        int result4_tab = tabulation(arr4, k4);
        int result4_opt = ultimateSpaceOptimization(arr4, k4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Element Match ===");
        int[] arr5 = {5};
        int k5 = 5;
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("Target K: " + k5);
        System.out.println("\nSubset {5} → sum = 5");
        System.out.println("Count: 1\n");

        int result5_tab = tabulation(arr5, k5);
        int result5_opt = ultimateSpaceOptimization(arr5, k5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Element No Match ===");
        int[] arr6 = {5};
        int k6 = 3;
        System.out.println("Array: " + arrayToString(arr6));
        System.out.println("Target K: " + k6);
        System.out.println("\nNo subset sums to 3");
        System.out.println("Count: 0\n");

        int result6_tab = tabulation(arr6, k6);
        int result6_opt = ultimateSpaceOptimization(arr6, k6);

        System.out.println("✓ Tabulation Result: " + result6_tab);
        System.out.println("✓ Space Optimized Result: " + result6_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Many Combinations ===");
        int[] arr7 = {1, 1, 1, 1};
        int k7 = 2;
        System.out.println("Array: " + arrayToString(arr7));
        System.out.println("Target K: " + k7);
        System.out.println("\nNeed to pick 2 ones from 4");
        System.out.println("C(4,2) = 6 combinations\n");

        int result7_tab = tabulation(arr7, k7);
        int result7_opt = ultimateSpaceOptimization(arr7, k7);

        System.out.println("✓ Tabulation Result: " + result7_tab);
        System.out.println("✓ Space Optimized Result: " + result7_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result7_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Count all subsets with sum equal to K              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Difference from Subset Sum (boolean):                       ║");
        System.out.println("║    Instead of returning true/false,                          ║");
        System.out.println("║    Count and sum all possible ways                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Recursive Relation:                                         ║");
        System.out.println("║    count(idx, target) = notPick + pick                       ║");
        System.out.println("║    notPick = count(idx-1, target)                            ║");
        System.out.println("║    pick = count(idx-1, target - arr[idx])                    ║");
        System.out.println("║           (only if arr[idx] <= target)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Base Cases:                                                 ║");
        System.out.println("║    if (target == 0) return 1 (empty subset)                  ║");
        System.out.println("║    if (idx == 0) return arr[0] == target ? 1 : 0             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Tabulation:                                                 ║");
        System.out.println("║    dp[i][j] = count of subsets from arr[0..i] summing to j   ║");
        System.out.println("║    Base: dp[i][0] = 1 (all rows, empty subset)               ║");
        System.out.println("║    Base: dp[0][arr[0]] = 1 (first element)                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Transition:                                                 ║");
        System.out.println("║    dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]                 ║");
        System.out.println("║               (not pick)    (pick, if arr[i] <= j)           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Use prev[] and curr[] arrays                              ║");
        System.out.println("║    Only need previous row to compute current                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×k) - Fill DP table                             ║");
        System.out.println("║    Space: O(k) optimized, O(n×k) tabulation                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
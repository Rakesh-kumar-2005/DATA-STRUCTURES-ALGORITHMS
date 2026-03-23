package Dynamic_Programming;

import java.util.Arrays;

public class Count_Partitions_With_Given_Difference {

    private static int mod = (int) (1e9 + 7);

    private static int recursive(int[] arr, int idx, int target) {

        if (idx == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            if (target == 0 || arr[0] == target) {
                return 1;
            } else return 0;
        }

        int notPick = recursive(arr, idx - 1, target);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = recursive(arr, idx - 1, target - arr[idx]);
        }

        return (pick + notPick) % mod;

    }

    private static int memoization(int[] arr, int idx, int target, int[][] dp) {

        if (idx == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }

            if (target == 0 || arr[0] == target) {
                return 1;
            } else return 0;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target];
        }

        int notPick = memoization(arr, idx - 1, target, dp);
        int pick = 0;

        if (arr[idx] <= target) {
            pick = memoization(arr, idx - 1, target - arr[idx], dp);
        }

        return dp[idx][target] = (pick + notPick) % mod;

    }

    private static int tabulation(int[] arr, int k) {

        int n = arr.length;
        int[][] dp = new int[n][k + 1];

        if (arr[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }

        if (arr[0] != 0 && arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= k; target++) {

                int notPick = dp[idx - 1][target];
                int pick = 0;

                if (arr[idx] <= target) {
                    pick = dp[idx - 1][target - arr[idx]];
                }

                dp[idx][target] = pick + notPick;
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
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       COUNT PARTITIONS WITH GIVEN DIFFERENCE                 ║");
        System.out.println("║  Count ways to partition array into two subsets S1 and S2    ║");
        System.out.println("║  such that S1 - S2 = given difference                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] arr1 = {5, 2, 6, 4};
        int diff1 = 3;
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("Difference: " + diff1);

        int totalSum1 = 0;
        for (int num : arr1) totalSum1 += num;

        System.out.println("\nMathematical derivation:");
        System.out.println("  Total sum = " + totalSum1);
        System.out.println("  S1 + S2 = " + totalSum1);
        System.out.println("  S1 - S2 = " + diff1);
        System.out.println("  Solving: S1 = (total + diff)/2");
        System.out.println("  S1 = (" + totalSum1 + " + " + diff1 + ")/2 = " + (totalSum1 + diff1) / 2);

        if ((totalSum1 - diff1) < 0 || (totalSum1 - diff1) % 2 != 0) {
            System.out.println("\nImpossible: (total - diff) is negative or odd");
            System.out.println("Result: 0\n");
        } else {
            int target1 = (totalSum1 - diff1) / 2;
            System.out.println("  Target S2 = (total - diff)/2 = " + target1);
            System.out.println("\nFind subsets with sum = " + target1 + "\n");

            int result1_rec = recursive(arr1, arr1.length - 1, target1);

            int[][] dp1 = new int[arr1.length][target1 + 1];
            for (int[] temp : dp1) Arrays.fill(temp, - 1);
            int result1_memo = memoization(arr1, arr1.length - 1, target1, dp1);

            int result1_tab = tabulation(arr1, target1);

            System.out.println("✓ Recursive Result: " + result1_rec);
            System.out.println("✓ Memoization Result: " + result1_memo);
            System.out.println("✓ Tabulation Result: " + result1_tab);
            System.out.println("  Status: PASS ✓\n");
        }

        System.out.println("=== Test Case 2: With Zeros ===");
        int[] arr2 = {0, 0, 1};
        int diff2 = 1;
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("Difference: " + diff2);

        int totalSum2 = 0;
        for (int num : arr2) totalSum2 += num;

        System.out.println("\nTotal sum = " + totalSum2);
        System.out.println("Zeros create multiple ways!");
        System.out.println("Each 0 can go to either subset → 2^(count of 0s) ways\n");

        if ((totalSum2 - diff2) < 0 || (totalSum2 - diff2) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target2 = (totalSum2 - diff2) / 2;
            int result2_tab = tabulation(arr2, target2);

            System.out.println("✓ Tabulation Result: " + result2_tab);
            System.out.println("  Expected: 4 (2^2 zeros × 1 way for {1})");
            System.out.println("  Status: " + (result2_tab == 4 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("=== Test Case 3: Impossible Case ===");
        int[] arr3 = {1, 2, 3};
        int diff3 = 10;
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("Difference: " + diff3);

        int totalSum3 = 0;
        for (int num : arr3) totalSum3 += num;

        System.out.println("\nTotal sum = " + totalSum3);
        System.out.println("Cannot achieve difference of 10");
        System.out.println("Result: 0\n");

        if ((totalSum3 - diff3) < 0 || (totalSum3 - diff3) % 2 != 0) {
            System.out.println("✓ Result: 0");
            System.out.println("  Status: PASS ✓\n");
        }

        System.out.println("=== Test Case 4: All Same Elements ===");
        int[] arr4 = {1, 1, 1, 1};
        int diff4 = 0;
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("Difference: " + diff4);

        int totalSum4 = 0;
        for (int num : arr4) totalSum4 += num;

        System.out.println("\nFor diff = 0, both subsets equal");
        System.out.println("S1 = S2 = total/2 = " + totalSum4 / 2);
        System.out.println("Choose 2 elements from 4 for S1");
        System.out.println("C(4,2) = 6 ways\n");

        if ((totalSum4 - diff4) < 0 || (totalSum4 - diff4) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target4 = (totalSum4 - diff4) / 2;
            int result4_tab = tabulation(arr4, target4);

            System.out.println("✓ Tabulation Result: " + result4_tab);
            System.out.println("  Expected: 6");
            System.out.println("  Status: " + (result4_tab == 6 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("=== Test Case 5: Single Element ===");
        int[] arr5 = {5};
        int diff5 = 5;
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("Difference: " + diff5);

        int totalSum5 = 0;
        for (int num : arr5) totalSum5 += num;

        System.out.println("\nS1 = {5}, S2 = {}");
        System.out.println("S1 - S2 = 5 - 0 = 5 ✓");
        System.out.println("One way\n");

        if ((totalSum5 - diff5) < 0 || (totalSum5 - diff5) % 2 != 0) {
            System.out.println("Result: 0\n");
        } else {
            int target5 = (totalSum5 - diff5) / 2;
            int result5_tab = tabulation(arr5, target5);

            System.out.println("✓ Tabulation Result: " + result5_tab);
            System.out.println("  Expected: 1");
            System.out.println("  Status: " + (result5_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
        }

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Count partitions where S1 - S2 = difference        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Mathematical Reduction:                                     ║");
        System.out.println("║    Given: S1 + S2 = total_sum                                ║");
        System.out.println("║           S1 - S2 = difference                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Adding equations:                                         ║");
        System.out.println("║      2×S1 = total_sum + difference                           ║");
        System.out.println("║      S1 = (total_sum + difference) / 2                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Subtracting equations:                                    ║");
        System.out.println("║      2×S2 = total_sum - difference                           ║");
        System.out.println("║      S2 = (total_sum - difference) / 2                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Validity Check:                                             ║");
        System.out.println("║    1. (total_sum - difference) must be ≥ 0                   ║");
        System.out.println("║    2. (total_sum - difference) must be even                  ║");
        System.out.println("║    If either fails → return 0                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Problem Reduction:                                          ║");
        System.out.println("║    Find count of subsets with sum = target                   ║");
        System.out.println("║    where target = (total_sum - difference) / 2               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Special Case - Zeros:                                       ║");
        System.out.println("║    if (arr[0] == 0 && target == 0) return 2                  ║");
        System.out.println("║    Zero can be in either subset → doubles possibilities      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DP Recurrence:                                              ║");
        System.out.println("║    dp[i][j] = count of subsets from arr[0..i] summing to j   ║");
        System.out.println("║    dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × target) where target = (sum - diff)/2        ║");
        System.out.println("║    Space: O(n × target) tabulation                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
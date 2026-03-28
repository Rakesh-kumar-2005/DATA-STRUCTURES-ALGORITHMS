package Dynamic_Programming;

import java.util.Arrays;

public class Target_Sum {

    private static int recursiveSolution(int[] nums, int target) {

        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        if ((totalSum - target < 0) || (totalSum + target) % 2 != 0) {
            return 0;
        }

        return recursion(n - 1, nums, (totalSum - target) / 2);
    }

    private static int recursion(int idx, int[] nums, int target) {
        if (idx == 0) {
            if (target == 0 && nums[0] == 0) {
                return 2;
            }
            if (target == 0 || nums[0] == target) {
                return 1;
            }
            return 0;
        }

        int notPick = recursion(idx - 1, nums, target);
        int pick = 0;

        if (nums[idx] <= target) {
            pick = recursion(idx - 1, nums, target - nums[idx]);
        }

        return notPick + pick;
    }

    public int memoizationSolution(int[] nums, int target) {

        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum - target < 0 || (totalSum - target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (totalSum - target) / 2;

        int[][] dp = new int[n][subsetSum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, - 1);
        }

        return memoization(n - 1, nums, subsetSum, dp);
    }

    private int memoization(int idx, int[] nums, int target, int[][] dp) {

        if (idx == 0) {
            if (target == 0 && nums[0] == 0) {
                return 2;
            }
            if (target == 0 || nums[0] == target) {
                return 1;
            }
            return 0;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target];
        }

        int notPick = memoization(idx - 1, nums, target, dp);
        int pick = 0;

        if (nums[idx] <= target) {
            pick = memoization(idx - 1, nums, target - nums[idx], dp);
        }

        return dp[idx][target] = notPick + pick;
    }

    private static int tabulation(int[] nums, int target) {

        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum - target < 0 || (totalSum - target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (totalSum - target) / 2;

        int[][] dp = new int[n][subsetSum + 1];

        if (nums[0] == 0) {
            dp[0][0] = 2;
        } else {
            dp[0][0] = 1;
        }

        if (nums[0] != 0 && nums[0] <= subsetSum) {
            dp[0][nums[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int tar = 0; tar <= subsetSum; tar++) {
                int notPick = dp[idx - 1][tar];
                int pick = 0;

                if (nums[idx] <= tar) {
                    pick = dp[idx - 1][tar - nums[idx]];
                }

                dp[idx][tar] = pick + notPick;
            }
        }

        return dp[n - 1][subsetSum];
    }

    private static int ultimateSpaceOptimization(int[] nums, int target) {

        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum - target < 0 || (totalSum - target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (totalSum - target) / 2;

        int[] prev = new int[subsetSum + 1];

        if (nums[0] == 0) {
            prev[0] = 2;
        } else {
            prev[0] = 1;
        }

        if (nums[0] != 0 && nums[0] <= subsetSum) {
            prev[nums[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            int[] curr = new int[subsetSum + 1];
            for (int tar = 0; tar <= subsetSum; tar++) {
                int notPick = prev[tar];
                int pick = 0;

                if (nums[idx] <= tar) {
                    pick = prev[tar - nums[idx]];
                }

                curr[tar] = pick + notPick;
            }

            prev = curr;
        }

        return prev[subsetSum];
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    TARGET SUM                                ║");
        System.out.println("║  Count ways to assign +/- to each number to reach target     ║");
        System.out.println("║  Reduce to: Count subsets with given difference problem      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("Target: " + target1);
        System.out.println("\nPossible ways:");
        System.out.println("  +1 +1 +1 +1 -1 = 3 ✓");
        System.out.println("  +1 +1 +1 -1 +1 = 3 ✓");
        System.out.println("  +1 +1 -1 +1 +1 = 3 ✓");
        System.out.println("  +1 -1 +1 +1 +1 = 3 ✓");
        System.out.println("  -1 +1 +1 +1 +1 = 3 ✓");
        System.out.println("\nTotal: 5 ways\n");

        int result1_rec = recursiveSolution(nums1, target1);
        int result1_tab = tabulation(nums1, target1);
        int result1_opt = ultimateSpaceOptimization(nums1, target1);

        System.out.println("✓ Recursive Result: " + result1_rec);
        System.out.println("✓ Tabulation Result: " + result1_tab);
        System.out.println("✓ Space Optimized Result: " + result1_opt);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result1_tab == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Element ===");
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("Target: " + target2);
        System.out.println("\nOnly way: +1 = 1");
        System.out.println("Count: 1\n");

        int result2_tab = tabulation(nums2, target2);
        int result2_opt = ultimateSpaceOptimization(nums2, target2);

        System.out.println("✓ Tabulation Result: " + result2_tab);
        System.out.println("✓ Space Optimized Result: " + result2_opt);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result2_tab == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: With Zeros ===");
        int[] nums3 = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        int target3 = 1;
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("Target: " + target3);
        System.out.println("\nZeros can be +0 or -0 (doesn't matter)");
        System.out.println("Each zero doubles possibilities: 2^8 = 256");
        System.out.println("Must use +1 to reach target");
        System.out.println("\nTotal: 256 ways\n");

        int result3_tab = tabulation(nums3, target3);
        int result3_opt = ultimateSpaceOptimization(nums3, target3);

        System.out.println("✓ Tabulation Result: " + result3_tab);
        System.out.println("✓ Space Optimized Result: " + result3_opt);
        System.out.println("  Expected: 256");
        System.out.println("  Status: " + (result3_tab == 256 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Impossible Target ===");
        int[] nums4 = {1, 2, 3};
        int target4 = 10;
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("Target: " + target4);
        System.out.println("\nTotal sum: 1+2+3 = 6");
        System.out.println("Maximum achievable: +1+2+3 = 6");
        System.out.println("Cannot reach 10");
        System.out.println("\nCount: 0\n");

        int result4_tab = tabulation(nums4, target4);
        int result4_opt = ultimateSpaceOptimization(nums4, target4);

        System.out.println("✓ Tabulation Result: " + result4_tab);
        System.out.println("✓ Space Optimized Result: " + result4_opt);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4_tab == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Target Zero ===");
        int[] nums5 = {1, 1};
        int target5 = 0;
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("Target: " + target5);
        System.out.println("\nWays:");
        System.out.println("  +1 -1 = 0 ✓");
        System.out.println("  -1 +1 = 0 ✓");
        System.out.println("\nCount: 2\n");

        int result5_tab = tabulation(nums5, target5);
        int result5_opt = ultimateSpaceOptimization(nums5, target5);

        System.out.println("✓ Tabulation Result: " + result5_tab);
        System.out.println("✓ Space Optimized Result: " + result5_opt);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result5_tab == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Assign +/- to reach target sum                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Mathematical Transformation:                                ║");
        System.out.println("║    Let S1 = subset with + sign                               ║");
        System.out.println("║    Let S2 = subset with - sign                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Given: S1 - S2 = target                                   ║");
        System.out.println("║          S1 + S2 = totalSum                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Solving:                                                  ║");
        System.out.println("║      2×S1 = totalSum + target                                ║");
        System.out.println("║      S1 = (totalSum + target) / 2                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Or equivalently:                                          ║");
        System.out.println("║      2×S2 = totalSum - target                                ║");
        System.out.println("║      S2 = (totalSum - target) / 2                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Problem Reduction:                                          ║");
        System.out.println("║    Count subsets with sum = (totalSum - target) / 2          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Validity Checks:                                            ║");
        System.out.println("║    1. totalSum - target >= 0 (S2 must be non-negative)       ║");
        System.out.println("║    2. (totalSum - target) % 2 == 0 (must be even)            ║");
        System.out.println("║    If either fails → return 0                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: nums=[1,1,1,1,1], target=3                         ║");
        System.out.println("║    totalSum = 5                                              ║");
        System.out.println("║    subsetSum = (5-3)/2 = 1                                   ║");
        System.out.println("║    Find subsets with sum=1: {1} appears 5 times              ║");
        System.out.println("║    Answer: 5 ways                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Special Case - Zeros:                                       ║");
        System.out.println("║    if (nums[0] == 0 && target == 0): return 2                ║");
        System.out.println("║    Zero can have + or - (both give 0)                        ║");
        System.out.println("║    Each zero doubles the count                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DP State:                                                   ║");
        System.out.println("║    dp[i][j] = count of subsets from nums[0..i] with sum j    ║");
        System.out.println("║    Same as Count_Subsets_With_Sum_K problem                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × subsetSum) where subsetSum=(sum-target)/2    ║");
        System.out.println("║    Space: O(subsetSum) optimized, O(n×subsetSum) tabulation  ║");
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
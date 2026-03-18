package Dynamic_Programming;

public class Partition_Equal_Subset_Sum {

    private static boolean helper(int[] nums, int k) {

        int n = nums.length;
        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (nums[0] <= k) {
            dp[0][nums[0]] = true;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 1; target <= k; target++) {

                boolean notTaken = dp[idx - 1][target];
                boolean taken = false;

                if (nums[idx] <= target) {
                    taken = dp[idx - 1][target - nums[idx]];
                }

                dp[idx][target] = notTaken || taken;
            }
        }

        return dp[n - 1][k];
    }

    private static boolean canPartition(int[] nums) {

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        return helper(nums, target);
    }

    private static boolean canPartitionOptimized(int[] nums) {

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }

        return dp[target];
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
        System.out.println("║           PARTITION EQUAL SUBSET SUM                         ║");
        System.out.println("║  Check if array can be partitioned into two equal sum halves ║");
        System.out.println("║  Extension of Subset Sum problem                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Partition ===");
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("\nTotal sum: 1+5+11+5 = 22");
        System.out.println("Target for each partition: 22/2 = 11");
        System.out.println("\nPartition 1: {1, 5, 5} → sum = 11 ✓");
        System.out.println("Partition 2: {11} → sum = 11 ✓");
        System.out.println("\nCan partition!\n");

        boolean result1 = canPartition(nums1);
        boolean result1_opt = canPartitionOptimized(nums1);

        System.out.println("✓ Standard Result: " + result1);
        System.out.println("✓ Optimized Result: " + result1_opt);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Odd Sum ===");
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("\nTotal sum: 1+2+3+5 = 11 (odd)");
        System.out.println("Cannot divide odd sum into two equal integers");
        System.out.println("Cannot partition!\n");

        boolean result2 = canPartition(nums2);
        boolean result2_opt = canPartitionOptimized(nums2);

        System.out.println("✓ Standard Result: " + result2);
        System.out.println("✓ Optimized Result: " + result2_opt);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result2 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Element ===");
        int[] nums3 = {2};
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("\nCannot split single element into two partitions");
        System.out.println("Cannot partition!\n");

        boolean result3 = canPartition(nums3);
        boolean result3_opt = canPartitionOptimized(nums3);

        System.out.println("✓ Standard Result: " + result3);
        System.out.println("✓ Optimized Result: " + result3_opt);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result3 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Two Equal Elements ===");
        int[] nums4 = {1, 1};
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("\nTotal sum: 1+1 = 2");
        System.out.println("Target: 2/2 = 1");
        System.out.println("\nPartition 1: {1} → sum = 1 ✓");
        System.out.println("Partition 2: {1} → sum = 1 ✓");
        System.out.println("\nCan partition!\n");

        boolean result4 = canPartition(nums4);
        boolean result4_opt = canPartitionOptimized(nums4);

        System.out.println("✓ Standard Result: " + result4);
        System.out.println("✓ Optimized Result: " + result4_opt);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result4 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Cannot Partition ===");
        int[] nums5 = {1, 2, 5};
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("\nTotal sum: 1+2+5 = 8");
        System.out.println("Target: 8/2 = 4");
        System.out.println("\nPossible subsets:");
        System.out.println("  {1} → 1, {2} → 2, {5} → 5");
        System.out.println("  {1,2} → 3, {1,5} → 6, {2,5} → 7");
        System.out.println("No subset sums to 4!");
        System.out.println("\nCannot partition!\n");

        boolean result5 = canPartition(nums5);
        boolean result5_opt = canPartitionOptimized(nums5);

        System.out.println("✓ Standard Result: " + result5);
        System.out.println("✓ Optimized Result: " + result5_opt);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result5 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: All Same Elements ===");
        int[] nums6 = {3, 3, 3, 3};
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("\nTotal sum: 3+3+3+3 = 12");
        System.out.println("Target: 12/2 = 6");
        System.out.println("\nPartition 1: {3, 3} → sum = 6 ✓");
        System.out.println("Partition 2: {3, 3} → sum = 6 ✓");
        System.out.println("\nCan partition!\n");

        boolean result6 = canPartition(nums6);
        boolean result6_opt = canPartitionOptimized(nums6);

        System.out.println("✓ Standard Result: " + result6);
        System.out.println("✓ Optimized Result: " + result6_opt);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result6 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Numbers ===");
        int[] nums7 = {100, 100, 100, 100, 100, 100, 100, 100};
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("\nTotal sum: 800");
        System.out.println("Target: 400");
        System.out.println("\nPartition: any 4 elements = 400 ✓\n");

        boolean result7 = canPartition(nums7);
        boolean result7_opt = canPartitionOptimized(nums7);

        System.out.println("✓ Standard Result: " + result7);
        System.out.println("✓ Optimized Result: " + result7_opt);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result7 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Partition array into two subsets with equal sum    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    If total sum is odd → impossible to partition equally     ║");
        System.out.println("║    If total sum is even → find subset with sum = total/2     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Reduction to Subset Sum:                                    ║");
        System.out.println("║    If we can find subset with sum = total/2,                 ║");
        System.out.println("║    remaining elements automatically sum to total/2           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Steps:                                            ║");
        System.out.println("║    1. Calculate total sum                                    ║");
        System.out.println("║    2. If sum is odd → return false                           ║");
        System.out.println("║    3. Set target = sum/2                                     ║");
        System.out.println("║    4. Use Subset Sum DP to check if target achievable        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  DP State:                                                   ║");
        System.out.println("║    dp[i][j] = can we achieve sum j using first i elements?   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Optimization:                                         ║");
        System.out.println("║    Only need 1D array: dp[target+1]                          ║");
        System.out.println("║    Process elements right-to-left to avoid overwriting       ║");
        System.out.println("║    dp[t] = dp[t] || dp[t - num]                              ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × sum/2) - DP table size                       ║");
        System.out.println("║    Space: O(sum/2) optimized, O(n × sum/2) standard          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
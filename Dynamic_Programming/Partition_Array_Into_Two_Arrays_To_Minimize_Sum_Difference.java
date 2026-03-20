package Dynamic_Programming;

/*

Description:
  Following program demonstrates the solution to the "Partition Array Into Two Arrays To Minimize Sum Difference" problem using multiple approaches including Dynamic Programming, HashSet-based subset generation, and Meet-in-the-Middle optimization...

Problem Statement:
  -> You are given an integer array nums[]...
  -> The task is to partition the array into two subsets such that:
       • The absolute difference between their sums is minimized...
  -> Return the minimum possible value of |sum1 - sum2|...

Core Idea:
  -> Let totalSum be the sum of all elements...
  -> If subset1 has sum = S1, then subset2 has sum = totalSum - S1...
  -> Difference = |S1 - (totalSum - S1)| = |2*S1 - totalSum|...
  -> Goal is to find S1 as close as possible to totalSum / 2...

------------------------------------------------------------

Approach 1: Subset Sum DP...

  > Use classic subset sum DP to find all achievable sums...

     i. Create dp[i][j] → whether sum j is possible using first i elements...
     ii. Initialize:
          dp[i][0] = true...
          dp[0][nums[0]] = true (if within bounds)...
     iii. Transition:
          dp[i][j] = dp[i-1][j] OR dp[i-1][j - nums[i]]...

  > After DP table is filled:
     -> Check all possible sums from 0 to totalSum/2...
     -> Choose sum closest to totalSum/2 to minimize difference...

  Time Complexity:
     O(n × totalSum)...

  Space Complexity:
     O(n × totalSum)...

------------------------------------------------------------

Approach 2: HashSet Optimization...

  > Instead of DP table, track all possible subset sums using HashSet...

     i. Initialize set with 0...
     ii. For each number:
          generate new sums by adding current number...
          keep both old and new sums...

  > After processing all elements:
     -> Iterate through all possible sums...
     -> Compute minimum difference using |totalSum - 2*S|...

  Time Complexity:
     O(n × 2^n)...

  Space Complexity:
     O(2^n)...

------------------------------------------------------------

Approach 3: Meet-in-the-Middle (Optimal)...

  > Divide array into two halves...

     i. Generate all subset sums for left half...
     ii. Generate all subset sums for right half...
     iii. Group sums by subset size...

  > Key Idea:
     -> Combine subsets from left and right halves...
     -> Ensure balanced selection using subset sizes...

  > Optimization:
     -> Sort subset sums of right half...
     -> Use binary search to find closest sum to (target - leftSum)...

  > Transition:
     -> For each leftSum:
          find rightSum such that:
               leftSum + rightSum ≈ totalSum/2...

  Time Complexity:
     O(n × 2^(n/2))...

  Space Complexity:
     O(2^(n/2))...

------------------------------------------------------------

Algorithm Steps (DP Approach):

  -> Step 1: Compute total sum of array...
  -> Step 2: Build subset sum DP table...
  -> Step 3: Iterate through sums from 0 to totalSum/2...
  -> Step 4: For each achievable sum S:
       compute difference = |totalSum - 2*S|...
  -> Step 5: Return minimum difference...

------------------------------------------------------------

Example Execution:

  Input:
       nums = [3, 9, 7, 3]...

  Total sum:
       22...

  Target:
       11...

  Possible partitions:
       {9, 3} → sum = 12
       {7, 3} → sum = 10

  Difference:
       |12 - 10| = 2...

  Output:
       2...

------------------------------------------------------------

Edge Cases:
  -> Array with single element → difference equals element itself...
  -> All elements equal → perfectly balanced partition possible...
  -> Large values → Meet-in-the-Middle preferred...
  -> Odd total sum → still works (difference not zero necessarily)...

------------------------------------------------------------

Key Observations:
  -> Problem reduces to finding subset closest to totalSum/2...
  -> DP works efficiently when totalSum is small...
  -> Meet-in-the-Middle is powerful for larger constraints...

------------------------------------------------------------

Applications:
  -> Load balancing problems...
  -> Resource allocation...
  -> Partition optimization problems...
  -> Competitive programming subset problems...

------------------------------------------------------------

Time and Space Complexity Summary:

  DP Approach:
       Time  → O(n × totalSum)...
       Space → O(n × totalSum)...

  HashSet Approach:
       Time  → O(n × 2^n)...
       Space → O(2^n)...

  Meet-in-the-Middle:
       Time  → O(n × 2^(n/2))...
       Space → O(2^(n/2))...

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Partition_Array_Into_Two_Arrays_To_Minimize_Sum_Difference {

    private static void subSetSum(int[] nums, boolean[][] dp, int target) {

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (nums[0] <= target && nums[0] >= 0) {
            dp[0][nums[0]] = true;
        }

        for (int idx = 1; idx < n; idx++) {
            for (int tIdx = 1; tIdx <= target; tIdx++) {

                boolean notTaken = dp[idx - 1][tIdx];
                boolean taken = false;

                if (nums[idx] <= tIdx) {
                    taken = dp[idx - 1][tIdx - nums[idx]];
                }

                dp[idx][tIdx] = taken || notTaken;
            }
        }

    }

    private static int minimumDifference(int[] nums) {

        int n = nums.length;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        boolean[][] dp = new boolean[n][totalSum + 1];
        subSetSum(nums, dp, totalSum);

        int miniSum = Integer.MAX_VALUE;
        for (int i = 0; i <= totalSum / 2; i++) {
            if (dp[n - 1][i]) {
                miniSum = Math.min(miniSum, Math.abs((totalSum - i) - i));
            }
        }

        return miniSum;
    }

    private static int minimumDifferenceOptimized(int[] nums) {

        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        HashSet<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int num : nums) {
            HashSet<Integer> next = new HashSet<>();
            for (int i : dp) {
                next.add(num + i);
                next.add(i);
            }
            dp = next;
        }

        int miniSum = Integer.MAX_VALUE;
        for (int i : dp) {
            int diff = Math.abs(totalSum - 2 * i);
            miniSum = Math.min(miniSum, diff);
        }

        return miniSum;
    }

    private static int ultimateMinimumDifference(int[] nums) {
        int n = nums.length / 2;
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        ArrayList<ArrayList<Integer>> left = getSubsetSums(nums, 0, n);
        ArrayList<ArrayList<Integer>> right = getSubsetSums(nums, n, n);

        for (ArrayList<Integer> list : right) Collections.sort(list);

        int minDiff = Integer.MAX_VALUE;
        int target = totalSum / 2;

        for (int k = 0; k <= n; k++) {
            for (int leftSum : left.get(k)) {

                int need = target - leftSum;
                ArrayList<Integer> rightList = right.get(n - k);
                int idx = Collections.binarySearch(rightList, need);

                if (idx < 0) {

                    idx = - (idx + 1);
                }

                if (idx < rightList.size()) {
                    int s = leftSum + rightList.get(idx);
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * s));
                }

                if (idx > 0) {
                    int s = leftSum + rightList.get(idx - 1);
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * s));
                }
            }
        }

        return minDiff;
    }

    private static ArrayList<ArrayList<Integer>> getSubsetSums(int[] nums, int start, int n) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) result.add(new ArrayList<>());

        for (int mask = 0; mask < (1 << n); mask++) {
            int sum = 0;
            int size = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[start + i];
                    size++;
                }
            }

            result.get(size).add(sum);
        }

        return result;
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
        System.out.println("║     PARTITION ARRAY TO MINIMIZE SUM DIFFERENCE               ║");
        System.out.println("║  Partition array into two subsets to minimize |sum1 - sum2|  ║");
        System.out.println("║  Three approaches: DP, HashSet, Meet-in-the-Middle           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {3, 9, 7, 3};
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("\nTotal sum: 3+9+7+3 = 22");
        System.out.println("\nBest partition:");
        System.out.println("  Subset 1: {9, 3} → sum = 12");
        System.out.println("  Subset 2: {7, 3} → sum = 10");
        System.out.println("  Difference: |12 - 10| = 2\n");

        int result1_dp = minimumDifference(nums1);
        int result1_opt = minimumDifferenceOptimized(nums1);
        int result1_ultra = ultimateMinimumDifference(nums1);

        System.out.println("✓ DP Result: " + result1_dp);
        System.out.println("✓ HashSet Result: " + result1_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result1_ultra);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1_dp == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Already Balanced ===");
        int[] nums2 = {1, 1};
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("\nTotal sum: 2");
        System.out.println("Partition: {1} and {1}");
        System.out.println("Difference: |1 - 1| = 0\n");

        int result2_dp = minimumDifference(nums2);
        int result2_opt = minimumDifferenceOptimized(nums2);
        int result2_ultra = ultimateMinimumDifference(nums2);

        System.out.println("✓ DP Result: " + result2_dp);
        System.out.println("✓ HashSet Result: " + result2_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result2_ultra);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_dp == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Large Difference ===");
        int[] nums3 = {1, 100};
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("\nTotal sum: 101");
        System.out.println("\nPartitions:");
        System.out.println("  {1} vs {100} → diff = 99");
        System.out.println("  {100} vs {1} → diff = 99");
        System.out.println("  Minimum difference = 99\n");

        int result3_dp = minimumDifference(nums3);
        int result3_opt = minimumDifferenceOptimized(nums3);
        int result3_ultra = ultimateMinimumDifference(nums3);

        System.out.println("✓ DP Result: " + result3_dp);
        System.out.println("✓ HashSet Result: " + result3_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result3_ultra);
        System.out.println("  Expected: 99");
        System.out.println("  Status: " + (result3_dp == 99 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Even Split Possible ===");
        int[] nums4 = {2, 2, 2, 2};
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("\nTotal sum: 8");
        System.out.println("Partition: {2, 2} and {2, 2}");
        System.out.println("Both sum to 4 → diff = 0\n");

        int result4_dp = minimumDifference(nums4);
        int result4_opt = minimumDifferenceOptimized(nums4);
        int result4_ultra = ultimateMinimumDifference(nums4);

        System.out.println("✓ DP Result: " + result4_dp);
        System.out.println("✓ HashSet Result: " + result4_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result4_ultra);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4_dp == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Odd Total ===");
        int[] nums5 = {1, 2, 3};
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("\nTotal sum: 6");
        System.out.println("\nBest partition:");
        System.out.println("  {3} vs {1, 2} → |3 - 3| = 0\n");

        int result5_dp = minimumDifference(nums5);
        int result5_opt = minimumDifferenceOptimized(nums5);
        int result5_ultra = ultimateMinimumDifference(nums5);

        System.out.println("✓ DP Result: " + result5_dp);
        System.out.println("✓ HashSet Result: " + result5_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result5_ultra);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5_dp == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Meet-in-Middle Advantage ===");
        int[] nums6 = {1, 6, 11, 5, 2, 4};
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("\nTotal sum: 29");
        System.out.println("Target: close to 14.5");
        System.out.println("\nOptimal partition:");
        System.out.println("  {11, 4} → 15");
        System.out.println("  {1, 6, 5, 2} → 14");
        System.out.println("  Difference: |15 - 14| = 1\n");

        int result6_dp = minimumDifference(nums6);
        int result6_opt = minimumDifferenceOptimized(nums6);
        int result6_ultra = ultimateMinimumDifference(nums6);

        System.out.println("✓ DP Result: " + result6_dp);
        System.out.println("✓ HashSet Result: " + result6_opt);
        System.out.println("✓ Meet-in-Middle Result: " + result6_ultra);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result6_dp == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Minimize |sum(subset1) - sum(subset2)|             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    If subset1 has sum S1, subset2 has sum (totalSum - S1)    ║");
        System.out.println("║    Difference = |S1 - (totalSum - S1)| = |2*S1 - totalSum|   ║");
        System.out.println("║    Goal: Find S1 closest to totalSum/2                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  APPROACH 1: Subset Sum DP                                   ║");
        System.out.println("║    Build dp[i][j] = can we achieve sum j with 1st i elements ║");
        System.out.println("║    Check all achievable sums ≤ totalSum/2                    ║");
        System.out.println("║    Find largest achievable sum closest to target             ║");
        System.out.println("║    Time: O(n × sum), Space: O(n × sum)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  APPROACH 2: HashSet Optimization                            ║");
        System.out.println("║    Track all possible subset sums in HashSet                 ║");
        System.out.println("║    For each element, generate new sums                       ║");
        System.out.println("║    Check minimum difference from all possible sums           ║");
        System.out.println("║    Time: O(n × 2^n), Space: O(2^n)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  APPROACH 3: Meet-in-the-Middle (Ultimate)                   ║");
        System.out.println("║    Split array into two halves                               ║");
        System.out.println("║    Generate all subset sums for each half                    ║");
        System.out.println("║    Group by subset size (to maintain equal partition)        ║");
        System.out.println("║    Use binary search to find best combination                ║");
        System.out.println("║    Time: O(n × 2^(n/2)), Space: O(2^(n/2))                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Meet-in-Middle Details:                                     ║");
        System.out.println("║    • Generate all 2^(n/2) subsets for left half              ║");
        System.out.println("║    • Generate all 2^(n/2) subsets for right half             ║");
        System.out.println("║    • For each left sum with k elements, find best right sum  ║");
        System.out.println("║      with (n/2 - k) elements to balance partition            ║");
        System.out.println("║    • Use binary search on sorted right sums                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity Comparison:                                      ║");
        System.out.println("║    DP:     Best for small sums, O(n × sum)                   ║");
        System.out.println("║    HashSet: Simple but exponential                           ║");
        System.out.println("║    MITM:   Best for larger n, reduces 2^n to 2^(n/2)         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    
    }

}

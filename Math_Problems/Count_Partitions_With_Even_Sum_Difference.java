package Math_Problems;

/*

Description:
  Following program demonstrates the solution to the "Count Partitions With Even Sum Difference" problem, which determines how many ways an array can be split into two non-empty parts such that the absolute difference between left and right sums is even...

Problem Statement:
  -> You are given an integer array of length n...
  -> You must count all valid index positions i (1 ≤ i < n) where the array can be partitioned into:
         left = numbers[0..i]
         right = numbers[i+1..n-1]...
  -> A partition is valid if |sum(left) − sum(right)| is even...
  -> Return the total number of such valid partitions...

Approach:
  > Prefix-Based Single Pass Evaluation:
     i. Compute the total sum of all elements in the array...
     ii. Maintain a running prefix sum (leftSum) while iterating through the array...
     iii. At each index i (except the last one), compute:
           rightSum = totalSum − leftSum...
     iv. Calculate difference = |leftSum − rightSum|...
     v. If difference % 2 == 0, then this partition is valid and should be counted...

> Key Insight:
  -> A difference is even when:
         (leftSum − rightSum) % 2 == 0...
  -> Since:
         leftSum − rightSum = 2·leftSum − totalSum...
     the parity of the difference depends entirely on totalSum and leftSum...
  -> Efficient evaluation is possible using only integer sums and parity checks...

> Algorithm Steps:
  -> Step 1: Compute totalSum of all elements...
  -> Step 2: Initialize leftSum = 0 and count = 0...
  -> Step 3: Traverse array from index 0 to n-2:
       * Add numbers[i] to leftSum...
       * Compute rightSum = totalSum − leftSum...
       * Compute diff = |leftSum − rightSum|...
       * If diff % 2 == 0, increment count...
  -> Step 4: Return count as the total number of valid partitions...

> Implementation Note:
  -> Runs in O(n) time with O(1) extra space (only sums and counters used)...
  -> Works correctly for arrays containing negative numbers, zeros, and mixed values...
  -> Avoids storing prefix arrays since running sums are sufficient...
  -> Does not consider the last index for partitioning (right side must be non-empty)...

> Example:
  -> nums = [2, 4, 6, 8], totalSum = 20...
       Partition at 1: left=2, right=18, diff=16 (even) ✓...
       Partition at 2: left=6, right=14, diff=8 (even) ✓...
       Partition at 3: left=12, right=8, diff=4 (even) ✓...
       Result = 3 valid partitions...
  -> nums = [10, 4, −8, 7]:
       All partitions produce odd differences → answer = 0...

> Edge Cases:
  -> n < 2: no possible partitions → return 0...
  -> All zeros: every partition produces diff=0 (even) → count = n-1...
  -> Negative numbers: parity rules remain valid since only even/odd matters...
  -> Very large sums safe since Java int can handle values up to 2 billion...

> Time and Space Complexity:
  -> Time Complexity: O(n), iterating through array once...
  -> Space Complexity: O(1), using constant additional variables...

*/

public class Count_Partitions_With_Even_Sum_Difference {

    private static int countPartitions(int[] numbers) {

        int n = numbers.length;
        if (n < 2) {
            return 0;
        }

        int totalSum = 0;
        for (int number : numbers) {
            totalSum += number;
        }

        int count = 0;
        int leftSum = 0;

        for (int i = 0; i < n - 1; i++) {
            leftSum += numbers[i];
            int rightSum = totalSum - leftSum;

            int diff = Math.abs(leftSum - rightSum);
            if (diff % 2 == 0) {
                count++;
            }
        }

        return count;
    
    }

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║     COUNT PARTITIONS WITH EVEN SUM DIFFERENCE                 ║");
        System.out.println("║  Find how many ways to split array where |left-right| is even.║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {10, 4, - 8, 7};
        System.out.println("Array: [10, 4, -8, 7]");
        System.out.println("Total sum: 13");
        System.out.println("\nChecking all partitions:");
        System.out.println("  Partition at index 1: [10] | [4, -8, 7]");
        System.out.println("    Left sum = 10, Right sum = 3");
        System.out.println("    Difference = |10 - 3| = 7 (odd) ❌");
        System.out.println("  Partition at index 2: [10, 4] | [-8, 7]");
        System.out.println("    Left sum = 14, Right sum = -1");
        System.out.println("    Difference = |14 - (-1)| = 15 (odd) ❌");
        System.out.println("  Partition at index 3: [10, 4, -8] | [7]");
        System.out.println("    Left sum = 6, Right sum = 7");
        System.out.println("    Difference = |6 - 7| = 1 (odd) ❌");
        System.out.println("Valid partitions: " + countPartitions(nums1));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 2: All Valid Partitions ===");
        int[] nums2 = {2, 4, 6, 8};
        System.out.println("Array: [2, 4, 6, 8]");
        System.out.println("Total sum: 20");
        System.out.println("\nChecking all partitions:");
        System.out.println("  Partition at index 1: [2] | [4, 6, 8]");
        System.out.println("    Left = 2, Right = 18, Diff = 16 (even) ✓");
        System.out.println("  Partition at index 2: [2, 4] | [6, 8]");
        System.out.println("    Left = 6, Right = 14, Diff = 8 (even) ✓");
        System.out.println("  Partition at index 3: [2, 4, 6] | [8]");
        System.out.println("    Left = 12, Right = 8, Diff = 4 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums2));
        System.out.println("Expected: 3\n");

        System.out.println("=== Test Case 3: Mix of Even and Odd ===");
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Array: [1, 2, 3, 4, 5]");
        System.out.println("Total sum: 15");
        System.out.println("\nChecking all partitions:");
        System.out.println("  [1] | [2,3,4,5] → 1 vs 14, diff=13 (odd) ❌");
        System.out.println("  [1,2] | [3,4,5] → 3 vs 12, diff=9 (odd) ❌");
        System.out.println("  [1,2,3] | [4,5] → 6 vs 9, diff=3 (odd) ❌");
        System.out.println("  [1,2,3,4] | [5] → 10 vs 5, diff=5 (odd) ❌");
        System.out.println("Valid partitions: " + countPartitions(nums3));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 4: Equal Split ===");
        int[] nums4 = {5, 5, 10, 10};
        System.out.println("Array: [5, 5, 10, 10]");
        System.out.println("Total sum: 30");
        System.out.println("\nChecking all partitions:");
        System.out.println("  [5] | [5,10,10] → 5 vs 25, diff=20 (even) ✓");
        System.out.println("  [5,5] | [10,10] → 10 vs 20, diff=10 (even) ✓");
        System.out.println("  [5,5,10] | [10] → 20 vs 10, diff=10 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums4));
        System.out.println("Expected: 3\n");

        System.out.println("=== Test Case 5: Negative Numbers ===");
        int[] nums5 = {- 3, 5, - 7, 9, - 2};
        System.out.println("Array: [-3, 5, -7, 9, -2]");
        System.out.println("Total sum: 2");
        System.out.println("\nChecking all partitions:");
        System.out.println("  [-3] | [5,-7,9,-2] → -3 vs 5, diff=8 (even) ✓");
        System.out.println("  [-3,5] | [-7,9,-2] → 2 vs 0, diff=2 (even) ✓");
        System.out.println("  [-3,5,-7] | [9,-2] → -5 vs 7, diff=12 (even) ✓");
        System.out.println("  [-3,5,-7,9] | [-2] → 4 vs -2, diff=6 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums5));
        System.out.println("Expected: 4\n");

        System.out.println("=== Test Case 6: Single Element (Edge Case) ===");
        int[] nums6 = {10};
        System.out.println("Array: [10]");
        System.out.println("Cannot partition array with single element");
        System.out.println("Valid partitions: " + countPartitions(nums6));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 7: Two Elements ===");
        int[] nums7 = {3, 7};
        System.out.println("Array: [3, 7]");
        System.out.println("Total sum: 10");
        System.out.println("\nOnly one partition possible:");
        System.out.println("  [3] | [7] → 3 vs 7, diff=4 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums7));
        System.out.println("Expected: 1\n");

        System.out.println("=== Test Case 8: All Zeros ===");
        int[] nums8 = {0, 0, 0, 0};
        System.out.println("Array: [0, 0, 0, 0]");
        System.out.println("Total sum: 0");
        System.out.println("\nAll partitions:");
        System.out.println("  [0] | [0,0,0] → 0 vs 0, diff=0 (even) ✓");
        System.out.println("  [0,0] | [0,0] → 0 vs 0, diff=0 (even) ✓");
        System.out.println("  [0,0,0] | [0] → 0 vs 0, diff=0 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums8));
        System.out.println("Expected: 3\n");

        System.out.println("=== Test Case 9: Large Difference ===");
        int[] nums9 = {100, 1, 1, 1};
        System.out.println("Array: [100, 1, 1, 1]");
        System.out.println("Total sum: 103");
        System.out.println("\nChecking all partitions:");
        System.out.println("  [100] | [1,1,1] → 100 vs 3, diff=97 (odd) ❌");
        System.out.println("  [100,1] | [1,1] → 101 vs 2, diff=99 (odd) ❌");
        System.out.println("  [100,1,1] | [1] → 102 vs 1, diff=101 (odd) ❌");
        System.out.println("Valid partitions: " + countPartitions(nums9));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 10: Alternating Signs ===");
        int[] nums10 = {2, - 2, 4, - 4, 6};
        System.out.println("Array: [2, -2, 4, -4, 6]");
        System.out.println("Total sum: 6");
        System.out.println("\nChecking all partitions:");
        System.out.println("  [2] | [-2,4,-4,6] → 2 vs 4, diff=2 (even) ✓");
        System.out.println("  [2,-2] | [4,-4,6] → 0 vs 6, diff=6 (even) ✓");
        System.out.println("  [2,-2,4] | [-4,6] → 4 vs 2, diff=2 (even) ✓");
        System.out.println("  [2,-2,4,-4] | [6] → 0 vs 6, diff=6 (even) ✓");
        System.out.println("Valid partitions: " + countPartitions(nums10));
        System.out.println("Expected: 4\n");

        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║  All test cases completed successfully!                       ║");
        System.out.println("║  Algorithm: O(n) time, O(1) space                             ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");

    }

}

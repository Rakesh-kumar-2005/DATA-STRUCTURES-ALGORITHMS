package Math_Problems;

/*

Description:
  Following program demonstrates the solution to the "Minimum Operations To Make Array Sum Divisible By K" problem using simple modular arithmetic to determine the smallest number of increments or decrements needed to make the array sum divisible by k...

Problem Statement:
  -> You are given an integer array and an integer k...
  -> The task is to determine the minimum number of operations needed to make the total sum divisible by k...
  -> One operation consists of adding or subtracting 1 from any element (i.e., adjusting the total sum by ±1)...
  -> Return the minimum number of such operations required...

Approach:
  > Modular Arithmetic Based Calculation:
     i. Compute the total sum of all elements in the array...
     ii. Determine the remainder when the sum is divided by k...
     iii. If remainder is zero, no operations are required...
     iv. Otherwise, two choices exist:
          - Increase the sum to the next multiple of k...
          - Decrease the sum to the previous multiple of k...
     v. The minimum operations required is: min(remainder, k - remainder)...

> Key Insight:
  -> Only the remainder matters, not individual array values...
  -> If remainder = r:
       - You can add (k - r) to reach next multiple of k...
       - You can subtract r to reach previous multiple of k...
  -> Best choice is whichever requires fewer operations...
  -> This uses the mathematical fact:
       sum % k = r  ⇒  minimum operations = min(r, k - r)...

> Algorithm Steps:
  -> Initialize sum = 0...
  -> Add all elements of the array to compute total sum...
  -> Compute remainder = sum % k...
  -> If remainder == 0:
       * Return 0 (already divisible)...
  -> Else:
       * Return Math.min(remainder, k - remainder)...

> Implementation Note:
  -> This solution is extremely efficient since it does not require prefix sums or subarray manipulation...
  -> Only computes one sum and applies modular arithmetic...
  -> Handles large values safely since Java int can handle sums up to 2 billion (or change to long if needed)...
  -> The method is optimal and works for all valid inputs...

> Example:
  -> For nums = [1, 2, 3], k = 4:
       sum = 6 → 6 % 4 = 2...
       remainder = 2...
       min operations = min(2, 4 - 2 = 2) = 2...
  -> For nums = [1, 2, 3, 4], k = 5:
       sum = 10 → 10 % 5 = 0...
       already divisible → answer = 0...
  -> For nums = [2, 3, 4], k = 5:
       sum = 9 → 9 % 5 = 4...
       min operations = min(4, 1) = 1...

> Edge Cases:
  -> Array sum already divisible → result = 0...
  -> Single element case handled directly by modulo check...
  -> Large numbers work correctly as only sum % k is used...
  -> Works for any k ≥ 1 (k = 1 always returns 0)...

> Time and Space Complexity:
  -> Time Complexity: O(n), for summing n elements...
  -> Space Complexity: O(1), constant extra space...

*/

public class Minimum_Operations_To_Make_Array_Sum_Divisible_By_K {

    private static int minOperations(int[] numbers, int k) {
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        int remainder = sum % k;

        return Math.min(remainder, k - remainder);

    }

    public static void main(String[] args) {

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {1, 2, 3, 4};
        int k1 = 5;

        System.out.println("Array: [1, 2, 3, 4], k = 5");
        System.out.println("Sum: 1 + 2 + 3 + 4 = 10");
        System.out.println("Remainder: 10 % 5 = 0");

        System.out.println("Already divisible! Need 0 operations");
        System.out.println("Minimum operations: " + minOperations(nums1, k1));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 2: Need to Add Elements ===");
        int[] nums2 = {1, 2, 3};
        int k2 = 4;

        System.out.println("Array: [1, 2, 3], k = 4");
        System.out.println("Sum: 1 + 2 + 3 = 6");
        System.out.println("Remainder: 6 % 4 = 2");

        System.out.println("To make sum divisible by 4:");
        System.out.println("  Option 1: Add 2 more → new sum = 8 (divisible by 4) ✓");
        System.out.println("  Option 2: Subtract elements until sum is divisible");
        System.out.println("Need 2 operations to reach next multiple of 4");
        System.out.println("Minimum operations: " + minOperations(nums2, k2));
        System.out.println("Expected: 2\n");

        System.out.println("=== Test Case 3: One Operation Needed ===");
        int[] nums3 = {5, 6, 7};
        int k3 = 6;

        System.out.println("Array: [5, 6, 7], k = 6");
        System.out.println("Sum: 5 + 6 + 7 = 18");
        System.out.println("Remainder: 18 % 6 = 0");

        System.out.println("Already divisible by 6!");
        System.out.println("Minimum operations: " + minOperations(nums3, k3));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 4: Large Remainder ===");
        int[] nums4 = {1, 1, 1, 1};
        int k4 = 7;

        System.out.println("Array: [1, 1, 1, 1], k = 7");
        System.out.println("Sum: 1 + 1 + 1 + 1 = 4");
        System.out.println("Remainder: 4 % 7 = 4");

        System.out.println("Current sum = 4, need to reach 7 or 0");
        System.out.println("Add 3 more to get sum = 7 (divisible)");
        System.out.println("Minimum operations: " + minOperations(nums4, k4));
        System.out.println("Expected: 3 (or 4 to go from 4→0, but 3 is better)\n");

        System.out.println("=== Test Case 5: k = 3 Example ===");
        int[] nums5 = {10, 11, 12};
        int k5 = 3;

        System.out.println("Array: [10, 11, 12], k = 3");
        System.out.println("Sum: 10 + 11 + 12 = 33");
        System.out.println("Remainder: 33 % 3 = 0");

        System.out.println("Already divisible by 3!");
        System.out.println("Minimum operations: " + minOperations(nums5, k5));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 6: Small k ===");
        int[] nums6 = {7, 8};
        int k6 = 2;

        System.out.println("Array: [7, 8], k = 2");
        System.out.println("Sum: 7 + 8 = 15");
        System.out.println("Remainder: 15 % 2 = 1");

        System.out.println("Need 1 operation to make it even (divisible by 2)");
        System.out.println("Add 1 → sum becomes 16 (divisible by 2)");
        System.out.println("Minimum operations: " + minOperations(nums6, k6));
        System.out.println("Expected: 1\n");

        System.out.println("=== Test Case 7: Single Element ===");
        int[] nums7 = {5};
        int k7 = 3;

        System.out.println("Array: [5], k = 3");
        System.out.println("Sum: 5");
        System.out.println("Remainder: 5 % 3 = 2");

        System.out.println("Need to add 1 element to reach 6 (divisible by 3)");
        System.out.println("Minimum operations: " + minOperations(nums7, k7));
        System.out.println("Expected: 1\n");

        System.out.println("=== Test Case 8: All Same Numbers ===");
        int[] nums8 = {3, 3, 3, 3, 3};
        int k8 = 5;

        System.out.println("Array: [3, 3, 3, 3, 3], k = 5");
        System.out.println("Sum: 3 × 5 = 15");
        System.out.println("Remainder: 15 % 5 = 0");

        System.out.println("Already divisible by 5!");
        System.out.println("Minimum operations: " + minOperations(nums8, k8));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 9: k-1 Remainder ===");
        int[] nums9 = {2, 3, 4};
        int k9 = 5;

        System.out.println("Array: [2, 3, 4], k = 5");
        System.out.println("Sum: 2 + 3 + 4 = 9");
        System.out.println("Remainder: 9 % 5 = 4");

        System.out.println("Need 1 more to reach 10 (divisible by 5)");
        System.out.println("Or need 4 operations to go backward to 5");
        System.out.println("Minimum = min(1, 4) = 1");
        System.out.println("Minimum operations: " + minOperations(nums9, k9));
        System.out.println("Expected: 1 (but code returns 4 - see note below)\n");

        System.out.println("=== Test Case 10: Large Numbers ===");
        int[] nums10 = {100, 200, 300};
        int k10 = 10;

        System.out.println("Array: [100, 200, 300], k = 10");
        System.out.println("Sum: 100 + 200 + 300 = 600");
        System.out.println("Remainder: 600 % 10 = 0");

        System.out.println("Already divisible by 10!");
        System.out.println("Minimum operations: " + minOperations(nums10, k10));
        System.out.println("Expected: 0\n");

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  NOTE: Current implementation returns remainder directly ║");
        System.out.println("║  This works for most cases but might not be optimal      ║");
        System.out.println("║  when remainder > k/2                                    ║");
        System.out.println("║                                                          ║");
        System.out.println("║  For optimal solution, use:                              ║");
        System.out.println("║  return Math.min(remainder, k - remainder)               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

    }

}

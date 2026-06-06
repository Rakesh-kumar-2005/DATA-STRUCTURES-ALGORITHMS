package Arrays;

/*

    Description:
      Following program computes the left-right sum difference array where each
        position stores the absolute difference between its right sum and left sum...

    Problem Statement:
      -> Given an integer array nums of size n...
      -> For each index i, compute leftSum and rightSum:
           leftSum  = sum of all elements strictly before index i...
           rightSum = sum of all elements strictly after index i...
      -> Store result[i] = |rightSum - leftSum| for each index...
      -> Return the result array...

    Key Insight:
      -> Recomputing leftSum and rightSum from scratch for each index is O(n²)...
      -> Instead, maintain running sums updated incrementally in a single pass...
      -> Initialize rightSum as total sum of all elements...
      -> At each index: subtract current element from rightSum first, then compute...
      -> After computing, add current element to leftSum for the next iteration...

    Example:
      -> nums = [10, 4, 8, 3], total = 25:
           i=0: rightSum = 25-10 = 15, leftSum = 0,  result = |15-0|  = 15...
           i=1: rightSum = 15-4  = 11, leftSum = 10, result = |11-10| = 1...
           i=2: rightSum = 11-8  = 3,  leftSum = 14, result = |3-14|  = 11...
           i=3: rightSum = 3-3   = 0,  leftSum = 22, result = |0-22|  = 22...
           Output: [15, 1, 11, 22]...

    Order of Operations Per Index (Critical):
      -> Step 1: rightSum -= nums[i]...
                 rightSum now represents sum of elements strictly after i...
      -> Step 2: result[i] = Math.abs(rightSum - leftSum)...
                 Both sums now correctly exclude index i...
      -> Step 3: leftSum += nums[i]...
                 leftSum now includes index i, ready for next iteration...
      -> The subtraction must happen before the result, and addition after...
      -> Swapping this order would incorrectly include nums[i] in one of the sums...

    Why rightSum Is Initialized to Total Sum:
      -> Before any iteration, all elements lie to the right of index -1...
      -> Subtracting nums[0] at i=0 gives the correct sum after index 0...
      -> This avoids a separate preprocessing pass for rightSum values...
      -> leftSum starts at 0 since no elements exist before index 0...

    Naive vs Optimized Approach:
      -> Naive O(n²):
           For each index i, loop 0 to i-1 for leftSum...
           Loop i+1 to n-1 for rightSum...
           Two nested loops, recomputing everything each time...
      -> Optimized O(n):
           One pass to compute total sum as initial rightSum...
           One pass to update both running sums and compute results...
           Each element processed exactly twice total...

    Step-by-Step Trace (nums = [5, 0, 3, 0, 2], total = 10):
      -> i=0: rightSum = 10-5 = 5,  leftSum = 0, result = |5-0|  = 5,  leftSum = 5...
      -> i=1: rightSum = 5-0  = 5,  leftSum = 5, result = |5-5|  = 0,  leftSum = 5...
      -> i=2: rightSum = 5-3  = 2,  leftSum = 5, result = |2-5|  = 3,  leftSum = 8...
      -> i=3: rightSum = 2-0  = 2,  leftSum = 8, result = |2-8|  = 6,  leftSum = 8...
      -> i=4: rightSum = 2-2  = 0,  leftSum = 8, result = |0-8|  = 8,  leftSum = 10...
      -> Output: [5, 0, 3, 6, 8]...

    Edge Cases:
      -> Single element → leftSum = 0, rightSum = 0 → result = [0]...
      -> All zeros → all differences are 0 → result is all zeros...
      -> Symmetric array (5,3,3,5) → result array is also symmetric...
      -> Zeros in array → contribute 0 to sums but shift running totals normally...
      -> Negative numbers → Math.abs() ensures non-negative result regardless...

    Why Math.abs() Is Necessary:
      -> rightSum may be less than leftSum at later indices...
      -> Without abs, results would be negative for right-heavy positions...
      -> Example: i=2 in [10,4,8,3]: rightSum=3, leftSum=14 → 3-14 = -11 without abs...

    Time and Space Complexity:
      -> Time:  O(n) — two linear passes through the array...
      -> Space: O(n) — result array of size n (input and output not counted extra)...

    Applications:
      -> Balance point detection in arrays and sequences...
      -> Prefix and suffix sum problems in competitive programming...
      -> Load balancing analysis across distributed positions...
      -> Signal asymmetry detection in data stream processing...

*/

public class Left_And_Right_Sum_Differences {

    private static int[] leftRightDifference(int[] nums) {

        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }

        int n = nums.length;
        int leftSum = 0;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            rightSum -= nums[i];
            result[i] = Math.abs(rightSum - leftSum);
            leftSum += nums[i];
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
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private static boolean arraysEqual(int[] arr1, int[] arr2) {

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           LEFT AND RIGHT SUM DIFFERENCES                     ║");
        System.out.println("║  For each position i, find |rightSum - leftSum|              ║");
        System.out.println("║  leftSum = sum of elements before i                          ║");
        System.out.println("║  rightSum = sum of elements after i                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {10, 4, 8, 3};
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("Total sum: 10 + 4 + 8 + 3 = 25\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=25-10=15 → |15-0| = 15");
        System.out.println("  i=1: leftSum=10, rightSum=15-4=11 → |11-10| = 1");
        System.out.println("  i=2: leftSum=14, rightSum=11-8=3 → |3-14| = 11");
        System.out.println("  i=3: leftSum=22, rightSum=3-3=0 → |0-22| = 22\n");

        int[] result1 = leftRightDifference(nums1);
        int[] expected1 = {15, 1, 11, 22};
        System.out.println("✓ Result: " + arrayToString(result1));
        System.out.println("  Expected: " + arrayToString(expected1));
        System.out.println("  Status: " + (arraysEqual(result1, expected1) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Element ===");
        int[] nums2 = {5};
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("\nPosition analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=0 → |0-0| = 0\n");

        int[] result2 = leftRightDifference(nums2);
        int[] expected2 = {0};
        System.out.println("✓ Result: " + arrayToString(result2));
        System.out.println("  Expected: " + arrayToString(expected2));
        System.out.println("  Status: " + (arraysEqual(result2, expected2) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: All Same Values ===");
        int[] nums3 = {2, 2, 2, 2};
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("Total sum: 8\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=8-2=6 → |6-0| = 6");
        System.out.println("  i=1: leftSum=2, rightSum=6-2=4 → |4-2| = 2");
        System.out.println("  i=2: leftSum=4, rightSum=4-2=2 → |2-4| = 2");
        System.out.println("  i=3: leftSum=6, rightSum=2-2=0 → |0-6| = 6\n");

        int[] result3 = leftRightDifference(nums3);
        int[] expected3 = {6, 2, 2, 6};
        System.out.println("✓ Result: " + arrayToString(result3));
        System.out.println("  Expected: " + arrayToString(expected3));
        System.out.println("  Status: " + (arraysEqual(result3, expected3) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Balanced Array ===");
        int[] nums4 = {5, 3, 3, 5};
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("Total sum: 16\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=16-5=11 → |11-0| = 11");
        System.out.println("  i=1: leftSum=5, rightSum=11-3=8 → |8-5| = 3");
        System.out.println("  i=2: leftSum=8, rightSum=8-3=5 → |5-8| = 3");
        System.out.println("  i=3: leftSum=11, rightSum=5-5=0 → |0-11| = 11\n");

        int[] result4 = leftRightDifference(nums4);
        int[] expected4 = {11, 3, 3, 11};
        System.out.println("✓ Result: " + arrayToString(result4));
        System.out.println("  Expected: " + arrayToString(expected4));
        System.out.println("  Status: " + (arraysEqual(result4, expected4) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: With Zeros ===");
        int[] nums5 = {5, 0, 3, 0, 2};
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("Total sum: 10\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=10-5=5 → |5-0| = 5");
        System.out.println("  i=1: leftSum=5, rightSum=5-0=5 → |5-5| = 0");
        System.out.println("  i=2: leftSum=5, rightSum=5-3=2 → |2-5| = 3");
        System.out.println("  i=3: leftSum=8, rightSum=2-0=2 → |2-8| = 6");
        System.out.println("  i=4: leftSum=8, rightSum=2-2=0 → |0-8| = 8\n");

        int[] result5 = leftRightDifference(nums5);
        int[] expected5 = {5, 0, 3, 6, 8};
        System.out.println("✓ Result: " + arrayToString(result5));
        System.out.println("  Expected: " + arrayToString(expected5));
        System.out.println("  Status: " + (arraysEqual(result5, expected5) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Negative Numbers ===");
        int[] nums6 = {- 5, 3, 2};
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("Total sum: 0\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=0-(-5)=5 → |5-0| = 5");
        System.out.println("  i=1: leftSum=-5, rightSum=5-3=2 → |2-(-5)| = 7");
        System.out.println("  i=2: leftSum=-2, rightSum=2-2=0 → |0-(-2)| = 2\n");

        int[] result6 = leftRightDifference(nums6);
        int[] expected6 = {5, 7, 2};
        System.out.println("✓ Result: " + arrayToString(result6));
        System.out.println("  Expected: " + arrayToString(expected6));
        System.out.println("  Status: " + (arraysEqual(result6, expected6) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Two Elements ===");
        int[] nums7 = {1, 10};
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("Total sum: 11\n");
        System.out.println("Position analysis:");
        System.out.println("  i=0: leftSum=0, rightSum=11-1=10 → |10-0| = 10");
        System.out.println("  i=1: leftSum=1, rightSum=10-10=0 → |0-1| = 1\n");

        int[] result7 = leftRightDifference(nums7);
        int[] expected7 = {10, 1};
        System.out.println("✓ Result: " + arrayToString(result7));
        System.out.println("  Expected: " + arrayToString(expected7));
        System.out.println("  Status: " + (arraysEqual(result7, expected7) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: For each position, calculate |rightSum - leftSum|  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Definition:                                                 ║");
        System.out.println("║    leftSum = sum of all elements before index i              ║");
        System.out.println("║    rightSum = sum of all elements after index i              ║");
        System.out.println("║    result[i] = |rightSum - leftSum|                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Naive Approach O(n²):                                       ║");
        System.out.println("║    For each position i:                                      ║");
        System.out.println("║      Calculate leftSum by summing 0 to i-1                   ║");
        System.out.println("║      Calculate rightSum by summing i+1 to n-1                ║");
        System.out.println("║      Calculate difference                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimized Approach O(n):                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Calculate total sum (all elements)                  ║");
        System.out.println("║    rightSum = total sum                                      ║");
        System.out.println("║    leftSum = 0                                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: For each position i (left to right):                ║");
        System.out.println("║    a. Remove current element from rightSum:                  ║");
        System.out.println("║       rightSum -= nums[i]                                    ║");
        System.out.println("║       (Now rightSum = sum after i)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║    b. Calculate difference:                                  ║");
        System.out.println("║       result[i] = |rightSum - leftSum|                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    c. Add current element to leftSum:                        ║");
        System.out.println("║       leftSum += nums[i]                                     ║");
        System.out.println("║       (Now leftSum = sum before i+1)                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: nums = [10, 4, 8, 3]                               ║");
        System.out.println("║    Total = 25                                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=0:                                                      ║");
        System.out.println("║      rightSum = 25 - 10 = 15 (sum after 10)                  ║");
        System.out.println("║      difference = |15 - 0| = 15                              ║");
        System.out.println("║      leftSum = 0 + 10 = 10 (sum before next)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=1:                                                      ║");
        System.out.println("║      rightSum = 15 - 4 = 11 (sum after 4)                    ║");
        System.out.println("║      difference = |11 - 10| = 1                              ║");
        System.out.println("║      leftSum = 10 + 4 = 14 (sum before next)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=2:                                                      ║");
        System.out.println("║      rightSum = 11 - 8 = 3 (sum after 8)                     ║");
        System.out.println("║      difference = |3 - 14| = 11                              ║");
        System.out.println("║      leftSum = 14 + 8 = 22 (sum before next)                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=3:                                                      ║");
        System.out.println("║      rightSum = 3 - 3 = 0 (no elements after)                ║");
        System.out.println("║      difference = |0 - 22| = 22                              ║");
        System.out.println("║      leftSum = 22 + 3 = 25 (all elements)                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Result: [15, 1, 11, 22]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Points:                                                 ║");
        System.out.println("║    • Single pass through array after initial sum             ║");
        System.out.println("║    • No nested loops → O(n) time                             ║");
        System.out.println("║    • Track running sums as we move through array             ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Two passes through array                    ║");
        System.out.println("║    Space: O(n) - Result array (not counting input/output)    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}

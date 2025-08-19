package Arrays;

/*

Description:
    -> This program counts the total number of subarrays in an integer array that are completely filled with zeros.
    -> A subarray is a contiguous part of the array.
    -> Example: For nums = {1, 3, 0, 0, 2, 0, 0, 4}, the zero-filled subarrays are:
       {0}, {0}, {0,0}, {0}, {0}, {0,0}.
       Hence, total count = 6.

Problem Statement:
    -> Given an integer array nums, return the total number of contiguous subarrays filled only with zeros.

Approach:
    -> Use a linear scan with two variables:
        - streak: counts the number of consecutive zeros up to the current index.
        - count: accumulates the total number of zero-filled subarrays.
    -> If the current element is zero:
        - Increment streak by 1.
        - Add streak to count (because each new zero extends all previous zero subarrays).
    -> If the current element is non-zero:
        - Reset streak to 0.

Algorithm Steps:
    1. Initialize count = 0, streak = 0.
    2. Traverse the array:
        a. If num == 0:
            - streak = streak + 1
            - count += streak
        b. Else:
            - streak = 0
    3. Return count.

Key Observations:
    -> A sequence of k consecutive zeros contributes:
           (k * (k + 1)) / 2 subarrays.
       Example: For k = 2 (two consecutive zeros) → subarrays = 3 ({0}, {0}, {0,0}).
    -> The algorithm calculates this incrementally instead of recomputing every time.

Time and Space Complexity:
    -> Time Complexity: O(n), as the array is traversed once.
    -> Space Complexity: O(1), only uses constant extra variables.

Example:
    Input:
        nums = {1, 3, 0, 0, 2, 0, 0, 4}
    Output:
        Total number of sub-arrays filled with Zero = 6

*/

public class Number_Of_Zero_Filled_Subarrays {

    private static long zeroFilledSubarray(int[] nums) {

        long count = 0;
        long streak = 0;

        for (int num : nums) {
            streak = (num == 0) ? streak + 1 : 0;
            count += streak;
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 0, 0, 2, 0, 0, 4};
        System.out.println("Total number of sub-arrays filled with Zero = " + zeroFilledSubarray(nums));

    }

}

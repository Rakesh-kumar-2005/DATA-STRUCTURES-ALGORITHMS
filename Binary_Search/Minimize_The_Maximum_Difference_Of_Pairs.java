package Binary_Search;

/*

Description:
    -> This program minimizes the **maximum difference** among selected pairs of integers from a given array...
    -> The goal is to form exactly `k` pairs such that the **largest difference** within any chosen pair is as small as possible...

Problem Statement:
    -> Given an array of integers `nums` and an integer `pairs`, form `pairs` number of **non-overlapping pairs**...
    -> Each pair must consist of two adjacent elements (after sorting), and the objective is to **minimize the maximum difference** among all chosen pairs...

Approach:
    > Binary Search on Answer Space:
        -> Sort the array to bring close numbers together...
        -> Perform binary search on the **maximum difference value** (from -1 to 1e9+7)...
        -> For each candidate maximum `mid`, count how many pairs can be formed such that their difference is ≤ `mid`...

    > Greedy Pairing Strategy:
        -> Traverse the sorted array from left to right...
        -> If two consecutive elements differ by ≤ `mid`, count it as a valid pair and skip the next element (to avoid overlap)...
        -> If the count of valid pairs ≥ required `pairs`, the current `mid` is a valid answer...

Algorithm Steps:
    -> Step 1: Sort the input array `nums`...
    -> Step 2: Initialize `low = -1`, `high = 1e9 + 7` (search space for answer)...
    -> Step 3: While `low < high - 1`:
        a. Set `mid = (low + high) / 2`...
        b. Count number of valid pairs with difference ≤ `mid`...
        c. If count ≥ `pairs`, update `high = mid` (try smaller difference)...
        d. Else, update `low = mid` (need to allow larger difference)...

    -> Step 4: Return `high` as the minimized maximum difference...

Key Characteristics:
    -> Uses binary search to reduce time complexity...
    -> Greedy pairing ensures minimal maximum difference among pairs...
    -> Avoids brute-force checking of all pair combinations...

Time and Space Complexity:
    -> Time Complexity: O(n log(maxDiff)), where `n` is array length and `maxDiff` is the difference range (~1e9)...
    -> Space Complexity: O(1), ignoring sorting...

Demonstration:
    -> Input: nums = [1, 3, 5, 9], pairs = 2
    -> Sorted: [1, 3, 5, 9]
    -> Valid pairs (for minimized max diff = 4): (1,3), (5,9)
    -> Output: 4

*/

import java.util.Arrays;

public class Minimize_The_Maximum_Difference_Of_Pairs {

    private static int minimizeMax(int[] nums, int pairs) {

        Arrays.sort(nums);
        int low = - 1;
        int high = (int) 1e9 + 7;

        while (low < high - 1) {

            int mid = low + (high - low) / 2;
            int count = 0;

            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i + 1] - nums[i] <= mid) {
                    count++;
                    i++;
                }
            }

            if (count >= pairs) {
                high = mid;
            } else {
                low = mid;
            }

        }

        return high;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 9};
        int pairs = 2;
        System.out.println("The minimized maximum difference of pairs is = " + minimizeMax(nums, pairs));

    }

}

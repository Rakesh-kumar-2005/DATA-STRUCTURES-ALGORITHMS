package Arrays;

/*

Description:
    -> This program computes the sum of a range of sorted subarray sums from a given integer array...
    -> It first generates all possible subarray sums, sorts them, and then calculates the cumulative sum
       of elements between the given positions `left` and `right` (1-based indexing)...
    -> The final result is returned modulo 1e9+7 to prevent overflow...

Problem Statement:
    -> Given an integer array `nums` of length `n`, compute the sum of sorted subarray sums
       between indices `left` and `right` (inclusive)...
    -> Constraints:
         - Each subarray sum is computed for all contiguous subarrays...
         - The subarray sums are stored and sorted in ascending order...
         - The result must be modulo 1,000,000,007 (1e9+7)...

Approach:
    > Subarray Sum Generation and Sorting:
        -> Generate all possible contiguous subarrays and compute their sums...
        -> Store all subarray sums in an array of size `n*(n+1)/2` (total subarrays)...
        -> Sort this array in ascending order...
        -> Compute the sum of values from index `left-1` to `right-1` (0-based adjustment)...
        -> Apply modulo operation during accumulation to handle large sums...

Algorithm Steps:
    -> Initialization:
        1. Create an array `arr` of size `n*(n+1)/2` to hold subarray sums...

    -> Subarray Sum Calculation:
        1. For each starting index `i` from 0 to n-1:
            - Initialize `sum = 0`...
            - For each ending index `j` from i to n-1:
                - Add `nums[j]` to `sum`...
                - Store `sum` in `arr`...

    -> Sorting:
        1. Sort the array `arr` containing all subarray sums...

    -> Range Sum Calculation:
        1. Initialize `ans = 0` and `mod = 1e9+7`...
        2. For indices from `left-1` to `right-1`:
             - Add `arr[i]` to `ans` with modulo adjustment...

    -> Return:
        1. Return `ans` as the final answer...

Key Characteristics:
    -> Straightforward brute-force method using subarray sums and sorting...
    -> Handles modular arithmetic to avoid overflow issues...
    -> Guarantees correct results for any valid input of size `n`...

Time and Space Complexity:
    -> Time Complexity: O(n^2 + n^2 log n)
         - O(n^2) to compute all subarray sums...
         - O(n^2 log n) to sort them...
    -> Space Complexity: O(n^2) for storing all subarray sums...

Demonstration:
    -> Input: nums = [1, 2, 3, 4], n = 4, left = 1, right = 5
    -> Subarrays and their sums:
         [1] = 1, [1,2] = 3, [1,2,3] = 6, [1,2,3,4] = 10
         [2] = 2, [2,3] = 5, [2,3,4] = 9
         [3] = 3, [3,4] = 7
         [4] = 4
       → All subarray sums: [1, 3, 6, 10, 2, 5, 9, 3, 7, 4]
       → Sorted: [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]
    -> Range (left=1, right=5) → [1, 2, 3, 3, 4]
    -> Sum = 1 + 2 + 3 + 3 + 4 = 13
    -> Output: "The range sum of sorted subarray sums is : 13"

*/

import java.util.Arrays;

public class Range_Sum_Of_Sorted_Subarray_Sums {

    private static int rangeSum(int[] nums, int n, int left, int right) {

        int[] arr = new int[n * (n + 1) / 2];

        for (int i = 0, k = 0; i < n; ++ i) {
            int sum = 0;

            for (int j = i; j < n; ++ j) {
                sum += nums[j];
                arr[k++] = sum;
            }

        }

        Arrays.sort(arr);
        int ans = 0;
        final int mod = (int) 1e9 + 7;

        for (int i = left - 1; i < right; ++ i) {
            ans = (ans + arr[i]) % mod;
        }

        return ans;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        int left = 1;
        int right = 5;
        System.out.println("The range sum of sorted subarray sums is : " + rangeSum(arr, n, left, right));

    }

}

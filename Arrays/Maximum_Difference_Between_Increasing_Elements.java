package Arrays;

/*

Description:
    -> This program finds the **maximum difference** between two elements in an array such that the later element is **greater than** the earlier one and appears **after** it in the array...
    -> It ensures the condition `nums[j] > nums[i]` for `j > i` is satisfied before considering the difference...

Problem Statement:
    -> Given an array `nums`, find the maximum value of `nums[j] - nums[i]` such that `j > i` and `nums[j] > nums[i]`...
    -> If no such pair exists, return -1...

Approach:
    > Greedy Pointer Update:
        -> Use two pointers: `i` (potential minimum element) and `j` (current element)...
        -> For every `j`, check if `nums[j] > nums[i]`:
            --> If yes, calculate the difference and update maxDiff...
            --> If no, update `i = j` since we found a new potential minimum...

    > Efficient Traversal:
        -> Traverse the array once using a single loop...
        -> Maintain the smallest seen element from the left to compare with current element...

Algorithm Steps:
    -> Step 1: Initialize `maxDiff = -1` and `i = 0`...
    -> Step 2: Traverse from `j = 0` to `n - 1`:
        a. If `nums[j] > nums[i]`, update `maxDiff` with `nums[j] - nums[i]`...
        b. Else, update `i = j` as new potential start...
    -> Step 3: Return the maximum difference found or -1 if not found...

Key Characteristics:
    -> One-pass traversal with dynamic tracking of potential minimum...
    -> Ensures that element order is respected (`j > i`)...
    -> Avoids nested loops, resulting in efficient performance...

Time and Space Complexity:
    -> Time Complexity: O(n), where `n` is the number of elements in the array...
    -> Space Complexity: O(1), using constant extra space...

Demonstration:
    -> Input: nums = [7, 1, 5, 4, 6, 4]
    -> Pairs considered: (1,5)=4, (1,4)=3, (1,6)=5 → max = 5
    -> Output: 5

*/

public class Maximum_Difference_Between_Increasing_Elements {

    private static int maximumDifference(int[] nums) {

        int n = nums.length;
        int maxDiff = - 1;

        int i = 0;
        for (int j = 0; j < n; j++) {

            if (j > i && nums[j] > nums[i]) {
                int currDiff = nums[j] - nums[i];
                maxDiff = Math.max(maxDiff, currDiff);
            } else {
                i = j;
            }
            
        }

        return maxDiff;
    }

    public static void main(String[] args) {

        int[] nums = {7, 1, 5, 4, 6, 4};
        System.out.println("The maximum difference in the increasing elements is = " + maximumDifference(nums));
    }

}

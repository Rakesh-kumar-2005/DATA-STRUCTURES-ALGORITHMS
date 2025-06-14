package Arrays;

/*

Description:
    -> This program finds the maximum absolute difference between any two adjacent elements in a circular array...
    -> A circular array means the last and first elements are also considered adjacent...

Problem Statement:
    -> Given an integer array `nums`, consider it as circular...
    -> Compute the **maximum absolute difference** between any two **consecutive elements**, including the circular pair `nums[n - 1]` and `nums[0]`...

Approach:
    > Linear Scan with Circular Wrap:
        -> Traverse the array and calculate the absolute difference between each pair of adjacent elements...
        -> Keep updating a variable `maxDiff` to store the maximum of these differences...
        -> After the loop, check the difference between the **last and first** elements (circular edge) and compare it with `maxDiff`...

Algorithm Steps:
    -> Initialization:
        1. Set `maxDiff = 0` to hold the maximum adjacent difference...
        2. Get the array length `n`...

    -> Iterative Comparison:
        1. For `i = 1` to `n - 1`:
            a. Compute `currDiff = |nums[i] - nums[i - 1]|`...
            b. Update `maxDiff = max(maxDiff, currDiff)`...

    -> Handle Circular Edge:
        1. Compute `edgeDiff = |nums[0] - nums[n - 1]|`...
        2. Update `maxDiff = max(maxDiff, edgeDiff)`...

    -> Return `maxDiff` as the final answer...

Key Characteristics:
    -> Efficient one-pass traversal...
    -> Handles both linear and circular adjacency correctly...
    -> Uses simple absolute difference to determine maximum jump between neighbors...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the array...
    -> Space Complexity: O(1), uses only constant extra space...

Demonstration:
    -> Input: nums = [1, 2, 3, 6]
    -> Adjacent differences: |1-2|=1, |2-3|=1, |3-6|=3, |6-1|=5 (circular)
    -> Output: 5 (maximum among all adjacent differences)

*/

public class Maximum_Difference_Between_Adjacent_Elements_In_A_Circular_Array {

    private static int maxAdjacentDistance(int[] nums) {

        int maxDiff = 0;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int currDiff = Math.abs(nums[i - 1] - nums[i]);
            maxDiff = Math.max(maxDiff, currDiff);
        }

        int edgeDiff = Math.abs(nums[0] - nums[n - 1]);
        maxDiff = Math.max(maxDiff, edgeDiff);

        return maxDiff;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 6};
        System.out.println("The maximum difference between adjacent elements in a circular array is = " + maxAdjacentDistance(nums));

    }

}

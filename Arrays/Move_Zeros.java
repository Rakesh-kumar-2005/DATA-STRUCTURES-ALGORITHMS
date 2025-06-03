package Arrays;

/*

Description:
    -> This program rearranges elements in an integer array such that all zeros are moved to the end...
    -> It preserves the relative order of the non-zero elements...

Problem Statement:
    -> Given an array `nums`, move all 0's to the end of the array while maintaining the relative order of the non-zero elements...
    -> The operation must be performed in-place without making a copy of the array...

Approach:
    > Two-Pointer Technique:
        -> Use two pointers: 
            - `j` iterates through each element of the array...
            - `i` tracks the position to place the next non-zero element...
        -> If a non-zero element is found, it is swapped with the element at index `i`, and `i` is incremented...

Algorithm Steps:
    1. Initialize two pointers: `i = 0` to mark the position of the next non-zero, and `j` to iterate over the array...
    2. Traverse the array with `j`:
        a. If `nums[j]` is not zero:
            i. Swap `nums[i]` and `nums[j]`...
            ii. Increment `i`...
    3. At the end of the loop, all non-zero elements are shifted to the front and zeros are at the end...

Key Characteristics:
    -> In-place algorithm, does not use additional storage...
    -> Maintains the order of non-zero elements...
    -> Efficient for large datasets due to linear traversal...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of elements in the array...
    -> Space Complexity: O(1), as the operation is done in-place...

Demonstration:
    -> Given Input: [0, 1, 0, 3, 12]
    -> Process:
        - Move 1 to front (swap with 0 at index 0)
        - Move 3 to next non-zero position
        - Move 12 to next non-zero position
    -> Output: [1, 3, 12, 0, 0]

*/

import java.util.Arrays;

public class Move_Zeros {

    private static void moveZeroes(int[] nums) {

        int n = nums.length;
        int i = 0;

        for (int j = 0; j < n; j++) {

            if (nums[j] != 0) {
                swap(nums, i, j);
                i++;
            }

        }

    }

    private static void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("Original Array : " + Arrays.toString(nums));

        moveZeroes(nums);
        System.out.println("Modified Array : " + Arrays.toString(nums));

    }

}

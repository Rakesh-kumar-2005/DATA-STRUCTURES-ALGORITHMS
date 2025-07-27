package Arrays;

/*

Description:
    -> This program counts the number of hills and valleys in a given integer array...
    -> A **hill** is defined as an element that is strictly greater than its closest non-equal neighbors...
    -> A **valley** is defined as an element that is strictly smaller than its closest non-equal neighbors...

Problem Statement:
    -> You are given an array `nums` of integers...
    -> A position `i` is a hill if nums[i - 1] < nums[i] > nums[i + 1]...
    -> A position `i` is a valley if nums[i - 1] > nums[i] < nums[i + 1]...
    -> The task is to count the total number of hills and valleys in the array...
    -> Note: Equal adjacent elements are treated as part of the same slope and should be skipped when checking neighbors...

Approach:
    -> Step 1: Iterate through the array from index 1 to n - 2 (excluding endpoints)...
    -> Step 2: Use a `prev` pointer to track the last significant (non-equal) element before the current element...
    -> Step 3: For each index, check whether it's a hill or a valley using the strict comparison with both neighbors...
    -> Step 4: If a hill or valley is found, increment the count and update `prev` to the current index...

Edge Case Handling:
    -> Handles consecutive equal elements by not updating `prev` unless a hill/valley is found...
    -> Ensures boundaries (first and last elements) are ignored as they cannot form hills/valleys...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the size of the array...
    -> Space Complexity: O(1), constant space used...

Demonstration:
    -> Test Case:
        Input: nums = [2, 4, 1, 1, 6, 5]
        Output: 3
        Explanation:
            - nums[1] = 4 is a hill (2 < 4 > 1)
            - nums[2] = 1 is a valley (4 > 1 < 6)
            - nums[4] = 6 is a hill (1 < 6 > 5)
*/

public class Count_Hills_And_Valleys_In_An_Array {

    private static int countHillValley(int[] nums) {

        int count = 0;
        int prev = 0;

        for (int curr = 1; curr < nums.length - 1; curr++) {

            if ((nums[prev] > nums[curr] && nums[curr] < nums[curr + 1]) ||
                (nums[prev] < nums[curr] && nums[curr] > nums[curr + 1])) {
                count++;
                prev = curr;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {2, 4, 1, 1, 6, 5};
        System.out.println("The number of hills and valleys is = " + countHillValley(nums));

    }

}

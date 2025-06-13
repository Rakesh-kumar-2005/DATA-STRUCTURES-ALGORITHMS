package Arrays;

/*

Description:
    -> This program counts the number of **contiguous subarrays** whose sum equals a given `target` value...
    -> It efficiently uses **prefix sum** along with a HashMap to avoid brute-force computation...

Problem Statement:
    -> Given an integer array `nums` and an integer `target`, return the **total number of continuous subarrays** whose sum equals to `target`...
    -> Subarrays must be contiguous and maintain order from the original array...

Approach:
    > Prefix Sum + HashMap:
        -> Maintain a running sum (`currSum`) as you iterate through the array...
        -> Use a HashMap to keep track of how many times each prefix sum has occurred...
        -> For each element:
            --> Check if `currSum - target` exists in the map...
            --> If yes, that means there's a subarray ending at current index with sum = target...

    > HashMap Usage:
        -> Key: a prefix sum seen so far...
        -> Value: count of how many times that prefix sum has occurred...
        -> Initialize map with {0:1} to handle cases where subarray starts from index 0...

Algorithm Steps:
    -> Initialization:
        1. Set `ans = 0` to count matching subarrays...
        2. Set `currSum = 0` to maintain running prefix sum...
        3. Initialize HashMap with (0, 1)...

    -> Iteration Over Array:
        1. For each number `num` in the array:
            a. Add `num` to `currSum`...
            b. If `(currSum - target)` exists in map:
                --> Add its count to `ans` (means valid subarrays end here)...
            c. Update the map with current `currSum` frequency...

    -> Return `ans` as the total number of valid subarrays...

Key Characteristics:
    -> Avoids nested loops using prefix sum technique...
    -> Handles overlapping subarrays with ease using map frequency counts...
    -> Highly optimized for large input sizes...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the array...
    -> Space Complexity: O(n), for storing prefix sums in HashMap...

Demonstration:
    -> Input: nums = [1, 1, 1], target = 2
    -> Valid subarrays: [1,1] (at index 0-1), [1,1] (at index 1-2)
    -> Output: 2

*/

import java.util.HashMap;

public class Subarray_Sum_Equals_Target {

    private static int subarraySum(int[] nums, int target) {

        int ans = 0;
        int currSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {

            currSum += num;

            if (map.containsKey(currSum - target)) {
                ans += map.get(currSum - target);
            }

            if (map.containsKey(currSum)) {
                map.put(currSum, map.get(currSum) + 1);
            } else {
                map.put(currSum, 1);
            }

        }

        return ans;

    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1};
        int target = 2;
        System.out.println("The number of subarrays with sum " + target + " is = " + subarraySum(nums, target));

    }

}

package Arrays;

/*

Description:
    -> This program solves the Two Sum II problem using the two-pointer technique on a sorted array...
    -> It finds two numbers such that their sum equals a given target and returns their 1-based indices...

Problem Statement:
    -> Given a 1-indexed array of integers that is already sorted in non-decreasing order...
    -> Find two numbers such that they add up to a specific target number...
    -> Return the indices of the two numbers (1-based) as an array of size 2...
    -> You must use only constant extra space and cannot modify the input array...

Approach:
    > Two-Pointer Technique:
        -> Initialize two pointers — one at the beginning (`i = 0`) and one at the end (`j = n - 1`) of the array...
        -> While i ≤ j:
            a. Calculate the sum of arr[i] + arr[j]...
            b. If the sum equals the target, return [i + 1, j + 1] as 1-based indices...
            c. If the sum is greater than the target, decrement j...
            d. If the sum is less than the target, increment i...

Algorithm Steps:
    -> Step 1: Initialize `i = 0` and `j = arr.length - 1`...
    -> Step 2: Loop while i ≤ j:
        a. Compute sum = arr[i] + arr[j]...
        b. If sum == target → return [i + 1, j + 1]...
        c. If sum > target → move `j` left...
        d. If sum < target → move `i` right...
    -> Step 3: If no pair is found, return an empty result [0, 0] or [0, 0]...

Key Characteristics:
    -> Assumes the input array is sorted in non-decreasing order...
    -> Efficient linear time solution using constant space...
    -> Returns 1-based indices as per the problem specification...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of elements in the array...
    -> Space Complexity: O(1), using only two pointers and a fixed-size output...

Demonstration:
    -> Input: arr = [2, 7, 11, 15], target = 9
    -> Valid pair: 2 + 7 = 9 → indices = [1, 2]
    -> Output: The indices of the two numbers are which have the combined sum of 9 : 1 2

*/

public class Two_Sum_II {

    private static int[] twoSum(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;

        while (i <= j) {
            int ans = arr[i] + arr[j];

            if (ans == target) {
                return new int[]{i + 1, j + 1};
            } else if (ans > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] ans = twoSum(arr, target);
        System.out.println("The indices of the two numbers are which have the combined sum of " + target + " : " + ans[0] + " " + ans[1]);

    }
}

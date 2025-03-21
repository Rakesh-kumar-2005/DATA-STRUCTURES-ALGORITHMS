package Arrays;

/*

Description:
  Following program demonstrates the solution to the "Longest Sub-array with Sum Divisible by K" problem using a HashMap and the prefix sum technique...

Problem Statement:
  -> Given an array of integers and a target value K...
  -> Find the length of the longest sub-array such that the sum of its elements is divisible by K...
  -> Return the maximum length found...

Approach:
  > Prefix Sum with Remainder Tracking:
     i. Use a HashMap to store the first occurrence of each remainder when prefix sum is divided by K...
     ii. When a remainder repeats, we've found a sub-array whose sum is divisible by K...
     iii. Calculate the length of this sub-array and keep track of the maximum length...

> Algorithm Steps:
  -> Initialize an empty HashMap to store remainders and their indices...
  -> Track a running prefix sum, taking modulo K at each step...
  -> For each position i in the array:
     * Update prefix sum by adding the current element and taking modulo K...
     * Apply a normalization ((sum % target + target) % target) to handle negative numbers...
     * If prefix sum is 0, the entire sub-array from start to current position is divisible by K...
     * If the current remainder exists in HashMap, calculate length and update maximum if needed...
     * If the remainder is encountered for the first time, store its position in the HashMap...

> Key Insight:
  -> If prefix sums at indices i and j have the same remainder when divided by K,
     then the sum of elements between i+1 and j is divisible by K...
  -> This is because: (prefixSum[j] - prefixSum[i]) % K = 0

> Example:
  -> For input array [2, 7, 6, 1, 4, 5, 1, 3, 4, 7] and K = 3
  -> The longest sub-array with sum divisible by 3 has length 5

> Edge Cases:
  -> Handles negative numbers correctly using the normalization formula...
  -> Special case when prefix sum itself is divisible by K (remainder = 0)...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the length of the array...
  -> Space Complexity: O(min(n, K)) as there can be at most K distinct remainders...

*/

import java.util.HashMap;

public class Longest_Sub_Array_Sum_Divisible_By_Target {

    private static int longestSubArray(int[] arr, int target) {

        int maxLength = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        int prefixSum = 0;

        for (int i = 0; i < arr.length; i++) {

            prefixSum = ((prefixSum + arr[i]) % target + target) % target;

            if (prefixSum == 0) {
                maxLength = i + 1;
            } else if (mp.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - mp.get(prefixSum));
            } else {
                mp.put(prefixSum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 6, 1, 4, 5, 1, 3, 4, 7};
        int target = 3;
        System.out.println("The longest sub-array sum divisible by " + target + " has the length of = " + longestSubArray(arr, target));

    }
}

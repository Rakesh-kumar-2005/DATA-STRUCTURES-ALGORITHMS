package Math_Problems;

/*

Description:
    -> This program calculates the number of distinct values obtained by applying the bitwise OR operation
       on all possible contiguous subarrays of a given integer array.
    -> It returns the total count of unique results from all subarray bitwise ORs.

Problem Statement:
    -> Given an integer array `arr`, compute the number of distinct bitwise OR results 
       from all contiguous subarrays.

Approach:
    > HashSet-based Rolling Calculation:
        -> Use two sets:
            - `curr`: stores all OR combinations ending at the current index.
            - `ans`: accumulates all unique OR values across all subarrays.
        -> For each number in the array:
            - Start a new set `temp` containing just the number itself.
            - For each value `x` in `curr`, compute `x | num` and add it to `temp`.
            - Assign `temp` to `curr` and add all elements of `curr` to `ans`.

Algorithm Steps:
    -> Initialization:
        1. Create `ans` as a HashSet to store final unique ORs.
        2. Create `curr` as a HashSet to store rolling ORs at each step.

    -> Iteration:
        1. For each number `num` in the input array:
            a. Create a temporary set `temp`.
            b. Add `num` to `temp`.
            c. For each element `x` in `curr`, compute `x | num` and add to `temp`.
            d. Assign `curr = temp`.
            e. Add all elements of `curr` to `ans`.

    -> Finalizing:
        1. Return the size of `ans`, which represents the number of distinct OR results.

Key Characteristics:
    -> Efficiently explores all possible OR outcomes using set-based dynamic tracking.
    -> Avoids nested loops, leading to improved performance over brute-force.
    -> Capable of handling large arrays with limited value ranges due to bit constraints.

Time and Space Complexity:
    -> Time Complexity: O(N * log(Max)), where N is the array length and Max is the maximum number in the array.
    -> Space Complexity: O(N * log(Max)), due to storage in HashSets.

Demonstration:
    -> Input: [1, 2, 4]
    -> Subarray ORs: 1, 2, 4, (1|2)=3, (2|4)=6, (1|2|4)=7
    -> Output: 6 distinct values

*/

import java.util.HashSet;

public class Bitwise_ORs_Of_Subarrays_II {

    private static int subarrayBitwiseORs(int[] arr) {

        HashSet<Integer> result = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);

            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == (arr[j] | arr[i])) break;
                arr[j] |= arr[i];
                result.add(arr[j]);
            }

        }

        return result.size();

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 4, 8};
        System.out.println("The number of distinct subarray bitwise ORs is : " + subarrayBitwiseORs(arr));

    }


}

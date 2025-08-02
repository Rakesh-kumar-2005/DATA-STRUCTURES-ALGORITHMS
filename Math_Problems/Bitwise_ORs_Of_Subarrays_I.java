package Math_Problems;

/*

Description:
    -> This program computes the number of distinct values obtained by taking the bitwise OR of every subarray of a given array...
    -> It incrementally builds possible OR results ending at each position and accumulates unique results across all subarrays...

Problem Statement:
    -> Given an integer array `arr`, consider all possible subarrays and compute the bitwise OR of each subarray...
    -> Return the count of distinct bitwise OR results among all those subarrays...

Approach:
    -> Step 1: Maintain two sets:
        - `curr`: all distinct OR values of subarrays ending at the previous index...
        - `ans`: global set accumulating all distinct OR values seen so far...
    -> Step 2: For each element `num` in `arr`:
        a. Start a temporary set `temp` with `num` itself (subarray of length 1)...
        b. For every value `x` in `curr`, compute `x | num` and add to `temp` (extending previous subarrays)...
        c. Merge `temp` into `ans` and set `curr = temp` for the next iteration...
    -> Step 3: After processing all elements, the size of `ans` is the number of distinct subarray ORs...

Key Characteristics:
    -> Reuses previous computations to avoid re-evaluating all subarrays from scratch...
    -> Exploits the fact that bitwise OR results can only accumulate bits, so the number of distinct ongoing values stays manageable...

Time and Space Complexity:
    -> Time Complexity: O(n * k), where n is array length and k is the average size of `curr` (bounded by number of bits, typically small)...
    -> Space Complexity: O(n * k) in the worst case due to sets holding intermediate OR values...

Demonstration:
    -> Input: arr = [1, 2, 4]
    -> Subarray ORs:
        [1] = 1
        [1,2] = 1|2 = 3
        [1,2,4] = 3|4 = 7
        [2] = 2
        [2,4] = 6
        [4] = 4
    -> Distinct results: {1, 2, 3, 4, 6, 7}
    -> Output: The number of distinct subarray bitwise ORs is : 6

*/

import java.util.HashSet;

public class Bitwise_ORs_Of_Subarrays_I {

    private static int subarrayBitwiseORs(int[] arr) {

        HashSet<Integer> ans = new HashSet<>();
        HashSet<Integer> curr = new HashSet<>();

        for (int num : arr) {

            HashSet<Integer> temp = new HashSet<>();
            temp.add(num);

            for (int x : curr) {
                temp.add(x | num);
            }

            ans.addAll(temp);
            curr = temp;

        }

        return ans.size();

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 4};
        System.out.println("The number of distinct subarray bitwise ORs is : " + subarrayBitwiseORs(arr));

    }


}

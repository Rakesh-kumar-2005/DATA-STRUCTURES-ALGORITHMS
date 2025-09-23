package Hashing;

/*

Description:
    -> This program checks if one array (`arr`) can be transformed into another (`target`)
       by reversing subarrays any number of times...
    -> Since reversing subarrays allows any permutation, the problem reduces to checking
       whether both arrays contain the same elements with the same frequencies...

Problem Statement:
    -> Given two integer arrays `target` and `arr`, return true if `arr` can be transformed
       into `target` using any number of subarray reversals, otherwise return false...
    -> Example: 
         target = [1, 2, 3, 4]
         arr    = [2, 4, 1, 3]
       → Since arr is a permutation of target, output = true...

Approach:
    > Key Insight:
        -> Any array can be rearranged into any permutation of itself using subarray reversals...
        -> Therefore, the problem is equivalent to verifying if `arr` is a permutation of `target`...

    > Frequency Counting with HashMap:
        -> Count the frequency of each element in `target`...
        -> Traverse `arr`:
             - Decrease the frequency of each element...
             - If an element is not found or frequency mismatch occurs, return false...
        -> If all frequencies match, return true...

Algorithm Steps:
    1. If `target` and `arr` are already equal → return true...
    2. If lengths differ → return false...
    3. Create a HashMap `mp` to store frequencies of elements in `target`...
    4. For each element in `target`, update its count in `mp`...
    5. Traverse `arr`:
         - If element not in `mp` → return false...
         - Else, reduce its count (remove from map if count reaches zero)...
    6. If traversal completes successfully → return true...

Key Characteristics:
    -> Uses HashMap for efficient frequency counting...
    -> Order of elements does not matter (only counts matter)...
    -> Handles duplicates correctly...
    -> Early termination on mismatch improves efficiency...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the arrays...
    -> Space Complexity: O(n), due to the HashMap storing element frequencies...

Demonstration:
    -> Input:
         target = [1, 2, 3, 4]
         arr    = [2, 4, 1, 3]
    -> Frequency(target):
         1 → 1, 2 → 1, 3 → 1, 4 → 1
    -> Checking arr:
         - 2 found → decrement count
         - 4 found → decrement count
         - 1 found → decrement count
         - 3 found → decrement count
    -> All counts resolved successfully...
    -> Output: "The arrays can be made equal by reversing subarrays..."

*/

import java.util.Arrays;
import java.util.HashMap;

public class Make_Two_Arrays_Equal_By_Reversing_Subarrays {

    private static boolean canBeEqual(int[] target, int[] arr) {
        
        if (Arrays.equals(target, arr)) {
            return true;
        }

        if (arr.length != target.length) {
            return false;
        }

        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int i : target) {
            if (mp.containsKey(i)) {
                mp.put(i, mp.get(i) + 1);
            } else {
                mp.put(i, 1);
            }
        }

        for (int num : arr) {
            if (! mp.containsKey(num)) {
                return false;
            } else {
                if (mp.get(num) == 1) {
                    mp.remove(num);
                } else {
                    mp.put(num, mp.get(num) - 1);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] target = {1, 2, 3, 4};
        int[] arr = {2, 4, 1, 3};

        boolean ans = canBeEqual(target, arr);

        if (ans) {
            System.out.println("The arrays can be made equal by reversing subarrays...");
        } else {
            System.out.println("The arrays cannot be made equal by reversing subarrays...");
        }

    }

}

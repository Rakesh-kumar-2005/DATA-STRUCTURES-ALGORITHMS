package Hashing;

/*

Description:
    -> This program finds all elements in an integer array that appear more than ⌊n / 3⌋ times.
    -> A "majority element" in this context is defined as any element whose frequency in the array
       exceeds one-third of the array's length.
    -> The algorithm uses a HashMap to store and count the frequency of each element.

Problem Statement:
    -> Given an integer array, return all elements that appear more than ⌊n / 3⌋ times.
    -> If no such elements exist, return an empty list.
    -> The order of output elements does not matter.

Approach:
    > Frequency Counting with HashMap:
        -> Use a HashMap to store each element along with its frequency count.
        -> Iterate through the array:
            1. If the element is not in the map → add it with count = 1.
            2. If the element is already in the map → increment its count.
        -> While counting, check if the frequency of the current element exceeds ⌊n / 3⌋.
           - If yes and the element is not already in the result list, add it.
        -> Return the result list at the end.

Algorithm Steps:
    1. Let limit = ⌊array length / 3⌋.
    2. Create an empty list `ans` to store majority elements.
    3. Create an empty HashMap `map` to store element → frequency mapping.
    4. For each number in the array:
        a. Update its frequency in the map.
        b. If frequency > limit and number not in `ans` → add to `ans`.
    5. Return `ans`.

Key Characteristics:
    -> Uses a HashMap for fast frequency counting.
    -> Checks and adds majority elements during iteration to avoid extra passes.
    -> Works for arrays of any size.

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of elements in the array.
    -> Space Complexity: O(k), where k is the number of distinct elements.

Example:
    Input:
        arr = {1, 2, 3, 1, 1, 2, 1, 1, 1, 2, 2, 2}
    Output:
        The majority elements are : [1, 2]

*/

import java.util.ArrayList;
import java.util.HashMap;

public class Majority_Element {


    private static ArrayList<Integer> majorityElement(int[] arr) {

        int n = arr.length / 3;
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {

            if (! map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }

            if (map.get(num) > n && ! ans.contains(num)) {
                ans.add(num);
            }
        }

        return ans;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 1, 2, 1, 1, 1, 2, 2, 2,};
        ArrayList<Integer> ans = majorityElement(arr);

        System.out.println("The majority elements are : " + ans);

    }


}

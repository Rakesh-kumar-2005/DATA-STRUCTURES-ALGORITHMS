package String;

/*

Description:
    -> This program finds the k-th distinct string in an array of strings.

Problem Statement:
    You are given an array of strings `arr` and an integer `k`.
    A "distinct" string is a string that occurs exactly once in the array.
    You need to return the k-th distinct string in the order in which they appear in `arr`.
    If there are fewer than k distinct strings, return an empty string "".

Example:
    Input:
        arr = ["a", "b", "a", "c", "c", "d", "e"]
        k = 2

    Step-by-step process:
        - Frequencies:
            "a" → 2 times
            "b" → 1 time
            "c" → 2 times
            "d" → 1 time
            "e" → 1 time

        - Distinct strings in order of appearance:
            "b", "d", "e"

        - 1st distinct → "b"
        - 2nd distinct → "d"

    Output:
        "d"

Approach:
    1. Use a LinkedHashMap<String, Integer> to store string frequencies 
       while preserving insertion order.
    2. Iterate through `arr`:
        - Increase frequency count for each string.
    3. Traverse the LinkedHashMap:
        - For each string with frequency = 1 (distinct string), increment a counter.
        - When the counter reaches `k`, return that string.
    4. If no such k-th distinct string exists, return "".

Why LinkedHashMap?
    -> Unlike HashMap, LinkedHashMap preserves the insertion order, 
       ensuring we return the k-th distinct string in the same order as in the input.

Time and Space Complexity:
    -> Time Complexity: O(n), where n = length of arr
        - One pass to build frequency map
        - One pass to find the k-th distinct string
    -> Space Complexity: O(n), for storing string frequencies.

Conclusion:
    This program efficiently finds the k-th distinct string in an array 
    while respecting the original order of elements.

*/

import java.util.LinkedHashMap;

public class Kth_Distinct_String_In_An_Array {

    private static String kthDistinct(String[] arr, int k) {
        LinkedHashMap<String, Integer> mp = new LinkedHashMap<>();

        for (String x : arr) {
            if (mp.containsKey(x)) {
                mp.put(x, mp.get(x) + 1);
            } else {
                mp.put(x, 1);
            }
        }

        int count = 0;

        for (String s : mp.keySet()) {
            if (mp.get(s) == 1) {
                count++;
                if (count == k) {
                    return s;
                }
            }
        }

        return "";
    }

    public static void main(String[] args) {

        String[] arr = {"a", "b", "a", "c", "c", "d", "e"};
        int k = 2;

        System.out.println("The " + k + "th distinct string in the array is : " + kthDistinct(arr, k));

    }

}

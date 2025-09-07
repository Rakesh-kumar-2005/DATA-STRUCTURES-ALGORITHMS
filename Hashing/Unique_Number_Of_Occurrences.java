package Hashing;

/*

Description:
    -> This program checks whether the number of occurrences of each 
       element in an array is unique or not.

Problem Statement:
    Given an integer array `arr`, return true if the number of occurrences 
    of each value in the array is unique. Otherwise, return false.

Example:
    Input:
        arr = [1, 2, 2, 1, 1, 3]
    Frequency Count:
        1 -> 3 times
        2 -> 2 times
        3 -> 1 time
    Unique Frequencies: {3, 2, 1}
    Output:
        true  (since all frequencies are unique)

    Input:
        arr = [1, 2, 2, 1, 3, 3]
    Frequency Count:
        1 -> 2 times
        2 -> 2 times
        3 -> 2 times
    Unique Frequencies: {2}
    Output:
        false (since frequencies are not unique)

Approach:
    1. Use a HashMap<Integer, Integer> to store the frequency 
       of each element in the array.
    2. Use a HashSet<Integer> to track unique occurrence counts.
    3. Compare the size of the HashSet and HashMap:
       - If equal, all frequencies are unique.
       - Otherwise, some elements share the same occurrence count.

Key Methods:
    -> uniqueOccurrences(int[] arr) : returns true if all frequencies are unique.
    -> main() : tests the function with sample input.

Time and Space Complexity:
    -> Time Complexity: O(n), where n = size of the array.
    -> Space Complexity: O(n), due to HashMap and HashSet.

Conclusion:
    This program ensures that no two different numbers in the array 
    occur the same number of times.

*/

import java.util.HashMap;
import java.util.HashSet;

public class Unique_Number_Of_Occurrences {

    private static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i : arr) {
            if (mp.containsKey(i)) {
                mp.put(i, mp.get(i) + 1);
            } else {
                mp.put(i, 1);
            }
        }
        HashSet<Integer> st = new HashSet<>();
        for (int i : mp.values()) {
            st.add(i);
        }
        return st.size() == mp.size();
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 1, 1, 3};
        boolean ans = uniqueOccurrences(arr);

        if (ans) {
            System.out.println("All elements have unique number of occurrences...");
        } else {
            System.out.println("All elements do not have unique number of occurrences...");
        }

    }

}


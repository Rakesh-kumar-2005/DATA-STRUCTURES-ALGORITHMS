package String;

/*

Description:
    -> This program sorts the characters of a given string based on their frequency of occurrence in descending order...
    -> Characters that appear more frequently are placed earlier in the result...

Problem Statement:
    -> Given a string `s`, sort it such that characters with higher frequency come first...
    -> Return the resulting string...

Approach:
    > Frequency Counting + Max Heap:
        -> Count the frequency of each character using a HashMap...
        -> Use a Max Heap (PriorityQueue) to sort characters by their frequencies...
        -> Build the output string by appending each character multiple times based on its frequency...

Algorithm Steps:
    1. Edge Case Handling:
        -> If the input string is empty, return `null` (as there's nothing to sort)...

    2. Frequency Mapping:
        -> Traverse the input string and populate a HashMap with character frequencies...

    3. Max Heap Construction:
        -> Use a PriorityQueue (Max Heap) to sort characters by frequency in descending order...
        -> Insert all entries from the HashMap into the PriorityQueue...

    4. String Construction:
        -> While the PriorityQueue is not empty:
            a. Extract the character and its frequency...
            b. Append the character to the result as many times as it appears...

    5. Return Result:
        -> Convert the StringBuilder to a string and return it...

Key Characteristics:
    -> Efficiently counts character frequency...
    -> Utilizes a heap to maintain ordering based on frequency...
    -> Ensures that higher frequency characters appear earlier in the output...

Time and Space Complexity:
    -> Time Complexity: O(n log k), where n is the length of the input string and k is the number of unique characters...
    -> Space Complexity: O(n + k), for the frequency map, heap, and output string...

Demonstration:
    -> Example: Input = "cvtcvagCCXrr"
        - Frequencies: c=2, v=2, t=1, a=1, g=1, C=2, X=1, r=2
        - Output may vary due to same frequencies but should contain all characters sorted by frequency...

*/

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Characters_By_Frequency {

    private static String frequencySort(String s) {

        if (s.isEmpty()) {
            return null;
        }

        HashMap<Character, Integer> mp = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            if (! mp.containsKey(currChar)) {
                mp.put(currChar, 1);
            } else {
                mp.put(currChar, mp.get(currChar) + 1);
            }

        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(mp.entrySet());

        StringBuilder ans = new StringBuilder();

        while (! pq.isEmpty()) {

            Map.Entry<Character, Integer> currEntry = pq.poll();
            int frequency = currEntry.getValue();

            while (frequency != 0) {
                ans.append(currEntry.getKey());
                frequency--;
            }

        }

        return ans.toString();

    }

    public static void main(String[] args) {

        String s = "cvtcvagCCXrr";
        System.out.println("Original String is : " + s);
        System.out.println("Sorted String based on frequency is : " + frequencySort(s));

    }

}

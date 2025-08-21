package String;

/*

Description:
    -> This program finds the length of the longest palindrome that can be formed 
       from the characters of a given string.
    -> The palindrome does not need to be a substring of the input string, 
       but rather a rearrangement of its characters.

Problem Statement:
    -> Given a string `s` consisting of lowercase or uppercase letters,
       determine the maximum possible length of a palindrome that can be built using its letters.

Example:
    Input:  "abccccdd"
    Output: 7
    Explanation:
        One longest palindrome that can be built is "dccaccd" (length = 7).

Approach:
    1. Use a HashSet to track characters with odd occurrences.
    2. Iterate over the string:
        - If a character is not in the set, add it.
        - If it is already in the set, remove it and increase count by 2 
          (since we found a pair to add to the palindrome).
    3. After processing, if the set is not empty, it means there are characters 
       with odd frequency. We can place exactly one of them in the center of the palindrome, 
       so increment count by 1.
    4. Return the final count.

Key Observations:
    -> Characters with even frequency can always contribute fully to the palindrome.
    -> Characters with odd frequency can contribute all but one occurrence; 
       at most one odd-frequency character can sit in the center.

Time and Space Complexity:
    -> Time Complexity: O(n), where n = length of the string (single pass).
    -> Space Complexity: O(k), where k = number of distinct characters 
       (at most 52 for English uppercase + lowercase).

Example Walkthrough:
    Input:  "abccccdd"
    Process:
        - 'a' → add to set
        - 'b' → add to set
        - 'c' → add to set
        - next 'c' → remove from set, count = 2
        - next 'c' → add to set
        - next 'c' → remove from set, count = 4
        - 'd' → add to set
        - next 'd' → remove from set, count = 6
        Final set = {'a','b'}, not empty → count = 6 + 1 = 7
    Output: 7

*/

import java.util.HashSet;

public class Longest_Palindrome {

    private static int longestPalindrome(String s) {
        HashSet<Character> mp = new HashSet<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (! mp.contains(ch)) {
                mp.add(ch);
            } else {
                mp.remove(ch);
                count += 2;
            }

        }
        if (! mp.isEmpty()) {
            count++;
        }

        return count;

    }

    public static void main(String[] args) {

        String s = "abccccdd";
        System.out.println("The longest palindrome that can be formed from the given string has the length of : " + longestPalindrome(s));

    }

}

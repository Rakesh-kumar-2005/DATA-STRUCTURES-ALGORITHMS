package String;

/*

Description:
    -> This program counts how many words from a given list are "consistent" 
       with a set of allowed characters.

Problem Statement:
    You are given a string `allowed` consisting of distinct characters and 
    an array of strings `words`. 
    A string is called "consistent" if all characters of the string 
    appear in the string `allowed`.

    Return the number of consistent strings in the array.

Example:
    Input:
        allowed = "ab"
        words = ["ad", "bd", "aaab", "baa", "badab"]

    Step-by-step check:
        - "ad"    -> contains 'd' (not allowed) → inconsistent
        - "bd"    -> contains 'd' (not allowed) → inconsistent
        - "aaab"  -> all chars in {a, b}        → consistent
        - "baa"   -> all chars in {a, b}        → consistent
        - "badab" -> contains 'd' (not allowed) → inconsistent

    Output:
        2   (only "aaab" and "baa" are consistent)

Approach:
    1. Iterate over each word in the `words` array.
    2. For each word, check every character:
        - If any character is not in the `allowed` string → mark as inconsistent.
        - Otherwise, keep checking until the word ends.
    3. If a word is fully valid, increment the count.
    4. Return the final count.

Helper Method:
    -> helper(String allowed, String currWord):
        Checks whether a given word is consistent by verifying 
        that every character is present in `allowed`.

Time and Space Complexity:
    -> Time Complexity: O(m * n), where 
           m = number of words,
           n = average length of words.
    -> Space Complexity: O(1), since only simple variables are used.

Conclusion:
    This program efficiently counts how many words in the list 
    consist only of characters from the allowed set.

*/

public class Count_the_Number_Of_Consistent_Strings {

    private static int countConsistentStrings(String allowed, String[] words) {
        int count = 0;

        for (String currWord : words) {
            if (helper(allowed, currWord)) {
                count++;
            }
        }

        return count;
    }

    private static boolean helper(String allowed, String currWord) {

        for (int i = 0; i < currWord.length(); i++) {
            if (! allowed.contains(String.valueOf(currWord.charAt(i)))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};

        System.out.println("The number of consistent strings is : " + countConsistentStrings(allowed, words));

    }

}

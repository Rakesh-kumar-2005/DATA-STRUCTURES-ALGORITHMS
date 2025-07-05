package String;

/*
Description:
    -> This program checks whether a given sentence is a circular sentence...
    -> A sentence is said to be circular if the last character of each word matches the first character of the next word, and the last word connects back to the first word...

Problem Statement:
    -> Given a sentence as a string, determine if it forms a circular sentence...
    -> The sentence is circular if:
        a. The first character of the first word matches the last character of the last word...
        b. The last character of each word matches the first character of the next word...

Approach:
    > Preprocessing:
        -> Trim any leading or trailing spaces in the input sentence...
        -> Split the sentence into individual words based on spaces...

    > Circular Condition Check:
        -> First, check if the first character of the first word matches the last character of the last word...
        -> Then, for each adjacent pair of words, verify if the last character of the current word matches the first character of the next word...

    > Early Return:
        -> If any mismatch is found during the checks, return false immediately...
        -> If all conditions are satisfied, return true...

Algorithm Steps:
    -> Step 1: Trim the sentence and split it into words...
    -> Step 2: Check the circular connection between the first and last words...
    -> Step 3: Loop through all adjacent word pairs:
        a. Check if last character of word[i] == first character of word[i + 1]...
    -> Step 4: If all checks pass, return true...

Key Characteristics:
    -> Works on any number of words including just one...
    -> Uses simple string operations and character comparisons...
    -> Efficient one-pass check for circularity...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the total number of characters in the sentence...
    -> Space Complexity: O(w), where w is the number of words due to array splitting...

Demonstration:
    -> Input: "the eagle eats silently yet the"
    -> Word connections: the → eagle → eats → silently → yet → the
    -> Each word ends where the next starts, and it loops back to the first
    -> Output: This sentence is circular...

*/

public class Circular_Sentence {

    private static boolean isCircularSentence(String s) {

        s = s.trim();
        String[] words = s.split(" ");

        int n = words.length;
        String word1 = words[0];
        String word2 = words[n - 1];

        if (word1.charAt(0) != word2.charAt(word2.length() - 1)) {
            return false;
        }

        if (n == 1) {
            return word1.charAt(0) == word1.charAt(word1.length() - 1);
        }


        for (int i = 0; i < n - 1; i++) {

            word1 = words[i];
            word2 = words[i + 1];

            if (word2.charAt(0) != word1.charAt(word1.length() - 1)) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        String s = "the quick brown fox jumps over the lazy dog";
        boolean ans = isCircularSentence(s);

        if (ans) {
            System.out.println("This sentence is circular...");
        } else {
            System.out.println("This sentence is not circular...");
        }

    }

}

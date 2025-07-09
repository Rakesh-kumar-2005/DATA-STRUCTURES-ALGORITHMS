package String;

/*
Description:
    -> This program calculates the length of the last word in a given string...
    -> A word is defined as a maximal substring consisting of non-space characters only...

Problem Statement:
    -> Given a string `word` consisting of letters and spaces, return the length of the last word in the string...
    -> The last word is the last sequence of non-space characters after trimming any trailing or leading spaces...

Approach:
    > Trimming and Reverse Scan:
        -> First, trim the string to remove any leading or trailing spaces...
        -> Start scanning from the end of the trimmed string...
        -> Count characters until a space is encountered or the beginning of the string is reached...

Algorithm Steps:
    -> Step 1: Trim the input string to remove any leading/trailing whitespace...
    -> Step 2: Initialize a pointer at the end of the string and a counter for the last word’s length...
    -> Step 3: Traverse backwards:
        a. If character is not a space, increment count...
        b. Stop when a space is encountered or index goes out of bounds...
    -> Step 4: Return the count...

Key Characteristics:
    -> Handles multiple spaces between and around words...
    -> Works in a single pass after trimming...
    -> Efficient and simple implementation without splitting the string...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string...
    -> Space Complexity: O(1), no extra space used except variables...

Demonstration:
    -> Input: "   fly me   to   the moon  "
    -> Trimmed: "fly me   to   the moon"
    -> Last word: "moon"
    -> Output: Length of the last word is : 4

*/

public class Length_Of_Last_Word {

    private static int lengthOfLastWord(String word) {
        word = word.trim();
        int i = word.length() - 1;
        int count = 0;

        while (i >= 0 && word.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }

    public static void main(String[] args) {
        String word = "   fly me   to   the moon  ";
        System.out.println("Length of the last word is : " + lengthOfLastWord(word));
    }

}

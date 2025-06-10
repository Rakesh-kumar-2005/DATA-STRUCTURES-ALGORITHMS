package String;

/*

Description:
    -> This program checks whether one string can become another string after performing some number of **rotations** (cyclic shifts) on it...
    -> It determines the possibility by concatenating the original string with itself and checking if the target string is a substring of the result...

Problem Statement:
    -> Given two strings `s` and `goal`, determine whether `s` can become `goal` after some number of **clockwise rotations**...
    -> A rotation involves moving the first character to the end of the string...

Approach:
    > Substring Search via Concatenation:
        -> If we concatenate the original string `s` with itself (i.e., `s + s`), all possible rotations of `s` are present as substrings...
        -> So we simply check if `goal` is a substring of this concatenated string...

Algorithm Steps:
    -> Edge Case Handling:
        1. If lengths of `s` and `goal` are not equal, return false...
        2. If `s` is already equal to `goal`, return true...

    -> Rotation Check:
        1. Concatenate `s` with itself and store it in a temporary string `temp`...
        2. If `temp` contains `goal` as a substring, return true...
        3. Otherwise, return false...

Key Characteristics:
    -> Avoids explicit rotation logic using character arrays or loops...
    -> Efficiently checks all rotations using a single string operation...
    -> Simplifies the problem using string manipulation properties...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string (due to substring check in `s + s`)...
    -> Space Complexity: O(n), due to the temporary string `temp = s + s`...

Demonstration:
    -> Input: s = "abcde", goal = "cdeab"
    -> temp = "abcdeabcde"
    -> "cdeab" is a substring of "abcdeabcde" → Match found
    -> Output: true

*/

public class Rotate_String_2 {

    private static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            return true;
        }

        String temp = s + s;

        if (temp.contains(goal)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String s = "abcde";
        String goal = "cdeab";

        boolean result = rotateString(s, goal);

        if (result) {
            System.out.println("The given strings can be rotated to each other");
        } else {
            System.out.println("The given strings cannot be rotated to each other");
        }

    }

}

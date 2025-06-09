package String;

/*

Description:
    -> This program checks whether one string can become another string after performing some number of **rotations** (cyclic shifts) on it...
    -> It simulates string rotations using character array manipulation and compares with the target string...

Problem Statement:
    -> Given two strings `s` and `goal`, determine whether `s` can become `goal` after some number of **clockwise rotations**...
    -> A rotation involves moving the first character to the end of the string...

Approach:
    > Rotation Simulation Using Character Arrays:
        -> Convert both strings to character arrays for in-place operations...
        -> For each possible rotation (from 1 to length - 1):
            --> Rotate the character array by `i` positions...
            --> Compare with the target array...
            --> If not equal, reverse the rotation to restore original state before the next trial...

    > Rotation Logic (Using Reverse Operations):
        -> To rotate an array by `k` positions:
            1. Reverse the entire array...
            2. Reverse the first `k` elements...
            3. Reverse the remaining `n - k` elements...
        -> This technique achieves rotation in-place with O(1) extra space...

Algorithm Steps:
    -> Edge Case Handling:
        1. If lengths of `s` and `goal` are not equal, return false...
        2. If `s` is already equal to `goal`, return true...

    -> Rotation Trials:
        1. Convert `s` and `goal` to character arrays (`st` and `gt`)...
        2. For each `i` from 1 to `st.length - 1`:
            a. Rotate `st` by `i` positions...
            b. If `st` equals `gt`, return true...
            c. Otherwise, rotate back by `(length - i)` to reset `st`...

    -> If no matching rotation found, return false...

Key Characteristics:
    -> Simulates each rotation without creating new strings...
    -> Restores the array state after each unsuccessful rotation attempt...
    -> Avoids brute-force substring creation or concatenation...

Time and Space Complexity:
    -> Time Complexity: O(n²), where n is the length of the string (O(n) rotation and comparison done up to n times)...
    -> Space Complexity: O(n), for character array conversion and temporary operations...

Demonstration:
    -> Input: s = "abcde", goal = "cdeab"
    -> Rotations: "bcdea", "cdeab" → Match found
    -> Output: true

*/

import java.util.Arrays;

public class Rotate_String_1 {

    private static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            return true;
        }
        char[] st = s.toCharArray();
        char[] gt = goal.toCharArray();

        for (int i = 1; i < st.length; i++) {
            rotate(st, i);
            if (Arrays.equals(st, gt)) return true;
            else rotate(st, st.length - i);
        }
        return false;

    }

    private static void rotate(char[] arr, int i) {

        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, i - 1);
        reverse(arr, i, arr.length - 1);

    }

    private static void reverse(char[] arr, int i, int j) {

        while (i <= j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

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

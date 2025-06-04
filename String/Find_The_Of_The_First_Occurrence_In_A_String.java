package String;

/*

Description:
    -> This program finds the index of the first occurrence of a substring (`str2`) within a main string (`str1`)...
    -> It demonstrates two methods: 
        1. Using Java’s built-in `indexOf()` method...
        2. Implementing a manual substring matching algorithm...

Problem Statement:
    -> Given two strings `str1` (haystack) and `str2` (needle), return the index of the first occurrence of `str2` in `str1`...
    -> If `str2` is not part of `str1`, return -1...

Approach:
    > Method 1 – Built-in Function:
        -> Directly uses `String.indexOf()` to find the index of `str2` in `str1`...

    > Method 2 – Manual Iteration (Brute Force):
        -> Iterate through `str1` using a loop to check every possible starting position for `str2`...
        -> At each index `i`, compare the substring of length equal to `str2` with `str2`...
        -> If all characters match, return the index `i`...

Algorithm Steps (Method 2):
    1. Measure lengths of both strings (`length1` for `str1`, `length2` for `str2`)...
    2. Iterate from `i = 0` to `length1 - length2`:
        a. For each position `i`, use a nested loop to compare each character of `str2` with `str1[i + j]`...
        b. If all characters match, return `i`...
        c. If mismatch occurs, break the inner loop and move to next index...
    3. If no match is found after the loop ends, return -1...

Key Characteristics:
    -> Demonstrates both standard library usage and a custom algorithm for educational comparison...
    -> Useful for understanding how string matching works at a low level...

Time and Space Complexity:
    -> Method 1 (indexOf):
        - Time Complexity: O(n * m) in worst case (depends on Java’s internal implementation)...
        - Space Complexity: O(1)...

    -> Method 2 (Manual Search):
        - Time Complexity: O(n * m), where n = length of `str1`, m = length of `str2`...
        - Space Complexity: O(1)...

Demonstration:
    -> Given:
        str1 = "sadbutsad"
        str2 = "sad"
    -> Output:
        Index of first occurrence of "sad" in "sadbutsad" is:
            - Method 1: 0
            - Method 2: 0

*/

public class Find_The_Of_The_First_Occurrence_In_A_String {

    private static int strStr1(String str1, String str2) {
        return str1.indexOf(str2);
    }

    private static int strStr2(String str1, String str2) {

        int length1 = str1.length();
        int length2 = str2.length();

        for (int i = 0; i <= length1 - length2; i++) {

            boolean flag = true;

            for (int j = 0; j < length2; j++) {

                if (str1.charAt(i + j) != str2.charAt(j)) {
                    flag = false;
                    break;
                }

            }

            if (flag) {
                return i;
            }

        }

        return - 1;

    }

    public static void main(String[] args) {

        String str1 = "sadbutsad";
        String str2 = "sad";

        System.out.println("Index of first occurrence of " + str2 + " in " + str1 + " is from method 1 : " + strStr1(str1, str2));
        System.out.println("Index of first occurrence of " + str2 + " in " + str1 + " is from method 2 : " + strStr2(str1, str2));

    }

}

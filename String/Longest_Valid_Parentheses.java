package String;

/* 

Description: 
    -> This program finds the length of the longest valid (well-formed) parentheses substring using a two-pass linear scan approach...
    -> It ensures optimal performance by scanning the input string from both left-to-right and right-to-left...
    -> The key idea is to count the number of '(' and ')' and track when they are balanced...

Problem Statement:
    -> Given a string s consisting of '(' and ')' characters...
    -> Return the length of the longest valid (well-formed) parentheses substring...

Approach:
    > Two-Pass Linear Scan Approach:
        -> First pass: Left to Right scan...
        -> Second pass: Right to Left scan...
        -> Both passes help to catch unbalanced prefixes and suffixes respectively...

Algorithm Steps:
    -> Initialization:
        1. Initialize left = 0, right = 0, and maxLength = 0...

    -> Left to Right Scan:
        1. Iterate through the string from start to end...
        2. For every '(', increment left...
        3. For every ')', increment right...
        4. If left == right, update maxLength with 2 * left...
        5. If right > left, reset both counters to 0 to ignore invalid prefixes...

    -> Right to Left Scan:
        1. Reset left = 0 and right = 0...
        2. Iterate from end of string to beginning...
        3. For every ')', increment right...
        4. For every '(', increment left...
        5. If left == right, update maxLength with 2 * left...
        6. If left > right, reset both counters to 0 to ignore invalid suffixes...

    -> Final Output:
        1. Return maxLength, which stores the maximum length of valid parentheses found...

Key Characteristics:
    -> Handles edge cases where unbalanced parentheses could exist at either end...
    -> Efficiently computes result in a single forward and backward scan...
    -> No extra space is used except for counters...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string...
    -> Space Complexity: O(1), constant space for counters only...

Demonstration:
    -> For input: "((()))()(()))))((()))(((())()()((((((()))))))"...
    -> Output: Length of longest valid parentheses substring is printed...

*/


public class Longest_Valid_Parentheses {

    private static int longestValidParentheses(String s) {

        int left = 0;
        int right = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(2 * left, maxLength);
            }

            if (left < right) {
                left = right = 0;
            }

        }

        left = right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(2 * left, maxLength);
            }

            if (left > right) {
                left = right = 0;
            }

        }
        return maxLength;

    }

    public static void main(String[] args) {

        String s = "((()))()(()))))((()))(((())()()((((((()))))))";
        System.out.println("Longest Valid Parentheses of the String has the length of : " + longestValidParentheses(s));
    }

}

package Stack;

/* 

Description: 
    -> This program calculates the length of the longest valid parentheses substring using brute-force checking and a stack-based validation approach...
    -> It generates all possible substrings and checks each for validity using a helper method that simulates a stack...

Problem Statement:
    -> Given a string s containing only parentheses characters like '()', '{}', '[]'...
    -> Determine the length of the longest valid (well-formed) substring...

Approach:
    > Brute-Force with Stack-Based Validation:
        -> Generate every possible substring from the original string...
        -> For each substring, check if it's valid using a stack...
        -> Keep track of the maximum length among all valid substrings...

Algorithm Steps:
    -> Helper Function: `isValid(String s)`:
        1. Convert string to character array...
        2. Use a stack to store opening brackets '(', '{', '['...
        3. For each character:
            -> If opening, push to stack...
            -> If closing, check top of stack for matching pair...
            -> If mismatched or stack is empty when expecting match, return false...
        4. At end, if stack is empty, return true (string is valid)...

    -> Main Function: `longestValidParentheses(String s)`:
        1. Handle base case where string is empty or has only one character — return 0...
        2. Iterate through all possible substrings using two nested loops...
        3. For each substring, use `isValid()` to check validity...
        4. Track and update maximum valid substring length...

    -> Final Output:
        1. Print and return the maximum length of valid substrings found...

Key Characteristics:
    -> Simple and straightforward implementation...
    -> Checks all possibilities, guaranteeing correctness...
    -> Uses a helper stack function for accurate bracket matching...
    -> Supports multiple bracket types: (), {}, []...

Time and Space Complexity:
    -> Time Complexity: O(n^3), due to nested loops and substring creation...
    -> Space Complexity: O(n) for stack used in validation...

Demonstration:
    -> For input: "()((()))()))()())))()()()()()()((()))"...
    -> Output: Prints length of the longest valid parentheses substring...

*/

import java.util.Stack;

public class Longest_Valid_Parentheses {

    private static boolean isValid(String s) {

        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<>();

        for (char c : arr) {

            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.isEmpty()) {
                    return false;
                } else {
                    char top = st.peek();

                    if ((c == ')' && top == '(') || (c == '}' && top == '{') || (c == ']' && top == '[')) {
                        st.pop();
                    } else {
                        return false;
                    }

                }

            }

        }

        return st.isEmpty();
    }

    private static int longestValidParentheses(String s) {

        if (s.isEmpty() || s.length() == 1) {
            return 0;
        }

        int maxSub = 0;

        for (int i = 0; i < s.length(); i++) {
            int temp = 0;

            for (int j = i + 1; j <= s.length(); j++) {
                if (isValid(s.substring(i, j))) {
                    temp = s.substring(i, j).length();
                }
            }

            maxSub = Math.max(maxSub, temp);

        }

        return maxSub;
    }

    public static void main(String[] args) {

        String s = "()((()))()))()())))()()()()()()((()))";
        System.out.println("The longest valid parentheses have the length of : " + longestValidParentheses(s));

    }

}


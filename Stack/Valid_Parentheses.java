package Stack;

/*

Description:
    -> This program checks whether a given string of parentheses/brackets/braces is valid.

Problem Statement:
    Given a string containing just the characters:
        - '(' , ')' 
        - '{' , '}'
        - '[' , ']'
    Determine if the input string is valid.

    A string is considered valid if:
        1. Every opening bracket has a corresponding closing bracket of the same type.
        2. Brackets close in the correct order (proper nesting).
        3. No unmatched or extra brackets remain.

Example:
    Input: 
        brackets = "()[]{}"
    Output:
        true
    Explanation:
        - Each opening bracket has a matching closing bracket.
        - Correctly nested and ordered.

    Input:
        brackets = "([)]"
    Output:
        false
    Explanation:
        - Even though counts match, the order of nesting is wrong.

Approach (Using Stack):
    1. Initialize an empty stack.
    2. Traverse the string character by character:
        - If the current character is an opening bracket 
          ('(', '{', '['), push it onto the stack.
        - If it is a closing bracket (')', '}', ']'):
            a. If the stack is empty → return false (no matching opening).
            b. Otherwise, check the top of the stack:
                - If it matches the current closing bracket, pop it.
                - If not, return false (mismatched type).
    3. After traversal:
        - If the stack is empty → return true (all brackets matched).
        - If not → return false (unmatched opening brackets remain).

Why this works:
    - The stack ensures last opened bracket must be closed first (LIFO principle).
    - Guarantees correct nesting and type-matching.

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string (single pass).
    -> Space Complexity: O(n), in the worst case when all characters are opening brackets.

Conclusion:
    This stack-based approach efficiently validates bracket sequences, 
    ensuring proper order and matching of parentheses, braces, and brackets.

*/

import java.util.Stack;

public class Valid_Parentheses {

    private static boolean isValid(String brackets) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char currChar = brackets.charAt(i);

            if (currChar == '(' || currChar == '[' || currChar == '{') {
                st.push(currChar);
            } else {

                if (st.isEmpty()) {
                    return false;
                } else {

                    if ((currChar == ')' && st.peek() == '(') || (currChar == ']' && st.peek() == '[') || (currChar == '}' && st.peek() == '{')) {
                        st.pop();
                    } else {
                        return false;
                    }

                }

            }

        }

        return st.isEmpty();
    }

    public static void main(String[] args) {

        String brackets = "()[]{}";
        boolean ans = isValid(brackets);

        if (ans) {
            System.out.println("The following String brackets " + brackets + " is Valid");
        } else {
            System.out.println("The following String brackets " + brackets + " is Not Valid");
        }

    }


}

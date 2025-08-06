package String;

/*

Description:
    -> This program calculates the minimum number of parentheses that must be added
       to a given string to make it a valid parentheses sequence.
    -> It efficiently tracks unmatched parentheses and returns the required additions.

Problem Statement:
    -> Given a string `sequence` containing only '(' and ')',
       return the minimum number of parentheses that must be added to make the string valid.

Approach:
    > Stack Simulation Using Counters:
        -> Use two integer counters `open` and `close`:
            - `open` counts unmatched '(' parentheses.
            - `close` counts unmatched ')' parentheses.
        -> Traverse the string character by character:
            - If the character is '(', increment `open`.
            - If the character is ')':
                * If there's an unmatched '(', decrement `open` (pairing it).
                * Otherwise, increment `close` (unmatched closing).

Algorithm Steps:
    -> Initialization:
        1. Initialize `open = 0` and `close = 0`.

    -> Iteration Over the String:
        1. For each character `currChar` in the string:
            a. If `currChar` is '(', increment `open`.
            b. If `currChar` is ')':
                - If `open > 0`, decrement `open` (valid pair found).
                - Else, increment `close` (need to add an opening).

    -> Finalizing:
        1. The result is the sum of `open + close`, which gives the total unmatched parentheses.

Key Characteristics:
    -> Time-efficient single-pass solution (O(n) time).
    -> No use of stack or extra space beyond two counters.
    -> Handles edge cases like all opening or all closing brackets.

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string.
    -> Space Complexity: O(1), constant space.

Demonstration:
    -> Input: sequence = "))(("
    -> Output: 4
       - Two unmatched ')' at the beginning.
       - Two unmatched '(' at the end.
       - Total additions needed: 2 '(' + 2 ')' = 4

*/

public class Minimum_Add_To_Make_Parentheses_Valid {

    private static int minAddToMakeValid(String sequence) {

        int close = 0;
        int open = 0;

        for (int i = 0; i < sequence.length(); i++) {
            char currChar = sequence.charAt(i);

            if (currChar == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }

        }

        return close + open;
        
    }

    public static void main(String[] args) {

        String sequence = "))((";
        System.out.println("Minimum number of parentheses to form a valid sequence is = " + minAddToMakeValid(sequence));

    }


}

package String;

/*

Description:
    -> This program checks whether a numeric string is **balanced**, meaning the sum of digits at even indices equals the sum at odd indices...
    -> It iterates through each character of the string, separates digits based on index parity, and compares their cumulative sums...

Problem Statement:
    -> Given a string `num` consisting of digits, determine if it is **balanced**...
    -> A string is considered balanced if the sum of digits at even indices equals the sum at odd indices (0-based indexing)...

Approach:
    > Index-Based Digit Summation:
        -> Traverse the string character by character...
        -> If index `i` is even, add digit to `sum1`...
        -> If index `i` is odd, add digit to `sum2`...
        -> After traversal, compare both sums to check for balance...

Algorithm Steps:
    -> Initialization:
        1. Initialize `sum1 = 0` and `sum2 = 0` to store sums of even and odd index digits respectively...

    -> Traversal and Summation:
        1. Loop over each character in the string using index `i`...
        2. Convert the character to integer using `num.charAt(i) - '0'`...
        3. If `i % 2 == 0`, add value to `sum1`; else, add to `sum2`...

    -> Final Check:
        1. Return true if `sum1 == sum2`, indicating the string is balanced...
        2. Otherwise, return false...

Key Characteristics:
    -> Efficient single-pass solution with linear time complexity...
    -> Uses basic arithmetic and character manipulation...
    -> Works for any length of numeric string...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string...
    -> Space Complexity: O(1), only integer variables used for summation...

Demonstration:
    -> Input: "324132526"
    -> Even indices: 3, 4, 3, 5, 6 → sum1 = 21
    -> Odd indices: 2, 1, 2, 2 → sum2 = 7
    -> Since sum1 ≠ sum2 → Output: "The given string is not balanced"

*/

public class Check_Balanced_String {

    private static boolean isBalanced(String num) {

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';

            if (i % 2 == 0) {
                sum1 += val;
            } else {
                sum2 += val;
            }

        }

        return sum1 == sum2;
    }

    public static void main(String[] args) {

        String s = "324132526";
        boolean isBalanced = isBalanced(s);

        if (isBalanced) {
            System.out.println("The given string is balanced");
        } else {
            System.out.println("The given string is not balanced");
        }
        
    }

}

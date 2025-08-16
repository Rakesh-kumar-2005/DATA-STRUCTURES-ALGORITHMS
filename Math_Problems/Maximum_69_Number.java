package Math_Problems;

/*

Description:
    -> This program converts a given integer consisting only of digits 6 and 9 
       into the maximum possible number by changing at most one digit '6' to '9'.
    -> It provides two different methods:
        1. Using digit extraction and reconstruction.
        2. Using string replacement.

Problem Statement:
    -> Given a positive integer number (containing only digits 6 and 9),
       return the maximum number obtainable by changing at most one '6' into '9'.
    -> If no '6' exists, the number remains unchanged.

Approach:
    > Method 1 (Digit Manipulation with ArrayList):
        -> Extract digits of the number one by one and store them in a list.
        -> Traverse the list from left to right:
            - On encountering the first '6', change it to '9' and stop further changes.
        -> Reconstruct the number from the modified list of digits.
        -> Return the result.

    > Method 2 (String Replacement):
        -> Convert the number to a string.
        -> Replace the first occurrence of '6' with '9' using replaceFirst().
        -> Convert the modified string back to an integer.
        -> Return the result.

Algorithm Steps:
    Method 1:
        1. Extract digits and store them in ArrayList in order.
        2. Traverse digits → replace the first '6' with '9'.
        3. Reconstruct the integer from digits.
        4. Return result.

    Method 2:
        1. Convert number to string.
        2. Replace first '6' with '9'.
        3. Parse string back to integer.
        4. Return result.

Key Characteristics:
    -> Guarantees maximum number by ensuring only the leftmost '6' is changed.
    -> Two different approaches (digit-based and string-based).
    -> Works for any positive integer containing digits 6 and 9.

Time and Space Complexity:
    -> Method 1: 
        - Time Complexity: O(d), where d = number of digits.
        - Space Complexity: O(d) for storing digits in a list.
    -> Method 2:
        - Time Complexity: O(d), string manipulation requires scanning digits.
        - Space Complexity: O(d), due to string conversion.

Example:
    Input:
        number = 69699
    Output:
        The maximum 69 number from the method 1 for the number 69699 is = 99699
        The maximum 69 number from the method 2 for the number 69699 is = 99699

*/

import java.util.ArrayList;

public class Maximum_69_Number {

    private static int maximum69Number1(int number) {
        ArrayList<Integer> ans = new ArrayList<>();

        while (number > 0) {
            ans.add(0, number % 10);
            number /= 10;
        }

        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i) == 6) {
                ans.set(i, 9);
                break;
            }
        }

        int result = 0;
        for (int digit : ans) {
            result = result * 10 + digit;
        }

        return result;
    }

    private static int maximum69Number2(int number) {
        return Integer.parseInt(String.valueOf(number).replaceFirst("6", "9"));
    }

    public static void main(String[] args) {

        int number = 69699;
        System.out.println("The maximum 69 number from the method 1 for the number " + number + " is = " + maximum69Number1(number));
        System.out.println("The maximum 69 number from the method 2 for the number " + number + " is = " + maximum69Number2(number));

    }

}

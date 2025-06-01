package Math_Problems;

/*

Description:
    -> This program implements two different approaches to solve the "Plus One" problem...
    -> It simulates the addition of one to a non-negative integer represented as an array of digits...

Problem Statement:
    -> Given a non-empty array of decimal digits representing a non-negative integer...
    -> Increment the integer by one and return the resulting array of digits...

Approach 1 (plusOne1):
    > In-place Optimization:
        -> Traverse the array from the end (least significant digit) towards the beginning...
        -> If a digit is less than 9, increment it and return immediately...
        -> If a digit is 9, set it to 0 and continue the carry...
        -> If all digits are 9, a new array of size n+1 is created with the leading digit as 1...

Algorithm Steps (plusOne1):
    1. Loop from the last index to the first:
        a. If current digit is less than 9:
            -> Increment it and return the array...
        b. Else:
            -> Set the current digit to 0 (carry forward)...
    2. If loop completes, all digits were 9:
        -> Create a new array with size n+1 and set the first digit to 1...
        -> Return the new array...

Approach 2 (plusOne2):
    > Using an ArrayList:
        -> Simulates manual addition with carry management...
        -> Supports larger manipulations and intermediate results stored in a dynamic list...

Algorithm Steps (plusOne2):
    1. Start from the last digit and initialize carry as 1...
    2. While there are digits to process or carry is not zero:
        a. Compute the sum of the digit and carry...
        b. Store `sum % 10` in the result list...
        c. Update carry to `sum / 10`...
    3. Reverse the ArrayList to match the correct digit order...
    4. Convert the ArrayList to an integer array and return...

Key Characteristics:
    -> Both approaches correctly handle edge cases like `[9, 9, 9]`...
    -> `plusOne1` is more space-efficient by modifying the array in-place...
    -> `plusOne2` is more flexible with dynamic sizing using `ArrayList`...

Time and Space Complexity:
    -> Time Complexity: O(n) for both methods, where n is the number of digits...
    -> Space Complexity:
        - plusOne1: O(1) if no new array is needed, otherwise O(n)...
        - plusOne2: O(n) due to ArrayList and result array creation...

Demonstration:
    -> Tests both methods on input `{9, 9, 9}`...
    -> Shows that both return the expected result: `{1, 0, 0, 0}`...

*/

import java.util.ArrayList;

public class Plus_One {

    private static int[] plusOne1(int[] digits) {

        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;

        }

        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;

        return newDigits;

    }

    private static int[] plusOne2(int[] digits) {

        ArrayList<Integer> ans = new ArrayList<>();

        int i = digits.length - 1;
        int sum = 0;
        int carry = 1;

        while (i >= 0 || carry != 0) {

            int val = (i >= 0) ? digits[i] : 0;

            sum = val + carry;
            carry = sum / 10;
            int rem = sum % 10;

            ans.add(rem);
            i--;

        }

        int[] result = new int[ans.size()];
        int idx = 0;

        for (int j = ans.size() - 1; j >= 0; j--) {
            result[idx++] = ans.get(j);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] digits = {9, 9, 9};
        int[] result = plusOne1(digits);

        System.out.println("Output from method 1: ");
        for (int num : result) {
            System.out.print(num);
        }

        System.out.println();

        digits = new int[]{9, 9, 9};
        result = plusOne2(digits);

        System.out.println("Output from method 2: ");
        for (int num : result) {
            System.out.print(num);
        }

    }

}

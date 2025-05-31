package Math_Problems;

/*

Description:
    -> This program performs binary addition of two binary strings...
    -> It returns the resulting binary string equivalent to the sum...

Problem Statement:
    -> Given two binary strings `a` and `b`...
    -> Return their sum as a binary string...

Approach:
    > Bit-by-bit Addition from Right to Left:
        -> Traverse both strings from the end (least significant bit) to the beginning...
        -> Add corresponding digits along with any carry from the previous addition...
        -> Build the result string in reverse and then reverse it at the end...

Algorithm Steps:
    -> Initialization:
        1. Set two pointers `i` and `j` to the end of strings `a` and `b` respectively...
        2. Initialize a variable `carry` to 0...
        3. Use a `StringBuilder` to construct the result string...

    -> Iterative Addition:
        1. While either pointer has not reached the beginning or there is a non-zero carry:
            a. Start with `sum = carry`...
            b. If `i >= 0`, add the integer value of `a.charAt(i)` to `sum`, then decrement `i`...
            c. If `j >= 0`, add the integer value of `b.charAt(j)` to `sum`, then decrement `j`...
            d. Append `sum % 2` to the result string (0 or 1)...
            e. Update `carry` to `sum / 2` (either 0 or 1)...

    -> Finalizing:
        1. Reverse the `StringBuilder` result to correct the digit order...
        2. Convert it to a string and return...

Key Characteristics:
    -> Handles binary strings of unequal lengths gracefully...
    -> Efficient in both time and space, using a single traversal...
    -> Avoids converting binary to decimal and back, staying in binary domain...

Time and Space Complexity:
    -> Time Complexity: O(max(m, n)), where m and n are lengths of the input strings...
    -> Space Complexity: O(max(m, n)), for storing the result in `StringBuilder`...

Demonstration:
    -> Takes two binary strings `a = "11"` and `b = "1"`...
    -> Applies the `addBinary` function...
    -> Outputs the resulting binary sum: "100"...

*/

public class Add_Binary_Numbers {

    private static String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {

            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();

    }

    public static void main(String[] args) {

        String a = "11";
        String b = "1";

        System.out.println("The sum of " + a + " and " + b + " is: " + addBinary(a, b));
        
    }

}

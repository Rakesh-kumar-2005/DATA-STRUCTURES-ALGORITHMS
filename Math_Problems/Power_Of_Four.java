package Math_Problems;

/*

Description:
    -> This program checks whether a given integer is a power of four.
    -> A power of four is defined as any positive integer that can be expressed 
       as 4 raised to the power of some non-negative integer (e.g., 1, 4, 16, 64, 256, ...).
    -> The algorithm uses iterative division to verify if the number can be reduced to 1 
       by repeatedly dividing by 4.

Problem Statement:
    -> Given an integer n, determine if it is a power of four.
    -> Return true if n is a power of four; otherwise, return false.
    -> Negative numbers, zero, and non-powers of four should return false.

Approach:
    > Iterative Division:
        -> If n <= 0 → immediately return false (invalid for powers of four).
        -> While n is divisible by 4:
            - Divide n by 4.
        -> After the loop, check if n == 1.
           - If yes → n is a power of four.
           - Else → not a power of four.

Algorithm Steps:
    1. If n <= 0 → return false.
    2. While n % 4 == 0:
        a. Divide n by 4.
    3. Return true if n == 1, else false.

Key Characteristics:
    -> Works for both small and large integers within the 32-bit signed range.
    -> Simple to understand and implement.
    -> Can be optimized further using bitwise checks and mathematical properties.

Time and Space Complexity:
    -> Time Complexity: O(log₄ n) (division reduces n by a factor of 4 each step).
    -> Space Complexity: O(1) (only a few extra variables used).

Example:
    Input:
        n = 16
    Output:
        The number 16 is a power of four.

*/

public class Power_Of_Four {

    private static boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;

    }

    public static void main(String[] args) {

        int n = 16;
        boolean result = isPowerOfFour(n);

        if (result) {
            System.out.println("The number " + n + " is a power of four.");
        } else {
            System.out.println("The number " + n + " is not a power of four.");
        }

    }

}

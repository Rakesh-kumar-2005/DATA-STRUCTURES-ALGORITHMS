package Math_Problems;

/*

Description:
    -> This program checks whether a given integer is a power of two.
    -> A power of two is defined as any positive integer that can be expressed
       as 2 raised to the power of some non-negative integer (e.g., 1, 2, 4, 8, 16, ...).
    -> The algorithm uses a bitwise trick to determine if the number has exactly one set bit.

Problem Statement:
    -> Given an integer n, determine if it is a power of two.
    -> Return true if n is a power of two; otherwise, return false.
    -> Negative numbers, zero, and non-powers of two should return false.

Approach:
    > Bitwise AND Trick:
        -> A power of two in binary form has exactly one bit set to 1.
           Example:
                1  -> 0001
                2  -> 0010
                4  -> 0100
                8  -> 1000
        -> For any power of two n:
                n & (n - 1) == 0
           This works because:
                - n has one set bit.
                - (n - 1) has all bits set after that bit position.
                - AND-ing them results in zero.
        -> Steps:
            1. Ensure n > 0 (since negative numbers and zero are not powers of two).
            2. Check if (n & (n - 1)) == 0.
            3. If both conditions are true → n is a power of two.

Algorithm Steps:
    1. Input the number n.
    2. If n <= 0 → return false.
    3. Compute (n & (n - 1)).
    4. If result == 0 → return true (n is a power of two).
    5. Else → return false.

Key Characteristics:
    -> Uses constant time check with bitwise operations.
    -> Avoids loops or recursion.
    -> Works efficiently for both small and large integers.

Time and Space Complexity:
    -> Time Complexity: O(1) (single bitwise operation).
    -> Space Complexity: O(1) (no extra space used).

Example:
    Input:
        n = 16
    Output:
        The number 16 is a power of two.

*/

public class Power_Of_Two {

    private static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void main(String[] args) {

        int number = 16;
        boolean result = isPowerOfTwo(number);

        if (result) {
            System.out.println("The number " + number + " is a power of two.");
        } else {

            System.out.println("The number " + number + " is not a power of two.");
        }

    }
    
}

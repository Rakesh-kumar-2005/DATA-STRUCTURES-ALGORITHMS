package Math_Problems;

/*

Description:
    -> This program checks whether a given integer is a power of three using two different methods.
    -> A power of three is defined as any positive integer that can be expressed as 3 raised to 
       the power of some non-negative integer (e.g., 1, 3, 9, 27, 81, ...).
    -> The first method uses a mathematical property with the largest power of 3 in the integer range.
    -> The second method uses iterative division to check divisibility by 3.

Problem Statement:
    -> Given an integer n, determine if it is a power of three.
    -> Return true if n is a power of three; otherwise, return false.
    -> Negative numbers, zero, and non-powers of three should return false.

Approach:
    > Method 1 (Mathematical Property):
        -> The largest power of 3 within the 32-bit signed integer range is 3^19 = 1162261467.
        -> If n is a power of three, it must divide 1162261467 evenly (without remainder).
        -> Steps:
            1. Ensure n > 0.
            2. Check if 1162261467 % n == 0.
            3. If yes → n is a power of three.

    > Method 2 (Iterative Division):
        -> Continuously divide n by 3 while it is divisible by 3.
        -> If the result becomes 1, then n is a power of three.
        -> Steps:
            1. If n <= 0 → return false.
            2. While n % 3 == 0 → n = n / 3.
            3. Return true if n == 1, else false.

Algorithm Steps:
    1. Take integer input n.
    2. Apply both methods:
        a. Method 1: Use max power of 3 divisibility check.
        b. Method 2: Use iterative division until non-divisible by 3.
    3. Print results for both methods.

Key Characteristics:
    -> Method 1 runs in O(1) time (single modulus operation).
    -> Method 2 runs in O(log₃ n) time (repeated division).
    -> Both use O(1) space.

Time and Space Complexity:
    -> Method 1: Time = O(1), Space = O(1)
    -> Method 2: Time = O(log₃ n), Space = O(1)

Example:
    Input:
        n = 27
    Output:
        The number 27 is a power of 3 using method 1 : true
        The number 27 is a power of 3 using method 2 : true

*/

public class Power_Of_Three {

    private static boolean isPowerOfThree1(int n) {

        int maxPowerOf3 = 1162261467;
        return n > 0 && maxPowerOf3 % n == 0;

    }

    private static boolean isPowerOfThree2(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n = n / 3;
        }

        return n == 1;

    }

    public static void main(String[] args) {

        int n = 27;
        boolean ans1 = isPowerOfThree1(n);
        boolean ans2 = isPowerOfThree2(n);

        if (ans1) {
            System.out.println("The number " + n + " is a power of 3 using method 1 : " + ans1);
        } else {
            System.out.println("The number " + n + " is not a power of 3 using method 1 : " + ans1);
        }

        if (ans2) {
            System.out.println("The number " + n + " is a power of 3 using method 2 : " + ans2);
        } else {
            System.out.println("The number " + n + " is not a power of 3 using method 2 : " + ans2);
        }

    }


}

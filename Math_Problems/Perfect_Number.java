package Math_Problems;

/*

Description:
    -> This program checks whether a given integer is a "Perfect Number" using two different methods.
    -> A Perfect Number is defined as a positive integer that is equal to the sum of all its 
       positive divisors excluding itself.
       Example: 6 → divisors = {1, 2, 3}, sum = 6 → Perfect Number.

Problem Statement:
    -> Given a positive integer num, determine if it is a Perfect Number.
    -> Return true if num is perfect, otherwise return false.

Approach:
    > Method 1 (Divisor Summation):
        -> Initialize a variable ans = 0.
        -> Iterate i from 1 to num / 2 (no divisor can be greater than num/2 except num).
        -> If num % i == 0 → add i to ans.
        -> After loop, check if ans == num.
        -> If yes → num is a perfect number.

    > Method 2 (Precomputed Known Perfect Numbers):
        -> Use the fact that all known perfect numbers within the 32-bit signed integer range are:
           6, 28, 496, 8128, 33550336.
        -> Return true if num matches one of these values.
        -> Else return false.
        -> This method works because perfect numbers are extremely rare and follow a 
           mathematical pattern involving Mersenne primes.

Algorithm Steps:
    Method 1:
        1. Initialize ans = 0.
        2. Loop i from 1 to num / 2:
            a. If num % i == 0 → ans += i.
        3. Return true if ans == num, else false.

    Method 2:
        1. Check if num matches any known perfect numbers.
        2. Return true if match found, else false.

Key Characteristics:
    -> Method 1 is general and works for any integer but is slower for large numbers.
    -> Method 2 is highly efficient (O(1)) but limited to known perfect numbers in 32-bit range.
    -> Demonstrates both brute-force and optimized mathematical approaches.

Time and Space Complexity:
    -> Method 1:
        - Time Complexity: O(n), since it checks all divisors up to n/2.
        - Space Complexity: O(1).
    -> Method 2:
        - Time Complexity: O(1).
        - Space Complexity: O(1).

Example:
    Input:
        num = 28
    Output:
        The number 28 is a perfect number by method 1
        The number 28 is a perfect number by method 2

*/

public class Perfect_Number {

    private static boolean checkPerfectNumber1(int num) {
        int ans = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) ans += i;
        }
        return ans == num;
    }

    private static boolean checkPerfectNumber2(int num) {
        return (num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336);
    }

    public static void main(String[] args) {

        int num = 28;
        boolean ans1 = checkPerfectNumber1(num);
        boolean ans2 = checkPerfectNumber2(num);

        if (ans1) {
            System.out.println("The number " + num + " is a perfect number by method 1");
        } else {
            System.out.println("The number " + num + " is not a perfect number by method 1");

        }

        if (ans2) {
            System.out.println("The number " + num + " is a perfect number by method 2");
        } else {
            System.out.println("The number " + num + " is not a perfect number by method 2");

        }

    }

}

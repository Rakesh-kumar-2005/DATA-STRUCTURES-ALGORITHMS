package Math_Problems;

/*

Description:
    -> This program checks whether a given number is an "ugly number"...
    -> A number is considered ugly if its only prime factors are 2, 3, or 5...

Problem Statement:
    -> Given an integer `n`, determine whether it is an ugly number...
    -> Return true if `n` is positive and all its prime factors are limited to 2, 3, or 5...
    -> Otherwise, return false...

Approach:
    > Recursive Division Method:
        -> Continuously divide the number by 2, 3, or 5 as long as it's divisible by them...
        -> If the number reduces to 1 through this process, then it is ugly...
        -> If at any point the number is not divisible by 2, 3, or 5 and greater than 1, then it contains other prime factors...

Algorithm Steps:
    1. Base Cases:
        a. If n == 0 → return false (zero is not considered ugly)...
        b. If n is between 1 and 3 (inclusive) → return true (since 1 is considered ugly, and 2 and 3 are prime factors themselves)...

    2. Recursive Division:
        a. If n is divisible by 2, recursively check for `n / 2`...
        b. If divisible by 3, recursively check for `n / 3`...
        c. If divisible by 5, recursively check for `n / 5`...

    3. Final Return:
        -> If none of the above divisions are possible, return false as it contains other prime factors...

Key Characteristics:
    -> Uses recursion to simplify the number by its prime factors...
    -> Elegant and concise method to validate ugly numbers...

Time and Space Complexity:
    -> Time Complexity: O(log n) — each division significantly reduces the number...
    -> Space Complexity: O(log n) — due to recursion stack depth in the worst case...

Demonstration:
    -> Example 1: n = 6
        - Factors: 2 × 3
        - Output: true (since both are allowed prime factors)...
    
    -> Example 2: m = 14
        - Factors: 2 × 7
        - Output: false (7 is not an allowed prime factor)...

*/

public class Ugly_Number {

    private static boolean isUgly(int n) {

        if (n == 0) {
            return false;
        }

        if (n <= 3 && n > 0) {
            return true;
        }

        if (n % 2 == 0) {
            return isUgly(n / 2);
        }

        if (n % 3 == 0) {
            return isUgly(n / 3);
        }

        if (n % 5 == 0) {
            return isUgly(n / 5);
        }

        return false;
        
    }

    public static void main(String[] args) {

        int n = 6;
        int m = 14;

        System.out.println("Is " + n + " ugly : " + isUgly(n));
        System.out.println("Is " + m + " ugly : " + isUgly(m));

    }

}

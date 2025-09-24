package Math_Problems;

/*

Description:
    -> This program calculates the sum of all numbers up to a given integer `n`
       that are divisible by 3, 5, or 7...
    -> It iterates through numbers from 3 to `n`, checking divisibility and
       accumulating their sum...

Problem Statement:
    -> Given an integer `n`, return the sum of all multiples of 3, 5, or 7 
       that are less than or equal to `n`...
    -> Example:
         Input: n = 7
         Multiples = {3, 5, 6, 7}
         Sum = 21

Approach:
    > Brute Force Iteration:
        -> Loop from 3 to `n`...
        -> For each number, check if it is divisible by 3, 5, or 7...
        -> If true, add it to the cumulative sum...
        -> Return the final result...

Algorithm Steps:
    1. Initialize sum = 0...
    2. Iterate `i` from 3 to `n`:
         - If `i % 3 == 0` OR `i % 5 == 0` OR `i % 7 == 0` → add `i` to sum...
    3. After loop ends, return sum...

Key Characteristics:
    -> Simple and easy-to-understand brute force approach...
    -> Works for any positive integer `n`...
    -> Directly implements the definition of multiples...

Time and Space Complexity:
    -> Time Complexity: O(n), since we loop from 1 to n...
    -> Space Complexity: O(1), as only a few variables are used...

Demonstration:
    -> Input: n = 7
    -> Numbers divisible by 3, 5, or 7: {3, 5, 6, 7}
    -> Sum = 3 + 5 + 6 + 7 = 21
    -> Output: "The sum of multiples of 3, 5, and 7 up to 7 is: 21"

*/

public class Sum_Multiples {

    private static int sumOfMultiples(int n) {
        int sum = 0;
        
        for (int i = 3; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) sum += i;
        }
        
        return sum;
    
    }

    public static void main(String[] args) {

        int n = 7;
        System.out.println("The sum of multiples of 3, 5, and 7 up to " + n + " is: " + sumOfMultiples(n));

    }

}

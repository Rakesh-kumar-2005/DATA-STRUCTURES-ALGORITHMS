package Math_Problems;

/*

Description:
    -> This program finds two positive integers (without any digit being zero) 
       such that their sum equals the given integer `n`.

Problem Statement:
    Given an integer `n`, split it into two positive integers `a` and `b` such that:
        1. a + b = n
        2. Neither `a` nor `b` contains the digit '0'

    Return any such pair of integers.

Example:
    Input: 
        n = 2

    Possible Output:
        1 1

    Explanation:
        - Both 1 and 1 are positive integers.
        - Their sum = 2
        - Neither contains the digit '0'.
        - Hence, valid answer.

    Another Example:
        Input:
            n = 101
        Possible Output:
            11 + 90 (invalid because 90 contains '0')
            Correct Output:
            11 + 90 ❌
            11 + 90 (skip, contains 0)
            13 + 88 ✔ (valid, no zero in both numbers)

Approach:
    1. Iterate from 1 to n-1:
        - Let i be the first number, j = n - i.
    2. Check if neither i nor j contains the digit '0'.
        - Convert i and j to strings and check with `.contains("0")`.
    3. Return the first valid pair {i, j}.
    4. If no such pair exists (which won’t happen for n >= 2), return {0, 0}.

Why this works:
    - Since every number `n` ≥ 2 can be expressed as the sum of two positive numbers, 
      and we check systematically, a valid pair will always be found.

Time and Space Complexity:
    -> Time Complexity: O(n * d), where d is the number of digits (string conversion + check).
    -> Space Complexity: O(1), only storing the result pair.

Conclusion:
    This brute-force but efficient approach guarantees finding two numbers 
    without zeros that add up to `n`.

*/

public class Convert_Integer_To_The_Sum_Of_Two_No_Zero_Integers {

    private static int[] getNoZeroIntegers(int n) {

        for (int i = 1; i < n; i++) {
            int j = n - i;

            if (! String.valueOf(i).contains("0") && ! String.valueOf(j).contains("0")) {
                return new int[]{i, j};
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {

        int n = 2;
        int[] ans = getNoZeroIntegers(n);

        System.out.println("The two Non-Zero numbers are which sum up to " + n + " are : ");
        System.out.println(ans[0] + " " + ans[1]);

    }

}

package Math_Problems;

/*

Description:
    -> This program calculates the number of set bits (1s) in the binary 
       representation of a given integer.
    -> The count of 1 bits is also known as the **Hamming Weight**.

Problem Statement:
    -> Given a positive integer n, return the number of '1' bits in its binary form.

Example:
    Input:  n = 15
    Binary: 1111
    Output: 4
    Explanation:
        Since binary 1111 has four '1's, the output is 4.

Approach:
    1. Initialize count = 0.
    2. Loop until n becomes 0:
        - Use bitwise AND (`n & 1`) to check the least significant bit:
            • If it's 1, increment count.
        - Right shift the number (`n >> 1`) to check the next bit.
    3. Return count.

Key Observations:
    -> Bitwise AND with 1 extracts the last bit of the number.
    -> Right shift (>>) moves to the next bit in each iteration.
    -> Efficient as it checks one bit per loop iteration.

Time and Space Complexity:
    -> Time Complexity: O(log n), since it iterates over the number of bits in n.
    -> Space Complexity: O(1), constant extra space.

Alternative Optimization:
    -> Use Brian Kernighan’s Algorithm:
        while (n != 0) {
            n = n & (n - 1); // removes the lowest set bit
            count++;
        }
    -> This reduces the number of iterations to the number of 1 bits instead of total bits.

Example Walkthrough:
    Input:  n = 15
    Binary: 1111
    Process:
        Step 1: n = 1111 → last bit = 1 → count = 1 → shift → 111
        Step 2: n = 111  → last bit = 1 → count = 2 → shift → 11
        Step 3: n = 11   → last bit = 1 → count = 3 → shift → 1
        Step 4: n = 1    → last bit = 1 → count = 4 → shift → 0
    Output: 4

*/

public class Number_Of_1_Bits {

    private static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }

    public static void main(String[] args) {

        int n = 15;
        System.out.println("The number of 1 bits in " + n + " is : " + hammingWeight(n));

    }

}

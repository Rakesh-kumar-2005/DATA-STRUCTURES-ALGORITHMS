package Math_Problems;

/*

Description:
    Following program demonstrates three different approaches to solve the "Climbing Stairs" problem
    using dynamic programming, recursion, and optimized iteration...

Problem Statement:
    -> Given a staircase with n steps...
    -> A person can climb either 1 or 2 steps at a time...
    -> The task is to find the total number of distinct ways to climb to the top...

Approach:
    > Three Different Implementations:
        i. Dynamic Programming with tabulation (bottom-up)...
        ii. Simple Recursion (top-down)...
        iii. Space-Optimized Iteration (Fibonacci-style)...

    > Algorithm Steps for Method 1 (Dynamic Programming):
        -> Initialization:
            1. Create a DP array of size n+1 to store the number of ways for each step...
            2. Set base cases: dp[0] = 1 and dp[1] = 1...

        -> DP state transition:
            1. Iterate from step 2 to n...
            2. For each step i, the number of ways equals the sum of ways to reach the previous two steps...
            3. Use the recurrence relation: dp[i] = dp[i-1] + dp[i-2]...

        -> Result calculation:
            1. Return dp[n] as the final answer...

    > Algorithm Steps for Method 2 (Recursion):
        -> Base cases:
            1. If n = 1, return 1 (only one way to climb 1 step)...
            2. If n = 2, return 2 (two ways to climb 2 steps: 1+1 or 2)...

        -> Recursive calculation:
            1. For any other n, calculate recursively using the formula: f(n) = f(n-1) + f(n-2)...
            2. This follows the Fibonacci sequence pattern...

    > Algorithm Steps for Method 3 (Space-Optimized):
        -> Base cases:
            1. If n ≤ 3, return n directly...

        -> Iterative calculation:
            1. Initialize variables i = 2 (ways for 2 steps) and j = 3 (ways for 3 steps)...
            2. Iterate from step 3 to n-1...
            3. Calculate count = i + j for current step...
            4. Update i = j and j = count for next iteration...

        -> Result calculation:
            1. Return the final count...

    > Key Characteristics:
        -> The problem follows the Fibonacci sequence pattern...
        -> Each solution trades off between time and space complexity...
        -> The DP solution uses O(n) space but avoids recalculations...
        -> The recursive solution is elegant but has exponential time complexity...
        -> The optimized solution achieves O(n) time with O(1) space...
        -> All three methods produce the same mathematical result...

    > Implementation Details:
        -> Method 1 uses an array to store all intermediate results...
        -> Method 2 uses pure recursion but suffers from redundant calculations...
        -> Method 3 uses only variables to track the necessary previous values...
        -> The program demonstrates all three methods with n = 16...
        -> Each method is implemented as a separate function for clarity...

    > Time and Space Complexity:
        -> Method 1 (DP): O(n) time, O(n) space...
        -> Method 2 (Recursion): O(2^n) time, O(n) space (recursion stack)...
        -> Method 3 (Optimized): O(n) time, O(1) space...
   
*/

public class Climbing_Stairs {

    private static int climbStairs1(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }

    private static int climbStairs2(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs2(n - 1) + climbStairs2(n - 2);

    }

    private static int climbStairs3(int n) {

        if (n <= 3) return n;

        int i = 2;
        int j = 3;

        int count = 0;

        for (int k = 3; k < n; k++) {
            count = i + j;
            i = j;
            j = count;
        }

        return count;
    }

    public static void main(String[] args) {

        int number = 16;
        System.out.println("The number of ways to climb " + number + " steps with method 1 is = " + climbStairs1(number));
        System.out.println("The number of ways to climb " + number + " steps with method 2 is = " + climbStairs2(number));
        System.out.println("The number of ways to climb " + number + " steps with method 3 is = " + climbStairs3(number));

    }

}

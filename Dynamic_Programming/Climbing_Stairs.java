package Dynamic_Programming;

/*

Description:
  Following program demonstrates multiple dynamic programming techniques to solve the classic "Climbing Stairs" problem, which asks in how many distinct ways a person can climb n stairs by taking either 1 or 2 steps at a time...

Problem Statement:
  -> You are given an integer stairs representing the total number of steps...
  -> You may climb either 1 step or 2 steps at a time...
  -> The task is to compute the total number of unique ways to reach the top of the staircase...
  -> This problem naturally follows the Fibonacci pattern:
         ways(n) = ways(n-1) + ways(n-2)...

Approach:
  > Method 1: Space-Optimized Iterative DP:
     i. Handles base cases: stairs ≤ 3 → return stairs...
     ii. Uses two running variables (curr, prev) to simulate Fibonacci recurrence...
     iii. Updates values iteratively without storing full DP array...
     iv. Runs in O(n) time and O(1) space...

  > Method 2: Pure Recursion (Brute Force):
     i. Directly applies recurrence:
           f(n) = f(n-1) + f(n-2)...
     ii. Extremely slow for large n (exponential time)...
     iii. Used only for conceptual demonstration...

  > Method 3: Memoization (Top-Down DP):
     i. Uses recursion combined with a dp[] array...
     ii. Stores results of previously computed states...
     iii. Converts exponential recursion into linear O(n) time...

  > Method 4: Tabulation (Bottom-Up DP):
     i. Builds dp[] array from base values upwards...
     ii. dp[i] = dp[i-1] + dp[i-2]...
     iii. Efficient and simple but uses O(n) space...

> Algorithm Steps:
  -> For Method 1:
       * Initialize curr = 3, prev = 2 for stairs ≥ 4...
       * Update values iteratively and return curr as final answer...
  -> For Method 2:
       * Direct recursive calls without caching...
  -> For Method 3:
       * Fill dp[] with -1...
       * Use recursion + memoization to compute ways...
  -> For Method 4:
       * Create dp[] of size stairs+1...
       * Set dp[1]=1, dp[2]=2 and fill until dp[stairs]...

> Implementation Note:
  -> Number of ways to climb stairs exactly matches Fibonacci sequence:
         ways(1)=1, ways(2)=2, ways(3)=3, ways(4)=5, ways(5)=8...
  -> Method 1 (space-optimized) is the most recommended in practice...
  -> Method 2 should be avoided for large input due to exponential growth...
  -> Memoization and tabulation provide fast and reliable alternatives...

> Example:
  -> stairs = 2:
       ways = 2 → (1+1), (2)...
  -> stairs = 3:
       ways = 3 → (1+1+1), (1+2), (2+1)...
  -> stairs = 5:
       ways = 8 (Fibonacci sequence)...

> Edge Cases:
  -> stairs = 1 → return 1...
  -> stairs = 2 → return 2...
  -> stairs <= 0 → treat invalid; recursion method returns 0 for negative values...
  -> Large stairs values best handled using Method 1 or Method 4...

> Time and Space Complexity:
  -> Method 1 (Optimized):
         Time: O(n)...
         Space: O(1)...
  -> Method 2 (Recursion):
         Time: O(2^n)...
         Space: O(n) recursion depth...
  -> Method 3 (Memoization):
         Time: O(n)...
         Space: O(n)...
  -> Method 4 (Tabulation):
         Time: O(n)...
         Space: O(n)...

*/

import java.util.Arrays;

public class Climbing_Stairs {

    private static int climbStairs1(int stairs) {
        if (stairs <= 3) {
            return stairs;
        }

        int countSteps = 0;
        int curr = 3;
        int prev = 2;

        for (int i = 3; i < stairs; i++) {

            countSteps = curr + prev;
            prev = curr;
            curr = countSteps;

        }

        return countSteps;

    }

    private static int climbStairs2(int stairs) {

        if (stairs < 0) {
            return 0;
        }

        if (stairs <= 3) {
            return stairs;
        }

        return climbStairs2(stairs - 1) + climbStairs2(stairs - 2);

    }

    private static int climbStairs3(int stairs, int[] dp) {

        if (stairs <= 2) {
            return stairs;
        }

        if (dp[stairs] != - 1) {
            return dp[stairs];
        }

        dp[stairs] = climbStairs3(stairs - 1, dp) + climbStairs3(stairs - 2, dp);

        return dp[stairs];
    }

    private static int climbStairs4(int stairs) {

        if (stairs <= 2) {
            return stairs;
        }

        int[] dp = new int[stairs + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= stairs; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[stairs];
    }

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║          CLIMBING STAIRS - ALL METHODS                ║");
        System.out.println("║     Problem: How many ways to climb n stairs?         ║");
        System.out.println("║          Can climb 1 or 2 steps at a time             ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: stairs = 2 ===");
        System.out.println("Stairs: 2");
        System.out.println("Ways: (1+1) or (2)");
        System.out.println("Method 1 (Optimized): " + climbStairs1(2));
        System.out.println("Method 2 (Recursion): " + climbStairs2(2));
        System.out.println("Expected: 2\n");

        System.out.println("=== Test Case 2: stairs = 3 ===");
        System.out.println("Stairs: 3");
        System.out.println("Ways: (1+1+1), (1+2), (2+1)");
        System.out.println("Method 1 (Optimized): " + climbStairs1(3));
        System.out.println("Method 2 (Recursion): " + climbStairs2(3));
        System.out.println("Expected: 3\n");

        System.out.println("=== Test Case 3: stairs = 5 ===");
        System.out.println("Stairs: 5");
        System.out.println("Ways: 8 different combinations");
        System.out.println("Method 1 (Optimized): " + climbStairs1(5));
        System.out.println("Method 2 (Recursion): " + climbStairs2(5));
        System.out.println("Expected: 8\n");

        System.out.println("=== Test Case 4: stairs = 10 ===");
        System.out.println("Stairs: 10");
        int[] dp = new int[11];
        Arrays.fill(dp, - 1);
        System.out.println("Method 1 (Optimized):  " + climbStairs1(10));
        System.out.println("Method 3 (Memoization): " + climbStairs3(10, dp));
        System.out.println("Method 4 (Tabulation):  " + climbStairs4(10));
        // Don't use Method 2 for large n - too slow!
        System.out.println("Expected: 89\n");

        System.out.println("=== Fibonacci Pattern (stairs 1-10) ===");
        System.out.print("Stairs: ");
        for (int i = 1; i <= 10; i++) System.out.printf("%4d", i);
        System.out.print("\nWays:   ");
        for (int i = 1; i <= 10; i++) System.out.printf("%4d", climbStairs1(i));
        System.out.println("\n");

        System.out.println("=== Performance Comparison (n=20) ===");
        int n = 20;

        long start1 = System.nanoTime();
        int result1 = climbStairs1(n);
        long time1 = System.nanoTime() - start1;
        System.out.println("Method 1 (Optimized):  " + result1 + " - Time: " + time1 + " ns");

        int[] dp20 = new int[n + 1];
        Arrays.fill(dp20, - 1);
        long start3 = System.nanoTime();
        int result3 = climbStairs3(n, dp20);
        long time3 = System.nanoTime() - start3;
        System.out.println("Method 3 (Memoization): " + result3 + " - Time: " + time3 + " ns");

        long start4 = System.nanoTime();
        int result4 = climbStairs4(n);
        long time4 = System.nanoTime() - start4;
        System.out.println("Method 4 (Tabulation):  " + result4 + " - Time: " + time4 + " ns");

        System.out.println("\nNote: Method 2 (Pure Recursion) skipped - too slow for n=20!");

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║       BEST APPROACH: Method 1 (Space Optimized)       ║");
        System.out.println("║               Time: O(n), Space: O(1)                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        
    }

}

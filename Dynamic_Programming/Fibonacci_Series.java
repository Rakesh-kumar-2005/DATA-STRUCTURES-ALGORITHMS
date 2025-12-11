package Dynamic_Programming;

/*

Description:
  Following program demonstrates multiple dynamic programming techniques to compute the Fibonacci series, 
      showcasing memoization, tabulation, and space-optimized iterative approaches...

Problem Statement:
  -> Given an integer n, compute the nth Fibonacci number...
  -> Fibonacci follows the recurrence:
         F(0) = 0, F(1) = 1
         F(n) = F(n-1) + F(n-2) for n ≥ 2...
  -> The goal is to implement efficient methods avoiding exponential recursion...

Approach:
  > Method 1: Memoization (Top-Down DP):
     i. Use recursion with an auxiliary dp[] array to store computed results...
     ii. Before computing F(n), check if dp[n] already contains the value...
     iii. If yes, return dp[n] directly (avoids recomputation)...
     iv. Otherwise compute recursively and store dp[n]...

  > Method 2: Tabulation (Bottom-Up DP):
     i. Create a dp[] array of size n+1...
     ii. Initialize dp[0] = 0 and dp[1] = 1...
     iii. Fill the table iteratively using:
            dp[i] = dp[i-1] + dp[i-2]...
     iv. Return dp[n] as the final result...

  > Method 3: Space-Optimized Iterative Method:
     i. Instead of storing entire dp[] array, track only:
            prev2 = F(n-2)
            prev1 = F(n-1)
     ii. Compute current value: curr = prev1 + prev2...
     iii. Update prev2 ← prev1 and prev1 ← curr for each iteration...
     iv. This reduces space to O(1)...

  > Method 4: Clean Iterative Version:
     i. Similar to method 3 but written in a more compact style...
     ii. Uses two variables a and b to track sequence progression...

> Algorithm Steps:
  -> Choose a method depending on efficiency requirements...
  -> For memoization:
       * Initialize dp[] with -1 and call recursive function...
  -> For tabulation:
       * Build dp[] iteratively from base cases...
  -> For space-optimized methods:
       * Loop from 2 to n while updating two running values...
  -> Return the computed Fibonacci number for each method...

> Implementation Note:
  -> Memoization reduces recursion from exponential O(2^n) to linear O(n)...
  -> Tabulation avoids recursion entirely and computes sequentially...
  -> Space-optimized version uses only constant memory...
  -> All methods correctly handle edge cases n = 0 and n = 1...

> Example:
  -> For n = 10:
       F(10) = 55...
       All four methods compute the same output...
  -> The main method prints Fibonacci values from F(0) to F(10) using method 4...

> Edge Cases:
  -> n = 0 → returns 0...
  -> n = 1 → returns 1...
  -> Large n should prefer iterative methods to avoid stack overflow...
  -> Negative n is not supported (typical Fibonacci domain)...

> Time and Space Complexity:
  -> Method 1 (Memoization):
         Time: O(n)...
         Space: O(n) for recursion + dp array...
  -> Method 2 (Tabulation):
         Time: O(n)...
         Space: O(n)...
  -> Method 3 & Method 4 (Space-Optimized):
         Time: O(n)...
         Space: O(1)...

*/

import java.util.Arrays;

public class Fibonacci_Series {

    private static int method1(int n, int[] dp) {

        if (n <= 1) {
            return n;
        }

        if (dp[n] != - 1) {
            return dp[n];
        }

        dp[n] = method1(n - 1, dp) + method1(n - 2, dp);

        return dp[n];

    }

    private static int method2(int n) {

        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }

    private static int method3(int n) {

        if (n <= 1) {
            return n;
        }

        int prev2 = 0;
        int prev1 = 1;
        int curr = 0;

        for (int i = 2; i <= n; i++) {

            curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;

        }

        return curr;

    }

    private static int method4(int n) {
        if (n <= 1) {
            return n;
        }
        
        int a = 0, b = 1;
        
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        
        return b;
    }

    public static void main(String[] args) {

        int n = 10;
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, - 1);

        // Method 1: Memoization
        System.out.println("Method 1 (Memoization) - Fib(" + n + "): " + method1(n, dp));

        // Method 2: Tabulation
        System.out.println("Method 2 (Tabulation) - Fib(" + n + "): " + method2(n));

        // Method 3: Space Optimized
        System.out.println("Method 3 (Space Optimized) - Fib(" + n + "): " + method3(n));

        // Method 4: Cleaner version
        System.out.println("Method 4 (Clean) - Fib(" + n + "): " + method4(n));

        // Test all methods with multiple values
        System.out.println("\n=== Fibonacci Sequence (0-10) ===");
        System.out.print("Index:  ");

        for (int i = 0; i <= 10; i++) {
            System.out.print(i + "  ");
        }

        System.out.print("\nValue:  ");

        for (int i = 0; i <= 10; i++) {
            System.out.print(method4(i) + "  ");
        }

    }

}

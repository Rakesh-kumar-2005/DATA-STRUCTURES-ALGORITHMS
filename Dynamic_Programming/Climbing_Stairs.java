package Dynamic_Programming;

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
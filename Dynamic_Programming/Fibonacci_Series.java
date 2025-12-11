package Dynamic_Programming;

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
        if (n <= 1) return n;

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

        // Method 1: Memoization
        int[] dp = new int[n + 1];
        Arrays.fill(dp, - 1);
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
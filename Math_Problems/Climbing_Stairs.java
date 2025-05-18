package Math_Problems;


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
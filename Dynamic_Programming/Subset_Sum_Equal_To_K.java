package Dynamic_Programming;

public class Subset_Sum_Equal_To_K {

    private static boolean recursive(int[] arr, int idx, int target) {

        if (target == 0) {
            return true;
        }

        if (idx == 0) {
            return arr[0] == target;
        }

        boolean notTaken = recursive(arr, idx - 1, target);
        boolean taken = arr[idx] <= target && recursive(arr, idx - 1, target - arr[idx]);

        return notTaken || taken;

    }

    private static boolean memoization(int[] arr, int idx, int target, int[][] dp) {

        if (target == 0) {
            return true;
        }

        if (idx == 0) {
            return arr[0] == target;
        }

        if (dp[idx][target] != - 1) {
            return dp[idx][target] == 1;
        }

        boolean notTaken = memoization(arr, idx - 1, target, dp);
        boolean taken = arr[idx] <= target && memoization(arr, idx - 1, target - arr[idx], dp);

        dp[idx][target] = (notTaken || taken) ? 1 : 0;
        return notTaken || taken;

    }

}

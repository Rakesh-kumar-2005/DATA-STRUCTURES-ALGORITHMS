package Arrays;

import java.util.Arrays;

public class Range_Sum_Of_Sorted_Subarray_Sums {

    private static int rangeSum(int[] nums, int n, int left, int right) {

        int[] arr = new int[n * (n + 1) / 2];

        for (int i = 0, k = 0; i < n; ++ i) {
            int sum = 0;

            for (int j = i; j < n; ++ j) {
                sum += nums[j];
                arr[k++] = sum;
            }

        }

        Arrays.sort(arr);
        int ans = 0;
        final int mod = (int) 1e9 + 7;

        for (int i = left - 1; i < right; ++ i) {
            ans = (ans + arr[i]) % mod;
        }

        return ans;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        int left = 1;
        int right = 5;
        System.out.println("The range sum of sorted subarray sums is : " + rangeSum(arr, n, left, right));

    }

}
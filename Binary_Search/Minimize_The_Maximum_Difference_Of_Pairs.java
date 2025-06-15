package Binary_Search;

import java.util.Arrays;

public class Minimize_The_Maximum_Difference_Of_Pairs {

    private static int minimizeMax(int[] nums, int pairs) {

        Arrays.sort(nums);
        int low = - 1;
        int high = (int) 1e9 + 7;

        while (low < high - 1) {

            int mid = low + (high - low) / 2;
            int count = 0;

            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i + 1] - nums[i] <= mid) {
                    count++;
                    i++;
                }
            }

            if (count >= pairs) {
                high = mid;
            } else {
                low = mid;
            }

        }

        return high;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 9};
        int pairs = 2;
        System.out.println("The minimized maximum difference of pairs is = " + minimizeMax(nums, pairs));

    }

}
package Arrays;

import java.util.Arrays;

public class Move_Zeros {

    private static void moveZeroes(int[] nums) {

        int n = nums.length;
        int i = 0;

        for (int j = 0; j < n; j++) {

            if (nums[j] != 0) {
                swap(nums, i, j);
                i++;
            }

        }

    }

    private static void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("Original Array : " + Arrays.toString(nums));

        moveZeroes(nums);
        System.out.println("Modified Array : " + Arrays.toString(nums));

    }

}
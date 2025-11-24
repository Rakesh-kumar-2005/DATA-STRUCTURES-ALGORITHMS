package Math_Problems;

import java.util.ArrayList;

public class Binary_Prefix_Divisible_By_5 {

    private static ArrayList<Boolean> prefixesDivBy5(int[] nums) {

        ArrayList<Boolean> ans = new ArrayList<>();
        int val = 0;

        for (int num : nums) {
            val = ((val << 1) + num) % 5;
            ans.add(val == 0);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, 1, 1, 0};
        ArrayList<Boolean> ans = prefixesDivBy5(nums);

        System.out.println("=== Binary Prefix Divisible By 5 ===\n");
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        System.out.println();

        int decimalValue = 0;

        for (int i = 0; i < nums.length; i++) {

            decimalValue = (decimalValue << 1) + nums[i];
            int modulo = decimalValue % 5;

            System.out.println("Step " + (i + 1) + ":");
            System.out.println("  Binary prefix: " + getBinaryString(nums, i));
            System.out.println("  Decimal value: " + decimalValue);
            System.out.println("  Modulo 5: " + modulo);
            System.out.println("  Divisible by 5? " + ans.get(i));
            System.out.println();
        }

        System.out.println("Final Result: " + ans);
    }

    private static String getBinaryString(int[] nums, int endIndex) {

        StringBuilder binary = new StringBuilder();

        for (int i = 0; i <= endIndex; i++) {
            binary.append(nums[i]);
        }

        return binary.toString();

    }

}
package Arrays;

public class Number_Of_Zero_Filled_Subarrays {

    private static long zeroFilledSubarray(int[] nums) {

        long count = 0;
        long streak = 0;

        for (int num : nums) {
            streak = (num == 0) ? streak + 1 : 0;
            count += streak;
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 0, 0, 2, 0, 0, 4};
        System.out.println("Total number of sub-arrays filled with Zero = " + zeroFilledSubarray(nums));

    }

}
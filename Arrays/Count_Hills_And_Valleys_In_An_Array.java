package Arrays;

public class Count_Hills_And_Valleys_In_An_Array {

    private static int countHillValley(int[] nums) {

        int count = 0;
        int prev = 0;

        for (int curr = 1; curr < nums.length - 1; curr++) {

            if ((nums[prev] > nums[curr] && nums[curr] < nums[curr + 1]) ||
                (nums[prev] < nums[curr] && nums[curr] > nums[curr + 1])) {
                count++;
                prev = curr;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {2, 4, 1, 1, 6, 5};
        System.out.println("The number of hills and valleys is = " + countHillValley(nums));

    }

}
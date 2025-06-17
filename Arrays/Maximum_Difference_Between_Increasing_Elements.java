package Arrays;

public class Maximum_Difference_Between_Increasing_Elements {

    private static int maximumDifference(int[] nums) {

        int n = nums.length;
        int maxDiff = - 1;

        int i = 0;
        for (int j = 0; j < n; j++) {

            if (j > i && nums[j] > nums[i]) {
                int currDiff = nums[j] - nums[i];
                maxDiff = Math.max(maxDiff, currDiff);
            } else {
                i = j;
            }
            
        }

        return maxDiff;
    }

    public static void main(String[] args) {

        int[] nums = {7, 1, 5, 4, 6, 4};
        System.out.println("The maximum difference in the increasing elements is = " + maximumDifference(nums));
    }

}
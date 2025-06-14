package Arrays;

public class Maximum_Difference_Between_Adjacent_Elements_In_A_Circular_Array {

    private static int maxAdjacentDistance(int[] nums) {

        int maxDiff = 0;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int currDiff = Math.abs(nums[i - 1] - nums[i]);
            maxDiff = Math.max(maxDiff, currDiff);
        }

        int edgeDiff = Math.abs(nums[0] - nums[n - 1]);
        maxDiff = Math.max(maxDiff, edgeDiff);

        return maxDiff;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 6};
        System.out.println("The maximum difference between adjacent elements in a circular array is = " + maxAdjacentDistance(nums));

    }

}
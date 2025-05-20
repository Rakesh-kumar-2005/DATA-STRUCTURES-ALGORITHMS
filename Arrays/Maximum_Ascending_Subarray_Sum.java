package Arrays;

public class Maximum_Ascending_Subarray_Sum {

    private static int maxAscendingSum(int[] nums) {

        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        
        int i = 0;
        int tempSum = 0;

        while (i < n) {
            tempSum += nums[i];
            maxSum = Math.max(maxSum, tempSum);

            if (i != n - 1 && nums[i] >= nums[i + 1]) {
                tempSum = 0;
            }
            i++;
        }

        return maxSum;

    }

    public static void main(String[] args) {

        int[] nums = {10, 20, 30, 5, 10, 50};
        System.out.println("The maximum ascending subarray sum is : " + maxAscendingSum(nums));

    }

}
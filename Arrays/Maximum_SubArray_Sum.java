package Arrays;

public class Maximum_SubArray_Sum {

    private static int maxSubarraySum(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static int helper(int[] arr, int low, int high) {
        if (low > high) return Integer.MIN_VALUE;

        int mid = (low + high) / 2;
        int leftSum = 0, rightSum = 0;

        int curSum = 0;
        for (int i = mid - 1; i >= low; i--) {
            curSum += arr[i];
            leftSum = Math.max(leftSum, curSum);
        }

        curSum = 0;
        for (int i = mid + 1; i <= high; i++) {
            curSum += arr[i];
            rightSum = Math.max(rightSum, curSum);
        }

        int leftMax = helper(arr, low, mid - 1);
        int rightMax = helper(arr, mid + 1, high);

        return Math.max(Math.max(leftMax, rightMax), leftSum + arr[mid] + rightSum);
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum sub-array sum of the array is = " + maxSubarraySum(arr));
    }
}

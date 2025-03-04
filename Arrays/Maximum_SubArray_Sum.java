package Arrays;

/*
        Description :-
            This Java program finds the maximum sub-array sum using a divide-and-conquer
            approach. It recursively divides the array, computes the max sum for left,
            right, and crossing sub-arrays, and returns the highest value...

        Approach :-
            > The algorithm recursively splits the array into left and right halves,
                computing their maximum sub-array sums...
            > It also calculates the maximum sum of a sub-array crossing the midpoint...
            > The crossing sum is found by extending from the middle to the left and right...
            > The function returns the maximum of the three values: 
                left sub-array, right sub-array, and crossing sub-array...
            > The base case handles a single element.
            > This method follows O(n log n) time complexity, making it efficient for large inputs...
*/

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

package Binary_Search;

/*
        Description :-
                The question revolves around finding the smallest integer divisor for an array of numbers
                such that when each number in the array is divided by this divisor (with the result rounded
                up to the nearest integer), the total sum of these rounded-up results does not exceed a given
                threshold....

        Approach :-
              > Using binary search, it iteratively adjusts the range (low to high, starting from 1 to the maximum
                  element of the array) by checking the division result...
              > The helper function computes the sum of rounded-up divisions for a given divisor. If the sum
                  exceeds the threshold, the search moves higher; otherwise, it lowers the range...
              > Time Complexity = O(n log(n))...
              > Space Complexity = O(1)...
*/

public class Finding_The_Smallest_Divisor_GIven_A_Threshold {

    private static int helper(int[] arr, int div) {
        int ans = 0;
        for (int num : arr) {
            ans += (int) Math.ceil((double) num / (double) div);
        }
        return ans;
    }

    private static int smallestDivisor(int[] arr, int threshold) {
        if (arr.length > threshold) return -1;

        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int num : arr) {
            high = Math.max(high, num);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            int total = helper(arr, mid);

            if (total <= threshold) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {44, 22, 33, 11, 1};
        int threshold = 5;

        System.out.println("For not to exceed the Threshold value the smallest divisor is = " + smallestDivisor(arr, threshold));
    }
}

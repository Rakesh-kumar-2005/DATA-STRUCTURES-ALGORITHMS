package Math_Problems;

public class Longest_Subarray_With_Maximum_Bitwise_AND {

    private static int longestSubarray(int[] arr) {

        int maxVal = 0;
        int ans = 0;
        int curr = 0;

        for (int i : arr) {

            if (maxVal < i) {
                maxVal = i;
                ans = curr = 0;
            }

            if (maxVal == i) {
                curr++;
            } else {
                curr = 0;
            }

            ans = Math.max(ans, curr);

        }

        return ans;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 3, 6, 7};
        System.out.println("Length of the longest Subarray with Maximum Bitwise AND is : " + longestSubarray(arr));

    }

}

package Math_Problems;

/*

Description:
    -> This program finds the length of the longest contiguous subarray where
       all elements are equal to the maximum value in the array.
    -> Since the bitwise AND of identical maximum values is equal to the maximum itself,
       the problem reduces to finding the longest sequence of consecutive maximum elements.

Problem Statement:
    Given an integer array 'arr', return the length of the longest subarray
    such that the bitwise AND of all elements in this subarray is equal to
    the maximum value present in the entire array.

Example:
    Input:  arr = [1, 2, 3, 3, 6, 7]
    Maximum element = 7
    Longest consecutive run of maximum = [7]
    Output: 1

    Input:  arr = [5, 5, 5, 1, 2, 5, 5]
    Maximum element = 5
    Longest consecutive run of maximum = [5, 5, 5]
    Output: 3

Approach:
    1. Traverse the array to find the maximum element (`maxVal`).
    2. Keep a running counter (`curr`) for the length of the current consecutive sequence
       of elements equal to `maxVal`.
    3. Reset `curr` to 0 if the current element is not equal to `maxVal`.
    4. Update the answer (`ans`) with the maximum value of `curr` found during traversal.
    5. Return `ans` as the final result.

Helper Variables:
    -> maxVal : stores the current maximum value in the array.
    -> curr   : stores the length of the current consecutive run of maxVal.
    -> ans    : stores the maximum length of such subarrays found so far.

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the array (single traversal).
    -> Space Complexity: O(1), only a few variables are used.

Example Walkthrough:
    Input: arr = [1, 2, 3, 3, 6, 7]
    maxVal = 7
    Iteration:
        i=1 → maxVal=1, curr=1, ans=1
        i=2 → maxVal=2, curr=1, ans=1
        i=3 → maxVal=3, curr=1 → 2, ans=2
        i=6 → maxVal=6, curr=1, ans=2
        i=7 → maxVal=7, curr=1, ans=2
    Output: 1

Conclusion:
    The algorithm efficiently computes the length of the longest subarray
    where the bitwise AND equals the maximum element of the array.

*/

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

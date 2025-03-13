package Binary_Search;

/*
        Description :-
			Given a sorted array of distinct positive integers, find the k-th missing positive number that
			does not appear in the array. The missing numbers are counted in an increasing sequence
			from 1 onward...

        Approach :-
              > This solution uses binary search to efficiently find the kth missing positive number...
              > The key insight is calculating how many positive integers are missing at each position...
              > At index mid, the value arr[mid] - (mid + 1) represents the count of missing numbers. If this
              	count is less than k, search in the right half; otherwise, search in the left half...
              > The solution completes when the binary search terminates, returning low + k as the answer,
              	where low is the final position after the binary search and k is adjusted to find the exact missing number....
              > Time Complexity = O(log n)...
              > Space Complexity = O(1)...
*/

import java.util.Arrays;

public class Kth_Missing_Positive_Number {

	private static int findKthPositive(int[] arr, int k) {
		int n = arr.length;
		int low = 0;
		int high = n - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			int missing = arr[ mid ] - (mid + 1);

			if (missing < k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low + k;
	}

	public static void main(String[] args) {
		int[] arr = {2, 3, 4, 7, 11};
		int k = 5;
		System.out.println(Arrays.toString(arr));
		System.out.println("The " + k + "th missing positive number in the above array is = " + findKthPositive(arr, k));
	}
}

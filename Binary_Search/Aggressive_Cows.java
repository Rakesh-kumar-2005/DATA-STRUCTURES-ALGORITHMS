package Binary_Search;

/*
Description:
   Following program demonstrates the solution to the Aggressive Cows problem using Binary Search...

Problem Statement:
   -> Given an array representing positions of N stalls and K cows...
   -> We need to assign cows to stalls such that the minimum distance between any two cows is maximized...

Approach:
   > Binary Search on Answer:
      i. Sort the array of stall positions...
      ii. The minimum possible distance is 1, and maximum possible distance is the difference between the farthest stalls...
      iii. Apply binary search on this range to find the maximum possible minimum distance...

> Validation Function (canWePlace):
   -> For a given distance, check if all K cows can be placed:
      i. Place the first cow at the first stall...
      ii. Try to place each subsequent cow at the next stall that is at least the given distance away...
      iii. If we can place all K cows, the distance is valid; otherwise, it's not...

> Binary Search Process:
   -> If we can place all cows with a minimum distance of 'mid':
      i. This distance is possible, so try a larger distance (low = mid + 1)...
   -> If we cannot place all cows:
      i. Try a smaller distance (high = mid - 1)...
   -> Continue until low > high, then return high as the answer...

> Time and Space Complexity:
   -> Time Complexity: O(N log M) where N is the number of stalls and M is the range of possible distances...
   -> Space Complexity: O(1) extra space (excluding input)...
   -> Sorting the stalls takes O(N log N) time...
*/

import java.util.Arrays;

public class Aggressive_Cows {

	private static boolean canWePlace(int[] stalls, int dist, int cows) {
		int n = stalls.length;
		int cntCows = 1;
		int last = stalls[ 0 ];
		
		for (int i = 1; i < n; i++) {
			if (stalls[ i ] - last >= dist) {
				cntCows++;
				last = stalls[ i ];
			}
			if (cntCows >= cows) return true;
		}
		return false;
	}

	private static int aggressiveCows(int[] stalls, int k) {
		int n = stalls.length;

		Arrays.sort(stalls);
		int low = 1;
		int high = stalls[ n - 1 ] - stalls[ 0 ];

		while (low <= high) {
			int mid = (low + high) / 2;
			if (canWePlace(stalls, mid, k)) {
				low = mid + 1;
			} else{
				high = mid - 1;
			}
		}
		return high;
	}

	public static void main(String[] args) {
		int[] stalls = {0, 3, 4, 7, 10, 9};
		int k = 4;
		int ans = aggressiveCows(stalls, k);
		System.out.println("The maximum possible minimum distance is: " + ans);
	}
}

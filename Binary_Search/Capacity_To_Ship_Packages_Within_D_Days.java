package Binary_Search;

/*
        Description :-
                The task is to find the minimum capacity needed for a ship to transport arr weights within days...
                Each sub-array (day's load) cannot exceed the ship's capacity, and loads must remain in order....

        Approach :-
              > You need to divide a list of weights (arr) into a set number of days (days) so that each day's total
                   weight doesn't exceed the ship's capacity. The goal is to find the smallest capacity that still allows
                   you to do this...
			  > The code uses binary search between the heaviest single weight (minimum capacity) and the total
			  	   of all weights (maximum capacity). It checks if a guessed capacity (mid) works by counting how
			  	   many days it would take to ship all weights with that capacity...
			  > Depending on the result, it adjusts the search range until it finds the smallest capacity that works....
              > Time Complexity = O(n log(n))...
              > Space Complexity = O(1)...
*/

public class Capacity_To_Ship_Packages_Within_D_Days {

	private static int shipWithinDays(int[] weights, int days) {
		int low = 0;
		int high = 0;
		int ans = 0;

		for (int num : weights) {
			low = Math.max(low, num);
			high += num;
		}

		while (low <= high) {
			int mid = low + (high - low) / 2;
			int total = possible(weights, mid);

			if (total <= days) {
				high = mid - 1;
				ans = mid;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	private static int possible(int[] weights, int cap) {
		int total = 1;
		int curr = 0;

		for (int num : weights) {
			if (curr + num > cap) {
				total++;
				curr = 0;
			}
			curr += num;

		}
		return total;
	}

	public static void main(String[] args) {
		int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int days = 5;
		System.out.println("The minimum capacity of the ship has to be = " + shipWithinDays(weights, days) + " to transport the packages...");
	}

}

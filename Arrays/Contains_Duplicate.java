package Arrays;

/*
        Description :-
                The problem requires checking if an array contains any duplicate elements...
                Given an integer array, the goal is to determine whether any number appears
					more than once. If duplicates exist, return true; otherwise, return false....

        Approach :-
              	> If the array has only one element, return false since no duplicates are possible...
				> Create a HashSet to store unique numbers...
				> Iterate through the array:
						-> If a number is already in the set, return true (duplicate found)...
						-> Otherwise, add the number to the set.
				>If no duplicates are found, return false....
             	> Time Complexity = O(n)...
             	> Space Complexity = O(n)...
*/

import java.util.Arrays;
import java.util.HashSet;

public class Contains_Duplicate {

	private static boolean duplicateChecking(int[] arr) {
		if (arr.length == 1) {
			return false;
		}
		HashSet<Integer> set = new HashSet<>();

		for (int number : arr) {
			if (set.contains(number)) {
				return true;
			} else {
				set.add(number);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = {1, 2, 2, 33, 5, 66, 7, 22, 2};
		boolean ans = duplicateChecking(arr);

		System.out.println("The array is : ");
		System.out.println(Arrays.toString(arr));

		if (ans) {
			System.out.println("This array contains duplicate elements...");
		} else {
			System.out.println("This array doesn't contain any duplicate elements...");
		}
	}

}
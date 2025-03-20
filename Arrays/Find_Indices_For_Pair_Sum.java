package Arrays;

/*

Description:
  Following program demonstrates the solution to the "Two Sum" or "Find Indices For Pair Sum" problem using a HashMap-based approach...

Problem Statement:
  -> Given an array of integers and a target sum...
  -> Find indices of two numbers in the array such that they add up to the given target sum...
  -> Return the indices of the two numbers if found, otherwise return null...

Approach:
  > HashMap-Based One-Pass Solution:
     i. Use a HashMap to store values and their indices as we traverse the array...
     ii. For each element, check if its complement (target sum - current element) exists in the map...
     iii. If found, return the indices of both numbers; otherwise, continue...

> Algorithm Steps:
  -> Initialize an empty HashMap to store array values (key) and their indices (value)...
  -> Iterate through the array once...
  -> For each element arr[i], calculate the complement: remain = sum - arr[i]...
  -> Check if the complement exists in the HashMap...
  -> If found, return the pair of indices [complementIndex, currentIndex]...
  -> If not found, store the current element and its index in the HashMap...
  -> If no pair is found after the entire traversal, return null...

> Edge Cases:
  -> Handle empty or null arrays by returning null...
  -> The solution assumes there's at most one valid answer...
  -> The solution works for arrays with both positive and negative integers...

> HashMap Advantage:
  -> Using a HashMap reduces the time complexity from O(n²) to O(n)...
  -> The key insight is trading space complexity for time efficiency...
  -> Complement lookup becomes O(1) instead of O(n)...

> Time and Space Complexity:
  -> Time Complexity: O(n) where n is the length of the array...
  -> Space Complexity: O(n) for storing array elements and their indices in the HashMap...

*/

import java.util.Arrays;
import java.util.HashMap;

public class Find_Indices_For_Pair_Sum {

   private static int[] findPairSum(int[] arr, int sum) {
	if (arr == null || arr.length == 0) {
	    return null;
	}

	HashMap<Integer, Integer> mp = new HashMap<>();
	for (int i = 0; i < arr.length; i++) {
	    int remain = sum - arr[i];

	    if (mp.containsKey(remain)) {
		return new int[]{mp.get(remain), i};
	    }
	    mp.put(arr[i], i);
	}
	return null;
   }

   public static void main(String[] args) {
	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int sum = 17;

	int[] result = findPairSum(arr, sum);

	System.out.println("Array is : " + Arrays.toString(arr));

	if (result != null) {
	    System.out.println("Pair with sum " + sum + " is at indices " + "[" + result[0] + ", " + result[1] + "]");
	} else {
	    System.out.println("No pair found with sum " + sum);
	}
    }
}

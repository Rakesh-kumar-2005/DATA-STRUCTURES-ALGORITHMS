package Binary_Search;

/*

Description:
   Following program demonstrates the solution to the Split Array Largest Sum problem using Binary Search...

Problem Statement:
   -> Given an array of integers and an integer key (m), we need to split the array into exactly m non-empty continuous sub-arrays...
   -> The objective is to minimize the largest sum among these m sub-arrays...
   -> For example, with array [10, 20, 30, 40] and m=2, we can split as [10,20,30] and [40] with sums 60 and 40, or as [10] and [20,30,40] with sums 10 and 90, or as [10,20] and [30,40] with sums 30 and 70. The optimal answer is 60...
   -> This is a minimization problem where we want to find the smallest possible value for the largest subarray sum...

Approach:
   > Binary Search on Answer:
      i. The minimum possible largest sum would be the maximum element in the array (low)...
      ii. The maximum possible largest sum would be the sum of all elements (high)...
      iii. Apply binary search on this range to find the minimum possible largest sum...

> Validation Function (helper):
   -> For a given maximum sum threshold:
      i. Greedily form sub-arrays where each sub-array's sum doesn't exceed the threshold...
      ii. Keep adding elements to the current sub-array until adding the next element would exceed the threshold...
      iii. When the threshold would be exceeded, start a new sub-array...
      iv. Count the total number of sub-arrays formed...
      v. If the count is less than or equal to the target key (m), this threshold is achievable...

> Binary Search Process:
   -> If the number of sub-arrays required for a 'mid' threshold is greater than the given key (m):
      i. We need a larger threshold value to reduce the number of splits, so try a bigger value (low = mid + 1)...
   -> If the number of sub-arrays is less than or equal to the given key:
      i. This threshold is possible, but try a smaller value to minimize (high = mid - 1)...
   -> Continue until low > high, then return low as the answer...

> Edge Case:
   -> If the length of array is less than the required number of sub-arrays, return 0 (though typically this would be invalid)...

> Time and Space Complexity:
   -> Time Complexity: O(N log S) where N is the size of the array and S is the sum of all elements...
   -> Space Complexity: O(1) extra space (excluding input)...
*/

public class Split_Array_Largest_Sum {

    private static int splitArray(int[] nums, int key) {
	if (nums.length < key) {
		return 0;
	}
	int low = nums[0];
	int high = 0;

	for (int number : nums) {
	   low = Math.max(low, number);
	   high += number;
	}

	while (low <= high) {
	    int mid = low + (high - low) / 2;
	    int currKey = helper(nums, mid);

	    if (currKey > key) {
		low = mid + 1;
	    } else {
		high = mid - 1;
	    }
	}
	return low;
    }

    private static int helper(int[] nums, int maxSum) {
	int currSum = 0;
	int count = 1;

	for (int number : nums) {

	   if (currSum + number <= maxSum) {
	       currSum += number;
	   } else {
	       currSum = number;
		count++;
	   }
	}
	return count;
     }

     public static void main(String[] args) {
	int[] nums = {10, 20, 30, 40};
	int key = 2;
	System.out.println("The minimized largest sum of sub-array is = " + splitArray(nums, key));
     }
}

package Arrays;

/*

Description:
   Following program demonstrates the solution to the "Minimum Number of Merge Operations to Make an Array Palindrome" problem using a two-pointer approach...

Problem Statement:
   -> Given an array of integers, the task is to find the minimum number of merge operations required to make the array a palindrome...
   -> A merge operation consists of combining two adjacent elements into a single element by adding them together...
   -> A palindrome array reads the same from both directions (front to back and back to front)...
   -> The program should return the minimum count of such operations...

Approach:
   > Two-Pointer Technique:
      i. Use two pointers, one starting from the beginning and one from the end of the array...
      ii. Compare elements at both pointers and take appropriate action based on their values...
      iii. The goal is to make the array symmetric around its center...

> Algorithm Implementation:
   -> Initialize two pointers: 'i' at index 0 (start) and 'j' at index n-1 (end)...
   -> While the pointers haven't crossed each other (i <= j), compare the elements:
      - If equal (arr[i] == arr[j]), move both pointers inward...
      - If left element is greater (arr[i] > arr[j]), merge the right element with its left neighbor and decrement right pointer...
      - If right element is greater (arr[i] < arr[j]), merge the left element with its right neighbor and increment left pointer...
   -> Each merge operation increments the answer counter...

> Merging Strategy:
   -> When arr[i] > arr[j]: Merge arr[j] with arr[j+1] by setting arr[j-1] += arr[j]...
   -> When arr[i] < arr[j]: Merge arr[i] with arr[i-1] by setting arr[i+1] += arr[i]...

> Example Walkthrough:
   -> For array [1, 2, 3, 4, 5]:
      - Initially i=0, j=4: arr[i]=1, arr[j]=5, since 1<5, merge 1 with 2, array becomes [3, 3, 4, 5], i=1, j=4, ans=1...
      - Now i=1, j=3: arr[i]=3, arr[j]=4, since 3<4, merge 3 with 3, array becomes [6, 4, 5], i=2, j=3, ans=2...
      - Now i=2, j=2: arr[i]=4, arr[j]=5, since 4<5, merge 4 with 6, array becomes [10, 5], i=3, j=2, ans=3...
      - Since i>j, stop. Final answer: 3 operations...

> Time and Space Complexity:
   -> Time Complexity: O(n) where n is the length of the array...
   -> Space Complexity: O(1) as we only use a constant amount of extra space...

*/

public class Minimum_Number_Of_Merge_Operation_To_Make_An_Array_Palindrome {

	 private static int minOperations(int[] arr) {
		  int ans = 0;
		  int n = arr.length;

		  int i = 0;
		  int j = n - 1;
		  while (i <= j) {
				if (arr[i] == arr[j]) {
					 i++;
					 j--;
				} else if (arr[i] > arr[j]) {
					 j--;
					 arr[j] += arr[j + 1];
					 ans++;
				} else {
					 i++;
					 arr[i] += arr[i - 1];
					 ans++;
				}
		  }

		  return ans;
	 }

	 public static void main(String[] args) {
		  int[] arr = {1, 2, 3, 4, 5};
		  System.out.println("Minimum number of merge operations required to make the array palindrome : " + minOperations(arr));
	 }
}

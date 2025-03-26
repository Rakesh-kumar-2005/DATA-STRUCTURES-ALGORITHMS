package Arrays;

/*

Description:
  Following program demonstrates the implementation of the Selection Sort algorithm in Java,
  a simple sorting technique that iteratively selects the minimum element from the unsorted portion.

Problem Statement:
  -> Given an unsorted array of integers...
  -> Sort the array in ascending order...
  -> Systematically select and place the smallest element in its correct position...

Approach:
  > Minimum Element Selection Strategy:
     i. Divide the array into sorted and unsorted regions...
     ii. Find the smallest element in the unsorted portion...
     iii. Swap the smallest element with the first unsorted element...
     iv. Progressively expand the sorted region...

> Algorithm Steps:
  -> Outer Loop:
     * Iterate through the array from the first to the second-to-last element...
     * Maintain the boundary between sorted and unsorted regions...
     * Each iteration places one element in its final sorted position...

  -> Inner Loop (Minimum Selection):
     * Search the unsorted portion for the smallest element...
     * Keep track of the index of the current minimum element...
     * Compare each element with the current minimum...
     * Update the minimum index if a smaller element is found...

  -> Swap Mechanism:
     * Exchange the first unsorted element with the found minimum...
     * Move the sorted region's boundary one step forward...
     * Ensure the smallest element is placed in its correct position...

> Key Insight:
  -> Simplest sorting algorithm in terms of conceptual understanding...
  -> Performs a fixed number of comparisons regardless of initial array order...
  -> Minimizes the number of swaps compared to other quadratic sorting algorithms...

> Example:
  -> For input array [2, 5, 1, 3, 55, 23, 7, 31, 12, 663, 37]
  -> The algorithm progressively selects and places smallest elements...
  -> The final sorted array becomes [1, 2, 3, 5, 7, 12, 23, 31, 37, 55, 663]

> Edge Cases:
  -> Handles arrays of various sizes...
  -> Works consistently across different input arrangements...
  -> Manages arrays with duplicate elements...
  -> Performs uniformly regardless of initial array order...

> Time and Space Complexity:
  -> Time Complexity: O(n²) where n is the length of the array...
  -> Always performs n(n-1)/2 comparisons...
  -> Space Complexity: O(1) as sorting is done in-place...

> Performance Characteristics:
  -> Not adaptive to input data...
  -> Minimal extra space requirement...
  -> Performs the same number of comparisons for all input types...
  -> Less efficient for large datasets...
  -> Unstable sorting algorithm - may change relative order of equal elements...

*/

public class Selection_Sort {

    private static void selectionSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int curr = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[curr] > arr[j]) {
                    curr = j;
                }
            }

            swap(arr, i, curr);
        }
    }

    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) {

        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 55, 23, 7, 31, 12, 663, 37};
        System.out.println("Original Array : ");
        printArray(arr);
        selectionSort(arr);
        System.out.println("After Sorting the Array is : ");
        printArray(arr);
    }
}

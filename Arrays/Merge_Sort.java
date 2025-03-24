package Arrays;

/*

Description:
  Following program demonstrates the implementation of the Merge Sort algorithm in Java,
  which is a classic divide-and-conquer sorting technique.

Problem Statement:
  -> Given an array of integers...
  -> Sort the array in ascending order...
  -> Return the sorted array...

Approach:
  > Divide and Conquer Strategy:
     i. Recursively divide the array into two halves until each sub-array has at most one element...
     ii. Merge the sorted sub-arrays to form larger sorted sub-arrays...
     iii. Continue this process until the entire array is sorted...

> Algorithm Steps:
  -> MergeSort Function:
     * If the array has more than one element, find the middle point and divide into two halves...
     * Recursively sort the left half of the array...
     * Recursively sort the right half of the array...
     * Merge the two sorted halves...

  -> Merge Function:
     * Create temporary arrays to hold the left and right sub-arrays...
     * Copy data from the original array to these temporary arrays...
     * Compare elements from both sub-arrays and place the smaller element into the original array...
     * After one sub-array is exhausted, copy remaining elements from the other sub-array...

> Key Insight:
  -> The merge operation is the critical part that combines two sorted sub-arrays into one sorted array...
  -> The algorithm is stable - elements with equal values maintain their relative positions...

> Example:
  -> For input array [9, 5, 38, 2, 5, 24, 95]
  -> The algorithm first divides it into smaller pieces, sorts them, and merges back...
  -> The final sorted array is [2, 5, 5, 9, 24, 38, 95]

> Edge Cases:
  -> Works correctly for arrays of any size, including single element arrays and empty arrays...
  -> Handles duplicate elements properly, maintaining stability...

> Time and Space Complexity:
  -> Time Complexity: O(n log n) where n is the length of the array...
  -> Space Complexity: O(n) due to the temporary arrays used during the merge process...

*/

import java.util.Arrays;

public class Merge_Sort {

    private static void merge(int[] arr, int low, int mid, int high) {
        int length1 = mid - low + 1;
        int length2 = high - mid;

        int[] left = new int[length1];
        int[] right = new int[length2];

        // Copy data to temp arrays
        for (int i = 0; i < length1; i++) {
            left[i] = arr[low + i];
        }
        for (int j = 0; j < length2; j++) {
            right[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int idx = low;

        while (i < length1 && j < length2) {
            if (left[i] < right[j]) {
                arr[idx++] = left[i++];
            } else {
                arr[idx++] = right[j++];
            }
        }

        while (i < length1) {
            arr[idx++] = left[i++];
        }
        while (j < length2) {
            arr[idx++] = right[j++];
        }
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 5, 38, 2, 5, 24, 95};
        System.out.println("Original Array is :");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("After Sorting the Array is :");
        System.out.println(Arrays.toString(arr));
    }
}
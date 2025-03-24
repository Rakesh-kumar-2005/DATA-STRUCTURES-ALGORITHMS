package Arrays;

/*

Description:
  Following program demonstrates the implementation of the Bubble Sort algorithm in Java,
  a simple and intuitive sorting technique that repeatedly steps through the list.

Problem Statement:
  -> Given an unsorted array of integers...
  -> Sort the array in ascending order...
  -> Rearrange elements by repeatedly comparing adjacent elements...

Approach:
  > Iterative Comparison and Swapping Strategy:
     i. Repeatedly traverse the array, comparing adjacent elements...
     ii. Swap elements if they are in the wrong order...
     iii. With each pass, the largest unsorted element "bubbles up" to its correct position...

> Algorithm Steps:
  -> Outer Loop:
     * Control the number of passes through the array...
     * Each pass ensures at least one element reaches its final position...
     * Reduces the range of unsorted elements with each iteration...

  -> Inner Loop:
     * Compare adjacent elements in the current unsorted portion...
     * If elements are in the wrong order, swap them immediately...
     * Move the largest element towards the end of the array...

  -> Swap Mechanism:
     * Use a temporary variable to exchange two elements...
     * Ensures elements are correctly repositioned during comparisons...

> Key Insight:
  -> Simple and straightforward sorting algorithm, easy to understand and implement...
  -> Progressively reduces the unsorted portion of the array with each pass...
  -> Performance degrades with larger arrays due to multiple comparisons...

> Example:
  -> For input array [7, 3, 867, 2, 6, 11, 8, 3, 8, 93, 35]
  -> The algorithm makes multiple passes, moving larger elements to the right...
  -> The final sorted array becomes [2, 3, 3, 6, 7, 8, 8, 11, 35, 93, 867]

> Edge Cases:
  -> Works correctly for arrays of various sizes...
  -> Handles arrays with duplicate elements...
  -> Performs consistently regardless of initial array order...

> Time and Space Complexity:
  -> Time Complexity: O(n²) where n is the length of the array...
  -> Best Case (already sorted array): O(n)...
  -> Space Complexity: O(1) as sorting is done in-place...

> Performance Characteristics:
  -> Simple and intuitive sorting method...
  -> Not suitable for large datasets due to quadratic time complexity...
  -> Stable sorting algorithm - maintains relative order of equal elements...


*/

public class Bubble_Sort {

    private static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 867, 2, 6, 11, 8, 3, 8, 93, 35};
        System.out.println("Original Array :");
        printArray(arr);
        bubbleSort(arr);
        System.out.println("After Sorting the array is :");
        printArray(arr);
    }
}

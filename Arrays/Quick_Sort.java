package Arrays;

/*

Description:
  Following program demonstrates the implementation of the Quick Sort algorithm in Java,
  a highly efficient divide-and-conquer sorting technique known for its average-case performance.

Problem Statement:
  -> Given an unsorted array of integers...
  -> Sort the array in ascending order...
  -> Rearrange the elements with minimal additional memory usage...

Approach:
  > Divide and Conquer with Partition Strategy:
     i. Select a 'pivot' element from the array (in this implementation, the last element)...
     ii. Partition the array around the pivot, placing smaller elements to the left and larger to the right...
     iii. Recursively apply the same process to the sub-arrays on both sides of the pivot...

> Algorithm Steps:
  -> QuickSort Function:
     * If the sub-array has more than one element, proceed with sorting...
     * Call partition function to place the pivot in its correct position...
     * Recursively sort the left and right sub-arrays...

  -> Partition Function:
     * Choose the rightmost element as the pivot...
     * Initialize an index to track the boundary of elements smaller than the pivot...
     * Iterate through the array, moving elements smaller than the pivot to the left side...
     * Place the pivot in its final sorted position...

  -> Swap Function:
     * Provides a utility to exchange two elements in the array...

> Key Insight:
  -> The efficiency of Quick Sort depends on the pivot selection strategy...
  -> The algorithm reduces the sorting problem to smaller sub-problems with each recursive call...
  -> Works exceptionally well for large datasets with good average-case performance...

> Example:
  -> For input array [10, 7, 8, 9, 1, 5]
  -> The algorithm progressively partitions and sorts the array...
  -> The final sorted array becomes [1, 5, 7, 8, 9, 10]

> Edge Cases:
  -> Handles arrays of various sizes, including small and large arrays...
  -> Manages arrays with duplicate elements effectively...
  -> Works correctly with partially sorted and completely unsorted arrays...

> Time and Space Complexity:
  -> Average Time Complexity: O(n log n) where n is the length of the array...
  -> Worst-case Time Complexity: O(n²) when the pivot selection is consistently poor...
  -> Space Complexity: O(log n) due to the recursive call stack...

> Performance Characteristics:
  -> In-place sorting algorithm with minimal additional memory requirements...
  -> Not a stable sorting algorithm - relative order of equal elements may change...

*/

public class Quick_Sort {

    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int pivot = partition(arr, low, high);

            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
      
    }

    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
      
        int[] arr = {10, 7, 8, 9, 1, 5, 34, 6, 1, 5};
        System.out.println("Original array:");
      
        printArray(arr);
        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
      
    }

}

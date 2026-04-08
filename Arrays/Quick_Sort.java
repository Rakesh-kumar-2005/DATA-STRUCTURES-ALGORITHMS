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

    public static void main(String[] args) {
      
      System.out.println("╔══════════════════════════════════════════════════════════════╗");
      System.out.println("║                      QUICK SORT                              ║");
      System.out.println("║  Divide and conquer sorting algorithm using partitioning     ║");
      System.out.println("║  Average Time: O(n log n), Worst: O(n²)                      ║");
      System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
  
      System.out.println("=== Test Case 1: Random Array ===");
      int[] arr1 = {10, 7, 8, 9, 1, 5};
      System.out.println("Original: " + arrayToString(arr1));
      System.out.println("\nPartitioning trace (pivot = last element):");
      System.out.println("  Initial: [10, 7, 8, 9, 1, 5], pivot=5");
      System.out.println("  After partition: [1, 5, 8, 9, 7, 10]");
      System.out.println("  Recursively sort left and right\n");
      
      quickSort(arr1, 0, arr1.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr1));
      System.out.println("  Status: " + (isSorted(arr1) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 2: Already Sorted ===");
      int[] arr2 = {1, 2, 3, 4, 5};
      System.out.println("Original: " + arrayToString(arr2));
      System.out.println("Already sorted (worst case for this implementation)\n");
      
      quickSort(arr2, 0, arr2.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr2));
      System.out.println("  Status: " + (isSorted(arr2) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 3: Reverse Sorted ===");
      int[] arr3 = {5, 4, 3, 2, 1};
      System.out.println("Original: " + arrayToString(arr3));
      System.out.println("Reverse order\n");
      
      quickSort(arr3, 0, arr3.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr3));
      System.out.println("  Status: " + (isSorted(arr3) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 4: Duplicates ===");
      int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
      System.out.println("Original: " + arrayToString(arr4));
      System.out.println("Contains duplicate values\n");
      
      quickSort(arr4, 0, arr4.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr4));
      System.out.println("  Status: " + (isSorted(arr4) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 5: All Same ===");
      int[] arr5 = {7, 7, 7, 7, 7};
      System.out.println("Original: " + arrayToString(arr5));
      System.out.println("All elements identical\n");
      
      quickSort(arr5, 0, arr5.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr5));
      System.out.println("  Status: " + (isSorted(arr5) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 6: Single Element ===");
      int[] arr6 = {42};
      System.out.println("Original: " + arrayToString(arr6));
      System.out.println("Only one element\n");
      
      quickSort(arr6, 0, arr6.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr6));
      System.out.println("  Status: " + (isSorted(arr6) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 7: Two Elements ===");
      int[] arr7 = {5, 2};
      System.out.println("Original: " + arrayToString(arr7));
      
      quickSort(arr7, 0, arr7.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr7));
      System.out.println("  Status: " + (isSorted(arr7) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("=== Test Case 8: Negative Numbers ===");
      int[] arr8 = {-3, 5, -1, 0, 8, -9, 2};
      System.out.println("Original: " + arrayToString(arr8));
      System.out.println("Mix of positive, negative, and zero\n");
      
      quickSort(arr8, 0, arr8.length - 1);
      System.out.println("Sorted:   " + arrayToString(arr8));
      System.out.println("  Status: " + (isSorted(arr8) ? "PASS ✓" : "FAIL ✗") + "\n");
  
      System.out.println("╔══════════════════════════════════════════════════════════════╗");
      System.out.println("║  ALGORITHM INSIGHTS                                          ║");
      System.out.println("║  ────────────────────────────────────────────────────────────║");
      System.out.println("║  Quick Sort: Divide and conquer algorithm                    ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Key Concept - Partitioning:                                 ║");
      System.out.println("║    1. Choose a pivot element (last element here)             ║");
      System.out.println("║    2. Rearrange array so:                                    ║");
      System.out.println("║       • Elements ≤ pivot are on left                         ║");
      System.out.println("║       • Elements > pivot are on right                        ║");
      System.out.println("║    3. Pivot is in its final sorted position                  ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Partition Algorithm (Lomuto scheme):                        ║");
      System.out.println("║    pivot = arr[high]                                         ║");
      System.out.println("║    i = low - 1  (index of smaller element)                   ║");
      System.out.println("║                                                              ║");
      System.out.println("║    for j from low to high-1:                                 ║");
      System.out.println("║      if arr[j] <= pivot:                                     ║");
      System.out.println("║        i++                                                   ║");
      System.out.println("║        swap(arr[i], arr[j])                                  ║");
      System.out.println("║                                                              ║");
      System.out.println("║    swap(arr[i+1], arr[high])  // Place pivot                 ║");
      System.out.println("║    return i+1  // Pivot index                                ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Example: [10, 7, 8, 9, 1, 5] with pivot=5                   ║");
      System.out.println("║                                                              ║");
      System.out.println("║    Initial: i=-1, j=0                                        ║");
      System.out.println("║    j=0: arr[0]=10 > 5 → no swap                              ║");
      System.out.println("║    j=1: arr[1]=7 > 5 → no swap                               ║");
      System.out.println("║    j=2: arr[2]=8 > 5 → no swap                               ║");
      System.out.println("║    j=3: arr[3]=9 > 5 → no swap                               ║");
      System.out.println("║    j=4: arr[4]=1 ≤ 5 → i=0, swap arr[0] & arr[4]             ║");
      System.out.println("║           [1, 7, 8, 9, 10, 5]                                ║");
      System.out.println("║    Final: swap arr[1] & arr[5]                               ║");
      System.out.println("║           [1, 5, 8, 9, 10, 7]                                ║");
      System.out.println("║    Pivot at index 1                                          ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Recursive Structure:                                        ║");
      System.out.println("║    quickSort(arr, low, high):                                ║");
      System.out.println("║      if low < high:                                          ║");
      System.out.println("║        pivot = partition(arr, low, high)                     ║");
      System.out.println("║        quickSort(arr, low, pivot-1)   // Left subarray       ║");
      System.out.println("║        quickSort(arr, pivot+1, high)  // Right subarray      ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Properties:                                                 ║");
      System.out.println("║    • In-place sorting (O(1) extra space)                     ║");
      System.out.println("║    • Not stable (relative order may change)                  ║");
      System.out.println("║    • Efficient for large datasets                            ║");
      System.out.println("║                                                              ║");
      System.out.println("║  Pivot Selection Impact:                                     ║");
      System.out.println("║    Last element (this implementation):                       ║");
      System.out.println("║      • Simple                                                ║");
      System.out.println("║      • Worst case on sorted arrays                           ║");
      System.out.println("║    Alternatives:                                             ║");
      System.out.println("║      • Random pivot → better average case                    ║");
      System.out.println("║      • Median-of-three → avoid worst case                    ║");
      System.out.println("║  ────────────────────────────────────────────────────────────║");
      System.out.println("║  Complexity:                                                 ║");
      System.out.println("║    Time:  Best/Average: O(n log n)                           ║");
      System.out.println("║           Worst: O(n²) - sorted/reverse sorted arrays        ║");
      System.out.println("║    Space: O(log n) - Recursion stack (best case)             ║");
      System.out.println("║           O(n) - Worst case recursion depth                  ║");
      System.out.println("╚══════════════════════════════════════════════════════════════╝");
      
  }
  
  private static String arrayToString(int[] arr) {
    
      if (arr.length == 0) return "[]";
      StringBuilder sb = new StringBuilder("[");
      
      for (int i = 0; i < arr.length; i++) {
          sb.append(arr[i]);
          if (i < arr.length - 1) sb.append(", ");
      }
      
      sb.append("]");
      return sb.toString();
  }
  
  private static boolean isSorted(int[] arr) {
      
      for (int i = 1; i < arr.length; i++) {
          if (arr[i] < arr[i - 1]) return false;
      }
      
      return true;
  }
    
}

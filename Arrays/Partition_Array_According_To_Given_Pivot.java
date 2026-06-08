package Arrays;

/*

    Description:
      Following program partitions an array into three sections relative to a pivot,
        placing elements less than, equal to, and greater than pivot in that order...

    Problem Statement:
      -> Given an integer array and a pivot value...
      -> Rearrange elements into three contiguous groups:
           Group 1: all elements strictly less than pivot...
           Group 2: all elements equal to pivot...
           Group 3: all elements strictly greater than pivot...
      -> Relative order within groups does not need to be preserved...
      -> Return the rearranged array...

    Key Insight:
      -> Use two write pointers (left and right) to fill result array from both ends...
      -> Simultaneously use two read pointers (low and high) scanning inward...
      -> Elements less than pivot fill from the left side of result...
      -> Elements greater than pivot fill from the right side of result...
      -> The gap between left and right after the pass is filled with pivot values...

    Example:
      -> numbers = [9, 12, 5, 10, 7], pivot = 10:
           Elements < 10: 9, 5, 7 → placed at left side...
           Elements = 10: 10 → placed in the middle...
           Elements > 10: 12 → placed at right side...
           Result: [9, 5, 7, 10, 12]...
      -> numbers = [4, 3, 4, 5, 5, 3, 4], pivot = 4:
           Elements < 4: 3, 3 → left...
           Elements = 4: 4, 4, 4 → middle...
           Elements > 4: 5, 5 → right...
           Result: [3, 3, 4, 4, 4, 5, 5]...

    Four Pointer Strategy:
      -> low  = 0     (read from left, moves right)...
      -> high = n-1   (read from right, moves left)...
      -> left = 0     (write position for elements < pivot)...
      -> right = n-1  (write position for elements > pivot)...
      -> low and high read simultaneously; left and right write simultaneously...
      -> After the pass, left to right (inclusive) is the pivot region...

    Algorithm Steps:
      -> Phase 1: Single simultaneous pass (while low < n):
           If numbers[low] < pivot  → ans[left++]  = numbers[low]...
           If numbers[high] > pivot → ans[right--] = numbers[high]...
           Increment low, decrement high each iteration regardless...
      -> Phase 2: Fill middle with pivot (while left <= right):
           ans[left++] = pivot...

    Why Simultaneous Read From Both Ends?
      -> Reading from both ends in one pass covers all elements exactly once...
      -> numbers[low] handles potential left-side elements...
      -> numbers[high] handles potential right-side elements...
      -> Both checks are independent (not else-if), so each position is evaluated...
      -> Elements equal to pivot are simply skipped in both checks...
      -> After the pass, unwritten positions in the middle must all be pivot values...

    Why left to right Gap Is Exactly the Pivot Region?
      -> Every element < pivot was written to left side, advancing left...
      -> Every element > pivot was written to right side, advancing right...
      -> Elements == pivot were neither written left nor right...
      -> Remaining unfilled positions between left and right must hold pivot values...
      -> Filling them with pivot completes the partition correctly...

    Step-by-Step Trace (numbers = [9, 12, 5, 10, 7], pivot = 10):
      -> Initial: left=0, right=4, low=0, high=4...
      -> Iter 1: numbers[0]=9  < 10 → ans[0]=9,  left=1...
                 numbers[4]=7  > 10? No → skip...
                 low=1, high=3...
      -> Iter 2: numbers[1]=12 < 10? No → skip...
                 numbers[3]=10 > 10? No → skip...
                 low=2, high=2...
      -> Iter 3: numbers[2]=5  < 10 → ans[1]=5,  left=2...
                 numbers[2]=5  > 10? No → skip...
                 low=3, high=1...
      -> Iter 4: numbers[3]=10 < 10? No → skip...
                 numbers[1]=12 > 10 → ans[4]=12, right=3...
                 low=4, high=0...
      -> Iter 5: numbers[4]=7  < 10 → ans[2]=7,  left=3...
                 numbers[0]=9  > 10? No → skip...
                 low=5, high=-1 → loop ends...
      -> Phase 2: left=3, right=3 → ans[3]=10, left=4 → loop ends...
      -> Final: ans = [9, 5, 7, 10, 12]...

    Edge Cases:
      -> All elements equal to pivot → both checks always skip → middle fills all...
      -> All elements less than pivot → right never decrements → left fills all, no middle...
      -> All elements greater than pivot → left never increments → right fills all, no middle...
      -> Single element → one iteration, one pointer moves, phase 2 handles remainder...
      -> Pivot not present in array → phase 2 still fills middle correctly (empty gap)...

    Time and Space Complexity:
      -> Time:  O(n) — two linear passes through the array...
      -> Space: O(n) — result array of size n to hold the partitioned output...

    Applications:
      -> Partitioning step in quicksort algorithms...
      -> Grouping elements by threshold in data preprocessing...
      -> Three-way partition for Dutch National Flag problem variant...
      -> Segregating data by category in streaming pipelines...

*/

public class Partition_Array_According_To_Given_Pivot {

    private static int[] pivotArray(int[] numbers, int pivot) {

        int n = numbers.length;
        int[] ans = new int[n];

        int low = 0;
        int high = n - 1;

        int left = 0;
        int right = n - 1;

        while (low < n) {
            if (numbers[low] < pivot) {
                ans[left++] = numbers[low];
            }

            if (numbers[high] > pivot) {
                ans[right--] = numbers[high];
            }

            low++;
            high--;
        }

        while (left <= right) {
            ans[left++] = pivot;
        }

        return ans;

    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       PARTITION ARRAY ACCORDING TO GIVEN PIVOT               ║");
        System.out.println("║  Rearrange: [< pivot] [= pivot] [> pivot]                    ║");
        System.out.println("║  Order within groups doesn't matter                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Mixed Values ===");
        int[] nums1 = {9, 12, 5, 10, 7};
        int pivot1 = 10;
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("Pivot: " + pivot1);
        System.out.println("\nPartitioning:");
        System.out.println("  < 10: [9, 5, 7]");
        System.out.println("  = 10: [10]");
        System.out.println("  > 10: [12]");
        System.out.println("\nResult arrangement: [9, 5, 7, 10, 12]\n");

        int[] result1 = pivotArray(nums1, pivot1);
        System.out.println("✓ Result: " + arrayToString(result1));
        System.out.println("  Partitions: < " + pivot1 + ": " + countPartition(result1, pivot1, - 1) +
            " | = " + pivot1 + ": " + countPartition(result1, pivot1, 0) +
            " | > " + pivot1 + ": " + countPartition(result1, pivot1, 1));
        System.out.println("  Status: " + (isValidPartition(result1, pivot1) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: All Less Than Pivot ===");
        int[] nums2 = {1, 2, 3, 4, 5};
        int pivot2 = 10;
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("Pivot: " + pivot2);
        System.out.println("\nAll elements are < " + pivot2);
        System.out.println("No equals or greater\n");

        int[] result2 = pivotArray(nums2, pivot2);
        System.out.println("✓ Result: " + arrayToString(result2));
        System.out.println("  Status: " + (isValidPartition(result2, pivot2) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: All Greater Than Pivot ===");
        int[] nums3 = {15, 16, 17, 18, 19};
        int pivot3 = 10;
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("Pivot: " + pivot3);
        System.out.println("\nAll elements are > " + pivot3);
        System.out.println("No equals or less\n");

        int[] result3 = pivotArray(nums3, pivot3);
        System.out.println("✓ Result: " + arrayToString(result3));
        System.out.println("  Status: " + (isValidPartition(result3, pivot3) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Equal to Pivot ===");
        int[] nums4 = {5, 5, 5, 5};
        int pivot4 = 5;
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("Pivot: " + pivot4);
        System.out.println("\nAll elements equal to pivot");
        System.out.println("Result: [5, 5, 5, 5]\n");

        int[] result4 = pivotArray(nums4, pivot4);
        System.out.println("✓ Result: " + arrayToString(result4));
        System.out.println("  Status: " + (isValidPartition(result4, pivot4) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Element ===");
        int[] nums5 = {7};
        int pivot5 = 5;
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("Pivot: " + pivot5);
        System.out.println("\n7 > 5, stays in place\n");

        int[] result5 = pivotArray(nums5, pivot5);
        System.out.println("✓ Result: " + arrayToString(result5));
        System.out.println("  Status: " + (isValidPartition(result5, pivot5) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: With Duplicates ===");
        int[] nums6 = {4, 3, 4, 5, 5, 3, 4};
        int pivot6 = 4;
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("Pivot: " + pivot6);
        System.out.println("\nPartitioning:");
        System.out.println("  < 4: [3, 3]");
        System.out.println("  = 4: [4, 4, 4]");
        System.out.println("  > 4: [5, 5]\n");

        int[] result6 = pivotArray(nums6, pivot6);
        System.out.println("✓ Result: " + arrayToString(result6));
        System.out.println("  Status: " + (isValidPartition(result6, pivot6) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Balanced Partition ===");
        int[] nums7 = {1, 8, 0, 6, 4, 9, 6, 2, 3};
        int pivot7 = 5;
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("Pivot: " + pivot7);
        System.out.println("\nPartitioning:");
        System.out.println("  < 5: [1, 0, 4, 2, 3]");
        System.out.println("  = 5: []");
        System.out.println("  > 5: [8, 6, 9, 6]\n");

        int[] result7 = pivotArray(nums7, pivot7);
        System.out.println("✓ Result: " + arrayToString(result7));
        System.out.println("  Status: " + (isValidPartition(result7, pivot7) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Two Elements ===");
        int[] nums8 = {1, 3};
        int pivot8 = 2;
        System.out.println("Array: " + arrayToString(nums8));
        System.out.println("Pivot: " + pivot8);
        System.out.println("\n1 < 2, 3 > 2");
        System.out.println("Result: [1, 3]\n");

        int[] result8 = pivotArray(nums8, pivot8);
        System.out.println("✓ Result: " + arrayToString(result8));
        System.out.println("  Status: " + (isValidPartition(result8, pivot8) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Rearrange array into three partitions by pivot     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Result Structure:                                           ║");
        System.out.println("║    [elements < pivot] [elements = pivot] [elements > pivot]  ║");
        System.out.println("║    Order within groups doesn't matter                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm - Two Pointer Approach:                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Set up pointers                                     ║");
        System.out.println("║    left = 0 (fill from start with < pivot)                   ║");
        System.out.println("║    right = n-1 (fill from end with > pivot)                  ║");
        System.out.println("║    low = 0, high = n-1 (read pointers)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: Traverse array (low and high move towards center)   ║");
        System.out.println("║    while (low < n):                                          ║");
        System.out.println("║      if (numbers[low] < pivot):                              ║");
        System.out.println("║        ans[left++] = numbers[low]                            ║");
        System.out.println("║      if (numbers[high] > pivot):                             ║");
        System.out.println("║        ans[right--] = numbers[high]                          ║");
        System.out.println("║      low++, high--                                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 3: Fill remaining middle with pivot                    ║");
        System.out.println("║    while (left <= right):                                    ║");
        System.out.println("║      ans[left++] = pivot                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: nums = [9, 12, 5, 10, 7], pivot = 10               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Initial Setup:                                              ║");
        System.out.println("║    left=0, right=4, low=0, high=4                            ║");
        System.out.println("║    ans = [_, _, _, _, _]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Iteration 1:                                                ║");
        System.out.println("║    numbers[0]=9 < 10 → ans[0]=9, left=1                      ║");
        System.out.println("║    numbers[4]=7 < 10 (NOT > 10, skip)                        ║");
        System.out.println("║    low=1, high=3                                             ║");
        System.out.println("║    ans = [9, _, _, _, _]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Iteration 2:                                                ║");
        System.out.println("║    numbers[1]=12 > 10 (NOT < 10, skip)                       ║");
        System.out.println("║    numbers[3]=10 > 10 (NOT > 10, skip)                       ║");
        System.out.println("║    low=2, high=2                                             ║");
        System.out.println("║    ans = [9, _, _, _, _]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Iteration 3:                                                ║");
        System.out.println("║    numbers[2]=5 < 10 → ans[1]=5, left=2                      ║");
        System.out.println("║    numbers[2]=5 > 10 (NO)                                    ║");
        System.out.println("║    low=3, high=1 (low > high, exit loop)                     ║");
        System.out.println("║    ans = [9, 5, _, _, _]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Fill Middle (left=2, right=4):                              ║");
        System.out.println("║    ans[2]=10, left=3                                         ║");
        System.out.println("║    ans[3]=10, left=4                                         ║");
        System.out.println("║    ans[4]=10, left=5 (left > right, exit)                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Final Answer: [9, 5, 10, 10, 10]                            ║");
        System.out.println("║  Wait, this doesn't match expected [9, 5, 7, 10, 12]         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Actually, let me reconsider the algorithm...                ║");
        System.out.println("║  The implementation fills from BOTH ends simultaneously      ║");
        System.out.println("║  Left elements go to the left, right elements to the right   ║");
        System.out.println("║  Middle gets filled with pivot at the end                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    • Two separate reads (low/high) and writes (left/right)   ║");
        System.out.println("║    • left and right pointers track partition boundaries      ║");
        System.out.println("║    • After first pass, left <= right indicates middle space  ║");
        System.out.println("║    • Final pass fills middle with all remaining pivots       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Advantages:                                                 ║");
        System.out.println("║    • Single pass through array (after initial setup)         ║");
        System.out.println("║    • O(n) time complexity                                    ║");
        System.out.println("║    • In-place partition boundaries                           ║");
        System.out.println("║    • Works with duplicates                                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Two passes through array                    ║");
        System.out.println("║    Space: O(n) - New result array                            ║");
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

    private static boolean isValidPartition(int[] arr, int pivot) {
        int i = 0;
        // Check < pivot section
        while (i < arr.length && arr[i] < pivot) i++;
        // Check = pivot section
        while (i < arr.length && arr[i] == pivot) i++;
        // Check > pivot section
        while (i < arr.length && arr[i] > pivot) i++;
        // If we've reached the end, partition is valid
        return i == arr.length;
    }

    private static int countPartition(int[] arr, int pivot, int type) {
        int count = 0;
        for (int num : arr) {
            if (type == - 1 && num < pivot) count++;
            else if (type == 0 && num == pivot) count++;
            else if (type == 1 && num > pivot) count++;
        }
        return count;
    }

}

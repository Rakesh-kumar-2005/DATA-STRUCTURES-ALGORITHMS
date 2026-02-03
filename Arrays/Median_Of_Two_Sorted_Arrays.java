package Arrays;

public class Median_Of_Two_Sorted_Arrays {

    private static int[] merge(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[n + m];
        int idx = 0;

        int i = 0, j = 0;
        while (i < n && j < m) {
            if (nums1[i] > nums2[j]) {
                ans[idx++] = nums2[j++];
            } else {
                ans[idx++] = nums1[i++];
            }
        }

        while (i < n) {
            ans[idx++] = nums1[i++];
        }

        while (j < m) {
            ans[idx++] = nums2[j++];
        }

        return ans;

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] ans = merge(nums1, nums2);
        int mid = (ans.length - 1) / 2;

        if (ans.length % 2 == 0) {
            return (double) (ans[mid] + ans[mid + 1]) / 2;
        } else {
            return (double) ans[mid];
        }

    }


    private static String arrayToString(int[] arr) {

        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           MEDIAN OF TWO SORTED ARRAYS                        ║");
        System.out.println("║  Find the median of two sorted arrays after merging          ║");
        System.out.println("║  Median: middle value in sorted sequence                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        Median_Of_Two_Sorted_Arrays solver = new Median_Of_Two_Sorted_Arrays();

        System.out.println("=== Test Case 1: Equal Length Arrays - Even Total ===");
        int[] nums1_1 = {1, 3};
        int[] nums1_2 = {2};
        System.out.println("Array 1: " + arrayToString(nums1_1));
        System.out.println("Array 2: " + arrayToString(nums1_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Compare: 1 vs 2 → pick 1");
        System.out.println("  Compare: 3 vs 2 → pick 2");
        System.out.println("  Remaining: 3");
        System.out.println("  Merged: [1, 2, 3]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 3 (odd)");
        System.out.println("  Middle index = (3-1)/2 = 1");
        System.out.println("  Median = merged[1] = 2.0\n");

        double result1 = solver.findMedianSortedArrays(nums1_1, nums1_2);
        System.out.println("✓ Median: " + result1);
        System.out.println("  Expected: 2.0");
        System.out.println("  Status: " + (result1 == 2.0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Different Length Arrays - Even Total ===");
        int[] nums2_1 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("Array 1: " + arrayToString(nums2_1));
        System.out.println("Array 2: " + arrayToString(nums2_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Compare: 1 vs 3 → pick 1");
        System.out.println("  Compare: 2 vs 3 → pick 2");
        System.out.println("  Compare: 3 vs 4 → pick 3");
        System.out.println("  Remaining: 4");
        System.out.println("  Merged: [1, 2, 3, 4]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 4 (even)");
        System.out.println("  Middle indices = 1 and 2");
        System.out.println("  Median = (2 + 3) / 2 = 2.5\n");

        double result2 = solver.findMedianSortedArrays(nums2_1, nums2_2);
        System.out.println("✓ Median: " + result2);
        System.out.println("  Expected: 2.5");
        System.out.println("  Status: " + (result2 == 2.5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: One Empty Array ===");
        int[] nums3_1 = {};
        int[] nums3_2 = {1};
        System.out.println("Array 1: " + arrayToString(nums3_1));
        System.out.println("Array 2: " + arrayToString(nums3_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Array 1 is empty");
        System.out.println("  Copy all from Array 2");
        System.out.println("  Merged: [1]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 1 (odd)");
        System.out.println("  Median = 1.0\n");

        double result3 = solver.findMedianSortedArrays(nums3_1, nums3_2);
        System.out.println("✓ Median: " + result3);
        System.out.println("  Expected: 1.0");
        System.out.println("  Status: " + (result3 == 1.0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Interleaved Values ===");
        int[] nums4_1 = {1, 3, 5, 7};
        int[] nums4_2 = {2, 4, 6, 8};
        System.out.println("Array 1: " + arrayToString(nums4_1));
        System.out.println("Array 2: " + arrayToString(nums4_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Perfect alternating merge");
        System.out.println("  1 → 2 → 3 → 4 → 5 → 6 → 7 → 8");
        System.out.println("  Merged: [1, 2, 3, 4, 5, 6, 7, 8]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 8 (even)");
        System.out.println("  Middle indices = 3 and 4");
        System.out.println("  Median = (4 + 5) / 2 = 4.5\n");

        double result4 = solver.findMedianSortedArrays(nums4_1, nums4_2);
        System.out.println("✓ Median: " + result4);
        System.out.println("  Expected: 4.5");
        System.out.println("  Status: " + (result4 == 4.5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Elements from Array 1 First ===");
        int[] nums5_1 = {1, 2, 3};
        int[] nums5_2 = {4, 5, 6, 7};
        System.out.println("Array 1: " + arrayToString(nums5_1));
        System.out.println("Array 2: " + arrayToString(nums5_2));
        System.out.println("\nMerge Process:");
        System.out.println("  All Array 1 elements < Array 2 elements");
        System.out.println("  Pick: 1, 2, 3 (exhaust Array 1)");
        System.out.println("  Then: 4, 5, 6, 7 (remaining from Array 2)");
        System.out.println("  Merged: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 7 (odd)");
        System.out.println("  Middle index = 3");
        System.out.println("  Median = 4.0\n");

        double result5 = solver.findMedianSortedArrays(nums5_1, nums5_2);
        System.out.println("✓ Median: " + result5);
        System.out.println("  Expected: 4.0");
        System.out.println("  Status: " + (result5 == 4.0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Duplicate Values ===");
        int[] nums6_1 = {1, 2, 2};
        int[] nums6_2 = {2, 2, 3};
        System.out.println("Array 1: " + arrayToString(nums6_1));
        System.out.println("Array 2: " + arrayToString(nums6_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Multiple 2's from both arrays");
        System.out.println("  Stable merge maintains order");
        System.out.println("  Merged: [1, 2, 2, 2, 2, 3]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 6 (even)");
        System.out.println("  Middle indices = 2 and 3");
        System.out.println("  Median = (2 + 2) / 2 = 2.0\n");

        double result6 = solver.findMedianSortedArrays(nums6_1, nums6_2);
        System.out.println("✓ Median: " + result6);
        System.out.println("  Expected: 2.0");
        System.out.println("  Status: " + (result6 == 2.0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Negative Numbers ===");
        int[] nums7_1 = {- 5, - 3, - 1};
        int[] nums7_2 = {- 4, - 2, 0};
        System.out.println("Array 1: " + arrayToString(nums7_1));
        System.out.println("Array 2: " + arrayToString(nums7_2));
        System.out.println("\nMerge Process:");
        System.out.println("  -5 → -4 → -3 → -2 → -1 → 0");
        System.out.println("  Merged: [-5, -4, -3, -2, -1, 0]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 6 (even)");
        System.out.println("  Middle indices = 2 and 3");
        System.out.println("  Median = (-3 + -2) / 2 = -2.5\n");

        double result7 = solver.findMedianSortedArrays(nums7_1, nums7_2);
        System.out.println("✓ Median: " + result7);
        System.out.println("  Expected: -2.5");
        System.out.println("  Status: " + (result7 == - 2.5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large Gap Between Arrays ===");
        int[] nums8_1 = {1, 2};
        int[] nums8_2 = {100, 101, 102};
        System.out.println("Array 1: " + arrayToString(nums8_1));
        System.out.println("Array 2: " + arrayToString(nums8_2));
        System.out.println("\nMerge Process:");
        System.out.println("  Array 1 exhausted first (1, 2)");
        System.out.println("  Then all from Array 2 (100, 101, 102)");
        System.out.println("  Merged: [1, 2, 100, 101, 102]");
        System.out.println("\nMedian Calculation:");
        System.out.println("  Length = 5 (odd)");
        System.out.println("  Middle index = 2");
        System.out.println("  Median = 100.0\n");

        double result8 = solver.findMedianSortedArrays(nums8_1, nums8_2);
        System.out.println("✓ Median: " + result8);
        System.out.println("  Expected: 100.0");
        System.out.println("  Status: " + (result8 == 100.0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Median Definition:                                          ║");
        System.out.println("║    • Odd length: middle element                              ║");
        System.out.println("║    • Even length: average of two middle elements             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two-Pointer Merge Algorithm:                                ║");
        System.out.println("║    1. Initialize pointers i=0, j=0 for both arrays           ║");
        System.out.println("║    2. Compare elements at current pointers                   ║");
        System.out.println("║    3. Pick smaller element, advance that pointer             ║");
        System.out.println("║    4. Continue until one array exhausted                     ║");
        System.out.println("║    5. Copy remaining elements from other array               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Merge Example: [1,3] and [2,4]                              ║");
        System.out.println("║    Step 1: 1 < 2 → pick 1, i++                               ║");
        System.out.println("║    Step 2: 3 > 2 → pick 2, j++                               ║");
        System.out.println("║    Step 3: 3 < 4 → pick 3, i++                               ║");
        System.out.println("║    Step 4: Array 1 exhausted, pick 4                         ║");
        System.out.println("║    Result: [1, 2, 3, 4]                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Median Index Calculation:                                   ║");
        System.out.println("║    mid = (length - 1) / 2                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For ODD length (e.g., 5 elements):                        ║");
        System.out.println("║      mid = (5-1)/2 = 2                                       ║");
        System.out.println("║      Return: arr[2]                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For EVEN length (e.g., 6 elements):                       ║");
        System.out.println("║      mid = (6-1)/2 = 2                                       ║");
        System.out.println("║      Return: (arr[2] + arr[3]) / 2                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why (length - 1) / 2?                                       ║");
        System.out.println("║    • For odd: gives exact middle                             ║");
        System.out.println("║    • For even: gives lower middle, then add +1 for upper     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Type Casting:                                               ║");
        System.out.println("║    • (double) ensures decimal result for even-length median  ║");
        System.out.println("║    • Without cast: integer division truncates                ║");
        System.out.println("║    • Example: 5/2 = 2, but (double)5/2 = 2.5                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(m + n) - Merge both arrays              ║");
        System.out.println("║  Space Complexity: O(m + n) - Store merged array             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Note: O(log(m+n)) solution exists using binary search       ║");
        System.out.println("║        without merging arrays (more complex implementation)  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
    }

}
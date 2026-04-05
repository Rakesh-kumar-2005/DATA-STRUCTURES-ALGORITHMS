package Binary_Search;

/*
        Description :-
            We have to find the index to insert the given target in the sorted Array...
            But we have condition that the time complexity shouldn't exceed O(log(n))...

        Approach :-
            > From the condition we must know that we have to use binary search...
            > So,the typical left and right pointer,then iterate until left <= right...
            > Then we have to compare every mid-value with the given element and reduce
                our search space into it's half-length...
*/

public class Search_Insertion_Position {

    private static int searchInsert(int[] nums, int target) {
        
        int st = 0;
        int ed = nums.length - 1;

        while (st <= ed) {
            int mid = st + (ed - st) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                st = mid + 1;
            } else {
                ed = mid - 1;
            }
        }
        
        return st;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            SEARCH INSERTION POSITION                         ║");
        System.out.println("║  Find target index or position where it should be inserted   ║");
        System.out.println("║  Array is sorted in ascending order                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    
        System.out.println("=== Test Case 1: Target Found ===");
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("Target: " + target1);
        System.out.println("\nBinary Search:");
        System.out.println("  Target 5 found at index 2\n");
        
        int result1 = searchInsert(nums1, target1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 2: Insert in Middle ===");
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("Target: " + target2);
        System.out.println("\nBinary Search:");
        System.out.println("  Target 2 not found");
        System.out.println("  Should be inserted between 1 and 3");
        System.out.println("  Insert at index 1\n");
        
        int result2 = searchInsert(nums2, target2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 3: Insert at End ===");
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("Target: " + target3);
        System.out.println("\nBinary Search:");
        System.out.println("  Target 7 larger than all elements");
        System.out.println("  Insert at end (index 4)\n");
        
        int result3 = searchInsert(nums3, target3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result3 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 4: Insert at Beginning ===");
        int[] nums4 = {1, 3, 5, 6};
        int target4 = 0;
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("Target: " + target4);
        System.out.println("\nBinary Search:");
        System.out.println("  Target 0 smaller than all elements");
        System.out.println("  Insert at beginning (index 0)\n");
        
        int result4 = searchInsert(nums4, target4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 5: Single Element - Found ===");
        int[] nums5 = {1};
        int target5 = 1;
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("Target: " + target5);
        System.out.println("\nFound at index 0\n");
        
        int result5 = searchInsert(nums5, target5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 6: Single Element - Insert After ===");
        int[] nums6 = {1};
        int target6 = 2;
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("Target: " + target6);
        System.out.println("\nInsert after element (index 1)\n");
        
        int result6 = searchInsert(nums6, target6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result6 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 7: Large Array ===");
        int[] nums7 = {1, 3, 5, 7, 9, 11, 13, 15};
        int target7 = 10;
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("Target: " + target7);
        System.out.println("\nBinary Search trace:");
        System.out.println("  mid=3 (7): 10 > 7 → search right");
        System.out.println("  mid=5 (11): 10 < 11 → search left");
        System.out.println("  mid=4 (9): 10 > 9 → search right");
        System.out.println("  Insert at index 5\n");
        
        int result7 = searchInsert(nums7, target7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result7 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find target or insertion position in sorted array  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Binary Search Approach:                                     ║");
        System.out.println("║    Search space: [0, n-1]                                    ║");
        System.out.println("║    Divide array in half at each step                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    st = 0, ed = n-1                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    while (st <= ed):                                         ║");
        System.out.println("║      mid = st + (ed - st) / 2                                ║");
        System.out.println("║      if (nums[mid] == target): return mid (found!)           ║");
        System.out.println("║      if (nums[mid] < target): st = mid + 1 (search right)    ║");
        System.out.println("║      else: ed = mid - 1 (search left)                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    return st (insertion position)                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Return st?                                              ║");
        System.out.println("║    When loop ends (st > ed):                                 ║");
        System.out.println("║    • st points to where target should be inserted            ║");
        System.out.println("║    • All elements before st are < target                     ║");
        System.out.println("║    • All elements from st onward are >= target               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: nums=[1,3,5,6], target=2                           ║");
        System.out.println("║    Initial: st=0, ed=3                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Iteration 1:                                              ║");
        System.out.println("║      mid = 0 + (3-0)/2 = 1                                   ║");
        System.out.println("║      nums[1]=3 > target=2                                    ║");
        System.out.println("║      ed = mid - 1 = 0                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Iteration 2:                                              ║");
        System.out.println("║      mid = 0 + (0-0)/2 = 0                                   ║");
        System.out.println("║      nums[0]=1 < target=2                                    ║");
        System.out.println("║      st = mid + 1 = 1                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Now st=1, ed=0 → st > ed, loop ends                       ║");
        System.out.println("║    Return st=1 (insert position)                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Overflow Prevention:                                        ║");
        System.out.println("║    mid = st + (ed - st) / 2                                  ║");
        System.out.println("║    Instead of: mid = (st + ed) / 2                           ║");
        System.out.println("║    Avoids integer overflow when st + ed > MAX_INT            ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(log n) - Binary search                           ║");
        System.out.println("║    Space: O(1) - Constant extra space                        ║");
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
    
}

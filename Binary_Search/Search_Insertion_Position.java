package Binary_Search;

/*

    Description:
      Following program finds the index of a target value in a sorted array,
        or the position where it should be inserted to maintain sorted order...
    
    Problem Statement:
      -> Given a sorted array of distinct integers and a target value...
      -> If target exists in the array → return its index...
      -> If target does not exist → return the index where it should be inserted...
      -> Insertion must preserve the ascending sorted order...
      -> Must achieve O(log n) time complexity...
    
    Core Insight:
      -> Standard binary search is used for efficient lookup...
      -> When the target is not found, the loop ends with st > ed...
      -> At that point, st holds the exact insertion position...
      -> No additional logic needed: return st works for both cases...
    
    Algorithm - Binary Search with Insertion:
      -> Initialize st = 0, ed = nums.length - 1...
      -> While st <= ed:
           i.  Compute mid = st + (ed - st) / 2...
           ii. If nums[mid] == target → return mid (found)...
           iii.If nums[mid] < target  → st = mid + 1 (search right half)...
           iv. If nums[mid] > target  → ed = mid - 1 (search left half)...
      -> Return st (insertion position when target not found)...
    
    Why Return st After Loop Ends?
      -> Loop exits when st > ed (search space exhausted)...
      -> At this point:
           All elements before index st are strictly less than target...
           All elements from index st onward are strictly greater than target...
      -> Inserting target at index st maintains sorted order...
      -> st naturally equals the lower bound of target in the array...
    
    Example Walkthroughs:
      -> nums = [1, 3, 5, 6], target = 5 (found):
           Iteration 1: st=0, ed=3, mid=1, nums[1]=3 < 5 → st=2...
           Iteration 2: st=2, ed=3, mid=2, nums[2]=5 == 5 → return 2...
           Result: 2...
    
      -> nums = [1, 3, 5, 6], target = 2 (insert middle):
           Iteration 1: st=0, ed=3, mid=1, nums[1]=3 > 2 → ed=0...
           Iteration 2: st=0, ed=0, mid=0, nums[0]=1 < 2 → st=1...
           Loop ends: st=1, ed=0 (st > ed)...
           Result: st = 1 (insert between 1 and 3)...
    
      -> nums = [1, 3, 5, 6], target = 7 (insert at end):
           Iterations narrow to st=4, ed=3 (st > ed)...
           Result: st = 4 (insert after last element)...
    
      -> nums = [1, 3, 5, 6], target = 0 (insert at beginning):
           Iteration 1: st=0, ed=3, mid=1, nums[1]=3 > 0 → ed=0...
           Iteration 2: st=0, ed=0, mid=0, nums[0]=1 > 0 → ed=-1...
           Loop ends: st=0, ed=-1 (st > ed)...
           Result: st = 0 (insert before all elements)...
    
    Overflow-Safe Midpoint Calculation:
      -> Used: mid = st + (ed - st) / 2...
      -> Avoid: mid = (st + ed) / 2...
      -> If st and ed are both large, (st + ed) may exceed Integer.MAX_VALUE...
      -> Subtracting first keeps intermediate values within safe integer range...
    
    Search Space Narrowing:
      -> After each iteration, search space is halved...
      -> If target < nums[mid]: discard right half → ed = mid - 1...
      -> If target > nums[mid]: discard left half  → st = mid + 1...
      -> At most ceil(log2(n)) iterations before loop terminates...
    
    Binary Search Trace (nums=[1,3,5,7,9,11,13,15], target=10):
      -> st=0, ed=7, mid=3, nums[3]=7  < 10 → st=4...
      -> st=4, ed=7, mid=5, nums[5]=11 > 10 → ed=4...
      -> st=4, ed=4, mid=4, nums[4]=9  < 10 → st=5...
      -> st=5, ed=4 → loop ends...
      -> Return st = 5 (insert between 9 and 11)...
    
    Connection to Lower Bound:
      -> This algorithm computes the lower bound of target in the array...
      -> Lower bound = index of first element >= target...
      -> If target exists: lower bound = its index...
      -> If target missing: lower bound = correct insertion position...
      -> st after binary search always equals the lower bound...
    
    Edge Cases:
      -> Target smaller than all elements → return 0...
      -> Target larger than all elements  → return nums.length...
      -> Target found at first index      → return 0...
      -> Target found at last index       → return nums.length - 1...
      -> Single element array, target matches → return 0...
      -> Single element array, target larger  → return 1...
      -> Single element array, target smaller → return 0...
    
    Why Binary Search Works Here:
      -> Array is sorted → enables elimination of half the search space each step...
      -> No duplicates → exactly one valid index exists for each target...
      -> Sorted order guarantees that once nums[mid] < target, all left half < target...
      -> Similarly, if nums[mid] > target, all right half > target...
    
    Time and Space Complexity:
      -> Time:  O(log n)...
           Search space halves at every iteration...
           At most ceil(log2(n)) iterations...
      -> Space: O(1)...
           Only three integer variables: st, ed, mid...
           No additional arrays or recursion stack used...
    
    Applications:
      -> Maintaining sorted order in dynamic arrays...
      -> Lower bound and upper bound queries in sorted lists...
      -> Binary indexed trees and segment trees with coordinate compression...
      -> Sorted insertion in competitive programming...
      -> Foundation for advanced binary search problems like finding floor/ceiling...

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

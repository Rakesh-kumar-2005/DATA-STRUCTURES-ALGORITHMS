package Arrays;

/*

Description:
  This program divides an array into **exactly three non-empty subarrays**
  such that the **total cost is minimized**...
  The cost is defined as:
       first element + two smallest elements after it...

Problem Understanding:
  -> We must create 3 subarrays...
  -> The first subarray must always start at index 0...
  -> Every new subarray contributes its first element to cost...
  -> Therefore:
       cost = nums[first split] + nums[second split] + nums[third split]...

Key Observation:
  -> First split is FIXED at index 0...
  -> So nums[0] is always included...
  -> We only need TWO more starting points from indices 1 to n-1...
  -> To minimize cost:
       choose the two smallest numbers from nums[1..n-1]...

Core Idea:
  -> Find:
       smallest value (temp1)...
       second smallest value (temp2)...
  -> Final answer:
       nums[0] + temp1 + temp2...

Method: minimumCost():
  -> Initialize:
       temp1 = Integer.MAX_VALUE...
       temp2 = Integer.MAX_VALUE...
  -> Traverse array from index 1...
  -> For each number:
       if smaller than temp1:
           shift temp1 → temp2...
           update temp1...
       else if smaller than temp2:
           update temp2...
  -> Return sum...

Two-Minimum Tracking Logic:
  -> This avoids sorting...
  -> Maintains smallest two in one pass...
  -> Efficient O(n) approach...

Update Rules:
  -> Case 1: nums[i] < temp1
       temp2 = temp1...
       temp1 = nums[i]...
  -> Case 2: temp1 ≤ nums[i] < temp2
       temp2 = nums[i]...

Why Integer.MAX_VALUE:
  -> Acts as infinity placeholder...
  -> Guarantees first comparison succeeds...
  -> Simplifies logic without extra checks...

Algorithm Steps:
  -> Step 1: Fix nums[0] in cost...
  -> Step 2: Scan remaining elements...
  -> Step 3: Track two smallest values...
  -> Step 4: Return sum...

Example Walkthrough:
  -> nums = [10, 3, 1, 1]...
       temp1=∞, temp2=∞...
       see 3  → temp1=3...
       see 1  → temp1=1, temp2=3...
       see 1  → temp2=1...
       cost = 10 + 1 + 1 = 12...

Edge Cases Covered:
  -> Minimum valid size (3 elements)...
  -> Duplicate values...
  -> All equal elements...
  -> Descending arrays...
  -> Ascending arrays...
  -> Large first element (mandatory inclusion)...

Why Not Sorting:
  -> Sorting costs O(n log n)...
  -> We only need 2 smallest...
  -> One-pass scan gives O(n)...
  -> More optimal...

Time Complexity:
  -> O(n)...
  -> Single traversal...

Space Complexity:
  -> O(1)...
  -> Only two variables used...

Key Insight:
  -> Convert "3 subarrays" problem into:
       "find two minimum values" problem...
  -> Greedy choice works because cost depends only on starts...

Interview Tip:
  -> Classic pattern:
       track smallest and second smallest...
  -> Common optimization over sorting...
  -> Useful in many greedy problems...

Final Verdict:
  -> Efficient greedy solution...
  -> Linear time...
  -> Constant space...
  -> Optimal and clean implementation...

*/

public class Divide_An_Array_Into_Subarrays_With_Minimum_Cost {

    private static int minimumCost(int[] nums) {

        int temp1 = 2147483647;
        int temp2 = 2147483647;

        for (int i = 1; i < nums.length; i++) {
            if (temp1 > nums[i]) {
                temp2 = temp1;
                temp1 = nums[i];
            } else if (temp2 > nums[i]) {
                temp2 = nums[i];
            }
        }

        return nums[0] + temp1 + temp2;

    }

    private static String arrayToString(int[] arr) {
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
        System.out.println("║  DIVIDE ARRAY INTO SUBARRAYS WITH MINIMUM COST               ║");
        System.out.println("║  Split array into 3 non-empty subarrays                      ║");
        System.out.println("║  Cost = first element + two smallest elements after it       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {1, 2, 3, 12};
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 1");
        System.out.println("  Remaining elements: [2, 3, 12]");
        System.out.println("  Two smallest: 2 and 3");
        System.out.println("  Cost = 1 + 2 + 3 = 6\n");
        System.out.println("Subarray division example:");
        System.out.println("  Subarray 1: [1]");
        System.out.println("  Subarray 2: [2]");
        System.out.println("  Subarray 3: [3, 12]\n");

        int result1 = minimumCost(nums1);
        System.out.println("✓ Minimum Cost: " + result1);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result1 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Larger Values ===");
        int[] nums2 = {5, 4, 11};
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 5");
        System.out.println("  Remaining elements: [4, 11]");
        System.out.println("  Two smallest: 4 and 11 (only 2 elements)");
        System.out.println("  Cost = 5 + 4 + 11 = 20\n");
        System.out.println("Subarray division:");
        System.out.println("  Subarray 1: [5]");
        System.out.println("  Subarray 2: [4]");
        System.out.println("  Subarray 3: [11]\n");

        int result2 = minimumCost(nums2);
        System.out.println("✓ Minimum Cost: " + result2);
        System.out.println("  Expected: 20");
        System.out.println("  Status: " + (result2 == 20 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Minimum Length Array ===");
        int[] nums3 = {10, 3, 1, 1};
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 10");
        System.out.println("  Remaining elements: [3, 1, 1]");
        System.out.println("  Two smallest: 1 and 1");
        System.out.println("  Cost = 10 + 1 + 1 = 12\n");
        System.out.println("Optimal division:");
        System.out.println("  Subarray 1: [10]");
        System.out.println("  Subarray 2: [3, 1]");
        System.out.println("  Subarray 3: [1]\n");

        int result3 = minimumCost(nums3);
        System.out.println("✓ Minimum Cost: " + result3);
        System.out.println("  Expected: 12");
        System.out.println("  Status: " + (result3 == 12 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Equal Elements ===");
        int[] nums4 = {7, 7, 7, 7, 7};
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 7");
        System.out.println("  Remaining elements: [7, 7, 7, 7]");
        System.out.println("  Two smallest: 7 and 7 (all equal)");
        System.out.println("  Cost = 7 + 7 + 7 = 21\n");

        int result4 = minimumCost(nums4);
        System.out.println("✓ Minimum Cost: " + result4);
        System.out.println("  Expected: 21");
        System.out.println("  Status: " + (result4 == 21 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Descending Order ===");
        int[] nums5 = {50, 40, 30, 20, 10};
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 50");
        System.out.println("  Remaining elements: [40, 30, 20, 10]");
        System.out.println("  Two smallest: 10 and 20");
        System.out.println("  Cost = 50 + 10 + 20 = 80\n");
        System.out.println("Algorithm efficiently finds:");
        System.out.println("  temp1 updates: 40 → 30 → 20 → 10");
        System.out.println("  temp2 updates: 40 → 30 → 20\n");

        int result5 = minimumCost(nums5);
        System.out.println("✓ Minimum Cost: " + result5);
        System.out.println("  Expected: 80");
        System.out.println("  Status: " + (result5 == 80 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Ascending Order ===");
        int[] nums6 = {1, 2, 3, 4, 5};
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 1");
        System.out.println("  Remaining elements: [2, 3, 4, 5]");
        System.out.println("  Two smallest: 2 and 3");
        System.out.println("  Cost = 1 + 2 + 3 = 6\n");
        System.out.println("Algorithm finds smallest early:");
        System.out.println("  First iteration: temp1 = 2");
        System.out.println("  Second iteration: temp2 = 3\n");

        int result6 = minimumCost(nums6);
        System.out.println("✓ Minimum Cost: " + result6);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result6 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Mixed Values ===");
        int[] nums7 = {100, 5, 50, 3, 75, 8};
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 100");
        System.out.println("  Remaining elements: [5, 50, 3, 75, 8]");
        System.out.println("  Two smallest: 3 and 5");
        System.out.println("  Cost = 100 + 3 + 5 = 108\n");
        System.out.println("Tracking smallest two:");
        System.out.println("  After 5:  temp1=5,  temp2=MAX");
        System.out.println("  After 50: temp1=5,  temp2=50");
        System.out.println("  After 3:  temp1=3,  temp2=5  ← swap occurs");
        System.out.println("  After 75: temp1=3,  temp2=5");
        System.out.println("  After 8:  temp1=3,  temp2=5  ← 8 > both\n");

        int result7 = minimumCost(nums7);
        System.out.println("✓ Minimum Cost: " + result7);
        System.out.println("  Expected: 108");
        System.out.println("  Status: " + (result7 == 108 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large First Element ===");
        int[] nums8 = {1000, 1, 2};
        System.out.println("Array: " + arrayToString(nums8));
        System.out.println("\nAnalysis:");
        System.out.println("  First element (mandatory): nums[0] = 1000");
        System.out.println("  Remaining elements: [1, 2]");
        System.out.println("  Two smallest: 1 and 2");
        System.out.println("  Cost = 1000 + 1 + 2 = 1003\n");
        System.out.println("Note: First element always included regardless");
        System.out.println("      of its value (it's mandatory)\n");

        int result8 = minimumCost(nums8);
        System.out.println("✓ Minimum Cost: " + result8);
        System.out.println("  Expected: 1003");
        System.out.println("  Status: " + (result8 == 1003 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Constraint:                                         ║");
        System.out.println("║    • Must divide into exactly 3 non-empty subarrays          ║");
        System.out.println("║    • First subarray MUST start at index 0                    ║");
        System.out.println("║    • Cost = first element + sum of subarray starts           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Observation:                                            ║");
        System.out.println("║    • nums[0] is always included (mandatory)                  ║");
        System.out.println("║    • We need 2 more division points from nums[1..n-1]        ║");
        System.out.println("║    • To minimize cost: pick 2 smallest from nums[1..n-1]     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two-Variable Tracking:                                      ║");
        System.out.println("║    temp1: smallest value seen                                ║");
        System.out.println("║    temp2: second smallest value seen                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Update Logic:                                               ║");
        System.out.println("║    if (temp1 > nums[i]):                                     ║");
        System.out.println("║      • New smallest found                                    ║");
        System.out.println("║      • Old temp1 becomes temp2                               ║");
        System.out.println("║      • temp1 = nums[i]                                       ║");
        System.out.println("║    else if (temp2 > nums[i]):                                ║");
        System.out.println("║      • New second smallest found                             ║");
        System.out.println("║      • temp2 = nums[i]                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Integer.MAX_VALUE (2147483647)?                         ║");
        System.out.println("║    • Initializes temps to maximum possible value             ║");
        System.out.println("║    • Ensures any array element is smaller                    ║");
        System.out.println("║    • Simplifies comparison logic                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Walkthrough: [10, 3, 1, 1]                          ║");
        System.out.println("║    Initial: temp1=MAX, temp2=MAX                             ║");
        System.out.println("║    i=1 (3):  temp1=3, temp2=MAX                              ║");
        System.out.println("║    i=2 (1):  temp1=1, temp2=3  ← swap                        ║");
        System.out.println("║    i=3 (1):  temp1=1, temp2=1  ← update temp2                ║");
        System.out.println("║    Result: 10 + 1 + 1 = 12                                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n) - Single pass through array          ║");
        System.out.println("║  Space Complexity: O(1) - Only two variables used            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

package Hashing;

/*

Description:
  This program solves the problem **N-Repeated Element in Size 2N Array** using hashing techniques...
  The array has size 2n, contains n+1 unique elements, and exactly one element is repeated n times...
  The goal is to efficiently identify that repeated element...

Problem Statement:
  -> Given an integer array `numbers` of size 2n...
  -> The array contains n+1 distinct elements...
  -> Exactly one element appears n times...
  -> All other elements appear exactly once...
  -> Return the element that is repeated n times...

Key Properties of the Problem:
  -> Total elements = 2n...
  -> One element frequency = n...
  -> Remaining n elements are unique...
  -> Guarantees existence of a valid repeated element (except edge discussion when n = 1)...

Approach 1: HashMap (Frequency Counting):
  -> Use a HashMap<Integer, Integer> to count occurrences of each element...
  -> Traverse the array and store frequency counts...
  -> Since the repeated element appears exactly n times:
       • Identify the key whose frequency equals n...
  -> Return that key...

Algorithm Steps (HashMap):
  -> Initialize an empty HashMap...
  -> For each number in the array:
       • Increment its frequency in the map...
  -> Iterate through map entries:
       • If frequency == n, return that key...
  -> Time Complexity: O(n)...
  -> Space Complexity: O(n)...

Advantages:
  -> Very clear and explicit logic...
  -> Easy to debug and explain...
  -> Useful for analytical understanding...

Disadvantages:
  -> Requires two passes (count + search)...
  -> Uses extra memory for frequency storage...

Approach 2: HashSet (Early Detection):
  -> Use a HashSet to track already seen elements...
  -> Traverse the array once...
  -> Attempt to insert each element into the set...
  -> If insertion fails:
       • The element already exists → this is the repeated element...
       • Return immediately (early exit)...

Algorithm Steps (HashSet):
  -> Initialize an empty HashSet...
  -> For each number in the array:
       • If set.add(num) returns false:
            → num is already present...
            → return num immediately...
  -> Time Complexity:
       • Best case: O(1) (duplicate found early)...
       • Worst case: O(n)...
  -> Space Complexity: O(n)...

Why Early Detection Works:
  -> Since one element appears n times, it must repeat...
  -> HashSet guarantees uniqueness...
  -> First duplicate encountered must be the repeated element...
  -> No need to scan entire array...

Edge Case Discussion:
  -> n = 1 (array size = 2):
       • Both elements appear once...
       • Problem constraint technically breaks here...
       • Either element may be returned...
       • Most platforms ignore or constrain n ≥ 2...

Examples:
  -> [1, 2, 3, 3]:
       • Repeated element = 3...
  -> [2, 1, 2, 5, 3, 2]:
       • Repeated element = 2...
  -> [5, 1, 5, 2, 5, 3, 5, 4]:
       • Repeated element = 5...

Complexity Comparison:
  -> HashMap:
       • Time: O(n)...
       • Space: O(n)...
       • Explicit frequency logic...
  -> HashSet:
       • Time: O(n) worst-case, often much faster...
       • Space: O(n)...
       • Early termination gives practical speed advantage...

Key Insight:
  -> Only ONE element repeats...
  -> Detecting the first duplicate is sufficient...
  -> HashSet approach is optimal in practice...

Final Recommendation:
  -> Use **HashSet (Method 2)** for production and interviews...
  -> Use **HashMap (Method 1)** for explanation and clarity...

Summary:
  -> Clean problem demonstrating hashing fundamentals...
  -> Shows trade-off between clarity and performance...
  -> Excellent example of early-exit optimization...
  -> HashSet approach is the winner 🚀...

*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class N_Repeated_Element_In_Size_2N_Array {

    // Method 1: HashMap with frequency counting
    private static int repeatedNTimes1(int[] numbers) {
        int n = numbers.length;
        int frequency = n / 2;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : numbers) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (map.get(key) == frequency) {
                return key;
            }
        }

        return - 1;
    }

    // Method 2: HashSet (early detection)
    private static int repeatedNTimes2(int[] numbers) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : numbers) {
            if (! seen.add(num)) {
                return num;
            }
        }

        return - 1;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        N-REPEATED ELEMENT IN SIZE 2N ARRAY                   ║");
        System.out.println("║       Array of size 2n with n+1 unique elements              ║");
        System.out.println("║         Exactly one element appears n times                  ║");
        System.out.println("║            Find that repeated element                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {1, 2, 3, 3};
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Array size: 4 (2n where n=2)");
        System.out.println("Element 3 appears 2 times (n times)");
        System.out.println("\nMethod 1 (HashMap):");
        System.out.println("  Count: {1:1, 2:1, 3:2}");
        System.out.println("  Find element with count = n = 2");
        System.out.println("  Answer: 3");
        System.out.println("\nMethod 2 (HashSet):");
        System.out.println("  Add 1 → success");
        System.out.println("  Add 2 → success");
        System.out.println("  Add 3 → success");
        System.out.println("  Add 3 → fails! (already exists)");
        System.out.println("  Answer: 3 (found on first duplicate)\n");

        int result1_m1 = repeatedNTimes1(nums1);
        int result1_m2 = repeatedNTimes2(nums1);
        System.out.println("✓ Method 1 result: " + result1_m1);
        System.out.println("✓ Method 2 result: " + result1_m2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1_m1 == 3 && result1_m2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Larger Array ===");
        int[] nums2 = {2, 1, 2, 5, 3, 2};
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Array size: 6 (2n where n=3)");
        System.out.println("Element 2 appears 3 times (n times)");
        System.out.println("\nAnalysis:");
        System.out.println("  Unique elements: {1, 2, 3, 5} = 4 elements");
        System.out.println("  Element 2 repeats 3 times");
        System.out.println("  Total: 3 + 1 + 1 + 1 = 6 ✓\n");

        int result2_m1 = repeatedNTimes1(nums2);
        int result2_m2 = repeatedNTimes2(nums2);
        System.out.println("✓ Method 1 result: " + result2_m1);
        System.out.println("✓ Method 2 result: " + result2_m2);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result2_m1 == 2 && result2_m2 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Repeated at End ===");
        int[] nums3 = {5, 1, 5, 2, 5, 3, 5, 4};
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Array size: 8 (2n where n=4)");
        System.out.println("Element 5 appears 4 times (n times)");
        System.out.println("\nPattern:");
        System.out.println("  5 appears at positions: 0, 2, 4, 6");
        System.out.println("  Alternating with unique elements");
        System.out.println("\nMethod 2 advantage:");
        System.out.println("  Detects duplicate at index 2 (early exit!)\n");

        int result3_m1 = repeatedNTimes1(nums3);
        int result3_m2 = repeatedNTimes2(nums3);
        System.out.println("✓ Method 1 result: " + result3_m1);
        System.out.println("✓ Method 2 result: " + result3_m2);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result3_m1 == 5 && result3_m2 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Consecutive Repeats ===");
        int[] nums4 = {9, 9, 9, 9};
        System.out.println("Array: " + Arrays.toString(nums4));
        System.out.println("Array size: 4 (2n where n=2)");
        System.out.println("Element 9 appears 2 times (n times)");
        System.out.println("\nNote:");
        System.out.println("  All elements are the same");
        System.out.println("  Method 2 finds answer immediately at index 1!\n");

        int result4_m1 = repeatedNTimes1(nums4);
        int result4_m2 = repeatedNTimes2(nums4);
        System.out.println("✓ Method 1 result: " + result4_m1);
        System.out.println("✓ Method 2 result: " + result4_m2);
        System.out.println("  Expected: 9");
        System.out.println("  Status: " + (result4_m1 == 9 && result4_m2 == 9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Large Numbers ===");
        int[] nums5 = {1000, 2000, 3000, 1000};
        System.out.println("Array: " + Arrays.toString(nums5));
        System.out.println("Array size: 4 (2n where n=2)");
        System.out.println("Element 1000 appears 2 times (n times)\n");

        int result5_m1 = repeatedNTimes1(nums5);
        int result5_m2 = repeatedNTimes2(nums5);
        System.out.println("✓ Method 1 result: " + result5_m1);
        System.out.println("✓ Method 2 result: " + result5_m2);
        System.out.println("  Expected: 1000");
        System.out.println("  Status: " + (result5_m1 == 1000 && result5_m2 == 1000 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: n = 1 (Smallest Valid) ===");
        int[] nums6 = {0, 1};
        System.out.println("Array: " + Arrays.toString(nums6));
        System.out.println("Array size: 2 (2n where n=1)");
        System.out.println("No element repeats (edge case)");
        System.out.println("According to problem, one element must repeat n times");
        System.out.println("With n=1, both appear once - technically both valid\n");

        int result6_m1 = repeatedNTimes1(nums6);
        int result6_m2 = repeatedNTimes2(nums6);
        System.out.println("✓ Method 1 result: " + result6_m1);
        System.out.println("✓ Method 2 result: " + result6_m2);
        System.out.println("  Note: Edge case - either answer acceptable\n");

        System.out.println("=== Test Case 7: Mixed Pattern ===");
        int[] nums7 = {7, 8, 7, 9, 7, 10};
        System.out.println("Array: " + Arrays.toString(nums7));
        System.out.println("Array size: 6 (2n where n=3)");
        System.out.println("Element 7 appears 3 times");
        System.out.println("\nPositions of 7: [0, 2, 4]");
        System.out.println("Method 2 finds it at index 2 (early!)\n");

        int result7_m1 = repeatedNTimes1(nums7);
        int result7_m2 = repeatedNTimes2(nums7);
        System.out.println("✓ Method 1 result: " + result7_m1);
        System.out.println("✓ Method 2 result: " + result7_m2);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result7_m1 == 7 && result7_m2 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Negative Numbers ===");
        int[] nums8 = {- 1, - 2, - 1, - 3, - 1, - 4};
        System.out.println("Array: " + Arrays.toString(nums8));
        System.out.println("Array size: 6 (2n where n=3)");
        System.out.println("Element -1 appears 3 times\n");

        int result8_m1 = repeatedNTimes1(nums8);
        int result8_m2 = repeatedNTimes2(nums8);
        System.out.println("✓ Method 1 result: " + result8_m1);
        System.out.println("✓ Method 2 result: " + result8_m2);
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result8_m1 == - 1 && result8_m2 == - 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPLEXITY COMPARISON                                       ║");
        System.out.println("║  ┌──────────────────┬────────────┬─────────────┬──────────┐  ║");
        System.out.println("║  │ Method           │ Time       │ Space       │ Best For │  ║");
        System.out.println("║  ├──────────────────┼────────────┼─────────────┼──────────┤  ║");
        System.out.println("║  │ HashMap          │ O(n)       │ O(n)        │ Analysis │  ║");
        System.out.println("║  │ HashSet (Early)  │ O(n) avg   │ O(n)        │ Speed ⚡  │  ║");
        System.out.println("║  │                  │ O(1) best  │             │          │  ║");
        System.out.println("║  └──────────────────┴────────────┴─────────────┴──────────┘  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║  • Method 1: Counts all, then finds the repeated element     ║");
        System.out.println("║  • Method 2: Returns immediately on first duplicate          ║");
        System.out.println("║  • Method 2 is FASTER in practice (early termination)        ║");
        System.out.println("║  • Both have O(n) worst case, but Method 2 often O(1)-O(n)   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  🏆 Winner: Method 2 (HashSet) - Faster average case         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}



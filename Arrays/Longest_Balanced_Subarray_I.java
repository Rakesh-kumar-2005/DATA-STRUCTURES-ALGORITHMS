package Arrays;

/*

Description:
  This program finds the **length of the longest balanced subarray**
  from a given integer array...

  A subarray is called **balanced** if:
    → Number of DISTINCT even elements
      ==
    → Number of DISTINCT odd elements...

------------------------------------------------------------
Problem Clarification:
------------------------------------------------------------

  • Order matters (subarray = continuous segment)...
  • DISTINCT count is important...
  • Duplicate values are counted only once...
  • Balance is based on parity (even vs odd), not frequency...

------------------------------------------------------------
Key Definitions:
------------------------------------------------------------

  -> Even number:
       number % 2 == 0...

  -> Odd number:
       number % 2 != 0...

  -> Balanced Subarray:
       distinctEvenCount == distinctOddCount...

------------------------------------------------------------
Approach Used (Brute Force):
------------------------------------------------------------

  Two nested loops are used to examine **all possible subarrays**...

  Outer loop (i):
    → Marks the starting index of subarray...

  Inner loop (j):
    → Expands the subarray from index i to j...

------------------------------------------------------------
Data Structures Used:
------------------------------------------------------------

  -> HashSet<Integer> even:
       Stores DISTINCT even numbers in current subarray...

  -> HashSet<Integer> odd:
       Stores DISTINCT odd numbers in current subarray...

  Why HashSet?
    • Automatically avoids duplicates...
    • size() gives distinct count directly...
    • O(1) average insert time...

------------------------------------------------------------
Algorithm Steps:
------------------------------------------------------------

STEP 1:
  Initialize max = 0...

STEP 2:
  For each starting index i:
    • Create fresh HashSet for even and odd...

STEP 3:
  For each ending index j ≥ i:
    • Check parity of numbers[j]...
    • Add it to even or odd HashSet...
    • Compare sizes:
         if even.size() == odd.size():
             update max length...

STEP 4:
  Return max as final answer...

------------------------------------------------------------
Example Walkthrough:
------------------------------------------------------------

Input:
  [1, 2, 3, 4]...

Process:
  i = 0
    j = 0 → [1] → evens={}, odds={1} → 0≠1 ✗
    j = 1 → [1,2] → evens={2}, odds={1} → 1=1 ✓ len=2
    j = 2 → [1,2,3] → evens={2}, odds={1,3} → 1≠2 ✗
    j = 3 → [1,2,3,4] → evens={2,4}, odds={1,3} → 2=2 ✓ len=4

Result:
  max = 4...

------------------------------------------------------------
Important Observations:
------------------------------------------------------------

  • DISTINCT count matters:
      [2, 2, 2] → distinct evens = {2} → count = 1...

  • Duplicates do NOT increase balance...

  • Subarrays with only evens or only odds
    can NEVER be balanced...

------------------------------------------------------------
Edge Cases Handled:
------------------------------------------------------------

  ✓ Empty array → result = 0...
  ✓ Single element → result = 0...
  ✓ All even numbers → result = 0...
  ✓ All odd numbers → result = 0...
  ✓ Entire array balanced → return full length...

------------------------------------------------------------
Why This Solution Works:
------------------------------------------------------------

  • Tries every possible subarray...
  • Tracks DISTINCT parity counts correctly...
  • Guarantees correctness for all inputs...

  Trade-off:
    → Higher time complexity, but very clear logic...

------------------------------------------------------------
Complexity Analysis:
------------------------------------------------------------

  Time Complexity:
    O(n²)
      • Outer loop runs n times...
      • Inner loop runs up to n times...

  Space Complexity:
    O(n)
      • HashSets can store up to n distinct elements...

------------------------------------------------------------
Optimization Note:
------------------------------------------------------------

  More optimal O(n) solutions exist using:
    → Prefix sums
    → HashMaps

  However:
    This brute-force approach is:
      • Easy to understand...
      • Correct...
      • Suitable for moderate constraints...

------------------------------------------------------------
Final Insight:
------------------------------------------------------------

  This problem is NOT about counting elements...
  It is about counting **unique parity values**...

  HashSet + nested subarray expansion
  gives a clean and reliable solution...

*/

import java.util.HashSet;

public class Longest_Balanced_Subarray_I {

    private static int longestBalanced(int[] numbers) {
        int max = 0;

        for (int i = 0; i < numbers.length; i++) {

            HashSet<Integer> even = new HashSet<>();
            HashSet<Integer> odd = new HashSet<>();

            for (int j = i; j < numbers.length; j++) {
                if (numbers[j] % 2 == 0) {
                    even.add(numbers[j]);
                } else {
                    odd.add(numbers[j]);
                }

                if (even.size() == odd.size()) {
                    max = Math.max(max, j - i + 1);
                }
            }

        }
        return max;

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
        System.out.println("║                LONGEST BALANCED SUBARRAY I                   ║");
        System.out.println("║ Find longest subarray with equal distinct even & odd numbers ║");
        System.out.println("║     Balanced: # of distinct evens = # of distinct odds       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Balanced Array ===");
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println("\nAnalysis:");
        System.out.println("  Even numbers: {2, 4} → 2 distinct");
        System.out.println("  Odd numbers: {1, 3} → 2 distinct");
        System.out.println("  Entire array is balanced!\n");
        System.out.println("Subarray [1, 2, 3, 4]:");
        System.out.println("  Distinct evens: 2 (2, 4)");
        System.out.println("  Distinct odds: 2 (1, 3)");
        System.out.println("  Length: 4\n");

        int result1 = longestBalanced(nums1);
        System.out.println("✓ Longest Balanced Length: " + result1);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result1 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Mixed Array ===");
        int[] nums2 = {2, 3, 4, 5, 6};
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println("\nChecking all subarrays:");
        System.out.println("  [2, 3, 4, 5, 6]: evens={2,4,6}=3, odds={3,5}=2 ✗");
        System.out.println("  [2, 3, 4, 5]: evens={2,4}=2, odds={3,5}=2 ✓ len=4");
        System.out.println("  [3, 4, 5, 6]: evens={4,6}=2, odds={3,5}=2 ✓ len=4");
        System.out.println("  [2, 3]: evens={2}=1, odds={3}=1 ✓ len=2\n");
        System.out.println("Best subarray: [2, 3, 4, 5] or [3, 4, 5, 6]");
        System.out.println("  Length: 4\n");

        int result2 = longestBalanced(nums2);
        System.out.println("✓ Longest Balanced Length: " + result2);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result2 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: All Even Numbers ===");
        int[] nums3 = {2, 4, 6, 8};
        System.out.println("Array: " + arrayToString(nums3));
        System.out.println("\nAnalysis:");
        System.out.println("  All numbers are even");
        System.out.println("  No odd numbers exist");
        System.out.println("  Cannot form balanced subarray\n");
        System.out.println("Every subarray:");
        System.out.println("  Even count > 0, Odd count = 0");
        System.out.println("  Never balanced!\n");

        int result3 = longestBalanced(nums3);
        System.out.println("✓ Longest Balanced Length: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Odd Numbers ===");
        int[] nums4 = {1, 3, 5, 7};
        System.out.println("Array: " + arrayToString(nums4));
        System.out.println("\nAnalysis:");
        System.out.println("  All numbers are odd");
        System.out.println("  No even numbers exist");
        System.out.println("  Cannot form balanced subarray\n");
        System.out.println("Every subarray:");
        System.out.println("  Even count = 0, Odd count > 0");
        System.out.println("  Never balanced!\n");

        int result4 = longestBalanced(nums4);
        System.out.println("✓ Longest Balanced Length: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Duplicate Numbers ===");
        int[] nums5 = {1, 1, 2, 2, 3, 3};
        System.out.println("Array: " + arrayToString(nums5));
        System.out.println("\nImportant: Uses DISTINCT count!");
        System.out.println("  Distinct evens: {2} → 1 unique");
        System.out.println("  Distinct odds: {1, 3} → 2 unique\n");
        System.out.println("Checking key subarrays:");
        System.out.println("  [1, 1, 2]: distinct evens={2}=1, odds={1}=1 ✓ len=3");
        System.out.println("  [1, 2, 2]: distinct evens={2}=1, odds={1}=1 ✓ len=3");
        System.out.println("  [2, 2, 3]: distinct evens={2}=1, odds={3}=1 ✓ len=3");
        System.out.println("  [1, 2]: distinct evens={2}=1, odds={1}=1 ✓ len=2\n");
        System.out.println("Duplicates don't increase distinct count!\n");

        int result5 = longestBalanced(nums5);
        System.out.println("✓ Longest Balanced Length: " + result5);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result5 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Element ===");
        int[] nums6 = {5};
        System.out.println("Array: " + arrayToString(nums6));
        System.out.println("\nAnalysis:");
        System.out.println("  Only one element: 5 (odd)");
        System.out.println("  Evens = 0, Odds = 1");
        System.out.println("  Not balanced\n");

        int result6 = longestBalanced(nums6);
        System.out.println("✓ Longest Balanced Length: " + result6);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Alternating Even-Odd ===");
        int[] nums7 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Array: " + arrayToString(nums7));
        System.out.println("\nPattern: odd, even, odd, even...");
        System.out.println("  Distinct evens: {2, 4, 6, 8} → 4");
        System.out.println("  Distinct odds: {1, 3, 5, 7} → 4\n");
        System.out.println("Full array analysis:");
        System.out.println("  Entire array is perfectly balanced!");
        System.out.println("  Length: 8\n");

        int result7 = longestBalanced(nums7);
        System.out.println("✓ Longest Balanced Length: " + result7);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result7 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Complex Unbalanced Array ===");
        int[] nums8 = {10, 20, 30, 1};
        System.out.println("Array: " + arrayToString(nums8));
        System.out.println("\nAnalysis:");
        System.out.println("  Evens: {10, 20, 30} → 3 distinct");
        System.out.println("  Odds: {1} → 1 distinct\n");
        System.out.println("Checking subarrays:");
        System.out.println("  [10, 20, 30, 1]: evens=3, odds=1 ✗");
        System.out.println("  [10, 20, 30]: evens=3, odds=0 ✗");
        System.out.println("  [20, 30, 1]: evens=2, odds=1 ✗");
        System.out.println("  [30, 1]: evens=1, odds=1 ✓ len=2");
        System.out.println("  [10]: evens=1, odds=0 ✗\n");

        int result8 = longestBalanced(nums8);
        System.out.println("✓ Longest Balanced Length: " + result8);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result8 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Large Distinct Values ===");
        int[] nums9 = {100, 101, 102, 103, 104, 105};
        System.out.println("Array: " + arrayToString(nums9));
        System.out.println("\nValue parity:");
        System.out.println("  100 (even), 101 (odd), 102 (even)");
        System.out.println("  103 (odd), 104 (even), 105 (odd)\n");
        System.out.println("Checking full array:");
        System.out.println("  Distinct evens: {100, 102, 104} = 3");
        System.out.println("  Distinct odds: {101, 103, 105} = 3");
        System.out.println("  Balanced! Length: 6\n");

        int result9 = longestBalanced(nums9);
        System.out.println("✓ Longest Balanced Length: " + result9);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result9 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Repeated Same Number ===");
        int[] nums10 = {2, 2, 2, 2};
        System.out.println("Array: " + arrayToString(nums10));
        System.out.println("\nAnalysis:");
        System.out.println("  All elements are 2 (even)");
        System.out.println("  Distinct evens: {2} = 1");
        System.out.println("  Distinct odds: {} = 0\n");
        System.out.println("Any subarray:");
        System.out.println("  Only contains 2 (same distinct even)");
        System.out.println("  Never balanced!\n");

        int result10 = longestBalanced(nums10);
        System.out.println("✓ Longest Balanced Length: " + result10);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result10 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Find the longest subarray where:                          ║");
        System.out.println("║      # of DISTINCT even numbers = # of DISTINCT odd numbers  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight - DISTINCT Count:                               ║");
        System.out.println("║    • [2, 2, 2, 1] has 1 distinct even (just 2)               ║");
        System.out.println("║    • Not 3 evens! Duplicates count only once                 ║");
        System.out.println("║    • HashSet automatically handles uniqueness                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Brute Force Approach (Current Implementation):              ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Outer loop (i): Starting position of subarray             ║");
        System.out.println("║    Inner loop (j): Ending position of subarray               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For each subarray [i...j]:                                ║");
        System.out.println("║      1. Use two HashSets to track distinct numbers           ║");
        System.out.println("║      2. Check if number is even or odd (n % 2)               ║");
        System.out.println("║      3. Add to appropriate HashSet                           ║");
        System.out.println("║      4. If even.size() == odd.size() → balanced!             ║");
        System.out.println("║      5. Update max length if longer                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Walkthrough: [1, 2, 3, 4]                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=0, j=0: [1] → evens={}, odds={1} → 0≠1 ✗                ║");
        System.out.println("║    i=0, j=1: [1,2] → evens={2}, odds={1} → 1=1 ✓ max=2       ║");
        System.out.println("║    i=0, j=2: [1,2,3] → evens={2}, odds={1,3} → 1≠2 ✗         ║");
        System.out.println("║    i=0, j=3: [1,2,3,4] → evens={2,4}, odds={1,3} → 2=2 ✓     ║");
        System.out.println("║                max=4                                         ║");
        System.out.println("║    i=1, j=1: [2] → evens={2}, odds={} → 1≠0 ✗                ║");
        System.out.println("║    i=1, j=2: [2,3] → evens={2}, odds={3} → 1=1 ✓             ║");
        System.out.println("║    i=1, j=3: [2,3,4] → evens={2,4}, odds={3} → 2≠1 ✗         ║");
        System.out.println("║    ...continue for i=2, i=3                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Final result: max = 4                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Even/Odd Detection:                                         ║");
        System.out.println("║    • n % 2 == 0 → even number                                ║");
        System.out.println("║    • n % 2 != 0 → odd number                                 ║");
        System.out.println("║    • Works for negative numbers too!                         ║");
        System.out.println("║      -4 % 2 = 0 (even), -3 % 2 = -1 (odd in Java)            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why HashSet?                                                ║");
        System.out.println("║    • Automatically prevents duplicates                       ║");
        System.out.println("║    • O(1) add and contains operations                        ║");
        System.out.println("║    • size() gives distinct count directly                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Edge Cases Handled:                                         ║");
        System.out.println("║    ✓ All even numbers → length 0                             ║");
        System.out.println("║    ✓ All odd numbers → length 0                              ║");
        System.out.println("║    ✓ Duplicate values → counted once                         ║");
        System.out.println("║    ✓ Single element → length 0                               ║");
        System.out.println("║    ✓ Entire array balanced → full length                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example with Duplicates: [1, 1, 2, 2]                       ║");
        System.out.println("║    Subarray [1, 1, 2]:                                       ║");
        System.out.println("║      • evens.add(2) → evens = {2}                            ║");
        System.out.println("║      • odds.add(1), odds.add(1) → odds = {1}                 ║");
        System.out.println("║      • size: 1 = 1 ✓ (balanced!)                             ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n²) - Nested loops check all subarrays  ║");
        System.out.println("║  Space Complexity: O(n) - Two HashSets (worst case)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimization Note: More efficient O(n) solutions exist      ║");
        System.out.println("║  using prefix sums and hashmaps, but this brute force        ║");
        System.out.println("║  approach is clear and works well for moderate input sizes.  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

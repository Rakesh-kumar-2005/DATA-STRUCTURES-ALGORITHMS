package Arrays;

/*

    Description:
      Following program separates each integer in an array into its individual digits,
        maintaining left-to-right order across all numbers in the input array...

    Problem Statement:
      -> Given an array of positive integers...
      -> Break each number into its individual digits in left-to-right order...
      -> Concatenate all separated digits into a single result array...
      -> Return the final array of digits...

    Key Insight:
      -> Digit extraction using % and / naturally yields digits right-to-left...
      -> But the required output order is left-to-right...
      -> A Stack (LIFO) reverses the extraction order back to correct order...
      -> Pushing right-to-left and popping gives left-to-right automatically...

    Example:
      -> numbers = [13, 25, 83, 77]:
           13 → [1, 3]...
           25 → [2, 5]...
           83 → [8, 3]...
           77 → [7, 7]...
           Result → [1, 3, 2, 5, 8, 3, 7, 7]...
      -> numbers = [1000, 99]:
           1000 → [1, 0, 0, 0]...
           99   → [9, 9]...
           Result → [1, 0, 0, 0, 9, 9]...

    Why Stack Is Needed:
      -> Modulo extracts digits from rightmost to leftmost...
      -> Example: 123 → extracts 3 first, then 2, then 1...
      -> Stack stores them in that order (3, 2, 1 from bottom to top)...
      -> Popping reverses to 1, 2, 3 → correct left-to-right order...
      -> LIFO property acts as a built-in digit-order corrector...

    Step-by-Step Trace (number = 123):
      -> Push phase (right to left extraction):
           123 % 10 = 3 → push(3), number = 12...
           12  % 10 = 2 → push(2), number = 1...
           1   % 10 = 1 → push(1), number = 0...
           Stack bottom → top: [3, 2, 1]...
      -> Pop phase (left to right restoration):
           pop() → 1, add to list...
           pop() → 2, add to list...
           pop() → 3, add to list...
           List: [1, 2, 3] ← correct order...

    Algorithm Steps:
      -> Initialize an ArrayList to collect all digits across numbers...
      -> For each number in the input array:
           Push all digits onto a Stack using % 10 and /= 10...
           Pop all digits from Stack into the ArrayList...
      -> Convert ArrayList to int[] and return...

    Handling Zeros Inside Numbers:
      -> Loop condition is number != 0, not number > 0...
      -> Zeros in the middle (101 → 1, 0, 1) are extracted normally via %...
      -> Zeros are pushed and popped like any other digit...
      -> No special handling needed for internal zeros...

    ArrayList to int[] Conversion:
      -> Java does not support direct ArrayList<Integer> to int[] cast...
      -> Manually iterate and copy each element by index...
      -> Result array size equals list.size()...

    Alternative Approach:
      -> Convert number to String using Integer.toString(n)...
      -> Iterate each character and subtract '0' to get digit value...
      -> Simpler logic but involves string conversion overhead...
      -> Stack approach is more efficient and avoids string operations...

    Edge Cases:
      -> Single digit numbers → one iteration, stack holds one element → correct...
      -> Numbers with internal zeros (101, 202) → zeros extracted and preserved...
      -> All single digit input → output equals input...
      -> Large numbers (1000) → trailing zeros become internal zeros in result...

    Time and Space Complexity:
      -> Time:  O(n × d) where n = count of numbers, d = average digits per number...
      -> Space: O(n × d) — Stack and ArrayList each hold total digit count...

    Applications:
      -> Digit-level processing in number theory problems...
      -> Building digit frequency maps or histograms...
      -> Flattening numbers for sorting or comparison by digit...
      -> Preprocessing step in competitive programming digit-based problems...

*/

import java.util.ArrayList;
import java.util.Stack;

public class Separate_The_Digits_In_An_Array {

    private static int[] separateDigits(int[] numbers) {

        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int number : numbers) {
            while (number != 0) {
                int rem = number % 10;
                number /= 10;
                stack.push(rem);
            }

            while (! stack.isEmpty()) {
                list.add(stack.pop());
            }
        }

        int[] result = new int[list.size()];
        int idx = 0;
        for (int i : list) {
            result[idx++] = i;
        }

        return result;
    }

    private static String arrayToString(int[] arr) {

        if (arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private static boolean arraysEqual(int[] arr1, int[] arr2) {

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           SEPARATE THE DIGITS IN AN ARRAY                    ║");
        System.out.println("║  Break each number into individual digits left to right      ║");
        System.out.println("║        Return array of all separated digits                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] nums1 = {13, 25, 83, 77};
        System.out.println("Input: " + arrayToString(nums1));
        System.out.println("\nSeparation process:");
        System.out.println("  13 → [1, 3]");
        System.out.println("  25 → [2, 5]");
        System.out.println("  83 → [8, 3]");
        System.out.println("  77 → [7, 7]");
        System.out.println("\nCombined: [1, 3, 2, 5, 8, 3, 7, 7]\n");

        int[] result1 = separateDigits(nums1);
        int[] expected1 = {1, 3, 2, 5, 8, 3, 7, 7};
        System.out.println("✓ Result: " + arrayToString(result1));
        System.out.println("  Expected: " + arrayToString(expected1));
        System.out.println("  Status: " + (arraysEqual(result1, expected1) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Digits ===");
        int[] nums2 = {7, 1, 3, 9};
        System.out.println("Input: " + arrayToString(nums2));
        System.out.println("\nAll numbers are single digits");
        System.out.println("Result: [7, 1, 3, 9] (same as input)\n");

        int[] result2 = separateDigits(nums2);
        int[] expected2 = {7, 1, 3, 9};
        System.out.println("✓ Result: " + arrayToString(result2));
        System.out.println("  Expected: " + arrayToString(expected2));
        System.out.println("  Status: " + (arraysEqual(result2, expected2) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Three-Digit Numbers ===");
        int[] nums3 = {123, 456};
        System.out.println("Input: " + arrayToString(nums3));
        System.out.println("\nSeparation:");
        System.out.println("  123 → [1, 2, 3]");
        System.out.println("  456 → [4, 5, 6]");
        System.out.println("\nCombined: [1, 2, 3, 4, 5, 6]\n");

        int[] result3 = separateDigits(nums3);
        int[] expected3 = {1, 2, 3, 4, 5, 6};
        System.out.println("✓ Result: " + arrayToString(result3));
        System.out.println("  Expected: " + arrayToString(expected3));
        System.out.println("  Status: " + (arraysEqual(result3, expected3) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Mixed Sizes ===");
        int[] nums4 = {1, 23, 4, 567};
        System.out.println("Input: " + arrayToString(nums4));
        System.out.println("\nSeparation:");
        System.out.println("  1 → [1]");
        System.out.println("  23 → [2, 3]");
        System.out.println("  4 → [4]");
        System.out.println("  567 → [5, 6, 7]");
        System.out.println("\nCombined: [1, 2, 3, 4, 5, 6, 7]\n");

        int[] result4 = separateDigits(nums4);
        int[] expected4 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("✓ Result: " + arrayToString(result4));
        System.out.println("  Expected: " + arrayToString(expected4));
        System.out.println("  Status: " + (arraysEqual(result4, expected4) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Large Numbers ===");
        int[] nums5 = {1000, 99};
        System.out.println("Input: " + arrayToString(nums5));
        System.out.println("\nSeparation:");
        System.out.println("  1000 → [1, 0, 0, 0]");
        System.out.println("  99 → [9, 9]");
        System.out.println("\nCombined: [1, 0, 0, 0, 9, 9]\n");

        int[] result5 = separateDigits(nums5);
        int[] expected5 = {1, 0, 0, 0, 9, 9};
        System.out.println("✓ Result: " + arrayToString(result5));
        System.out.println("  Expected: " + arrayToString(expected5));
        System.out.println("  Status: " + (arraysEqual(result5, expected5) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: With Zeros ===");
        int[] nums6 = {101, 202};
        System.out.println("Input: " + arrayToString(nums6));
        System.out.println("\nSeparation:");
        System.out.println("  101 → [1, 0, 1]");
        System.out.println("  202 → [2, 0, 2]");
        System.out.println("\nCombined: [1, 0, 1, 2, 0, 2]\n");

        int[] result6 = separateDigits(nums6);
        int[] expected6 = {1, 0, 1, 2, 0, 2};
        System.out.println("✓ Result: " + arrayToString(result6));
        System.out.println("  Expected: " + arrayToString(expected6));
        System.out.println("  Status: " + (arraysEqual(result6, expected6) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Separate each number into individual digits        ║");
        System.out.println("║           Maintain left-to-right order                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Challenge:                                                  ║");
        System.out.println("║    Extracting digits using % and / gives RIGHT to LEFT       ║");
        System.out.println("║    But we need LEFT to RIGHT order!                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Solution: Use Stack                                         ║");
        System.out.println("║    Stack reverses the order (LIFO property)                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Steps:                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  For each number:                                            ║");
        System.out.println("║    1. Extract digits (right to left):                        ║");
        System.out.println("║       while (number != 0):                                   ║");
        System.out.println("║         digit = number % 10                                  ║");
        System.out.println("║         stack.push(digit)                                    ║");
        System.out.println("║         number /= 10                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║    2. Pop from stack (left to right):                        ║");
        System.out.println("║       while (!stack.isEmpty()):                              ║");
        System.out.println("║         list.add(stack.pop())                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: number = 123                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1 - Extract and Push:                                  ║");
        System.out.println("║    123 % 10 = 3 → push(3), number = 12                       ║");
        System.out.println("║    12 % 10 = 2 → push(2), number = 1                         ║");
        System.out.println("║    1 % 10 = 1 → push(1), number = 0                          ║");
        System.out.println("║    Stack (bottom→top): [3, 2, 1]                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2 - Pop and Add:                                       ║");
        System.out.println("║    pop() → 1, add to list                                    ║");
        System.out.println("║    pop() → 2, add to list                                    ║");
        System.out.println("║    pop() → 3, add to list                                    ║");
        System.out.println("║    List: [1, 2, 3] ← correct order!                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Stack Works:                                            ║");
        System.out.println("║    Extraction order: 3, 2, 1 (right to left)                 ║");
        System.out.println("║    Stack reversal: 1, 2, 3 (left to right)                   ║");
        System.out.println("║    LIFO property reverses the reversed order!                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Alternative Without Stack:                                  ║");
        System.out.println("║    Convert to string, iterate characters                     ║");
        System.out.println("║    Less efficient but simpler logic                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Final Conversion:                                           ║");
        System.out.println("║    ArrayList → int[] array                                   ║");
        System.out.println("║    Copy elements one by one                                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n×d) where n = numbers, d = avg digits           ║");
        System.out.println("║    Space: O(n×d) - Stack and ArrayList storage               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
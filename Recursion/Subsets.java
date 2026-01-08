package Recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {

    private static void getAllSubsets(int index, ArrayList<Integer> temp, int n, int[] numbers, ArrayList<ArrayList<Integer>> result) {

        if (index == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // Include current element
        temp.add(numbers[index]);
        getAllSubsets(index + 1, temp, n, numbers, result);

        // Exclude current element
        temp.removeLast();
        getAllSubsets(index + 1, temp, n, numbers, result);

    }

    private static ArrayList<ArrayList<Integer>> subsets(int[] numbers) {

        int index = 0;
        int n = numbers.length;

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        getAllSubsets(index, temp, n, numbers, result);

        return result;

    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              GENERATE ALL SUBSETS (POWER SET)                ║");
        System.out.println("║       Find all possible subsets using backtracking           ║");
        System.out.println("║   Total subsets = 2^n (include or exclude each element)      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example [1,2,3] ===");
        int[] nums1 = {1, 2, 3};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("\nRecursion Tree (Include/Exclude decision):");
        System.out.println("                    []");
        System.out.println("                   /  \\");
        System.out.println("            [1]          []");
        System.out.println("           /   \\        /   \\");
        System.out.println("      [1,2]   [1]    [2]    []");
        System.out.println("       / \\    / \\    / \\    / \\");
        System.out.println("  [1,2,3][1,2][1,3][1][2,3][2][3][]");
        System.out.println("\nExpected subsets: 2^3 = 8 subsets");

        ArrayList<ArrayList<Integer>> result1 = subsets(nums1);
        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result1.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result1.get(i));
        }
        System.out.println("\nTotal count: " + result1.size());
        System.out.println("Expected: 8");
        System.out.println("Status: " + (result1.size() == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Single Element [5] ===");
        int[] nums2 = {5};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("\nPossible subsets:");
        System.out.println("  1. [] (exclude 5)");
        System.out.println("  2. [5] (include 5)");
        System.out.println("\nExpected: 2^1 = 2 subsets");

        ArrayList<ArrayList<Integer>> result2 = subsets(nums2);
        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result2.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result2.get(i));
        }
        System.out.println("\nTotal count: " + result2.size());
        System.out.println("Expected: 2");
        System.out.println("Status: " + (result2.size() == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Empty Array [] ===");
        int[] nums3 = {};
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("\nEmpty array has only one subset: []");
        System.out.println("Expected: 2^0 = 1 subset");

        ArrayList<ArrayList<Integer>> result3 = subsets(nums3);
        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result3.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result3.get(i));
        }
        System.out.println("\nTotal count: " + result3.size());
        System.out.println("Expected: 1");
        System.out.println("Status: " + (result3.size() == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Two Elements [0,1] ===");
        int[] nums4 = {0, 1};
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("\nDecision tree:");
        System.out.println("  Start: []");
        System.out.println("  ├─ Include 0: [0]");
        System.out.println("  │  ├─ Include 1: [0,1]");
        System.out.println("  │  └─ Exclude 1: [0]");
        System.out.println("  └─ Exclude 0: []");
        System.out.println("     ├─ Include 1: [1]");
        System.out.println("     └─ Exclude 1: []");
        System.out.println("\nExpected: 2^2 = 4 subsets");

        ArrayList<ArrayList<Integer>> result4 = subsets(nums4);
        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result4.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result4.get(i));
        }
        System.out.println("\nTotal count: " + result4.size());
        System.out.println("Expected: 4");
        System.out.println("Status: " + (result4.size() == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Four Elements [1,2,3,4] ===");
        int[] nums5 = {1, 2, 3, 4};
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("\nFor 4 elements, we have 2^4 = 16 subsets");
        System.out.println("Each element can be included or excluded");

        ArrayList<ArrayList<Integer>> result5 = subsets(nums5);
        System.out.println("\n✓ All 16 subsets:");
        for (int i = 0; i < result5.size(); i++) {
            System.out.print("  " + (i + 1) + ". " + result5.get(i));
            if ((i + 1) % 4 == 0) System.out.println();
            else System.out.print(" | ");
        }
        System.out.println("\n\nTotal count: " + result5.size());
        System.out.println("Expected: 16");
        System.out.println("Status: " + (result5.size() == 16 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Negative Numbers [-1,0,1] ===");
        int[] nums6 = {- 1, 0, 1};
        System.out.println("Input: " + Arrays.toString(nums6));
        System.out.println("\nAlgorithm works with any integers (positive, negative, zero)");
        System.out.println("Expected: 2^3 = 8 subsets");

        ArrayList<ArrayList<Integer>> result6 = subsets(nums6);
        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result6.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result6.get(i));
        }
        System.out.println("\nTotal count: " + result6.size());
        System.out.println("Expected: 8");
        System.out.println("Status: " + (result6.size() == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Numbers [10,20,30] ===");
        int[] nums7 = {10, 20, 30};
        System.out.println("Input: " + Arrays.toString(nums7));
        System.out.println("\nSubset size distribution:");

        ArrayList<ArrayList<Integer>> result7 = subsets(nums7);
        int[] sizeCount = new int[4];
        for (ArrayList<Integer> subset : result7) {
            sizeCount[subset.size()]++;
        }

        System.out.println("  Size 0 (empty): " + sizeCount[0] + " subset");
        System.out.println("  Size 1: " + sizeCount[1] + " subsets");
        System.out.println("  Size 2: " + sizeCount[2] + " subsets");
        System.out.println("  Size 3: " + sizeCount[3] + " subset");
        System.out.println("  Pattern: 1, 3, 3, 1 (Pascal's Triangle!)");

        System.out.println("\n✓ All subsets:");
        for (int i = 0; i < result7.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + result7.get(i));
        }
        System.out.println("\nTotal count: " + result7.size());
        System.out.println("Expected: 8");
        System.out.println("Status: " + (result7.size() == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Five Elements [1,2,3,4,5] ===");
        int[] nums8 = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(nums8));
        System.out.println("\nFor 5 elements: 2^5 = 32 subsets");
        System.out.println("Demonstrating exponential growth!");

        ArrayList<ArrayList<Integer>> result8 = subsets(nums8);

        System.out.println("\n✓ Showing first 10 and last 10 subsets:");
        System.out.println("  First 10:");
        for (int i = 0; i < 10 && i < result8.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + result8.get(i));
        }
        System.out.println("  ... (middle subsets omitted) ...");
        System.out.println("  Last 10:");
        for (int i = Math.max(0, result8.size() - 10); i < result8.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + result8.get(i));
        }

        System.out.println("\nTotal count: " + result8.size());
        System.out.println("Expected: 32");
        System.out.println("Status: " + (result8.size() == 32 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY: Backtracking (Include/Exclude)           ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Approach:                                                   ║");
        System.out.println("║  1. At each index, make two choices:                         ║");
        System.out.println("║     a) Include current element in subset                     ║");
        System.out.println("║     b) Exclude current element from subset                   ║");
        System.out.println("║  2. Recursively explore both branches                        ║");
        System.out.println("║  3. Base case: reached end of array → save current subset    ║");
        System.out.println("║  4. Backtrack by removing last added element                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(2^n × n)                                ║");
        System.out.println("║    • 2^n subsets to generate                                 ║");
        System.out.println("║    • O(n) to copy each subset to result                      ║");
        System.out.println("║  Space Complexity: O(n)                                      ║");
        System.out.println("║    • Recursion depth: O(n)                                   ║");
        System.out.println("║    • Temporary list: O(n)                                    ║");
        System.out.println("║    • (Output space O(2^n × n) not counted)                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Key Insights:                                               ║");
        System.out.println("║  • Binary decision tree: 2 choices at each level             ║");
        System.out.println("║  • Total subsets = 2^n (exponential growth)                  ║");
        System.out.println("║  • Subset sizes follow Pascal's Triangle: C(n,k)             ║");
        System.out.println("║  • Also known as Power Set problem                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }
}
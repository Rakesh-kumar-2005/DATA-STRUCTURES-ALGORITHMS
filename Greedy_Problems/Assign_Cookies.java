package Greedy_Problems;

import java.util.Arrays;

public class Assign_Cookies {

    private static int findContentChildren(int[] greedFac, int[] cookies) {
        Arrays.sort(greedFac);
        Arrays.sort(cookies);

        int i = 0;
        int j = 0;

        while (i < greedFac.length && j < cookies.length) {
            if (greedFac[i] <= cookies[j]) {
                i++;
            }
            j++;
        }

        return i;
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ASSIGN COOKIES                            ║");
        System.out.println("║  Maximize number of content children with available cookies  ║");
        System.out.println("║  Cookie size must be >= child's greed factor                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] greed1 = {1, 2, 3};
        int[] cookies1 = {1, 1};
        System.out.println("Greed Factors: " + arrayToString(greed1));
        System.out.println("Cookie Sizes: " + arrayToString(cookies1));
        System.out.println("\nAfter sorting:");
        System.out.println("  Greed: [1, 2, 3]");
        System.out.println("  Cookies: [1, 1]");
        System.out.println("\nAssignment:");
        System.out.println("  Child with greed=1 gets cookie size=1 ✓");
        System.out.println("  Child with greed=2 gets cookie size=1 (not enough) ✗");
        System.out.println("  Result: 1 content child\n");

        int result1 = findContentChildren(greed1, cookies1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result1 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: All Children Content ===");
        int[] greed2 = {1, 2};
        int[] cookies2 = {1, 2, 3};
        System.out.println("Greed Factors: " + arrayToString(greed2));
        System.out.println("Cookie Sizes: " + arrayToString(cookies2));
        System.out.println("\nAssignment:");
        System.out.println("  Child with greed=1 gets cookie size=1 ✓");
        System.out.println("  Child with greed=2 gets cookie size=2 ✓");
        System.out.println("  Cookie size=3 unused");
        System.out.println("  Result: 2 content children\n");

        int result2 = findContentChildren(greed2, cookies2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result2 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: No Cookies ===");
        int[] greed3 = {1, 2, 3};
        int[] cookies3 = {};
        System.out.println("Greed Factors: " + arrayToString(greed3));
        System.out.println("Cookie Sizes: " + arrayToString(cookies3));
        System.out.println("\nNo cookies available");
        System.out.println("Result: 0 content children\n");

        int result3 = findContentChildren(greed3, cookies3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: No Children ===");
        int[] greed4 = {};
        int[] cookies4 = {1, 2, 3};
        System.out.println("Greed Factors: " + arrayToString(greed4));
        System.out.println("Cookie Sizes: " + arrayToString(cookies4));
        System.out.println("\nNo children to satisfy");
        System.out.println("Result: 0 content children\n");

        int result4 = findContentChildren(greed4, cookies4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Cookies Too Small ===");
        int[] greed5 = {10, 9, 8, 7};
        int[] cookies5 = {5, 6, 7, 8};
        System.out.println("Greed Factors: " + arrayToString(greed5));
        System.out.println("Cookie Sizes: " + arrayToString(cookies5));
        System.out.println("\nAfter sorting:");
        System.out.println("  Greed: [7, 8, 9, 10]");
        System.out.println("  Cookies: [5, 6, 7, 8]");
        System.out.println("\nAssignment:");
        System.out.println("  Child greed=7 gets cookie=7 ✓");
        System.out.println("  Child greed=8 gets cookie=8 ✓");
        System.out.println("  Child greed=9 cannot be satisfied ✗");
        System.out.println("  Result: 2 content children\n");

        int result5 = findContentChildren(greed5, cookies5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result5 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Perfect Match ===");
        int[] greed6 = {1, 2, 3};
        int[] cookies6 = {1, 2, 3};
        System.out.println("Greed Factors: " + arrayToString(greed6));
        System.out.println("Cookie Sizes: " + arrayToString(cookies6));
        System.out.println("\nPerfect 1-to-1 match");
        System.out.println("All children content\n");

        int result6 = findContentChildren(greed6, cookies6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result6 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Cookies ===");
        int[] greed7 = {1, 2, 7, 15};
        int[] cookies7 = {1, 3, 5, 100};
        System.out.println("Greed Factors: " + arrayToString(greed7));
        System.out.println("Cookie Sizes: " + arrayToString(cookies7));
        System.out.println("\nAssignment:");
        System.out.println("  Child greed=1 gets cookie=1 ✓");
        System.out.println("  Child greed=2 gets cookie=3 ✓");
        System.out.println("  Child greed=7 gets cookie=100 ✓");
        System.out.println("  Child greed=15 cannot be satisfied ✗");
        System.out.println("  Result: 3 content children\n");

        int result7 = findContentChildren(greed7, cookies7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result7 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Maximize content children with available cookies   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Constraint: cookie[j] >= greed[i] to satisfy child i        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Greedy Strategy:                                            ║");
        System.out.println("║    1. Sort both arrays (children by greed, cookies by size)  ║");
        System.out.println("║    2. Use two pointers starting from smallest values         ║");
        System.out.println("║    3. Try to satisfy least greedy child first                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Greedy Works:                                           ║");
        System.out.println("║    • Satisfying a child with smallest cookie possible        ║");
        System.out.println("║      preserves larger cookies for greedier children          ║");
        System.out.println("║    • Starting with least greedy child maximizes chances      ║");
        System.out.println("║      of satisfying more children overall                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    i = child pointer, j = cookie pointer                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    while (i < children && j < cookies):                      ║");
        System.out.println("║      if (greed[i] <= cookie[j]):                             ║");
        System.out.println("║        i++  // Child satisfied, move to next child           ║");
        System.out.println("║      j++    // Always move to next cookie                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║    return i  // Number of satisfied children                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: greed=[1,2,3], cookies=[1,1]                       ║");
        System.out.println("║    Step 1: greed[0]=1 <= cookie[0]=1 → satisfy, i=1, j=1     ║");
        System.out.println("║    Step 2: greed[1]=2 > cookie[1]=1 → skip, i=1, j=2         ║");
        System.out.println("║    Step 3: j=2 >= cookies.length → stop                      ║");
        System.out.println("║    Result: i=1 (1 child satisfied)                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Points:                                                 ║");
        System.out.println("║    • Cookie pointer always advances (use or discard)         ║");
        System.out.println("║    • Child pointer only advances when satisfied              ║");
        System.out.println("║    • Final value of i = number of content children           ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n log n + m log m) - Sorting both arrays         ║");
        System.out.println("║    Space: O(1) - Two pointers only (excluding sort space)    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
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

}

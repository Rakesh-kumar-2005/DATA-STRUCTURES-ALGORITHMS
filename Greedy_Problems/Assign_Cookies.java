package Greedy_Problems;

/*

    Description:
      Following program maximizes the number of content children by assigning
        cookies to children based on their greed factors using a greedy approach...
    
    Problem Statement:
      -> Given an array greedFac[] where greedFac[i] is the minimum cookie size
           that satisfies child i...
      -> Given an array cookies[] where cookies[j] is the size of cookie j...
      -> Each child can receive at most one cookie...
      -> A child is content if assigned a cookie of size >= their greed factor...
      -> Return the maximum number of content children...
    
    Constraint:
      -> cookies[j] >= greedFac[i] to satisfy child i with cookie j...
      -> Each cookie can be assigned to at most one child...
      -> Goal is to maximize the count of satisfied children...
    
    Core Insight - Greedy Strategy:
      -> Sort both arrays in ascending order...
      -> Always try to satisfy the least greedy child first...
      -> For each child, use the smallest cookie that can satisfy them...
      -> This preserves larger cookies for greedier children...
      -> Greedy choice at each step leads to global optimum...
    
    Why Greedy Works Here:
      -> Satisfying a greedier child with a smaller cookie wastes potential...
      -> If a small cookie can satisfy a less greedy child, always do it...
      -> Skipping a cookie that satisfies the current child can never help...
        (a larger cookie would also satisfy them, but wastes more)...
      -> Sorting + two pointer approach ensures minimum waste at every step...
    
    Algorithm - Two Pointer Approach:
      -> Step 1: Sort greedFac[] in ascending order...
      -> Step 2: Sort cookies[] in ascending order...
      -> Step 3: Initialize two pointers i = 0 (children), j = 0 (cookies)...
      -> Step 4: While i < greedFac.length AND j < cookies.length:
           If greedFac[i] <= cookies[j]:
             Child i is satisfied → increment i (move to next child)...
           Always increment j (move to next cookie regardless)...
      -> Step 5: Return i (total number of satisfied children)...
    
    Why j Always Increments:
      -> If cookies[j] satisfies child i → assign it, move both pointers...
      -> If cookies[j] cannot satisfy child i → this cookie is too small...
           No point assigning it to any child (all remaining are greedier)...
           Discard the cookie by moving j forward...
      -> Cookie pointer always advances; child pointer only on satisfaction...
    
    Example Walkthrough:
      -> greedFac = [1, 2, 3], cookies = [1, 1]:
           After sorting: greed = [1, 2, 3], cookies = [1, 1]...
    
           Step 1: greed[0]=1 <= cookie[0]=1 → satisfy, i=1, j=1...
           Step 2: greed[1]=2 >  cookie[1]=1 → discard, i=1, j=2...
           Step 3: j=2 >= cookies.length → loop ends...
           Result: i = 1 (one child satisfied)...
    
      -> greedFac = [1, 2], cookies = [1, 2, 3]:
           After sorting: greed = [1, 2], cookies = [1, 2, 3]...
    
           Step 1: greed[0]=1 <= cookie[0]=1 → satisfy, i=1, j=1...
           Step 2: greed[1]=2 <= cookie[1]=2 → satisfy, i=2, j=2...
           Step 3: i=2 >= greedFac.length → loop ends...
           Result: i = 2 (both children satisfied)...
    
    Pointer Movement Summary:
      -> Cookie satisfies child:
           i++ (child pointer advances)...
           j++ (cookie pointer always advances)...
      -> Cookie does not satisfy child:
           j++ only (cookie discarded, child pointer stays)...
      -> Final value of i = total satisfied children...
    
    Sorting Justification:
      -> Without sorting, a large cookie might be wasted on a low-greed child...
      -> Sorting ensures smallest valid cookie is always tried first for each child...
      -> Ascending sort on both arrays aligns minimum satisfiable pairs...
      -> This is the canonical greedy matching technique...
    
    Comparison With Brute Force:
      -> Brute force: try all cookie-to-child assignments → O(n! × m!) time...
      -> Greedy: sort and scan once → O(n log n + m log m) time...
      -> Greedy provably optimal: no better assignment exists after sorting...
    
    Edge Cases:
      -> Empty cookies array → no child can be satisfied → return 0...
      -> Empty greedFac array → no children → return 0...
      -> All cookies too small for all children → return 0...
      -> All children satisfied → return greedFac.length...
      -> More cookies than children → some cookies remain unused → return greedFac.length...
      -> Perfect 1-to-1 match → every child gets exactly their minimum cookie...
    
    Patterns & Observations:
      -> If max(cookies) < min(greedFac) → return 0 (quick check possible)...
      -> If sum of all cookies >= sum of all greed factors, all may be satisfied...
      -> Duplicate greed factors handled naturally by sorted traversal...
      -> Large cookie satisfying low-greed child is never optimal (greedy avoids this)...
    
    Time and Space Complexity:
      -> Time:  O(n log n + m log m)...
           Sorting greedFac takes O(n log n)...
           Sorting cookies takes O(m log m)...
           Two pointer scan takes O(n + m)...
           Dominated by sorting: O(n log n + m log m)...
      -> Space: O(1)...
           Only two integer pointers i and j used...
           Sorting done in-place (Arrays.sort uses O(log n) stack space)...
    
    Applications:
      -> Resource allocation with minimum requirements...
      -> Task scheduling with priority and resource constraints...
      -> Matching problems where smaller resources satisfy smaller demands...
      -> Greedy interval and assignment problems in competitive programming...

*/

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

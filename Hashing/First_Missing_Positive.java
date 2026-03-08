package Hashing;

import java.util.HashSet;


public class First_Missing_Positive {
        
    private int firstMissingPositive(int[] arr) {
        HashSet<Integer> st = new HashSet<>();
        for (int i : arr) {
            st.add(i);
        }

        int ans = 1;
        while (st.contains(ans)) {
            ans++;
        }
        return ans;
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

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              FIRST MISSING POSITIVE                          ║");
        System.out.println("║  Find the smallest positive integer (≥1) missing from array  ║");
        System.out.println("║  Using HashSet for O(1) lookup                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    
        First_Missing_Positive solver = new First_Missing_Positive();
    
        System.out.println("=== Test Case 1: Basic Example ===");
        int[] arr1 = {1, 2, 0};
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 1 ✓, 2 ✓, 0 (ignored, not positive)");
        System.out.println("  Missing: 3 ← first missing positive\n");
        
        int result1 = solver.firstMissingPositive(arr1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 2: Consecutive from 1 ===");
        int[] arr2 = {3, 4, -1, 1};
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 1 ✓, 3 ✓, 4 ✓, -1 (ignored)");
        System.out.println("  Missing: 2 ← first gap in sequence\n");
        
        int result2 = solver.firstMissingPositive(arr2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result2 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 3: Perfect Sequence ===");
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 1 ✓, 2 ✓, 3 ✓, 4 ✓, 5 ✓");
        System.out.println("  All consecutive from 1");
        System.out.println("  Missing: 6 ← next in sequence\n");
        
        int result3 = solver.firstMissingPositive(arr3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result3 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 4: All Negative ===");
        int[] arr4 = {-1, -2, -3};
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("\nAnalysis:");
        System.out.println("  No positive numbers present");
        System.out.println("  Missing: 1 ← smallest positive\n");
        
        int result4 = solver.firstMissingPositive(arr4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 5: Missing 1 ===");
        int[] arr5 = {2, 3, 4};
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 2 ✓, 3 ✓, 4 ✓");
        System.out.println("  Missing: 1 ← not in array\n");
        
        int result5 = solver.firstMissingPositive(arr5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 6: Large Gap ===");
        int[] arr6 = {1, 1000};
        System.out.println("Array: " + arrayToString(arr6));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 1 ✓, 1000 ✓");
        System.out.println("  Large gap between numbers");
        System.out.println("  Missing: 2 ← first in gap\n");
        
        int result6 = solver.firstMissingPositive(arr6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result6 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 7: Duplicates ===");
        int[] arr7 = {1, 1, 2, 2, 3, 3};
        System.out.println("Array: " + arrayToString(arr7));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 1 ✓, 2 ✓, 3 ✓ (duplicates ignored)");
        System.out.println("  HashSet removes duplicates automatically");
        System.out.println("  Missing: 4\n");
        
        int result7 = solver.firstMissingPositive(arr7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result7 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 8: Mixed Values ===");
        int[] arr8 = {7, 8, 9, 11, 12};
        System.out.println("Array: " + arrayToString(arr8));
        System.out.println("\nAnalysis:");
        System.out.println("  Present: 7, 8, 9, 11, 12");
        System.out.println("  Missing from start: 1, 2, 3, 4, 5, 6...");
        System.out.println("  First missing: 1\n");
        
        int result8 = solver.firstMissingPositive(arr8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result8 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find smallest positive integer not in array        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  HashSet Approach:                                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Build HashSet                                       ║");
        System.out.println("║    Add all array elements to set                             ║");
        System.out.println("║    O(1) insertion, automatic duplicate handling              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: Find First Missing                                  ║");
        System.out.println("║    Start from ans = 1                                        ║");
        System.out.println("║    while (set.contains(ans)): ans++                          ║");
        System.out.println("║    Return first ans not in set                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Start at 1?                                             ║");
        System.out.println("║    Problem asks for positive integers (≥1)                   ║");
        System.out.println("║    Zero and negatives are ignored                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: [3, 4, -1, 1]                                      ║");
        System.out.println("║    Set: {3, 4, -1, 1}                                        ║");
        System.out.println("║    Check ans=1: in set → ans=2                               ║");
        System.out.println("║    Check ans=2: NOT in set → return 2                        ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Build set + find missing                    ║");
        System.out.println("║    Space: O(n) - HashSet storage                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
 
}

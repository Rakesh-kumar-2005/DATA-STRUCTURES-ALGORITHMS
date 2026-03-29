package String;

/*

    Description:
      Following program checks whether two strings of length 4 can be made equal
        by performing a specific set of swap operations any number of times...
    
    Problem Statement:
      -> Given two strings s1 and s2, each of fixed length 4...
      -> Allowed operations (can be applied any number of times):
           • Swap characters at indices 0 and 2 (even positions)...
           • Swap characters at indices 1 and 3 (odd positions)...
      -> Return true if s1 can be transformed into s2, false otherwise...
    
    Allowed Operations Visualized:
      -> s1 = "abcd":
           Swap indices 0 and 2 → "cbad"...
           Swap indices 1 and 3 → "adcb"...
           Both swaps applied   → "cdab"...
      -> Swapping the same pair twice returns to original state...
      -> So only 2 states exist per pair: original or swapped...
    
    Key Insight - Independence of Even and Odd Positions:
      -> Index 0 can only interact with index 2 (both even)...
      -> Index 1 can only interact with index 3 (both odd)...
      -> Even and odd positions are completely independent...
      -> Solve each group separately and combine with AND...
    
    Core Logic:
      -> For even indices (0 and 2):
           Check if the pair {s1[0], s1[2]} matches {s2[0], s2[2]}...
           Case 1 (no swap): s1[0] == s2[0] AND s1[2] == s2[2]...
           Case 2 (swapped): s1[0] == s2[2] AND s1[2] == s2[0]...
           Even condition = Case 1 OR Case 2...
    
      -> For odd indices (1 and 3):
           Check if the pair {s1[1], s1[3]} matches {s2[1], s2[3]}...
           Case 1 (no swap): s1[1] == s2[1] AND s1[3] == s2[3]...
           Case 2 (swapped): s1[1] == s2[3] AND s1[3] == s2[1]...
           Odd condition = Case 1 OR Case 2...
    
      -> Final answer = even condition AND odd condition...
    
    Why Only Two Cases Per Pair?
      -> Swapping index 0 and 2 twice returns s1 to its original state...
      -> Repeating the same swap operation is idempotent (toggle behavior)...
      -> No matter how many times operations are applied:
           Even pair is either original {s1[0], s1[2]} or swapped {s1[2], s1[0]}...
           Odd pair is either original {s1[1], s1[3]} or swapped {s1[3], s1[1]}...
      -> Total configurations: 2 × 2 = 4 possible states for s1...
      -> Check if any of the 4 states equals s2...
    
    Solution Code Logic:
      -> return (
              (s1[0] == s2[0] && s1[2] == s2[2]) ||   // even: no swap...
               (s1[0] == s2[2] && s1[2] == s2[0])      // even: swapped...
             )
             &&
             (
              (s1[1] == s2[1] && s1[3] == s2[3]) ||   // odd: no swap...
               (s1[1] == s2[3] && s1[3] == s2[1])      // odd: swapped...
             )...
    
    Example Walkthroughs:
      -> s1 = "abcd", s2 = "abcd" (already equal):
           Even: s1[0]='a'==s2[0]='a', s1[2]='c'==s2[2]='c' → Case 1 ✓...
           Odd:  s1[1]='b'==s2[1]='b', s1[3]='d'==s2[3]='d' → Case 1 ✓...
           Result: true...
    
      -> s1 = "abcd", s2 = "cbad" (even swap only):
           Even: s1[0]='a'==s2[2]='a', s1[2]='c'==s2[0]='c' → Case 2 ✓...
           Odd:  s1[1]='b'==s2[1]='b', s1[3]='d'==s2[3]='d' → Case 1 ✓...
           Result: true...
    
      -> s1 = "abcd", s2 = "cdab" (both swaps):
           Even: s1[0]='a'==s2[2]='a', s1[2]='c'==s2[0]='c' → Case 2 ✓...
           Odd:  s1[1]='b'==s2[3]='b', s1[3]='d'==s2[1]='d' → Case 2 ✓...
           Result: true...
    
      -> s1 = "abcd", s2 = "dacb" (impossible):
           Even: s1[0]='a' != s2[0]='d' and s1[0]='a' != s2[2]='c' → ✗...
           Result: false...
    
    Constraint That Enables This:
      -> Fixed string length of 4 makes all index checks constant...
      -> Only positions 0, 1, 2, 3 exist → no loop required...
      -> Entire solution compresses to a single boolean expression...
    
    Edge Cases:
      -> s1 == s2 → both Case 1 conditions pass trivially → return true...
      -> All characters same (e.g., "aaaa") → any swap yields same string → true...
      -> No matching pair in either group → return false...
      -> s1 and s2 are anagrams but characters in wrong groups → return false...
           Example: s1 = "abcd", s2 = "bacd":
             Even: s1[0]='a' vs s2[0]='b' mismatch in both cases → false...
    
    Why Greedy or Brute Force Is Unnecessary:
      -> Brute force would simulate all swap sequences → infinite loop risk...
      -> Since each pair toggles between only 2 states, we check at most 4 combinations...
      -> No simulation needed; direct character comparison suffices...
    
    Generalization Insight:
      -> For a string of length n, swapping i and j creates two independent groups...
      -> Each group can only reach its own permutations via swaps...
      -> Equality holds iff each group's multiset matches between s1 and s2...
      -> For length 4 with only pairs (0,2) and (1,3): just match sorted pairs...
    
    Time and Space Complexity:
      -> Time:  O(1)...
           Fixed 4 character comparisons, no loops or recursion...
      -> Space: O(1)...
           No extra arrays or data structures used...
    
    Applications:
      -> String transformation problems with constrained swaps...
      -> Permutation reachability under restricted operations...
      -> Puzzle and game state equivalence checks...
      -> Interview problems on string manipulation and invariants...

*/

public class Check_If_Strings_Can_Be_Made_Equal_With_Operations_I {

    private static boolean canBeEqual(String s1, String s2) {
        return (
            (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0)) &&
            (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3) ||
                s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  CHECK IF STRINGS CAN BE MADE EQUAL WITH OPERATIONS I        ║");
        System.out.println("║  Can swap characters at indices (0,2) or (1,3) any times     ║");
        System.out.println("║  String length is always 4                                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Already Equal ===");
        String s1_1 = "abcd";
        String s2_1 = "abcd";
        System.out.println("s1: \"" + s1_1 + "\"");
        System.out.println("s2: \"" + s2_1 + "\"");
        System.out.println("\nStrings already equal");
        System.out.println("No swaps needed\n");

        boolean result1 = canBeEqual(s1_1, s2_1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Swap Position 0 and 2 ===");
        String s1_2 = "abcd";
        String s2_2 = "cbad";
        System.out.println("s1: \"" + s1_2 + "\"");
        System.out.println("s2: \"" + s2_2 + "\"");
        System.out.println("\nAnalysis:");
        System.out.println("  Even indices (0,2): 'a','c' vs 'c','a' → swapped ✓");
        System.out.println("  Odd indices (1,3): 'b','d' vs 'b','d' → same ✓");
        System.out.println("\nCan be made equal!\n");

        boolean result2 = canBeEqual(s1_2, s2_2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result2 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Swap Position 1 and 3 ===");
        String s1_3 = "abcd";
        String s2_3 = "adcb";
        System.out.println("s1: \"" + s1_3 + "\"");
        System.out.println("s2: \"" + s2_3 + "\"");
        System.out.println("\nAnalysis:");
        System.out.println("  Even indices (0,2): 'a','c' vs 'a','c' → same ✓");
        System.out.println("  Odd indices (1,3): 'b','d' vs 'd','b' → swapped ✓");
        System.out.println("\nCan be made equal!\n");

        boolean result3 = canBeEqual(s1_3, s2_3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result3 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Both Swaps Needed ===");
        String s1_4 = "abcd";
        String s2_4 = "cdab";
        System.out.println("s1: \"" + s1_4 + "\"");
        System.out.println("s2: \"" + s2_4 + "\"");
        System.out.println("\nAnalysis:");
        System.out.println("  Even indices (0,2): 'a','c' vs 'c','a' → swapped ✓");
        System.out.println("  Odd indices (1,3): 'b','d' vs 'd','b' → swapped ✓");
        System.out.println("\nCan be made equal!\n");

        boolean result4 = canBeEqual(s1_4, s2_4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result4 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Cannot Be Equal ===");
        String s1_5 = "abcd";
        String s2_5 = "dacb";
        System.out.println("s1: \"" + s1_5 + "\"");
        System.out.println("s2: \"" + s2_5 + "\"");
        System.out.println("\nAnalysis:");
        System.out.println("  Even indices (0,2): 'a','c' vs 'd','c'");
        System.out.println("    Neither 'a'='d' nor 'a'='c' or 'c'='d' ✗");
        System.out.println("\nCannot be made equal!\n");

        boolean result5 = canBeEqual(s1_5, s2_5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result5 == false ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Different Characters ===");
        String s1_6 = "bnxw";
        String s2_6 = "bwxn";
        System.out.println("s1: \"" + s1_6 + "\"");
        System.out.println("s2: \"" + s2_6 + "\"");
        System.out.println("\nAnalysis:");
        System.out.println("  Even indices (0,2): 'b','x' vs 'b','x' → same ✓");
        System.out.println("  Odd indices (1,3): 'n','w' vs 'w','n' → swapped ✓");
        System.out.println("\nCan be made equal!\n");

        boolean result6 = canBeEqual(s1_6, s2_6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result6 == true ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Check if s1 can become s2 with allowed swaps       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Allowed Operations:                                         ║");
        System.out.println("║    • Swap indices 0 and 2 (even positions)                   ║");
        System.out.println("║    • Swap indices 1 and 3 (odd positions)                    ║");
        System.out.println("║    • Can perform operations any number of times              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    Even and odd positions are independent!                   ║");
        System.out.println("║    Characters at even indices can only swap with each other  ║");
        System.out.println("║    Characters at odd indices can only swap with each other   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Solution:                                                   ║");
        System.out.println("║    For even indices (0,2):                                   ║");
        System.out.println("║      Check if {s1[0], s1[2]} equals {s2[0], s2[2]}           ║");
        System.out.println("║      Either: s1[0]=s2[0] AND s1[2]=s2[2] (no swap)           ║");
        System.out.println("║      Or: s1[0]=s2[2] AND s1[2]=s2[0] (swapped)               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For odd indices (1,3):                                    ║");
        System.out.println("║      Check if {s1[1], s1[3]} equals {s2[1], s2[3]}           ║");
        System.out.println("║      Either: s1[1]=s2[1] AND s1[3]=s2[3] (no swap)           ║");
        System.out.println("║      Or: s1[1]=s2[3] AND s1[3]=s2[1] (swapped)               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Return: (even condition) AND (odd condition)              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: s1=\"abcd\", s2=\"cdab\"                               ║");
        System.out.println("║    Even: 'a','c' vs 'c','a' → swapped ✓                      ║");
        System.out.println("║    Odd: 'b','d' vs 'd','b' → swapped ✓                       ║");
        System.out.println("║    Result: true                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Works:                                             ║");
        System.out.println("║    Swapping same pair multiple times returns to original     ║");
        System.out.println("║    So only 2 states possible: original or swapped            ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(1) - Fixed 4 character comparisons               ║");
        System.out.println("║    Space: O(1) - No extra space needed                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

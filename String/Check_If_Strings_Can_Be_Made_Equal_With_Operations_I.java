package String;

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
package Math_Problems;

public class Binary_Number_With_Alternating_Bits {

    private static boolean hasAlternatingBits(int n) {

        String number = Integer.toBinaryString(n);

        for (int i = 1; i < number.length(); i++) {
            if (number.charAt(i) == number.charAt(i - 1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         BINARY NUMBER WITH ALTERNATING BITS                  ║");
        System.out.println("║  Check if binary representation has alternating 0s and 1s    ║");
        System.out.println("║  Valid: 101, 1010, 10101 | Invalid: 100, 111, 1001           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Perfect Alternating - 5 ===");
        int num1 = 5;
        String binary1 = Integer.toBinaryString(num1);
        System.out.println("Input: " + num1);
        System.out.println("Binary: " + binary1);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 1");
        System.out.println("  Position 1: 0");
        System.out.println("  Position 2: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  1 → 0 (different ✓)");
        System.out.println("  0 → 1 (different ✓)");
        System.out.println("\nConclusion: Alternating pattern!\n");

        boolean result1 = hasAlternatingBits(num1);
        System.out.println("✓ Has Alternating Bits: " + result1);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Non-Alternating - 7 ===");
        int num2 = 7;
        String binary2 = Integer.toBinaryString(num2);
        System.out.println("Input: " + num2);
        System.out.println("Binary: " + binary2);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 1");
        System.out.println("  Position 1: 1  ← consecutive 1s!");
        System.out.println("  Position 2: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  1 → 1 (same ✗) FAIL!");
        System.out.println("\nConclusion: NOT alternating!\n");

        boolean result2 = hasAlternatingBits(num2);
        System.out.println("✓ Has Alternating Bits: " + result2);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (! result2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Bit - 1 ===");
        int num3 = 1;
        String binary3 = Integer.toBinaryString(num3);
        System.out.println("Input: " + num3);
        System.out.println("Binary: " + binary3);
        System.out.println("\nBit Analysis:");
        System.out.println("  Only one bit: 1");
        System.out.println("  No adjacent bits to compare");
        System.out.println("\nPattern Check:");
        System.out.println("  No pairs to check");
        System.out.println("\nConclusion: Vacuously true (alternating)!\n");

        boolean result3 = hasAlternatingBits(num3);
        System.out.println("✓ Has Alternating Bits: " + result3);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Two Bits - 2 ===");
        int num4 = 2;
        String binary4 = Integer.toBinaryString(num4);
        System.out.println("Input: " + num4);
        System.out.println("Binary: " + binary4);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 0");
        System.out.println("  Position 1: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  0 → 1 (different ✓)");
        System.out.println("\nConclusion: Alternating!\n");

        boolean result4 = hasAlternatingBits(num4);
        System.out.println("✓ Has Alternating Bits: " + result4);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Longer Alternating - 10 ===");
        int num5 = 10;
        String binary5 = Integer.toBinaryString(num5);
        System.out.println("Input: " + num5);
        System.out.println("Binary: " + binary5);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 0");
        System.out.println("  Position 1: 1");
        System.out.println("  Position 2: 0");
        System.out.println("  Position 3: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  0 → 1 (different ✓)");
        System.out.println("  1 → 0 (different ✓)");
        System.out.println("  0 → 1 (different ✓)");
        System.out.println("\nConclusion: Perfect alternating!\n");

        boolean result5 = hasAlternatingBits(num5);
        System.out.println("✓ Has Alternating Bits: " + result5);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Non-Alternating - 4 ===");
        int num6 = 4;
        String binary6 = Integer.toBinaryString(num6);
        System.out.println("Input: " + num6);
        System.out.println("Binary: " + binary6);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 0");
        System.out.println("  Position 1: 0  ← consecutive 0s!");
        System.out.println("  Position 2: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  0 → 0 (same ✗) FAIL!");
        System.out.println("\nConclusion: NOT alternating!\n");

        boolean result6 = hasAlternatingBits(num6);
        System.out.println("✓ Has Alternating Bits: " + result6);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (! result6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Large Alternating - 21 ===");
        int num7 = 21;
        String binary7 = Integer.toBinaryString(num7);
        System.out.println("Input: " + num7);
        System.out.println("Binary: " + binary7);
        System.out.println("\nBit Analysis:");
        System.out.println("  " + binary7 + " (left to right)");
        System.out.println("  1 → 0 → 1 → 0 → 1");
        System.out.println("\nPattern Check:");
        System.out.println("  All adjacent bits differ ✓");
        System.out.println("\nConclusion: Alternating!\n");

        boolean result7 = hasAlternatingBits(num7);
        System.out.println("✓ Has Alternating Bits: " + result7);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Non-Alternating - 11 ===");
        int num8 = 11;
        String binary8 = Integer.toBinaryString(num8);
        System.out.println("Input: " + num8);
        System.out.println("Binary: " + binary8);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 1");
        System.out.println("  Position 1: 1  ← consecutive 1s!");
        System.out.println("  Position 2: 0");
        System.out.println("  Position 3: 1");
        System.out.println("\nPattern Check:");
        System.out.println("  1 → 1 (same ✗) FAIL at position 1!");
        System.out.println("\nConclusion: NOT alternating!\n");

        boolean result8 = hasAlternatingBits(num8);
        System.out.println("✓ Has Alternating Bits: " + result8);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (! result8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Edge Case - 42 ===");
        int num9 = 42;
        String binary9 = Integer.toBinaryString(num9);
        System.out.println("Input: " + num9);
        System.out.println("Binary: " + binary9);
        System.out.println("\nBit Analysis:");
        System.out.println("  " + binary9);
        System.out.println("  1 0 1 0 1 0");
        System.out.println("\nPattern Check:");
        System.out.println("  1 → 0 ✓");
        System.out.println("  0 → 1 ✓");
        System.out.println("  1 → 0 ✓");
        System.out.println("  0 → 1 ✓");
        System.out.println("  1 → 0 ✓");
        System.out.println("\nConclusion: Perfect alternating!\n");

        boolean result9 = hasAlternatingBits(num9);
        System.out.println("✓ Has Alternating Bits: " + result9);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Triple Ones - 14 ===");
        int num10 = 14;
        String binary10 = Integer.toBinaryString(num10);
        System.out.println("Input: " + num10);
        System.out.println("Binary: " + binary10);
        System.out.println("\nBit Analysis:");
        System.out.println("  Position 0: 0");
        System.out.println("  Position 1: 1");
        System.out.println("  Position 2: 1  ← consecutive 1s!");
        System.out.println("  Position 3: 1  ← consecutive 1s!");
        System.out.println("\nPattern Check:");
        System.out.println("  0 → 1 ✓");
        System.out.println("  1 → 1 ✗ FAIL!");
        System.out.println("\nConclusion: NOT alternating!\n");

        boolean result10 = hasAlternatingBits(num10);
        System.out.println("✓ Has Alternating Bits: " + result10);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (! result10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Determine if binary representation has alternating bits   ║");
        System.out.println("║    • No two adjacent bits should be the same                 ║");
        System.out.println("║    • Pattern: ...010101... or ...101010...                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  String-Based Approach (Current Implementation):             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 1: Convert to Binary String                            ║");
        System.out.println("║    Integer.toBinaryString(n)                                 ║");
        System.out.println("║    • Converts integer to binary string representation        ║");
        System.out.println("║    • Example: 5 → \"101\", 10 → \"1010\"                         ║");
        System.out.println("║    • No leading zeros (minimal representation)               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 2: Compare Adjacent Characters                         ║");
        System.out.println("║    for (int i = 1; i < number.length(); i++)                 ║");
        System.out.println("║      if (number.charAt(i) == number.charAt(i-1))             ║");
        System.out.println("║        return false                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    • Start from index 1 (compare with previous)              ║");
        System.out.println("║    • charAt(i) gets character at position i                  ║");
        System.out.println("║    • If any two adjacent chars match → not alternating       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Walkthrough: n = 5 (binary: \"101\")                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    String: \"101\"                                             ║");
        System.out.println("║    Indices: 0 1 2                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=1: Compare charAt(1)='0' with charAt(0)='1'             ║");
        System.out.println("║          '0' ≠ '1' ✓ Continue                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=2: Compare charAt(2)='1' with charAt(1)='0'             ║");
        System.out.println("║          '1' ≠ '0' ✓ Continue                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    All checks passed → return true                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Counter-Example: n = 7 (binary: \"111\")                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    String: \"111\"                                             ║");
        System.out.println("║    Indices: 0 1 2                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=1: Compare charAt(1)='1' with charAt(0)='1'             ║");
        System.out.println("║          '1' == '1' ✗ Return false immediately!              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Valid Alternating Patterns:                                 ║");
        System.out.println("║    1 (1) ✓                                                   ║");
        System.out.println("║    2 (10) ✓                                                  ║");
        System.out.println("║    5 (101) ✓                                                 ║");
        System.out.println("║    10 (1010) ✓                                               ║");
        System.out.println("║    21 (10101) ✓                                              ║");
        System.out.println("║    42 (101010) ✓                                             ║");
        System.out.println("║    85 (1010101) ✓                                            ║");
        System.out.println("║    170 (10101010) ✓                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Invalid Patterns (consecutive same bits):                   ║");
        System.out.println("║    3 (11) ✗ - two 1s                                         ║");
        System.out.println("║    4 (100) ✗ - two 0s                                        ║");
        System.out.println("║    7 (111) ✗ - three 1s                                      ║");
        System.out.println("║    11 (1011) ✗ - two 1s in middle                            ║");
        System.out.println("║    14 (1110) ✗ - three 1s                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Character Comparison:                                       ║");
        System.out.println("║    • charAt() returns char type                              ║");
        System.out.println("║    • '0' and '1' are character literals                      ║");
        System.out.println("║    • == compares character values directly                   ║");
        System.out.println("║    • '0' has ASCII value 48, '1' has value 49                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Alternative Bit Manipulation Approach:                      ║");
        System.out.println("║    • Use XOR to check alternating pattern                    ║");
        System.out.println("║    • n ^ (n >> 1) should give all 1s if alternating          ║");
        System.out.println("║    • More efficient but less readable                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Edge Cases:                                                 ║");
        System.out.println("║    ✓ n = 1 (single bit) → true                               ║");
        System.out.println("║    ✓ n = 2 (\"10\") → true                                     ║");
        System.out.println("║    ✓ Powers of 2 minus 1 with alternating bits               ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(log n) - Binary string length           ║");
        System.out.println("║  Space Complexity: O(log n) - String storage                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Note: Bit manipulation solution exists with O(1) space      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
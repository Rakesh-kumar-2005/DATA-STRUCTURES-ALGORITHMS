package Math_Problems;


public class Complement_Of_Base_10_Integer {

    private static int bitwiseComplement(int n) {

        String st = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder();

        for (char ch : st.toCharArray()) {
            if (ch == '0') {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }

        return Integer.parseInt(sb.toString(), 2);
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          COMPLEMENT OF BASE 10 INTEGER                       ║");
        System.out.println("║  Find complement by flipping all bits in binary form         ║");
        System.out.println("║  0 → 1, 1 → 0                                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: n = 5 ===");
        int n1 = 5;
        System.out.println("Input: " + n1);
        System.out.println("\nBinary representation:");
        System.out.println("  5 = 101₂");
        System.out.println("\nFlip each bit:");
        System.out.println("  1 → 0");
        System.out.println("  0 → 1");
        System.out.println("  1 → 0");
        System.out.println("\nComplement: 010₂ = 2₁₀\n");

        int result1 = bitwiseComplement(n1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: n = 7 ===");
        int n2 = 7;
        System.out.println("Input: " + n2);
        System.out.println("\nBinary: 111₂");
        System.out.println("Complement: 000₂ = 0₁₀\n");

        int result2 = bitwiseComplement(n2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: n = 10 ===");
        int n3 = 10;
        System.out.println("Input: " + n3);
        System.out.println("\nBinary: 1010₂");
        System.out.println("Flip bits:");
        System.out.println("  1 → 0");
        System.out.println("  0 → 1");
        System.out.println("  1 → 0");
        System.out.println("  0 → 1");
        System.out.println("\nComplement: 0101₂ = 5₁₀\n");

        int result3 = bitwiseComplement(n3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result3 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: n = 0 (Edge Case) ===");
        int n4 = 0;
        System.out.println("Input: " + n4);
        System.out.println("\nBinary: 0₂");
        System.out.println("Complement: 1₂ = 1₁₀\n");

        int result4 = bitwiseComplement(n4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: n = 1 ===");
        int n5 = 1;
        System.out.println("Input: " + n5);
        System.out.println("\nBinary: 1₂");
        System.out.println("Complement: 0₂ = 0₁₀\n");

        int result5 = bitwiseComplement(n5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: n = 15 (All 1s) ===");
        int n6 = 15;
        System.out.println("Input: " + n6);
        System.out.println("\nBinary: 1111₂ (all 1s)");
        System.out.println("Complement: 0000₂ = 0₁₀ (all 0s)\n");

        int result6 = bitwiseComplement(n6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: n = 8 (Power of 2) ===");
        int n7 = 8;
        System.out.println("Input: " + n7);
        System.out.println("\nBinary: 1000₂");
        System.out.println("Complement: 0111₂ = 7₁₀\n");

        int result7 = bitwiseComplement(n7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result7 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: n = 31 ===");
        int n8 = 31;
        System.out.println("Input: " + n8);
        System.out.println("\nBinary: 11111₂ (5 ones)");
        System.out.println("Complement: 00000₂ = 0₁₀\n");

        int result8 = bitwiseComplement(n8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result8 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Flip all bits in binary representation             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Steps:                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. Convert to binary string                                 ║");
        System.out.println("║     Integer.toBinaryString(n)                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. Flip each bit                                            ║");
        System.out.println("║     if (ch == '0') → append '1'                              ║");
        System.out.println("║     if (ch == '1') → append '0'                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  3. Convert back to decimal                                  ║");
        System.out.println("║     Integer.parseInt(binary, 2)                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: n = 5                                              ║");
        System.out.println("║    Step 1: 5 → \"101\"                                         ║");
        System.out.println("║    Step 2: Flip → \"010\"                                      ║");
        System.out.println("║    Step 3: \"010\" → 2                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Pattern Observation:                                        ║");
        System.out.println("║    For n with k bits: complement = (2^k - 1) - n             ║");
        System.out.println("║    Example: 5 (101) has 3 bits                               ║");
        System.out.println("║    Complement = 2^3 - 1 - 5 = 7 - 5 = 2                      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(log n) - Binary string length                    ║");
        System.out.println("║    Space: O(log n) - StringBuilder storage                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}

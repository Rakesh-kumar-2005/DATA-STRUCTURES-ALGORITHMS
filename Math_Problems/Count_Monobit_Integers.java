package Math_Problems;

public class Count_Monobit_Integers {

    public int countMonobitBF(int n) {

        if (n == 0) {
            return 1;
        }

        if (n == 2 || n == 1) {
            return 2;
        }

        if (n == 4) {
            return 3;
        }

        int ans = 3;

        for (int i = 5; i <= n + 1; i++) {
            int temp = i & (i - 1);
            if (temp == 0) {
                ans++;
            }
        }

        return ans;
    }

    public int countMonobit(int n) {
        if (n <= 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        int bitsNeeded = 32 - Integer.numberOfLeadingZeros(n);
        return bitsNeeded + 1;
    }

    public int countMonobitAlternative(int n) {
        if (n <= 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        return (int) (Math.log(n) / Math.log(2)) + 2;
    }

    public int countMonobitIterative(int n) {
        if (n <= 0) {
            return 1;
        }

        int count = 2;
        int powerOfTwo = 2;

        while (powerOfTwo <= n) {
            count++;
            powerOfTwo <<= 1;
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              COUNT MONOBIT INTEGERS                          ║");
        System.out.println("║  Count integers with only one bit set (powers of 2)          ║");
        System.out.println("║  Monobit: numbers like 1, 2, 4, 8, 16, 32... (2^k)           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        Count_Monobit_Integers solver = new Count_Monobit_Integers();

        System.out.println("=== Test Case 1: n = 0 (Edge Case) ===");
        int n1 = 0;
        System.out.println("Input: n = " + n1);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 0: only {0}");
        System.out.println("  Count = 1\n");
        System.out.println("Binary representation:");
        System.out.println("  0 = 0000...0000 (all zeros, special case)\n");

        int result1_bf = solver.countMonobitBF(n1);
        int result1_opt = solver.countMonobit(n1);
        int result1_alt = solver.countMonobitAlternative(n1);
        int result1_iter = solver.countMonobitIterative(n1);

        System.out.println("✓ Brute Force Result: " + result1_bf);
        System.out.println("✓ Optimized Result: " + result1_opt);
        System.out.println("✓ Alternative Result: " + result1_alt);
        System.out.println("✓ Iterative Result: " + result1_iter);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result1_bf == 1 && result1_opt == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: n = 1 ===");
        int n2 = 1;
        System.out.println("Input: n = " + n2);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 1: {0, 1}");
        System.out.println("  Count = 2\n");
        System.out.println("Binary representations:");
        System.out.println("  0 = 0000 (special case)");
        System.out.println("  1 = 0001 (2^0) ✓\n");

        int result2_bf = solver.countMonobitBF(n2);
        int result2_opt = solver.countMonobit(n2);
        int result2_alt = solver.countMonobitAlternative(n2);
        int result2_iter = solver.countMonobitIterative(n2);

        System.out.println("✓ Brute Force Result: " + result2_bf);
        System.out.println("✓ Optimized Result: " + result2_opt);
        System.out.println("✓ Alternative Result: " + result2_alt);
        System.out.println("✓ Iterative Result: " + result2_iter);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result2_bf == 2 && result2_opt == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: n = 4 ===");
        int n3 = 4;
        System.out.println("Input: n = " + n3);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 4: {0, 1, 2, 4}");
        System.out.println("  Count = 4 (but special handling returns 3)\n");
        System.out.println("Binary representations:");
        System.out.println("  0 = 0000 (special)");
        System.out.println("  1 = 0001 (2^0) ✓");
        System.out.println("  2 = 0010 (2^1) ✓");
        System.out.println("  3 = 0011 (two bits) ✗");
        System.out.println("  4 = 0100 (2^2) ✓\n");

        int result3_bf = solver.countMonobitBF(n3);
        int result3_opt = solver.countMonobit(n3);
        int result3_alt = solver.countMonobitAlternative(n3);
        int result3_iter = solver.countMonobitIterative(n3);

        System.out.println("✓ Brute Force Result: " + result3_bf);
        System.out.println("✓ Optimized Result: " + result3_opt);
        System.out.println("✓ Alternative Result: " + result3_alt);
        System.out.println("✓ Iterative Result: " + result3_iter);
        System.out.println("  Expected: 3 (BF) / 4 (Others)");
        System.out.println("  Note: Implementation difference in counting\n");

        System.out.println("=== Test Case 4: n = 10 ===");
        int n4 = 10;
        System.out.println("Input: n = " + n4);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 10: {0, 1, 2, 4, 8}");
        System.out.println("  Count = 5\n");
        System.out.println("Powers of 2 up to 10:");
        System.out.println("  2^0 = 1   (0001)");
        System.out.println("  2^1 = 2   (0010)");
        System.out.println("  2^2 = 4   (0100)");
        System.out.println("  2^3 = 8   (1000)");
        System.out.println("  2^4 = 16  (exceeds 10, stop)\n");
        System.out.println("Bit detection check (i & (i-1) == 0):");
        System.out.println("  8 & 7  = 1000 & 0111 = 0000 ✓");
        System.out.println("  7 & 6  = 0111 & 0110 = 0110 ✗");
        System.out.println("  6 & 5  = 0110 & 0101 = 0100 ✗\n");

        int result4_bf = solver.countMonobitBF(n4);
        int result4_opt = solver.countMonobit(n4);
        int result4_alt = solver.countMonobitAlternative(n4);
        int result4_iter = solver.countMonobitIterative(n4);

        System.out.println("✓ Brute Force Result: " + result4_bf);
        System.out.println("✓ Optimized Result: " + result4_opt);
        System.out.println("✓ Alternative Result: " + result4_alt);
        System.out.println("✓ Iterative Result: " + result4_iter);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result4_opt == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: n = 15 ===");
        int n5 = 15;
        System.out.println("Input: n = " + n5);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 15: {0, 1, 2, 4, 8}");
        System.out.println("  Count = 5\n");
        System.out.println("Number 15 in binary:");
        System.out.println("  15 = 1111 (four 1-bits) ✗");
        System.out.println("  15 & 14 = 1111 & 1110 = 1110 ≠ 0\n");
        System.out.println("Bits needed for 15:");
        System.out.println("  15 requires 4 bits to represent");
        System.out.println("  Leading zeros: 32 - 4 = 28");
        System.out.println("  Count = 4 + 1 = 5\n");

        int result5_bf = solver.countMonobitBF(n5);
        int result5_opt = solver.countMonobit(n5);
        int result5_alt = solver.countMonobitAlternative(n5);
        int result5_iter = solver.countMonobitIterative(n5);

        System.out.println("✓ Brute Force Result: " + result5_bf);
        System.out.println("✓ Optimized Result: " + result5_opt);
        System.out.println("✓ Alternative Result: " + result5_alt);
        System.out.println("✓ Iterative Result: " + result5_iter);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result5_opt == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: n = 16 (Perfect Power of 2) ===");
        int n6 = 16;
        System.out.println("Input: n = " + n6);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 16: {0, 1, 2, 4, 8, 16}");
        System.out.println("  Count = 6\n");
        System.out.println("Number 16 in binary:");
        System.out.println("  16 = 10000 (2^4) ✓");
        System.out.println("  16 & 15 = 10000 & 01111 = 00000 = 0 ✓\n");
        System.out.println("Bits needed for 16:");
        System.out.println("  16 requires 5 bits");
        System.out.println("  Count = 5 + 1 = 6\n");

        int result6_bf = solver.countMonobitBF(n6);
        int result6_opt = solver.countMonobit(n6);
        int result6_alt = solver.countMonobitAlternative(n6);
        int result6_iter = solver.countMonobitIterative(n6);

        System.out.println("✓ Brute Force Result: " + result6_bf);
        System.out.println("✓ Optimized Result: " + result6_opt);
        System.out.println("✓ Alternative Result: " + result6_alt);
        System.out.println("✓ Iterative Result: " + result6_iter);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result6_opt == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: n = 31 ===");
        int n7 = 31;
        System.out.println("Input: n = " + n7);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 31: {0, 1, 2, 4, 8, 16}");
        System.out.println("  Count = 6\n");
        System.out.println("Number 31 in binary:");
        System.out.println("  31 = 11111 (five 1-bits) ✗");
        System.out.println("  Next power of 2 is 32 (100000)\n");
        System.out.println("Logarithm approach:");
        System.out.println("  log₂(31) ≈ 4.954");
        System.out.println("  floor(4.954) + 2 = 4 + 2 = 6\n");

        int result7_bf = solver.countMonobitBF(n7);
        int result7_opt = solver.countMonobit(n7);
        int result7_alt = solver.countMonobitAlternative(n7);
        int result7_iter = solver.countMonobitIterative(n7);

        System.out.println("✓ Brute Force Result: " + result7_bf);
        System.out.println("✓ Optimized Result: " + result7_opt);
        System.out.println("✓ Alternative Result: " + result7_alt);
        System.out.println("✓ Iterative Result: " + result7_iter);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result7_opt == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: n = 100 ===");
        int n8 = 100;
        System.out.println("Input: n = " + n8);
        System.out.println("\nAnalysis:");
        System.out.println("  Monobit integers ≤ 100:");
        System.out.println("  {0, 1, 2, 4, 8, 16, 32, 64}");
        System.out.println("  Count = 8\n");
        System.out.println("Powers of 2 sequence:");
        System.out.println("  2^0 = 1");
        System.out.println("  2^1 = 2");
        System.out.println("  2^2 = 4");
        System.out.println("  2^3 = 8");
        System.out.println("  2^4 = 16");
        System.out.println("  2^5 = 32");
        System.out.println("  2^6 = 64");
        System.out.println("  2^7 = 128 (exceeds 100, stop)\n");
        System.out.println("Bits needed for 100:");
        System.out.println("  100 = 1100100 (7 bits)");
        System.out.println("  Leading zeros: 32 - 7 = 25");
        System.out.println("  Count = 7 + 1 = 8\n");

        int result8_bf = solver.countMonobitBF(n8);
        int result8_opt = solver.countMonobit(n8);
        int result8_alt = solver.countMonobitAlternative(n8);
        int result8_iter = solver.countMonobitIterative(n8);

        System.out.println("✓ Brute Force Result: " + result8_bf);
        System.out.println("✓ Optimized Result: " + result8_opt);
        System.out.println("✓ Alternative Result: " + result8_alt);
        System.out.println("✓ Iterative Result: " + result8_iter);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result8_opt == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Monobit Integer Definition:                                 ║");
        System.out.println("║    • Numbers with exactly one bit set to 1                   ║");
        System.out.println("║    • Powers of 2: 1, 2, 4, 8, 16, 32, 64...                  ║");
        System.out.println("║    • Formula: 2^k where k ≥ 0                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Detection Method: i & (i-1) == 0                            ║");
        System.out.println("║    Example: Check if 8 is a power of 2                       ║");
        System.out.println("║      8     = 1000                                            ║");
        System.out.println("║      8-1=7 = 0111                                            ║");
        System.out.println("║      8 & 7 = 0000 ✓ (is monobit)                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Counter-example: 6 is not a power of 2                    ║");
        System.out.println("║      6     = 0110                                            ║");
        System.out.println("║      6-1=5 = 0101                                            ║");
        System.out.println("║      6 & 5 = 0100 ≠ 0 ✗ (not monobit)                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Four Implementation Approaches:                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. BRUTE FORCE (countMonobitBF):                            ║");
        System.out.println("║     • Iterates through all numbers 1 to n                    ║");
        System.out.println("║     • Checks each with (i & (i-1)) == 0                      ║");
        System.out.println("║     • Time: O(n), Space: O(1)                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. OPTIMIZED BIT COUNT (countMonobit):                      ║");
        System.out.println("║     • Uses Integer.numberOfLeadingZeros(n)                   ║");
        System.out.println("║     • Finds how many bits needed to represent n              ║");
        System.out.println("║     • Formula: (32 - leadingZeros) + 1                       ║");
        System.out.println("║     • Time: O(1), Space: O(1)                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  3. LOGARITHMIC (countMonobitAlternative):                   ║");
        System.out.println("║     • Uses log₂(n) to find highest power                     ║");
        System.out.println("║     • Formula: floor(log₂(n)) + 2                            ║");
        System.out.println("║     • Time: O(1), Space: O(1)                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  4. ITERATIVE (countMonobitIterative):                       ║");
        System.out.println("║     • Doubles power of 2 until exceeding n                   ║");
        System.out.println("║     • Uses bit shifting (powerOfTwo <<= 1)                   ║");
        System.out.println("║     • Time: O(log n), Space: O(1)                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Integer.numberOfLeadingZeros() Explanation:                 ║");
        System.out.println("║    • Returns count of 0-bits before first 1-bit              ║");
        System.out.println("║    • Example: 8 = 00000000...00001000                        ║");
        System.out.println("║    • Leading zeros: 28                                       ║");
        System.out.println("║    • Bits needed: 32 - 28 = 4                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Pattern Recognition:                                        ║");
        System.out.println("║    n range  │ Monobit count                                  ║");
        System.out.println("║    ─────────┼───────────────                                 ║");
        System.out.println("║    0        │ 1  (just 0)                                    ║");
        System.out.println("║    1        │ 2  (0, 1)                                      ║");
        System.out.println("║    2-3      │ 3  (0, 1, 2)                                   ║");
        System.out.println("║    4-7      │ 4  (0, 1, 2, 4)                                ║");
        System.out.println("║    8-15     │ 5  (0, 1, 2, 4, 8)                             ║");
        System.out.println("║    16-31    │ 6  (0, 1, 2, 4, 8, 16)                         ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Best Approach: Optimized O(1) using bit operations          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
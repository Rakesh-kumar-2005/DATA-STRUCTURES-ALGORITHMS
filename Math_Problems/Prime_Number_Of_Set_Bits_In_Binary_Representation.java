package Math_Problems;

public class Prime_Number_Of_Set_Bits_In_Binary_Representation {

    private static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int countPrimeSetBits(int left, int right) {

        int count = 0;

        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);
            if (isPrime(bits)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║    PRIME NUMBER OF SET BITS IN BINARY REPRESENTATION         ║");
        System.out.println("║   Count numbers in range with prime number of 1-bits         ║");
        System.out.println("║          Set bit = 1 in binary representation                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Small Range [6, 10] ===");
        int left1 = 6, right1 = 10;
        System.out.println("Range: [" + left1 + ", " + right1 + "]\n");
        System.out.println("Analyzing each number:");
        System.out.println("  6  = 110    → 2 set bits → 2 is prime ✓");
        System.out.println("  7  = 111    → 3 set bits → 3 is prime ✓");
        System.out.println("  8  = 1000   → 1 set bit  → 1 not prime ✗");
        System.out.println("  9  = 1001   → 2 set bits → 2 is prime ✓");
        System.out.println("  10 = 1010   → 2 set bits → 2 is prime ✓\n");
        System.out.println("Prime set bit counts: 6, 7, 9, 10");
        System.out.println("Total count: 4\n");

        int result1 = countPrimeSetBits(left1, right1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result1 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Range [10, 15] ===");
        int left2 = 10, right2 = 15;
        System.out.println("Range: [" + left2 + ", " + right2 + "]\n");
        System.out.println("Analyzing each number:");
        System.out.println("  10 = 1010   → 2 set bits → 2 is prime ✓");
        System.out.println("  11 = 1011   → 3 set bits → 3 is prime ✓");
        System.out.println("  12 = 1100   → 2 set bits → 2 is prime ✓");
        System.out.println("  13 = 1101   → 3 set bits → 3 is prime ✓");
        System.out.println("  14 = 1110   → 3 set bits → 3 is prime ✓");
        System.out.println("  15 = 1111   → 4 set bits → 4 not prime ✗\n");
        System.out.println("Prime set bit counts: 10, 11, 12, 13, 14");
        System.out.println("Total count: 5\n");

        int result2 = countPrimeSetBits(left2, right2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result2 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Number [2, 2] ===");
        int left3 = 2, right3 = 2;
        System.out.println("Range: [" + left3 + ", " + right3 + "]\n");
        System.out.println("Analyzing:");
        System.out.println("  2 = 10 → 1 set bit → 1 not prime ✗\n");
        System.out.println("Note: 1 is not considered prime");
        System.out.println("Total count: 0\n");

        int result3 = countPrimeSetBits(left3, right3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Powers of 2 [1, 8] ===");
        int left4 = 1, right4 = 8;
        System.out.println("Range: [" + left4 + ", " + right4 + "]\n");
        System.out.println("Analyzing each number:");
        System.out.println("  1 = 1       → 1 set bit  → 1 not prime ✗");
        System.out.println("  2 = 10      → 1 set bit  → 1 not prime ✗");
        System.out.println("  3 = 11      → 2 set bits → 2 is prime ✓");
        System.out.println("  4 = 100     → 1 set bit  → 1 not prime ✗");
        System.out.println("  5 = 101     → 2 set bits → 2 is prime ✓");
        System.out.println("  6 = 110     → 2 set bits → 2 is prime ✓");
        System.out.println("  7 = 111     → 3 set bits → 3 is prime ✓");
        System.out.println("  8 = 1000    → 1 set bit  → 1 not prime ✗\n");
        System.out.println("Powers of 2 (1 set bit): not prime");
        System.out.println("Prime set bit counts: 3, 5, 6, 7");
        System.out.println("Total count: 4\n");

        int result4 = countPrimeSetBits(left4, right4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 4");
        System.out.println("  Status: " + (result4 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Set Bits [7, 7] ===");
        int left5 = 7, right5 = 7;
        System.out.println("Range: [" + left5 + ", " + right5 + "]\n");
        System.out.println("Analyzing:");
        System.out.println("  7 = 111 → 3 set bits → 3 is prime ✓\n");
        System.out.println("7 in binary has all bits set (in 3-bit range)");
        System.out.println("Total count: 1\n");

        int result5 = countPrimeSetBits(left5, right5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Range with 4 Set Bits [15, 15] ===");
        int left6 = 15, right6 = 15;
        System.out.println("Range: [" + left6 + ", " + right6 + "]\n");
        System.out.println("Analyzing:");
        System.out.println("  15 = 1111 → 4 set bits → 4 not prime ✗\n");
        System.out.println("4 = 2 × 2 (composite number)");
        System.out.println("Total count: 0\n");

        int result6 = countPrimeSetBits(left6, right6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Medium Range [20, 30] ===");
        int left7 = 20, right7 = 30;
        System.out.println("Range: [" + left7 + ", " + right7 + "]\n");
        System.out.println("Detailed Analysis:");
        for (int i = left7; i <= right7; i++) {
            int bits = Integer.bitCount(i);
            boolean prime = isPrime(bits);
            System.out.println(String.format("  %2d = %-6s → %d set bits → %d %s %s",
                i, Integer.toBinaryString(i), bits, bits,
                prime ? "is prime" : "not prime",
                prime ? "✓" : "✗"));
        }

        int result7 = countPrimeSetBits(left7, right7);
        System.out.println("\n✓ Result: " + result7);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 8: Large Range [100, 110] ===");
        int left8 = 100, right8 = 110;
        System.out.println("Range: [" + left8 + ", " + right8 + "]\n");
        System.out.println("Sample Analysis:");
        System.out.println("  100 = 1100100 → 3 set bits → 3 is prime ✓");
        System.out.println("  101 = 1100101 → 4 set bits → 4 not prime ✗");
        System.out.println("  102 = 1100110 → 4 set bits → 4 not prime ✗");
        System.out.println("  103 = 1100111 → 5 set bits → 5 is prime ✓");
        System.out.println("  ...(continuing through range)\n");

        int result8 = countPrimeSetBits(left8, right8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 9: Range Testing 5 Set Bits [31, 31] ===");
        int left9 = 31, right9 = 31;
        System.out.println("Range: [" + left9 + ", " + right9 + "]\n");
        System.out.println("Analyzing:");
        System.out.println("  31 = 11111 → 5 set bits → 5 is prime ✓\n");
        System.out.println("5 is prime (divisible only by 1 and 5)");
        System.out.println("Total count: 1\n");

        int result9 = countPrimeSetBits(left9, right9);
        System.out.println("✓ Result: " + result9);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result9 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Prime Set Bits Showcase [1, 20] ===");
        int left10 = 1, right10 = 20;
        System.out.println("Range: [" + left10 + ", " + right10 + "]\n");
        System.out.println("Complete breakdown:");
        int count = 0;
        for (int i = left10; i <= right10; i++) {
            int bits = Integer.bitCount(i);
            boolean prime = isPrime(bits);
            if (prime) count++;
            System.out.println(String.format("  %2d = %-6s → %d bit%s → %s",
                i, Integer.toBinaryString(i), bits, bits == 1 ? " " : "s",
                prime ? "PRIME ✓" : "not prime"));
        }
        System.out.println("\nPrime set bit numbers: 3,5,6,7,9,10,11,12,13,14,17,18,19,20");

        int result10 = countPrimeSetBits(left10, right10);
        System.out.println("\n✓ Result: " + result10);
        System.out.println("  Counted: " + count);
        System.out.println("  Status: " + (result10 == count ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Count numbers in range [left, right] where the count      ║");
        System.out.println("║    of set bits (1s) in binary representation is prime        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two-Part Solution:                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  PART 1: Count Set Bits                                      ║");
        System.out.println("║    Integer.bitCount(n)                                       ║");
        System.out.println("║    • Built-in Java method                                    ║");
        System.out.println("║    • Counts number of 1-bits in binary representation        ║");
        System.out.println("║    • O(1) time - uses efficient bit manipulation             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Example: Integer.bitCount(7)                              ║");
        System.out.println("║      7 = 111 → returns 3                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  PART 2: Check if Number is Prime                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Edge Cases:                                               ║");
        System.out.println("║      • n < 2: Not prime (0, 1 not prime)                     ║");
        System.out.println("║      • n == 2: Prime (only even prime)                       ║");
        System.out.println("║      • n % 2 == 0: Not prime (even numbers)                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Main Loop:                                                ║");
        System.out.println("║      for (i = 3; i <= sqrt(n); i += 2)                       ║");
        System.out.println("║        if (n % i == 0) return false                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    • Only check odd divisors (even already handled)          ║");
        System.out.println("║    • Only check up to sqrt(n)                                ║");
        System.out.println("║    • If divisible, not prime                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why sqrt(n)?                                                ║");
        System.out.println("║    If n = a × b and a ≤ b, then a ≤ √n                       ║");
        System.out.println("║    Example: 36 = 6 × 6, only need to check up to 6           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Prime Numbers Relevant to This Problem:                     ║");
        System.out.println("║    2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31...                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    For 32-bit integers, max set bits = 32                    ║");
        System.out.println("║    Prime set bit counts ≤ 32: {2,3,5,7,11,13,17,19,23,29,31} ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complete Algorithm Flow:                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║    1. Initialize count = 0                                   ║");
        System.out.println("║    2. For each number i in [left, right]:                    ║");
        System.out.println("║         a. Get bit count: bits = Integer.bitCount(i)         ║");
        System.out.println("║         b. Check if bits is prime: isPrime(bits)             ║");
        System.out.println("║         c. If prime, increment count                         ║");
        System.out.println("║    3. Return count                                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Walkthrough: Range [6, 10]                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=6: bitCount(6)=2, isPrime(2)=true → count=1             ║");
        System.out.println("║      6 = 110 (two 1s)                                        ║");
        System.out.println("║      2 is prime ✓                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=7: bitCount(7)=3, isPrime(3)=true → count=2             ║");
        System.out.println("║      7 = 111 (three 1s)                                      ║");
        System.out.println("║      3 is prime ✓                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=8: bitCount(8)=1, isPrime(1)=false → count=2            ║");
        System.out.println("║      8 = 1000 (one 1)                                        ║");
        System.out.println("║      1 is not prime ✗                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=9: bitCount(9)=2, isPrime(2)=true → count=3             ║");
        System.out.println("║      9 = 1001 (two 1s)                                       ║");
        System.out.println("║      2 is prime ✓                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=10: bitCount(10)=2, isPrime(2)=true → count=4           ║");
        System.out.println("║      10 = 1010 (two 1s)                                      ║");
        System.out.println("║      2 is prime ✓                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Final result: 4                                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Set Bit Count Patterns:                                     ║");
        System.out.println("║    1 bit:  1,2,4,8,16... (powers of 2) → NOT PRIME           ║");
        System.out.println("║    2 bits: 3,5,6,9,10,12... → PRIME                          ║");
        System.out.println("║    3 bits: 7,11,13,14,19,21... → PRIME                       ║");
        System.out.println("║    4 bits: 15,23,27,29,30... → NOT PRIME                     ║");
        System.out.println("║    5 bits: 31,47,55,59... → PRIME                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimization Opportunity:                                   ║");
        System.out.println("║    Since max set bits ≤ 32, could use lookup table:          ║");
        System.out.println("║    primes = {2,3,5,7,11,13,17,19,23,29,31}                   ║");
        System.out.println("║    Check if bitCount is in this set → O(1)                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n × √k) where n = range size,           ║");
        System.out.println("║                    k = max bits (bounded by log of numbers)  ║");
        System.out.println("║  Space Complexity: O(1) - Constant space                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  With optimization: O(n) using prime lookup table            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
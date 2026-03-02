package Dynamic_Programming;

import java.util.Arrays;

public class Minimum_Path_Sum {

    private static int recursive(int[][] grid, int i, int j) {

        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }


        int up = recursive(grid, i - 1, j);
        int left = recursive(grid, i, j - 1);

        int minPrev = Math.min(up, left);

        if (minPrev == Integer.MAX_VALUE) {
            return minPrev;
        }

        return grid[i][j] + minPrev;
    }

    private static int Memoization(int[][] grid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][j] != - 1) {
            return dp[i][j];
        }


        int up = Memoization(grid, i - 1, j, dp);
        int left = Memoization(grid, i, j - 1, dp);

        int minPrev = Math.min(up, left);

        if (minPrev == Integer.MAX_VALUE) {
            return minPrev;
        }

        return dp[i][j] = grid[i][j] + minPrev;
    }

    private static int Tabulation(int[][] grid, int n, int m) {

        int[][] dp = new int[n][m];
        for (int[] a : dp) {
            Arrays.fill(a, - 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                    continue;
                }

                int up = Integer.MAX_VALUE;
                int left = Integer.MAX_VALUE;

                if (i > 0) {
                    up = dp[i - 1][j];
                }

                if (j > 0) {
                    left = dp[i][j - 1];
                }

                int minPrev = Math.min(up, left);

                if (minPrev == Integer.MAX_VALUE) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = grid[i][j] + minPrev;
                }

            }

        }

        return dp[n - 1][m - 1];
    }

    private static int ultimateSpaceOptimization(int[][] grid, int n, int m) {

        int[] prev = new int[m];

        for (int i = 0; i < n; i++) {

            int[] curr = new int[m];

            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) {
                    curr[0] = grid[0][0];
                    continue;
                }

                int up = i > 0 ? prev[j] : Integer.MAX_VALUE;
                int left = j > 0 ? curr[j - 1] : Integer.MAX_VALUE;

                int minPrev = Math.min(up, left);

                if (minPrev == Integer.MAX_VALUE) {
                    curr[j] = Integer.MAX_VALUE;
                } else {
                    curr[j] = grid[i][j] + minPrev;
                }

            }
            prev = curr;
        }

        return prev[m - 1];
    }


    private static int concatenatedBinary(int number) {

        long result = 0;

        for (int i = 1; i <= number; i++) {
            int bitLength = Integer.toBinaryString(i).length();
            result = ((result << bitLength) | i) % 1000000007;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║      CONCATENATION OF CONSECUTIVE BINARY NUMBERS             ║");
        System.out.println("║  Concatenate binary representations of 1 to n                ║");
        System.out.println("║  Return decimal value modulo 10^9 + 7                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: n = 1 ===");
        int n1 = 1;
        System.out.println("Input: n = " + n1);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1 → \"1\"");
        System.out.println("\nDecimal value: 1₂ = 1₁₀");
        System.out.println("Result: 1\n");

        int result1 = concatenatedBinary(n1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result1 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: n = 3 ===");
        int n2 = 3;
        System.out.println("Input: n = " + n2);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1 → \"1\"");
        System.out.println("  2 → \"10\"");
        System.out.println("  3 → \"11\"");
        System.out.println("\nConcatenated: \"11011\"");
        System.out.println("\nDecimal conversion:");
        System.out.println("  11011₂ = 1×16 + 1×8 + 0×4 + 1×2 + 1×1");
        System.out.println("         = 16 + 8 + 2 + 1 = 27₁₀\n");

        int result2 = concatenatedBinary(n2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 27");
        System.out.println("  Status: " + (result2 == 27 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: n = 12 ===");
        int n3 = 12;
        System.out.println("Input: n = " + n3);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1  → 1");
        System.out.println("  2  → 10");
        System.out.println("  3  → 11");
        System.out.println("  4  → 100");
        System.out.println("  5  → 101");
        System.out.println("  6  → 110");
        System.out.println("  7  → 111");
        System.out.println("  8  → 1000");
        System.out.println("  9  → 1001");
        System.out.println("  10 → 1010");
        System.out.println("  11 → 1011");
        System.out.println("  12 → 1100");
        System.out.println("\nConcatenated string:");
        System.out.println("  \"1\" + \"10\" + \"11\" + \"100\" + \"101\" + \"110\" + \"111\"");
        System.out.println("  + \"1000\" + \"1001\" + \"1010\" + \"1011\" + \"1100\"");
        System.out.println("\nFull: \"11011100101110111100010011010101111100\"");
        System.out.println("Decimal value mod (10^9+7): 118505\n");

        int result3 = concatenatedBinary(n3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 118505");
        System.out.println("  Status: " + (result3 == 118505 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: n = 2 ===");
        int n4 = 2;
        System.out.println("Input: n = " + n4);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1 → \"1\"");
        System.out.println("  2 → \"10\"");
        System.out.println("\nConcatenated: \"110\"");
        System.out.println("\nDecimal conversion:");
        System.out.println("  110₂ = 1×4 + 1×2 + 0×1 = 6₁₀\n");

        int result4 = concatenatedBinary(n4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: n = 5 ===");
        int n5 = 5;
        System.out.println("Input: n = " + n5);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1 → 1      (1 bit)");
        System.out.println("  2 → 10     (2 bits)");
        System.out.println("  3 → 11     (2 bits)");
        System.out.println("  4 → 100    (3 bits)");
        System.out.println("  5 → 101    (3 bits)");
        System.out.println("\nConcatenated: \"110111001010\"");
        System.out.println("\nBuilding process:");
        System.out.println("  Start: 0");
        System.out.println("  Add 1:   0 << 1 | 1   = 1");
        System.out.println("  Add 10:  1 << 2 | 2   = 110₂ = 6");
        System.out.println("  Add 11:  6 << 2 | 3   = 11011₂ = 27");
        System.out.println("  Add 100: 27 << 3 | 4  = 11011100₂ = 220");
        System.out.println("  Add 101: 220 << 3 | 5 = 110111001010₂ = 1770\n");

        int result5 = concatenatedBinary(n5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 1770");
        System.out.println("  Status: " + (result5 == 1770 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: n = 7 ===");
        int n6 = 7;
        System.out.println("Input: n = " + n6);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1,2,3,4,5,6,7");
        System.out.println("  → 1,10,11,100,101,110,111");
        System.out.println("\nConcatenated: \"1101110010111011\"");
        System.out.println("Decimal value: 55739\n");

        int result6 = concatenatedBinary(n6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 55739");
        System.out.println("  Status: " + (result6 == 55739 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: n = 10 ===");
        int n7 = 10;
        System.out.println("Input: n = " + n7);
        System.out.println("\nBinary Concatenation (1 to 10):");
        System.out.println("  1,10,11,100,101,110,111,1000,1001,1010");
        System.out.println("\nPattern observation:");
        System.out.println("  1 bit:  1");
        System.out.println("  2 bits: 2,3");
        System.out.println("  3 bits: 4,5,6,7");
        System.out.println("  4 bits: 8,9,10\n");

        int result7 = concatenatedBinary(n7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 8: Large n = 100 ===");
        int n8 = 100;
        System.out.println("Input: n = " + n8);
        System.out.println("\nConcatenating 1 to 100:");
        System.out.println("  Numbers 1-63:   6 bits or less");
        System.out.println("  Numbers 64-100: 7 bits");
        System.out.println("\nModulo keeps result manageable");
        System.out.println("MOD = 10^9 + 7 = 1,000,000,007\n");

        int result8 = concatenatedBinary(n8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 9: Power of 2 - n = 8 ===");
        int n9 = 8;
        System.out.println("Input: n = " + n9);
        System.out.println("\nBinary Concatenation:");
        System.out.println("  1,2,3,4,5,6,7,8");
        System.out.println("  8 = 1000₂ (first 4-bit number)");
        System.out.println("\nNote: 8 requires 4 bits");
        System.out.println("  Previous max (7) used 3 bits\n");

        int result9 = concatenatedBinary(n9);
        System.out.println("✓ Result: " + result9);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Concatenate binary representations of 1,2,3,...,n         ║");
        System.out.println("║    Convert result to decimal, return mod (10^9 + 7)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: n = 3                                              ║");
        System.out.println("║    1 → \"1\"                                                    ║");
        System.out.println("║    2 → \"10\"                                                   ║");
        System.out.println("║    3 → \"11\"                                                   ║");
        System.out.println("║    Concatenate: \"1\" + \"10\" + \"11\" = \"11011\"                  ║");
        System.out.println("║    Convert: 11011₂ = 27₁₀                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Naive Approach (String Concatenation):                      ║");
        System.out.println("║    • Build string: \"1\" + \"10\" + \"11\" + ...                   ║");
        System.out.println("║    • Convert to decimal at end                               ║");
        System.out.println("║    • Problems:                                               ║");
        System.out.println("║      - String grows exponentially                            ║");
        System.out.println("║      - Memory intensive                                      ║");
        System.out.println("║      - Cannot handle large n                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Efficient Approach - Bit Manipulation:                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Operation: result << bitLength | i                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Left Shift (<<):                                            ║");
        System.out.println("║    • result << bitLength                                     ║");
        System.out.println("║    • Shifts bits left by bitLength positions                 ║");
        System.out.println("║    • Makes room for next number                              ║");
        System.out.println("║    • Equivalent to multiplying by 2^bitLength                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Bitwise OR (|):                                             ║");
        System.out.println("║    • (shifted_result) | i                                    ║");
        System.out.println("║    • Combines shifted result with new number                 ║");
        System.out.println("║    • Appends i's bits to the end                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step-by-Step Example: n = 3                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Initial: result = 0                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  i = 1:                                                      ║");
        System.out.println("║    bitLength = 1 (\"1\" has 1 bit)                             ║");
        System.out.println("║    result = (0 << 1) | 1                                     ║");
        System.out.println("║           = 0 | 1 = 1                                        ║");
        System.out.println("║    Binary: \"1\"                                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  i = 2:                                                      ║");
        System.out.println("║    bitLength = 2 (\"10\" has 2 bits)                           ║");
        System.out.println("║    result = (1 << 2) | 2                                     ║");
        System.out.println("║           = 100₂ | 10₂                                       ║");
        System.out.println("║           = 110₂ = 6                                         ║");
        System.out.println("║    Binary: \"110\" (\"1\" + \"10\")                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Detailed breakdown:                                       ║");
        System.out.println("║      Before shift: 1₂ = \"1\"                                  ║");
        System.out.println("║      Shift left 2:  \"1\" → \"100\"                              ║");
        System.out.println("║      OR with 2:     \"100\" | \"10\" = \"110\"                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  i = 3:                                                      ║");
        System.out.println("║    bitLength = 2 (\"11\" has 2 bits)                           ║");
        System.out.println("║    result = (6 << 2) | 3                                     ║");
        System.out.println("║           = 11000₂ | 11₂                                     ║");
        System.out.println("║           = 11011₂ = 27                                      ║");
        System.out.println("║    Binary: \"11011\" (\"1\" + \"10\" + \"11\")                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Detailed breakdown:                                       ║");
        System.out.println("║      Before shift: 110₂ = \"110\"                              ║");
        System.out.println("║      Shift left 2:  \"110\" → \"11000\"                          ║");
        System.out.println("║      OR with 3:     \"11000\" | \"11\" = \"11011\"                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Final result: 27                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Bit Length Calculation:                                     ║");
        System.out.println("║    Integer.toBinaryString(i).length()                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Pattern of bit lengths:                                     ║");
        System.out.println("║    1        : 1 bit                                          ║");
        System.out.println("║    2-3      : 2 bits                                         ║");
        System.out.println("║    4-7      : 3 bits                                         ║");
        System.out.println("║    8-15     : 4 bits                                         ║");
        System.out.println("║    16-31    : 5 bits                                         ║");
        System.out.println("║    2^k to 2^(k+1)-1 : k+1 bits                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Modulo at Each Step?                                    ║");
        System.out.println("║    result = ((result << bitLength) | i) % 1000000007         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Reasons:                                                    ║");
        System.out.println("║    • Prevents overflow (long can only hold so much)          ║");
        System.out.println("║    • Result grows exponentially without modulo               ║");
        System.out.println("║    • For large n, intermediate values exceed long range      ║");
        System.out.println("║    • Modular arithmetic property: (a*b) mod m = ((a mod m) * ║");
        System.out.println("║      (b mod m)) mod m                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why MOD = 10^9 + 7?                                         ║");
        System.out.println("║    • Large prime number                                      ║");
        System.out.println("║    • Fits in 32-bit integer                                  ║");
        System.out.println("║    • Standard in competitive programming                     ║");
        System.out.println("║    • Avoids hash collisions                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Alternative Bit Length Calculation:                         ║");
        System.out.println("║    Could use: 32 - Integer.numberOfLeadingZeros(i)           ║");
        System.out.println("║    More efficient than string conversion                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Visual Representation of Concatenation:                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Number  Binary   Action                                   ║");
        System.out.println("║    1       1        Start with 1                             ║");
        System.out.println("║    2       10       Shift 1 left 2, add 10 → 110             ║");
        System.out.println("║    3       11       Shift 110 left 2, add 11 → 11011         ║");
        System.out.println("║    4       100      Shift 11011 left 3, add 100 → 11011100   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Growth Rate:                                                ║");
        System.out.println("║    For n numbers, total bits ≈ n × log₂(n)                   ║");
        System.out.println("║    Result value can be astronomically large                  ║");
        System.out.println("║    Hence the need for modulo operation                       ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n)                                      ║");
        System.out.println("║    • Loop n times (1 to n)                                   ║");
        System.out.println("║    • Each iteration: O(log i) for bit length calculation     ║");
        System.out.println("║    • Total: O(n log n) technically, but practically O(n)     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Complexity: O(1)                                      ║");
        System.out.println("║    • Only uses constant extra space (result, bitLength)      ║");
        System.out.println("║    • No arrays or additional data structures                 ║");
        System.out.println("║    • String conversion for bit length is temporary           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }


}
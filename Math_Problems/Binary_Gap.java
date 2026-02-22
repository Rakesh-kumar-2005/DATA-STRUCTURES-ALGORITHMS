package Math_Problems;

/*

Description:
  Following program computes the binary gap of an integer, defined as the longest distance between two consecutive 1-bits in its binary representation...

Problem Statement:
  -> Given a positive integer n, analyze its binary representation...
  -> Identify positions of all set bits (bits equal to 1)...
  -> Compute the distance between consecutive 1s...
  -> Return the maximum distance found...
  -> If fewer than two 1s exist, return 0...

Core Idea:
  -> A binary gap measures spacing between consecutive set bits...
  -> Distance is calculated as the difference between indices of adjacent 1s...
  -> The maximum such distance is the required result...

Approach:
  > Two Implementations Provided:

  ═════════════════════════════════════════════
  APPROACH 1: STRING-BASED SCANNING
  ═════════════════════════════════════════════

     i. Convert number to binary string...
     ii. Traverse the character array to locate '1' bits...
     iii. Use two-pointer scanning to find the next '1'...
     iv. Compute distance between indices...
     v. Track the maximum distance...

> String Conversion Logic:
  -> Integer.toBinaryString(n) converts integer to binary string...
  -> toCharArray() allows direct index access...
  -> Example:
       22 → "10110" → ['1','0','1','1','0']

> Gap Detection Logic:
  -> Pointer i marks a '1' position...
  -> Pointer j searches forward to the next '1'...
  -> Distance = j − i...
  -> Update maximum gap if larger value found...

  ═════════════════════════════════════════════
  APPROACH 2: BIT MANIPULATION (OPTIMAL)
  ═════════════════════════════════════════════

     i. Traverse all 32 bit positions...
     ii. Extract bits using right shift and AND...
     iii. Track index of previous set bit...
     iv. Compute distance between consecutive set bits...
     v. Maintain maximum distance...

> Bit Extraction Logic:
  -> ((n >> i) & 1) extracts bit at position i...
  -> If result equals 1 → set bit found...

> Tracking Logic:
  -> last stores index of previous 1-bit...
  -> maxD stores maximum gap found...
  -> When a new 1-bit appears:
       gap = currentIndex − last...

> Algorithm Steps:
  -> Initialize last = −1 and maxGap = 0...
  -> Loop through bit positions:
       * If bit is 1:
            - If last exists → compute gap...
            - Update maxGap...
            - Update last position...
  -> Return maxGap...

> Example:
  -> Input: 22
     Binary: 10110
     Positions of 1s: 1, 2, 4
     Gaps:
       1 → 2 = 1
       2 → 4 = 2
     Output: 2...

> Step Execution:
  -> Identify first 1-bit position...
  -> Continue scanning for next 1-bit...
  -> Compute and compare distances...
  -> Return maximum gap...

> Edge Cases:
  -> Only one 1-bit (e.g., powers of 2) → return 0...
  -> Adjacent 1s → gap = 1...
  -> All bits 1 → maximum gap = 1...
  -> Large gaps handled correctly...
  -> Works for any 32-bit integer...

> Comparison of Approaches:
  -> String Approach:
       ✓ Easy to understand and visualize...
       ✓ Useful for debugging...
       ✗ Requires extra memory...
       ✗ Slightly slower due to conversion...
  -> Bit Manipulation Approach:
       ✓ Constant space usage...
       ✓ Faster and efficient...
       ✓ Direct hardware-level operations...
       ✗ Less intuitive for beginners...

> Time and Space Complexity:
  -> Approach 1:
       Time: O(log n) proportional to bit length...
       Space: O(log n) for binary string storage...
  -> Approach 2:
       Time: O(1), fixed 32 iterations...
       Space: O(1), constant variables...

> Applications:
  -> Digital signal spacing analysis...
  -> Bit pattern analysis in embedded systems...
  -> Data encoding and compression schemes...
  -> Competitive programming and bit manipulation problems...

*/

public class Binary_Gap {

    private static int binaryGap1(int n) {

        char[] temp = Integer.toBinaryString(n).toCharArray();
        int ans = 0;
        int l = temp.length;

        int i = 0;
        while (i < l) {

            if (i == l - 1) {
                break;
            }
            int j = i + 1;
            while (j < l && temp[j] != '1') {
                j++;
            }

            if (j < l && temp[j] == '1') {
                ans = Math.max(ans, j - i);
            }
            i = j;
        }

        return ans;

    }

    private static int binaryGap2(int n) {

        int last = - 1;
        int maxD = 0;

        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                if (last != - 1) {
                    maxD = Math.max(maxD, i - last);
                }
                last = i;
            }
        }

        return maxD;

    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    BINARY GAP                                ║");
        System.out.println("║  Find longest distance between two consecutive 1s in binary  ║");
        System.out.println("║  Distance = number of positions between consecutive 1s       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Gap - 22 ===");
        int num1 = 22;
        String binary1 = Integer.toBinaryString(num1);
        System.out.println("Input: " + num1);
        System.out.println("Binary: " + binary1);
        System.out.println("\nBit positions (right to left, 0-indexed):");
        System.out.println("  10110");
        System.out.println("  ↑↑ ↑↑  ");
        System.out.println("  43 21  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 1, 2, 4");
        System.out.println("  Gap 1→2: distance = 1");
        System.out.println("  Gap 2→4: distance = 2 ← maximum!");
        System.out.println("\nLongest gap: 2\n");

        int result1_v1 = binaryGap1(num1);
        int result1_v2 = binaryGap2(num1);
        System.out.println("✓ String Approach Result: " + result1_v1);
        System.out.println("✓ Bit Manipulation Result: " + result1_v2);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1_v1 == 2 && result1_v2 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Longer Gap - 8 ===");
        int num2 = 8;
        String binary2 = Integer.toBinaryString(num2);
        System.out.println("Input: " + num2);
        System.out.println("Binary: " + binary2);
        System.out.println("\nAnalysis:");
        System.out.println("  1000");
        System.out.println("  ↑");
        System.out.println("  Only one 1 at position 3");
        System.out.println("\nNo consecutive 1s → gap = 0\n");

        int result2_v1 = binaryGap1(num2);
        int result2_v2 = binaryGap2(num2);
        System.out.println("✓ String Approach Result: " + result2_v1);
        System.out.println("✓ Bit Manipulation Result: " + result2_v2);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_v1 == 0 && result2_v2 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Maximum Gap - 5 ===");
        int num3 = 5;
        String binary3 = Integer.toBinaryString(num3);
        System.out.println("Input: " + num3);
        System.out.println("Binary: " + binary3);
        System.out.println("\nBit Analysis:");
        System.out.println("  101");
        System.out.println("  ↑ ↑");
        System.out.println("  2 0  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 0, 2");
        System.out.println("  Gap 0→2: distance = 2\n");

        int result3_v1 = binaryGap1(num3);
        int result3_v2 = binaryGap2(num3);
        System.out.println("✓ String Approach Result: " + result3_v1);
        System.out.println("✓ Bit Manipulation Result: " + result3_v2);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result3_v1 == 2 && result3_v2 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Adjacent 1s - 3 ===");
        int num4 = 3;
        String binary4 = Integer.toBinaryString(num4);
        System.out.println("Input: " + num4);
        System.out.println("Binary: " + binary4);
        System.out.println("\nBit Analysis:");
        System.out.println("  11");
        System.out.println("  ↑↑");
        System.out.println("  10  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 0, 1");
        System.out.println("  Gap 0→1: distance = 1 (adjacent)\n");

        int result4_v1 = binaryGap1(num4);
        int result4_v2 = binaryGap2(num4);
        System.out.println("✓ String Approach Result: " + result4_v1);
        System.out.println("✓ Bit Manipulation Result: " + result4_v2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4_v1 == 1 && result4_v2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Multiple Gaps - 6 ===");
        int num5 = 6;
        String binary5 = Integer.toBinaryString(num5);
        System.out.println("Input: " + num5);
        System.out.println("Binary: " + binary5);
        System.out.println("\nBit Analysis:");
        System.out.println("  110");
        System.out.println("  ↑↑ ");
        System.out.println("  21   positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 1, 2");
        System.out.println("  Gap 1→2: distance = 1\n");

        int result5_v1 = binaryGap1(num5);
        int result5_v2 = binaryGap2(num5);
        System.out.println("✓ String Approach Result: " + result5_v1);
        System.out.println("✓ Bit Manipulation Result: " + result5_v2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5_v1 == 1 && result5_v2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Large Gap - 529 ===");
        int num6 = 529;
        String binary6 = Integer.toBinaryString(num6);
        System.out.println("Input: " + num6);
        System.out.println("Binary: " + binary6);
        System.out.println("\nBit Analysis:");
        System.out.println("  1000010001");
        System.out.println("  ↑    ↑   ↑");
        System.out.println("  9    4   0  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 0, 4, 9");
        System.out.println("  Gap 0→4: distance = 4");
        System.out.println("  Gap 4→9: distance = 5 ← maximum!\n");

        int result6_v1 = binaryGap1(num6);
        int result6_v2 = binaryGap2(num6);
        System.out.println("✓ String Approach Result: " + result6_v1);
        System.out.println("✓ Bit Manipulation Result: " + result6_v2);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result6_v1 == 5 && result6_v2 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: All 1s - 7 ===");
        int num7 = 7;
        String binary7 = Integer.toBinaryString(num7);
        System.out.println("Input: " + num7);
        System.out.println("Binary: " + binary7);
        System.out.println("\nBit Analysis:");
        System.out.println("  111");
        System.out.println("  ↑↑↑");
        System.out.println("  210  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 0, 1, 2");
        System.out.println("  Gap 0→1: distance = 1");
        System.out.println("  Gap 1→2: distance = 1");
        System.out.println("  All gaps are minimum (adjacent)\n");

        int result7_v1 = binaryGap1(num7);
        int result7_v2 = binaryGap2(num7);
        System.out.println("✓ String Approach Result: " + result7_v1);
        System.out.println("✓ Bit Manipulation Result: " + result7_v2);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result7_v1 == 1 && result7_v2 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Extreme Gap - 1000 ===");
        int num8 = 1000;
        String binary8 = Integer.toBinaryString(num8);
        System.out.println("Input: " + num8);
        System.out.println("Binary: " + binary8);
        System.out.println("\nBit Analysis:");
        System.out.println("  1111101000");
        System.out.println("  ↑↑↑↑↑ ↑");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 3, 6, 7, 8, 9");
        System.out.println("  Gap 3→6: distance = 3 ← maximum!");
        System.out.println("  Gap 6→7: distance = 1");
        System.out.println("  Gap 7→8: distance = 1");
        System.out.println("  Gap 8→9: distance = 1\n");

        int result8_v1 = binaryGap1(num8);
        int result8_v2 = binaryGap2(num8);
        System.out.println("✓ String Approach Result: " + result8_v1);
        System.out.println("✓ Bit Manipulation Result: " + result8_v2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result8_v1 == 3 && result8_v2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Power of 2 - 16 ===");
        int num9 = 16;
        String binary9 = Integer.toBinaryString(num9);
        System.out.println("Input: " + num9);
        System.out.println("Binary: " + binary9);
        System.out.println("\nBit Analysis:");
        System.out.println("  10000");
        System.out.println("  ↑");
        System.out.println("  4  position");
        System.out.println("\nAnalysis:");
        System.out.println("  Only one 1-bit (power of 2)");
        System.out.println("  No consecutive 1s → gap = 0\n");

        int result9_v1 = binaryGap1(num9);
        int result9_v2 = binaryGap2(num9);
        System.out.println("✓ String Approach Result: " + result9_v1);
        System.out.println("✓ Bit Manipulation Result: " + result9_v2);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result9_v1 == 0 && result9_v2 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 10: Complex Pattern - 74 ===");
        int num10 = 74;
        String binary10 = Integer.toBinaryString(num10);
        System.out.println("Input: " + num10);
        System.out.println("Binary: " + binary10);
        System.out.println("\nBit Analysis:");
        System.out.println("  1001010");
        System.out.println("  ↑  ↑ ↑");
        System.out.println("  6  3 1  positions");
        System.out.println("\nAnalysis:");
        System.out.println("  1s at positions: 1, 3, 6");
        System.out.println("  Gap 1→3: distance = 2");
        System.out.println("  Gap 3→6: distance = 3 ← maximum!\n");

        int result10_v1 = binaryGap1(num10);
        int result10_v2 = binaryGap2(num10);
        System.out.println("✓ String Approach Result: " + result10_v1);
        System.out.println("✓ Bit Manipulation Result: " + result10_v2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result10_v1 == 3 && result10_v2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Find the longest distance between two consecutive 1-bits  ║");
        System.out.println("║    in the binary representation of a number                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two Implementation Approaches:                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 1: STRING-BASED (binaryGap1)                       ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Convert to Binary String                            ║");
        System.out.println("║    Integer.toBinaryString(n).toCharArray()                   ║");
        System.out.println("║    • Converts number to binary string                        ║");
        System.out.println("║    • Converts string to char array for easy access           ║");
        System.out.println("║    • Example: 22 → \"10110\" → ['1','0','1','1','0']           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: Two-Pointer Technique                               ║");
        System.out.println("║    • i: position of current '1'                              ║");
        System.out.println("║    • j: searches for next '1'                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 3: Find Next '1'                                       ║");
        System.out.println("║    while (j < l && temp[j] != '1')                           ║");
        System.out.println("║      j++                                                     ║");
        System.out.println("║    • Skip all '0's between consecutive '1's                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 4: Calculate Gap                                       ║");
        System.out.println("║    if (j < l && temp[j] == '1')                              ║");
        System.out.println("║      ans = Math.max(ans, j - i)                              ║");
        System.out.println("║    • Distance = j - i (index difference)                     ║");
        System.out.println("║    • Update max if larger gap found                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: n = 22 (\"10110\")                                   ║");
        System.out.println("║    Array: ['1','0','1','1','0']                              ║");
        System.out.println("║    Index:   0   1   2   3   4                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=0, j=2: gap = 2-0 = 2, ans=2                            ║");
        System.out.println("║    i=2, j=3: gap = 3-2 = 1, ans=2                            ║");
        System.out.println("║    i=3, j=5: out of bounds, stop                             ║");
        System.out.println("║    Result: 2                                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║  APPROACH 2: BIT MANIPULATION (binaryGap2)                   ║");
        System.out.println("║  ═══════════════════════════════════════════════════════════ ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Variables:                                              ║");
        System.out.println("║    • last: position of previous '1' bit (-1 initially)       ║");
        System.out.println("║    • maxD: maximum distance found                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Bit Extraction:                                             ║");
        System.out.println("║    ((n >> i) & 1) == 1                                       ║");
        System.out.println("║    • n >> i: shift right by i positions                      ║");
        System.out.println("║    • & 1: extract rightmost bit                              ║");
        System.out.println("║    • Check if it's 1                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Flow:                                             ║");
        System.out.println("║    for i = 0 to 31:                                          ║");
        System.out.println("║      if bit at position i is 1:                              ║");
        System.out.println("║        if last != -1:                                        ║");
        System.out.println("║          maxD = max(maxD, i - last)                          ║");
        System.out.println("║        last = i                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: n = 22 (binary: 10110)                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=0: 0 → skip                                             ║");
        System.out.println("║    i=1: 1 → last=-1, so set last=1                           ║");
        System.out.println("║    i=2: 1 → maxD=max(0, 2-1)=1, last=2                       ║");
        System.out.println("║    i=3: 0 → skip                                             ║");
        System.out.println("║    i=4: 1 → maxD=max(1, 4-2)=2, last=4                       ║");
        System.out.println("║    i=5-31: 0 → skip                                          ║");
        System.out.println("║    Result: 2                                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Check All 32 Bits?                                      ║");
        System.out.println("║    • Java int is 32-bit signed                               ║");
        System.out.println("║    • Ensures all bits are checked                            ║");
        System.out.println("║    • Works for any int value                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Comparison of Approaches:                                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║    String Approach:                                          ║");
        System.out.println("║      ✓ More intuitive and readable                           ║");
        System.out.println("║      ✓ Easier to debug visually                              ║");
        System.out.println("║      ✗ Extra space for string/array                          ║");
        System.out.println("║      ✗ String conversion overhead                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Bit Manipulation:                                         ║");
        System.out.println("║      ✓ More space efficient (O(1))                           ║");
        System.out.println("║      ✓ No string conversion needed                           ║");
        System.out.println("║      ✓ Direct bit operations                                 ║");
        System.out.println("║      ✗ Less intuitive for beginners                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Edge Cases:                                                 ║");
        System.out.println("║    • Single 1-bit (powers of 2): return 0                    ║");
        System.out.println("║    • Adjacent 1s: return 1 (minimum gap)                     ║");
        System.out.println("║    • All 1s: return 1 (all adjacent)                         ║");
        System.out.println("║    • Two 1s far apart: return their distance                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:                                            ║");
        System.out.println("║    Approach 1: O(log n) - String length proportional to bits ║");
        System.out.println("║    Approach 2: O(1) - Always 32 iterations                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Complexity:                                           ║");
        System.out.println("║    Approach 1: O(log n) - String and char array storage      ║");
        System.out.println("║    Approach 2: O(1) - Only a few variables                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

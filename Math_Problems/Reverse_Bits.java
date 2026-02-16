package Math_Problems;

/*

Description:
  Following program demonstrates how to reverse the binary representation of a 32-bit integer using bitwise operations...

Problem Statement:
  -> Given a 32-bit signed integer, reverse its binary bits...
  -> Bit at position i must move to position (31 − i)...
  -> Return the integer formed after reversing all 32 bits...
  -> The result must preserve the full 32-bit pattern including the sign bit...

Core Idea:
  -> Read bits from right to left (least significant to most significant)...
  -> Build the result from left to right by shifting and inserting bits...
  -> Use bitwise operators for efficient extraction and placement...

Approach:
  > Bit Manipulation Technique:
     i. Initialize answer = 0 to store reversed bits...
     ii. Iterate through all 32 bit positions...
     iii. Extract the i-th bit from the number...
     iv. Shift answer left to make room for the new bit...
     v. Insert extracted bit into answer using OR...

> Bit Extraction Logic:
  -> (number >> i) shifts the desired bit to the rightmost position...
  -> & 1 masks all other bits, leaving only the extracted bit...

> Bit Placement Logic:
  -> answer << 1 shifts result left to create space...
  -> OR operation inserts the extracted bit at rightmost position...

> Algorithm Steps:
  -> Initialize answer = 0...
  -> Loop from i = 0 to 31:
       * Extract bit = (number >> i) & 1...
       * Left shift answer by 1...
       * Insert bit using OR...
  -> Return the final reversed integer...

> Supporting Utility:
  -> toBinaryString32():
       * Prints a 32-bit formatted binary representation...
       * Groups bits into bytes for readability...
  -> demonstrateBitReversal():
       * Shows step-by-step bit extraction and building of result...

> Implementation Notes:
  -> Loop runs exactly 32 times because Java int is 32-bit...
  -> Handles both positive and negative numbers...
  -> Sign bit is treated like any other bit during reversal...
  -> Bitwise operations ensure constant time performance...

> Example:
  -> Input: 43261596
     Binary:
     00000010 10010100 00011110 10011100
  -> Reversed:
     00111001 01111000 00101001 01000000
  -> Output: 964176192...

> Step Execution (first few iterations):
  -> i=0: extract LSB → append to answer...
  -> i=1: extract next bit → shift & append...
  -> Process continues until all 32 bits are reversed...

> Edge Cases:
  -> Input 0 → result remains 0...
  -> Input -1 (all bits set) → remains unchanged...
  -> Single bit set (e.g., 1) moves to highest position...
  -> Alternating bit patterns reverse correctly...
  -> Maximum integer may become negative after reversal...

> Time and Space Complexity:
  -> Time Complexity: O(1), exactly 32 iterations...
  -> Space Complexity: O(1), uses constant extra space...

> Applications:
  -> Low-level data manipulation and network protocols...
  -> Cryptography and hashing algorithms...
  -> Image processing and signal transformation...
  -> Embedded systems and hardware-level programming...

*/

public class Reverse_Bits {

    private static int reverseBits(int number) {
        int answer = 0;

        for (int i = 0; i < 32; i++) {
            answer = (answer << 1) | ((number >> i) & 1);
        }

        return answer;
    }


    private static String toBinaryString32(int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 31; i >= 0; i--) {
            sb.append((n >> i) & 1);
            if (i % 8 == 0 && i != 0) sb.append(" ");
        }

        return sb.toString();
    }

    private static void demonstrateBitReversal(int number, int steps) {

        System.out.println("  First " + steps + " iterations:");
        int answer = 0;

        for (int i = 0; i < steps; i++) {
            int bit = (number >> i) & 1;
            answer = (answer << 1) | bit;
            System.out.println("    i=" + i + ": extract bit " + bit +
                " → answer = " + String.format("%8s", Integer.toBinaryString(answer)).replace(' ', '0'));
        }

        System.out.println();
    }


    public static void main(String[] args) {
      
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    REVERSE BITS                              ║");
        System.out.println("║  Reverse the binary representation of a 32-bit integer       ║");
        System.out.println("║  Bit at position i moves to position (31-i)                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Binary Number ===");
        int num1 = 43261596;
        System.out.println("Input: " + num1);
        System.out.println("\nBinary Representation (32-bit):");
        System.out.println("  Original: " + toBinaryString32(num1));
        System.out.println("  Decimal:  " + num1 + "\n");
        System.out.println("Step-by-step reversal:");
        System.out.println("  Read bits right-to-left");
        System.out.println("  Write bits left-to-right\n");

        int result1 = reverseBits(num1);
        System.out.println("  Reversed: " + toBinaryString32(result1));
        System.out.println("  Decimal:  " + result1 + "\n");
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 964176192");
        System.out.println("  Status: " + (result1 == 964176192 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: All Ones (Maximum Value) ===");
        int num2 = - 1;
        System.out.println("Input: " + num2 + " (all bits set to 1)");
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num2));
        System.out.println("  11111111111111111111111111111111 (32 ones)\n");
        System.out.println("Analysis:");
        System.out.println("  All bits are 1");
        System.out.println("  Reversing doesn't change anything");
        System.out.println("  Result: same as input\n");

        int result2 = reverseBits(num2);
        System.out.println("  Reversed: " + toBinaryString32(result2));
        System.out.println("\n✓ Result: " + result2);
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result2 == - 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Zero ===");
        int num3 = 0;
        System.out.println("Input: " + num3);
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num3));
        System.out.println("  00000000000000000000000000000000 (32 zeros)\n");
        System.out.println("Analysis:");
        System.out.println("  All bits are 0");
        System.out.println("  Reversing gives all 0s");
        System.out.println("  Result: 0\n");

        int result3 = reverseBits(num3);
        System.out.println("  Reversed: " + toBinaryString32(result3));
        System.out.println("\n✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Power of 2 (Single Bit Set) ===");
        int num4 = 1;
        System.out.println("Input: " + num4 + " (2^0)");
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num4));
        System.out.println("  Position: Rightmost bit (index 0)\n");
        System.out.println("Reversal Process:");
        System.out.println("  Bit at position 0 → position 31");
        System.out.println("  All other bits remain 0\n");

        int result4 = reverseBits(num4);
        System.out.println("  Reversed: " + toBinaryString32(result4));
        System.out.println("  Position: Leftmost bit (index 31)\n");
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: " + (1 << 31) + " (2^31, negative in signed int)");
        System.out.println("  Status: " + (result4 == (1 << 31) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Small Number with Few Bits ===");
        int num5 = 5;
        System.out.println("Input: " + num5);
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num5));
        System.out.println("  Binary:   00000000000000000000000000000101");
        System.out.println("                                          ^^");
        System.out.println("  Positions: bits at index 0 and 2\n");
        System.out.println("Reversal Process:");
        System.out.println("  Bit 0 (value 1) → position 31");
        System.out.println("  Bit 2 (value 1) → position 29");
        System.out.println("  Result: 101...000 (reversed)\n");

        int result5 = reverseBits(num5);
        System.out.println("  Reversed: " + toBinaryString32(result5));
        System.out.println("\n✓ Result: " + result5);
        System.out.println("  Binary shows 1s at positions 31 and 29");
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 6: Alternating Bits Pattern ===");
        int num6 = 0b10101010101010101010101010101010;
        System.out.println("Input: " + num6);
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num6));
        System.out.println("  Pattern: 10101010... (alternating)\n");
        System.out.println("Analysis:");
        System.out.println("  Every other bit is set");
        System.out.println("  Reversing gives: 01010101...");
        System.out.println("  Pattern flips but remains alternating\n");

        int result6 = reverseBits(num6);
        System.out.println("  Reversed: " + toBinaryString32(result6));
        System.out.println("\n✓ Result: " + result6);
        System.out.println("  Expected: " + 0b01010101010101010101010101010101);
        System.out.println("  Status: " + (result6 == 0b01010101010101010101010101010101 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Maximum Positive Integer ===");
        int num7 = Integer.MAX_VALUE;
        System.out.println("Input: " + num7 + " (Integer.MAX_VALUE)");
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num7));
        System.out.println("  Pattern: 01111111111111111111111111111111");
        System.out.println("           ^-- Sign bit is 0 (positive)\n");
        System.out.println("Analysis:");
        System.out.println("  31 ones with leading zero");
        System.out.println("  Reversed: 1111...1110");
        System.out.println("  Leading 1 makes it negative in signed int\n");

        int result7 = reverseBits(num7);
        System.out.println("  Reversed: " + toBinaryString32(result7));
        System.out.println("\n✓ Result: " + result7);
        System.out.println("  Expected: -2 (11111111111111111111111111111110)");
        System.out.println("  Status: " + (result7 == - 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Byte-Sized Pattern ===");
        int num8 = 0b11110000111100001111000011110000;
        System.out.println("Input: " + num8);
        System.out.println("\nBinary Representation:");
        System.out.println("  Original: " + toBinaryString32(num8));
        System.out.println("  Pattern: 4 ones, 4 zeros (repeated 4 times)\n");
        System.out.println("Bit-by-bit reversal:");
        demonstrateBitReversal(num8, 8);

        int result8 = reverseBits(num8);
        System.out.println("  Reversed: " + toBinaryString32(result8));
        System.out.println("\n✓ Result: " + result8);
        System.out.println("  Pattern reversed: 0000111100001111...");
        System.out.println("  Status: PASS ✓\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Core Algorithm: Extract, Shift, Combine                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Three Key Operations:                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. EXTRACT bit at position i:                               ║");
        System.out.println("║     (number >> i) & 1                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║     • number >> i: Shift right by i positions                ║");
        System.out.println("║     • & 1: Mask to get only the rightmost bit                ║");
        System.out.println("║                                                              ║");
        System.out.println("║     Example: Extract bit at position 2 from 0101             ║");
        System.out.println("║       0101 >> 2 = 0001                                       ║");
        System.out.println("║       0001 & 1  = 0001 → bit is 1                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. SHIFT answer left:                                       ║");
        System.out.println("║     answer << 1                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║     • Makes room for next bit on the right                   ║");
        System.out.println("║     • Builds result from left to right                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║     Example: answer = 0011                                   ║");
        System.out.println("║       0011 << 1 = 0110                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  3. COMBINE using OR:                                        ║");
        System.out.println("║     answer = (answer << 1) | bit                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║     • OR sets the rightmost bit                              ║");
        System.out.println("║     • Doesn't affect other bits                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║     Example: 0110 | 1 = 0111                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complete Operation Per Iteration:                           ║");
        System.out.println("║    answer = (answer << 1) | ((number >> i) & 1)              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step-by-Step Example: Reverse 1011 (4-bit for clarity)      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Initial: number = 1011, answer = 0000                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=0: Extract rightmost bit (1)                            ║");
        System.out.println("║      • (1011 >> 0) & 1 = 1011 & 1 = 1                        ║");
        System.out.println("║      • answer = (0000 << 1) | 1 = 0001                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=1: Extract second bit (1)                               ║");
        System.out.println("║      • (1011 >> 1) & 1 = 0101 & 1 = 1                        ║");
        System.out.println("║      • answer = (0001 << 1) | 1 = 0011                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=2: Extract third bit (0)                                ║");
        System.out.println("║      • (1011 >> 2) & 1 = 0010 & 1 = 0                        ║");
        System.out.println("║      • answer = (0011 << 1) | 0 = 0110                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=3: Extract fourth bit (1)                               ║");
        System.out.println("║      • (1011 >> 3) & 1 = 0001 & 1 = 1                        ║");
        System.out.println("║      • answer = (0110 << 1) | 1 = 1101                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Result: 1011 reversed → 1101 ✓                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why 32 Iterations?                                          ║");
        System.out.println("║    • Java int is 32-bit signed integer                       ║");
        System.out.println("║    • Must process all 32 bits including sign bit             ║");
        System.out.println("║    • Even if number is small, we reverse full 32-bit pattern ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Sign Bit Considerations:                                    ║");
        System.out.println("║    • Positive numbers: sign bit (bit 31) = 0                 ║");
        System.out.println("║    • After reversal: bit 0 becomes bit 31                    ║");
        System.out.println("║    • Can change sign of result!                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Bitwise Operator Reference:                                 ║");
        System.out.println("║    >>  Right shift (arithmetic, preserves sign)              ║");
        System.out.println("║    <<  Left shift                                            ║");
        System.out.println("║    &   Bitwise AND                                           ║");
        System.out.println("║    |   Bitwise OR                                            ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(1) - Always exactly 32 iterations       ║");
        System.out.println("║  Space Complexity: O(1) - Only one integer variable used     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Applications: Network protocols, image processing,          ║");
        System.out.println("║                cryptography, data compression                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

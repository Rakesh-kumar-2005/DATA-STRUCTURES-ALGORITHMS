package Math_Problems;

public class Number_Of_Steps_To_Reduce_A_Number_In_Binary_Representation_To_One {

    private static int numSteps(String str) {
        int steps = 0;
        int carry = 0;

        for (int i = str.length() - 1; i > 0; i--) {
            int digit = (str.charAt(i) - '0') + carry;

            if (digit == 1) {
                steps += 2;
                carry = 1;
            } else {
                steps++;
            }
        }
        return steps + carry;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  NUMBER OF STEPS TO REDUCE BINARY NUMBER TO ONE              ║");
        System.out.println("║  Operations: divide by 2 (if even) or add 1 (if odd)         ║");
        System.out.println("║  Goal: Reduce to 1 in minimum steps                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Binary \"1101\" ===");
        String s1 = "1101";
        System.out.println("Binary: " + s1 + " (decimal: " + Integer.parseInt(s1, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  1101 (13) → odd → add 1");
        System.out.println("  1110 (14) → even → divide by 2");
        System.out.println("  0111 (7)  → odd → add 1");
        System.out.println("  1000 (8)  → even → divide by 2");
        System.out.println("  0100 (4)  → even → divide by 2");
        System.out.println("  0010 (2)  → even → divide by 2");
        System.out.println("  0001 (1)  → DONE!");
        System.out.println("\nTotal steps: 6\n");

        int result1 = numSteps(s1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result1 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Power of 2 \"1000\" ===");
        String s2 = "1000";
        System.out.println("Binary: " + s2 + " (decimal: " + Integer.parseInt(s2, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  1000 (8) → even → divide by 2");
        System.out.println("  0100 (4) → even → divide by 2");
        System.out.println("  0010 (2) → even → divide by 2");
        System.out.println("  0001 (1) → DONE!");
        System.out.println("\nPower of 2: only divisions needed");
        System.out.println("Total steps: 3\n");

        int result2 = numSteps(s2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Already \"1\" ===");
        String s3 = "1";
        System.out.println("Binary: " + s3 + " (decimal: 1)");
        System.out.println("\nAlready at target!");
        System.out.println("Total steps: 0\n");

        int result3 = numSteps(s3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Small Odd \"11\" ===");
        String s4 = "11";
        System.out.println("Binary: " + s4 + " (decimal: " + Integer.parseInt(s4, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  11 (3) → odd → add 1");
        System.out.println("  100 (4) → even → divide by 2");
        System.out.println("  10 (2) → even → divide by 2");
        System.out.println("  1 (1) → DONE!");
        System.out.println("\nTotal steps: 3\n");

        int result4 = numSteps(s4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result4 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Ones \"1111\" ===");
        String s5 = "1111";
        System.out.println("Binary: " + s5 + " (decimal: " + Integer.parseInt(s5, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  1111 (15) → odd → add 1");
        System.out.println("  10000 (16) → even → divide by 2");
        System.out.println("  1000 (8) → even → divide by 2");
        System.out.println("  100 (4) → even → divide by 2");
        System.out.println("  10 (2) → even → divide by 2");
        System.out.println("  1 (1) → DONE!");
        System.out.println("\nPattern: all 1s → add 1 creates power of 2");
        System.out.println("Total steps: 5\n");

        int result5 = numSteps(s5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result5 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Alternating \"10101\" ===");
        String s6 = "10101";
        System.out.println("Binary: " + s6 + " (decimal: " + Integer.parseInt(s6, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  10101 (21) → odd → add 1");
        System.out.println("  10110 (22) → even → divide by 2");
        System.out.println("  1011 (11) → odd → add 1");
        System.out.println("  1100 (12) → even → divide by 2");
        System.out.println("  110 (6) → even → divide by 2");
        System.out.println("  11 (3) → odd → add 1");
        System.out.println("  100 (4) → even → divide by 2");
        System.out.println("  10 (2) → even → divide by 2");
        System.out.println("  1 (1) → DONE!");
        System.out.println("\nTotal steps: 8\n");

        int result6 = numSteps(s6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 8");
        System.out.println("  Status: " + (result6 == 8 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Even Number \"10110\" ===");
        String s7 = "10110";
        System.out.println("Binary: " + s7 + " (decimal: " + Integer.parseInt(s7, 2) + ")");
        System.out.println("\nStep-by-step simulation:");
        System.out.println("  10110 (22) → even → divide by 2");
        System.out.println("  1011 (11) → odd → add 1");
        System.out.println("  1100 (12) → even → divide by 2");
        System.out.println("  110 (6) → even → divide by 2");
        System.out.println("  11 (3) → odd → add 1");
        System.out.println("  100 (4) → even → divide by 2");
        System.out.println("  10 (2) → even → divide by 2");
        System.out.println("  1 (1) → DONE!");
        System.out.println("\nTotal steps: 7\n");

        int result7 = numSteps(s7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result7 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Large Binary \"11011\" ===");
        String s8 = "11011";
        System.out.println("Binary: " + s8 + " (decimal: " + Integer.parseInt(s8, 2) + ")");
        System.out.println("\nAnalysis:");
        System.out.println("  Multiple 1s at end → multiple add operations");
        System.out.println("  Creates carry propagation\n");

        int result8 = numSteps(s8);
        System.out.println("✓ Result: " + result8);
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 9: Long Binary \"10000000\" ===");
        String s9 = "10000000";
        System.out.println("Binary: " + s9 + " (decimal: " + Integer.parseInt(s9, 2) + ")");
        System.out.println("\nAnalysis:");
        System.out.println("  Power of 2 (128)");
        System.out.println("  Only needs divisions");
        System.out.println("  Steps = number of trailing zeros = 7\n");

        int result9 = numSteps(s9);
        System.out.println("✓ Result: " + result9);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result9 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Given binary string, reduce to \"1\" using operations:      ║");
        System.out.println("║    • If even (ends in 0): divide by 2 (remove last digit)    ║");
        System.out.println("║    • If odd (ends in 1): add 1                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Naive Approach Problems:                                    ║");
        System.out.println("║    • Actually performing operations is slow                  ║");
        System.out.println("║    • Adding 1 to binary requires carry propagation           ║");
        System.out.println("║    • String manipulation is expensive                        ║");
        System.out.println("║    • Converting to/from integer has limits                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight - Binary Operations:                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Divide by 2 (even number):                                  ║");
        System.out.println("║    • Binary: right shift (remove rightmost 0)                ║");
        System.out.println("║    • Example: 1010 → 101 (10 → 5)                            ║");
        System.out.println("║    • Cost: 1 step                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Add 1 (odd number):                                         ║");
        System.out.println("║    • Binary: flip rightmost 1 to 0, carry left               ║");
        System.out.println("║    • Example: 1011 + 1 = 1100 (11 + 1 = 12)                  ║");
        System.out.println("║    • Then divide by 2: 1100 → 110                            ║");
        System.out.println("║    • Cost: 2 steps (add 1, then divide)                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Clever Optimization - Process Right to Left:                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Instead of simulating, analyze the pattern:                 ║");
        System.out.println("║    • Process from right to left (LSB to MSB)                 ║");
        System.out.println("║    • Track carry from previous add operation                 ║");
        System.out.println("║    • Calculate steps without actual operations               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Digit Analysis with Carry:                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Current bit + carry = 0 (even):                             ║");
        System.out.println("║    • Just divide by 2                                        ║");
        System.out.println("║    • Steps += 1                                              ║");
        System.out.println("║    • Carry remains 0                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Current bit + carry = 1 (odd):                              ║");
        System.out.println("║    • Add 1 (creates carry)                                   ║");
        System.out.println("║    • Then divide by 2                                        ║");
        System.out.println("║    • Steps += 2                                              ║");
        System.out.println("║    • Carry becomes 1                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Current bit + carry = 2 (0 + carry):                        ║");
        System.out.println("║    • Result is even (carry + 0 = 10 in binary)               ║");
        System.out.println("║    • Just divide by 2                                        ║");
        System.out.println("║    • Steps += 1                                              ║");
        System.out.println("║    • Carry remains 1                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Walkthrough: \"1101\" (13)                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Initial: carry = 0, steps = 0                             ║");
        System.out.println("║    Process right to left (skip leftmost bit):                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=3 (rightmost): digit = 1 + 0 = 1 (odd)                  ║");
        System.out.println("║      → steps += 2 (add 1, divide)                            ║");
        System.out.println("║      → carry = 1                                             ║");
        System.out.println("║      → steps = 2                                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=2: digit = 0 + 1 = 1 (odd due to carry)                 ║");
        System.out.println("║      → steps += 2                                            ║");
        System.out.println("║      → carry = 1                                             ║");
        System.out.println("║      → steps = 4                                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    i=1: digit = 1 + 1 = 2 (even)                             ║");
        System.out.println("║      → steps += 1 (just divide)                              ║");
        System.out.println("║      → carry stays 1                                         ║");
        System.out.println("║      → steps = 5                                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Final: steps + carry = 5 + 1 = 6                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Add Final Carry?                                        ║");
        System.out.println("║    If carry = 1 after processing:                            ║");
        System.out.println("║      → Last operation created carry beyond MSB               ║");
        System.out.println("║      → Need one more division to remove it                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Skip Leftmost Bit?                                      ║");
        System.out.println("║    Loop: for (i = length-1; i > 0; i--)                      ║");
        System.out.println("║    • Leftmost bit (i=0) is always 1 in valid binary          ║");
        System.out.println("║    • We stop when number becomes 1                           ║");
        System.out.println("║    • Processing leftmost would add unnecessary step          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Pattern Recognition:                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Power of 2 (e.g., \"1000\"):                                  ║");
        System.out.println("║    • All bits after first are 0                              ║");
        System.out.println("║    • No carry needed                                         ║");
        System.out.println("║    • Steps = number of zeros = log₂(n)                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  All ones (e.g., \"1111\"):                                    ║");
        System.out.println("║    • Every bit is 1                                          ║");
        System.out.println("║    • Constant carry propagation                              ║");
        System.out.println("║    • First add creates power of 2                            ║");
        System.out.println("║    • Then just divisions                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Character to Digit Conversion:                              ║");
        System.out.println("║    str.charAt(i) - '0'                                       ║");
        System.out.println("║    • '0' has ASCII 48, '1' has ASCII 49                      ║");
        System.out.println("║    • '0' - '0' = 48 - 48 = 0                                 ║");
        System.out.println("║    • '1' - '0' = 49 - 48 = 1                                 ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n) where n = length of binary string    ║");
        System.out.println("║    • Single pass through string (right to left)              ║");
        System.out.println("║    • Constant work per character                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Complexity: O(1)                                      ║");
        System.out.println("║    • Only two variables: steps and carry                     ║");
        System.out.println("║    • No additional data structures needed                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Advantages over Simulation:                                 ║");
        System.out.println("║    ✓ No string manipulation needed                           ║");
        System.out.println("║    ✓ No actual arithmetic operations                         ║");
        System.out.println("║    ✓ Works for arbitrarily large binary numbers              ║");
        System.out.println("║    ✓ O(n) instead of O(n × steps) for simulation             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
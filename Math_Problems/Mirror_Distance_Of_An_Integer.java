package Math_Problems;

/*

    Description:
      Following program calculates the mirror distance of an integer,
        defined as the absolute difference between the number and its digit-reversal...

    Problem Statement:
      -> Given a positive integer n...
      -> Compute the reverse of its digits...
      -> Return the absolute difference: |n - reverse(n)|...

    Key Insight:
      -> Mirror distance measures how far a number is from being a palindrome...
      -> If n is a palindrome, reverse(n) == n → distance = 0...
      -> Trailing zeros in n become leading zeros in reverse, which are dropped...
      -> Math.abs() ensures a positive result regardless of which value is larger...

    Example:
      -> n = 123:
           Reverse = 321...
           Distance = |123 - 321| = 198...
      -> n = 121:
           Reverse = 121 (palindrome)...
           Distance = |121 - 121| = 0...
      -> n = 1000:
           Reverse = 1 (trailing zeros dropped as leading zeros)...
           Distance = |1000 - 1| = 999...

    Reverse Algorithm:
      -> Initialize rev = 0, temp = n...
      -> While temp > 0:
           Extract last digit: digit = temp % 10...
           Append to reverse: rev = rev * 10 + digit...
           Remove last digit: temp = temp / 10...
      -> Each iteration shifts rev left by one decimal place and appends next digit...

    Step-by-Step Trace (n = 123):
      -> Initial:      temp = 123, rev = 0...
      -> Iteration 1:  digit = 3,  rev = 0  * 10 + 3 = 3,   temp = 12...
      -> Iteration 2:  digit = 2,  rev = 3  * 10 + 2 = 32,  temp = 1...
      -> Iteration 3:  digit = 1,  rev = 32 * 10 + 1 = 321, temp = 0...
      -> Loop ends: rev = 321...
      -> Distance = |123 - 321| = 198...

    Why Math.abs()?
      -> n may be greater than reverse(n): 123 → reverse 321, diff is negative...
      -> n may be less than reverse(n):    987 → reverse 789, diff is positive...
      -> Math.abs() handles both cases and always returns a non-negative distance...

    Trailing Zeros Behavior:
      -> Integer reversal naturally drops leading zeros in the result...
      -> n = 1000: digits are 1, 0, 0, 0 → reversed builds 0, 0, 0, 1 = 1...
      -> This is correct integer behavior, not a bug...

    Edge Cases:
      -> Single digit number → reverse equals itself → distance = 0...
      -> Palindrome (121, 1331) → reverse equals itself → distance = 0...
      -> Number with trailing zeros (1000) → reverse loses leading zeros → large distance...
      -> Two-digit number (45) → reverse is 54 → distance = |45 - 54| = 9...

    Palindrome Connection:
      -> Mirror distance = 0 if and only if the number is a palindrome...
      -> Larger mirror distance implies greater asymmetry in the digit sequence...
      -> Example: 12345 → reverse 54321 → distance = 41976 (highly asymmetric)...

    Time and Space Complexity:
      -> Time:  O(d) where d = number of digits in n...
      -> Space: O(1) — only a constant number of integer variables used...

    Applications:
      -> Palindrome detection and distance measurement...
      -> Number theory and digit manipulation problems...
      -> Symmetry analysis in mathematical sequences...
      -> Competitive programming digit-based problem patterns...

*/

public class Mirror_Distance_Of_An_Integer {

    private static int mirrorDistance(int n) {

        int temp = n;
        int rev = 0;

        while (temp > 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }

        return Math.abs(n - rev);
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            MIRROR DISTANCE OF AN INTEGER                     ║");
        System.out.println("║  Find absolute difference between number and its reverse     ║");
        System.out.println("║  Mirror distance = |n - reverse(n)|                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Simple Number ===");
        int n1 = 123;
        System.out.println("Number: " + n1);
        System.out.println("\nReverse calculation:");
        System.out.println("  123 → extract 3: rev = 0*10 + 3 = 3");
        System.out.println("  12  → extract 2: rev = 3*10 + 2 = 32");
        System.out.println("  1   → extract 1: rev = 32*10 + 1 = 321");
        System.out.println("\nReverse: 321");
        System.out.println("Distance: |123 - 321| = 198\n");

        int result1 = mirrorDistance(n1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 198");
        System.out.println("  Status: " + (result1 == 198 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Palindrome ===");
        int n2 = 121;
        System.out.println("Number: " + n2);
        System.out.println("\nReverse: 121 (palindrome)");
        System.out.println("Distance: |121 - 121| = 0\n");

        int result2 = mirrorDistance(n2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Digit ===");
        int n3 = 7;
        System.out.println("Number: " + n3);
        System.out.println("\nReverse: 7 (single digit)");
        System.out.println("Distance: |7 - 7| = 0\n");

        int result3 = mirrorDistance(n3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Reverse Greater ===");
        int n4 = 987;
        System.out.println("Number: " + n4);
        System.out.println("\nReverse: 789");
        System.out.println("Distance: |987 - 789| = 198\n");

        int result4 = mirrorDistance(n4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 198");
        System.out.println("  Status: " + (result4 == 198 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: With Trailing Zeros ===");
        int n5 = 1000;
        System.out.println("Number: " + n5);
        System.out.println("\nReverse calculation:");
        System.out.println("  1000 → extract 0: rev = 0");
        System.out.println("  100  → extract 0: rev = 0");
        System.out.println("  10   → extract 0: rev = 0");
        System.out.println("  1    → extract 1: rev = 1");
        System.out.println("\nReverse: 1 (leading zeros dropped)");
        System.out.println("Distance: |1000 - 1| = 999\n");

        int result5 = mirrorDistance(n5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 999");
        System.out.println("  Status: " + (result5 == 999 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Large Number ===");
        int n6 = 12345;
        System.out.println("Number: " + n6);
        System.out.println("\nReverse: 54321");
        System.out.println("Distance: |12345 - 54321| = 41976\n");

        int result6 = mirrorDistance(n6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 41976");
        System.out.println("  Status: " + (result6 == 41976 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Two Digits ===");
        int n7 = 45;
        System.out.println("Number: " + n7);
        System.out.println("\nReverse: 54");
        System.out.println("Distance: |45 - 54| = 9\n");

        int result7 = mirrorDistance(n7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 9");
        System.out.println("  Status: " + (result7 == 9 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Calculate |n - reverse(n)|                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Reverse Algorithm:                                          ║");
        System.out.println("║    Initialize: rev = 0                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    While temp > 0:                                           ║");
        System.out.println("║      1. Extract last digit: temp % 10                        ║");
        System.out.println("║      2. Add to reverse: rev = rev * 10 + digit               ║");
        System.out.println("║      3. Remove last digit: temp /= 10                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: n = 123                                            ║");
        System.out.println("║    Initial: temp=123, rev=0                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Iteration 1:                                              ║");
        System.out.println("║      digit = 123 % 10 = 3                                    ║");
        System.out.println("║      rev = 0 * 10 + 3 = 3                                    ║");
        System.out.println("║      temp = 123 / 10 = 12                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Iteration 2:                                              ║");
        System.out.println("║      digit = 12 % 10 = 2                                     ║");
        System.out.println("║      rev = 3 * 10 + 2 = 32                                   ║");
        System.out.println("║      temp = 12 / 10 = 1                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Iteration 3:                                              ║");
        System.out.println("║      digit = 1 % 10 = 1                                      ║");
        System.out.println("║      rev = 32 * 10 + 1 = 321                                 ║");
        System.out.println("║      temp = 1 / 10 = 0                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Result: rev = 321                                         ║");
        System.out.println("║    Distance: |123 - 321| = 198                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Special Cases:                                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Palindromes:                                                ║");
        System.out.println("║    n = reverse(n) → distance = 0                             ║");
        System.out.println("║    Examples: 121, 1331, 7                                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Trailing Zeros:                                             ║");
        System.out.println("║    1000 → reverse = 1 (not 0001)                             ║");
        System.out.println("║    Leading zeros dropped in integer reversal                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Use Math.abs()?                                         ║");
        System.out.println("║    Ensures positive result regardless of which is larger     ║");
        System.out.println("║    Example: 123 → 321 or 987 → 789                           ║");
        System.out.println("║             Both give positive distance                      ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(d) where d = number of digits in n               ║");
        System.out.println("║    Space: O(1) - Only constant variables used                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

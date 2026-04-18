package Math_Problems;

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
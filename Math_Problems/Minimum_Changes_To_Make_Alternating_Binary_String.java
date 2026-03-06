package Math_Problems;

/*

Description:
  Following program demonstrates the solution to the "Minimum Changes To Make Alternating Binary String" problem by determining the minimum number of character flips required to convert a binary string into an alternating pattern...

Problem Statement:
  -> You are given a binary string consisting of characters '0' and '1'...
  -> A binary string is considered alternating if no two adjacent characters are the same...
  -> Two possible alternating patterns exist:
       • "010101..."
       • "101010..."
  -> The goal is to determine the minimum number of character changes needed to transform the given string into one of these valid alternating patterns...

Approach:
  > Two methods are implemented:
     i. Optimized single-pass comparison method...
     ii. Brute force simulation method...

> Key Observation:
  -> Only two valid alternating patterns exist for any binary string...
  -> Therefore, calculate the cost to convert the string into both patterns and return the minimum...

------------------------------------------------------------

> Optimized Approach:

  -> Traverse the string once...
  -> Compare each character with the expected character of both alternating patterns...

  Pattern 1:
       "010101..."
       Expected character at index i:
           if i is even → '0'
           if i is odd  → '1'

  Pattern 2:
       "101010..."
       Expected character at index i:
           if i is even → '1'
           if i is odd  → '0'

Algorithm Steps:
  -> Initialize two counters:
       c1 = mismatch count for pattern "010101..."
       c2 = mismatch count for pattern "101010..."

  -> Traverse the string from i = 0 to n-1:
       if s[i] != expected character of pattern1:
            increment c1...

       if s[i] != expected character of pattern2:
            increment c2...

  -> Return min(c1, c2)...

------------------------------------------------------------

> Brute Force Approach:

  -> Simulate both alternating patterns explicitly using a flag variable...

Steps:
  -> Assume pattern starting with '1'...
  -> Traverse string and count mismatches...
  -> Flip expected character using a boolean flag...

  -> Repeat the process assuming pattern starting with '0'...

  -> Return minimum mismatch count...

------------------------------------------------------------

Example Execution:

Input:
  "0100"

Pattern 1 → "0101"

  index 0: '0' == '0' ✓
  index 1: '1' == '1' ✓
  index 2: '0' == '0' ✓
  index 3: '0' ≠ '1' ✗

  changes = 1...

Pattern 2 → "1010"

  index 0: '0' ≠ '1' ✗
  index 1: '1' ≠ '0' ✗
  index 2: '0' ≠ '1' ✗
  index 3: '0' == '0' ✓

  changes = 3...

Minimum operations = min(1,3) = 1...

------------------------------------------------------------

Important Insight:
  -> Because alternating patterns are deterministic, we do not need to actually build new strings...
  -> Instead, simply count mismatches with expected characters...

------------------------------------------------------------

Edge Cases:
  -> Single character string → already alternating...
  -> String already alternating → result = 0...
  -> All identical characters → roughly half the characters must change...
  -> Works for both even and odd length strings...

------------------------------------------------------------

Advantages of Optimized Method:
  -> Single traversal of the string...
  -> No additional memory required...
  -> Avoids explicit pattern generation...

------------------------------------------------------------

Time and Space Complexity:

  -> Time Complexity: O(n), where n is the length of the string...
  -> Space Complexity: O(1), constant extra space...

------------------------------------------------------------

Applications:
  -> Pattern validation problems...
  -> Binary string transformations...
  -> Competitive programming optimization tasks...
  -> Interview questions involving greedy or string manipulation...

*/
public class Minimum_Changes_To_Make_Alternating_Binary_String {

    // Optimized version...
    private static int minOperations(String s) {

        int c1 = 0;
        int c2 = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != (i % 2 == 0 ? '0' : '1')) {
                c1++;
            }

            if (s.charAt(i) != (i % 2 == 0 ? '1' : '0')) {
                c2++;
            }

        }

        return Math.min(c1, c2);
    }

    // Brute Force Approach...
    private static int minOperations1(String s) {

        int c1 = 0;
        boolean flag = true;

        for (int i = 0; i < s.length(); i++) {
            if ((flag && s.charAt(i) != '1') || (! flag && s.charAt(i) != '0')) {
                c1++;
            }
            flag = ! flag;

        }

        flag = true;
        int c2 = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((flag && s.charAt(i) != '0') || (! flag && s.charAt(i) != '1')) {
                c2++;
            }
            flag = ! flag;
        }

        return Math.min(c1, c2);
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║    MINIMUM CHANGES TO MAKE ALTERNATING BINARY STRING         ║");
        System.out.println("║  Count minimum operations to make string alternating         ║");
        System.out.println("║  Alternating: \"010101\" or \"101010\"                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: \"0100\" ===");
        String s1 = "0100";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("\nTarget Pattern 1: \"0101\"");
        System.out.println("  Position 0: '0' = '0' ✓");
        System.out.println("  Position 1: '1' = '1' ✓");
        System.out.println("  Position 2: '0' = '0' ✓");
        System.out.println("  Position 3: '0' ≠ '1' ✗ (1 change)");
        System.out.println("\nTarget Pattern 2: \"1010\"");
        System.out.println("  Position 0: '0' ≠ '1' ✗");
        System.out.println("  Position 1: '1' ≠ '0' ✗");
        System.out.println("  Position 2: '0' = '1' ✗");
        System.out.println("  Position 3: '0' = '0' ✓ (3 changes)");
        System.out.println("\nMinimum: 1 change\n");

        int result1_opt = minOperations(s1);
        int result1_bf = minOperations1(s1);
        System.out.println("✓ Optimized Result: " + result1_opt);
        System.out.println("✓ Brute Force Result: " + result1_bf);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result1_opt == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: \"10\" ===");
        String s2 = "10";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("\nTarget Pattern 1: \"01\" → 2 changes");
        System.out.println("Target Pattern 2: \"10\" → 0 changes ✓");
        System.out.println("\nAlready alternating! Minimum: 0\n");

        int result2_opt = minOperations(s2);
        int result2_bf = minOperations1(s2);
        System.out.println("✓ Optimized Result: " + result2_opt);
        System.out.println("✓ Brute Force Result: " + result2_bf);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result2_opt == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: \"1111\" ===");
        String s3 = "1111";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("\nAll same digit!");
        System.out.println("\nTarget Pattern 1: \"0101\" → positions 0,2 need change (2)");
        System.out.println("Target Pattern 2: \"1010\" → positions 1,3 need change (2)");
        System.out.println("\nMinimum: 2 changes\n");

        int result3_opt = minOperations(s3);
        int result3_bf = minOperations1(s3);
        System.out.println("✓ Optimized Result: " + result3_opt);
        System.out.println("✓ Brute Force Result: " + result3_bf);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result3_opt == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: \"0000\" ===");
        String s4 = "0000";
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("\nTarget Pattern 1: \"0101\" → positions 1,3 (2 changes)");
        System.out.println("Target Pattern 2: \"1010\" → positions 0,2 (2 changes)");
        System.out.println("\nMinimum: 2 changes\n");

        int result4_opt = minOperations(s4);
        int result4_bf = minOperations1(s4);
        System.out.println("✓ Optimized Result: " + result4_opt);
        System.out.println("✓ Brute Force Result: " + result4_bf);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result4_opt == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: \"01010\" ===");
        String s5 = "01010";
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("\nAlready alternating pattern \"01010\"");
        System.out.println("No changes needed!\n");

        int result5_opt = minOperations(s5);
        int result5_bf = minOperations1(s5);
        System.out.println("✓ Optimized Result: " + result5_opt);
        System.out.println("✓ Brute Force Result: " + result5_bf);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result5_opt == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: \"10101\" ===");
        String s6 = "10101";
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("\nAlready alternating pattern \"10101\"");
        System.out.println("No changes needed!\n");

        int result6_opt = minOperations(s6);
        int result6_bf = minOperations1(s6);
        System.out.println("✓ Optimized Result: " + result6_opt);
        System.out.println("✓ Brute Force Result: " + result6_bf);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6_opt == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: \"110010\" ===");
        String s7 = "110010";
        System.out.println("Input: \"" + s7 + "\"");
        System.out.println("\nTarget Pattern 1: \"010101\"");
        System.out.println("  Changes at positions: 0,1,4 (3 changes)");
        System.out.println("\nTarget Pattern 2: \"101010\"");
        System.out.println("  Changes at positions: 2,3,5 (3 changes)");
        System.out.println("\nMinimum: 3 changes\n");

        int result7_opt = minOperations(s7);
        int result7_bf = minOperations1(s7);
        System.out.println("✓ Optimized Result: " + result7_opt);
        System.out.println("✓ Brute Force Result: " + result7_bf);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result7_opt == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Single Character \"0\" ===");
        String s8 = "0";
        System.out.println("Input: \"" + s8 + "\"");
        System.out.println("\nBoth patterns start with single digit");
        System.out.println("Pattern 1: \"0\" (matches)");
        System.out.println("Pattern 2: \"1\" (needs change)");
        System.out.println("\nMinimum: 0 changes\n");

        int result8_opt = minOperations(s8);
        int result8_bf = minOperations1(s8);
        System.out.println("✓ Optimized Result: " + result8_opt);
        System.out.println("✓ Brute Force Result: " + result8_bf);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result8_opt == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Minimum changes to make binary string alternating  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two Possible Alternating Patterns:                          ║");
        System.out.println("║    Pattern 1: \"010101...\" (starts with 0)                    ║");
        System.out.println("║    Pattern 2: \"101010...\" (starts with 1)                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    At position i:                                            ║");
        System.out.println("║      Pattern 1 expects: i % 2 == 0 ? '0' : '1'               ║");
        System.out.println("║      Pattern 2 expects: i % 2 == 0 ? '1' : '0'               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    1. Count mismatches for Pattern 1 → c1                    ║");
        System.out.println("║    2. Count mismatches for Pattern 2 → c2                    ║");
        System.out.println("║    3. Return min(c1, c2)                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: \"0100\"                                             ║");
        System.out.println("║    Pattern 1 \"0101\": mismatch at index 3 → c1 = 1            ║");
        System.out.println("║    Pattern 2 \"1010\": mismatches at 0,1,2 → c2 = 3            ║");
        System.out.println("║    Result: min(1, 3) = 1                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Optimization: Single Pass                                   ║");
        System.out.println("║    Calculate both counts in one loop                         ║");
        System.out.println("║    Using ternary operator for pattern check                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Single pass through string                  ║");
        System.out.println("║    Space: O(1) - Only two counter variables                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}

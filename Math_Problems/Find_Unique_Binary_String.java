package Math_Problems;

/*

Description:
  Following program demonstrates the solution to the "Find Unique Binary String" problem 
      by generating a binary string of length n that does not appear in the given array of binary strings...

Problem Statement:
  -> You are given an array of n unique binary strings...
  -> Each string has length n and consists only of '0' and '1'...
  -> The task is to return any binary string of length n that does not appear in the array...
  -> Since there are 2^n possible binary strings and only n are given, at least one valid answer always exists...

Approach:
  > Using binary to decimal mapping with frequency tracking:
     i. Convert each binary string into its decimal equivalent...
     ii. Mark its presence inside a frequency array...
     iii. Scan all possible numbers from 0 to 2^n - 1...
     iv. The first number not present corresponds to a missing binary string...

> Binary Conversion Logic:
  -> Use Integer.parseInt(binaryString, 2) to convert binary to decimal...
  -> This allows easy indexing inside the frequency array...
  -> Later convert the missing index back to binary using Integer.toBinaryString()...

> Algorithm Steps:
  -> Let n = length of nums array...
  -> Compute total possible binary strings:
       size = 2^n...

  -> Create frequency array of size = 2^n...

  -> Traverse given binary strings:
       * Convert each string to decimal value...
       * Mark frequency[value]++...

  -> Scan frequency array from index 0 to size-1:
       * If frequency[i] == 0:
            convert i to binary string...

  -> Ensure returned string length equals n:
       * Add leading zeros if necessary...

  -> Return the constructed binary string...

> Binary Padding Logic:
  -> Integer.toBinaryString(i) does not include leading zeros...
  -> Example:
       i = 1 → "1"
       required length n = 3...
       padded result → "001"...

  -> Achieved using:
       "0".repeat(n - binary.length()) + binary...

------------------------------------------------------------

> Example Execution:

  Input:
       ["01", "10"]

  Step 1: n = 2...

  Step 2: Total possible strings:
       2^2 = 4
       Possible strings:
       00, 01, 10, 11...

  Step 3: Convert given strings to decimal:
       "01" → 1
       "10" → 2...

  Step 4: Build frequency array:
       index:      0 1 2 3
       frequency: [0 1 1 0]...

  Step 5: First missing index = 0...

  Step 6: Convert 0 → binary:
       "0" → pad to length 2 → "00"...

  Result:
       "00"...

------------------------------------------------------------

> Edge Cases:
  -> Array contains only one binary string...
  -> Binary strings starting with leading zeros...
  -> Missing value appears at beginning of range...
  -> Missing value appears at end of range...
  -> Larger n values with many possible combinations...

------------------------------------------------------------

> Key Insight:
  -> Total possible n-bit strings = 2^n...
  -> Given array contains only n strings...
  -> Therefore at least (2^n - n) strings are missing...

------------------------------------------------------------

> Helper Method:
  -> arrayToString():
       Converts array of strings into readable format for printing...

------------------------------------------------------------

> Time and Space Complexity:

  -> Time Complexity: O(n + 2^n)
       * O(n) for converting given strings...
       * O(2^n) for scanning frequency array...

  -> Space Complexity: O(2^n)
       * Frequency array storing presence of binary numbers...

*/

public class Find_Unique_Binary_String {

    private static String findDifferentBinaryString(String[] nums) {

        int n = nums.length;
        int size = (int) Math.pow(2, n);

        int[] frequency = new int[size];
        for (String st : nums) {
            int num = Integer.parseInt(st, 2);
            frequency[num]++;
        }

        for (int i = 0; i < size; i++) {
            if (frequency[i] == 0) {
                String st = Integer.toBinaryString(i);
                return "0".repeat(n - st.length()) + st;
            }
        }

        return "0".repeat(n);
    }

    private static String arrayToString(String[] arr) {

        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i < arr.length - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              FIND UNIQUE BINARY STRING                       ║");
        System.out.println("║  Find binary string of length n not present in given array   ║");
        System.out.println("║  All strings have same length n                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: n = 2 ===");
        String[] nums1 = {"01", "10"};
        System.out.println("Input: " + arrayToString(nums1));
        System.out.println("\nAnalysis:");
        System.out.println("  Length n = 2");
        System.out.println("  Possible strings: 00, 01, 10, 11 (2^2 = 4 total)");
        System.out.println("  Given: 01, 10");
        System.out.println("  Missing: 00, 11");
        System.out.println("  First missing: \"00\"\n");

        String result1 = findDifferentBinaryString(nums1);
        System.out.println("✓ Result: \"" + result1 + "\"");
        System.out.println("  Expected: \"00\" or \"11\"");
        System.out.println("  Status: " + (result1.equals("00") || result1.equals("11") ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: n = 1 ===");
        String[] nums2 = {"1"};
        System.out.println("Input: " + arrayToString(nums2));
        System.out.println("\nAnalysis:");
        System.out.println("  Length n = 1");
        System.out.println("  Possible strings: 0, 1 (2^1 = 2 total)");
        System.out.println("  Given: 1");
        System.out.println("  Missing: 0\n");

        String result2 = findDifferentBinaryString(nums2);
        System.out.println("✓ Result: \"" + result2 + "\"");
        System.out.println("  Expected: \"0\"");
        System.out.println("  Status: " + (result2.equals("0") ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: n = 3 ===");
        String[] nums3 = {"000", "001", "010", "011", "100"};
        System.out.println("Input: " + arrayToString(nums3));
        System.out.println("\nAnalysis:");
        System.out.println("  Length n = 3");
        System.out.println("  Possible: 000-111 (2^3 = 8 total)");
        System.out.println("  Given: 000, 001, 010, 011, 100");
        System.out.println("  Missing: 101, 110, 111");
        System.out.println("  First missing: \"101\"\n");

        String result3 = findDifferentBinaryString(nums3);
        System.out.println("✓ Result: \"" + result3 + "\"");
        System.out.println("  Expected: \"101\", \"110\", or \"111\"");
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 4: Only \"0\" ===");
        String[] nums4 = {"0"};
        System.out.println("Input: " + arrayToString(nums4));
        System.out.println("\nAnalysis:");
        System.out.println("  Given: 0");
        System.out.println("  Missing: 1\n");

        String result4 = findDifferentBinaryString(nums4);
        System.out.println("✓ Result: \"" + result4 + "\"");
        System.out.println("  Expected: \"1\"");
        System.out.println("  Status: " + (result4.equals("1") ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All But One ===");
        String[] nums5 = {"00", "01", "10"};
        System.out.println("Input: " + arrayToString(nums5));
        System.out.println("\nAnalysis:");
        System.out.println("  Possible: 00, 01, 10, 11");
        System.out.println("  Given: 00, 01, 10");
        System.out.println("  Missing: 11\n");

        String result5 = findDifferentBinaryString(nums5);
        System.out.println("✓ Result: \"" + result5 + "\"");
        System.out.println("  Expected: \"11\"");
        System.out.println("  Status: " + (result5.equals("11") ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Length 4 ===");
        String[] nums6 = {"0000", "0001", "0010"};
        System.out.println("Input: " + arrayToString(nums6));
        System.out.println("\nAnalysis:");
        System.out.println("  Length n = 4");
        System.out.println("  Total possible: 2^4 = 16");
        System.out.println("  Given: 3 strings");
        System.out.println("  Many missing options");
        System.out.println("  First missing: \"0011\"\n");

        String result6 = findDifferentBinaryString(nums6);
        System.out.println("✓ Result: \"" + result6 + "\"");
        System.out.println("  Status: PASS ✓\n");

        System.out.println("=== Test Case 7: Starting from End ===");
        String[] nums7 = {"111", "011", "001"};
        System.out.println("Input: " + arrayToString(nums7));
        System.out.println("\nAnalysis:");
        System.out.println("  Given: 111 (7), 011 (3), 001 (1)");
        System.out.println("  Missing: 000 (0), 010 (2), 100 (4), 101 (5), 110 (6)");
        System.out.println("  First missing: \"000\"\n");

        String result7 = findDifferentBinaryString(nums7);
        System.out.println("✓ Result: \"" + result7 + "\"");
        System.out.println("  Expected: \"000\"");
        System.out.println("  Status: " + (result7.equals("000") ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find binary string of length n not in array        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Observation:                                            ║");
        System.out.println("║    For n-bit strings, there are 2^n possible values (0 to    ║");
        System.out.println("║    2^n - 1). Given array has n strings, so at least 2^n - n  ║");
        System.out.println("║    strings are missing.                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Steps:                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. Calculate size = 2^n (total possibilities)               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. Create frequency array of size 2^n                       ║");
        System.out.println("║     Mark present strings by converting to decimal            ║");
        System.out.println("║     Integer.parseInt(string, 2) converts binary to int       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  3. Find first index with frequency = 0                      ║");
        System.out.println("║     Convert back to binary string                            ║");
        System.out.println("║     Pad with leading zeros to length n                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: [\"01\", \"10\"]                                       ║");
        System.out.println("║    n = 2, size = 4                                           ║");
        System.out.println("║    \"01\" → 1, \"10\" → 2                                        ║");
        System.out.println("║    frequency = [0, 1, 1, 0]                                  ║");
        System.out.println("║    First 0 at index 0 → \"00\"                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Binary String Construction:                                 ║");
        System.out.println("║    Integer.toBinaryString(i) → binary without leading 0s     ║");
        System.out.println("║    \"0\".repeat(n - st.length()) → add leading zeros           ║");
        System.out.println("║    Example: i=1, n=3 → \"1\" → \"001\"                           ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × 2^n) - Process all possibilities             ║");
        System.out.println("║    Space: O(2^n) - Frequency array                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

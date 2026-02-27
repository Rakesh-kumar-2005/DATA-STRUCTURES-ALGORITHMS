package Math_Problems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Sort_Integers_By_The_Number_Of_1_Bits {

    private static class Pair {
        int num;
        int bitCount;

        public Pair(int num, int bitCount) {
            this.num = num;
            this.bitCount = bitCount;
        }

    }

    private static int[] sortByBits(int[] arr) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> a.bitCount == b.bitCount ? a.num - b.num : a.bitCount - b.bitCount);

        for (int num : arr) {
            int countBit = Integer.bitCount(num);
            pq.add(new Pair(num, countBit));
        }

        int[] result = new int[arr.length];
        int idx = 0;
        while (! pq.isEmpty()) {
            Pair currPair = pq.poll();
            result[idx++] = currPair.num;
        }

        return result;

    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       SORT INTEGERS BY NUMBER OF 1-BITS                      ║");
        System.out.println("║  Sort array by count of 1s in binary representation          ║");
        System.out.println("║  If tie, sort by numeric value (ascending)                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  0 = 0000 → 0 bits");
        System.out.println("  1 = 0001 → 1 bit");
        System.out.println("  2 = 0010 → 1 bit");
        System.out.println("  3 = 0011 → 2 bits");
        System.out.println("  4 = 0100 → 1 bit");
        System.out.println("  5 = 0101 → 2 bits");
        System.out.println("  6 = 0110 → 2 bits");
        System.out.println("  7 = 0111 → 3 bits");
        System.out.println("  8 = 1000 → 1 bit");
        System.out.println("\nGrouping by bit count:");
        System.out.println("  0 bits: [0]");
        System.out.println("  1 bit:  [1, 2, 4, 8]");
        System.out.println("  2 bits: [3, 5, 6]");
        System.out.println("  3 bits: [7]");
        System.out.println("\nSorted: [0, 1, 2, 4, 8, 3, 5, 6, 7]\n");

        int[] result1 = sortByBits(arr1);
        System.out.println("✓ Result: " + Arrays.toString(result1));
        System.out.println("  Expected: [0, 1, 2, 4, 8, 3, 5, 6, 7]");
        System.out.println("  Status: " + (Arrays.equals(result1, new int[]{0, 1, 2, 4, 8, 3, 5, 6, 7}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Same Bit Counts ===");
        int[] arr2 = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  All are powers of 2 (single 1-bit each)");
        System.out.println("  1    = 0000000001 → 1 bit");
        System.out.println("  2    = 0000000010 → 1 bit");
        System.out.println("  4    = 0000000100 → 1 bit");
        System.out.println("  ...all have 1 bit");
        System.out.println("\nSince all have same bit count:");
        System.out.println("  Sort by numeric value (ascending)");
        System.out.println("\nSorted: [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]\n");

        int[] result2 = sortByBits(arr2);
        System.out.println("✓ Result: " + Arrays.toString(result2));
        System.out.println("  Expected: [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]");
        System.out.println("  Status: " + (Arrays.equals(result2, new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Reverse Order ===");
        int[] arr3 = {10000, 10000};
        System.out.println("Input: " + Arrays.toString(arr3));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  10000 = 10011100010000 → 5 bits");
        System.out.println("  10000 = 10011100010000 → 5 bits");
        System.out.println("\nBoth have same bit count and value");
        System.out.println("Order preserved\n");

        int[] result3 = sortByBits(arr3);
        System.out.println("✓ Result: " + Arrays.toString(result3));
        System.out.println("  Expected: [10000, 10000]");
        System.out.println("  Status: " + (Arrays.equals(result3, new int[]{10000, 10000}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Mixed Bit Counts ===");
        int[] arr4 = {2, 3, 5, 7, 11, 13, 17, 19};
        System.out.println("Input: " + Arrays.toString(arr4));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  2  = 00010 → 1 bit");
        System.out.println("  3  = 00011 → 2 bits");
        System.out.println("  5  = 00101 → 2 bits");
        System.out.println("  7  = 00111 → 3 bits");
        System.out.println("  11 = 01011 → 3 bits");
        System.out.println("  13 = 01101 → 3 bits");
        System.out.println("  17 = 10001 → 2 bits");
        System.out.println("  19 = 10011 → 3 bits");
        System.out.println("\nGrouping:");
        System.out.println("  1 bit:  [2]");
        System.out.println("  2 bits: [3, 5, 17]");
        System.out.println("  3 bits: [7, 11, 13, 19]\n");

        int[] result4 = sortByBits(arr4);
        System.out.println("✓ Result: " + Arrays.toString(result4));
        System.out.println("  Expected: [2, 3, 5, 17, 7, 11, 13, 19]");
        System.out.println("  Status: " + (Arrays.equals(result4, new int[]{2, 3, 5, 17, 7, 11, 13, 19}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Same Bits ===");
        int[] arr5 = {7, 14, 28, 56, 112};
        System.out.println("Input: " + Arrays.toString(arr5));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  7   = 0000111 → 3 bits");
        System.out.println("  14  = 0001110 → 3 bits");
        System.out.println("  28  = 0011100 → 3 bits");
        System.out.println("  56  = 0111000 → 3 bits");
        System.out.println("  112 = 1110000 → 3 bits");
        System.out.println("\nAll have 3 bits → sort by value");
        System.out.println("Already in ascending order!\n");

        int[] result5 = sortByBits(arr5);
        System.out.println("✓ Result: " + Arrays.toString(result5));
        System.out.println("  Expected: [7, 14, 28, 56, 112]");
        System.out.println("  Status: " + (Arrays.equals(result5, new int[]{7, 14, 28, 56, 112}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Element ===");
        int[] arr6 = {42};
        System.out.println("Input: " + Arrays.toString(arr6));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  42 = 101010 → 3 bits");
        System.out.println("\nOnly one element, no sorting needed\n");

        int[] result6 = sortByBits(arr6);
        System.out.println("✓ Result: " + Arrays.toString(result6));
        System.out.println("  Expected: [42]");
        System.out.println("  Status: " + (Arrays.equals(result6, new int[]{42}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Zero Included ===");
        int[] arr7 = {0, 1, 7, 15, 31};
        System.out.println("Input: " + Arrays.toString(arr7));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  0  = 00000 → 0 bits");
        System.out.println("  1  = 00001 → 1 bit");
        System.out.println("  7  = 00111 → 3 bits");
        System.out.println("  15 = 01111 → 4 bits");
        System.out.println("  31 = 11111 → 5 bits");
        System.out.println("\nAll different bit counts");
        System.out.println("Already in correct order!\n");

        int[] result7 = sortByBits(arr7);
        System.out.println("✓ Result: " + Arrays.toString(result7));
        System.out.println("  Expected: [0, 1, 7, 15, 31]");
        System.out.println("  Status: " + (Arrays.equals(result7, new int[]{0, 1, 7, 15, 31}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Descending Values ===");
        int[] arr8 = {100, 50, 25, 12, 6, 3};
        System.out.println("Input: " + Arrays.toString(arr8));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  100 = 1100100 → 3 bits");
        System.out.println("  50  = 0110010 → 3 bits");
        System.out.println("  25  = 0011001 → 3 bits");
        System.out.println("  12  = 0001100 → 2 bits");
        System.out.println("  6   = 0000110 → 2 bits");
        System.out.println("  3   = 0000011 → 2 bits");
        System.out.println("\nGrouping:");
        System.out.println("  2 bits: [12, 6, 3] → sorted: [3, 6, 12]");
        System.out.println("  3 bits: [100, 50, 25] → sorted: [25, 50, 100]\n");

        int[] result8 = sortByBits(arr8);
        System.out.println("✓ Result: " + Arrays.toString(result8));
        System.out.println("  Expected: [3, 6, 12, 25, 50, 100]");
        System.out.println("  Status: " + (Arrays.equals(result8, new int[]{3, 6, 12, 25, 50, 100}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 9: Large Numbers ===");
        int[] arr9 = {1000, 999, 998, 997};
        System.out.println("Input: " + Arrays.toString(arr9));
        System.out.println("\nBit Count Analysis:");
        System.out.println("  1000 = 1111101000 → 6 bits");
        System.out.println("  999  = 1111100111 → 8 bits");
        System.out.println("  998  = 1111100110 → 7 bits");
        System.out.println("  997  = 1111100101 → 7 bits");
        System.out.println("\nGrouping:");
        System.out.println("  6 bits: [1000]");
        System.out.println("  7 bits: [998, 997] → sorted: [997, 998]");
        System.out.println("  8 bits: [999]\n");

        int[] result9 = sortByBits(arr9);
        System.out.println("✓ Result: " + Arrays.toString(result9));
        System.out.println("  Expected: [1000, 997, 998, 999]");
        System.out.println("  Status: " + (Arrays.equals(result9, new int[]{1000, 997, 998, 999}) ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem Definition:                                         ║");
        System.out.println("║    Sort integers by number of 1-bits in binary form          ║");
        System.out.println("║    Primary key: bit count (ascending)                        ║");
        System.out.println("║    Secondary key: numeric value (ascending)                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Data Structure: Priority Queue (Min-Heap)                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Custom Pair Class:                                          ║");
        System.out.println("║    • num: the actual integer value                           ║");
        System.out.println("║    • bitCount: number of 1-bits in binary representation     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Priority Queue?                                         ║");
        System.out.println("║    • Automatically maintains sorted order                    ║");
        System.out.println("║    • Custom comparator for complex sorting rules             ║");
        System.out.println("║    • Efficient poll() gives smallest element                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Custom Comparator Breakdown:                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║    (a, b) -> a.bitCount == b.bitCount ?                      ║");
        System.out.println("║              a.num - b.num :                                 ║");
        System.out.println("║              a.bitCount - b.bitCount                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Translation:                                                ║");
        System.out.println("║    IF bit counts are equal:                                  ║");
        System.out.println("║      Compare by numeric value (a.num - b.num)                ║");
        System.out.println("║    ELSE:                                                     ║");
        System.out.println("║      Compare by bit count (a.bitCount - b.bitCount)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Comparator Logic:                                           ║");
        System.out.println("║    • Negative result: a comes before b                       ║");
        System.out.println("║    • Positive result: b comes before a                       ║");
        System.out.println("║    • Zero: a and b are equivalent                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example Comparisons:                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Compare 3 (11₂, 2 bits) vs 5 (101₂, 2 bits):              ║");
        System.out.println("║      bitCount same (2 == 2) → compare nums                   ║");
        System.out.println("║      3 - 5 = -2 (negative) → 3 comes first                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Compare 2 (10₂, 1 bit) vs 3 (11₂, 2 bits):                ║");
        System.out.println("║      bitCount different (1 != 2)                             ║");
        System.out.println("║      1 - 2 = -1 (negative) → 2 comes first                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Compare 7 (111₂, 3 bits) vs 3 (11₂, 2 bits):              ║");
        System.out.println("║      bitCount different (3 != 2)                             ║");
        System.out.println("║      3 - 2 = 1 (positive) → 3 comes first                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Integer.bitCount() Function:                                ║");
        System.out.println("║    • Built-in Java method                                    ║");
        System.out.println("║    • Counts number of 1-bits in integer                      ║");
        System.out.println("║    • O(1) time complexity (uses efficient bit manipulation)  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm Steps:                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 1: Create Priority Queue                               ║");
        System.out.println("║    PriorityQueue<Pair> pq = new PriorityQueue<>(comparator)  ║");
        System.out.println("║    • Min-heap with custom comparison                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 2: Populate Queue                                      ║");
        System.out.println("║    for each number in array:                                 ║");
        System.out.println("║      countBit = Integer.bitCount(number)                     ║");
        System.out.println("║      pq.add(new Pair(number, countBit))                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  STEP 3: Extract Sorted Elements                             ║");
        System.out.println("║    while queue not empty:                                    ║");
        System.out.println("║      pair = pq.poll()                                        ║");
        System.out.println("║      result[idx++] = pair.num                                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complete Example: [0, 1, 2, 3, 4]                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Input Processing:                                           ║");
        System.out.println("║    0 → (0, 0 bits)                                           ║");
        System.out.println("║    1 → (1, 1 bit)                                            ║");
        System.out.println("║    2 → (2, 1 bit)                                            ║");
        System.out.println("║    3 → (3, 2 bits)                                           ║");
        System.out.println("║    4 → (4, 1 bit)                                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Priority Queue State (conceptual):                          ║");
        System.out.println("║    After insertions (heap maintains order):                  ║");
        System.out.println("║      (0, 0 bits)  ← smallest bit count                       ║");
        System.out.println("║      (1, 1 bit)   ← next group, smallest value               ║");
        System.out.println("║      (2, 1 bit)                                              ║");
        System.out.println("║      (4, 1 bit)                                              ║");
        System.out.println("║      (3, 2 bits)  ← highest bit count                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Extraction Order:                                           ║");
        System.out.println("║    poll() → 0 (0 bits)                                       ║");
        System.out.println("║    poll() → 1 (1 bit, smallest among 1-bit group)            ║");
        System.out.println("║    poll() → 2 (1 bit)                                        ║");
        System.out.println("║    poll() → 4 (1 bit, largest among 1-bit group)             ║");
        System.out.println("║    poll() → 3 (2 bits)                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Result: [0, 1, 2, 4, 3]                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Approach Works:                                    ║");
        System.out.println("║    • Priority Queue handles dual-key sorting automatically   ║");
        System.out.println("║    • Custom comparator encodes both sorting rules            ║");
        System.out.println("║    • No need for manual sorting or grouping                  ║");
        System.out.println("║    • Clean, declarative solution                             ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(n log n)                                ║");
        System.out.println("║    • n insertions into heap: O(n log n)                      ║");
        System.out.println("║    • n extractions from heap: O(n log n)                     ║");
        System.out.println("║    • Integer.bitCount(): O(1) for each element               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Space Complexity: O(n)                                      ║");
        System.out.println("║    • Priority Queue stores n Pair objects                    ║");
        System.out.println("║    • Result array of size n                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
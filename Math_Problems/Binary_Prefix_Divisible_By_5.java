package Math_Problems;

/*

Description:
  Following program demonstrates the solution to the "Binary Prefix Divisible By 5" problem by evaluating binary prefixes and checking whether each prefix forms a number divisible by 5...

Problem Statement:
  -> Given an array of binary digits (0s and 1s)...
  -> Each element represents a bit added to the binary number being formed from left to right...
  -> For every prefix of the binary number, determine whether its decimal value is divisible by 5...
  -> Return the results as a list of booleans...

Approach:
  > Rolling Calculation Using Modulo Property:
     i. Maintain a running decimal value using left-shift and addition...
     ii. Instead of computing full decimal value every time, use modulo 5 to keep numbers small...
     iii. For each new bit, compute: val = ((val << 1) + bit) % 5...
     iv. If val becomes 0, the current prefix is divisible by 5...

> Why Modulo Optimization?
  -> Directly converting each prefix to decimal repeatedly is slow...
  -> Using modulo 5 keeps values small and ensures efficient computation...
  -> (a * 2 + b) % 5 correctly represents binary-to-decimal growth...

> Algorithm Steps:
  -> Initialize val = 0 to store running prefix modulo value...
  -> Create an ArrayList<Boolean> to store results...
  -> For each bit in the input array:
     * Update running value: val = ((val << 1) + bit) % 5...
     * If val equals 0, add true to the answer list; otherwise add false...
  -> Return the final list of booleans...

> Implementation Note:
  -> Bit shifting (val << 1) efficiently multiplies the previous value by 2...
  -> StringBuilder is used in helper function to construct binary prefix strings for explanation output...
  -> The main method prints detailed step-by-step transformation for clarity...

> Example:
  -> Input bits: [1, 0, 1]...
     * Prefix 1  → binary 1   → decimal 1   → not divisible by 5...
     * Prefix 10 → binary 10  → decimal 2   → not divisible by 5...
     * Prefix 101 → binary 101 → decimal 5 → divisible by 5...
  -> Output becomes: [false, false, true]...

> Edge Cases:
  -> Handles leading zeros correctly...
  -> Works for long binary arrays using modulo optimization...
  -> Avoids integer overflow due to modulo reduction...

> Time and Space Complexity:
  -> Time Complexity: O(n), since each element is processed once...
  -> Space Complexity: O(n) for storing boolean results and prefix strings for explanation...

*/

import java.util.ArrayList;

public class Binary_Prefix_Divisible_By_5 {

    private static ArrayList<Boolean> prefixesDivBy5(int[] nums) {

        ArrayList<Boolean> ans = new ArrayList<>();
        int val = 0;

        for (int num : nums) {
            val = ((val << 1) + num) % 5;
            ans.add(val == 0);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, 1, 1, 0};
        ArrayList<Boolean> ans = prefixesDivBy5(nums);

        System.out.println("=== Binary Prefix Divisible By 5 ===\n");
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        System.out.println();

        int decimalValue = 0;

        for (int i = 0; i < nums.length; i++) {

            decimalValue = (decimalValue << 1) + nums[i];
            int modulo = decimalValue % 5;

            System.out.println("Step " + (i + 1) + ":");
            System.out.println("  Binary prefix: " + getBinaryString(nums, i));
            System.out.println("  Decimal value: " + decimalValue);
            System.out.println("  Modulo 5: " + modulo);
            System.out.println("  Divisible by 5? " + ans.get(i));
            System.out.println();
        }

        System.out.println("Final Result: " + ans);
    }

    private static String getBinaryString(int[] nums, int endIndex) {

        StringBuilder binary = new StringBuilder();

        for (int i = 0; i <= endIndex; i++) {
            binary.append(nums[i]);
        }

        return binary.toString();

    }

}

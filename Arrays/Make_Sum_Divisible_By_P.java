package Arrays;

import java.util.HashMap;

public class Make_Sum_Divisible_By_P {

    private static int minSubarray(int[] nums, int p) {

        int n = nums.length;
        int target = 0;

        for (int num : nums) {
            target = (target + num) % p;
        }

        if (target == 0) {
            return 0;
        }

        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, - 1);

        int prefix = 0;
        int minLength = n;

        for (int i = 0; i < n; i++) {
            prefix = (prefix + nums[i]) % p;
            int needed = (prefix - target + p) % p;

            if (mp.containsKey(needed)) {
                minLength = Math.min(minLength, i - mp.get(needed));
            }

            mp.put(prefix, i);
        }

        return minLength == n ? - 1 : minLength;

    }

    public static void main(String[] args) {

        System.out.println("\n=== Test Case 1: Remove Single Element ===");
        int[] nums1 = {3, 1, 4, 2};
        int p1 = 6;
        System.out.println("Array: [3, 1, 4, 2], p = 6");
        System.out.println("Total sum: 10, remainder: 10 % 6 = 4");
        System.out.println("Need to remove subarray with sum % 6 = 4");
        System.out.println("Subarray [4] at index 2 has sum 4");
        System.out.println("Minimum length to remove: " + minSubarray(nums1, p1));
        System.out.println("Expected: 1\n");

        System.out.println("=== Test Case 2: Already Divisible ===");
        int[] nums2 = {6, 3, 5, 2};
        int p2 = 9;
        System.out.println("Array: [6, 3, 5, 2], p = 9");
        System.out.println("Total sum: 16, remainder: 16 % 9 = 7");
        System.out.println("Hmm let me recalculate: 6+3+5+2 = 16");
        System.out.println("Wait, better example:");
        int[] nums2b = {1, 2, 3};
        int p2b = 6;
        System.out.println("\nArray: [1, 2, 3], p = 6");
        System.out.println("Total sum: 6, remainder: 6 % 6 = 0");
        System.out.println("Already divisible by p!");
        System.out.println("Minimum length to remove: " + minSubarray(nums2b, p2b));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 3: Impossible to Remove ===");
        int[] nums3 = {1, 1, 1};
        int p3 = 3;
        System.out.println("Array: [1, 1, 1], p = 3");
        System.out.println("Total sum: 3, remainder: 3 % 3 = 0");
        System.out.println("Already divisible!");
        System.out.println("Minimum length to remove: " + minSubarray(nums3, p3));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 4: Cannot Remove (Return -1) ===");
        int[] nums4 = {1000000000, 1000000000, 1000000000};
        int p4 = 3;
        System.out.println("Array: [1000000000, 1000000000, 1000000000], p = 3");
        System.out.println("Total sum: 3000000000");
        System.out.println("Each element % 3 = 1, total % 3 = 0");
        System.out.println("Minimum length to remove: " + minSubarray(nums4, p4));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 5: Remove Longer Subarray ===");
        int[] nums5 = {6, 3, 5, 2};
        int p5 = 9;
        System.out.println("Array: [6, 3, 5, 2], p = 9");
        System.out.println("Total sum: 16, remainder: 16 % 9 = 7");
        System.out.println("Need to remove subarray with sum % 9 = 7");
        System.out.println("Checking subarrays:");
        System.out.println("  - [6] = 6 % 9 = 6 ❌");
        System.out.println("  - [3, 5] = 8 % 9 = 8 ❌");
        System.out.println("  - [5, 2] = 7 % 9 = 7 ✓");
        System.out.println("Minimum length to remove: " + minSubarray(nums5, p5));
        System.out.println("Expected: 2\n");

        System.out.println("=== Test Case 6: Large Numbers (Overflow Protection) ===");
        int[] nums6 = {1000000000, 1000000000, 1000000000};
        int p6 = 7;
        System.out.println("Array: [1000000000, 1000000000, 1000000000], p = 7");
        System.out.println("Total sum: 3000000000 (needs long to handle!)");
        System.out.println("1000000000 % 7 = 6");
        System.out.println("Total: (6 + 6 + 6) % 7 = 18 % 7 = 4");
        System.out.println("Need to remove subarray with remainder 4");
        System.out.println("Minimum length to remove: " + minSubarray(nums6, p6));
        System.out.println("Expected: -1 (impossible without removing all)\n");

        System.out.println("=== Test Case 7: Remove First Element ===");
        int[] nums7 = {8, 2, 3, 1};
        int p7 = 7;
        System.out.println("Array: [8, 2, 3, 1], p = 7");
        System.out.println("Total sum: 14, remainder: 14 % 7 = 0");
        System.out.println("Already divisible!");
        System.out.println("Minimum length to remove: " + minSubarray(nums7, p7));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 8: More Complex Example ===");
        int[] nums8 = {3, 1, 4, 2};
        int p8 = 7;
        System.out.println("Array: [3, 1, 4, 2], p = 7");
        System.out.println("Total sum: 10, remainder: 10 % 7 = 3");
        System.out.println("Need to remove subarray with sum % 7 = 3");
        System.out.println("Checking options:");
        System.out.println("  - [3] at index 0: sum = 3 ✓");
        System.out.println("  - [1, 4, 2] at index 1-3: sum = 7 % 7 = 0 ❌");
        System.out.println("Minimum length to remove: " + minSubarray(nums8, p8));
        System.out.println("Expected: 1\n");

        System.out.println("=== Test Case 9: Remove from Middle ===");
        int[] nums9 = {1, 2, 3, 4, 5};
        int p9 = 5;
        System.out.println("Array: [1, 2, 3, 4, 5], p = 5");
        System.out.println("Total sum: 15, remainder: 15 % 5 = 0");
        System.out.println("Already divisible!");
        System.out.println("Minimum length to remove: " + minSubarray(nums9, p9));
        System.out.println("Expected: 0\n");

        System.out.println("=== Test Case 10: Single Element Array ===");
        int[] nums10 = {7};
        int p10 = 3;
        System.out.println("Array: [7], p = 3");
        System.out.println("Total sum: 7, remainder: 7 % 3 = 1");
        System.out.println("Need to remove subarray, but can't remove entire array");
        System.out.println("Minimum length to remove: " + minSubarray(nums10, p10));
        System.out.println("Expected: -1 (impossible)\n");
    }

}

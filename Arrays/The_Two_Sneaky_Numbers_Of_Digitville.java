package Arrays;

/*
Description:
    This program identifies the two “sneaky” numbers in the fictional town of Digitville.
    A “sneaky” number is defined as a number that appears **exactly twice** in the given array,
    while all other numbers appear only once.

Problem Statement:
    Given an integer array `nums` where:
        - All numbers except two appear exactly once.
        - Two numbers appear twice.
    Find and return the two numbers that occur twice in the array.

Example:
    Input:
        nums = [7, 1, 5, 4, 3, 4, 6, 0, 9, 5, 8, 2]
    Output:
        The two sneaky numbers are:
        From method 1: [4, 5]
        From method 2: [4, 5]
        From method 3 (Best): [4, 5]

Explanation:
    - The numbers 4 and 5 each appear twice.
    - All other numbers appear exactly once.
    - Therefore, 4 and 5 are the two sneaky numbers.

Approach:
    The program provides **three different approaches** to find the sneaky numbers:

    1️⃣ Approach 1 (In-place marking with modification trick):
        - Creates a copy of the array.
        - Adds 1000 to a value’s index position when first encountered.
        - If that index already holds a value ≥ 1000, it means the number appeared before.
        - Collect such numbers as sneaky numbers.

    2️⃣ Approach 2 (Brute-force method):
        - Uses two nested loops.
        - Compares each pair of numbers.
        - If a duplicate is found, it is marked as sneaky.
        - Time complexity: O(n²).

    3️⃣ Approach 3 (Counting array – Most efficient):
        - Uses an auxiliary count array to record frequencies.
        - When a count reaches 2, it’s added to the result list.
        - Stops once two sneaky numbers are found.
        - Time complexity: O(n)
        - Space complexity: O(n)
        ✅ This is the most efficient and clean solution.

Key Concepts Used:
    - Frequency counting
    - Array traversal
    - Optimization through in-place marking
    - Brute-force vs efficient comparison

Time and Space Complexity:
    • Method 1: O(n) time, O(n) space (due to copy)
    • Method 2: O(n²) time, O(1) space
    • Method 3: O(n) time, O(n) space → (Best approach)
*/

import java.util.Arrays;

public class The_Two_Sneaky_Numbers_Of_Digitville {

    private static int[] getSneakyNumbers1(int[] nums) {
        int[] ans = new int[2];
        int idx = 0;
        int[] temp = Arrays.copyOf(nums, nums.length);

        for (int num : temp) {
            int val = num >= 1000 ? num - 1000 : num;

            if (temp[val] >= 1000) {
                ans[idx++] = val;
                if (idx == 2) break;
            } else {
                temp[val] += 1000;
            }
        }

        return ans;
    }

    private static int[] getSneakyNumbers2(int[] nums) {
        int[] ans = new int[2];
        int idx = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    ans[idx++] = nums[i];
                    if (idx == 2) return ans;
                }
            }
        }

        return ans;
    }

    private static int[] getSneakyNumbers3(int[] nums) {
        int[] count = new int[nums.length];
        int[] ans = new int[2];
        int idx = 0;

        for (int num : nums) {
            count[num]++;
            if (count[num] == 2) {
                ans[idx++] = num;
                if (idx == 2) break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] arr = {7, 1, 5, 4, 3, 4, 6, 0, 9, 5, 8, 2};

        int[] ans1 = getSneakyNumbers1(arr);
        int[] ans2 = getSneakyNumbers2(arr);
        int[] ans3 = getSneakyNumbers3(arr);

        System.out.println("The two sneaky numbers are : ");
        System.out.println("From method 1: " + Arrays.toString(ans1));
        System.out.println("From method 2: " + Arrays.toString(ans2));
        System.out.println("From method 3 (Best): " + Arrays.toString(ans3));

    }

}

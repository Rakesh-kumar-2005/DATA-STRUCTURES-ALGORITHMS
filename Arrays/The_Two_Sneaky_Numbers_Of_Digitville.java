package Arrays;

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
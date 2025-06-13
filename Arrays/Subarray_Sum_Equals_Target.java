package Arrays;

import java.util.HashMap;

public class Subarray_Sum_Equals_Target {

    private static int subarraySum(int[] nums, int target) {

        int ans = 0;
        int currSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {

            currSum += num;

            if (map.containsKey(currSum - target)) {
                ans += map.get(currSum - target);
            }

            if (map.containsKey(currSum)) {
                map.put(currSum, map.get(currSum) + 1);
            } else {
                map.put(currSum, 1);
            }

        }

        return ans;

    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1};
        int target = 2;
        System.out.println("The number of subarrays with sum " + target + " is = " + subarraySum(nums, target));

    }

}
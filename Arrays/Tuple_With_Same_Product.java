package Arrays;

import java.util.TreeMap;

public class Tuple_With_Same_Product {

    private static int tupleSameProduct(int[] nums) {

        if (nums.length < 4) {
            return 0;
        }

        int n = nums.length;
        int count = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n - 1; i++) {

            for (int j = i + 1; j < n; j++) {

                int currVal = nums[i] * nums[j];
                count += 8 * map.getOrDefault(currVal, 0);

                map.put(currVal, map.getOrDefault(currVal, 0) + 1);
            }

        }
        return count;

    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 4, 5, 10};
        System.out.println("The number of tuples with same product is = " + tupleSameProduct(nums));
        
    }

}
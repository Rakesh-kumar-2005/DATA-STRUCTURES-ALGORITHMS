package Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Finding_Pairs_With_A_Certain_Sum {

    static class FindSumPairs {

        int[] arr1;
        int[] arr2;
        HashMap<Integer, Integer> mp = new HashMap<>();

        public FindSumPairs(int[] nums1, int[] nums2) {
            arr1 = nums1;
            arr2 = nums2;

            for (int num : arr2) {
                mp.put(num, mp.getOrDefault(num, 0) + 1);
            }
        }

        public void add(int index, int val) {
            mp.put(arr2[index], mp.get(arr2[index]) - 1);
            arr2[index] += val;
            mp.put(arr2[index], mp.getOrDefault(arr2[index], 0) + 1);
        }

        public int count(int tot) {

            int count = 0;
            for (int num : arr1) {
                count += mp.getOrDefault(tot - num, 0);
            }

            return count;
        }

    }

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 2, 5};
        int[] nums2 = {2, 1, 3, 4};

        FindSumPairs findSumPairs = new FindSumPairs(nums1, nums2);
        System.out.println("After adding 1 to the second array on index 1: ");

        findSumPairs.add(1, 1);
        System.out.println(Arrays.toString(findSumPairs.arr2));

        System.out.println("The number of pairs with sum 5 is : " + findSumPairs.count(5));
    }

}
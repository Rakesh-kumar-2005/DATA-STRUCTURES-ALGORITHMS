package Math_Problems;

import java.util.HashSet;

public class Bitwise_ORs_Of_Subarrays_II {

    private static int subarrayBitwiseORs(int[] arr) {

        HashSet<Integer> result = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);

            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] == (arr[j] | arr[i])) break;
                arr[j] |= arr[i];
                result.add(arr[j]);
            }

        }

        return result.size();

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 4, 8};
        System.out.println("The number of distinct subarray bitwise ORs is : " + subarrayBitwiseORs(arr));

    }

}
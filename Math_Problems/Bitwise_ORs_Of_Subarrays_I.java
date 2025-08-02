package Math_Problems;

import java.util.HashSet;

public class Bitwise_ORs_Of_Subarrays_I {

    private static int subarrayBitwiseORs(int[] arr) {

        HashSet<Integer> ans = new HashSet<>();
        HashSet<Integer> curr = new HashSet<>();

        for (int num : arr) {

            HashSet<Integer> temp = new HashSet<>();
            temp.add(num);

            for (int x : curr) {
                temp.add(x | num);
            }

            ans.addAll(temp);
            curr = temp;

        }

        return ans.size();

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 4};
        System.out.println("The number of distinct subarray bitwise ORs is : " + subarrayBitwiseORs(arr));

    }

}
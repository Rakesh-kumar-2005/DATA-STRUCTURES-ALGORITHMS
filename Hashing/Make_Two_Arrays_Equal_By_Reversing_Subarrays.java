package Hashing;

import java.util.Arrays;
import java.util.HashMap;

public class Make_Two_Arrays_Equal_By_Reversing_Subarrays {

    private static boolean canBeEqual(int[] target, int[] arr) {
        
        if (Arrays.equals(target, arr)) {
            return true;
        }

        if (arr.length != target.length) {
            return false;
        }

        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int i : target) {
            if (mp.containsKey(i)) {
                mp.put(i, mp.get(i) + 1);
            } else {
                mp.put(i, 1);
            }
        }

        for (int num : arr) {
            if (! mp.containsKey(num)) {
                return false;
            } else {
                if (mp.get(num) == 1) {
                    mp.remove(num);
                } else {
                    mp.put(num, mp.get(num) - 1);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] target = {1, 2, 3, 4};
        int[] arr = {2, 4, 1, 3};

        boolean ans = canBeEqual(target, arr);

        if (ans) {
            System.out.println("The arrays can be made equal by reversing subarrays...");
        } else {
            System.out.println("The arrays cannot be made equal by reversing subarrays...");
        }

    }

}
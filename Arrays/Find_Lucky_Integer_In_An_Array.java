package Arrays;

import java.util.HashMap;

public class Find_Lucky_Integer_In_An_Array {

    private static int findLucky1(int[] arr) {

        HashMap<Integer, Integer> mp = new HashMap<>();
        int ans = - 1;

        for (int i : arr) {
            if (! mp.containsKey(i)) {
                mp.put(i, 1);
            } else {
                mp.put(i, mp.get(i) + 1);
            }
        }

        for (int num : mp.keySet()) {
            if (num == mp.get(num)) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }

    private static int findLucky2(int[] arr) {

        int ans = - 1;
        int[] freq = new int[501];

        for (int i : arr) {
            freq[i]++;
        }

        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                ans = i;
                break;
            }
        }

        return ans;

    }


    public static void main(String[] args) {

        int[] arr = {2, 2, 3, 4};

        System.out.println("From method 1 : ");
        System.out.println("The maximum lucky integer which has the same frequency as its number is : " + findLucky1(arr));

        System.out.println("From method 2 : ");
        System.out.println("The maximum lucky integer which has the same frequency as its number is : " + findLucky1(arr));

    }

}

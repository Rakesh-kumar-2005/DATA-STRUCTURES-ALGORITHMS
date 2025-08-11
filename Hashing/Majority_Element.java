package Hashing;

import java.util.ArrayList;
import java.util.HashMap;

public class Majority_Element {


    private static ArrayList<Integer> majorityElement(int[] arr) {

        int n = arr.length / 3;
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {

            if (! map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }

            if (map.get(num) > n && ! ans.contains(num)) {
                ans.add(num);
            }
        }

        return ans;

    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 1, 2, 1, 1, 1, 2, 2, 2,};
        ArrayList<Integer> ans = majorityElement(arr);

        System.out.println("The majority elements are : " + ans);

    }

}
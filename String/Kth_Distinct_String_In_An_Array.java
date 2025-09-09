package String;

import java.util.LinkedHashMap;

public class Kth_Distinct_String_In_An_Array {

    private static String kthDistinct(String[] arr, int k) {
        LinkedHashMap<String, Integer> mp = new LinkedHashMap<>();

        for (String x : arr) {
            if (mp.containsKey(x)) {
                mp.put(x, mp.get(x) + 1);
            } else {
                mp.put(x, 1);
            }
        }

        int count = 0;

        for (String s : mp.keySet()) {
            if (mp.get(s) == 1) {
                count++;
                if (count == k) {
                    return s;
                }
            }
        }

        return "";
    }

    public static void main(String[] args) {

        String[] arr = {"a", "b", "a", "c", "c", "d", "e"};
        int k = 2;

        System.out.println("The " + k + "th distinct string in the array is : " + kthDistinct(arr, k));

    }

}
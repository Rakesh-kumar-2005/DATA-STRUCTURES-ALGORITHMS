package Hashing;

import java.util.HashMap;
import java.util.HashSet;

public class Unique_Number_Of_Occurrences {

    private static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i : arr) {
            if (mp.containsKey(i)) {
                mp.put(i, mp.get(i) + 1);
            } else {
                mp.put(i, 1);
            }
        }
        HashSet<Integer> st = new HashSet<>();
        for (int i : mp.values()) {
            st.add(i);
        }
        return st.size() == mp.size();
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 1, 1, 3};
        boolean ans = uniqueOccurrences(arr);

        if (ans) {
            System.out.println("All elements have unique number of occurrences...");
        } else {
            System.out.println("All elements do not have unique number of occurrences...");
        }

    }

}

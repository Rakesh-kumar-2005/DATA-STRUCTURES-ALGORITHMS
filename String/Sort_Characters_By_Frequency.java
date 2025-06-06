package String;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Characters_By_Frequency {

    private static String frequencySort(String s) {

        if (s.isEmpty()) {
            return null;
        }

        HashMap<Character, Integer> mp = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            if (! mp.containsKey(currChar)) {
                mp.put(currChar, 1);
            } else {
                mp.put(currChar, mp.get(currChar) + 1);
            }

        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(mp.entrySet());

        StringBuilder ans = new StringBuilder();

        while (! pq.isEmpty()) {

            Map.Entry<Character, Integer> currEntry = pq.poll();
            int frequency = currEntry.getValue();

            while (frequency != 0) {
                ans.append(currEntry.getKey());
                frequency--;
            }

        }

        return ans.toString();

    }

    public static void main(String[] args) {

        String s = "cvtcvagCCXrr";
        System.out.println("Original String is : " + s);
        System.out.println("Sorted String based on frequency is : " + frequencySort(s));

    }

}
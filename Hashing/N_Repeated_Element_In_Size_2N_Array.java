package Hashing;

import java.util.HashMap;
import java.util.HashSet;

public class N_Repeated_Element_In_Size_2N_Array {

    private static int repeatedNTimes1(int[] numbers) {

        int n = numbers.length;
        int frequency = n / 2;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : numbers) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        for (int key : map.keySet()) {
            if (map.get(key) == frequency) {
                return key;
            }
        }

        return - 1;

    }

    private static int repeatedNTimes2(int[] numbers) {

        HashSet<Integer> seen = new HashSet<>();

        for (int num : numbers) {
            if (! seen.add(num)) {
                return num;
            }
        }

        return - 1;
    }


}

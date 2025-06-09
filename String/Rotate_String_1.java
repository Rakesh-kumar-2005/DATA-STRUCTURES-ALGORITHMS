package String;

import java.util.Arrays;

public class Rotate_String_1 {

    private static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            return true;
        }
        char[] st = s.toCharArray();
        char[] gt = goal.toCharArray();

        for (int i = 1; i < st.length; i++) {
            rotate(st, i);
            if (Arrays.equals(st, gt)) return true;
            else rotate(st, st.length - i);
        }
        return false;

    }

    private static void rotate(char[] arr, int i) {

        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, i - 1);
        reverse(arr, i, arr.length - 1);

    }

    private static void reverse(char[] arr, int i, int j) {

        while (i <= j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

    }

    public static void main(String[] args) {

        String s = "abcde";
        String goal = "cdeab";

        boolean result = rotateString(s, goal);

        if (result) {
            System.out.println("The given strings can be rotated to each other");
        } else {
            System.out.println("The given strings cannot be rotated to each other");
        }

    }

}
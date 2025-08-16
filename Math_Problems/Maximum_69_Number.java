package Math_Problems;

import java.util.ArrayList;

public class Maximum_69_Number {

    private static int maximum69Number1(int number) {
        ArrayList<Integer> ans = new ArrayList<>();

        while (number > 0) {
            ans.add(0, number % 10);
            number /= 10;
        }

        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i) == 6) {
                ans.set(i, 9);
                break;
            }
        }

        int result = 0;
        for (int digit : ans) {
            result = result * 10 + digit;
        }

        return result;
    }

    private static int maximum69Number2(int number) {
        return Integer.parseInt(String.valueOf(number).replaceFirst("6", "9"));
    }

    public static void main(String[] args) {

        int number = 69699;
        System.out.println("The maximum 69 number from the method 1 for the number " + number + " is = " + maximum69Number1(number));
        System.out.println("The maximum 69 number from the method 2 for the number " + number + " is = " + maximum69Number2(number));

    }

}
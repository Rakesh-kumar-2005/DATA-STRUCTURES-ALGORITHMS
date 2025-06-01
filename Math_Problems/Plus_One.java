package Math_Problems;

import java.util.ArrayList;

public class Plus_One {

    private static int[] plusOne1(int[] digits) {

        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;

        }

        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;

        return newDigits;

    }

    private static int[] plusOne2(int[] digits) {

        ArrayList<Integer> ans = new ArrayList<>();

        int i = digits.length - 1;
        int sum = 0;
        int carry = 1;

        while (i >= 0 || carry != 0) {

            int val = (i >= 0) ? digits[i] : 0;

            sum = val + carry;
            carry = sum / 10;
            int rem = sum % 10;

            ans.add(rem);
            i--;

        }

        int[] result = new int[ans.size()];
        int idx = 0;

        for (int j = ans.size() - 1; j >= 0; j--) {
            result[idx++] = ans.get(j);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] digits = {9, 9, 9};

        int[] result = plusOne1(digits);

        System.out.println("Output from method 1: ");
        for (int num : result) {
            System.out.print(num);
        }

        System.out.println();

        digits = new int[]{9, 9, 9};
        result = plusOne2(digits);

        System.out.println("Output from method 2: ");
        for (int num : result) {
            System.out.print(num);
        }

    }

}
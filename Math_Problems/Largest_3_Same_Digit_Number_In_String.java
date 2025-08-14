package Math_Problems;

public class Largest_3_Same_Digit_Number_In_String {

    private static String largestGoodInteger(String number) {
        String ans = "";

        for (int i = 0; i <= number.length() - 3; i++) {
            String temp = number.substring(i, i + 3);

            if (temp.charAt(0) == temp.charAt(1) && temp.charAt(1) == temp.charAt(2)) {
                if (ans.isEmpty() || temp.compareTo(ans) > 0) {
                    ans = temp;
                }
            }

        }

        return ans;

    }

    public static void main(String[] args) {

        String number = "6777133339";
        String ans = largestGoodInteger(number);
        System.out.println("The largest good integer in the string " + number + " is = " + ans);

    }

}
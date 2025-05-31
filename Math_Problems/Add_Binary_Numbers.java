package Math_Problems;

public class Add_Binary_Numbers {

    private static String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {

            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();

    }

    public static void main(String[] args) {

        String a = "11";
        String b = "1";

        System.out.println("The sum of " + a + " and " + b + " is: " + addBinary(a, b));
        
    }

}
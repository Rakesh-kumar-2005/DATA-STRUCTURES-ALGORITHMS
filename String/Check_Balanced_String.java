package String;

public class Check_Balanced_String {

    private static boolean isBalanced(String num) {

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';

            if (i % 2 == 0) {
                sum1 += val;
            } else {
                sum2 += val;
            }

        }

        return sum1 == sum2;
    }

    public static void main(String[] args) {

        String s = "324132526";
        boolean isBalanced = isBalanced(s);

        if (isBalanced) {
            System.out.println("The given string is balanced");
        } else {
            System.out.println("The given string is not balanced");
        }
        
    }

}
package String;

public class Valid_Palindrome_1 {

    private static boolean isPalindrome(String s) {

        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        s = s.toLowerCase();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char currChar = s.charAt(i);
            if (checkChar(currChar)) {
                result.append(currChar);
            }

        }

        int i = 0;
        int j = result.length() - 1;

        while (i <= j) {

            if (result.charAt(i) != result.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;

    }

    private static boolean checkChar(char ch) {

        if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
            return true;
        }

        return false;

    }

    public static void main(String[] args) {

        String s = "A man, a plan, a canal: Panama";
        boolean result = isPalindrome(s);

        if (result) {
            System.out.println("The given string is a palindrome");
        } else {
            System.out.println("The given string is not a palindrome");
        }

    }

}
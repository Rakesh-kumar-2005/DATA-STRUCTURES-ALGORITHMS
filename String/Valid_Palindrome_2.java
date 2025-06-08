package String;

public class Valid_Palindrome_2 {

    private static boolean isPalindrome(String s) {

        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        int i = 0, j = s.length() - 1;

        while (i < j) {

            char ci = s.charAt(i);
            char cj = s.charAt(j);

            if (ci >= 'A' && ci <= 'Z') {
                ci += 32;
            }
            if (cj >= 'A' && cj <= 'Z') {
                cj += 32;
            }

            if (! ((ci >= 'a' && ci <= 'z') || (ci >= '0' && ci <= '9'))) {
                i++;
                continue;
            }
            if (! ((cj >= 'a' && cj <= 'z') || (cj >= '0' && cj <= '9'))) {
                j--;
                continue;
            }

            if (ci != cj) {
                return false;
            }

            i++;
            j--;
        }

        return true;
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
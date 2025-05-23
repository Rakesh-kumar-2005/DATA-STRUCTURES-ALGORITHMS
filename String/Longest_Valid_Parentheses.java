package String;

public class Longest_Valid_Parentheses {

    private static int longestValidParentheses(String s) {

        int left = 0;
        int right = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(2 * left, maxLength);
            }

            if (left < right) {
                left = right = 0;
            }

        }

        left = right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(2 * left, maxLength);
            }

            if (left > right) {
                left = right = 0;
            }

        }
        return maxLength;

    }

    public static void main(String[] args) {

        String s = "((()))()(()))))((()))(((())()()((((((()))))))";
        System.out.println("Longest Valid Parentheses of the String has the length of : " + longestValidParentheses(s));
    }

}
package Stacks;

import java.util.Stack;

public class Longest_Valid_Parentheses {

    private static boolean isValid(String s) {

        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<>();

        for (char c : arr) {

            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {

                if (st.isEmpty()) {
                    return false;
                } else {
                    char top = st.peek();

                    if ((c == ')' && top == '(') || (c == '}' && top == '{') || (c == ']' && top == '[')) {
                        st.pop();
                    } else {
                        return false;
                    }

                }

            }

        }

        return st.isEmpty();
    }

    private static int longestValidParentheses(String s) {

        if (s.isEmpty() || s.length() == 1) {
            return 0;
        }

        int maxSub = 0;

        for (int i = 0; i < s.length(); i++) {
            int temp = 0;

            for (int j = i + 1; j <= s.length(); j++) {
                if (isValid(s.substring(i, j))) {
                    temp = s.substring(i, j).length();
                }
            }

            maxSub = Math.max(maxSub, temp);

        }

        return maxSub;
    }

    public static void main(String[] args) {

        String s = "()((()))()))()())))()()()()()()((()))";
        System.out.println("The longest valid parentheses have the length of : " + longestValidParentheses(s));

    }

}
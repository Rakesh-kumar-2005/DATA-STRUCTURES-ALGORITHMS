package Stack;

import java.util.Stack;

public class Valid_Parentheses {

    private static boolean isValid(String brackets) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < brackets.length(); i++) {
            char currChar = brackets.charAt(i);

            if (currChar == '(' || currChar == '[' || currChar == '{') {
                st.push(currChar);
            } else {

                if (st.isEmpty()) {
                    return false;
                } else {

                    if ((currChar == ')' && st.peek() == '(') || (currChar == ']' && st.peek() == '[') || (currChar == '}' && st.peek() == '{')) {
                        st.pop();
                    } else {
                        return false;
                    }

                }

            }

        }

        return st.isEmpty();
    }

    public static void main(String[] args) {

        String brackets = "()[]{}";
        boolean ans = isValid(brackets);

        if (ans) {
            System.out.println("The following String brackets " + brackets + " is Valid");
        } else {
            System.out.println("The following String brackets " + brackets + " is Not Valid");
        }

    }

}
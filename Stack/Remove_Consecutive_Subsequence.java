package Stack;

import java.util.Scanner;
import java.util.Stack;

public class Remove_Consecutive_Subsequence {

    private static String removeConsecutive(String word) {
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < word.length(); i++){
            if(st.isEmpty() || st.peek() != word.charAt(i)){
                st.push(word.charAt(i));
            }
        }
        while (!st.isEmpty()){
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String with same characters : ");
        String word = sc.next();

        System.out.println(removeConsecutive(word));
    }
}


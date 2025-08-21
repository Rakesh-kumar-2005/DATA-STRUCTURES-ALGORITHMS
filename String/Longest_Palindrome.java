package String;

import java.util.HashSet;

public class Longest_Palindrome {

    private static int longestPalindrome(String s) {
        HashSet<Character> mp = new HashSet<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (! mp.contains(ch)) {
                mp.add(ch);
            } else {
                mp.remove(ch);
                count += 2;
            }

        }
        if (! mp.isEmpty()) {
            count++;
        }

        return count;

    }

    public static void main(String[] args) {

        String s = "abccccdd";
        System.out.println("The longest palindrome that can be formed from the given string has the length of : " + longestPalindrome(s));

    }

}
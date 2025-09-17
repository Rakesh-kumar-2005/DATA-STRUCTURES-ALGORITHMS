package Hashing;

import java.util.HashMap;

public class Find_Most_Frequent_Vowel_And_Consonant {

    private static int maxFreqSum(String word) {

        HashMap<Character, Integer> mp1 = new HashMap<>();
        HashMap<Character, Integer> mp2 = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);

            if (isVowel(currChar)) {

                if (! mp1.containsKey(currChar)) {
                    mp1.put(currChar, 1);
                } else {
                    mp1.put(currChar, mp1.get(currChar) + 1);
                }

            } else {

                if (! mp2.containsKey(currChar)) {
                    mp2.put(currChar, 1);
                } else {
                    mp2.put(currChar, mp2.get(currChar) + 1);
                }

            }

        }

        int maxVowel = 0;
        int maxConsonants = 0;

        for (char key : mp1.keySet()) {
            maxVowel = Math.max(maxVowel, mp1.get(key));
        }

        for (char key : mp2.keySet()) {
            maxConsonants = Math.max(maxConsonants, mp2.get(key));
        }

        return maxVowel + maxConsonants;

    }

    private static boolean isVowel(char currChar) {
        if (currChar == 'a' || currChar == 'e' || currChar == 'i' || currChar == 'o' || currChar == 'u') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String word = "abcde";
        System.out.println("The addition of the maximum frequency of vowels and consonants in the word \"" + word + "\" is : " + maxFreqSum(word));

    }

}
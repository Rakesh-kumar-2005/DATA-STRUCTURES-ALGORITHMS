package Hashing;

/*

Description:
    -> This program finds the most frequent vowel and the most frequent consonant in a given word...
    -> It calculates the frequency of each character separately for vowels and consonants...
    -> Finally, it returns the sum of the highest frequency among vowels and the highest frequency among consonants...

Problem Statement:
    -> Given a lowercase string `word`, determine:
         1. The maximum frequency of any vowel in the string...
         2. The maximum frequency of any consonant in the string...
    -> Return the sum of these two values...

Approach:
    > Frequency Counting with HashMaps:
        -> Use one HashMap to store frequencies of vowels...
        -> Use another HashMap to store frequencies of consonants...
        -> Traverse the string character by character:
             - If character is a vowel, update vowel frequency in `mp1`...
             - Else, update consonant frequency in `mp2`...
        -> After traversal, find the maximum frequency in each HashMap...
        -> Return the sum of these maximum frequencies...

Algorithm Steps:
    -> Initialization:
        1. Create two HashMaps: `mp1` (for vowels) and `mp2` (for consonants)...

    -> Traversal:
        1. Loop through each character of the string...
        2. Use helper function `isVowel()` to check whether the character is a vowel...
        3. Update the corresponding HashMap:
             - If character not present, insert with frequency 1...
             - Else, increment the frequency...

    -> Maximum Frequency Calculation:
        1. Initialize `maxVowel = 0` and `maxConsonants = 0`...
        2. Traverse `mp1` to find maximum vowel frequency...
        3. Traverse `mp2` to find maximum consonant frequency...

    -> Return:
        1. Return `maxVowel + maxConsonants` as the result...

Key Characteristics:
    -> Efficient use of HashMaps for frequency counting...
    -> Separates vowel and consonant frequencies clearly...
    -> Uses a helper method `isVowel()` for clean design...
    -> Works for any lowercase string of alphabetic characters...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string (each character processed once)...
    -> Space Complexity: O(1), since HashMaps store at most 26 characters (bounded by alphabet size)...

Demonstration:
    -> Input: "abcde"
    -> Vowel frequencies: {a=1, e=1} → maxVowel = 1
    -> Consonant frequencies: {b=1, c=1, d=1} → maxConsonant = 1
    -> Sum = 1 + 1 = 2
    -> Output: "The addition of the maximum frequency of vowels and consonants in the word "abcde" is : 2"

*/

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

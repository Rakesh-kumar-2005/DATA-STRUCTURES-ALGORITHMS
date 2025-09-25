package String;

/*

Description:
    -> This program checks whether a given string is a **valid word** based on specific criteria...
    -> A valid word must have a minimum length of 3 and contain at least one vowel and one consonant...
    -> The word can include both letters and digits, but no special characters...

Problem Statement:
    -> Given a string `word`, determine whether it qualifies as a valid word based on the following rules:
        a. It must contain at least 3 characters...
        b. It must contain **at least one vowel** (a, e, i, o, u)...
        c. It must contain **at least one consonant**...
        d. All characters must be either alphabets (uppercase or lowercase) or digits...
        e. If any character is a symbol or special character, the word is invalid...

Approach:
    > Character-wise Validation:
        -> Loop through each character of the string...
        -> If it's a letter, convert uppercase to lowercase...
            --> Check if it's a vowel or consonant and update respective counts...
        -> If it's not a letter, check if it's a digit...
            --> If it's neither a digit nor a letter, return false immediately...

    > Final Validation:
        -> After checking all characters, confirm:
            --> The string has at least one vowel...
            --> The string has at least one consonant...

Algorithm Steps:
    -> Step 1: If length of the word is less than 3 → return false...
    -> Step 2: Initialize vowelCount and consonantCount to 0...
    -> Step 3: Traverse each character:
        a. If letter → convert to lowercase if uppercase...
            --> If vowel → increment vowelCount...
            --> Else → increment consonantCount...
        b. If not a digit → return false...
    -> Step 4: After loop, return true if vowelCount ≥ 1 and consonantCount ≥ 1...

Key Characteristics:
    -> Ensures input word is alphanumeric with required character diversity...
    -> Handles both uppercase and lowercase letters consistently...
    -> Rejects symbols or punctuation strictly...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the input string...
    -> Space Complexity: O(1), only counters and flags are used...

Demonstration:
    -> Input: "Abc123"
    -> Characters: A (vowel), b (consonant), c (consonant), 1, 2, 3
    -> Output: The given string is a valid word

*/

public class Valid_Word {
    private static boolean isValid(String word) {

        if (word.length() < 3) {
            return false;
        }

        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);

            if (isLetter(currChar)) {

                if (currChar >= 'A' && currChar <= 'Z') {
                    currChar += 32;
                }

                if (isVowel(currChar)) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }

            } else if (! isDigit(currChar)) {
                return false;
            }

        }

        return (vowelCount >= 1 && consonantCount >= 1);
    }

    private static boolean isLetter(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String word = "Abc123";
        boolean result = isValid(word);

        if (result) {
            System.out.println("The given string is a valid word");
        } else {
            System.out.println("The given string is not a valid word");
        }

    }

}

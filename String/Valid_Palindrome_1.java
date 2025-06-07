package String;

/*

Description:
    -> This program checks whether a given string is a Valid palindrome, considering only alphanumeric characters and ignoring cases...
    -> A string is considered a valid palindrome if it reads the same backward as forward after removing all non-alphanumeric characters...

Problem Statement:
    -> Given a string `s`, return `true` if it is a palindrome, or `false` otherwise...
    -> Ignore punctuation, spaces, and letter casing while checking...

Approach:
    > String Normalization:
        -> Convert the entire string to lowercase to handle case-insensitivity...
        -> Remove all characters that are not letters (a–z) or digits (0–9)...

    > Palindrome Check:
        -> Use two pointers (`i` and `j`) starting from beginning and end of the filtered string...
        -> Move inward and compare characters at each position...
        -> If at any point the characters don’t match, return false...
        -> If all characters match, return true...

Algorithm Steps:
    -> Base Case Handling:
        1. If the string is empty or has only one character, return true...

    -> Filtering Non-Alphanumeric Characters:
        1. Traverse each character of the input string...
        2. Append it to a `StringBuilder` if it's alphanumeric (checked using the `checkChar()` helper function)...

    -> Palindrome Verification:
        1. Initialize two pointers `i = 0` and `j = length - 1`...
        2. While `i <= j`:
            a. If characters at position `i` and `j` differ, return false...
            b. Increment `i` and decrement `j`...

    -> Return true if all characters matched during traversal...

Helper Function:
    > `checkChar(char ch)`:
        -> Returns true if the character is a digit or a lowercase letter...

Key Characteristics:
    -> Ignores case and non-alphanumeric characters...
    -> Uses a two-pointer technique for efficient comparison...
    -> Separates preprocessing and logic for readability and maintainability...

Time and Space Complexity:
    -> Time Complexity: O(n), where `n` is the length of the input string (each character is processed once)...
    -> Space Complexity: O(n), for storing the filtered string...

Demonstration:
    -> Input: "A man, a plan, a canal: Panama"
    -> Normalized: "amanaplanacanalpanama"
    -> Output: true (since it reads the same forward and backward)...

*/

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

package String;

/*

Description:
    -> This program checks if a given string is a **valid palindrome**, considering only alphanumeric characters and ignoring case...
    -> It performs in-place character validation and comparison using a two-pointer approach, without building a new filtered string...

Problem Statement:
    -> Given a string `s`, determine whether it reads the same backward as forward...
    -> Consider only letters and digits, and ignore letter casing...

Approach:
    > Two-Pointer Technique with On-the-Fly Filtering:
        -> Initialize two pointers: one at the beginning (`i`), one at the end (`j`) of the string...
        -> Skip non-alphanumeric characters from both ends...
        -> Convert uppercase characters to lowercase manually (ASCII manipulation)...
        -> Compare characters at both pointers:
            --> If they differ, return false...
            --> If they match, move both pointers inward...

Algorithm Steps:
    -> Base Case Handling:
        1. If the input string is empty or has one character, return true...

    -> In-Place Palindrome Check:
        1. Initialize `i = 0` and `j = s.length() - 1`...
        2. While `i < j`:
            a. Convert characters at `i` and `j` to lowercase if they are uppercase...
            b. If the character at `i` is not alphanumeric, move `i` forward and continue...
            c. If the character at `j` is not alphanumeric, move `j` backward and continue...
            d. Compare characters at `i` and `j`:
                - If not equal, return false...
                - Else, increment `i` and decrement `j`...

    -> Return true if all characters match according to the rules...

Key Characteristics:
    -> Optimized space usage (no new string or `StringBuilder`)...
    -> Character conversion and validation handled directly using ASCII values...
    -> Efficient for long strings with many non-alphanumeric characters...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the length of the string (each character checked at most once)...
    -> Space Complexity: O(1), as only constant extra space is used (no new collections)...

Demonstration:
    -> Input: "A man, a plan, a canal: Panama"
    -> Valid characters (normalized in-place): "amanaplanacanalpanama"
    -> Output: true (since it is a valid palindrome)...

*/

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

package Math_Problems;

/*

Description:
    -> This program finds the largest "good integer" in a numeric string.
    -> A "good integer" is defined as a substring of length 3 where all three digits are identical.
    -> The algorithm scans through the string, extracts substrings of length 3, 
       checks if all digits are the same, and keeps track of the largest such substring.

Problem Statement:
    -> Given a string `number` consisting only of digits (0–9), 
       find the largest "good integer" in terms of numerical value.
    -> Return the largest good integer as a string.
    -> If no such substring exists, return an empty string.

Approach:
    > Sliding Window of Size 3:
        -> Iterate over the string using index i from 0 to (length - 3).
        -> Extract the substring of length 3: temp = number.substring(i, i + 3).
        -> Check if all three characters are equal:
            - temp.charAt(0) == temp.charAt(1) 
            - temp.charAt(1) == temp.charAt(2)
        -> If they are equal:
            - Compare `temp` with the current largest `ans` using string comparison.
            - Update `ans` if `temp` is larger.
        -> Continue until the end of the string.

Algorithm Steps:
    1. Initialize ans = "" (empty string).
    2. For i from 0 to number.length() - 3:
        a. Extract substring of length 3.
        b. If all three characters are equal:
            - If ans is empty or temp > ans → update ans.
    3. Return ans.

Key Characteristics:
    -> Uses string comparison to determine the largest "good integer".
    -> Handles cases where no good integer exists by returning an empty string.
    -> Works for any numeric string of length ≥ 3.

Time and Space Complexity:
    -> Time Complexity: O(n), where n = length of the string.
    -> Space Complexity: O(1) (only a few extra variables used).

Example:
    Input:
        number = "6777133339"
    Output:
        The largest good integer in the string 6777133339 is = 777

*/

public class Largest_3_Same_Digit_Number_In_String {

    private static String largestGoodInteger(String number) {
        String ans = "";

        for (int i = 0; i <= number.length() - 3; i++) {
            String temp = number.substring(i, i + 3);

            if (temp.charAt(0) == temp.charAt(1) && temp.charAt(1) == temp.charAt(2)) {
                if (ans.isEmpty() || temp.compareTo(ans) > 0) {
                    ans = temp;
                }
            }

        }

        return ans;

    }

    public static void main(String[] args) {

        String number = "6777133339";
        String ans = largestGoodInteger(number);
        System.out.println("The largest good integer in the string " + number + " is = " + ans);

    }


}

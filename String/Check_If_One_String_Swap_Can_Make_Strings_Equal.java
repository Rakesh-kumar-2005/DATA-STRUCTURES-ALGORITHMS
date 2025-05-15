package String;

/*

Description:
    Following program demonstrates the solution to the "Check If One String Swap Can Make Strings Equal" 
    problem using character comparison and positional tracking...

Problem Statement:
    -> Given two strings s1 and s2 of equal length...
    -> Determine if they can be made equal by performing exactly one swap in one of the strings...
    -> A swap involves exchanging two characters at different positions in the same string...

Approach:
    > Character Difference Tracking:
        i. Compare the strings character by character to find all positions where they differ...
        ii. Count the number of differences between the strings...
        iii. Check if a single swap can resolve these differences...

    > Algorithm Steps:
        -> First check the base cases:
            1. If strings are already equal, return true (no swap needed)...
            2. If strings have different lengths, return false (swap won't help)...

        -> Character comparison and difference tracking:
            1. Initialize indices i and j to track positions of differences...
            2. Initialize a counter to track total number of differences found...
            3. Iterate through both strings, comparing characters at the same position...
            4. When a difference is found, store its position and increment the counter...

        -> Swap validation:
            1. For a valid swap, exactly two positions must differ (count = 2)...
            2. The characters at these positions must be swappable: s1[i] = s2[j] and s1[j] = s2[i]...

    > Key Characteristics:
        -> Handles all edge cases: equal strings, strings of different lengths...
        -> Works with any valid input strings containing any characters...
        -> Efficiently identifies if a single swap can make strings equal...
        -> Uses constant extra space for tracking positions and count...
        -> Returns a boolean result indicating if strings can be made equal...

    > Implementation Details:
        -> Uses three variables to track positions and count differences...
        -> Single-pass algorithm with O(n) time complexity where n is string length...
        -> Demonstrates the solution with example test cases...
        -> Correctly handles cases where no swap is needed (strings already equal)...
        -> Validates both the count of differences and character interchangeability...

    > Time and Space Complexity:
        -> Time Complexity: O(n) where n is the length of the strings...
        -> Space Complexity: O(1) as only constant extra space is used...
        -> Single traversal through both strings for efficient comparison...
*/

public class Check_If_One_String_Swap_Can_Make_Strings_Equal {

    private static boolean areAlmostEqual(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        int i = - 1;
        int j = - 1;
        int count = 0;

        for (int k = 0; k < s1.length(); k++) {

            if (s1.charAt(k) != s2.charAt(k)) {

                count++;

                if (i == - 1) {
                    i = k;
                } else if (j == - 1) {
                    j = k;
                }

            }

        }

        if (count == 2 && s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i)) {
            return true;
        }

        return false;

    }

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "hello";

        System.out.println("Are Strings Equal : " + areAlmostEqual(s1, s2));

        String s3 = "hello";
        String s4 = "lelho";

        System.out.println("Are Strings Equal : " + areAlmostEqual(s3, s4));

    }

}

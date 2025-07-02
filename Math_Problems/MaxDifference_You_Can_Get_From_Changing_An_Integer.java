package Math_Problems;

/*
Description:
    -> This program calculates the maximum possible difference between two integers obtained by changing digits in the given number...
    -> It transforms the number in two ways:
        --> One to get the maximum value by replacing digits with '9'...
        --> Another to get the minimum value by replacing digits with '1' or '0' based on position...

Problem Statement:
    -> Given an integer num, you are allowed to replace all occurrences of a single digit with another digit (0-9)...
    -> You can do this at most once for both the maximum and minimum version of the number...
    -> Return the maximum difference between these two remapped numbers...

Approach:
    > Max Version:
        -> Find the first digit from left that is not '9'...
        -> Replace all its occurrences with '9' to maximize the number...

    > Min Version:
        -> If the first digit is not '1', replace it with '1' to avoid leading zeros...
        -> Else, find the first non-zero digit after index 0 that is not '1', and replace all its occurrences with '0'...

Algorithm Steps:
    -> Step 1: Convert the integer to a character array for easy manipulation...
    -> Step 2: For max value:
        a. Identify the first digit not equal to '9'...
        b. Replace all its occurrences with '9'...
    -> Step 3: For min value:
        a. If first digit ≠ '1', replace it with '1'...
        b. Else, look for next digit ≠ '0' or '1' and replace all such digits with '0'...
    -> Step 4: Convert both character arrays back to integers...
    -> Step 5: Return the difference between max and min values...

Key Characteristics:
    -> Digit replacement happens only once per transformation...
    -> Ensures no leading zero in final output...
    -> Simple linear scan of digits makes it efficient...

Time and Space Complexity:
    -> Time Complexity: O(n), where n is the number of digits in the number...
    -> Space Complexity: O(n), for storing character arrays of the number...

Demonstration:
    -> Input: num = 1234
    -> Maximum remap: 9234 (replace '1' with '9')
    -> Minimum remap: 1034 (replace '2' with '0')
    -> Output: 9234 - 1034 = 8200

*/

public class MaxDifference_You_Can_Get_From_Changing_An_Integer {

    private static int maxDiff(int num) {

        String number = Integer.toString(num);
        char[] maxNum = number.toCharArray();
        char[] minNum = number.toCharArray();

        char replaceForMax = ' ';

        for (char curr : maxNum) {
            if (curr != '9') {
                replaceForMax = curr;
                break;
            }
        }

        for (int i = 0; i < maxNum.length; i++) {
            if (maxNum[i] == replaceForMax) {
                maxNum[i] = '9';
            }
        }

        char replaceForMin = ' ';
        char replaceWithMin = ' ';

        if (minNum[0] != '1') {
            replaceForMin = minNum[0];
            replaceWithMin = '1';
        } else {
            for (int i = 1; i < minNum.length; i++) {
                if (minNum[i] != '0' && minNum[i] != '1') {
                    replaceForMin = minNum[i];
                    replaceWithMin = '0';
                    break;
                }
            }
        }

        for (int i = 0; i < minNum.length; i++) {
            if (minNum[i] == replaceForMin) {
                minNum[i] = replaceWithMin;
            }
        }

        int maxValue = Integer.parseInt(new String(maxNum));
        int minValue = Integer.parseInt(new String(minNum));

        System.out.println("The maximum value of the number " + num + " after remapping is = " + maxValue);
        System.out.println("The minimum value of the number " + num + " after remapping is = " + minValue);

        return maxValue - minValue;

    }

    public static void main(String[] args) {

        int num = 1234;
        System.out.println("The maximum difference in the number " + num + " is = " + maxDiff(num));

    }

}

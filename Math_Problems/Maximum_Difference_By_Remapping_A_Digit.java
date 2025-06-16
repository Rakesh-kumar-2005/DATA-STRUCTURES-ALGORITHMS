package Math_Problems;

/*

Description:
    -> This program computes the **maximum possible difference** between two numbers formed by remapping digits of a given number...
    -> The objective is to replace **one digit type** in the number with another to get a **maximum and minimum** version...

Problem Statement:
    -> Given an integer `num`, form two new integers:
        1. A maximum number by replacing **one digit** with `'9'` (in all its occurrences)...
        2. A minimum number by replacing **one digit** (first digit) with `'0'` (in all its occurrences)...
    -> Return the **difference** between the maximum and minimum values formed...

Approach:
    > Convert to Character Arrays:
        -> Convert the number into character arrays to allow easy digit replacement...

    > Construct Maximum Value:
        -> Find the **first non-'9' digit** and replace all its occurrences with `'9'`...
        -> This maximizes the number by inflating its digits...

    > Construct Minimum Value:
        -> Take the **first digit** and replace all its occurrences with `'0'`...
        -> This minimizes the number by reducing leading digit contribution...

    > Convert Both Arrays to Integers:
        -> After modification, parse both character arrays back to integers...
        -> Compute and return their difference...

Algorithm Steps:
    -> Step 1: Convert the number to string and then to two character arrays `maxNum` and `minNum`...
    -> Step 2: For maximum value:
        a. Find the first digit not equal to '9'...
        b. Replace all its occurrences with '9' in `maxNum`...
    -> Step 3: For minimum value:
        a. Identify the first digit...
        b. Replace all its occurrences with '0' in `minNum`...
    -> Step 4: Parse both arrays back to integers and return the difference...

Key Characteristics:
    -> Replaces only one digit type in each transformation (but all its occurrences)...
    -> Ensures that the number remains valid (no digit rearrangement or length change)...
    -> Prints intermediate values to aid debugging...

Time and Space Complexity:
    -> Time Complexity: O(n), where `n` is the number of digits (each digit is checked and possibly replaced once)...
    -> Space Complexity: O(n), for character arrays...

Demonstration:
    -> Input: num = 1234
    -> Max remap: Replace '1' with '9' → 9234
    -> Min remap: Replace '1' with '0' → 0234 = 234
    -> Output: 9234 - 234 = 9000

*/

public class Maximum_Difference_By_Remapping_A_Digit {

    private static int minMaxDifference(int num) {

        String number = Integer.toString(num);
        char[] maxNum = number.toCharArray();
        char[] minNum = number.toCharArray();

        int n = number.length();
        char replaceForMax = ' ';

        for (char curr : maxNum) {
            if (curr != '9') {
                replaceForMax = curr;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (maxNum[i] == replaceForMax) {
                maxNum[i] = '9';
            }
        }

        int replaceForMin = minNum[0];

        for (int i = 0; i < n; i++) {
            if (minNum[i] == replaceForMin) {
                minNum[i] = '0';
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
        System.out.println("The maximum difference in the number " + num + " is = " + minMaxDifference(num));

    }

}

package Arrays;

/*
Description:
    This program calculates the total number of laser beams present in a security bank system,
    represented by a binary matrix (array of strings), where each character can be either '0' or '1'.

Problem Statement:
    Each string in the `bank` array represents a row in the security grid:
        - '1' indicates the presence of a security device.
        - '0' indicates an empty spot.
    A laser beam can only exist between two rows that both contain at least one device.
    The number of beams between two valid rows is given by:
        (number of devices in the first row) × (number of devices in the next non-empty row)

    Example:
        bank = {"011001", "000000", "010100", "001000"}

        - Row 1 → "011001" → 3 devices
        - Row 2 → "000000" → 0 devices (ignored)
        - Row 3 → "010100" → 2 devices
        - Row 4 → "001000" → 1 device

        Beams exist only between rows that have devices:
            (Row 1, Row 3): 3 × 2 = 6
            (Row 3, Row 4): 2 × 1 = 2
        Total beams = 6 + 2 = 8

Approach:
    1. Iterate through each row in the bank.
    2. For each row, count the number of '1's using the helper function `helper(String s)`.
    3. Skip empty rows (rows with 0 devices).
    4. For every non-empty row:
        - Multiply its device count with the previous non-empty row's device count.
        - Add the product to the total number of beams.
        - Update the previous row’s device count.
    5. Return the total number of beams after processing all rows.

Example Input:
    bank = {"011001", "000000", "010100", "001000"}

Example Output:
    The total number of beams in the bank is : 8

Key Concepts Used:
    - String traversal
    - Counting characters
    - Simple mathematical aggregation logic

Time and Space Complexity:
    Time Complexity: O(N × M)
        (Where N = number of rows, M = length of each row string)
    Space Complexity: O(1)
        (Only a few integer variables used)

*/

public class Number_Of_Laser_Beams_In_A_Bank {

    private static int helper(String s) {

        int numberOfLasers = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                numberOfLasers++;
            }
        }

        return numberOfLasers;

    }

    private static int numberOfBeams(String[] bank) {

        int currBeams = 0;
        int prevBeams = 0;
        int totalBeams = 0;
        int n = bank.length;

        for (String s : bank) {
            currBeams = helper(s);
            totalBeams += (currBeams * prevBeams);

            if (currBeams != 0) {
                prevBeams = currBeams;
            }
        }

        return totalBeams;
    }

    public static void main(String[] args) {

        String[] bank = {"011001", "000000", "010100", "001000"};
        System.out.println("The total number of beams in the bank is : " + numberOfBeams(bank));

    }

}

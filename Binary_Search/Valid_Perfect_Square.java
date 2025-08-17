package Binary_Search;

/*

Description:
    -> This program checks whether a given positive integer is a perfect square.
    -> A perfect square is a number that can be expressed as the square of an integer 
       (e.g., 1, 4, 9, 16, 25, ...).
    -> The algorithm uses binary search on the range of possible square roots to 
       efficiently determine if the number is a perfect square.

Problem Statement:
    -> Given a positive integer num, return true if it is a perfect square, otherwise return false.
    -> The solution must not use built-in functions like sqrt().

Approach:
    > Binary Search on Square Root Range:
        -> If num = 1 → return true (since 1 is a perfect square).
        -> Initialize search range: low = 0, high = num / 2.
           (No number greater than num/2 can be the square root of num, except num = 1).
        -> While low <= high:
            1. Find mid = (low + high) / 2.
            2. Compute mid * mid:
                - If mid * mid == num → return true.
                - If mid * mid < num → move right (low = mid + 1).
                - If mid * mid > num → move left (high = mid - 1).
        -> If no exact square found → return false.

Algorithm Steps:
    1. If num == 1 → return true.
    2. Set low = 0, high = num / 2.
    3. While low <= high:
        a. mid = (low + high) / 2.
        b. If mid * mid == num → return true.
        c. If mid * mid < num → set low = mid + 1.
        d. Else set high = mid - 1.
    4. Return false.

Key Characteristics:
    -> Avoids floating-point operations (ensures integer precision).
    -> Uses binary search for efficiency.
    -> Handles large numbers safely using long to prevent overflow.

Time and Space Complexity:
    -> Time Complexity: O(log n), due to binary search steps.
    -> Space Complexity: O(1), uses constant extra space.

Example:
    Input:
        num = 16
    Output:
        The number 16 is a perfect square number.

*/

public class Valid_Perfect_Square {

    private static boolean isPerfectSquare(int num) {

        if (num == 1)
            return true;

        long low = 0, high = num / 2;

        while (low <= high) {

            long mid = (low + high) / 2;

            if ((mid * mid) == num)
                return true;

            else if ((mid * mid) < num)
                low = mid + 1;

            else if ((mid * mid) > num)
                high = mid - 1;
        }
        return false;

    }

    public static void main(String[] args) {

        int num = 16;
        boolean ans = isPerfectSquare(num);

        if (ans) {
            System.out.println("The number " + num + " is a perfect square number.");
        } else {
            System.out.println("The number " + num + " is not a perfect square number.");
        }

    }

}

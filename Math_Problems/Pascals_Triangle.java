package Math_Problems;

/*

Description:
    -> This program generates Pascal's Triangle up to a given number of rows.
    -> It returns a 2D list where each inner list represents a row in Pascal's Triangle.

Problem Statement:
    -> Given an integer `numRows`, return the first `numRows` of Pascal's Triangle.

Approach:
    > Iterative Row-by-Row Construction:
        -> Start with the first row containing just [1].
        -> For each subsequent row:
            - Begin and end the row with 1.
            - Each inner element is the sum of the two elements directly above it from the previous row.

Algorithm Steps:
    -> Initialization:
        1. Create an empty list `ans` to hold the final triangle.
        2. Add the first row `[1]` to `ans`.

    -> Iterative Row Generation:
        1. For each row index `i` from 1 to `numRows - 1`:
            a. Initialize a new list `temp`.
            b. Add `1` as the first element of the row.
            c. Retrieve the previous row `prev` from `ans`.
            d. For each position `j` from 1 to `i - 1`, calculate:
                - temp[j] = prev[j - 1] + prev[j]
            e. Add `1` as the last element of the row.
            f. Append `temp` to `ans`.

    -> Finalizing:
        1. Return the list `ans` containing all rows of Pascal’s Triangle.

Key Characteristics:
    -> Efficiently builds each row using only the previous row.
    -> Uses space proportional to the number of rows and elements in the triangle.
    -> Avoids recursion, making it suitable for large input sizes.

Time and Space Complexity:
    -> Time Complexity: O(numRows^2), due to nested iteration over rows and elements.
    -> Space Complexity: O(numRows^2), to store all elements of the triangle.

Demonstration:
    -> Input: numRows = 5
    -> Output:
        [1]
        [1, 1]
        [1, 2, 1]
        [1, 3, 3, 1]
        [1, 4, 6, 4, 1]

*/

import java.util.ArrayList;

public class Pascals_Triangle {
    private static ArrayList<ArrayList<Integer>> generate(int numRows) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(1);
        ans.add(temp);

        if (numRows == 1) {
            return ans;
        }

        for (int i = 1; i < numRows; i++) {
            temp = new ArrayList<>();

            ArrayList<Integer> prev = ans.get(i - 1);
            temp.add(1);

            for (int j = 1; j < i; j++) {
                temp.add(prev.get(j - 1) + prev.get(j));
            }

            temp.add(1);
            ans.add(temp);
        }
        return ans;
    }

    public static void main(String[] args) {

        int numRows = 5;
        ArrayList<ArrayList<Integer>> ans = generate(numRows);

        System.out.println("Pascals Triangle of " + numRows + " rows is : ");
        for (ArrayList<Integer> an : ans) {
            System.out.println(an);
        }

    }


}

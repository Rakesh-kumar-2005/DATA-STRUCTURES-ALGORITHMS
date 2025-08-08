package Binary_Search;

/*

Description:
    -> This program finds a "peak" element in a 2D matrix.
    -> A peak element is defined as an element that is strictly greater than its
       left and right neighbors in the same row.
    -> The algorithm uses a binary search on columns to achieve O(m * log n) time complexity.

Problem Statement:
    -> Given an m x n matrix, find any one peak element's position [row, col].
    -> A peak is an element strictly greater than its immediate left and right neighbors
       in the same row.
    -> The top and bottom elements in the column are not compared, since the search
       focuses on column-wise maximums.

Approach:
    > Binary Search on Columns:
        -> Start with the leftmost and rightmost columns.
        -> Find the middle column (midCol).
        -> In midCol, find the row index (maxRow) where the value is maximum.
        -> Compare this maximum value with its left and right neighbors:
            - If it is greater than both → Peak found.
            - If the right neighbor is greater → Search moves to right half.
            - If the left neighbor is greater → Search moves to left half.

Algorithm Steps:
    1. Initialize pointers: left = 0, right = n - 1 (column indices).
    2. While left <= right:
        a. Find midCol = (left + right) / 2.
        b. Find maxRow in midCol.
        c. Let curr = matrix[maxRow][midCol].
        d. Let leftVal = matrix[maxRow][midCol - 1] if midCol > 0 else -1.
        e. Let rightVal = matrix[maxRow][midCol + 1] if midCol < n - 1 else -1.
        f. If curr > leftVal and curr > rightVal → return [maxRow, midCol].
        g. Else if rightVal > curr → move to right half (left = midCol + 1).
        h. Else → move to left half (right = midCol - 1).
    3. If no peak found → return [-1, -1].

Key Characteristics:
    -> Optimized search using binary search on columns.
    -> Avoids checking every element.
    -> Works for any m x n matrix where m, n ≥ 1.

Time and Space Complexity:
    -> Time Complexity: O(m * log n), where m = rows, n = columns.
    -> Space Complexity: O(1) (no extra space used).

Example:
    Input:
        mat = [[1, 4, 8, 10],
               [2, 4, 8, 10],
               [3, 4, 8, 10],
               [4, 4, 8, 10]]
    Output:
        The peak element is at indices [0, 3]

*/

public class Find_Peak_Element_In_A_2D_Array {

    private static int[] findPeakGrid(int[][] matrix) {

        int m = matrix.length; // rows
        int n = matrix[0].length; //col

        int left = 0;
        int right = n - 1;

        while (left <= right) {

            int midCol = left + (right - left) / 2;
            int maxRow = maxi(matrix, midCol);
            int curr = matrix[maxRow][midCol];

            int leftVal = midCol > 0 ? matrix[maxRow][midCol - 1] : - 1; // left value to compare
            int rightVal = midCol < n - 1 ? matrix[maxRow][midCol + 1] : - 1; // right value to compare

            // 2) Checking the condition
            if (curr > leftVal && curr > rightVal) {
                return new int[]{maxRow, midCol}; // found element
            } else if (curr > leftVal) {
                left = midCol + 1; // go right
            } else {
                right = midCol - 1; // go left
            }

        }

        return new int[]{- 1, - 1};

    }

    private static int maxi(int[][] mat, int col) {
        int maxRow = 0;

        for (int i = 1; i < mat.length; i++) {
            if (mat[i][col] > mat[maxRow][col]) {
                maxRow = i;
            }
        }

        return maxRow; // we just need index

    }

    public static void main(String[] args) {

        int[][] mat = {{1, 4, 8, 10}, {2, 4, 8, 10}, {3, 4, 8, 10}, {4, 4, 8, 10}};

        int[] ans = findPeakGrid(mat);
        System.out.println("The peak element is at the indices with row: " + ans[0] + " and column: " + ans[1]);


    }

}
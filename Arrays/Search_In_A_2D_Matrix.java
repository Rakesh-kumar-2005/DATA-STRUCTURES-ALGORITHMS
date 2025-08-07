package Arrays;

/*

Description:
    -> This program searches for a target value in a 2D matrix.
    -> It assumes the matrix is sorted in a way that each row is sorted left to right,
       and the first integer of each row is greater than the last integer of the previous row.
    -> It uses binary search over the matrix by treating it as a 1D array.

Problem Statement:
    -> Given an `m x n` matrix and a target value,
       return true if the target exists in the matrix, otherwise return false.

Approach:
    > Flattened Binary Search:
        -> Treat the 2D matrix as a virtual 1D sorted array.
        -> Use binary search logic with a mapping:
            - Index `i` in the 1D array corresponds to `(i / m, i % m)` in the 2D matrix.
        -> Check the middle value against the target and adjust search bounds accordingly.

Algorithm Steps:
    -> Initialization:
        1. Get number of rows `n` and columns `m` from the matrix.
        2. Set `low = 0` and `high = (n * m) - 1`.

    -> Binary Search Loop:
        1. While `low <= high`:
            a. Compute mid-point: `mid = (low + high) / 2`.
            b. Convert `mid` to 2D index: `r = mid / m`, `c = mid % m`.
            c. If matrix[r][c] == target → return true.
            d. If matrix[r][c] < target → search in the right half (`low = mid + 1`).
            e. Else → search in the left half (`high = mid - 1`).

    -> Finalizing:
        1. If loop exits without match, return false.

Key Characteristics:
    -> Eliminates the need for nested loops.
    -> Efficient for large sorted matrices.
    -> Converts logical 2D access into a single binary search pass.

Time and Space Complexity:
    -> Time Complexity: O(log(m * n)), where m is number of rows and n is number of columns.
    -> Space Complexity: O(1), no extra space used.

Demonstration:
    -> Input: matrix = [[1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 60]], target = 3
    -> Output: true (target found at position [0][1])

*/

public class Search_In_A_2D_Matrix {

    private static boolean searchMatrix(int[][] arr, int target) {

        int n = arr.length;
        int m = arr[0].length;

        int low = 0;
        int high = m * n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int r = mid / m;
            int c = mid % m;

            if (arr[r][c] == target) {
                return true;
            } else if (arr[r][c] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int[][] arr = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;

        boolean ans = searchMatrix(arr, target);

        if (ans) {
            System.out.println("The target " + target + " is present in the matrix...");
        } else {
            System.out.println("The target is not present in the matrix...");
        }

    }


}

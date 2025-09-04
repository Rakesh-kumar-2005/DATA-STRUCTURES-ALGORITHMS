package Arrays;

/*

Description:
    -> This program modifies a given matrix such that if an element is 0, 
       its entire row and column are set to 0.
    -> The solution uses two auxiliary arrays (rows[] and cols[]) 
       to keep track of which rows and columns need to be zeroed.

Problem Statement:
    Given an m × n matrix, if an element is 0, set its entire row and 
    column to 0 in-place.

Example:
    Input:
        1 1 1
        1 0 1
        1 1 1
    Output:
        1 0 1
        0 0 0
        1 0 1

Approach:
    1. Traverse the matrix to identify rows and columns containing 0.
    2. Mark those rows and columns in boolean arrays.
    3. Traverse the matrix again and set elements to 0 if their 
       corresponding row or column is marked.

Key Methods:
    -> setZeroes()    : modifies the matrix based on zero positions.
    -> displayMatrix(): prints the matrix.

Time and Space Complexity:
    -> Time Complexity: O(m × n) — two passes over the matrix.
    -> Space Complexity: O(m + n) — extra space for row and column markers.

Conclusion:
    The program efficiently updates the matrix to meet the condition 
    while using additional space proportional to the number of rows and columns.

*/

public class Set_Matrix_Zeroes_I {

    private static void setZeroes(int[][] matrix) {

        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = cols[j] = true;
                }
            }
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void displayMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

        System.out.println("The original matrix is : ");
        displayMatrix(matrix);

        setZeroes(matrix);
        System.out.println("The modified matrix is : ");
        displayMatrix(matrix);

    }

}

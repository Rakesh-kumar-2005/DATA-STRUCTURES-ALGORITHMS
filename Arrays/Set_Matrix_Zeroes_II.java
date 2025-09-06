package Arrays;

/*

Description:
    -> This program modifies a given matrix such that if an element is 0,
       its entire row and column are set to 0.
    -> Unlike the previous version (Set_Matrix_Zeroes_I), this solution 
       optimizes space usage by using the first row and first column of 
       the matrix itself as markers instead of separate arrays.

Problem Statement:
    Given an m × n matrix, if an element is 0, set its entire row and 
    column to 0 in-place, using constant space.

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
    1. Use the first row and first column as marker arrays to track 
       which rows and columns should be zeroed.
    2. A boolean `flag` is used to check whether the first column 
       itself needs to be set to zero.
    3. Traverse the matrix:
       - If matrix[row][col] == 0, mark matrix[row][0] = 0 and matrix[0][col] = 0.
    4. Iterate the matrix in reverse order to apply the zeroing, ensuring 
       that marker information is not overwritten too early.
    5. If `flag` is true, set the first column to 0.

Key Methods:
    -> setZeroes()    : modifies the matrix in-place using constant space.
    -> displayMatrix(): prints the matrix.

Time and Space Complexity:
    -> Time Complexity: O(m × n) — two full traversals of the matrix.
    -> Space Complexity: O(1) — no extra space used except the boolean flag.

Conclusion:
    This solution is more efficient in terms of space compared to 
    Set_Matrix_Zeroes_I, while still achieving the same result.

*/

public class Set_Matrix_Zeroes_II {
    
    private static void setZeroes(int[][] matrix) {
        boolean flag = false;

        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                flag = true;
            }

            for (int col = 1; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
            
        }

        for (int row = matrix.length - 1; row >= 0; row--) {

            for (int col = matrix[0].length - 1; col >= 1; col--) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }

            if (flag) {
                matrix[row][0] = 0;
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

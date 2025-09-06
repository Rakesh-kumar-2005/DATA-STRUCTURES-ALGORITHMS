package Arrays;

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


package Arrays;

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
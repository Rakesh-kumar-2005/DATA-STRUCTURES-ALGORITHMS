package Binary_Search;

public class Find_A_Peak_Element_In_A_2D_Array {

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
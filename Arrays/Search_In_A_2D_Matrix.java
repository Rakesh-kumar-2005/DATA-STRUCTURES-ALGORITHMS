package Arrays;

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
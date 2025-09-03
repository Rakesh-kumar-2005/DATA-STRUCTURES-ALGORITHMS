package BackTracking;

public class Sudoku_Solver {

    static int check = 0;

    private static boolean isValid(char[][] board, int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        int baseRow = row / 3 * 3;
        int baseCol = col / 3 * 3;

        for (int i = baseRow; i < baseRow + 3; i++) {
            for (int j = baseCol; j < baseCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;

    }

    private static void solve(char[][] board, int row, int col) {
        if (row == 9) {
            check = 1;
            return;
        } else if (board[row][col] != '.') {
            if (col != 8) {
                solve(board, row, col + 1);
            } else {
                solve(board, row + 1, 0);
            }
        } else {
            for (char ch = '1'; ch <= '9'; ch++) {
                if (isValid(board, row, col, ch)) {
                    board[row][col] = ch;

                    if (col != 8) {
                        solve(board, row, col + 1);
                    } else {
                        solve(board, row + 1, 0);
                    }

                    if (check == 1) {
                        return;
                    }

                    board[row][col] = '.';
                }
            }
        }
    }

    private static void solveSudoku(char[][] board) {
        solve(board, 0, 0);
        check = 0;
    }

    public static void main(String[] args) {

        char[][] sudoku = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println("The original Sudoku is : ");
        displaySudoku(sudoku);

        solveSudoku(sudoku);

        System.out.println("The solved Sudoku is : ");
        displaySudoku(sudoku);

    }

    private static void displaySudoku(char[][] sudoku) {

        for (char[] chars : sudoku) {
            for (char aChar : chars) {
                System.out.print(aChar + "  ");
            }
            System.out.println();
        }

    }

}
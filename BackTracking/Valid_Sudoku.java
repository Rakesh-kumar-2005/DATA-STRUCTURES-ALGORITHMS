package BackTracking;

public class Valid_Sudoku {

    private static boolean isValid(char[][] board, int row, int col, char currNum) {

        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == currNum) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == currNum) {
                return false;
            }
        }

        int sRow = row / 3 * 3;
        int sCol = col / 3 * 3;

        for (int i = sRow; i < sRow + 3; i++) {
            for (int j = sCol; j < sCol + 3; j++) {
                if (board[i][j] == currNum) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char currChar = board[i][j];
                if (board[i][j] == '.') {
                    continue;
                }
                board[i][j] = '.';
                if (! isValid(board, i, j, currChar)) {
                    return false;
                }
                board[i][j] = currChar;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        boolean ans = isValidSudoku(board);

        if (ans) {
            System.out.println("The sudoku is valid");
        } else {
            System.out.println("The sudoku is not valid");
        }

    }

}
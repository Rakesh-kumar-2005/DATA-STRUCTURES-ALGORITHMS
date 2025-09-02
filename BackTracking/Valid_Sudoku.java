package BackTracking;

/*

Description:
    -> This program checks whether a given Sudoku board is valid.
    -> A Sudoku board is valid if:
        1. Each row contains digits 1–9 without repetition.
        2. Each column contains digits 1–9 without repetition.
        3. Each 3×3 sub-grid contains digits 1–9 without repetition.
    -> The program does not solve the Sudoku; it only validates the current state of the board.

Problem Statement:
    Given a partially filled 9×9 Sudoku board, determine if it is valid.
    Empty cells are represented by '.'.

Example:
    Input Board (partially filled):
        [ ['5','3','.','.','7','.','.','.','.'],
          ['6','.','.','1','9','5','.','.','.'],
          ['.','9','8','.','.','.','.','6','.'],
          ['8','.','.','.','6','.','.','.','3'],
          ['4','.','.','8','.','3','.','.','1'],
          ['7','.','.','.','2','.','.','.','6'],
          ['.','6','.','.','.','.','2','8','.'],
          ['.','.','.','4','1','9','.','.','5'],
          ['.','.','.','.','8','.','.','7','9'] ]

    Output:
        "The sudoku is valid"

Approach:
    1. Traverse every cell of the board.
    2. For each non-empty cell:
        -> Temporarily remove the value.
        -> Check if the digit violates row, column, or 3×3 sub-grid rules.
        -> Restore the digit.
    3. If all cells satisfy the rules, the Sudoku is valid.

Key Methods:
    -> isValid()     : checks whether a number can be placed in a given row, column, and sub-grid.
    -> isValidSudoku(): iterates through the entire board and validates each cell.

Key Variables:
    -> board    : 2D char array representing the Sudoku board.
    -> row, col : current cell position.
    -> currChar : current digit to validate.

Time and Space Complexity:
    -> Time Complexity: O(9² × 9) = O(729) → constant time since Sudoku size is fixed.
    -> Space Complexity: O(1), no extra data structures used.

Conclusion:
    The program efficiently verifies the correctness of a Sudoku board 
    using backtracking-style checks without solving it.

*/

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

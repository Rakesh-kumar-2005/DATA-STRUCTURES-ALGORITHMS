package BackTracking;

/*

Description:
    -> This program solves a given Sudoku puzzle using **backtracking**.
    -> It fills the empty cells (denoted by '.') with digits 1–9 
       while ensuring the Sudoku rules are not violated:
          1. Each row must contain digits 1–9 without repetition.
          2. Each column must contain digits 1–9 without repetition.
          3. Each 3×3 sub-grid must contain digits 1–9 without repetition.
    -> If a digit placement leads to a dead end, the algorithm backtracks
       and tries a different number until the puzzle is solved.

Problem Statement:
    Given a partially filled 9×9 Sudoku board, complete it 
    by filling the empty cells according to Sudoku rules.

Example:
    Input (partially filled Sudoku):
        5 3 .  . 7 .  . . .
        6 . .  1 9 5  . . .
        . 9 8  . . .  . 6 .
        8 . .  . 6 .  . . 3
        4 . .  8 . 3  . . 1
        7 . .  . 2 .  . . 6
        . 6 .  . . .  2 8 .
        . . .  4 1 9  . . 5
        . . .  . 8 .  . 7 9

    Output (solved Sudoku):
        5 3 4  6 7 8  9 1 2
        6 7 2  1 9 5  3 4 8
        1 9 8  3 4 2  5 6 7
        8 5 9  7 6 1  4 2 3
        4 2 6  8 5 3  7 9 1
        7 1 3  9 2 4  8 5 6
        9 6 1  5 3 7  2 8 4
        2 8 7  4 1 9  6 3 5
        3 4 5  2 8 6  1 7 9

Approach:
    1. Use recursion and backtracking to fill empty cells.
    2. For each empty cell:
        -> Try digits from '1' to '9'.
        -> Validate placement using isValid() (row, column, sub-grid checks).
        -> If valid, place the digit and move to the next cell.
        -> If invalid, backtrack (reset to '.').
    3. Continue until the entire board is filled.

Key Methods:
    -> isValid()     : checks if a digit can be placed safely at a position.
    -> solve()       : recursive backtracking solver.
    -> solveSudoku() : initiates the solving process.
    -> displaySudoku(): prints the Sudoku board.

Key Variables:
    -> sudoku[][] : 2D char array representing the Sudoku board.
    -> row, col   : current cell indices.
    -> check      : flag to indicate when a solution has been found.

Time and Space Complexity:
    -> Time Complexity: O(9^(N)) in the worst case (N = number of empty cells),
       since each empty cell has up to 9 possibilities.
    -> Space Complexity: O(N) recursion depth (backtracking stack).

Conclusion:
    The program successfully solves a 9×9 Sudoku puzzle using 
    backtracking and constraint checking.

*/

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

    private static void displaySudoku(char[][] sudoku) {

        for (char[] chars : sudoku) {
            for (char aChar : chars) {
                System.out.print(aChar + "  ");
            }
            System.out.println();
        }

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

}

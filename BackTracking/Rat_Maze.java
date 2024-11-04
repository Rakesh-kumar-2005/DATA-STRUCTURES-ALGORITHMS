package BackTracking;

/*
        Description :-
            > it can only move in 2 direction - right and downwards.
            > for the following code we have the starting point at 0,0 cell
                and the destination is at the 5,5 cell.

        Approach :-
            -> Recursive Function: The printWays function uses recursion to explore all possible paths. It takes the current
                    row (sr),current column (sc), end row (er), end column (ec), and the path string (s) as parameters.
            -> Base Cases: If the current position (sr, sc) exceeds the boundaries (er, ec), it returns (ends this path).
                    > If the current position is the target position (sr == er && sc == ec), it prints the path string (s) and returns.
            -> Recursive Calls:
                > Move Down: Call printWays with sr + 1 (moving down) and append "D" to the path string.
                > Move Right: Call printWays with sc + 1 (moving right) and append "R" to the path string.

*/


public class Rat_Maze {
    private static void printWays(int sr, int sc, int er, int ec, String s) {
        if (sr > er || sc > ec) return;
        if (sr == er && sc == ec) {
            System.out.println(s);
            return;
        }
        printWays(sr + 1, sc, er, ec, s + "D");
        printWays(sr, sc + 1, er, ec, s + "R");
    }
    public static void main(String[] args) {
        int rows = 3;
        int cols = 3;
        printWays(0,0,rows-1,cols-1,"");
    }
}

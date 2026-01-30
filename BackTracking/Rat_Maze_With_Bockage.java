package BackTracking;

/*
        Description ;-
          > Here's the question is also to find the ways that a rat can reach its destination
            but there's a twist it can move in every direction, so we have to track its path so
            that it wouldn't return to its previous path. If it does that then the recursion won't
            stop. That's why we hve to maintain a boolean type array to mark its path as true.

        Approach :-
            -> Recursive Function with Visited Check:
                > The printWays function uses recursion to explore all possible paths.
                > It takes the current row (sr), current column (sc), end row (er),end column (ec), path string (s),
                    and a boolean matrix (isVisited) to track visited cells.
            -> Base Cases:
                > Out of Bounds: If the current position (sr, sc) is outside the grid boundaries, return.
                > Already Visited: If the current cell has already been visited, return.
                > Reached the End: If the current position is the end position (sr == er && sc == ec), print the path string (s) and return.
            -> Recursive Exploration:
                > Mark as Visited: Mark the current cell as visited (isVisited[sr][sc] = true).
                > Move in All Directions:
                     -- Down: Call printWays with sr + 1 (moving down) and append "D" to the path string.
                     -- Right: Call printWays with sc + 1 (moving right) and append "R" to the path string.
                     -- Left: Call printWays with sc - 1 (moving left) and append "L" to the path string.
                     -- Up: Call printWays with sr - 1 (moving up) and append "U" to the path string.
                > Unmark as Visited: Before returning, unmark the current cell as visited (isVisited[sr][sc] = false) to allow
                    other paths to explore this cell.

 */


public class Rat_Maze_With_Bockage {
    
    public static void printWays(int sr, int sc, int er, int ec, String s, boolean[][] isVisited){
        
        if(sr < 0 || sc < 0) return;
        if(sr > er || sc > ec) return;
        if(isVisited[sr][sc]) return;
        if(sr == er && sc == ec){
            System.out.println(s);
            return;
        }
        
        isVisited[sr][sc] = true;
        printWays(sr+1, sc, er, ec, s+'D', isVisited);
        printWays(sr, sc+1, er, ec, s+'R', isVisited);
        printWays(sr, sc-1, er, ec, s+'L', isVisited);
        printWays(sr-1, sc, er, ec, s+'U', isVisited);
        isVisited[sr][sc] = false;
    }
    
    public static void main(String[] args) {
            
        int rows = 3;
        int cols = 3;
        
        boolean[][] isVisited = new boolean[rows][cols];
        printWays(0,0,rows-1,cols-1,"",isVisited);
        
    }
    
}

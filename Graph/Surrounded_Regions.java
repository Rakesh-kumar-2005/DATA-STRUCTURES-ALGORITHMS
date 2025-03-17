package Graph;

/*
Description:
   Following program demonstrates the solution to the "Surrounded Regions" problem using Breadth-First Search (BFS)...

Problem Statement:
   -> Given an m×n matrix board containing 'X' and 'O', we need to flip all 'O's into 'X's in the board...
   -> However, any 'O' that is not completely surrounded by 'X's (i.e., any 'O' on the border of the board or any 'O' connected to an 'O' on the border) should not be flipped...
   -> In other words, only the 'O's that are completely surrounded by 'X's on all four sides should be captured and flipped to 'X's...

Approach:
   > Border-based BFS:
      i. Instead of directly identifying surrounded regions, we identify all 'O's that are NOT surrounded (those connected to the border)...
      ii. Start by adding all 'O's on the border to a queue and mark them as visited...
      iii. Perform BFS from these border 'O's to find all connected 'O's...
      iv. Finally, any 'O' that wasn't visited during the BFS is completely surrounded and should be flipped to 'X'...

> BFS Implementation:
   -> First, identify all 'O's on the border and add them to the queue...
   -> For each cell in the queue, explore its four adjacent cells (up, right, down, left)...
   -> If an adjacent cell contains an 'O' and hasn't been visited, add it to the queue and mark it as visited...
   -> Continue until the queue is empty...

> Final Transformation:
   -> After BFS, all 'O's that are not completely surrounded have been marked as visited...
   -> Iterate through the entire board and flip any unvisited 'O' to 'X'...

> Data Structures:
   -> Use a Pair class to represent row and column indices...
   -> Use a queue for BFS traversal...
   -> Use a boolean array to track visited cells...
   -> Use direction arrays (delrow, delcol) to explore adjacent cells...

> Time and Space Complexity:
   -> Time Complexity: O(n×m) where n is the number of rows and m is the number of columns in the board...
   -> Space Complexity: O(n×m) for the visited array and the queue in the worst case...
*/

import java.util.LinkedList;
import java.util.Queue;

public class Surrounded_Regions {

	static class Pair {
		int row;
		int col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	private static void solve(char[][] board) {
		Queue<Pair> q = new LinkedList<>();
		int n = board.length;
		int m = board[0].length;
		boolean[][] visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					if (board[i][j] == 'O') {
						q.add(new Pair(i, j));
						visited[i][j] = true;
					}
				}
			}
		}

		int[] delrow = {- 1, 0, 1, 0};
		int[] delcol = {0, 1, 0, - 1};

		while (! q.isEmpty()) {
			Pair pair = q.remove();
			int currRow = pair.row;
			int currCol = pair.col;

			for (int i = 0; i < 4; i++) {
				int newRow = currRow + delrow[i];
				int newCol = currCol + delcol[i];

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && board[newRow][newCol] == 'O' && visited[newRow][newCol] == false) {
					q.add(new Pair(newRow, newCol));
					visited[newRow][newCol] = true;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (! visited[i][j] && board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}


	public static void main(String[] args) {
		char[][] board = {{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'}};

		solve(board);

		System.out.println("After converting the regions, the board is: ");

		for (char[] row : board) {
			for (char c : row) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}

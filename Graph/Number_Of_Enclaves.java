package Graph;

/*
Description:
   Following program demonstrates the solution to the "Number of Enclaves" problem using Breadth-First Search (BFS)...

Problem Statement:
   -> Given an m×n binary matrix grid where 0 represents a sea cell and 1 represents a land cell...
   -> An enclave is a group of land cells (cells with value 1) that are completely surrounded by sea cells (cells with value 0) and cannot reach the border of the grid...
   -> In other words, any land cell that can reach the border of the grid by crossing land cells (moving in four directions: up, right, down, left) is NOT an enclave...
   -> The task is to count the number of land cells that are enclaves (i.e., land cells that cannot reach the border of the grid)...
   -> Additionally, the program marks all enclave cells with the value 2 in the grid...

Approach:
   > Border-based BFS:
      i. Similar to the "Surrounded Regions" problem, we identify land cells that can reach the border...
      ii. Start by adding all land cells (1's) on the border to a queue and mark them as visited...
      iii. Perform BFS from these border land cells to find all connected land cells...
      iv. Any land cell that wasn't visited during the BFS is part of an enclave...

> BFS Implementation:
   -> First, identify all land cells (1's) on the border and add them to the queue...
   -> For each cell in the queue, explore its four adjacent cells (up, right, down, left)...
   -> If an adjacent cell contains a 1 and hasn't been visited, add it to the queue and mark it as visited...
   -> Continue until the queue is empty...

> Counting Enclaves:
   -> After BFS, all land cells that can reach the border have been marked as visited...
   -> Iterate through the entire grid and count any unvisited land cell (1) as part of an enclave...
   -> Also, mark these enclave cells with the value 2 in the grid for visualization...

> Data Structures:
   -> Use a Pair class to represent row and column indices...
   -> Use a queue for BFS traversal...
   -> Use a boolean array to track visited cells...
   -> Use direction arrays (delrow, delcol) to explore adjacent cells...

> Time and Space Complexity:
   -> Time Complexity: O(n×m) where n is the number of rows and m is the number of columns in the grid...
   -> Space Complexity: O(n×m) for the visited array and the queue in the worst case...
*/

import java.util.LinkedList;
import java.util.Queue;

public class Number_Of_Enclaves {
	static class Pair {
		int row;
		int col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	private static int numEnclaves(int[][] grid) {
		Queue<Pair> q = new LinkedList<>();
		int n = grid.length;
		int m = grid[0].length;
		boolean[][] visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					if (grid[i][j] == 1) {
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

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1
					&& ! visited[newRow][newCol]) {
					q.add(new Pair(newRow, newCol));
					visited[newRow][newCol] = true;
				}
			}
		}

		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (! visited[i][j] && grid[i][j] == 1) {
					grid[i][j] = 2;
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] grid = {{0, 0, 0, 0},
			{1, 0, 1, 0},
			{0, 1, 1, 0},
			{0, 0, 0, 0}};

		System.out.println("The number of enclaves in the given grid is = " + numEnclaves(grid));
		System.out.println("The updated grid is where the enclave region is marked with 2 :");

		for (int[] row : grid) {
			for (int c : row) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}

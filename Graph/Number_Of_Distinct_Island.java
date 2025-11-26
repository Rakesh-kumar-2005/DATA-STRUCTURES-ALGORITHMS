package Graph;

/*

Description:
   Following program demonstrates the solution to the "Number of Distinct Islands" problem using Depth-First Search (DFS)...

Problem Statement:
   -> Given an n×m binary matrix grid where 1 represents land and 0 represents water...
   -> An island is a group of 1's connected horizontally or vertically (4-directionally)...
   -> Two islands are considered distinct if their shapes are different (not counting rotations and reflections)...
   -> The task is to count the number of distinct islands in the grid...

Approach:
   > Shape-Based Island Identification:
      i. Use DFS to explore each island and encode its shape...
      ii. Store each island's shape representation in a HashSet to count unique shapes...
      iii. Use relative coordinates from the starting point of each island to represent its shape...

> DFS Implementation:
   -> For each unvisited land cell (1), start a DFS traversal to explore the entire island...
   -> During DFS, record the relative position of each cell to the starting point of the island...
   -> This creates a unique "signature" for the shape of each island...
   -> Add this signature to a HashSet to eliminate duplicates...

> Shape Encoding:
   -> Use the starting cell of each island as a base reference point (baseRow, baseCol)...
   -> For each cell in the island, store its position relative to this base point as "row_diff,col_diff"...
   -> This ensures that islands with the same shape but different positions will have identical encodings...

> Direction Exploration:
   -> Use direction arrays (rows[] and cols[]) to explore the four adjacent cells (up, right, down, left)...
   -> For each valid adjacent land cell that hasn't been visited, continue the DFS traversal...

> Counting Distinct Islands:
   -> The size of the HashSet containing all unique island shapes gives the count of distinct islands...

> Data Structures:
   -> Use a boolean array to track visited cells...
   -> Use ArrayList<String> to store the shape encoding of each island...
   -> Use HashSet<ArrayList<String>> to store unique island shapes...
   -> Use direction arrays (rows, cols) to explore adjacent cells...

> Time and Space Complexity:
   -> Time Complexity: O(n×m) where n is the number of rows and m is the number of columns in the grid...
   -> Space Complexity: O(n×m) for the visited array and in the worst case for storing all island shapes...

*/

import java.util.ArrayList;
import java.util.HashSet;

public class Number_Of_Distinct_Island {

    private static void dfs(int currRow, int currCol, boolean[][] visited, int[][] grid, ArrayList<String> currList, int baseRow, int baseCol) {
	int n = grid.length;
	int m = grid[0].length;

	visited[currRow][currCol] = true;
	currList.add((currRow - baseRow) + "," + (currCol - baseCol));

	int[] rows = {- 1, 0, 1, 0};
	int[] cols = {0, 1, 0, - 1};

	for (int i = 0; i < 4; i++) {

		int newRow = currRow + rows[i];
        int newCol = currCol + cols[i];

	    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && ! visited[newRow][newCol] && grid[newRow][newCol] == 1) {
	        dfs(newRow, newCol, visited, grid, currList, baseRow, baseCol);
	    }
		
	}
	
   }

   private static int countDistinctIslands(int[][] grid) {
        int n = grid.length;
		int m = grid[0].length;
	
		boolean[][] visited = new boolean[n][m];
		HashSet<ArrayList<String>> st = new HashSet<>();
	
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < m; j++) {
			
				if (grid[i][j] == 1 && ! visited[i][j]) {
		            ArrayList<String> currList = new ArrayList<>();
			    	dfs(i, j, visited, grid, currList, i, j);
			    	st.add(currList);
				}
				
		    }
		}
	   
		return st.size();
    
   }

    public static void main(String[] args) {
		int[][] grid = {{1, 1, 0, 0, 0},
		                {1, 0, 0, 1, 1},
						{0, 0, 0, 0, 0},
						{1, 1, 0, 1, 1},
						{0, 0, 0, 1, 0}};
		System.out.println("The number of distinct islands in the given grid is = " + countDistinctIslands(grid));
     }
	
}

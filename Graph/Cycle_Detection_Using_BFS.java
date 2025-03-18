package Graph;

/*

Description:
   Following program demonstrates the solution to the "Number of Distinct Islands" problem using Depth-First Search (DFS).

Problem Statement:
   -> Given an n×m binary matrix grid where 1 represents land and 0 represents water.
   -> An island is a group of 1's connected horizontally or vertically (4-directionally).
   -> Two islands are considered distinct if their shapes are different (not counting rotations and reflections).
   -> The task is to count the number of distinct islands in the grid.

Approach:
   > Shape-Based Island Identification:
      i. Use DFS to explore each island and encode its shape.
      ii. Store each island's shape representation in a HashSet to count unique shapes.
      iii. Use relative coordinates from the starting point of each island to represent its shape.

> DFS Implementation:
   -> For each unvisited land cell (1), start a DFS traversal to explore the entire island.
   -> During DFS, record the relative position of each cell to the starting point of the island.
   -> This creates a unique "signature" for the shape of each island.
   -> Add this signature to a HashSet to eliminate duplicates.

> Shape Encoding:
   -> Use the starting cell of each island as a base reference point (baseRow, baseCol).
   -> For each cell in the island, store its position relative to this base point as "row_diff,col_diff".
   -> This ensures that islands with the same shape but different positions will have identical encodings.

> Direction Exploration:
   -> Use direction arrays (rows[] and cols[]) to explore the four adjacent cells (up, right, down, left).
   -> For each valid adjacent land cell that hasn't been visited, continue the DFS traversal.

> Counting Distinct Islands:
   -> The size of the HashSet containing all unique island shapes gives the count of distinct islands.

> Data Structures:
   -> Use a boolean array to track visited cells.
   -> Use ArrayList<String> to store the shape encoding of each island.
   -> Use HashSet<ArrayList<String>> to store unique island shapes.
   -> Use direction arrays (rows, cols) to explore adjacent cells.

> Time and Space Complexity:
   -> Time Complexity: O(n×m) where n is the number of rows and m is the number of columns in the grid.
   -> Space Complexity: O(n×m) for the visited array and in the worst case for storing all island shapes.

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Cycle_Detection_Using_BFS {

    static class Pair {
	int parent;
	int node;

	public Pair(int parent, int node) {
	    this.parent = parent;
	    this.node = node;
	}
    }
	
    private static boolean cycle(int source, int v, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {

	Queue<Pair> q = new LinkedList<>();
	vis[source] = true;
	q.add(new Pair(- 1, source));

	while (! q.isEmpty()) {
	    Pair curr = q.remove();
	    int currParent = curr.parent;
	    int currNode = curr.node;

	    for (int adjacentNode : adj.get(currNode)) {
		if (! vis[adjacentNode]) {
		     vis[adjacentNode] = true;
		     q.add(new Pair(currNode, adjacentNode));
		} else if (currParent != adjacentNode) return true;
	    }
	}
	return false;
    }

    public static ArrayList<ArrayList<Integer>> createAdjacencyList(int vertices, int[][] edges) {

	 ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

	 for (int i = 0; i < vertices; i++) {
		 adj.add(new ArrayList<>());
	 }

	 for (int[] edge : edges) {
	     int u = edge[0];
	     int v = edge[1];
		 
	     adj.get(u).add(v);
	     adj.get(v).add(u);
         }
	 return adj;
     }

     public static void main(String[] args) {
	 System.out.println("Following is a graph with 5 vertices and 8 edges : ");
	 int vertices = 5;
	 int[][] edges = {{0, 1}, {0, 4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {3, 4}};

	 ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(vertices, edges);

	 for (int i = 0; i < adjacencyList.size(); i++) {
	    System.out.print(i + " -> ");
		for (int j = 0; j < adjacencyList.get(i).size(); j++) {
		     System.out.print(adjacencyList.get(i).get(j) + " ");
		}
	    System.out.println();
	 }
	     
	System.out.println("Is there a cycle in the above graph? " + isCycle(adjacencyList));
	     
    }
	
}

package Graph;

/*

Description:
  Following program demonstrates the solution to the "Cycle Detection in Undirected Graph" problem using Depth-First Search (DFS).

Problem Statement:
  -> Given an undirected graph represented as an adjacency list.
  -> An undirected graph contains a cycle if there is a path from a vertex to itself without using any edge more than once.
  -> The task is to determine if the given undirected graph contains a cycle.

Approach:
  > DFS-Based Cycle Detection:
     i. Use DFS to traverse the graph starting from each unvisited vertex.
     ii. For each visited vertex, check if we can reach an already visited vertex through a path that doesn't involve backtracking.
     iii. If such a path exists, then the graph contains a cycle.

> DFS Implementation:
  -> For each unvisited vertex, perform a DFS traversal.
  -> Keep track of the parent vertex for each vertex during traversal.
  -> If we encounter an already visited vertex that is not the parent of the current vertex, it means we've found a cycle.
  -> The parent check is crucial to avoid false cycle detection due to the bidirectional nature of undirected edges.

> Parent Vertex Tracking:
  -> For each vertex, pass its parent information to the recursive DFS call.
  -> The parent is the vertex from which we reached the current vertex.
  -> For the starting vertex, the parent is set to -1 (non-existent).

> Graph Representation:
  -> The graph is represented as an adjacency list.
  -> Each vertex has a list of its adjacent vertices.
  -> For an undirected edge (u, v), both u's list contains v and v's list contains u.

> Visited Array:
  -> Use a boolean array to keep track of visited vertices.
  -> This helps avoid processing the same vertex multiple times.
  -> Also helps in detecting cycles by identifying already visited vertices.

> Time and Space Complexity:
  -> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges.
  -> Space Complexity: O(V) for the recursion stack and visited array.

*/

import java.util.ArrayList;

public class Cycle_Detection_Using_DFS {

	 static class Pair {
		  int parent;
		  int node;

		  public Pair(int parent, int node) {
				this.parent = parent;
				this.node = node;
		  }
	 }

	 private static boolean cycle(int source, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
		  vis[source] = true;

		  for (int adjacentNode : adj.get(source)) {
				if (! vis[adjacentNode]) {
					 if (cycle(adjacentNode, source, adj, vis)) {
						  return true;
					 }
				} else if (adjacentNode != parent) {
					 return true;
				}
		  }
		  return false;
	 }

	 private static boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
		  int v = adj.size();
		  boolean[] vis = new boolean[v];

		  for (int i = 0; i < v; i++) {
				if (! vis[i]) {
					 if (cycle(i, - 1, adj, vis)) {
						  return true;
					 }
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
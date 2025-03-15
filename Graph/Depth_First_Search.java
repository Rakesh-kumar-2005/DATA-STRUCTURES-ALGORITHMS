package Graph;

/*
Description:
	Following program demonstrates the Depth First Search (DFS) in a graph...

	Approach 1 :- (Iterator)
	> Initialization:
	i. A stack (or recursion) is used to manage the traversal order...
	ii. The source vertex is marked as visited...
	iii. DFS begins from the source vertex...

	> Traversal Process:
	-> For the current vertex:
	i. Process the current vertex (in this case, print it)...
	ii. Find all adjacent vertices of the current vertex...
	iii. For each adjacent vertex that hasn't been visited:
	- Mark it as visited...
	- Recursively call DFS on this vertex (or push to stack in iterative approach)...
	iv. Backtrack when all adjacent vertices have been explored...

	> Key Differences from BFS:
	-> DFS explores as far as possible along a branch before backtracking...
	-> DFS uses stack (explicit or implicit through recursion) instead of queue...
	-> DFS may not find the shortest path between vertices...

	> Time and Space Complexity:
	-> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges...
	-> Space Complexity: O(V) for the stack/recursion stack and visited array...
	-> In worst case (skewed graph), recursion depth can reach O(V)...

	Approach 2 :- (Recursive)
   > Initialization:
      i. A visited array/set is created to keep track of explored vertices...
      ii. The recursive DFS function is called with the source vertex...

> Recursive Function:
   -> For the current vertex (source):
      i. Mark the current vertex as visited...
      ii. Process the current vertex (in this case, print it)...
      iii. For each adjacent vertex of the current vertex:
         - If the adjacent vertex hasn't been visited:
            * Recursively call DFS on this adjacent vertex...
      iv. The function naturally backtracks when a vertex has no unvisited neighbors...

> Execution Flow:
   -> The recursion stack implicitly maintains the traversal path...
   -> Each recursive call explores one branch completely before moving to the next...
   -> When the function returns, it backtracks to explore other branches...

> Time and Space Complexity:
   -> Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges...
   -> Space Complexity: O(V) for the visited array and recursion stack...
   -> In worst case (long path or skewed graph), recursion depth can reach O(V)...
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Depth_First_Search {

	private static void addEdge(ArrayList<Edge>[] graph) {
		for (int i = 0; i < graph.length; i++) {
			graph[ i ] = new ArrayList<>();
		}
		graph[ 0 ].add(new Edge(0, 1));
		graph[ 0 ].add(new Edge(0, 3));
		graph[ 0 ].add(new Edge(0, 4));

		graph[ 1 ].add(new Edge(1, 0));
		graph[ 1 ].add(new Edge(1, 2));
		graph[ 1 ].add(new Edge(1, 7));

		graph[ 2 ].add(new Edge(2, 1));
		graph[ 2 ].add(new Edge(2, 6));
		graph[ 2 ].add(new Edge(2, 8));

		graph[ 3 ].add(new Edge(3, 0));
		graph[ 3 ].add(new Edge(3, 5));
		graph[ 3 ].add(new Edge(3, 7));

		graph[ 4 ].add(new Edge(4, 0));
		graph[ 4 ].add(new Edge(4, 5));
		graph[ 4 ].add(new Edge(4, 8));

		graph[ 5 ].add(new Edge(5, 3));
		graph[ 5 ].add(new Edge(5, 4));
		graph[ 5 ].add(new Edge(5, 6));

		graph[ 6 ].add(new Edge(6, 2));
		graph[ 6 ].add(new Edge(6, 5));
		graph[ 6 ].add(new Edge(6, 7));

		graph[ 7 ].add(new Edge(7, 1));
		graph[ 7 ].add(new Edge(7, 3));
		graph[ 7 ].add(new Edge(7, 6));

		graph[ 8 ].add(new Edge(8, 2));
		graph[ 8 ].add(new Edge(8, 4));
	}

	private static void DFS_Iterative(int source, ArrayList<Edge>[] graph, boolean[] visited) {
		visited[ source ] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(source);
		while (! stack.isEmpty()) {
			int curr = stack.pop();
			System.out.print(curr + " ");

			for (Edge edge : graph[ curr ]) {
				if (! visited[ edge.dest ]) {
					visited[ edge.dest ] = true;
					stack.push(edge.dest);
				}
			}
		}
	}

	private static void DFS_Recursive(int source, ArrayList<Edge>[] graph, boolean[] visited) {
		visited[ source ] = true;
		System.out.print(source + " ");

		for (Edge edge : graph[ source ]) {
			if (! visited[ edge.dest ]) {
				DFS_Recursive(edge.dest, graph, visited);
			}
		}
	}

	public static void main(String[] args) {
		int Vertices = 9;
		ArrayList<Edge>[] graph = new ArrayList[ Vertices ];
		addEdge(graph);

		boolean[] visited = new boolean[ Vertices ];
		System.out.println("Following is Depth First Traversal (starting from vertex 0) with recursion : ");
		DFS_Recursive(0, graph, visited);

		System.out.println();
		Arrays.fill(visited, false);

		System.out.println("Following is Depth First Traversal (starting from vertex 0) with iteration : ");
		DFS_Iterative(0, graph, visited);
	}

	static class Edge {
		int src;
		int dest;

		public Edge(int src, int dest) {
			this.src = src;
			this.dest = dest;
		}
	}
}

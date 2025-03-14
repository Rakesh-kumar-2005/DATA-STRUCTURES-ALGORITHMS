package Graph;

/*
        Description :-
            Following program demonstrates the Breadth First Search in a graph...

        Approach :-
        	> Initialization:
			i. A queue is created to manage the traversal order...
			ii. The source vertex is added to the queue...
			iii.The source vertex is marked as visited...

		> Traversal Process:
			-> While the queue is not empty:
				i. Dequeue a vertex (called curr)...
				ii. Process the current vertex (in this case, print it)...
				iii. Find all adjacent vertices of the current vertex...
				iv. For each adjacent vertex that hasn't been visited:
					- Add it to the queue...
					- Mark it as visited

		> Time and Space Complexity:
			-> Time Complexity : O(V + E) where V is the number of vertices and E is the number of edges...
			-> Space Complexity : O(V) for the queue and visited array...
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Breadth_First_Search {

	static class Edge {
		int src;
		int dest;

		public Edge(int src, int dest) {
			this.src = src;
			this.dest = dest;
		}
	}

	private static void addEdge(ArrayList<Edge>[] graph) {
		for (int i = 0; i < graph.length; i++) {
			graph[ i ] = new ArrayList<>();
		}
		graph[ 0 ].add(new Edge(0, 1));
		graph[ 0 ].add(new Edge(0, 2));

		graph[ 1 ].add(new Edge(1, 2));
		graph[ 1 ].add(new Edge(1, 3));

		graph[ 2 ].add(new Edge(2, 4));

		graph[ 3 ].add(new Edge(3, 4));

		graph[ 4 ].add(new Edge(4, 5));
		graph[ 4 ].add(new Edge(4, 6));

		graph[ 5 ].add(new Edge(5, 6));

		graph[ 6 ].add(new Edge(6, 7));
		graph[ 6 ].add(new Edge(6, 8));

		graph[ 7 ].add(new Edge(7, 8));
	}

	private static void BFS(int source, ArrayList<Edge>[] graph, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);
		visited[ source ] = true;

		while (! queue.isEmpty()) {
			int curr = queue.poll();
			System.out.print(curr + " ");
			for (Edge edge : graph[ curr ]) {
				if (! visited[ edge.dest ]) {
					queue.add(edge.dest);
					visited[ edge.dest ] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		int Vertices = 9;
		ArrayList<Edge>[] graph = new ArrayList[ Vertices ];
		addEdge(graph);

		boolean[] visited = new boolean[ Vertices ];

		System.out.println("Following is Breadth First Traversal (starting from vertex 0) : ");
		for (int i = 0; i < Vertices; i++) {
			if (! visited[ i ]) {
				BFS(i, graph, visited);
			}
		}
	}
}

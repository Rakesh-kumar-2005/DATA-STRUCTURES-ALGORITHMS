package Graph;

/*
        Description :-
            Following program demonstrates the implementation of a graph using Array of Arraylist of user-defined data-type..

        Approach :-
	    -> Edge Class:-
		i. Simple class representing a directed edge from a source vertex (src) to a destination vertex (dest)...
		ii. Each edge stores both its starting and ending points...

	    -> Graph Representation:-
		i. The graph is represented as an array of ArrayLists: ArrayList<Edge>[] graph...
		ii. Each index in this array represents a vertex...
		iii. The ArrayList at each index contains all edges originating from that vertex

	    -> Key Methods:-
		i. addEdge Method:-
		   - Initializes each vertex's adjacency list as an empty ArrayList...
		   - Adds specific edges to create a predefined graph structure...
		   - Each edge is added to the source vertex's adjacency list...

	        ii. printGraph Method:-
		   - Iterates through each vertex's adjacency list...
		   - Prints all edges in the format "src -> dest"...
		   - This provides a visual representation of the graph's structure...
	
 	> Time and Space Complexity:
		-> Time Complexity : O(V + E) where V is the number of vertices and E is the number of edges...
		-> Space Complexity : O(V) for the accessing all neighbour of vertices...
*/

import java.util.ArrayList;

public class Graph_Implementation_Using_Adjacency_LIst_II {

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

	private static void printGraph(ArrayList<Edge>[] graph) {
		for (ArrayList<Edge> list : graph) {
			for (Edge edge : list) {
				System.out.print(edge.src + "->" + edge.dest + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int Vertices = 9;
		ArrayList<Edge>[] graph = new ArrayList[ Vertices ];
		addEdge(graph);

		System.out.println("The  Adjacency list would be like : ");
		printGraph(graph);
	}
}

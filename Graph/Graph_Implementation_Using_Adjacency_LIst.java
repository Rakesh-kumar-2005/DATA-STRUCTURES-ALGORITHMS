package Graph;


/*
        Description :-
		-> How the Graph is Represented :-
			An adjacency list representation stores a graph as a collection of lists, where each list
			corresponds to a vertex and contains all the vertices adjacent to it. In this implementation:
				i)The graph is represented as an ArrayList<ArrayList<Integer>> where the outer ArrayList
					has one entry for each vertex...
				ii)Each inner ArrayList contains the neighbors of the corresponding vertex...
				iii)This is memory efficient for sparse graphs (graphs with relatively few edges compared to
					the maximum possible)...

		-> Key Functions in the Code :-
			i. addEdge(graph, u, v) :-
				- This function adds an edge between vertices u and v by...
				- Adding v to u's adjacency list: graph.get(u).add(v)...
				- Adding u to v's adjacency list: graph.get(v).add(u)...
				- This dual addition makes the graph undirected (bidirectional edges)...
			ii. printGraph(graph) :-
				- This function displays the adjacency list representation by...
				- Iterating through each vertex in the graph...
				- Printing the vertex number followed by its adjacency list...
				- Format: vertex -> [list of adjacent vertices]...
		-> Time Complexity :-
		  	- Adding an Edge = O(1)...
		  	- Printing the graph = O(V + E) where V = Vertices and E = Edges...
                -> Space Complexity = O(V + E)...
*/

import java.util.ArrayList;

public class Graph_Implementation_Using_Adjacency_LIst {

	private static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
		graph.get(u).add(v);
		graph.get(v).add(u);
	}

	private static void printGraph(ArrayList<ArrayList<Integer>> graph) {
		for (int i = 0; i < graph.size(); i++) {
			ArrayList<Integer> curr = graph.get(i);
			System.out.println(i + " -> " + curr);
		}
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int vertices = 5;

		for (int i = 0; i < vertices; i++) {
			graph.add(new ArrayList<>());
		}

		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 0, 3);
		addEdge(graph, 0, 4);
		addEdge(graph, 1, 2);
		addEdge(graph, 1, 4);
		addEdge(graph, 2, 3);
		addEdge(graph, 2, 4);

		System.out.println("The  Adjacency list would be like : ");
		printGraph(graph);
	}

}

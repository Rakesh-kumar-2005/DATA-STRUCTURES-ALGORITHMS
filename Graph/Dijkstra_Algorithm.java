package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra_Algorithm {

    static class Pair {
        int node;
        int distance;

        public Pair(int node, int dist) {
            this.node = node;
            this.distance = dist;
        }

    }

    private static int[] dijkstra(ArrayList<ArrayList<ArrayList<Integer>>> graph, int vertices, int src) {

        int[] distances = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);

        distances[src] = 0;
        pq.add(new Pair(src, 0));

        while (! pq.isEmpty()) {

            Pair currPair = pq.poll();
            int currNode = currPair.node;
            int currDistance = currPair.distance;

            for (int i = 0; i < graph.get(currNode).size(); i++) {

                int edgeWeight = graph.get(currNode).get(i).get(1);
                int adjNode = graph.get(currNode).get(i).get(0);

                if (currDistance + edgeWeight < distances[adjNode]) {
                    distances[adjNode] = currDistance + edgeWeight;
                    pq.add(new Pair(adjNode, distances[adjNode]));
                }

            }

        }

        return distances;

    }

    public static void main(String[] args) {

        int vertices = 5;

        ArrayList<ArrayList<ArrayList<Integer>>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Node 0 edges
        graph.get(0).add(new ArrayList<>());
        graph.get(0).get(0).add(1);
        graph.get(0).get(0).add(4);

        graph.get(0).add(new ArrayList<>());
        graph.get(0).get(1).add(2);
        graph.get(0).get(1).add(1);

        // Node 1 edges
        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(0).add(0);
        graph.get(1).get(0).add(4);

        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(1).add(2);
        graph.get(1).get(1).add(2);

        graph.get(1).add(new ArrayList<>());
        graph.get(1).get(2).add(3);
        graph.get(1).get(2).add(5);

        // Node 2 edges
        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(0).add(0);
        graph.get(2).get(0).add(1);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(1).add(1);
        graph.get(2).get(1).add(2);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(2).add(3);
        graph.get(2).get(2).add(3);

        graph.get(2).add(new ArrayList<>());
        graph.get(2).get(3).add(4);
        graph.get(2).get(3).add(2);

        // Node 3 edges
        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(0).add(1);
        graph.get(3).get(0).add(5);

        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(1).add(2);
        graph.get(3).get(1).add(3);

        graph.get(3).add(new ArrayList<>());
        graph.get(3).get(2).add(4);
        graph.get(3).get(2).add(1);

        // Node 4 edges
        graph.get(4).add(new ArrayList<>());
        graph.get(4).get(0).add(2);
        graph.get(4).get(0).add(2);

        graph.get(4).add(new ArrayList<>());
        graph.get(4).get(1).add(3);
        graph.get(4).get(1).add(1);

        int source = 0;

        int[] distances = dijkstra(graph, vertices, source);

        System.out.println("Shortest distances from source vertex " + source + ":");

        for (int i = 0; i < vertices; i++) {

            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": INFINITY (Not reachable)");
            } else {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }

        }

    }
    
}
package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Minimum_Spanning_Tree_Prims_Algorithm {

    static class Pair {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

    }

    private static int spanningTree(int V, int[][] edges) {

        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            graph.get(u).add(new Pair(v, wt));
            graph.get(v).add(new Pair(u, wt));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.dist - y.dist);
        pq.add(new Pair(0, 0));

        int sum = 0;
        int[] visited = new int[V];

        while (! pq.isEmpty()) {
            Pair curr = pq.poll();

            int node = curr.node;
            int dist = curr.dist;

            if (visited[node] == 1) {
                continue;
            }

            sum += dist;
            visited[node] = 1;

            for (Pair neighbour : graph.get(node)) {

                if (visited[neighbour.node] == 0) {
                    pq.add(neighbour);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        int[][] edges = {{0, 1, 5}, {1, 2, 3}, {0, 2, 1}, {0, 3, 6}, {1, 3, 2}, {2, 3, 7}};
        int V = 4;
        System.out.println("The cost of minimum spanning tree is : " + spanningTree(V, edges));

    }

}

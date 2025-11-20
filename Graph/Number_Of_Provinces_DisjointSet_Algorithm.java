package Graph;

import java.util.ArrayList;

public class Number_Of_Provinces_DisjointSet_Algorithm {

    static class DisjointSet {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public DisjointSet(int v) {
            for (int i = 0; i < v; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ulp = findParent(parent.get(node));
            parent.set(node, ulp);
            return ulp;
        }

        public void unionBySize(int u, int v) {

            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size.get(ulp_u) > size.get(ulp_v)) {
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
            } else {
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
            }

        }


    }

    private static int makeConnected(int n, int[][] connections) {

        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (connections[i][j] == 1) {
                    ds.unionBySize(i, j);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                count++;
            }
        }

        return count;

    }

    public static void main(String[] args) {

        int[][] graph = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int noOfProvinces = makeConnected(3, graph);

        System.out.println("Total Number of Provinces : " + noOfProvinces);

    }

}
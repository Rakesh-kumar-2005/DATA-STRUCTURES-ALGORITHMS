package Graph;

import java.util.ArrayList;

public class Number_Of_Operations_To_Make_Network_Connected {

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
        int countExtraEdges = 0;

        for (int[] connection : connections) {

            int u = connection[0];
            int v = connection[1];

            if (ds.findParent(u) == ds.findParent(v)) {
                countExtraEdges++;
            } else {
                ds.unionBySize(u, v);
            }

        }

        int countComponents = 0;

        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                countComponents++;
            }
        }

        int ans = countComponents - 1;
        if (countExtraEdges >= ans) {
            return ans;
        }

        return - 1;

    }

    public static void main(String[] args) {

        int[][] connections = {{0, 1}, {0, 2}, {1, 2}};
        int n = 4;

        int ans = makeConnected(n, connections);
        System.out.println("Minimum Number of Operations to make Network Connected : " + ans);

    }

}
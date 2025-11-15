package Graph;

import java.util.ArrayList;

public class Disjoint_Set_Find_Parent_And_Union_Set {

    static class Disjoint {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public Disjoint(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                rank.add(0);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ultimateParent = findParent(parent.get(node));
            parent.set(node, ultimateParent);
            return parent.get(node);
        }

        public void unionByRank(int u, int v) {

            int ulp_u = findParent(u);
            int ulp_v = findParent(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (rank.get(ulp_u) > rank.get(ulp_v)) {
                parent.set(ulp_v, ulp_u);
            } else if (rank.get(ulp_u) < rank.get(ulp_v)) {
                parent.set(ulp_u, ulp_v);
            } else {
                parent.set(ulp_v, ulp_u);
                rank.set(ulp_v, rank.get(ulp_u) + 1);
            }

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

    public static void main(String[] args) {

        // Union By Rank...
        Disjoint ds = new Disjoint(7);

        ds.unionByRank(0, 1);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);

        ds.unionByRank(4, 5);
        ds.unionByRank(5, 6);
        ds.unionByRank(3, 6);

        System.out.println("\n\nUnion By Rank...");

        System.out.println("Ultimate Parent of 1 is " + ds.findParent(1));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(2));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(3));

        System.out.println("Ultimate Parent of 1 is " + ds.findParent(4));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(5));
        System.out.println("Ultimate Parent of 1 is " + ds.findParent(6));

        // Union By Size...
        Disjoint ds2 = new Disjoint(7);

        ds2.unionBySize(1, 0);
        ds2.unionBySize(1, 2);
        ds2.unionBySize(2, 3);

        ds2.unionBySize(4, 5);
        ds2.unionBySize(5, 6);
        ds2.unionBySize(3, 6);

        System.out.println("\n\nUnion By Size...");

        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(1));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(2));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(3));

        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(4));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(5));
        System.out.println("Ultimate Parent of 1 is " + ds2.findParent(6));

    }

}
package Graph;

import java.util.*;

public class Accounts_Merge {

    static class DisjointSet {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            if (parent.get(node) == node) {
                return node;
            }

            int ultimateParent = findParent(parent.get(node));
            parent.set(node, ultimateParent);
            return ultimateParent;
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

    public ArrayList<ArrayList<String>> accountsMerge(ArrayList<ArrayList<String>> accounts) {

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mailNode = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {

                String mail = accounts.get(i).get(j);

                if (! mailNode.containsKey(mail)) {
                    mailNode.put(mail, i);
                } else {
                    ds.unionBySize(i, mailNode.get(mail));
                }

            }
        }

        ArrayList<String>[] mergedAccounts = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergedAccounts[i] = new ArrayList<>();
        }

        for (Map.Entry<String, Integer> it : mailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.findParent(it.getValue());

            mergedAccounts[node].add(mail);
        }

        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergedAccounts[i].isEmpty()) {
                continue;
            }

            Collections.sort(mergedAccounts[i]);

            ArrayList<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedAccounts[i]);

            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));

        Accounts_Merge obj = new Accounts_Merge();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              ACCOUNTS MERGE - INPUT                        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("Account " + (i + 1) + ": " + accounts.get(i).get(0));
            System.out.println("  Emails:");
            for (int j = 1; j < accounts.get(i).size(); j++) {
                System.out.println("    - " + accounts.get(i).get(j));
            }
            System.out.println();
        }

        ArrayList<ArrayList<String>> ans = obj.accountsMerge(accounts);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           ACCOUNTS MERGE - OUTPUT                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        System.out.println("Total merged accounts: " + ans.size());
        System.out.println();

        for (int i = 0; i < ans.size(); i++) {
            System.out.println("Merged Account " + (i + 1) + ": " + ans.get(i).get(0));
            System.out.println("  Emails (" + (ans.get(i).size() - 1) + " total):");
            for (int j = 1; j < ans.get(i).size(); j++) {
                System.out.println("    " + j + ". " + ans.get(i).get(j));
            }
            System.out.println();
        }

        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

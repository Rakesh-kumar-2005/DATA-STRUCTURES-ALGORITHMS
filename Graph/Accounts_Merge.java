package Graph;

/*

Description:
  Following program demonstrates the solution to the "Accounts Merge" problem using Disjoint Set Union (DSU) to group email addresses that belong to the same person...

Problem Statement:
  -> You are given a list of accounts, where each account contains a username followed by multiple email addresses...
  -> Two accounts belong to the same person if they share at least one email address...
  -> The goal is to merge all accounts that share common emails and return a grouped list of merged accounts...
  -> Each merged account should have the person's name followed by all unique emails sorted lexicographically...

Approach:
  > Using Disjoint Set Union (Union-Find):
     i. Treat every account as a separate node initially...
     ii. Map each email to the account index where it first appears...
     iii. If an email appears again in another account, union both account indices...
     iv. After unions, accounts belonging to the same parent become one merged group...

> Email Mapping Logic:
  -> Use a HashMap to map every unique email to the account index where it first appeared...
  -> When a repeated email is found, merge the two accounts using unionBySize...
  -> This ensures that all accounts with shared emails fall under the same representative parent...

> Algorithm Steps:
  -> Initialize DSU for n accounts...
  -> Traverse each account:
     * For each email in the account:
         - If email is new, map it to current account index...
         - If email exists, union the current index with mapped index...
  -> Create an array of lists to store emails grouped by their DSU parent...
  -> Assign each email to the list corresponding to its ultimate parent...
  -> Sort each email group and prepend the account owner's name...
  -> Collect all valid merged accounts into the final answer list...

> Implementation Note:
  -> Path compression accelerates parent lookup for DSU...
  -> Union by size ensures optimal merging and minimal tree height...
  -> Using ArrayList<String>[] allows efficient grouping of emails by parent...
  -> Sorting is applied only at the end, not during union operations...

> Example:
  -> Input Accounts:
       John: johnsmith@mail.com, john_newyork@mail.com
       John: johnsmith@mail.com, john00@mail.com
       Mary: mary@mail.com
       John: johnnybravo@mail.com
  -> johnsmith@mail.com appears in accounts 1 and 2, so they are merged...
  -> Final output merges John's first two accounts and keeps the others separate...
  -> Result includes grouped, unique, sorted email lists for each person...

> Edge Cases:
  -> Single account with multiple emails merges into itself...
  -> Multiple accounts with completely unique emails remain unchanged...
  -> Multiple overlapping email chains correctly merge into a single group...
  -> Handles large number of accounts and emails efficiently...

> Time and Space Complexity:
  -> Time Complexity: O(n * k * α(n) + m log m), where:
       n = number of accounts,
       k = average number of emails per account,
       m = total number of unique emails,
       α(n) = inverse Ackermann (almost constant)...
  -> Space Complexity: O(m) for email-to-index map and grouping arrays...

*/

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

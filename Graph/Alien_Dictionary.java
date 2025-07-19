package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Alien_Dictionary {

    // Topological Sort using Kahn's Algorithm
    private static int[] topologicalSorting(int V, ArrayList<ArrayList<Integer>> adjacencyList) {
        int[] inDegrees = new int[V];

        for (int i = 0; i < V; i++) {
            for (int adjacent : adjacencyList.get(i)) {
                inDegrees[adjacent]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        int idx = 0;
        int[] topoSort = new int[V];

        while (! q.isEmpty()) {
            int currNode = q.poll();
            topoSort[idx++] = currNode;

            for (int adjacent : adjacencyList.get(currNode)) {
                inDegrees[adjacent]--;
                if (inDegrees[adjacent] == 0) {
                    q.add(adjacent);
                }
            }
        }

        // Detect cycle: if not all characters are sorted
        if (idx < V) {
            throw new IllegalArgumentException("Cycle detected: No valid character order possible.");
        }

        return topoSort;
    }

    // Main function to find the order of characters
    private static char[] findOrder(String[] words, int key) {

        // Step 1: Form the graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < key; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 2: Add directed edges based on character difference
        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            // Check for invalid case: longer word before its prefix
            if (word1.startsWith(word2) && word1.length() > word2.length()) {
                throw new IllegalArgumentException("Invalid input: Word '" + word1 + "' comes before its prefix '" + word2 + "'");
            }

            int minLength = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLength; j++) {

                int ch1 = word1.charAt(j) - 'a';
                int ch2 = word2.charAt(j) - 'a';

                if (ch1 != ch2) {
                    graph.get(ch1).add(ch2);  // ch1 should come before ch2
                    break;
                }

            }

        }

        // Step 3: Topological sort to get character order
        int[] topoSort = topologicalSorting(key, graph);
        char[] ans = new char[key];

        for (int i = 0; i < key; i++) {
            ans[i] = (char) (topoSort[i] + 'a');
        }

        return ans;
    }

    public static void main(String[] args) {

        // Test Case 1 (Valid)
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println("Test Case 1:");
        printOrder(words1, 4);

        // Test Case 2 (Cycle)
        String[] words2 = {"a", "b", "a"};
        System.out.println("\nTest Case 2:");
        printOrder(words2, 2);

        // Test Case 3 (Prefix Violation)
        String[] words3 = {"abc", "ab"};
        System.out.println("\nTest Case 3:");
        printOrder(words3, 3);
    }

    // Helper method to print order and handle exceptions
    private static void printOrder(String[] words, int key) {

        try {

            char[] ans = findOrder(words, key);
            System.out.println("The order of characters is:");

            for (int i = 0; i < ans.length - 1; i++) {
                System.out.print(ans[i] + " -> ");
            }

            System.out.println(ans[ans.length - 1]);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
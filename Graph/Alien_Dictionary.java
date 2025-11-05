package Graph;

/*

Description:
    -> This program solves the Alien Dictionary problem using Topological Sorting...
    -> Given a sorted dictionary of an alien language with a known number of characters `K`, the task is to find the correct order of characters...

Problem Statement:
    -> You are given an array of words (sorted in alien language dictionary order) and an integer `K` representing the number of characters...
    -> Each character is assumed to be a lowercase English letter from 'a' to ('a' + K - 1)...
    -> Your goal is to find a valid character order that respects the given dictionary sequence...

Approach:
    -> Step 1: Construct a directed graph from the dictionary...
        - For each pair of adjacent words, find the first mismatching character...
        - If `word1[i] ≠ word2[i]`, it means `word1[i]` comes before `word2[i]` → create an edge from `word1[i]` to `word2[i]`...

    -> Step 2: Handle prefix violations...
        - If a longer word appears before its prefix (e.g., "abc" before "ab"), the dictionary is invalid...

    -> Step 3: Apply **Kahn’s Algorithm** for Topological Sorting...
        - Compute in-degrees of each node...
        - Use BFS to generate the character ordering...
        - If not all characters are processed (i.e., a cycle is detected), the order is invalid...

    -> Step 4: Convert sorted indices back to characters and return the result...

Cycle Detection and Edge Cases:
    -> If a cycle exists (e.g., ordering like a → b → a), the function throws an error...
    -> If a longer word comes before a prefix of itself, it throws an error...

Time and Space Complexity:
    -> Time Complexity: O(N + K), where N is the total number of characters in all words, and K is the number of unique characters...
    -> Space Complexity: O(K + E), where E is the number of edges in the graph...

Demonstration:
    -> Test Case 1:
        Input: ["baa", "abcd", "abca", "cab", "cad"], K = 4
        Output: b -> d -> a -> c

    -> Test Case 2:
        Input: ["a", "b", "a"], K = 2
        Output: Error: Cycle detected: No valid character order possible.

    -> Test Case 3:
        Input: ["abc", "ab"], K = 3
        Output: Error: Invalid input: Word 'abc' comes before its prefix 'ab'
*/

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


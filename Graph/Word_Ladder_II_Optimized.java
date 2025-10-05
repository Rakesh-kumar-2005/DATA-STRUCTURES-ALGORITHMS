package Graph;

/*

Description:
    -> This program solves the **Word Ladder II problem (Optimized Approach)**.
    -> The objective is to transform a `beginWord` into an `endWord` by changing only 
       one letter at a time, such that each intermediate word exists in the given `wordList`.
    -> Unlike Word Ladder I (which asks only for the shortest transformation length), 
       this problem requires finding **all the shortest transformation sequences**.

Problem Statement:
    -> Given two words, `beginWord` and `endWord`, along with a dictionary `wordList`:
        - Each transformation can change only one character.
        - The transformed word must be in the wordList.
        - Find all the shortest transformation sequences from `beginWord` to `endWord`.
        - If no sequence exists, return an empty list.

Optimized Approach:
    > The solution combines **Breadth-First Search (BFS)** and **Depth-First Search (DFS)**:
        1. **BFS Phase**:
            - Perform BFS starting from `beginWord` to calculate the shortest distance 
              (level) for each word to reach `endWord`.
            - Store each word and its level in a `HashMap`.
            - This ensures we only explore words that are part of the shortest paths.
        2. **DFS Phase**:
            - Backtrack from `endWord` to `beginWord` using the level map.
            - Only move to words that are exactly one level closer to the `beginWord`.
            - Collect all valid shortest sequences.

Key Details:
    -> BFS ensures we find the minimal depth of transformations.
    -> DFS reconstructs all valid paths by moving backward from `endWord` to `beginWord`.
    -> The algorithm avoids revisiting unnecessary words by removing them once used at a level.

Algorithm Steps:
    1. Insert all words of `wordList` into a HashSet for quick lookup.
    2. Run BFS from `beginWord`:
         - For each word, generate all possible one-letter transformations.
         - If a transformation exists in the set, record its level and push into the queue.
         - Stop BFS once `endWord` is found (since BFS ensures minimal steps).
    3. Run DFS from `endWord`:
         - At each step, move only to words whose level is one less.
         - Build sequences until `beginWord` is reached.
         - Reverse each sequence before storing (since DFS builds from `endWord`).
    4. Return all collected sequences.

Complexity:
    -> Time Complexity: O(N * L * 26)
         - N = number of words in wordList
         - L = word length
         - 26 = number of letters to try for each position
    -> Space Complexity: O(N * L) due to storing BFS levels, recursion stack, and final sequences.

Demonstration Example:
    Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log","cog"]

    Output:
        Shortest transformation sequences:
            hit -> hot -> dot -> dog -> cog
            hit -> hot -> lot -> log -> cog

Note:
    -> This optimized approach is much faster than the naive BFS solution
       since it avoids storing entire sequences during BFS and instead 
       reconstructs them later using DFS.

*/

import java.util.*;

public class Word_Ladder_II_Optimized {

    static String b;
    static ArrayList<ArrayList<String>> ans;
    static HashMap<String, Integer> mp;

    private static void dfs(String word, ArrayList<String> sequence) {

        if (word.equals(b)) {
            ArrayList<String> dup = new ArrayList<>(sequence);
            Collections.reverse(dup);
            
            ans.add(dup);
            return;
        }

        int level = mp.get(word);
        int len = word.length();

        for (int i = 0; i < len; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {

                char[] replacedCharArray = word.toCharArray();
                replacedCharArray[i] = ch;
                String replacedWord = new String(replacedCharArray);

                if (mp.containsKey(replacedWord) && mp.get(replacedWord) == level - 1) {

                    sequence.add(replacedWord);
                    dfs(replacedWord, sequence);
                    sequence.remove(replacedWord);

                }

            }
        }

    }

    private static ArrayList<ArrayList<String>> findLadders(String beginWord, String endWord, ArrayList<String> wordList) {

        HashSet<String> st = new HashSet<>();

        for (String currWord : wordList) {
            st.add(currWord);
        }

        b = beginWord;
        st.remove(beginWord);

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        mp = new HashMap<>();
        mp.put(beginWord, 1);

        while (! q.isEmpty()) {

            String currWord = q.poll();
            int currLevel = mp.get(currWord);

            if (currWord.equals(endWord)) {
                break;
            }

            for (int i = 0; i < currWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    char[] replacedCharArray = currWord.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    if (st.contains(replacedWord)) {
                        mp.put(replacedWord, currLevel + 1);
                        q.add(replacedWord);
                        st.remove(replacedWord);
                    }

                }
            }
        }

        ans = new ArrayList<>();
        
        if (mp.containsKey(endWord)) {
            ArrayList<String> sequence = new ArrayList<>();
            sequence.add(endWord);
            dfs(endWord, sequence);
        }

        return ans;
        
    }

    private static void printList(ArrayList<String> wordList) {

        for (int i = 0; i < wordList.size() - 1; i++) {
            System.out.print(wordList.get(i) + " -> ");
        }
        
        System.out.println(wordList.get(wordList.size() - 1));

    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String targetWord = "cog";

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println("The begin word is : " + beginWord);
        System.out.println("The end word is : " + targetWord);
        System.out.println("The word list is : ");
        printList(wordList);
        System.out.println();

        ArrayList<ArrayList<String>> ans = findLadders(beginWord, targetWord, wordList);

        if (ans.isEmpty()) {
            System.out.println("No sequence found");
            return;
        }

        System.out.println("The Sequence through which we can convert the begin word to end word is : ");

        for (ArrayList<String> currSequence : ans) {
            printList(currSequence);
            System.out.println();
        }
        
    }
    
}

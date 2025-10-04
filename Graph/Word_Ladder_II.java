package Graph;

/*

Description:
    -> This program solves the **Word Ladder II** problem.
    -> The goal is to transform a `beginWord` into an `endWord` by changing only 
       one character at a time, where each transformed word must exist in a given `wordList`.
    -> Unlike the basic Word Ladder problem (which only asks for the shortest length),
       this problem requires finding **all shortest transformation sequences**.

Problem Statement:
    -> Given two words, beginWord and endWord, and a wordList, 
       find all the shortest transformation sequences from beginWord to endWord.
    -> Each transformation changes only one letter at a time.
    -> Each intermediate word must be present in the wordList.
    -> If no transformation is possible, return an empty list.

Approach:
    > BFS (Breadth-First Search) + Backtracking idea:
        1. Use BFS to explore all possible word transformations level by level.
        2. For each word in the queue, generate all possible next words by replacing one letter at a time.
        3. If the new word exists in the dictionary (HashSet), add it to the sequence.
        4. Keep track of words used at the current level to avoid revisiting.
        5. Stop when the `endWord` is reached — since BFS ensures minimal steps.
        6. Collect all shortest sequences and return them.

Key Details:
    -> Each node in BFS stores the entire sequence (not just the word).
    -> The algorithm ensures we only keep shortest paths.
    -> Once a level is completed, we remove words used at that level from the dictionary 
       to prevent longer redundant paths.
    -> This guarantees only shortest transformations are collected.

Algorithm Steps:
    1. Insert all words of `wordList` into a HashSet for quick lookup.
    2. Initialize a BFS queue with a list containing only `beginWord`.
    3. Track words used at the current BFS level.
    4. For each sequence in the queue:
         - Check if the last word == `endWord`. If yes, add it to results.
         - Otherwise, generate all possible one-character transformations.
         - If a transformed word exists in the dictionary, extend the sequence and push it into the queue.
    5. Once the endWord is found at a certain level, collect all sequences of that level.
    6. Return all valid shortest sequences.

Time and Space Complexity:
    -> Time Complexity: O(N * L * 26)
         - N = number of words in the list
         - L = length of each word
         - 26 for trying all letters
    -> Space Complexity: O(N * L) due to storing sequences and BFS queue.

Demonstration:
    Example:
        Input:
            beginWord = "hit"
            endWord = "cog"
            wordList = ["hot", "dot", "dog", "lot", "log", "cog"]

        Output:
            The shortest transformation sequences are:
                hit -> hot -> dot -> dog -> cog
                hit -> hot -> lot -> log -> cog

*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Word_Ladder_II {

    private static ArrayList<ArrayList<String>> findLadders(String beginWord, String endWord, ArrayList<String> wordList) {

        HashSet<String> st = new HashSet<>();

        for (String word : wordList) {
            st.add(word);
        }

        st.remove(beginWord);

        Queue<ArrayList<String>> q = new LinkedList<>();
        ArrayList<String> ls = new ArrayList<>();
        
        ls.add(beginWord);
        q.add(ls);

        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        int level = 0;

        while (! q.isEmpty()) {

            ArrayList<String> currSequence = q.poll();
            
            if (currSequence.size() > level) {
                level++;

                for (String s : usedOnLevel) {
                    st.remove(s);
                }
            }

            String currWord = currSequence.get(currSequence.size() - 1);

            if (currWord.equals(endWord)) {
                
                if (ans.isEmpty()) {
                    ans.add(currSequence);
                } else if (ans.get(0).size() == currSequence.size()) {
                    ans.add(currSequence);
                }
                
            }

            for (int i = 0; i < currWord.length(); i++) {

                for (char ch = 'a'; ch <= 'z'; ch++) {

                    char[] replacedCharArray = currWord.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    if (st.contains(replacedWord)) {
                        
                        ArrayList<String> newSequence = new ArrayList<>(currSequence);
                        newSequence.add(replacedWord);
                        
                        q.add(newSequence);
                        usedOnLevel.add(replacedWord);
                    
                    }

                }

            }

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
        System.out.println("The Sequence through which we can convert the begin word to end word is : ");

        for (ArrayList<String> currSequence : ans) {
            printList(currSequence);
            System.out.println();
        }
        
    }
    
}

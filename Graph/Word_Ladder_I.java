package Graph;

/*

Description:
    -> This program finds the length of the shortest transformation sequence from a given `beginWord` to an `endWord` using a given list of words...
    -> Each transformation can only change one character at a time and must result in a valid word present in the word list...
    -> The solution uses Breadth-First Search (BFS) to find the shortest path efficiently...

Problem Statement:
    -> Given two words, `beginWord` and `endWord`, and a word list, find the length of the shortest transformation sequence from `beginWord` to `endWord` such that:
        - Only one letter can be changed at a time...
        - Each transformed word must exist in the word list...
    -> If no such transformation is possible, return 0...

Approach:
    -> Step 1: Add all words from the word list to a HashSet for O(1) lookup...
    -> Step 2: Initialize a queue for BFS with the beginWord and level = 1...
    -> Step 3: For each word in the queue, generate all possible one-letter transformations...
        - If a transformed word exists in the set, add it to the queue and remove it from the set...
        - Stop when the endWord is found and return the current step count...
    -> Step 4: If the endWord is never found, return 0...

Time and Space Complexity:
    -> Time Complexity: O(N * L * 26), where N = size of wordList, L = length of each word...
    -> Space Complexity: O(N), for the set and BFS queue...

Example:
    -> Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot", "dot", "dog", "lot", "log", "cog"]
    -> Output:
        The minimum number of steps required to convert hit to cog is : 5
    -> Transformation Sequence:
        "hit" -> "hot" -> "dot" -> "dog" -> "cog"

*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Word_Ladder_I {

    static class Pair {
        String word;
        int steps;

        public Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }

    }

    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        HashSet<String> set = new HashSet<>();

        // To keep track of visited words
        for (String word : wordList) {
            set.add(word);
        }

        set.remove(beginWord);

        while (! q.isEmpty()) {

            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currSteps = currPair.steps;

            if (currWord.equals(endWord)) {
                return currSteps;
            }

            for (int i = 0; i < currWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    char[] replacedCharArray = currWord.toCharArray();
                    replacedCharArray[i] = ch;

                    String wordAfterModification = new String(replacedCharArray);

                    if (set.contains(wordAfterModification)) {
                        set.remove(wordAfterModification);
                        q.add(new Pair(wordAfterModification, currSteps + 1));
                    }

                }

            }

        }

        return 0;

    }

    public static void main(String[] args) {

        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        String beginWord = "hit";
        String endWord = "cog";

        System.out.println("The minimum number of steps required to convert " + beginWord + " to " + endWord + " is : " + ladderLength(beginWord, endWord, List.of(wordList)));

    }

}
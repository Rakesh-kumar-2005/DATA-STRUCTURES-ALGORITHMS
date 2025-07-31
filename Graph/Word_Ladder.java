package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Word_Ladder {

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
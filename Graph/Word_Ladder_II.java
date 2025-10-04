package Graph;

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
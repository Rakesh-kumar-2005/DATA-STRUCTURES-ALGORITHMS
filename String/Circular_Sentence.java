package String;

public class Circular_Sentence {

    private static boolean isCircularSentence(String s) {

        s = s.trim();
        String[] words = s.split(" ");

        int n = words.length;
        String word1 = words[0];
        String word2 = words[n - 1];

        if (word1.charAt(0) != word2.charAt(word2.length() - 1)) {
            return false;
        }

        if (n == 1) {
            return word1.charAt(0) == word1.charAt(word1.length() - 1);
        }


        for (int i = 0; i < n - 1; i++) {

            word1 = words[i];
            word2 = words[i + 1];

            if (word2.charAt(0) != word1.charAt(word1.length() - 1)) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        String s = "the quick brown fox jumps over the lazy dog";
        boolean ans = isCircularSentence(s);

        if (ans) {
            System.out.println("This sentence is circular...");
        } else {
            System.out.println("This sentence is not circular...");
        }

    }

}
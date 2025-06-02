package String;

public class Counting_Words_With_A_Given_Prefix {

    private static int prefixCount(String[] words, String pref) {

        int count = 0;

        for (String currWord : words) {

            if (currWord.startsWith(pref)) {
                count++;
            }

        }

        return count;
    }

    public static void main(String[] args) {

        String[] words = {"pay", "attention", "practice", "attend"};
        String pref = "at";

        System.out.println("The number of words with the given prefix is : " + prefixCount(words, pref));

    }

}
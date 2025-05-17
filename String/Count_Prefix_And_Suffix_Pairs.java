package String;

public class Count_Prefix_And_Suffix_Pairs {

    private static int countPairs(String[] words) {

        int count = 0;
        int n = words.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
                    count++;
                }

            }
        }

        return count;

    }

    public static void main(String[] args) {

        String[] words = {"a", "aba", "ababa", "aa"};
        System.out.println("The number of prefix and suffix pairs are : " + countPairs(words));

    }

}
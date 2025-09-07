package String;

public class Count_the_Number_Of_Consistent_Strings {

    private static int countConsistentStrings(String allowed, String[] words) {
        int count = 0;

        for (String currWord : words) {
            if (helper(allowed, currWord)) {
                count++;
            }
        }

        return count;
    }

    private static boolean helper(String allowed, String currWord) {

        for (int i = 0; i < currWord.length(); i++) {
            if (! allowed.contains(String.valueOf(currWord.charAt(i)))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};

        System.out.println("The number of consistent strings is : " + countConsistentStrings(allowed, words));

    }

}

package String;

public class Length_Of_Last_Word {

    private static int lengthOfLastWord(String word) {
        word = word.trim();
        int i = word.length() - 1;
        int count = 0;

        while (i >= 0 && word.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }

    public static void main(String[] args) {
        String word = "   fly me   to   the moon  ";
        System.out.println("Length of the last word is : " + lengthOfLastWord(word));
    }

}
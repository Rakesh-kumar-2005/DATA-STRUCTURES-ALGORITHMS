package String;

public class Rotate_String_2 {

    private static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            return true;
        }

        String temp = s + s;

        if (temp.contains(goal)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        String s = "abcde";
        String goal = "cdeab";

        boolean result = rotateString(s, goal);

        if (result) {
            System.out.println("The given strings can be rotated to each other");
        } else {
            System.out.println("The given strings cannot be rotated to each other");
        }

    }

}

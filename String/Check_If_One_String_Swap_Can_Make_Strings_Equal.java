package String;


public class Check_If_One_String_Swap_Can_Make_Strings_Equal {

    private static boolean areAlmostEqual(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        int i = - 1;
        int j = - 1;
        int count = 0;

        for (int k = 0; k < s1.length(); k++) {

            if (s1.charAt(k) != s2.charAt(k)) {

                count++;

                if (i == - 1) {
                    i = k;
                } else if (j == - 1) {
                    j = k;
                }

            }

        }

        if (count == 2 && s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i)) {
            return true;
        }

        return false;

    }

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "hello";

        System.out.println("Are Strings Equal : " + areAlmostEqual(s1, s2));

        String s3 = "hello";
        String s4 = "lelho";

        System.out.println("Are Strings Equal : " + areAlmostEqual(s3, s4));

    }

}
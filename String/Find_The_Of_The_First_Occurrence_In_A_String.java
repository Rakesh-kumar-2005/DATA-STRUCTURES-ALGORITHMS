package String;

public class Find_The_Of_The_First_Occurrence_In_A_String {

    private static int strStr1(String str1, String str2) {
        return str1.indexOf(str2);
    }

    private static int strStr2(String str1, String str2) {

        int length1 = str1.length();
        int length2 = str2.length();

        for (int i = 0; i <= length1 - length2; i++) {

            boolean flag = true;

            for (int j = 0; j < length2; j++) {

                if (str1.charAt(i + j) != str2.charAt(j)) {
                    flag = false;
                    break;
                }

            }

            if (flag) {
                return i;
            }

        }

        return - 1;

    }

    public static void main(String[] args) {

        String str1 = "sadbutsad";
        String str2 = "sad";

        System.out.println("Index of first occurrence of " + str2 + " in " + str1 + " is from method 1 : " + strStr1(str1, str2));
        System.out.println("Index of first occurrence of " + str2 + " in " + str1 + " is from method 2 : " + strStr2(str1, str2));

    }

}
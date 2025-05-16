package String;

import java.util.ArrayList;

public class String_Matching_In_An_Array {

    private static ArrayList<String> stringMatching(String[] words) {
        ArrayList<String> ans = new ArrayList<>();
        int n = words.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (words[j].contains(words[i]) && i != j) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        String[] words = {"mass", "as", "hero", "superhero"};

        System.out.println("The words which has a substring in the array are : ");
        System.out.println(stringMatching(words));

    }

}

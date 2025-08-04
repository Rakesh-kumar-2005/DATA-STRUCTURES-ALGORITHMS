package Math_Problems;

import java.util.ArrayList;

public class Pascals_Triangle {
    private static ArrayList<ArrayList<Integer>> generate(int numRows) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(1);
        ans.add(temp);

        if (numRows == 1) {
            return ans;
        }

        for (int i = 1; i < numRows; i++) {
            temp = new ArrayList<>();

            ArrayList<Integer> prev = ans.get(i - 1);
            temp.add(1);

            for (int j = 1; j < i; j++) {
                temp.add(prev.get(j - 1) + prev.get(j));
            }

            temp.add(1);
            ans.add(temp);
        }
        return ans;
    }

    public static void main(String[] args) {

        int numRows = 5;
        ArrayList<ArrayList<Integer>> ans = generate(numRows);

        System.out.println("Pascals Triangle of " + numRows + " rows is : ");
        for (ArrayList<Integer> an : ans) {
            System.out.println(an);
        }

    }

}
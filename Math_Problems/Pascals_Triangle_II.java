package Math_Problems;

import java.util.ArrayList;

public class Pascals_Triangle_II {

    private static ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> ansList = new ArrayList<>();
        ansList.add(1);

        for (int i = 0; i < rowIndex; i++) {
            ArrayList<Integer> tempList = new ArrayList<>();

            tempList.add(1);
            for (int j = 1; j < ansList.size(); j++) {
                tempList.add(ansList.get(j - 1) + ansList.get(j));
            }
            tempList.add(1);

            ansList = tempList;
        }

        return ansList;

    }

    public static void main(String[] args) {

        int rowIndex = 3;
        ArrayList<Integer> row = getRow(rowIndex);

        System.out.print("The row is : ");
        for (int num : row) {
            System.out.print(num + " ");
        }
        System.out.println();

    }

}
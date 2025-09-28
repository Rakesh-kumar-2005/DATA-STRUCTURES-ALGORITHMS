package Math_Problems;

import java.util.Arrays;

public class Largest_Perimeter_Triangle {

    private static int largestPerimeter(int[] lengths) {

        Arrays.sort(lengths);

        for (int i = lengths.length - 1; i >= 2; i--) {

            int a = lengths[i];
            int b = lengths[i - 1];
            int c = lengths[i - 2];

            if (a < b + c) {
                return a + b + c;
            }

        }

        return 0;

    }

    public static void main(String[] args) {

        int[] lengths = {1, 2, 1, 10};
        int largestPerimeter = largestPerimeter(lengths);

        System.out.println("The largest perimeter is : " + largestPerimeter);

    }

}
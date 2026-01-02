package Arrays;

import java.util.Arrays;

public class N_Repeated_Element_In_Size_2N_Array {

    private static int repeatedNTimes(int[] numbers) {

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                return numbers[i];
            }
        }

        for (int i = 0; i < numbers.length - 2; i++) {
            if (numbers[i] == numbers[i + 2]) {
                return numbers[i];
            }
        }

        return numbers[0];
    }

    public static void main(String[] args) {
        
        int[][] testCases = {
            {1, 2, 3, 3},
            {2, 1, 2, 5, 3, 2},
            {5, 1, 5, 2, 5, 3, 5, 4},
            {9, 5, 6, 9},
            {7, 7, 8, 9}
        };

        System.out.println("=".repeat(50));
        System.out.println("N-REPEATED ELEMENT IN SIZE 2N ARRAY");
        System.out.println("=".repeat(50));

        for (int i = 0; i < testCases.length; i++) {

            int result = repeatedNTimes(testCases[i]);

            System.out.printf("Test %d: Array = %s\n", (i + 1), Arrays.toString(testCases[i]));
            System.out.printf("         Repeated Element = %d\n\n", result);
        }

        System.out.println("=".repeat(50));

    }

}


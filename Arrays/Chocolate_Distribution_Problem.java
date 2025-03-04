package Arrays;

/*
        Description :-
             Chocolate Distribution Problem by finding the minimum difference between
             the maximum and minimum chocolates distributed to students. It sorts the array
             and uses a sliding window approach....


        Approach :-
            > The program first sorts the chocolate packets array.
            > It then iterates through the array, considering every possible subset of m chocolates.
                For each subset, it calculates the difference between the maximum and minimum chocolates
                and updates the minimum difference found...
            > The sliding window technique ensures an optimal solution in O(n log n) time complexity due
                to sorting, followed by an O(n) traversal for subset evaluation....
*/

import java.util.Arrays;

public class Chocolate_Distribution_Problem {

    private static int findMinDiff(int[] arr, int m) {
        int n = arr.length;
        if (m == 0 || n == 0) return 0;

        Arrays.sort(arr);

        if (n < m) return -1;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i + m - 1 < n; i++) {
            int diff = arr[i + m - 1] - arr[i];
            if (diff < min) min = diff;
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 9, 56, 7, 9, 12};
        int noOfStudents = 5;
        System.out.println(findMinDiff(arr, noOfStudents));
    }
}


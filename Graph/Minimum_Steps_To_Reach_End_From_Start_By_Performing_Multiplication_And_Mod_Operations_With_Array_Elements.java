package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Minimum_Steps_To_Reach_End_From_Start_By_Performing_Multiplication_And_Mod_Operations_With_Array_Elements {

    static class Pair {
        int value;
        int steps;

        public Pair(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }

    }

    private static int minSteps(int[] arr, int start, int end) {

        if (start > end) {
            return - 1;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));

        int[] visited = new int[10000];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;

        while (! q.isEmpty()) {

            Pair currPair = q.poll();
            int currValue = currPair.value;
            int currSteps = currPair.steps;

            for (int i = 0; i < arr.length; i++) {
                int newValue = (currValue * arr[i]) % 10000;

                if (currSteps + 1 < visited[newValue]) {

                    if (newValue == end) {
                        return currSteps + 1;
                    }

                    visited[newValue] = currSteps + 1;
                    q.add(new Pair(newValue, currSteps + 1));

                }
            }

        }

        return - 1;

    }


    public static void main(String[] args) {

        int[] arr = {2, 5, 7};
        int start = 3;
        int end = 30;

        int totalSteps = minSteps(arr, start, end);

        if (totalSteps != - 1) {
            System.out.println("Total number of minimum steps to reach end " + end + " from start " + start + " is : " + totalSteps);
        } else {
            System.out.println("We can't reach from " + start + " to " + end + " by performing multiplication and mod operations with array elements...");
        }

    }

}

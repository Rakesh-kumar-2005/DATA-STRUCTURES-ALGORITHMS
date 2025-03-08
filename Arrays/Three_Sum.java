package Array;

/*
        Description :-
            Given an array of integers, the task is to find all unique triplets (three numbers) whose
            sum is equal to zero. The output should not contain duplicate triplets, even if elements repeat....

        Approach :-
            > The approach starts by sorting the array, which helps in efficiently handling duplicates
                    and using the two-pointer technique...
            > We iterate through the array, selecting each element arr[i] as the first number in the triplet...
            > To find the remaining two numbers, we use two pointers: j (starting from i+1) and k (starting
                    from the end). If the sum of arr[i] + arr[j] + arr[k] equals zero, we add the triplet to the result
                    and move both pointers while skipping duplicates...
            > If the sum is less than zero, we increase j; otherwise, we decrease k....
            > Time Complexity = O(n²)...
            > Space Complexity = O(1)...
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three_Sum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, -1, 0, 0, -2};
        List<List<Integer>> ans = threeSum(arr);

        System.out.println("The triplets with sum 0 ;");
        for (List<Integer> triplet : ans) {
            System.out.println(triplet);
        }
    }

    private static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr); // Sort the array first

        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate elements to avoid duplicate triplets
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];

                if (sum == 0) {
                    result.add(Arrays.asList(arr[i], arr[j], arr[k]));

                    // Move j and k to avoid duplicates
                    while (j < k && arr[j] == arr[j + 1]) j++;
                    while (j < k && arr[k] == arr[k - 1]) k--;

                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
}

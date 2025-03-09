package Binary_Search;

/*
        Description :-
                The problem asks for the minimum days needed to make m bouquets, each
                requiring k adjacent flowers, from an array of bloom days...
                Return -1 if impossible due to insufficient flowers...

        Approach :-
              > The solution uses binary search to find the minimum days. It checks if m * k flowers
                    are available; if not, returns -1....
             > It sets low and high as the min/max bloom days, then searches for the smallest day,
                    where m bouquets can be made...
             > The helper function simulates bouquet creation by counting adjacent flowers blooming
                    by day, dividing by k to get bouquets, and checking if the total meets m.
             > The binary search adjusts low or high based on feasibility, returning low as the answer....
             > Time Complexity = O( nlog(m) )...
             > Space Complexity = O(1)...
*/

public class Minimum_Number_Of_Days_To_Make_m_Number_Of_Bouquets {

    private static int minDays(int[] arr, int m, int k) {
        if ((long) m * k > arr.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int num : arr) {
            low = Math.min(num, low);
            high = Math.max(num, high);
        }

        while (low <= high) {
            int mid = (low + high) / 2;

            if (helper(arr, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean helper(int[] arr, int day, int m, int k) {
        int total = 0;
        int count = 0;
        for (int num : arr) {
            if (num <= day) {
                count++;
            } else {
                total += count / k;
                count = 0;
            }
        }
        total += count / k;
        return total >= m;
    }

    public static void main(String[] args) {
        int[] arr = {1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;
        System.out.println(minDays(arr, m, k));
    }
}

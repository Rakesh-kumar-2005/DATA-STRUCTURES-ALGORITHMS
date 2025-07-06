package Binary_Search;

public class Row_With_Maximum_ones {

    private static int lowerBound(int[] arr, int n, int x) {

        int low = 0;
        int high = n - 1;
        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private static int rowWithMax1s(int[][] arr, int n, int m) {

        int maxIndex = - 1;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int currCount = lowerBound(arr[i], m, 1);

            if (currCount > maxCount) {
                maxCount = currCount;
                maxIndex = i;
            }

        }

        return maxIndex;
    }

    public static void main(String[] args) {

        int n = 4;
        int m = 4;

        int[][] arr = {{0, 0, 1, 1},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 1}};

        int rowWithMaxOnes = rowWithMax1s(arr, n, m);
        System.out.println("The Row with maximum number of 1's = " + rowWithMaxOnes);

    }

}
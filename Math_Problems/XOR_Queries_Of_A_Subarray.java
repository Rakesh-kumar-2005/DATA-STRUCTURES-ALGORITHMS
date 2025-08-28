package Math_Problems;

public class XOR_Queries_Of_A_Subarray {


    private static int[] xorQueries(int[] arr, int[][] queries) {

        int[] ans = new int[queries.length];
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] ^ arr[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            if (left == 0) {
                ans[i] = prefixSum[right];
            } else {
                ans[i] = prefixSum[right] ^ prefixSum[left - 1];
            }
        }
        return ans;
    }

    private static void displayArray(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}};

        int[] ans = xorQueries(arr, queries);
        System.out.println("The ans array is : ");
        displayArray(ans);

    }

}
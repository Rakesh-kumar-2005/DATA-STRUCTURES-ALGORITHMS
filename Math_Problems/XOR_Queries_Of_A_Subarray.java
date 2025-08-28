package Math_Problems;

/*

Description:
    -> This program answers multiple queries about the XOR of elements in subarrays of a given array.
    -> Each query specifies a range [L, R], and the program efficiently calculates the XOR of 
       all elements from index L to R.
    -> It uses a prefix XOR technique to avoid recalculating the XOR for each query.

Problem Statement:
    Given an array `arr` and multiple queries where each query is a pair [L, R],
    return an array `ans` where ans[i] = XOR of arr[L..R].

Example:
    Input:
        arr = [1, 3, 4, 8]
        queries = [[0,1], [1,2], [0,3]]
    Output:
        ans = [2, 7, 14]
    Explanation:
        Query [0,1] -> 1 ^ 3 = 2
        Query [1,2] -> 3 ^ 4 = 7
        Query [0,3] -> 1 ^ 3 ^ 4 ^ 8 = 14

Approach:
    1. Precompute a prefix XOR array:
        prefix[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]
    2. For each query [L, R]:
        -> If L == 0, result = prefix[R]
        -> Else, result = prefix[R] ^ prefix[L-1]
    3. Store results in the `ans` array and return it.

Helper Method:
    -> displayArray(int[] arr): Prints the contents of an array.

Key Variables:
    -> prefixSum : stores cumulative XOR values for fast lookup.
    -> ans       : stores XOR results for each query.
    -> queries   : list of ranges [L, R].

Time and Space Complexity:
    -> Preprocessing: O(n) for prefix XOR.
    -> Query answering: O(1) per query.
    -> Total Time Complexity: O(n + q), where n = array length, q = number of queries.
    -> Space Complexity: O(n) for prefix array + O(q) for result array.

Conclusion:
    This program efficiently computes XOR values for multiple subarray queries 
    using the prefix XOR optimization.

*/

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

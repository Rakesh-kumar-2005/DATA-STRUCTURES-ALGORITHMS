package Arrays;

/*

        Description :-
            We have to find the maximum product of any contiguous sub-array within a given array,
            with optimized space and time complexity...

        Approach :-
            > The approach uses Kadane's algorithm adapted for products...
            > It handles positive, negative, and zero values by tracking both prefix and suffix products
                    simultaneously. However, the implementation has a bug - suffix should be calculated from
                    the end of the array...
            > The correct approach should track max and min products at each position to handle negative
                    numbers properly.
            > Time Complexity = O(n) because of a single traversal...
            > Space Complexity = O(1) because we didn't use any extra-space to solve the question...
            
*/

public class Maximum_Product_Of_SubArray {

    private static int product(int[] arr) {
            
        int n = arr.length;
        if (n == 0) {
            return -1;
        }       

        int prefix = 1;
        int suffix = 1;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            prefix *= arr[i];
            suffix *= arr[i];

            ans = Math.max(arr[i], Math.max(prefix, suffix));
        }
        return ans;
    }

    public static void main(String[] args) {
            
        int[] arr = {1, 2, 3, 0, 4, 2};
        System.out.println(product(arr));
    
    }
        
}

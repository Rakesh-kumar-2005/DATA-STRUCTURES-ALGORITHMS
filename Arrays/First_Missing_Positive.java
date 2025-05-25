package Arrays;

import java.util.HashSet;

public class First_Missing_Positive {

    private static int firstMissingPositive(int[] arr) {

        HashSet<Integer> st = new HashSet<>();
        int ans = 1;

        for (int i : arr) {
            st.add(i);
        }

        while (st.contains(ans)) {
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 0, 3, - 1, 4, 5, 6};
        System.out.println("First missing positive number in the array is : " + firstMissingPositive(arr));
    }

}
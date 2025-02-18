package Hashing;

import java.util.HashSet;

/*
        Description :-
            We have to find the first missing positive number( num >=1 ) in the arr Array...

        Approach :-
            > we have to iterate through each element in arr and add to the HashSet...
            > Then we do a while loop till the number is inside the set and als0 increment the number by 1 each time...
*/

public class First_Missing_Positive {
    public int firstMissingPositive(int[] arr) {
        HashSet<Integer> st = new HashSet<>();
        for (int i : arr) {
            st.add(i);
        }

        int ans = 1;
        while (st.contains(ans)) {
            ans++;
        }
        return ans;
    }
}

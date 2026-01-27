package BackTracking;
import java.util.ArrayList;

/*
        Description :-
            > A permutation is a rearrangement of the elements of a list in all possible orders.
                For example, the permutations of the array ['a', 'b', 'c'] are:
                abc
                acb
                bac
                bca
                cab
                cba

        Approach :-
            > Recursion: The function permute generates permutations by recursively swapping elements.
            > Base case: When the current index idx is the last index (n-1), it creates a list with the current
                         permutation and adds it to the result list ans.
            > Swapping Elements: Before making the recursive call, swap the current element with each subsequent
                         element (including itself).
            > After the recursive call, swap back to restore the original order. This is essential for backtracking,
                        ensuring other permutations are generated correctly.
            > Backtracking: The swaps before and after the recursive call ensure that the algorithm explores all permutations
                            without permanently altering the input array arr.

*/

public class Permutation {

    private static void permute(char[] arr,int idx,ArrayList<ArrayList<Character>> ans){
            
        int n = arr.length;
        if(idx == n-1){
            ArrayList<Character> temp = new ArrayList<>();
            for(char curr : arr){
                temp.add(curr);
            }
            ans.add(temp);
            return;
        }
            
        for(int i=idx; i<n; i++){
            swap(arr,i,idx);
            permute(arr,idx+1,ans);
            swap(arr,idx,i);
        }
            
    }

    private static void swap(char[] arr,int i, int j){
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }

    public static void main(String[] args) {
            
        char[] arr = {'a','b','c','d'};
        ArrayList<ArrayList<Character>> ans = new ArrayList<>();
        permute(arr,0,ans);

        System.out.println("All the possible permutations are ; ");
        for (ArrayList<Character> an : ans) {
            System.out.println(an);
        }
            
    }
        
}

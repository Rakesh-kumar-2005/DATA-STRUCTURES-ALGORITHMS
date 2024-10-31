package Hashing;
import java.util.HashMap;

/*
        Description :-
            We have to find the largest length of the sub array with the total sum is 0...

        Approach :-
            > Here, we'll use the concept of prefix sum...
            > We'll find the prefix sum for every index and check if the value is present in the HashMap,
                if yes it means all the indices between them has the total sum of 0...
            > So, just compare the length...
            > There's a chance that the whole array has the total sum of 0,
                that's from the beginning put a pair of (0,-1)...
*/

public class Largest_SubArray_with_0_sum {

    private static int largestLengthOfSubArray(int[] arr){
        HashMap<Integer,Integer> mp = new HashMap<>();
        mp.put(0,-1);

        int prefixSum = 0;
        int maxLength = 0;
        for(int i = 0; i < arr.length; i++){
            prefixSum += arr[i];

            if(mp.containsKey(prefixSum)) {
                maxLength = Math.max(i-mp.get(prefixSum),maxLength);
            }else {
                mp.put(prefixSum,i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {10,5,-2,-3,-10};
        System.out.println("The maximum length of the sub array with sum 0 is : " + largestLengthOfSubArray(arr));
    }
}

package Hashing;
import java.util.HashMap;

/*
        Description :-
                Here we have to calculate the number of sub arrays whose summation is equals to the given target value...

        Approach :-
            -> Use a HashMap to Store Prefix Sums:
                > The key idea is to use a hashmap to keep track of the cumulative sums (prefix sums)
                    as you iterate through the array.
                > The hashmap stores the prefix sums and their counts to check how many times a particular sum has occurred...
            > Iterate Through the Array:
                > For each element in the array, add it to the cumulative sum.
                > Check if the difference between the current cumulative sum and the target sum has been seen before.
                    If it has, then there are sub arrays that sum to the target.

*/


public class Sum_Of_Sub_Array_Equal_to_Given_Number {

    private static int noOfSubArraysWithSumEqualToGivenNumber(int[] arr,int target){
        int count = 0;
        int sum = 0;
        HashMap<Integer,Integer> mp = new HashMap<>();
        mp.put(0,1);

        for (int i : arr) {
            sum += i;
            if (mp.containsKey(sum - target)) {
                count += mp.get(sum - target);
            }

            if (mp.containsKey(sum)) {
                mp.put(sum, mp.get(sum) + 1);
            } else mp.put(sum, 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,3,2,4,1};
        int target = 10;
        System.out.println("The number of sub arrays with the summation value " + target + " is = " + noOfSubArraysWithSumEqualToGivenNumber(arr,target));
    }
}

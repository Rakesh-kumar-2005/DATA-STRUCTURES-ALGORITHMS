package Hashing;
import java.util.HashSet;

/*
        Description :-
            Here we need find the maximum length of the consecutive sequence present in an array...

        Approach :-
            -> Use a HashSet for Efficient Lookups:
                > Insert all elements of the array into a hash set. This helps in achieving O(1) time complexity
                  for checking if an element exists in the set.
            -> Identify the Start of a Sequence:
                > Iterate through the set, and for each element, check if it is the start of a sequence.
                An element is considered the start of a sequence if there is no smaller consecutive element present in the set (i.e., num-1 is not in the set).
            -> Calculate the Length of the Sequence:
                > For each starting element, find the length of the consecutive sequence by checking the presence of
                the next elements (num+1, num+2, etc.) in the set.
            -> Track the Maximum Length:
                > Keep track of the maximum sequence length encountered during the iterations.
*/


public class Longest_Consecutive_Sequence_In_An_Array {

    private static int lengthOfTheLargestConsecutiveSequence(int[] arr){
        HashSet<Integer> st = new HashSet<>();
        int maxLength = 0;

        for(int i : arr){
            st.add(i);
        }

        for(int num : st){
            if(!st.contains(num-1)){
                int currNum = num;
                int currlength = 1;

                while (st.contains(currNum+1)){
                    currlength++;
                    currNum++;
                }
                maxLength = Math.max(maxLength,currlength);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,3,2,85,209,567};
        System.out.println("The length of the largest consecutive sequence is = " + lengthOfTheLargestConsecutiveSequence(arr));
    }
}

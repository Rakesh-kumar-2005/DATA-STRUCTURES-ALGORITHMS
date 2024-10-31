package Hashing;
import java.util.HashSet;

/*
        Description :-
            We have to find the intersection of two Array...

        Approach :-
            > we have to iterate through each element in arr1 and add to the HashSet...
            > Then iterate through the arr2 and find the common element and increase a count value...
*/

public class Intersection_Of_2_Arrays {

    private static int interSectionOfArrays(int[] arr1, int[] arr2){
        HashSet<Integer> st = new HashSet<>();

        for (int num : arr1){
            st.add(num);
        }

        int count = 0;
        for (int num : arr2){
            if(st.contains(num)){
                count++;
                st.remove(num);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,32,7,89};
        int[] arr2 = {1,2,3,4323,4,8};

        System.out.println("Total number of common elements in these arrays is = " + interSectionOfArrays(arr1,arr2));
    }
}

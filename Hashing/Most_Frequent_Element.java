package Hashing;
import java.util.HashMap;

/*
        Description :-
            We have to find the most frequent element in an Array(no of elements = n)
            with a better time complexity of O(n) with the help of HashMap...

        Approach :-
            > We can use a hashmap to keep a track of the elements and their frequency...
            > At the end we just need to compare the highest frequency...
*/
public class Most_Frequent_Element {

    private static void frequentElement(int[] arr){
        HashMap<Integer,Integer> mp = new HashMap<>();

        for(int num : arr){
            if(!mp.containsKey(num)) {
                mp.put(num,1);
            }else {
                mp.put(num,mp.get(num) + 1);
            }
        }

        int mostFrequentElement = 0;
        int frequency = 0;

        for(var key : mp.entrySet()){
            if(key.getValue() > frequency){
                frequency = key.getValue();
                mostFrequentElement = key.getKey();
            }
        }

        System.out.println("The most frequented element is = " + mostFrequentElement + " with the frequency of = " + frequency);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,33,444,1,3,3,2,42,13,21,3,12,1,3,2};
        frequentElement(arr);
    }
}

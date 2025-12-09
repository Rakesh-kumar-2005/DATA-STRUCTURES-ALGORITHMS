package Hashing;

/*
    Description:
        Following program demonstrates the solution to the "Anagram Checking" problem 
        using HashMap-based Character Frequency Approach...

    Problem Statement:
        -> Determine if two given strings are anagrams of each other...
        -> An anagram is a word or phrase formed by rearranging the letters of another word or phrase...
        -> Two strings are considered anagrams if they have the same characters with identical frequencies...
        -> Case-sensitive character matching is performed...

    Approach:
        > HashMap-based Character Frequency Comparison:
            i. Create a frequency map for the first string...
            ii. Iterate through the second string and decrement character frequencies...
            iii. Check for complete character frequency match...
            iv. Handle edge cases like different string lengths...

    Algorithm Steps:
        -> Initial Validation:
            1. Check if strings have equal length...
            2. Return false if lengths differ...
        -> First String Processing:
            1. Create a HashMap to store character frequencies...
            2. Iterate through first string characters...
            3. Increment frequency for each character...
        -> Second String Validation:
            1. Iterate through second string characters...
            2. Check if character exists in frequency map...
            3. Decrement character frequency...
            4. Remove character if frequency reaches zero...
        -> Final Verification:
            1. Ensure all characters are processed...
            2. Return true if map becomes empty...

    Key Characteristics:
        -> Efficient O(n) time complexity for anagram checking...
        -> Uses HashMap for dynamic character frequency tracking...
        -> Handles strings of equal length...
        -> Supports case-sensitive comparison...

        > Frequency Tracking Mechanism:
            -> Builds frequency map for first string...
            -> Decrements frequencies during second string iteration...
            -> Removes characters with zero frequency...

    Time and Space Complexity:
        -> Time Complexity: O(n) where n is the length of input strings...
        -> Space Complexity: O(k) where k is the number of unique characters...
*/

import java.util.HashMap;

public class Anagram_Checking {

    private static HashMap<Character,Integer> makeMap(String st){
        
        HashMap<Character,Integer> mp = new HashMap<>();
        
        for (int i=0; i< st.length(); i++){
            char ch = st.charAt(i);
            
            if(!mp.containsKey(ch)){
                mp.put(ch,1);
            }else{
                mp.put(ch,mp.get(ch)+1);
            }
        }
        return mp;
    }
    
    public static boolean isAnagram(String s,String t){
        if(s.length() != t.length()) return false;
        
        HashMap<Character, Integer> mp = makeMap(s);
        
        for (int i=0; i<t.length(); i++){
           char ch = t.charAt(i);
            
           if(!mp.containsKey(ch)) {
               return false;
           }else {
               mp.put(ch,mp.get(ch)-1);
           }
        
           if(mp.get(ch) == 0) mp.remove(ch);
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Anagram checking - " + isAnagram("silent","lis"));
    }
    
}

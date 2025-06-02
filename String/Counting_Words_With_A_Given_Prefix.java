package String;

/*

Description:
    -> This program counts how many words in a given list start with a specified prefix...
    -> It demonstrates a simple and effective use of Java's built-in `startsWith()` string method...

Problem Statement:
    -> Given an array of strings `words` and a string `pref` representing a prefix...
    -> Return the number of strings in `words` that start with `pref`...

Approach:
    > Iterative Traversal with Prefix Matching:
        -> Iterate through each word in the input array...
        -> Check if the current word starts with the given prefix using `startsWith()`...
        -> If it does, increment a counter...

Algorithm Steps:
    1. Initialize a counter variable `count` to 0...
    2. For each word in the `words` array:
        a. Check if the word starts with the prefix `pref`...
        b. If yes, increment the counter by 1...
    3. After the loop, return the value of `count`...

Key Characteristics:
    -> Efficiently checks prefix match using native Java functionality...
    -> Avoids any complex string manipulation or additional data structures...
    -> Case-sensitive matching as per Java's `startsWith()` method...

Time and Space Complexity:
    -> Time Complexity: O(n * k), where n is the number of words and k is the length of the prefix...
    -> Space Complexity: O(1), as no additional data structures are used for storage...

Demonstration:
    -> Given the input array: {"pay", "attention", "practice", "attend"} and prefix: "at"...
    -> The program checks each word and counts those that begin with "at"...
    -> Output: 2 (for "attention" and "attend")...

*/

public class Counting_Words_With_A_Given_Prefix {

    private static int prefixCount(String[] words, String pref) {

        int count = 0;

        for (String currWord : words) {

            if (currWord.startsWith(pref)) {
                count++;
            }

        }

        return count;
    }

    public static void main(String[] args) {

        String[] words = {"pay", "attention", "practice", "attend"};
        String pref = "at";

        System.out.println("The number of words with the given prefix is : " + prefixCount(words, pref));

    }

}

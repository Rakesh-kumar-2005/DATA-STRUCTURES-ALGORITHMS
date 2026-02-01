package Binary_Search;

/*

Description:
  This program finds the **smallest character strictly greater than a given target**
  from a sorted array of lowercase letters using **Binary Search**...
  If such a character does not exist, the search **wraps around** and returns the first letter...

Problem Statement:
  -> Given:
       • Sorted character array letters[]...
       • Target character target...
  -> Return:
       • The smallest letter > target...
       • If none exists, return letters[0] (circular behavior)...

Core Idea:
  -> Since the array is sorted, we can apply Binary Search...
  -> We must find the **first element greater than target**...
  -> This is also called:
       "Upper Bound" or "Next Greater Element"...

Binary Search Strategy:
  -> Initialize:
       left = 0...
       right = n-1...
  -> While left <= right:
       • Find mid...
       • If letters[mid] <= target:
             move right (left = mid + 1)...
       • Else:
             move left (right = mid - 1)...
  -> After loop:
       left points to first index with value > target...

Wrap Around Logic:
  -> If target >= all letters:
       left becomes n...
  -> We use:
       letters[left % letters.length]...
  -> This converts:
       n % n = 0...
  -> So we return first letter...
  -> Achieves circular array behavior...

Method 1: nextGreatestLetter():
  -> Performs binary search...
  -> Narrows search space each iteration...
  -> Finds correct insertion position...
  -> Returns next greater character using modulo...

Method 2: arrayToString():
  -> Helper method for pretty printing...
  -> Formats characters like ['a', 'b', 'c']...
  -> Used only for demonstration...

Algorithm Steps:
  -> Step 1: Set left=0, right=n-1...
  -> Step 2: Compute mid safely...
  -> Step 3: Compare with target...
  -> Step 4: Adjust pointers...
  -> Step 5: Loop until left > right...
  -> Step 6: Return letters[left % n]...

Example Walkthrough:
  -> letters = ['c','f','j'], target = 'j'...
       All letters <= target...
       left becomes 3...
       3 % 3 = 0...
       Return 'c'...

Edge Cases Covered:
  -> Target smaller than first letter...
  -> Target equals existing letter...
  -> Target greater than all letters...
  -> Duplicate letters...
  -> Single element array...
  -> Large arrays...

Key Observations:
  -> We need strictly greater (>) not >= ...
  -> Binary search gives O(log n) performance...
  -> Modulo ensures circular handling...
  -> Using:
       mid = left + (right-left)/2
     prevents integer overflow...

Time Complexity:
  -> O(log n)...
  -> Search space halves each step...

Space Complexity:
  -> O(1)...
  -> Only pointer variables used...

Important Concepts:
  -> Binary Search...
  -> Upper Bound...
  -> Circular arrays...
  -> Modulo arithmetic...

Interview Insight:
  -> Classic "next greater element in sorted array" problem...
  -> Efficient alternative to linear scan O(n)...
  -> Common LeetCode/DSA binary search pattern...

Final Verdict:
  -> Clean and optimal solution...
  -> Logarithmic time complexity...
  -> Handles wrap-around elegantly...
  -> Recommended approach for sorted search problems...

*/

public class Find_Smallest_Letter_Greater_Than_Target {

    private static char nextGreatestLetter(char[] letters, char target) {

        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return letters[left % letters.length];

    }

    private static String arrayToString(char[] arr) {

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < arr.length; i++) {
            sb.append("'").append(arr[i]).append("'");
            if (i < arr.length - 1) sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   FIND SMALLEST LETTER GREATER THAN TARGET (Binary Search)   ║");
        System.out.println("║  Find the smallest character greater than target             ║");
        System.out.println("║  Wraps around if no greater character exists                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Search - Target in Middle ===");
        char[] letters1 = {'c', 'f', 'j'};
        char target1 = 'a';
        System.out.println("Letters: " + arrayToString(letters1));
        System.out.println("Target: '" + target1 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Letters: [c, f, j]");
        System.out.println("  Target 'a' < 'c' (first letter)");
        System.out.println("  → Smallest greater letter: 'c'\n");

        char result1 = nextGreatestLetter(letters1, target1);
        System.out.println("✓ Result: '" + result1 + "'");
        System.out.println("  Expected: 'c'");
        System.out.println("  Status: " + (result1 == 'c' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Target Between Letters ===");
        char[] letters2 = {'c', 'f', 'j'};
        char target2 = 'c';
        System.out.println("Letters: " + arrayToString(letters2));
        System.out.println("Target: '" + target2 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Letters: [c, f, j]");
        System.out.println("  Target 'c' = letters[0]");
        System.out.println("  Need strictly greater → 'f'\n");

        char result2 = nextGreatestLetter(letters2, target2);
        System.out.println("✓ Result: '" + result2 + "'");
        System.out.println("  Expected: 'f'");
        System.out.println("  Status: " + (result2 == 'f' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Wrap Around (Target > All Letters) ===");
        char[] letters3 = {'c', 'f', 'j'};
        char target3 = 'j';
        System.out.println("Letters: " + arrayToString(letters3));
        System.out.println("Target: '" + target3 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Letters: [c, f, j]");
        System.out.println("  Target 'j' >= all letters");
        System.out.println("  Wrap around to first letter: 'c'\n");
        System.out.println("Note: Uses modulo (%) to wrap to start\n");

        char result3 = nextGreatestLetter(letters3, target3);
        System.out.println("✓ Result: '" + result3 + "'");
        System.out.println("  Expected: 'c'");
        System.out.println("  Status: " + (result3 == 'c' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Duplicate Letters ===");
        char[] letters4 = {'a', 'a', 'b', 'b', 'c', 'c'};
        char target4 = 'b';
        System.out.println("Letters: " + arrayToString(letters4));
        System.out.println("Target: '" + target4 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Array has duplicates: [a, a, b, b, c, c]");
        System.out.println("  Target 'b' appears twice");
        System.out.println("  First letter > 'b' is 'c'\n");

        char result4 = nextGreatestLetter(letters4, target4);
        System.out.println("✓ Result: '" + result4 + "'");
        System.out.println("  Expected: 'c'");
        System.out.println("  Status: " + (result4 == 'c' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Letter Array ===");
        char[] letters5 = {'x'};
        char target5 = 'm';
        System.out.println("Letters: " + arrayToString(letters5));
        System.out.println("Target: '" + target5 + "'");
        System.out.println("\nEdge Case:");
        System.out.println("  Only one letter available");
        System.out.println("  Always returns that letter (wrap around)\n");

        char result5 = nextGreatestLetter(letters5, target5);
        System.out.println("✓ Result: '" + result5 + "'");
        System.out.println("  Expected: 'x'");
        System.out.println("  Status: " + (result5 == 'x' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Alphabet Subset ===");
        char[] letters6 = {'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
        char target6 = 'e';
        System.out.println("Letters: " + arrayToString(letters6));
        System.out.println("Target: '" + target6 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Multiple 'e' and 'n' letters");
        System.out.println("  Target 'e' matches first group");
        System.out.println("  Next greater letter: 'n'\n");

        char result6 = nextGreatestLetter(letters6, target6);
        System.out.println("✓ Result: '" + result6 + "'");
        System.out.println("  Expected: 'n'");
        System.out.println("  Status: " + (result6 == 'n' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Target Before All Letters ===");
        char[] letters7 = {'m', 'n', 'o', 'p', 'q'};
        char target7 = 'a';
        System.out.println("Letters: " + arrayToString(letters7));
        System.out.println("Target: '" + target7 + "'");
        System.out.println("\nBinary Search Process:");
        System.out.println("  Target 'a' < 'm' (first letter)");
        System.out.println("  Binary search points to index 0");
        System.out.println("  Result: 'm'\n");

        char result7 = nextGreatestLetter(letters7, target7);
        System.out.println("✓ Result: '" + result7 + "'");
        System.out.println("  Expected: 'm'");
        System.out.println("  Status: " + (result7 == 'm' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Full Alphabet Scenario ===");
        char[] letters8 = {'a', 'd', 'g', 'k', 'p', 's', 'w', 'z'};
        char target8 = 'r';
        System.out.println("Letters: " + arrayToString(letters8));
        System.out.println("Target: '" + target8 + "'");
        System.out.println("\nBinary Search Visualization:");
        System.out.println("  [a, d, g, k, p, s, w, z]");
        System.out.println("              ↑     ↑");
        System.out.println("           target result");
        System.out.println("  'r' falls between 'p' and 's'");
        System.out.println("  Next greater: 's'\n");

        char result8 = nextGreatestLetter(letters8, target8);
        System.out.println("✓ Result: '" + result8 + "'");
        System.out.println("  Expected: 's'");
        System.out.println("  Status: " + (result8 == 's' ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Binary Search Approach:                                     ║");
        System.out.println("║    • Divides search space in half each iteration             ║");
        System.out.println("║    • If letters[mid] <= target: search right half            ║");
        System.out.println("║    • If letters[mid] > target: search left half              ║");
        System.out.println("║    • Loop ends when left > right                             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Implementation Detail:                                  ║");
        System.out.println("║    return letters[left % letters.length]                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Modulo Operator (%)?                                    ║");
        System.out.println("║    • When target >= all letters, left = length               ║");
        System.out.println("║    • left % length wraps to index 0                          ║");
        System.out.println("║    • Achieves circular array behavior                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: letters = [c, f, j], target = 'z'                  ║");
        System.out.println("║    • After binary search: left = 3                           ║");
        System.out.println("║    • 3 % 3 = 0 → returns letters[0] = 'c'                    ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Search Process Breakdown:                                   ║");
        System.out.println("║    1. Initialize: left=0, right=length-1                     ║");
        System.out.println("║    2. Find mid: mid = left + (right-left)/2                  ║");
        System.out.println("║    3. Compare and adjust pointers                            ║");
        System.out.println("║    4. Continue until left > right                            ║");
        System.out.println("║    5. Return with wrap-around handling                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Not mid = (left+right)/2?                               ║");
        System.out.println("║    • Prevents integer overflow for large indices             ║");
        System.out.println("║    • left + (right-left)/2 is safer                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Time Complexity:  O(log n) - Binary search                  ║");
        System.out.println("║  Space Complexity: O(1) - Constant space                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}

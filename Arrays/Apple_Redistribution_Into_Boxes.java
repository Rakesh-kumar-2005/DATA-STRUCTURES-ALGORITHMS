package Arrays;

/*

Description:
  This program solves the “Apple Redistribution Into Boxes” problem using a greedy capacity-based strategy...
  The goal is to determine the minimum number of boxes required to store all apples across multiple packs...

Problem Statement:
  -> You are given two arrays:
       * apple[]  → number of apples in each pack...
       * capacity[] → capacity of each available box...
  -> All apples from all packs must be placed into boxes...
  -> A box can store apples up to its capacity...
  -> Boxes can be used in any order, and apples from different packs can go into the same box...
  -> Objective: Find the minimum number of boxes required to store all the apples...

Key Insight:
  -> Since we are minimizing number of boxes, we must use the largest-capacity boxes first...
  -> Sorting capacities in ascending order allows us to pick from the end (largest values) greedily...
  -> As soon as the total capacity used ≥ total apples, we stop...

Approach:
  > Step 1: Compute total apples from the array...
       total = sum(apple[i])...
  > Step 2: Sort the capacity[] array...
       After sorting, the largest boxes appear at the end...
  > Step 3: Greedy selection:
       Start from the box with the highest capacity and accumulate capacity...
       For each box used:
         accumulatedCapacity += boxCapacity...
         If accumulatedCapacity ≥ totalApples:
             return numberOfBoxesUsed...
  > Step 4: If all boxes are used but capacity still insufficient:
       return capacity.length (though practically sorted capacities always suffice)...

Algorithm Steps (Clean Overview):
  -> Compute total apples...
  -> Sort capacity array...
  -> Initialize accumulated = 0...
  -> Traverse from largest to smallest capacity:
       accumulated += capacity[i]...
       if accumulated >= total:
            return boxesUsed...
  -> Default return: capacity.length...

Edge Cases:
  -> Single box with enough capacity:
       return 1...
  -> All boxes too small individually but sufficient collectively:
       must use all boxes...
  -> Many tiny packs but few large boxes:
       greedy still optimal...
  -> apple array with single element:
       trivial capacity check...
  -> capacity array with a single element:
       must check if that single box can hold all apples...

Example:
  apple[]    = {1, 3, 2} → total = 6...
  capacity[] = {4, 3, 1, 5, 2} → after sort: [1,2,3,4,5]...
  Choose from largest:
     5 → total=5 (not enough)...
     4 → total=9 (enough)...
  Minimum boxes = 2...

Complexity Analysis:
  -> Time Complexity: O(n log n) due to sorting of capacity array...
  -> Space Complexity: O(1) since sorting is done in place and only scalar variables used...

Why Greedy Works:
  -> Using larger capacity boxes first minimizes the number of boxes immediately...
  -> No smaller box can replace a larger box to reduce count...
  -> This is a classic “minimize item count to reach sum” scenario when all items are positive and unbounded...

Summary:
  -> Greedy strategy using sorted capacities...
  -> Always pick the biggest remaining box until total apple requirement is met...
  -> Efficient, optimal, and simple to implement...

*/

import java.util.Arrays;

public class Apple_Redistribution_Into_Boxes {

    private static int minimumBoxes(int[] apple, int[] capacity) {

        int total = 0;
        for (int i : apple) {
            total += i;
        }

        Arrays.sort(capacity);

        int totalCapacity = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            totalCapacity += capacity[i];

            if (totalCapacity >= total) {
                return capacity.length - i;
            }
        }
        return capacity.length;
    }

     private static int getTotalApples(int[] apple) {
        int total = 0;
        for (int a : apple) {
            total += a;
        }
        return total;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        APPLE REDISTRIBUTION INTO BOXES                       ║");
        System.out.println("║  Find minimum boxes needed to store all apples               ║");
        System.out.println("║  Strategy: Greedy - Use largest capacity boxes first         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        int[] apple1 = {1, 3, 2};
        int[] capacity1 = {4, 3, 1, 5, 2};
        System.out.println("Apples per pack: " + Arrays.toString(apple1));
        System.out.println("Total apples: " + getTotalApples(apple1) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity1));
        System.out.println("\nStrategy:");
        System.out.println("  Sort boxes by capacity: [1, 2, 3, 4, 5]");
        System.out.println("  Use largest first:");
        System.out.println("    - Box[5]: 5 apples → total = 5/6 ✗");
        System.out.println("    - Box[4]: 4 apples → total = 9/6 ✓ (enough!)");
        int result1 = minimumBoxes(apple1, capacity1);
        System.out.println("\n✓ Minimum boxes needed: " + result1);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Exact Fit ===");
        int[] apple2 = {5, 5, 5};
        int[] capacity2 = {2, 4, 2, 7};
        System.out.println("Apples per pack: " + Arrays.toString(apple2));
        System.out.println("Total apples: " + getTotalApples(apple2) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity2));
        System.out.println("\nStrategy:");
        System.out.println("  Sort boxes: [2, 2, 4, 7]");
        System.out.println("  Use largest first:");
        System.out.println("    - Box[7]: 7 apples → total = 7/15 ✗");
        System.out.println("    - Box[4]: 4 apples → total = 11/15 ✗");
        System.out.println("    - Box[2]: 2 apples → total = 13/15 ✗");
        System.out.println("    - Box[2]: 2 apples → total = 15/15 ✓");
        int result2 = minimumBoxes(apple2, capacity2);
        System.out.println("\n✓ Minimum boxes needed: " + result2);
        System.out.println("  Expected: 4 (need all boxes)");
        System.out.println("  Status: " + (result2 == 4 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Single Large Box ===");
        int[] apple3 = {2, 3, 4};
        int[] capacity3 = {20, 5, 3};
        System.out.println("Apples per pack: " + Arrays.toString(apple3));
        System.out.println("Total apples: " + getTotalApples(apple3) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity3));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 9 apples");
        System.out.println("  Largest box has capacity 20");
        System.out.println("  One box is enough! 20 > 9 ✓");
        int result3 = minimumBoxes(apple3, capacity3);
        System.out.println("\n✓ Minimum boxes needed: " + result3);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result3 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Many Small Apples ===");
        int[] apple4 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] capacity4 = {3, 4, 5};
        System.out.println("Apples per pack: " + Arrays.toString(apple4));
        System.out.println("Total apples: " + getTotalApples(apple4) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity4));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 10 apples");
        System.out.println("  Use largest boxes:");
        System.out.println("    - Box[5]: 5 apples → total = 5/10 ✗");
        System.out.println("    - Box[4]: 4 apples → total = 9/10 ✗");
        System.out.println("    - Box[3]: 3 apples → total = 12/10 ✓");
        int result4 = minimumBoxes(apple4, capacity4);
        System.out.println("\n✓ Minimum boxes needed: " + result4);
        System.out.println("  Expected: 3 (need all boxes)");
        System.out.println("  Status: " + (result4 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Apple, Single Box ===");
        int[] apple5 = {10};
        int[] capacity5 = {15};
        System.out.println("Apples per pack: " + Arrays.toString(apple5));
        System.out.println("Total apples: " + getTotalApples(apple5) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity5));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 10 apples");
        System.out.println("  Only one box with capacity 15");
        System.out.println("  15 > 10 ✓ (sufficient)");
        int result5 = minimumBoxes(apple5, capacity5);
        System.out.println("\n✓ Minimum boxes needed: " + result5);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result5 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Large Numbers ===");
        int[] apple6 = {100, 200, 150};
        int[] capacity6 = {50, 100, 200, 150};
        System.out.println("Apples per pack: " + Arrays.toString(apple6));
        System.out.println("Total apples: " + getTotalApples(apple6) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity6));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 450 apples");
        System.out.println("  Sorted boxes: [50, 100, 150, 200]");
        System.out.println("  Use largest:");
        System.out.println("    - Box[200]: 200 → total = 200/450 ✗");
        System.out.println("    - Box[150]: 150 → total = 350/450 ✗");
        System.out.println("    - Box[100]: 100 → total = 450/450 ✓");
        int result6 = minimumBoxes(apple6, capacity6);
        System.out.println("\n✓ Minimum boxes needed: " + result6);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result6 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: All Equal Capacity ===");
        int[] apple7 = {3, 3, 3, 3, 3};
        int[] capacity7 = {5, 5, 5, 5};
        System.out.println("Apples per pack: " + Arrays.toString(apple7));
        System.out.println("Total apples: " + getTotalApples(apple7) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity7));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 15 apples");
        System.out.println("  All boxes have capacity 5");
        System.out.println("  Need: 15 / 5 = 3 boxes");
        int result7 = minimumBoxes(apple7, capacity7);
        System.out.println("\n✓ Minimum boxes needed: " + result7);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result7 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 8: Minimal Boxes Available ===");
        int[] apple8 = {7, 8, 9};
        int[] capacity8 = {30};
        System.out.println("Apples per pack: " + Arrays.toString(apple8));
        System.out.println("Total apples: " + getTotalApples(apple8) + " apples");
        System.out.println("Box capacities: " + Arrays.toString(capacity8));
        System.out.println("\nStrategy:");
        System.out.println("  Total = 24 apples");
        System.out.println("  Only one box available with capacity 30");
        System.out.println("  30 > 24 ✓ (just enough)");
        int result8 = minimumBoxes(apple8, capacity8);
        System.out.println("\n✓ Minimum boxes needed: " + result8);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result8 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM SUMMARY                                           ║");
        System.out.println("║  1. Calculate total apples needed                            ║");
        System.out.println("║  2. Sort boxes by capacity                                   ║");
        System.out.println("║  3. Greedy: Pick largest boxes until total is met            ║");
        System.out.println("║  Time Complexity: O(n log n) - dominated by sorting          ║");
        System.out.println("║  Space Complexity: O(1) - in-place sorting                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    
    }
    
}

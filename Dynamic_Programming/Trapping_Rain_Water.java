package Dynamic_Programming;

public class Trapping_Rain_Water {

    private static int trap(int[] height) {

        int n = height.length;
        int totalWater = 0;

        int leftMax = 0;
        int rightMax = 0;

        int start = 0;
        int end = n - 1;

        while (start < end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);

            if (leftMax < rightMax) {
                totalWater += leftMax - height[start];
                start++;
            } else {
                totalWater += rightMax - height[end];
                end--;
            }

        }

        return totalWater;

    }

    private static String arrayToString(int[] arr) {

        if (arr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              TRAPPING RAIN WATER                             ║");
        System.out.println("║  Calculate total units of water trapped after raining        ║");
        System.out.println("║  Water trapped = min(leftMax, rightMax) - current height     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Classic Valley ===");
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Heights: " + arrayToString(height1));
        System.out.println("\nVisualization (# = bar, ~ = water):");
        System.out.println("          #");
        System.out.println("      #~~~#");
        System.out.println("  #~~#~#~#~#");
        System.out.println("  #~#~#~#~#~#");
        System.out.println("Water trapped at each position:");
        System.out.println("  Position 2: min(1,3) - 0 = 1");
        System.out.println("  Position 4: min(2,3) - 1 = 1");
        System.out.println("  Position 5: min(1,3) - 0 = 1");
        System.out.println("  Position 6: min(1,3) - 1 = 0");
        System.out.println("  ... and more");
        System.out.println("\nTotal: 6 units\n");

        int result1 = trap(height1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result1 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Simple Container ===");
        int[] height2 = {3, 0, 2};
        System.out.println("Heights: " + arrayToString(height2));
        System.out.println("\nVisualization:");
        System.out.println("  #   #");
        System.out.println("  #~#~#");
        System.out.println("Water trapped: 3 - 0 = 3 units\n");

        int result2 = trap(height2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result2 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: No Water Trapped ===");
        int[] height3 = {0, 1, 2, 3, 4};
        System.out.println("Heights: " + arrayToString(height3));
        System.out.println("\nVisualization (all ascending):");
        System.out.println("            #");
        System.out.println("        #");
        System.out.println("    #");
        System.out.println("  #");
        System.out.println("#");
        System.out.println("\nNo valleys → no water trapped\n");

        int result3 = trap(height3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result3 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: All Descending ===");
        int[] height4 = {4, 3, 2, 1, 0};
        System.out.println("Heights: " + arrayToString(height4));
        System.out.println("\nVisualization (all descending):");
        System.out.println("#");
        System.out.println("  #");
        System.out.println("    #");
        System.out.println("      #");
        System.out.println("        #");
        System.out.println("\nNo valleys → no water trapped\n");

        int result4 = trap(height4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result4 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Multiple Valleys ===");
        int[] height5 = {4, 2, 0, 3, 2, 5};
        System.out.println("Heights: " + arrayToString(height5));
        System.out.println("\nVisualization:");
        System.out.println("  #       #");
        System.out.println("  #~#~#~#~#");
        System.out.println("  #~#~#~#~#");
        System.out.println("  #~#~#~#~#");
        System.out.println("Water trapped:");
        System.out.println("  Between 4 and 3: 1 unit");
        System.out.println("  Between 3 and 5: (min(3,5)-2) = 1 unit");
        System.out.println("  At position 0: 0");
        System.out.println("  Total: 7 units\n");

        int result5 = trap(height5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result5 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Single Element ===");
        int[] height6 = {5};
        System.out.println("Heights: " + arrayToString(height6));
        System.out.println("\nOnly one bar → no water trapped\n");

        int result6 = trap(height6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 0");
        System.out.println("  Status: " + (result6 == 0 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Deep Valley ===");
        int[] height7 = {5, 0, 5};
        System.out.println("Heights: " + arrayToString(height7));
        System.out.println("\nVisualization:");
        System.out.println("  #~~~#");
        System.out.println("  #~~~#");
        System.out.println("  #~~~#");
        System.out.println("  #~~~#");
        System.out.println("  #~~~#");
        System.out.println("Water trapped: min(5,5) - 0 = 5 units\n");

        int result7 = trap(height7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result7 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Calculate total water trapped between bars         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Key Insight:                                                ║");
        System.out.println("║    Water at position i = min(leftMax, rightMax) - height[i]  ║");
        System.out.println("║    leftMax = maximum height to the left of i                 ║");
        System.out.println("║    rightMax = maximum height to the right of i               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two-Pointer Approach:                                       ║");
        System.out.println("║    Use pointers from both ends moving towards center         ║");
        System.out.println("║    Track leftMax and rightMax as we move                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    start = 0, end = n-1                                      ║");
        System.out.println("║    leftMax = 0, rightMax = 0                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║    while (start < end):                                      ║");
        System.out.println("║      leftMax = max(leftMax, height[start])                   ║");
        System.out.println("║      rightMax = max(rightMax, height[end])                   ║");
        System.out.println("║                                                              ║");
        System.out.println("║      if (leftMax < rightMax):                                ║");
        System.out.println("║        Water trapped at start = leftMax - height[start]      ║");
        System.out.println("║        start++                                               ║");
        System.out.println("║      else:                                                   ║");
        System.out.println("║        Water trapped at end = rightMax - height[end]         ║");
        System.out.println("║        end--                                                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Move the Smaller Side?                                  ║");
        System.out.println("║    If leftMax < rightMax:                                    ║");
        System.out.println("║      Water at start is limited by leftMax (smaller)          ║");
        System.out.println("║      We can safely calculate it and move start pointer       ║");
        System.out.println("║    If rightMax <= leftMax:                                   ║");
        System.out.println("║      Water at end is limited by rightMax (smaller/equal)     ║");
        System.out.println("║      We can safely calculate it and move end pointer         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: height = [0,1,0,2,1,0,1,3,2,1,2,1]                 ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Initial: start=0, end=11, leftMax=0, rightMax=0             ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: leftMax=0, rightMax=1, leftMax < rightMax           ║");
        System.out.println("║    Water at 0: 0 - 0 = 0, start=1                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: leftMax=1, rightMax=1, leftMax = rightMax           ║");
        System.out.println("║    Water at 11: 1 - 1 = 0, end=10                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 3: leftMax=1, rightMax=2, leftMax < rightMax           ║");
        System.out.println("║    Water at 1: 1 - 1 = 0, start=2                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 4: leftMax=1, rightMax=2, leftMax < rightMax           ║");
        System.out.println("║    Water at 2: 1 - 0 = 1, start=3                            ║");
        System.out.println("║    ... continues ...                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Correctness:                                                ║");
        System.out.println("║    We don't need to know exact rightMax when moving left     ║");
        System.out.println("║    Because leftMax is smaller, water is limited by leftMax   ║");
        System.out.println("║    Similar logic applies when moving right                   ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Single pass with two pointers               ║");
        System.out.println("║    Space: O(1) - Only pointers and variables                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
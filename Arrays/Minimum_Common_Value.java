package Arrays;

public class Minimum_Common_Value {

    private static int getCommon(int[] nums1, int[] nums2) {
        int left = 0;
        int right = 0;

        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] == nums2[right]) {
                return nums1[left];
            } else if (nums1[left] > nums2[right]) {
                right++;
            } else {
                left++;
            }
        }

        return - 1;
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
        System.out.println("║          MINIMUM COMMON VALUE IN TWO ARRAYS                  ║");
        System.out.println("║  Find smallest integer common to both sorted arrays          ║");
        System.out.println("║  Return -1 if no common element exists                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Common Element Exists ===");
        int[] nums1_1 = {1, 2, 3};
        int[] nums2_1 = {2, 4};
        System.out.println("Array 1: " + arrayToString(nums1_1));
        System.out.println("Array 2: " + arrayToString(nums2_1));
        System.out.println("\nTwo-pointer traversal:");
        System.out.println("  Compare 1 and 2: 1 < 2 → move left pointer");
        System.out.println("  Compare 2 and 2: 2 == 2 → found common!");
        System.out.println("\nMinimum common: 2\n");

        int result1 = getCommon(nums1_1, nums2_1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result1 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: No Common Element ===");
        int[] nums1_2 = {1, 2, 3};
        int[] nums2_2 = {4, 5, 6};
        System.out.println("Array 1: " + arrayToString(nums1_2));
        System.out.println("Array 2: " + arrayToString(nums2_2));
        System.out.println("\nNo overlap between arrays");
        System.out.println("All elements in nums1 < all elements in nums2\n");

        int result2 = getCommon(nums1_2, nums2_2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result2 == - 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Multiple Common Elements ===");
        int[] nums1_3 = {1, 2, 3, 6};
        int[] nums2_3 = {2, 3, 4, 5};
        System.out.println("Array 1: " + arrayToString(nums1_3));
        System.out.println("Array 2: " + arrayToString(nums2_3));
        System.out.println("\nCommon elements: 2, 3");
        System.out.println("Return smallest: 2\n");

        int result3 = getCommon(nums1_3, nums2_3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 2");
        System.out.println("  Status: " + (result3 == 2 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: First Element Common ===");
        int[] nums1_4 = {1, 3, 5};
        int[] nums2_4 = {1, 2, 4};
        System.out.println("Array 1: " + arrayToString(nums1_4));
        System.out.println("Array 2: " + arrayToString(nums2_4));
        System.out.println("\nFirst element match immediately");
        System.out.println("Return: 1\n");

        int result4 = getCommon(nums1_4, nums2_4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 1");
        System.out.println("  Status: " + (result4 == 1 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: Single Element Arrays ===");
        int[] nums1_5 = {5};
        int[] nums2_5 = {5};
        System.out.println("Array 1: " + arrayToString(nums1_5));
        System.out.println("Array 2: " + arrayToString(nums2_5));
        System.out.println("\nBoth contain only 5");
        System.out.println("Return: 5\n");

        int result5 = getCommon(nums1_5, nums2_5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result5 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Different Sizes ===");
        int[] nums1_6 = {1, 2, 3, 4, 5, 6, 7};
        int[] nums2_6 = {5, 8, 9};
        System.out.println("Array 1: " + arrayToString(nums1_6));
        System.out.println("Array 2: " + arrayToString(nums2_6));
        System.out.println("\nCommon element: 5\n");

        int result6 = getCommon(nums1_6, nums2_6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result6 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 7: Last Element Common ===");
        int[] nums1_7 = {1, 3, 5, 7};
        int[] nums2_7 = {2, 4, 6, 7};
        System.out.println("Array 1: " + arrayToString(nums1_7));
        System.out.println("Array 2: " + arrayToString(nums2_7));
        System.out.println("\nCommon element at end: 7\n");

        int result7 = getCommon(nums1_7, nums2_7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result7 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find minimum common element in two sorted arrays   ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Two-Pointer Technique:                                      ║");
        System.out.println("║    Both arrays are sorted → use two pointers                 ║");
        System.out.println("║    left pointer for nums1, right pointer for nums2           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    left = 0, right = 0                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    while (left < n1 && right < n2):                          ║");
        System.out.println("║      if (nums1[left] == nums2[right]):                       ║");
        System.out.println("║        return nums1[left]  // Found common!                  ║");
        System.out.println("║      else if (nums1[left] < nums2[right]):                   ║");
        System.out.println("║        left++  // Advance smaller value                      ║");
        System.out.println("║      else:                                                   ║");
        System.out.println("║        right++  // Advance smaller value                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    return -1  // No common element found                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why This Works:                                             ║");
        System.out.println("║    • Arrays are sorted in ascending order                    ║");
        System.out.println("║    • If nums1[left] < nums2[right]:                          ║");
        System.out.println("║      nums1[left] cannot match anything in nums2[right:]      ║");
        System.out.println("║      → safe to advance left pointer                          ║");
        System.out.println("║    • Similar logic for nums1[left] > nums2[right]            ║");
        System.out.println("║    • First match found is the minimum (sorted property)      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: nums1=[1,2,3], nums2=[2,4]                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Step 1: left=0, right=0                                   ║");
        System.out.println("║      nums1[0]=1, nums2[0]=2                                  ║");
        System.out.println("║      1 < 2 → left++                                          ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Step 2: left=1, right=0                                   ║");
        System.out.println("║      nums1[1]=2, nums2[0]=2                                  ║");
        System.out.println("║      2 == 2 → return 2 ✓                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Advantages Over Other Approaches:                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  vs HashSet (O(n+m) time, O(n) space):                       ║");
        System.out.println("║    • Two-pointer: O(1) space                                 ║");
        System.out.println("║    • Leverages sorted property                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  vs Binary Search (O(n log m)):                              ║");
        System.out.println("║    • Two-pointer: O(n+m) simpler logic                       ║");
        System.out.println("║    • Single pass through both arrays                         ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n + m) - Single pass through both arrays         ║");
        System.out.println("║    Space: O(1) - Only two pointer variables                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

}
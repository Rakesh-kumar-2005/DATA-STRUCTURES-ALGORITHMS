package Binary_Search;

/*
        Description :-
            We have to check for the occurrence of the given target in the rotated sorted Array...
            But we have duplicates elements in the array...

        Approach :-
            > So,the typical left and right pointer,then iterate until left <= right...
            > then we have to check the sorted part by checking the comparing the left
                with the mid value and after that check for the duplicate elements and
                decrease our search space ,then check for the target if it lies between
                that section and update left and right value according to it...
*/

public class Search_in_Rotated_Sorted_Array_II {

    private static boolean search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return true;

            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                left++;
                right--;
                continue;
            }

            if (arr[mid] >= arr[left]) {
                if (target <= arr[mid] && target >= arr[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= arr[mid] && arr[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       SEARCH IN ROTATED SORTED ARRAY II (WITH DUPLICATES)   ║");
        System.out.println("║  Find target in rotated sorted array with duplicate values   ║");
        System.out.println("║  Modified binary search handling duplicates                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    
        System.out.println("=== Test Case 1: Target Present ===");
        int[] arr1 = {2, 5, 6, 0, 0, 1, 2};
        int target1 = 0;
        System.out.println("Array: " + arrayToString(arr1));
        System.out.println("Target: " + target1);
        System.out.println("\nRotated at index 3");
        System.out.println("Original: [0, 0, 1, 2, 2, 5, 6]");
        System.out.println("Target 0 is present at indices 3, 4\n");
        
        boolean result1 = search(arr1, target1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result1 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 2: Target Not Present ===");
        int[] arr2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 3;
        System.out.println("Array: " + arrayToString(arr2));
        System.out.println("Target: " + target2);
        System.out.println("\nTarget 3 does not exist in array\n");
        
        boolean result2 = search(arr2, target2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: false");
        System.out.println("  Status: " + (result2 == false ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 3: All Duplicates ===");
        int[] arr3 = {1, 1, 1, 1, 1, 1, 1};
        int target3 = 1;
        System.out.println("Array: " + arrayToString(arr3));
        System.out.println("Target: " + target3);
        System.out.println("\nAll elements are same");
        System.out.println("Worst case: must shrink search space repeatedly\n");
        
        boolean result3 = search(arr3, target3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result3 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 4: No Rotation ===");
        int[] arr4 = {1, 2, 3, 4, 5, 6, 7};
        int target4 = 5;
        System.out.println("Array: " + arrayToString(arr4));
        System.out.println("Target: " + target4);
        System.out.println("\nArray not rotated (sorted)");
        System.out.println("Standard binary search applies\n");
        
        boolean result4 = search(arr4, target4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result4 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 5: Duplicates at Boundaries ===");
        int[] arr5 = {3, 1, 2, 3, 3, 3, 3};
        int target5 = 2;
        System.out.println("Array: " + arrayToString(arr5));
        System.out.println("Target: " + target5);
        System.out.println("\nDuplicates at start and end");
        System.out.println("arr[left] == arr[mid] == arr[right] case\n");
        
        boolean result5 = search(arr5, target5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result5 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 6: Single Element ===");
        int[] arr6 = {5};
        int target6 = 5;
        System.out.println("Array: " + arrayToString(arr6));
        System.out.println("Target: " + target6);
        System.out.println("\nOnly one element\n");
        
        boolean result6 = search(arr6, target6);
        System.out.println("✓ Result: " + result6);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result6 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 7: Rotation with Duplicates ===");
        int[] arr7 = {4, 5, 6, 6, 7, 0, 1, 2, 4, 4};
        int target7 = 6;
        System.out.println("Array: " + arrayToString(arr7));
        System.out.println("Target: " + target7);
        System.out.println("\nRotated array with multiple duplicates");
        System.out.println("Target appears twice at indices 2, 3\n");
        
        boolean result7 = search(arr7, target7);
        System.out.println("✓ Result: " + result7);
        System.out.println("  Expected: true");
        System.out.println("  Status: " + (result7 == true ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Search in rotated sorted array WITH duplicates     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Rotation Example:                                           ║");
        System.out.println("║    Original: [0, 1, 2, 4, 5, 6, 7]                           ║");
        System.out.println("║    Rotated:  [4, 5, 6, 7, 0, 1, 2]                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Challenge: Duplicates break binary search logic             ║");
        System.out.println("║    Example: [1, 0, 1, 1, 1] - cannot determine which half    ║");
        System.out.println("║             is sorted when arr[left]=arr[mid]=arr[right]     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  1. Standard binary search setup                             ║");
        System.out.println("║     mid = (left + right) / 2                                 ║");
        System.out.println("║     if arr[mid] == target: return true                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  2. Handle duplicates (KEY DIFFERENCE)                       ║");
        System.out.println("║     if arr[left] == arr[mid] == arr[right]:                  ║");
        System.out.println("║       left++, right-- (shrink search space)                  ║");
        System.out.println("║       continue (cannot determine sorted half)                ║");
        System.out.println("║                                                              ║");
        System.out.println("║  3. Identify sorted half                                     ║");
        System.out.println("║     if arr[mid] >= arr[left]: (left half sorted)             ║");
        System.out.println("║       if target in [arr[left], arr[mid]]:                    ║");
        System.out.println("║         right = mid - 1                                      ║");
        System.out.println("║       else:                                                  ║");
        System.out.println("║         left = mid + 1                                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║     else: (right half sorted)                                ║");
        System.out.println("║       if target in [arr[mid], arr[right]]:                   ║");
        System.out.println("║         left = mid + 1                                       ║");
        System.out.println("║       else:                                                  ║");
        System.out.println("║         right = mid - 1                                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Duplicates Matter:                                      ║");
        System.out.println("║    In [1, 0, 1, 1, 1] with left=0, mid=2, right=4:           ║");
        System.out.println("║    arr[0]=1, arr[2]=1, arr[4]=1                              ║");
        System.out.println("║    Cannot tell if left or right is sorted!                   ║");
        System.out.println("║    Must shrink: left++, right-- → [0, 1, 1]                  ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(log n) average, O(n) worst (all duplicates)      ║");
        System.out.println("║    Space: O(1) - No extra space needed                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
    }
    
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
      
}

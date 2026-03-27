package Binary_Search;

/*
        Description :-
                The problem asks for the minimum days needed to make m bouquets, each
                requiring k adjacent flowers, from an array of bloom days...
                Return -1 if impossible due to insufficient flowers...

        Approach :-
              > The solution uses binary search to find the minimum days. It checks if m * k flowers
                    are available; if not, returns -1....
             > It sets low and high as the min/max bloom days, then searches for the smallest day,
                    where m bouquets can be made...
             > The helper function simulates bouquet creation by counting adjacent flowers blooming
                    by day, dividing by k to get bouquets, and checking if the total meets m.
             > The binary search adjusts low or high based on feasibility, returning low as the answer....
             > Time Complexity = O( nlog(m) )...
             > Space Complexity = O(1)...
*/

public class Minimum_Number_Of_Days_To_Make_m_Number_Of_Bouquets {

    private static int minDays(int[] arr, int m, int k) {
            
        if ((long) m * k > arr.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        for (int num : arr) {
            low = Math.min(num, low);
            high = Math.max(num, high);
        }

        while (low <= high) {
            int mid = (low + high) / 2;

            if (helper(arr, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }

    private static boolean helper(int[] arr, int day, int m, int k) {
        
        int total = 0;
        int count = 0;
        
        for (int num : arr) {
            if (num <= day) {
                count++;
            } else {
                total += count / k;
                count = 0;
            }
        }
        
        total += count / k;
        return total >= m;
    }

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              MINIMUM DAYS TO MAKE M BOUQUETS                 ║");
        System.out.println("║  Find minimum days to make m bouquets with k adjacent flowers║");
        System.out.println("║  Each flower blooms on a specific day                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    
        System.out.println("=== Test Case 1: Basic Example ===");
        int[] arr1 = {1, 10, 3, 10, 2};
        int m1 = 3, k1 = 1;
        System.out.println("Bloom Days: " + arrayToString(arr1));
        System.out.println("Bouquets needed (m): " + m1);
        System.out.println("Flowers per bouquet (k): " + k1);
        System.out.println("\nAnalysis:");
        System.out.println("  Day 1: flower[0] blooms → 1 bouquet");
        System.out.println("  Day 2: flower[4] blooms → 2 bouquets");
        System.out.println("  Day 3: flower[2] blooms → 3 bouquets ✓");
        System.out.println("\nMinimum days: 3\n");
        
        int result1 = minDays(arr1, m1, k1);
        System.out.println("✓ Result: " + result1);
        System.out.println("  Expected: 3");
        System.out.println("  Status: " + (result1 == 3 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 2: Need Adjacent Flowers ===");
        int[] arr2 = {1, 10, 3, 10, 2};
        int m2 = 3, k2 = 2;
        System.out.println("Bloom Days: " + arrayToString(arr2));
        System.out.println("Bouquets needed (m): " + m2);
        System.out.println("Flowers per bouquet (k): " + k2);
        System.out.println("\nAnalysis:");
        System.out.println("  Need 2 adjacent flowers per bouquet");
        System.out.println("  Total flowers needed: 3 × 2 = 6");
        System.out.println("  Available flowers: 5");
        System.out.println("\nImpossible! Return -1\n");
        
        int result2 = minDays(arr2, m2, k2);
        System.out.println("✓ Result: " + result2);
        System.out.println("  Expected: -1");
        System.out.println("  Status: " + (result2 == -1 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 3: All Same Day ===");
        int[] arr3 = {7, 7, 7, 7};
        int m3 = 2, k3 = 2;
        System.out.println("Bloom Days: " + arrayToString(arr3));
        System.out.println("Bouquets needed (m): " + m3);
        System.out.println("Flowers per bouquet (k): " + k3);
        System.out.println("\nAll bloom on day 7");
        System.out.println("Can make 2 bouquets of 2 flowers each\n");
        
        int result3 = minDays(arr3, m3, k3);
        System.out.println("✓ Result: " + result3);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result3 == 7 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 4: Exact Fit ===");
        int[] arr4 = {1, 2, 3, 4, 5, 6};
        int m4 = 2, k4 = 3;
        System.out.println("Bloom Days: " + arrayToString(arr4));
        System.out.println("Bouquets needed (m): " + m4);
        System.out.println("Flowers per bouquet (k): " + k4);
        System.out.println("\nNeed exactly 6 flowers total");
        System.out.println("Day 6: all flowers bloomed");
        System.out.println("Can make 2 bouquets of 3 adjacent flowers\n");
        
        int result4 = minDays(arr4, m4, k4);
        System.out.println("✓ Result: " + result4);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result4 == 6 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("=== Test Case 5: Single Bouquet ===");
        int[] arr5 = {5, 5, 5, 5, 10, 5, 5};
        int m5 = 1, k5 = 3;
        System.out.println("Bloom Days: " + arrayToString(arr5));
        System.out.println("Bouquets needed (m): " + m5);
        System.out.println("Flowers per bouquet (k): " + k5);
        System.out.println("\nDay 5: flowers at 0,1,2,3,5,6 bloom");
        System.out.println("Can make bouquet from indices [0,1,2]\n");
        
        int result5 = minDays(arr5, m5, k5);
        System.out.println("✓ Result: " + result5);
        System.out.println("  Expected: 5");
        System.out.println("  Status: " + (result5 == 5 ? "PASS ✓" : "FAIL ✗") + "\n");
    
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find minimum days to make m bouquets               ║");
        System.out.println("║  Each bouquet needs k consecutive bloomed flowers            ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Constraint Check:                                           ║");
        System.out.println("║    If m × k > array length → impossible (return -1)          ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Binary Search Approach:                                     ║");
        System.out.println("║    Search space: [min(arr), max(arr)] (days)                 ║");
        System.out.println("║    For each mid (candidate day):                             ║");
        System.out.println("║      Check if we can make m bouquets by day mid              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Helper Function - Can Make Bouquets?                        ║");
        System.out.println("║    Iterate through array:                                    ║");
        System.out.println("║      If arr[i] <= day: count++ (flower bloomed)              ║");
        System.out.println("║      Else: total += count/k, reset count                     ║");
        System.out.println("║    After loop: total += count/k                              ║");
        System.out.println("║    Return total >= m                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: arr=[1,10,3,10,2], m=3, k=1                        ║");
        System.out.println("║    Binary search range: [1, 10]                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Check day 5:                                              ║");
        System.out.println("║      Bloomed: arr[0]=1, arr[2]=3, arr[4]=2                   ║");
        System.out.println("║      Each is a separate bouquet (k=1)                        ║");
        System.out.println("║      Total: 3 bouquets ✓                                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Check day 2:                                              ║");
        System.out.println("║      Bloomed: arr[0]=1, arr[4]=2                             ║");
        System.out.println("║      Total: 2 bouquets (need 3) ✗                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Check day 3:                                              ║");
        System.out.println("║      Bloomed: arr[0]=1, arr[2]=3, arr[4]=2                   ║");
        System.out.println("║      Total: 3 bouquets ✓ → answer is 3                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why count/k?                                                ║");
        System.out.println("║    count = consecutive bloomed flowers                       ║");
        System.out.println("║    count/k = number of complete bouquets                     ║");
        System.out.println("║    Example: count=7, k=3 → can make 2 bouquets               ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n × log(max-min)) - Binary search × validation   ║");
        System.out.println("║    Space: O(1) - Constant extra space                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
    
    private static String arrayToString(int[] arr) {
        if (arr.length == 0)
            return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1)
                sb.append(", ");
        }
        
        sb.append("]");
        return sb.toString();
    }
    
}

package Linked_List;

import java.util.ArrayList;

public class Maximum_Twin_Sum_Of_A_Linked_List {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // BRUTE FORCE WITH ARRAYLIST
    private static int pairSum1(ListNode head) {

        ListNode temp = head;
        ArrayList<Integer> li = new ArrayList<>();

        while (temp != null) {
            li.add(temp.val);
            temp = temp.next;
        }

        int left = 0;
        int right = li.size() - 1;
        int maxSum = Integer.MIN_VALUE;

        while (left < right) {
            int tempSum = li.get(left) + li.get(right);
            maxSum = Math.max(tempSum, maxSum);
            left++;
            right--;
        }

        return maxSum;
    }

    // OPTIMIZED WITH REVERSING THE SECOND HALF OF THE LINKED LIST
    private static int pairSum2(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode pre = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        int maxSum = Integer.MIN_VALUE;
        int tempSum = 0;

        ListNode L1 = head;
        ListNode L2 = pre;

        while (L2 != null) {
            tempSum = L1.val + L2.val;
            maxSum = Math.max(maxSum, tempSum);

            L2 = L2.next;
            L1 = L1.next;
        }

        return maxSum;
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          MAXIMUM TWIN SUM OF A LINKED LIST                   ║");
        System.out.println("║  Find maximum sum of twin nodes (equidistant from ends)      ║");
        System.out.println("║  Twin pairs: (node_i, node_n-1-i)                            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        System.out.println("=== Test Case 1: Basic Example ===");
        ListNode head1 = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
        System.out.println("Linked List: 5 → 4 → 2 → 1 → null");
        System.out.println("List indices: [0] [1] [2] [3]");
        System.out.println("\nTwin pairs (equidistant from ends):");
        System.out.println("  i=0, i=3: 5 + 1 = 6");
        System.out.println("  i=1, i=2: 4 + 2 = 6");
        System.out.println("Maximum twin sum: 6\n");

        int result1_brute = pairSum1(head1);
        // Recreate list for second method (first one modifies structure)
        ListNode head1_opt = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
        int result1_opt = pairSum2(head1_opt);

        System.out.println("✓ Brute Force Result: " + result1_brute);
        System.out.println("✓ Optimized Result: " + result1_opt);
        System.out.println("  Expected: 6");
        System.out.println("  Status: " + (result1_brute == 6 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 2: Two Elements ===");
        ListNode head2 = new ListNode(1, new ListNode(100000));
        System.out.println("Linked List: 1 → 100000 → null");
        System.out.println("List indices: [0] [1]");
        System.out.println("\nTwin pairs:");
        System.out.println("  i=0, i=1: 1 + 100000 = 100001");
        System.out.println("Maximum twin sum: 100001\n");

        int result2_brute = pairSum1(head2);
        ListNode head2_opt = new ListNode(1, new ListNode(100000));
        int result2_opt = pairSum2(head2_opt);

        System.out.println("✓ Brute Force Result: " + result2_brute);
        System.out.println("✓ Optimized Result: " + result2_opt);
        System.out.println("  Expected: 100001");
        System.out.println("  Status: " + (result2_brute == 100001 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 3: Larger List ===");
        ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3,
            new ListNode(4, new ListNode(5, new ListNode(6))))));
        System.out.println("Linked List: 1 → 2 → 3 → 4 → 5 → 6 → null");
        System.out.println("List indices: [0] [1] [2] [3] [4] [5]");
        System.out.println("\nTwin pairs:");
        System.out.println("  i=0, i=5: 1 + 6 = 7");
        System.out.println("  i=1, i=4: 2 + 5 = 7");
        System.out.println("  i=2, i=3: 3 + 4 = 7");
        System.out.println("Maximum twin sum: 7\n");

        int result3_brute = pairSum1(head3);
        ListNode head3_opt = new ListNode(1, new ListNode(2, new ListNode(3,
            new ListNode(4, new ListNode(5, new ListNode(6))))));
        int result3_opt = pairSum2(head3_opt);

        System.out.println("✓ Brute Force Result: " + result3_brute);
        System.out.println("✓ Optimized Result: " + result3_opt);
        System.out.println("  Expected: 7");
        System.out.println("  Status: " + (result3_brute == 7 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 4: Different Values ===");
        ListNode head4 = new ListNode(100, new ListNode(50, new ListNode(10,
            new ListNode(20, new ListNode(30, new ListNode(40))))));
        System.out.println("Linked List: 100 → 50 → 10 → 20 → 30 → 40 → null");
        System.out.println("List indices: [0]  [1]  [2] [3] [4] [5]");
        System.out.println("\nTwin pairs:");
        System.out.println("  i=0, i=5: 100 + 40 = 140");
        System.out.println("  i=1, i=4: 50 + 30 = 80");
        System.out.println("  i=2, i=3: 10 + 20 = 30");
        System.out.println("Maximum twin sum: 140\n");

        int result4_brute = pairSum1(head4);
        ListNode head4_opt = new ListNode(100, new ListNode(50, new ListNode(10,
            new ListNode(20, new ListNode(30, new ListNode(40))))));
        int result4_opt = pairSum2(head4_opt);

        System.out.println("✓ Brute Force Result: " + result4_brute);
        System.out.println("✓ Optimized Result: " + result4_opt);
        System.out.println("  Expected: 140");
        System.out.println("  Status: " + (result4_brute == 140 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 5: All Same Values ===");
        ListNode head5 = new ListNode(5, new ListNode(5, new ListNode(5,
            new ListNode(5, new ListNode(5, new ListNode(5))))));
        System.out.println("Linked List: 5 → 5 → 5 → 5 → 5 → 5 → null");
        System.out.println("\nAll values are 5");
        System.out.println("All twin pairs sum to: 10");
        System.out.println("Maximum twin sum: 10\n");

        int result5_brute = pairSum1(head5);
        ListNode head5_opt = new ListNode(5, new ListNode(5, new ListNode(5,
            new ListNode(5, new ListNode(5, new ListNode(5))))));
        int result5_opt = pairSum2(head5_opt);

        System.out.println("✓ Brute Force Result: " + result5_brute);
        System.out.println("✓ Optimized Result: " + result5_opt);
        System.out.println("  Expected: 10");
        System.out.println("  Status: " + (result5_brute == 10 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("=== Test Case 6: Ascending Pairs ===");
        ListNode head6 = new ListNode(1, new ListNode(10, new ListNode(100,
            new ListNode(1000))));
        System.out.println("Linked List: 1 → 10 → 100 → 1000 → null");
        System.out.println("List indices: [0] [1]  [2]  [3]");
        System.out.println("\nTwin pairs:");
        System.out.println("  i=0, i=3: 1 + 1000 = 1001");
        System.out.println("  i=1, i=2: 10 + 100 = 110");
        System.out.println("Maximum twin sum: 1001\n");

        int result6_brute = pairSum1(head6);
        ListNode head6_opt = new ListNode(1, new ListNode(10, new ListNode(100,
            new ListNode(1000))));
        int result6_opt = pairSum2(head6_opt);

        System.out.println("✓ Brute Force Result: " + result6_brute);
        System.out.println("✓ Optimized Result: " + result6_opt);
        System.out.println("  Expected: 1001");
        System.out.println("  Status: " + (result6_brute == 1001 ? "PASS ✓" : "FAIL ✗") + "\n");

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITHM INSIGHTS                                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Problem: Find max sum of twin nodes in linked list          ║");
        System.out.println("║  Twin nodes: nodes at equal distance from start and end      ║");
        System.out.println("║                                                              ║");
        System.out.println("║  APPROACH 1: BRUTE FORCE (pairSum1)                          ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Algorithm:                                                  ║");
        System.out.println("║    1. Convert linked list to ArrayList                       ║");
        System.out.println("║       Store all node values                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║    2. Use two pointers (left and right)                      ║");
        System.out.println("║       left starts at 0, right at size-1                      ║");
        System.out.println("║                                                              ║");
        System.out.println("║    3. While left < right:                                    ║");
        System.out.println("║       tempSum = list[left] + list[right]                     ║");
        System.out.println("║       maxSum = max(maxSum, tempSum)                          ║");
        System.out.println("║       left++, right--                                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Example: List = [5, 4, 2, 1]                                ║");
        System.out.println("║    left=0, right=3: 5+1=6, maxSum=6                          ║");
        System.out.println("║    left=1, right=2: 4+2=6, maxSum=6                          ║");
        System.out.println("║    left=2 > right=1: stop                                    ║");
        System.out.println("║    Return 6                                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - One pass to list + one pass with pointers   ║");
        System.out.println("║    Space: O(n) - ArrayList storage                           ║");
        System.out.println("║                                                              ║");
        System.out.println("║  APPROACH 2: OPTIMIZED (pairSum2)                            ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Key Idea: Reverse second half to align twins together       ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1: Find middle using slow/fast pointers                ║");
        System.out.println("║    slow moves 1 step, fast moves 2 steps                     ║");
        System.out.println("║    When fast reaches end, slow is at middle                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2: Reverse second half of list                         ║");
        System.out.println("║    Reverse from slow to end                                  ║");
        System.out.println("║    Use three pointers: pre, curr, next                       ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Example: [5, 4, 2, 1] → [5, 4 | 2, 1]                     ║");
        System.out.println("║            After middle split at 4                           ║");
        System.out.println("║            Reverse [2, 1] to [1, 2]                          ║");
        System.out.println("║            Result: [5, 4, 1, 2]                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Reversal Process (in-place):                                ║");
        System.out.println("║    Original second half: 2 → 1 → null                        ║");
        System.out.println("║    After reversal:       1 → 2 → null                        ║");
        System.out.println("║                          ↑                                   ║");
        System.out.println("║                        pre (new head)                        ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 3: Traverse both halves simultaneously                 ║");
        System.out.println("║    L1 starts from head (beginning)                           ║");
        System.out.println("║    L2 starts from reversed middle (now beginning of half 2)  ║");
        System.out.println("║    They naturally point to twins!                            ║");
        System.out.println("║                                                              ║");
        System.out.println("║    Compare L1.val + L2.val for each step                     ║");
        System.out.println("║    Track maximum sum                                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complete Example: [5, 4, 2, 1]                              ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 1 - Find middle (slow/fast):                           ║");
        System.out.println("║    Initial: slow=5, fast=5                                   ║");
        System.out.println("║    After 1 move: slow=4, fast=2                              ║");
        System.out.println("║    After 2 moves: slow=2, fast=null, done                    ║");
        System.out.println("║    Middle at 2                                               ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 2 - Reverse from slow (2) to end:                      ║");
        System.out.println("║    Before: 5 → 4 → 2 → 1 → null                              ║");
        System.out.println("║    After:  5 → 4 → 1 → 2 → null                              ║");
        System.out.println("║            (points to reversed part)                         ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Step 3 - Compare twins:                                     ║");
        System.out.println("║    L1=5, L2=1: 5+1=6, maxSum=6                               ║");
        System.out.println("║    L1=4, L2=2: 4+2=6, maxSum=6                               ║");
        System.out.println("║    L2=null: stop                                             ║");
        System.out.println("║    Return 6                                                  ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Complexity:                                                 ║");
        System.out.println("║    Time:  O(n) - Find middle + reverse + compare             ║");
        System.out.println("║    Space: O(1) - No extra data structure                     ║");
        System.out.println("║                                                              ║");
        System.out.println("║  Why Optimization Works:                                     ║");
        System.out.println("║    • Reversing second half brings twins adjacent             ║");
        System.out.println("║    • Single simultaneous traversal finds all pairs           ║");
        System.out.println("║    • Avoids array conversion (constant space!)               ║");
        System.out.println("║  ────────────────────────────────────────────────────────────║");
        System.out.println("║  Comparison:                                                 ║");
        System.out.println("║    Approach      | Time | Space | Extra Operations           ║");
        System.out.println("║    ─────────────────────────────────────────────────────     ║");
        System.out.println("║    Brute Force   | O(n) | O(n)  | List conversion            ║");
        System.out.println("║    Optimized     | O(n) | O(1)  | List reversal              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

    }

}
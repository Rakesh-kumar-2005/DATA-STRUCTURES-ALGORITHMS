package Binary_Search;

/*

Description:
   Following program demonstrates the solution to the Book Allocation problem using Binary Search...

Problem Statement:
   -> Given an array representing pages of N books and M students...
   -> We need to allocate all books to students such that each student gets at least one book...
   -> The books must be allocated in a contiguous manner (i.e., a student gets a subset of consecutively placed books)...
   -> The objective is to minimize the maximum number of pages assigned to any student...

Approach:
   > Binary Search on Answer:
      i. The minimum possible allocation would be the book with maximum pages (low)...
      ii. The maximum possible allocation would be the sum of all books' pages (high)...
      iii. Apply binary search on this range to find the minimum possible maximum pages...

> Validation Function (helper):
   -> For a given maximum pages limit, check if all students can be allocated books:
      i. Allocate books to the first student until adding another book exceeds the limit...
      ii. Move to the next student and continue allocating...
      iii. If the total number of students required is less than or equal to the given number, the allocation is valid...

> Binary Search Process:
   -> If the number of students required for a 'mid' value is greater than the given students:
      i. We need a larger allocation limit, so try a bigger value (low = mid + 1)...
   -> If the number of students required is less than or equal to the given students:
      i. This allocation is possible, but try a smaller value to minimize (high = mid - 1)...
   -> Continue until low > high, then return low as the answer...

> Edge Case:
   -> If the number of books is less than the number of students, return -1 as allocation is impossible...

> Time and Space Complexity:
   -> Time Complexity: O(N log S) where N is the number of books and S is the sum of all pages...
   -> Space Complexity: O(1) extra space (excluding input)...
   
*/

public class Book_Allocation {

    private static int findPages(int[] pages, int students) {
        if (pages.length < students) {
            return -1;
        }

        int low = Integer.MIN_VALUE;
        int high = 0;

        for (int num : pages) {
            low = Math.max(num, low);
            high += num;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currStudent = helper(pages, mid);

            if (currStudent > students) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private static int helper(int[] pages, int maxPages) {
        int currStudent = 1;
        int temp = 0;

        for (int num : pages) {
            if (temp + num > maxPages) {
                currStudent++;
                temp = num;
            } else {
                temp += num;
            }
        }
        return currStudent;
    }

    public static void main(String[] args) {
        int[] pages = {25, 46, 28, 49, 24};
        int students = 4;

        System.out.println("The minimum number of pages required is = " + findPages(pages, students));
    }
}

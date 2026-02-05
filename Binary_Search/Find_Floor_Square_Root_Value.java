package Binary_Search;

/*

        Description :-
            We have to find out the Square root (Floor value) of a number...
            But we have condition - Time complexity shouldn't exceed O(log(n))...

        Approach :-
            > So,the typical low and high pointer,then iterate until low <= high...
            > We have to get the mid-index ,then check for its square is less than the
                given number ,then increment the low pointer otherwise decrement the high
                pointer...
                
*/

public class Find_Floor_Square_Root_Value {

    private static int floorSqrt(int n) {
        if (n == 1) return 1;
        int low = 0;
        int high = n / 2;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (mid * mid == n) return mid;

            if (mid * mid < n) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }

    public static void main(String[] args) {
        int number = 100;
        System.out.println("The floor value of the square root of the number " + number + " is = " + floorSqrt(number));
    }

}

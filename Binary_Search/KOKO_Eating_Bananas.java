package Binary_Search;

/*
        Description :-
            Koko needs to eat all banana piles within ℎ hours. Each pile takes
            ⌈pileSize / 𝐾⌉ hours to eat at speed 𝐾. Find the minimum 𝐾 using binary search
            between 1 and the largest pile, ensuring the total hours spent is within ℎ...


        Approach :-
            > To find the minimum eating speed 𝐾 for Koko, use binary search between
                the smallest and largest banana pile sizes...
            > Initialize low to 1 and high to the maximum pile. For each mid-point
                (average speed), calculate total hours required using the helper function.
                If hours are less than or equal to ℎ, Koko can eat at this speed, so reduce
                the high boundary. Otherwise, increase the low boundary...
            > Continue until low exceeds high, where low is the minimum speed 𝐾...
*/

public class KOKO_Eating_Bananas {

    private static int minEatingSpeed(int[] arr, int h) {
        int low = 0;
        int high = 0;

        for (int num : arr) {
            high = Math.max(num, high);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            int total = helper(arr, mid);

            if (total <= h) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int helper(int[] arr, int mid) {
        int ans = 0;
        for (int num : arr) {
            ans += (int) Math.ceil((double) num / (double) mid);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {30, 11, 23, 4, 20};
        int hours = 6;
        System.out.println("KOKO's minimum eating speed is = " + minEatingSpeed(arr, hours));

    }

}

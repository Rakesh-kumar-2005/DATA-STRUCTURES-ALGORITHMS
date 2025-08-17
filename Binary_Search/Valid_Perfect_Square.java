package Binary_Search;

public class Valid_Perfect_Square {

    private static boolean isPerfectSquare(int num) {

        if (num == 1)
            return true;

        long low = 0, high = num / 2;

        while (low <= high) {

            long mid = (low + high) / 2;

            if ((mid * mid) == num)
                return true;

            else if ((mid * mid) < num)
                low = mid + 1;

            else if ((mid * mid) > num)
                high = mid - 1;
        }
        return false;

    }

    public static void main(String[] args) {

        int num = 16;
        boolean ans = isPerfectSquare(num);

        if (ans) {
            System.out.println("The number " + num + " is a perfect square number.");
        } else {
            System.out.println("The number " + num + " is not a perfect square number.");
        }

    }

}
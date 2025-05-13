package Arrays;


public class Number_Of_Sub_Arrays_With_Odd_Sum {

    private static int numOfSubArrays(int[] arr) {

        int mod = 1000000007;
        int odd = 0;
        int even = 1;

        int result = 0;
        int sum = 0;

        for (int num : arr) {

            sum += num;

            if (sum % 2 == 0) {
                result = (result + odd) % mod;
                even++;
            } else {
                result = (result + even) % mod;
                odd++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("The number of sub-arrays with odd sum is = " + numOfSubArrays(arr));

    }

}
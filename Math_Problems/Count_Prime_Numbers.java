package Math_Problems;

public class Count_Prime_Numbers {

    private static int countPrimes(int n) {

        if (n < 2) {
            return 0;
        }

        int count = 0;
        int[] arr = new int[n];

        for (int i = 2; i * i < n; i++) {

            if (arr[i] == 0) {
                for (int j = i * i; j < n; j += i) {
                    arr[j] = 1;
                }
            }

        }

        for (int i = 2; i < n; i++) {
            if (arr[i] == 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int n = 10;
        System.out.println("The number of prime numbers till " + n + " is : " + countPrimes(n));
    }

}
package Math_Problems;

public class Find_N_Unique_Integers_Sum_Up_To_Zero {

    private static int[] sumZero1(int n) {

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2 - n + 1;
        }

        return arr;
    }

    private static int[] sumZero2(int n) {
        int[] arr = new int[n];
        arr[0] = n * (1 - n) / 2;

        for (int i = 1; i < n; i++) {
            arr[i] = i;
        }

        return arr;
    }

    private static void displayArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int sumOfElements(int[] arr) {

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        return sum;

    }

    public static void main(String[] args) {

        int n = 5;

        int[] arr1 = sumZero1(n);
        int[] arr2 = sumZero2(n);

        System.out.println("Result Array from method 1: ");
        displayArray(arr1);
        System.out.println("Sum of the elements of the above array is = " + sumOfElements(arr1));
        System.out.println("Result Array from method 2: ");
        displayArray(arr2);
        System.out.println("Sum of the elements of the above array is = " + sumOfElements(arr2));

    }

}
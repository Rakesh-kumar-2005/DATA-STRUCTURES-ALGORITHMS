package Math_Problems;

public class Count_Operations_To_Obtain_Zero {

    private static int countOperations(int num1, int num2) {

        System.out.println("Original numbers are : num1 = " + num1 + " num2 = " + num2);

        if (num1 == 0 || num2 == 0) {
            System.out.println("One of the number is zero...");
            return 0;
        }

        if (num1 == num2) {
            System.out.println("Both the numbers are same...");
            return 1;
        }

        int count = 0;
        while (num1 != 0 || num2 != 0) {

            if (num1 < num2) {
                count++;
                num2 -= num1;
                System.out.println("Step no. " + count + " num1 = " + num1 + " num2 = " + num2);
            } else if (num1 > num2) {
                count++;
                num1 -= num2;
                System.out.println("Step no. " + count + " num1 = " + num1 + " num2 = " + num2);
            } else {
                num1 -= num2;
                System.out.println("Final numbers are : num1 = " + num1 + " num2 = " + num2);
                return count + 1;
            }

        }

        System.out.println("Final numbers are : num1 = " + num1 + " num2 = " + num2);
        return count;

    }

    public static void main(String[] args) {

        int num1 = 5;
        int num2 = 9;

        int noOfOperations = countOperations(num1, num2);
        System.out.println("The number of operations to obtain zero from either one number is : " + noOfOperations);

    }

}

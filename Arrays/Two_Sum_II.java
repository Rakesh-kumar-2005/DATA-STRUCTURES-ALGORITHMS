package Arrays;

public class Two_Sum_II {

    private static int[] twoSum(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;

        while (i <= j) {
            int ans = arr[i] + arr[j];

            if (ans == target) {
                return new int[]{i + 1, j + 1};
            } else if (ans > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] ans = twoSum(arr, target);
        System.out.println("The indices of the two numbers are which have the combined sum of " + target + " : " + ans[0] + " " + ans[1]);

    }
}

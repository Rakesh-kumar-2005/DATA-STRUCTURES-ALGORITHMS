package Arrays;

public class Count_Elements_With_Maximum_Frequency {

    private static int maxFrequencyElements(int[] nums) {

        int[] frequency = new int[101];
        int max = 0;
        int result = 0;

        for (int num : nums) {

            int currFrequency = ++ frequency[num];

            if (currFrequency > max) {
                max = currFrequency;
                result = currFrequency;
            } else if (max == currFrequency) {
                result += currFrequency;
            }

        }

        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        System.out.println("The maximum frequency elements in the array is : " + maxFrequencyElements(arr));

    }

}
package Arrays;


public class Find_Pivot_Index {

    private static int pivotIndex(int[] nums) {

        int sum = 0;
        int temp = 0;

        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp == sum - temp - nums[i]) {
                return i;
            }
            temp += nums[i];
        }
        return - 1;

    }

    public static void main(String[] args) {

        int[] nums = {1, 7, 3, 6, 5, 6};
        System.out.println("The pivot index is = " + pivotIndex(nums));

    }

}
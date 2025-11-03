package String;

public class Minimum_Time_To_Make_Rope_Colorful {

    private static int minCost(String colors, int[] times) {

        int sum = 0;
        int n = colors.length();
        for (int i = 1; i < n; i++) {

            char curr = colors.charAt(i);
            char prev = colors.charAt(i - 1);

            if (curr == prev) {
                sum += Math.min(times[i], times[i - 1]);
                times[i] = Math.max(times[i], times[i - 1]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        String colors = "abaac";
        int[] times = {1, 2, 3, 4, 5};

        System.out.println("The minimum time to make the rope colorful is : " + minCost(colors, times));

    }

}

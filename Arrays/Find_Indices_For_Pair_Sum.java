package Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class Find_Indices_For_Pair_Sum {

	 private static int[] findPairSum(int[] arr, int sum) {
		  if (arr == null || arr.length == 0) {
				return null;
		  }

		  HashMap<Integer, Integer> mp = new HashMap<>();
		  for (int i = 0; i < arr.length; i++) {

				int remain = sum - arr[i];

				if (mp.containsKey(remain)) {
					 return new int[]{mp.get(remain), i};
				}
				mp.put(arr[i], i);
		  }
		  return null;
	 }

	 public static void main(String[] args) {
		  int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		  int sum = 17;

		  int[] result = findPairSum(arr, sum);

		  System.out.println("Array is : " + Arrays.toString(arr));

		  if (result != null) {
				System.out.println("Pair with sum " + sum + " is at indices " + "[" + result[0] + ", " + result[1] + "]");
		  } else {
				System.out.println("No pair found with sum " + sum);
		  }
	 }
}

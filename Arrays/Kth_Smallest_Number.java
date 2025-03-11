package Arrays;

/*
        Description :-
                Here's the problem is to reverse the array, without using any extra space...

        Approach :-
              	> The code approach creates a max heap priority queue (Descending order) to find the kth
			smallest element...
		> It processes each number in the array, adding it to the queue. When the queue size exceeds
			k, it removes the largest element. After processing all numbers, the largest value in the queue
			(the head) is the kth smallest number in the array...
              	> Time Complexity = O(n log(k) )...
              	> Space Complexity = O(k)...

        Another Approach :-
        	> We could sort the array and return the k - 1 index value to get the answer, but it'll take O(n log(n))
        		time complexity which is larger than O(n log(k))...
*/

import java.util.Arrays;
import java.util.PriorityQueue;

public class Kth_Smallest_Number {

	private static int findSmallest(int[] arr, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		int n = arr.length;

		for (int number : arr) {
			pq.offer(number);

			if (pq.size() > k)
				pq.poll();
		}

		return pq.peek();
	}

	public static void main(String[] args) {
		int[] arr = {3, 4, 2, 1, 888, 3, 88, 224, 373};
		int k = 4;
		System.out.println(Arrays.toString(arr));
		System.out.println("In the above array " + k + "th smallest number is  = " + findSmallest(arr, k));
	}

}

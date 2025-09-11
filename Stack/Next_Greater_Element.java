package Stack;

import java.util.Stack;

public class Next_Greater_Element {

    private static void print(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int[] nextGreater(int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(arr[n-1]);
        ans[n-1] = -1;

        for (int i = n-2; i >= 0; i--){
            while (arr[i] > st.peek() && !st.isEmpty()){
                st.pop();
            }
            if (st.isEmpty()) ans[i] = -1;
            else ans[i] = st.peek();
            st.push(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,234,3,32,721,3,6,-2,10,345,827};
        print(arr);

        int[] ansArr = nextGreater(arr);
        print(ansArr);
    }
}


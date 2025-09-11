package Stack;

import java.util.Stack;

public class Stock_Span_Problem {

    private static int[] stockSpan(int[] stocks){
        int n = stocks.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        st.push(0);
        ans[0] = 1;

        for (int i = 1; i < n; i++){
            while (!st.isEmpty() && stocks[st.peek()] < stocks[i]){
                st.pop();
            }
            if (!st.isEmpty()) ans[i] = i - st.peek();
            else ans[i] = 1;
            st.push(i);
        }
        return ans;
    }

    private static void print(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] stocks = {100,80,60,70,60,75,85};
        print(stocks);

        int profitDays[] = stockSpan(stocks);
        print(profitDays);
    }
}


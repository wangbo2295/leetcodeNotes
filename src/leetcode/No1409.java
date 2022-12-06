package leetcode;

import java.util.*;

public class No1409 {
    public int[] processQueries(int[] queries, int m) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int[] res = new int[queries.length];
        for (int i = m; i > 0; i--) {
            stack2.push(i);
        }
        for (int i = 0; i < queries.length; i++) {
            int k = 0;
            while (!stack2.empty() && stack2.peek() != queries[i]) {
                stack1.push(stack2.pop());
                k++;
            }
            res[i] = k;
            int cur = stack2.pop();
            while (!stack1.empty()) stack2.push(stack1.pop());
            stack2.push(cur);
        }
        return res;
    }
}

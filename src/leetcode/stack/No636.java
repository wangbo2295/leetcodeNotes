package leetcode.stack;

import java.util.List;
import java.util.Stack;

public class No636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        int pre = 0;
        for (String log : logs) {
            String[] params = log.split(":");
            int id = Integer.parseInt(params[0]), time = Integer.parseInt(params[2]);
            String type = params[1];
            if (type.equals("start")) {
                if (!stack.empty()) ans[stack.peek()] += time - pre;
                pre = time;
                stack.push(id);
            } else {
                ans[id] += time + 1 - pre;
                stack.pop();
                pre = time;
            }
        }
        return ans;
    }
}

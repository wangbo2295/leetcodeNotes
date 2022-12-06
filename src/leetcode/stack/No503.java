package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 遍历两遍数组
 */
public class No503 {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len * 2; i++) {
            while (!stack.empty() && nums[stack.peek()] < nums[i % len]) {
                Integer pop = stack.pop();
                res[pop] = nums[i % len];
            }
            if (stack.empty() || i % len > stack.peek()) {
                stack.push(i % len);
            }
        }
        return res;
    }
}

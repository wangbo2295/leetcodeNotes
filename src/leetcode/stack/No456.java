package leetcode.stack;

import java.util.Stack;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 */
public class No456 {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> buff = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.peek() > nums[i]) {
                while (!stack.empty() && stack.peek() > nums[i]) {
                    buff.push(stack.pop());
                }
                if (!stack.empty()) return true;
                while (!buff.empty()) {
                    stack.push(buff.pop());
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }
}

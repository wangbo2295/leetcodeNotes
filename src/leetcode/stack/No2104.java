package leetcode.stack;

import java.util.Stack;

/**
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 */
public class No2104 {
    /**
     * 思路：感觉自己是个呆逼
     */
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        Stack<Element> minstack = new Stack<>();
        Stack<Element> maxstack = new Stack<>();
        int dot1 = 0, dot2, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            while (!minstack.empty() && minstack.peek().val < nums[i]) {
                Element e = minstack.pop();
                dot1 -= e.val * e.count;
                count += e.count;
            }
        }
        return 0;
    }

    class Element {
        int val;
        int count;
        public Element(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}

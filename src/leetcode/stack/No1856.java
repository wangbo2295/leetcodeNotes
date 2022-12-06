package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 一个数组的最小乘积定义为这个数组中最小值乘以数组的和。
 * 给你一个正整数数组nums，请你返回nums任意非空子数组的最小乘积的最大值。由于答案可能很大，请你返回答案对109 + 7取余的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作之前的结果。题目保证最小乘积的最大值在不取余的情况下可以用 64 位有符号整数保存。
 * 子数组定义为一个数组的连续部分。
 */
public class No1856 {
    /**
     * 思路：单调栈
     * 以每个元素为最小值，则其左右元素都严格小于它，由于有重复元素，采用左边小于等于右边严格小于的做法
     * 找到每个元素为最小值对应的子数组左右边界，然后求前缀和，就很快得出最小值和对应的子数组和了
     * @param nums
     * @return
     */
    public int maxSumMinProduct(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        Arrays.fill(right, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[stack.peek()] >= nums[i]) {
                right[stack.pop()] = i - 1;
            }
            if (!stack.empty()) {
                left[i] = stack.peek() + 1;
            }
            stack.push(i);
        }
        //前缀和
        long[] presum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }

        long max = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i] * (presum[right[i] + 1] - presum[left[i]]));
        }
        return (int) (max % 1_000_000_007);
    }
}

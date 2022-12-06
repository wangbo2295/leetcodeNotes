package leetcode.stack;

import java.util.Stack;

/**
 * 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */
public class No907 {
    /**
     * 思路：单调栈
     * 找出以arr[j]结尾且arr[j]为最小值的子数组，那么这个子数组对结果的贡献为len * arr[j]
     * 用一个栈维护以arr[j]为最小值且结尾的数组长度，记录value-len键值对
     * 遇到更小的元素就弹出所有比当前元素大的并且合并区间，否则就入栈
     * 每次入栈都要累积当前贡献值，每次出栈都要减少当前贡献值
     * 减少的贡献值为旧的最小值的贡献，增加的贡献值为新的最小值的贡献
     * 入栈后在结果中加上当前贡献值
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        Stack<Pair> stack = new Stack<>();
        int ans = 0, cur = 0;
        for (int i : arr) {
            int len = 1;
            while (!stack.empty() && i <= stack.peek().val) {
                Pair p = stack.pop();
                len += p.len;
                cur -= p.val * p.len;
            }
            stack.push(new Pair(i, len));
            cur += i * len;
            ans = (ans + cur) % MOD;
        }
        return ans;
    }

    class Pair {
        int val;
        int len;
        public Pair(int val, int len) {
            this.val = val;
            this.len = len;
        }
    }
}

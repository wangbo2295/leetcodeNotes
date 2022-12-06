package leetcode.dynamicPrograming.subSequence;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 给你一个下标从 0 开始的整数数组 nums和一个整数 k。
 * 一开始你在下标0处。每一步，你最多可以往前跳k步，但你不能跳出数组的边界。也就是说，你可以从下标i跳到[i + 1， min(n - 1, i + k)]包含 两个端点的任意位置。
 * 你的目标是到达数组最后一个位置（下标为 n - 1），你的 得分为经过的所有数字之和。
 * 请你返回你能得到的 最大得分。
 */
public class No1696 {
    /**
     * 解法一：动态规划 + 优先队列优化
     * dp[i] 表示到达 i 能得到的最大得分, i 能从 i - k ~ i - 1 到达，因此状态转移方程为
     * dp[i] = max(dp[i - k] ~ dp[i - 1]) + nums[i]
     * 时间复杂度 O(n * k)，可见当 k 非常大时时间复杂度为 O(n ^ 2) , 会超时
     * 由于我们只关注 i 前面 k 个中最大的 dp, 考虑采用堆优化
     * 用一个大根堆保存 dp[i] 的最大值对应的下标，每次计算 dp[i] 时，若堆顶元素 j + k < i 说明不可达，需要弹出
     * 计算完 dp[i] 后将 i 入堆，最后 dp[n - 1] 即为答案。
     * 优化后的时间复杂度 O（n * log(n)）
     */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt((Integer o)->dp[o]).reversed());
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek() + k < i) pq.poll();
            if (pq.isEmpty()) dp[i] = nums[i];
            else dp[i] = dp[pq.peek()] + nums[i];
            pq.offer(i);
        }
        return dp[n - 1];
    }

    /**
     * 动态规划 + 单调队列优化
     * 我们还可以用单调队列优化使时间复杂度降到 O（n）
     * 同样的，队列中保存 dp 的下标值
     * 单调队列队首为 i 的前 k 个中值最大的 dp 对应下标
     * 对于起点，即队列为空的情况，直接 dp[0] = nums[0]
     * 每当计算 dp[i] 时，先将不满足的队首元素出队，即 j + k < i 时 poll(j)
     * 此时队首就是 i 的前 k 个候选 dp 的最大值 dp[j]，直接更新 dp[i] = dp[j] + nums[i] 即可
     * 然后将 i 加入队尾，在这之前先将队尾所有 dp[j] < dp[i] 的 j 出队，保持队列的单调性。
     */
    public int maxResult2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.getFirst() + k < i) deque.pollFirst();
            if (!deque.isEmpty()) dp[i] = dp[deque.getFirst()] + nums[i];
            else dp[i] = nums[i];
            while (!deque.isEmpty() && dp[deque.getLast()] < dp[i]) deque.pollLast();
            deque.addLast(i);
        }
        return dp[n - 1];
    }
}

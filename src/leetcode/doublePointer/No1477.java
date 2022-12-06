package leetcode.doublePointer;

/**
 * 给你一个整数数组arr 和一个整数值target。
 * 请你在 arr中找 两个互不重叠的子数组且它们的和都等于target。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1。
 */
public class No1477 {
    /**
     * 1、如何快速找到所有和为 target 的子数组？
     * 双指针，当和小于 targe 时右指针右移，当和大于 target 时左指针右移，等于时记录子数组
     * 2、如何在所有满足和为 target 的子数组中找到长度和最小且不重叠的两个？
     * 如果数组中每个数都一样且 target 为其中一段和，那么将出现很多重叠的情况，此时通过暴力枚举的方式就会超时
     * 用动态规划的思想，dp[i] 表示前 i 个数中满足和为 target 的最小子数组长度
     * 那么假设找到一个满足和为 target 的子数组 [l, r]，(r - l + 1) + dp[l] 即为一个候选答案
     * 然后更新 dp[r + 1] = min(dp[r], r - l + 1)
     * 在不满足和为 target 的右下标 r 中，dp[r + 1] = dp[r]，即沿用前一次计算的结果，如果找到新的更小子数组，则更新
     * 那么状态转移方程为
     *      dp[r + 1] = min(dp[r], len + dp[l]),     sum(l, r) = target
     *      dp[r + 1] = dp[r],                       sum(l, r) != target
     * 由于我们是枚举滑动窗口的右指针来计算子数组的，因此很方便的在计算子数组的同时计算 dp 数组并更新答案
     */
    public int minSumOfLengths(int[] arr, int target) {
        int sum = 0, n = arr.length, min = n + 1;
        int[] dp = new int[n + 1];
        dp[0] = n + 1;
        for (int i = 0, j = 0; j < n; j++) {
            sum += arr[j];
            while (sum > target && i <= j) {
                sum -= arr[i++];
            }
            if (sum == target) {
                int len = j - i + 1;
                min = Math.min(min, len + dp[i]);
                dp[j + 1] = Math.min(dp[j], len);

            } else {
                dp[j + 1] = dp[j];
            }
        }
        return min;
    }
}

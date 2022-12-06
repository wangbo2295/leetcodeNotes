package leetcode.doublePointer;

/**
 * 给出非负整数数组 A ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 L 和 M。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）
 * 从形式上看，返回最大的 V，而 V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) 并满足下列条件之一：
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, 或
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 */
public class No1031 {
    /**
     * 参考No1477，我们只需分别计算出前 i 个数中长度为 len1 和 len2 的最大子段和数组即可
     * 例如已知长度为 len1 的最大字段和数组 dp1, 那么可以枚举长度为 len2 的子段和(i - len2, i)，
     * 在 len2 左边长度为 len1 最大子段和为 dp1[i - len2] 总和为 sum(i - len2, i) + dp1[i - len2]
     * 对于 len2 在左边的情况，求出 dp2 再重复上述过程即可，返回两者计算的较大者
     */
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + nums[i - 1];
        return Math.max(maxsum(getDP(nums, firstLen), presum, secondLen), maxsum(getDP(nums, secondLen), presum, firstLen));
    }

    public int[] getDP(int[] nums, int firstLen) {
        int n = nums.length;
        int[] dp1 = new int[n + 1];
        int sum1 = 0;
        for (int i = 0; i < firstLen; i++) sum1 += nums[i];
        dp1[firstLen] = sum1;
        for (int i = firstLen + 1; i <= n; i++) {
            sum1 -= nums[i - 1 - firstLen];
            sum1 += nums[i - 1];
            dp1[i] = Math.max(dp1[i - 1], sum1);
        }
        return dp1;
    }

    public int maxsum(int[] dp, int[] presum, int len2) {
        int maxsum = 0;
        for (int i = len2; i < presum.length; i++) {
            int sum = presum[i] - presum[i - len2];
            maxsum = Math.max(maxsum, sum + dp[i - len2]);
        }
        return maxsum;
    }
}

package leetcode.dynamicPrograming.subSequence;

import java.util.Arrays;

public class No1027 {
    /**
     * 递归写法，包含很多重复计算，会超时
     * @param nums
     * @return
     */
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];
        for (int[] arr: dp) Arrays.fill(arr, 1);
        int max = 2;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 500;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);
                max = Math.max(max, dp[i][diff]);
            }
        }
        return max;
    }
}

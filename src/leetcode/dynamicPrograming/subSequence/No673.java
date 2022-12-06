package leetcode.dynamicPrograming.subSequence;

import java.util.Arrays;

public class No673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[2][n];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < n; i++) {
            int max = 0, cnt = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= nums[i]) continue;
                if (dp[0][j] == max) {
                    cnt++;
                } else if (dp[0][j] > max) {
                    max = dp[0][j];
                    cnt = 1;
                }
            }
            dp[0][i] = max;
            dp[1][i] = cnt;
        }
        int max = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (dp[0][i] == max) {
                cnt += dp[1][i];
            } else if (dp[0][i] > max) {
                max = dp[0][i];
                cnt = dp[1][i];
            }
        }
        return cnt;
    }
}

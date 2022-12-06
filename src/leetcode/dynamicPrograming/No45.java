package leetcode.dynamicPrograming;

import java.util.Arrays;

public class No45 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[nums.length - 1] = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j <= Math.min(i + nums[i], nums.length - 1); j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[0];
    }
}

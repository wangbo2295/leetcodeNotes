package leetcode.dynamicPrograming;

import java.util.Arrays;

public class No650 {
    //虚假的dp
    public int minSteps3(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.min(dp[i], dp[i - j]);
            }
        }
        return dp[n];
    }

    /**
     * dfs + 剪枝
     */
    public int minSteps2(int n) {
        if (n == 1) return 0;
        dfs(1, 1, 1, n, true);
        return min;
    }
    int min = Integer.MAX_VALUE;
    private void dfs(int copy, int cur, int k, int n, boolean copied) {
        if (cur >= n) {
            if (cur == n)
                min = Math.min(min, k);
            return;
        }
        if (!copied) {
            dfs(cur, cur, k + 1, n, true);
        }
        dfs(copy, cur + copy, k + 1, n, false);
    }
}

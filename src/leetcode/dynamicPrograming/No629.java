package leetcode.dynamicPrograming;

public class No629 {
    public int kInversePairs(int n, int k) {
        int[][] dp = new int[1001][1001];
        int MOD = (int) (1e9 + 7);
        for (int i = 1; i <= n; i++)    dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] - dp[i - 1][j - 1 - Math.min(j, i - 1)] + dp[i - 1][j]) % MOD;
            }
        }
        return dp[n][k];
    }
}

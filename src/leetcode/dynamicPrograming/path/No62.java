package leetcode.dynamicPrograming.path;

/**
 * 不同路径
 */
public class No62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)   continue;
                int left = j > 0 ? dp[i][j - 1] : 0;
                int up = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = left + up;
            }
        }
        return dp[m - 1][n - 1];
    }
}

package 剑指offer;

public class NumDistinct {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    for (int k = 0; k < j; j++) {
                        dp[j][i] += dp[k][i - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }
}

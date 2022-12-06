package leetcode.dynamicPrograming.subSequence;

/**
 * 分割回文子串II
 * 最少分割次数
 */
public class No132 {
    /**
     * 思路：先用dp统计任意两个子串是否为回文子串，然后在回溯寻找最少分割次数
     * @param s
     * @return
     */
    int minCut = Integer.MAX_VALUE;
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i; j < dp.length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 >= j || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        dfs(dp, 0, 0);
        return minCut;
    }

    private int dfs(boolean[][] dp, int k, int start) {
        if (start >= dp.length) {
            minCut = Math.min(minCut, k - 1);
            return k - 1;
        }
        int res = -1;
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[start][i]) {
                if ((res = dfs(dp, k + 1, i + 1)) != -1) {
                    break;
                }
            }
        }
        return res;
    }
}

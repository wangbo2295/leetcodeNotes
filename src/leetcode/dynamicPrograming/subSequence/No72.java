package leetcode.dynamicPrograming.subSequence;

/**
 * 编辑距离
 * 思路：
 * 由最长公共子序列的启发，将子问题看成是word1前i个字符变成word2前j个字符所需的最小编辑次数
 * 如果选择替换，那么操作数等于dp[i - 1][j - 1] + 1，即word1前i - 1个变成word2前j - 1再加上一次替换操作
 * 如果是删除， 那么操作数等于dp[i - 1][j] + 1, 即word1前i - 1个变成word2前j再加上一次删除word1第i个字符的操作
 * 如果是插入, 那么操作数等于dp[i][j - 1] + 1, 即word1前i个变成word2前j - 1，再插入word2的第j个字符。
 * 如果第i个和第j个相等，则替换操作不用加1。
 * 要记得初始化dp数组，当其中一个word长度为0时，编辑次数等于另一个的长度
 */
public class No72 {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <=m ; i++)    dp[i][0] = i;
        for (int j = 1; j <=n ; j++)    dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

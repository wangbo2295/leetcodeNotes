package leetcode.dynamicPrograming.subSequence;

/**
 * 动态规划特征一： 最优解包含子问题最优解
 * if c(i) == c(j)
 *    then LCS(i, j) <-- LCS(i - 1, j - 1) + 1
 * else
 *    LCS(i, j) <-- max(LCS(i, j - 1), LCS(i - 1, j)
 */
public class No1143 {
    /**
     * 解法一： 二维数组
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 解法二： 滚动数组（错误版， 暂时没想到怎么写对）
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] dp = new int[n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = dp[j - 1] + 1;
                }else{
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[n];
    }

    public String traceBackSingle(String text1, String text2){
        int[][] dp = dp(text1, text2);
        int m = dp.length - 1;
        int n = dp[0].length - 1;
        StringBuilder sb = new StringBuilder();
        while (m > 0 || n > 0) {
            if (dp[m][n] > dp[m-1][n] && dp[m][n] > dp[m][n - 1]) {
                sb.append(text1.charAt(m-1));
                m--;
                n--;
            }else if (dp[m-1][n] > dp[m][n - 1]) {
                m--;
            }else{
                n--;
            }
        }
        return sb.reverse().toString();
    }
    public int[][] dp(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        No1143 no1143 = new No1143();
        int i = no1143.longestCommonSubsequence2("abcde", "ace");
        System.out.println(i);
        String s = no1143.traceBackSingle("abcde", "ace");
        System.out.println(s);
    }
}

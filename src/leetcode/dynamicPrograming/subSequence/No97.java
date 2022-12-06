package leetcode.dynamicPrograming.subSequence;

/**
 * 交替字符串
 * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 */
public class No97 {

    /**
     * 思路：注意到s1的前i个和s2的前j个组成s3的前i + j 个，划分成子问题应用动态规划
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length())   return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <=m ; i++)    dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
        for (int i = 1; i <=n ; i++)    dp[0][i] = s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                char c3 = s3.charAt(i + j - 1);
                if (c1 == c3) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (c2 == c3) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}

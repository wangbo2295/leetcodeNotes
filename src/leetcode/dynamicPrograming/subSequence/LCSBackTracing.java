package leetcode.dynamicPrograming.subSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划 + 回溯    (好像不是很对, 没去重)
 * 求s1， s2 的所有最长公共子序列
 */
public class LCSBackTracing {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int[][] dp;

    public List<String> findLCS(String text1, String text2) {
        dp = dp(text1, text2);
        traceBack(text1, text2, dp.length - 1, dp[0].length - 1);
        return res;
    }

    private void traceBack(String text1, String text2, int m, int n){
        if (m == 0 && n == 0){
            res.add(new StringBuilder(sb).reverse().toString());
            return;
        }
        if (dp[m][n] > dp[m-1][n] && dp[m][n] > dp[m][n - 1]) {
            sb.append(text1.charAt(m-1));
            traceBack(text1, text2, m - 1, n - 1);
            sb.delete(sb.length()-1, sb.length());
        }else if (dp[m-1][n] > dp[m][n - 1]) {
            traceBack(text1, text2, m - 1, n);
        }else if (dp[m-1][n] < dp[m][n - 1]){
            traceBack(text1, text2, m, n - 1);
        }else {
            traceBack(text1, text2, m - 1, n);
            traceBack(text1, text2, m, n - 1);
        }
    }

    private int[][] dp(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
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
        LCSBackTracing lcsBackTracing = new LCSBackTracing();
        List<String> lcs = lcsBackTracing.findLCS("abcde", "acfed");
        for (String s: lcs) {
            System.out.println(s);
        }
    }
}

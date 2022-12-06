package leetcode.dynamicPrograming.subSequence;

public class No516 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len; i > 0; i--) {
            for (int j = i; j <= len; j++) {
                if (j == i) {
                    dp[i][j] = 1;
                }else if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    if (i + 1 == j) {
                        dp[i][j] = 2;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[1][len];
    }

    public static void main(String[] args) {
        No516 no516 = new No516();
        int bbbab = no516.longestPalindromeSubseq("cbbsssbbc");
        System.out.println(bbbab);
    }
}

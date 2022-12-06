package leetcode.dynamicPrograming.subSequence;

/**
 * 计算回文子串个数
 * 判断是否为回文数的同时统计回文子串个数
 */
public class No647 {

    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        No647 no647 = new No647();
        int abc = no647.countSubstrings("aaa");
        System.out.println(abc);
    }
}

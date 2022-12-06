package leetcode.dynamicPrograming.subSequence;

public class No392 {

    /**
     * 贪心？ 模拟？
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int len = s.length();
        if (len == 0)    return true;
        int slow = 0;
        for (int fast = 0; fast < t.length() && slow < len; fast++){
            if(s.charAt(slow) == t.charAt(fast)) {
                slow++;
            }
        }
        return slow == len ? true : false;
    }

    /**
     * 动态规划
     * IS(i, j): s的前 i 个字符和 t 的前 j 个字符最长公共子序列的长度
     * if (s[i] == t[j] )
     *      IS(i, j) <-- IS(i - 1, j - 1) + 1
     * else
     *      IS(i, j) <-- IS(i, j - 1)
     */
    public boolean isSubsequence2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m == 0)    return true;
        int[][] IS = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j - 1)) {
                    IS[i][j] = IS[i - 1][j - 1] + 1;
                }else {
                    IS[i][j] = IS[i][j - 1];
                }
            }
        }
        return IS[m][n] == s.length() ? true : false;
    }
}

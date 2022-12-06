package leetcode.dynamicPrograming.subSequence;

import java.util.Arrays;

/**
 * ND[i][j]: s 的前 i 个字符的子序列中跟 t 相同的个数
 * 子问题最优解：
 * if   t[i - 1] == s[j - 1]
 * //s的第 j 个字符匹配到 t 的第 i 个字符，这时s[j] 有两种状态：选和不选
 * //如果选s[j]，则有ND[i - 1][j - 1]种，如果不选s[j],则有ND[i][j - 1]种。
 * then ND[i][j] = ND[i - 1][j - 1] + ND[i][j - 1]
 * else
 *      ND[i][j] = ND[i][j - 1]     //延续上一个子问题的状态
 *
 * ND数组的初始化要将第一行初始化为1，因为ND[0][j] 表示s中子序列为空字符串的个数，是为 1 的。
 */
public class No115 {

    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();
        int[][] ND = new int[m + 1][n + 1];
        Arrays.fill(ND[0], 1);
        for(int i = 1; i <=m; i++) {
            for(int j = i; j <= n; j++) {
                if(t.charAt(i - 1) == s.charAt(j - 1)) {
                    ND[i][j] = ND[i - 1][j - 1] + ND[i][j - 1];
                }else {
                    ND[i][j] = ND[i][j - 1];
                }
            }
        }
        return ND[m][n];
    }
}

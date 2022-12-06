package leetcode.dynamicPrograming.subSequence;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
class No5 {
    /**
     * 动态规划特征一：最优解包含子问题的最优解
     * 本问题中, if(c(i) == c(j))
     *              if(i == j || i+1 == j)
     *                  LP(i,j) = true
     *              else
     *                  LP(i,j) <-- LP(i+1,j-1)
     *          else
     *              return false;
     * 特征二： 重叠子问题？
     * 上述递归过程需要对每一种i、j 的组合进行调用, 如果i1 = i2+1, j1 = j2 -1 ,那就会产生重复计算
     *
     * 动态规划： 记录计算过的子问题，自底向上的计算，也就是从 j == i 开始计算，对每个不同的 i，向右边延伸
     * 由于需要返回具体的子串，所以在计算过程中保存最长回文子串
     * 遍历顺序： i 从后往前遍历， 因为计算LP(i, j)时要用到LP(i - 1, j + 1)， i + 1 必须先计算出来
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 1;
        String res = s.substring(0,1);
        boolean[][] dp = new boolean[len][len];
        for(int i = len - 1; i >= 0; i--) {
            for(int j = i; j < len; j++) {
                boolean b = s.charAt(i) == s.charAt(j);
                if (j == i || (j == i + 1 && b)) {
                    dp[i][j] = true;
                }else if (b) {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    res = s.substring(i,j+1);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        No5 no5 = new No5();
        String babad = no5.longestPalindrome("babad");
        System.out.println(babad);
    }
}

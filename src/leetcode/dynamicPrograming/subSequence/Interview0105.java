package leetcode.dynamicPrograming.subSequence;

/**
 * 判断两个字符串是否只需要一次编辑
 */
public class Interview0105 {
    /**
     * 先按照推广的方法，计算最少编辑次数，如果小于等于1，则返回true
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int i1 = 1; i1 < dp[0].length; i1++) {
                if (first.charAt(i - 1) != second.charAt(i1 - 1)) {
                    dp[i][i1] = Math.min(dp[i - 1][i1] + 1, Math.min(dp[i - 1][i1 - 1] + 1, dp[i][i1 - 1] + 1));
                }else {
                    dp[i][i1] = Math.min(dp[i - 1][i1] + 1, Math.min(dp[i - 1][i1 - 1], dp[i][i1 - 1] + 1));
                }
            }
        }
        return dp[m][n] <= 1;
    }

    /**
     * 对于只需判断一次编辑的情况，上述做法显然太耗时
     * 而只需一次编辑的情况有三种：删除、插入、替换
     * 如果字符串长度相同，则不同字符的个数最多为1
     * 如果长度相差1，那么允许
     * 太多细节了，我吐了
     */
    public boolean oneEditAway2(String first, String second) {
        if (first.length() == 0)    return second.length() < 2;
        if (second.length() == 0)   return first.length() < 2;
        if (first.length() == second.length()) {
            int count = 0;
            for (int i = 0; i < first.length(); i++) {
                if (count > 1)  return false;
                if (first.charAt(i) != second.charAt(i)) {
                    count++;
                }
            }
            return count <= 1;
        }else if (Math.abs(first.length() - second.length()) <= 1) {
            int i = 0, j = 0;
            int count = 0;
            while (i < first.length() && j < second.length()) {
                if (first.charAt(i) != second.charAt(j)) {
                    if (i < first.length() - 1 && first.charAt(i + 1) == second.charAt(j)) {
                        i++;
                    }else if (j < second.length() - 1 && second.charAt(j + 1) == first.charAt(i)) {
                        j++;
                    }else {
                        break;
                    }
                    count++;
                }
                i++;j++;
            }
            if (count == 0 && Math.abs(i - j) <= 1 && (i == first.length() || j == second.length())) return true;
            return count <= 1 && i == first.length() && j == second.length();
        }else {
            return false;
        }
    }


    public static void main(String[] args) {
    }
}

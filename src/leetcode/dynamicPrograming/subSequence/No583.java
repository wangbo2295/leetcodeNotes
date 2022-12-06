package leetcode.dynamicPrograming.subSequence;

public class No583 {
    //同最长公共子序列
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] LCS = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][ j - 1] + 1;
                }else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }
        return m - LCS[m][n] + n - LCS[m][n];
    }
}

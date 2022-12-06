package 剑指offer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinCut {
    boolean[][] dp;
    public int minCut(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                boolean equal = s.charAt(i) == s.charAt(j);
                if (equal && (i == j || j - i == 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        int[] cut = new int[n + 1];
        Arrays.fill(cut, Integer.MAX_VALUE);
        cut[0] = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[j - 1][i - 1]) {
                    cut[i] = Math.min(cut[j - 1] + 1, cut[i]);
                }
            }
        }
        return cut[n];
    }
}

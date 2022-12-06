package leetcode.dynamicPrograming.path;

import java.util.*;

public class No787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 1][n];
        for (int[] d : dp) Arrays.fill(d, 0x3f);
        dp[0][src] = 0;
        int mincost = 0x3f;
        for (int i = 1; i <= k; i++) {
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                if (dp[i - 1][from] != 0x3f) {
                    dp[i][to] = Math.min(dp[i][to], dp[i - 1][from] + price);
                }
            }
            mincost = Math.min(mincost, dp[k][dst]);
        }
        return mincost == 0x3f ? -1 : mincost;
    }
}

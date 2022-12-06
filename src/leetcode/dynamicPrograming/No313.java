package leetcode.dynamicPrograming;

import java.util.Arrays;

public class No313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        int m = primes.length;
        int[][] pointers = new int[2][m];
        Arrays.fill(pointers[0], 1);
        for (int i = 1; i <= n; i++) {
            int min = Arrays.stream(pointers[1]).min().getAsInt();
            dp[i] = min;
            for (int j = 0; j < m; j++) {
                if (pointers[1][j] == min) {
                    ++pointers[0][j];
                    pointers[1][j] = dp[pointers[0][j]] * primes[j];
                }
            }
        }
        return dp[n];
    }
}

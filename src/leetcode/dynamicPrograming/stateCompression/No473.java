package leetcode.dynamicPrograming.stateCompression;

import java.util.Arrays;

public class No473 {
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0)   return false;
        int ceil = sum / 4;
        int n = matchsticks.length;
        int size = 1 << n;
        boolean[] dp = new boolean[size];
        int[] cursum = new int[size];
        dp[0] = true;
        for (int i = 0; i < size; i++) {
            if (!dp[i])     continue;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) != 0) continue;
                int add = i | 1 << j;
                if (cursum[i] % ceil + matchsticks[j] <= ceil) {
                    cursum[add] = cursum[i] + matchsticks[j];
                    dp[add] = true;
                }
            }
        }
        return dp[size - 1];
    }
}

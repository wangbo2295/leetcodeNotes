package leetcode.DC;

import java.util.Arrays;

public class No81 {
    public int countAsterisks(String s) {
        int slash = 0;
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (slash == 0 && s.charAt(i) == '*') {
                ++cnt;
            }
            if (s.charAt(i) == '|') {
                slash = (slash + 1) & 1;
            }
        }
        return cnt;
    }

    public long countPairs(int n, int[][] edges) {
        int[] points = new int[n];
        Arrays.fill(points, -1);
        for (int i = 0; i < edges.length; i++) {
            union(points, edges[i][0], edges[i][1]);
        }
        long cnt = (long) n * (n + 1) >> 1;
        for (int i = 0; i < n; i++) {
            if (points[i] < 0) {
                int p = -points[i];
                cnt -= (long) p * (p + 1) >> 1;
            }
        }
        return cnt;
    }

    private void union(int[] points, int i, int j) {
        int I = find(points, i);
        int J = find(points, j);
        if (I == J) return;
        points[I] = J;
        points[J]--;
    }

    private int find(int[] points, int i) {
        while (points[i] >= 0) {
            i = points[i];
        }
        return i;
    }

    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        for (int i = 0; i < 32; i++) {
            int one;
            one = 0;
            for (int num : nums) {
                if ((num & 1 << i) == 1) {
                    one++;
                }
            }
            one = Math.min(one, 1);
            ans &= one << i;
        }
        return ans;
    }

    public int distinctSequences(int n) {
        int[][] dp = new int[n][6];
        int[][] nums = {{1,2,3,4,5}, {0,2,4}, {0,1,3,4}, {0,2,4}, {0,1,2,3,5}, {0,4}};
        int MOD = 1_000_000_007;
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < nums[j].length; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
                if (i >= 2) {
                    dp[i][j] -= dp[i - 2][j] * nums[j].length;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 6; i++) {
            ans += dp[n - 1][i];
        }
        return ans;
    }
}

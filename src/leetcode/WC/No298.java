package leetcode.WC;

import java.util.Arrays;

public class No298 {
    public String greatestLetter(String s) {
        int[] minor = new int[26];
        int[] major = new int[26];
        for (char c : s.toCharArray()) {
            if (c <= 'Z') major[c - 'A']++;
            else minor[c - 'a']++;
        }
        for (int i = 25; i >= 0; i--) {
            if (minor[i] >= 1 && major[i] >= 1) return Character.toString('A' + i);
        }
        return "";
    }

    public int minimumNumbers(int num, int k) {
        if (num == 0)   return 0;
        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 10 == k) {
                for (int j = 1; j <= num; j++) {
                    if (j - i >= 0) {
                        dp[j] = Math.min(dp[j - i] + 1, dp[j]);
                    }
                }
            }
        }
        return dp[num] == Integer.MAX_VALUE / 2 ? -1 : dp[num];
    }

    public int longestSubsequence(String s, int k) {
        int max = 0, base, i, n = s.length();
        for (i = n - 1; i >= Math.max(n - 31, 0); i--) {
            base = (s.charAt(i) - '0') << (n - i - 1);
            if (max + base > k) {
                break;
            }
            max += base;
        }
        int zero = 0;
        for (int j = 0; j <= i; j++) {
            if (s.charAt(j) == '0') zero++;
        }
        return zero + n - i - 1;
    }

    public long sellingWood(int m, int n, int[][] prices) {
        int[][] price = new int[m + 1][n + 1];
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < prices.length; i++) {
            price[prices[i][0]][prices[i][1]] = prices[i][2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = price[i][j];
                for (int k = 1; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i - k][j], dp[k][j]);
                }
                for (int k = 1; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j - k], dp[i][k]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        No298 no298 = new No298();
        no298.minimumNumbers(58, 9);
    }
}

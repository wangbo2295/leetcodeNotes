package leetcode.dynamicPrograming;

public class No264 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p1, p2, p3;
        p1 = p2 = p3 = 1;
        for (int i = 2; i <= n; i++) {
            int n1 = dp[p1] * 2, n2 = dp[p2] * 3, n3 = dp[p3] * 5;
            dp[i] = Math.min(n1, Math.min(n2, n3));
            if (dp[i] == n1)    p1++;
            if (dp[i] == n2)    p2++;
            if (dp[i] == n3)    p3++;
        }
        return dp[n];
    }
}

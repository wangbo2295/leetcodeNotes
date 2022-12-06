package leetcode.dynamicPrograming.subSequence;

import java.util.Scanner;

public class No712 {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][][] dp = new int[m + 1][n + 1][2];
        int sum = 0;
        for (char c : s1.toCharArray()) {
            sum += c;
        }
        for (char c : s2.toCharArray()) {
            sum += c;
        }
        //s1[i] == s2[j], dp[i][j] = dp[i - 1][j - 1] + 1
        //s1[i] != s2[j], dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    dp[i][j][1] = dp[i - 1][j - 1][1] + s1.charAt(i - 1);
                } else if (dp[i - 1][j][0] > dp[i][j - 1][0]) {
                    dp[i][j][0] = dp[i - 1][j][0];
                    dp[i][j][1] = dp[i - 1][j][1];
                } else if (dp[i - 1][j][0] < dp[i][j - 1][0]) {
                    dp[i][j][0] = dp[i][j - 1][0];
                    dp[i][j][1] = dp[i][j - 1][1];
                } else {
                    dp[i][j][0] = dp[i][j - 1][0];
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);
                }
            }
        }
        return sum - dp[m][n][1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a + b);
    }
}

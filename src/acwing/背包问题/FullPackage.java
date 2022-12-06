package acwing.背包问题;

import java.util.Scanner;

public class FullPackage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        int[][] items = new int[n][2];
        for (int i = 0; i < n; i++) {
            int wi = scanner.nextInt();
            int vi = scanner.nextInt();
            items[i][0] = wi;
            items[i][1] = vi;
        }
        int[] dp = new int[v + 1];
        for (int i = 0; i < n; i++) {
            for (int j = items[i][0]; j <= v; j++) {
                dp[j] = Math.max(dp[j], dp[j - items[i][0]] + items[i][1]);
            }
        }
        System.out.println(dp[v]);
    }
}

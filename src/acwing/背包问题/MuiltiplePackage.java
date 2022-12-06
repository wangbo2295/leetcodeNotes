package acwing.背包问题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MuiltiplePackage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        List<int[]> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int vi = scanner.nextInt();
            int wi = scanner.nextInt();
            int si = scanner.nextInt();
            for (int j = 0; j < si; j++) {
                items.add(new int[] {vi, wi});
            }
        }
        int[] dp = new int[v + 1];
        for (int[] item: items) {
            for (int j = v; j >= item[0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - item[0]] + item[1]);
            }
        }
        System.out.println(dp[v]);
    }

    /**
     * 二进制优化
     * 对于一个数，可以表示为 a0 * 2^0 + a1 * 2^1 + ... + ak * 2^k
     * 对于每个物品，要取 m 个出来，不一定是一个个取，可以是以2的幂次为基底取，就能表示任意个数的该物品
     * 将基底对应的容量和价值当作新的物品，就转变为01背包了。
     * @param args
     */
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        List<int[]> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int vi = scanner.nextInt();
            int wi = scanner.nextInt();
            int si = scanner.nextInt();
            for (int k = 1; k <= si; k <<= 1) {
                items.add(new int[] {vi * k, wi * k});
                si -= k;
            }
            if (si > 0) items.add(new int[] {vi * si, wi * si});
        }
        int[] dp = new int[v + 1];
        for (int[] item: items) {
            for (int j = v; j >= item[0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - item[0]] + item[1]);
            }
        }
        System.out.println(dp[v]);
    }
}

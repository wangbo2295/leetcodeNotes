package leetcode.binarySearch;

import java.util.Arrays;

public class No1648 {
    public static int MOD = 1_000_000_007;
    public int maxProfit(int[] inventory, int orders) {
        int l = 0, r = MOD;
        while (l + 1 < r) {
            int m = l + (r - l >> 1);
            if (check(inventory, orders, m)) r = m;
            else l = m;
        }
        return calc(inventory, orders, r);
    }

    //切记！加法不能取模，只有 a * b % p = (a % p) * (b % p) % p, 没有 (a + b) % p = ((a % p) + (b % p)) % p !!!
    public int calc(int[] inv, int orders, int floor) {
        long profit = 0, s = floor + 1;
        for (int j : inv) {
            if (j <= floor) continue;
            long n = j - floor;
            profit = (profit + ((s + j) * n) / 2) % MOD;
            orders -= n;
        }
        profit = (profit + (long) orders * floor) % MOD;
        return (int) profit;
    }

    public boolean check(int[] inv, int orders, int floor) {
        long sum = 0;
        for (int j : inv) {
            if (j <= floor) continue;
            sum += j - floor;
        }
        return sum <= orders;
    }
}

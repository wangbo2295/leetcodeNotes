package leetcode.math;

/**
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 */
public class No829 {
    /**
     * 思路：求i， j，使得
     * (2i + j - 1) * j / 2  = n
     * i = (2 * n / j + 1 - j) / 2
     * j的上限：
     * (1 + j) * j / 2 <= n
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        for (int j = 1; j * (j + 1) <= 2 * n; j++) {
            if (2 * n % j != 0) continue;
            if (((2 * n / j + 1 - j) & 1) == 0) {
                ans++;
            }
        }
        return ans;
    }
}

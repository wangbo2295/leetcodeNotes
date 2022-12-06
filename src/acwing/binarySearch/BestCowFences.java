package acwing.binarySearch;

import java.util.Scanner;

/**
 * 农夫约翰的农场由 N 块田地组成，每块地里都有一定数量的牛，其数量不会少于 1 头，也不会超过 2000 头。
 * 约翰希望用围栏将一部分连续的田地围起来，并使得围起来的区域内每块地包含的牛的数量的平均值达到最大。
 * 围起区域内至少需要包含 F 块地，其中 F 会在输入中给出。
 * 在给定条件下，计算围起区域内每块地包含的牛的数量的平均值可能的最大值是多少。
 */

/**
 * 知识点：
 * 1、如何判断数组中是否存在平均值不小于 avg 的子段？将每个数减去 avg，再求最大子段和是否大于 0
 * 2、如何求长度不小于L的最大子段和？
 *    转化为前缀和相减，枚举以每个数为结尾的最大子段和，其等于sum[i] - min(sum[j]), 0 <= j <= i - L
 *    从最小长度 i = L 开始枚举，求出 min(sum[j])，枚举 i + 1 时，前缀 1 ～ i - L 的最小值 minval 已求得，新增的前缀只有 sum[i + 1 - L],更新 minval 即可
 * 结合以上两点，就可以二分平均值，找到平均值的最大值了。
 *
 * 如何证明二分的正确性？即二分的单调性？
 * 设平均值avg下长度最小为L的最大子段和为 maxsum(avg，L), 设 t = avg - avg'
 * 则 maxsum(avg'，L) = maxsum(avg，L) + t
 * 如果 maxsum(avg，L) >= 0, avg > avg', 则maxsum(avg'，L) > 0，对于满足题目的 avg，所有小于 avg 的答案也满足
 * 如果 maxsum(avg，L) < 0, avg < avg', 则maxsum(avg'，L) < 0，对于不满足题目的 avg，所有大于 avg 的答案也不满足
 * 因此二分 avg 满足单调性，可以找到满足题目要求的最大的 avg
 */
public class BestCowFences {
    /**
     * 思路：
     * 朴素法：枚举所有长度大于等于 F 的子段，并计算平均值，取最大值。可通过前缀和快速求得子段和，时间复杂度O(n ^ 2)
     * 二分：如果对平均值进行二分，再将每个数减去平均值，就可以通过求最大子段和是否大于等于 0 而知道是否存在子段的平均值大于等于目标平均值。
     * 二分后得到最大的子段平均值。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), f = scanner.nextInt();
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = scanner.nextInt();
        }
        double l = 1, r = 2000;
        while (r - l > 1e-5) {
            double m = (l + r) / 2;
            if (valid(cows, m, f)) {
                l = m;
            } else {
                r = m;
            }
        }
        System.out.println((int) (l * 1000));
    }

    public static boolean valid(int[] cows, double average, int f) {
        double min_pre_sum = 0x3f;
        double max_sum = -1.0;
        double[] presum = new double[cows.length + 1];
        for (int i = 1; i <= cows.length; i++) {
            presum[i] = presum[i - 1] + cows[i - 1] - average;
        }
        for (int i = f; i < presum.length; i++) {
            min_pre_sum = Math.min(min_pre_sum, presum[i - f]);
            max_sum = Math.max(max_sum, presum[i] - min_pre_sum);
        }
        return max_sum >= 0;
    }
}

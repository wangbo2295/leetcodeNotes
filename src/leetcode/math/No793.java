package leetcode.math;

import java.util.ArrayList;
import java.util.Collections;

/**
 * f(x)是x!末尾是 0 的数量。回想一下x! = 1 * 2 * 3 * ... * x，且 0! = 1。
 * 例如，f(3) = 0，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2，因为 11!= 39916800 末端有 2 个 0 。
 * 给定k，找出返回能满足 f(x) = k的非负整数 x的数量。
 */
public class No793 {
    /**
     * 思路：x！的末尾有几个 0 ，取决于满足 10^k 整除 x！的最大的 k，即 x！的因子中最大的 10^k
     * 将 10^k 拆成 2^k * 5^k，仔细思考，发现在 x 增加的过程中，因子 2 的增加速度一定比 5 大
     * 例如从 1！到 5! 增加了 1 个因子 5，却增加了三个因子 2，因此因子 5 的个数 决定了 x！末尾有几个 0
     * 这就转化成计算因子 5 的个数为 k 的阶乘有多少个。
     * 如果我们知道 a！是有 k 个因子 5 中的最小数，b！是有 k + 1 个因子 5 中的最小数，那么满足题意的个数就为 b - a
     * 如果不存在末尾有 k 个 0 的阶乘，说明某个 x 包含了大于 1 个因子 5，且加上这些因子后因子 5 的个数从小于 k 直接变成大于 k。
     * 因此我们需要设计一个计算阶乘 x！中因子 5 的个数的函数 g(x)。
     * 仔细观察，发现 x！有多少个因子 5 取决于每个乘数一共贡献了多少个因子 5，对于 5^1，每个是 5^1 的倍数的数都会贡献 1 个因子 5
     * 对于 5^2，每个是 5^2 的数都会贡献 2 个 5 的因子，但计算 5^1 的贡献时计算过一个了，因此贡献为 1，对于所有 5 的幂，以此类推
     * 我们只要不断枚举 5 的幂，将 x / 5^i 加入结果，直到 5^i > x，返回结果即为 x！中因子 5 的个数
     * 然后二分 x，求出 g(k) = a, g(k + 1) = b，b - a 即为所求。
     * 具体实现中，我将 g(x) 定义为满足因子 5 的个数小于 k 的最大 x，( g(k), g(k + 1) ] （左开右闭）即为所有满足因子 5 个数为 k 的数
     * 对于不存在 a = g(k) 的情况，两次二分查找会找到同一个边界，即 g(k) = g(k + 1) = a，答案为 0。
     */
    public int preimageSizeFZF(int k) {
        long l = lowerBound(k);
        long r = lowerBound(k + 1);
        return (int)Math.max(0, r - l);
    }

    /**
     * 对于二分的上界，我们发现是超过了 int 的最大值的
     * 因为 k <= 10^9, 粗略的计算 g(k) 的最大值
     * 观察到所有的为 5 的幂次的 x，其因子 5 的个数组成为一个等比数列，公比 5，首项 1，项数为幂次
     * 求出等比数列求和公式 S = (5 ^ n - 1) / 4 <= 10^9, 解不等式求得 n 大约为 14
     * 再令 5^14 < 2^m，解得 m 大约为 32.x，因此至少需要 33 位来作为二分的上界，考虑用 long
     */
    public long lowerBound(int k) {
        if (k == 0) return -1;
        long l = 0, r = 1L << 33;   //
        while (l + 1 < r) {
            long m = l + r >> 1;
            long cnt = 0, p = 5;
            while (p <= m) {
                cnt += m / p;
                p *= 5;
            }
            if (cnt < k) l = m;
            else r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        No793 no793 = new No793();
        no793.preimageSizeFZF(3);
    }
}
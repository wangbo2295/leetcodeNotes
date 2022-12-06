package leetcode.divideAndConquer;

/**
 * 思路：
 * 将两个数切换到同一符号在计算，结果一定为正数，如果只有一个换了符号，那么结果取负
 * 由于负数最小值取反后会越界，所以统一切换成负数计算
 * 单独考虑边界的情况
 * 对于一般情况， 使用二分法进行查找，原理如下：
 * X为除数，Y为被除数，Z为商（X、Y都为负数，Z为正数）
 * Z * Y >= X > (Z + 1) * Y
 * 对于Z * Y 的计算，使用快速乘（类比快速幂）, 看Z * Y 是否 < X ,是则说明Z大了，否则Z小了
 */
public class No29 {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) return Integer.MIN_VALUE;
            if (divisor == -1) return Integer.MAX_VALUE;
        }
        if (divisor == Integer.MIN_VALUE) return dividend == Integer.MIN_VALUE ? 1 : 0;
        if (dividend == 0)  return 0;
        int res = 0;
        //将两个数都化成负数
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = - divisor;
            rev = !rev;
        }
        int left = 1, right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = right + (left - right >> 1);
            boolean check = quickAdd(dividend, divisor, mid);
            if (check) {
                res = mid;
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return rev ? -res : res;
    }

    /**
     * 快速乘（类比快速幂）
     * 3 * 7 = (1) -> (1 * 2 + 1) -> (3 * 2 + 1) = 7
     *  7(2) = 111
     *  3 * 7 = 3 * 2^2 + 3 * 2^1 + 3 * 2^0
     * @return
     */
    public boolean quickAdd(int x, int y, int z) {
        int res = 0;
        int add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 res + add >= x
                if (res < x - add) {
                    return false;
                }
                res += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }

}

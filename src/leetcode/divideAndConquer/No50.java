package leetcode.divideAndConquer;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 */
public class No50 {
    /**
     * 迭代法：
     * 举例：(x^76 * x) <- (x^38^2 * x) <- (x^19^2) <- (x^9^2 * x) <- (x^4^2 * x) <- (x^2^2) <- (x^2) <- (x)
     *          77 =  (38 * 2 + 1) <- (19 * 2) <- (9 * 2 + 1) <- (4 * 2 + 1) <- (2 * 2) <- (1 * 2) <- (1)
     *              77(2) = 1001101
     * 观察到指数的变化过程：由乘2和加1的操作组成，最后得到77，这可以用77的二进制表示方法描述这个过程
     * 因为 77 = 2^0 + 2^2 + 2^3 + 2^6;
     *     x^77 = x^(2^0) * x^(2^2) * x^(2^3) * x^(2^6);
     *     由此将x的幂与幂的二进制关联起来，只有二进制不为0的位才参与x的幂的构建
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            return 1.0 / (x * quickMul(x, -n - 1));
        }
        return n > 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }
    public double quickMul(double x, int n) {
        double res = 1.0;
        double _x = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= _x;
            }
            _x *= _x;
            n >>= 1;
        }
        return res;
    }
}

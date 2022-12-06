package acwing.位运算;

import java.util.Scanner;

/**
 * 求 a ^ b % p
 * 1 <= a, b, p <= 1e9
 */
public class QuickPow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextInt(); //由于以 a 作为快速幂的基，可能会溢出，所以用long
        int b = scanner.nextInt();
        int p = scanner.nextInt();
        long ans = 1 % p;
        //b = k0 * 2^0 + k1 * 2^1 + ... + ki * 2^i + ... + kn * 2^n
        //a ^ b = a ^ (k0 * 2^0) * a ^ (k1 * 2^1) * ... * a ^ (ki * 2^i) * ... * a ^ (kn * 2^n)
        //ki 为 0 或 1 。
        //当 ki 为 0 时，a ^ (ki * 2^i) = 1
        //当 ki 为 1 时，a ^ (ki * 2^i) = a ^ (2 ^ i)
        //由此可知，只需要递推地计算 a ^ (2 ^ i),且当 b 的第 i 位为 1 时，将 a 乘到结果里面即可
        for (; b > 0; b >>= 1) {
            if ((b & 1) == 1) {
                ans = (ans * a) % p;
            }
            a = (a * a) % p;
        }
        System.out.println(ans);
    }
}

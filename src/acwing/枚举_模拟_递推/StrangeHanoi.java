package acwing.枚举_模拟_递推;


import java.util.Arrays;

/**
 * 奇怪的汉诺塔：n 片 4 塔
 */
public class StrangeHanoi {

    /**
     * 思路：
     * 已知三塔的递推公式：f[n] = 2 * f[n - 1] + 1
     * 四塔过程：先按照四塔方式将 m 个移到 B ，再以三塔方式将 n - m 个移到 C ，再以四塔方式将 B 上的 m 个移到 C 上
     * g[n] = min{2 * g[m] + f[n - m]} (0 < m < n)
     * @param args
     */
    public static void main(String[] args) {
        int[] g = new int[13];
        Arrays.fill(g, 0x3f);
        g[0] = 0;
        g[1] = 1;
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j < i; j++) {
                g[i] = Math.min(g[i], 2 * g[j] + (1 << (i - j)) - 1);
            }
            System.out.println(g[i]);
        }
    }
}

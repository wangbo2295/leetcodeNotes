package acwing.枚举_模拟_递推;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TallestCow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), p = scanner.nextInt(), h = scanner.nextInt(), m = scanner.nextInt();
        int[] cows = new int[n + 1];
        cows[0] = h;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt(), b = scanner.nextInt();
            if (a > b)  {
                int t = a;
                a = b;
                b = t;
            }
            if (!set.add("" + a + "-" + b)) continue;
            //这里用到了前缀和的技巧，由于 a 到 b 之间都要 -1，可以转化为求前缀和
            //只需要将 cows[a + 1] - 1， 求前缀和时会自动贡献到之后的结果中
            //而把 cows[b] + 1，求到此处会把之前的-1恢复，即 b 之后的不会再 -1。
            cows[a + 1]--;
            cows[b]++;
        }
        for (int i = 1; i <= n; i++) {
            cows[i] += cows[i - 1];
            System.out.println(cows[i]);
        }
    }
}

package acwing.枚举_模拟_递推;

import java.util.Scanner;

public class ThePilotsBrothersRefrige {
    /**
     * 1、每个位置最多点击一次，因为两次相当于没点击
     * 2、直接枚举所有点击可能 2 ^ 16 - 1
     * 3、对于初始为开的把手，其所在行列被点击的次数之和为偶数，对于初始为关的把手，其所在行列被点击次数之和为奇数
     * 4、利用 lowbit 计算点击次数，选次数最小的，同时保存次数最小的点击方案，再用位运算计算出点击位置
     * @param args
     */
    static int[] frige = new int[16];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String[] row = scanner.next().split("");
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals("+")) {
                    frige[i * 4 + j] = 1;
                } else {
                    frige[i * 4 + j] = 0;
                }
            }
        }
        int[] hash = getHash();
        int min = 17, plan = -1;
        for (int i = 1; i < 1 << 16; i++) {
            int[] rows = new int[4];
            int[] cols = new int[4];
            int t = i, cnt = 0;
            while (t > 0) {
                int lowbit = t & -t;
                int n = hash[lowbit % 37];
                rows[n / 4]++;
                cols[n % 4]++;
                t -= lowbit;
                ++cnt;
            }
            if (valid(rows, cols, i) && cnt < min) {
                min = cnt;
                plan = i;
            }
        }
        System.out.println(min);
        while (plan > 0) {
            int lowbit = plan & -plan;
            int n = hash[lowbit % 37];
            System.out.println((n / 4 + 1) + " " + (n % 4 + 1));
            plan -= lowbit;
        }
    }
    public static int[] getHash() {
        int[] hash = new int[37];
        for (int i = 0; i < 31; i++)    hash[(1 << i) % 37] = i;
        return hash;
    }
    public static boolean valid (int[] rows, int[] cols, int plan) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int sum = rows[r] + cols[c], stat = frige[r * 4 + c];
                if ((1 << (r * 4 + c) & plan) != 0) sum--;
                if ((stat == 1 && (sum & 1) == 0) || (stat == 0 && ((sum & 1) == 1))) {
                    return false;
                }
            }
        }
        return true;
    }
}

package acwing.binarySearch;

import java.util.Scanner;

/**
 * 防线（差点给我整破防了）
 * 给定 N 行数据，每行包括 S, E, D 三个数，分别表示从 S 开始，每隔 D 布置一个防具， S + K * D <= E
 * 只有一个位置的防具数量为奇数，找出这个位置和防具数量（0也算偶数），如果没有奇数位置，则输出 "There's no weakness."
 */
public class DefenceLine {
    /**
     * 思路：
     * 二分位置，如果左半区防具总数为偶数，说明在右半区，否则在左半区
     * 由于数据范围在 0 ～ Integer.MAX_VALUE，不能用数组保存前缀和，需要即时计算
     * 计算方式：如果指定前缀与防线区间有交集，即 x >= s, 那么交集的左区间显然是 s，右区间为 min(e, x)， 防具个数为 min(e, x) - s + 1 (起点也算)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int[][] sheilds = new int[n][3];
            for (int j = 0; j < n; j++) {
                int s = scanner.nextInt();
                int e = scanner.nextInt();
                int d = scanner.nextInt();
                sheilds[j] = new int[]{s, e, d};
            }
            int sum = presum(sheilds, Integer.MAX_VALUE);
            if ((sum & 1) == 0) {
                System.out.println("There's no weakness.");
                continue;
            }
            int l = 0, r = Integer.MAX_VALUE;
            while (l + 1 < r) {
                int m = l + (r - l >> 1);
                if ((presum(sheilds, m) - presum(sheilds, l - 1) & 1) == 0) {
                    l = m;
                } else {
                    r = m;
                }
            }
            System.out.println(r + " " + (presum(sheilds, r) - presum(sheilds, l)));
        }
    }

    static public int presum(int[][] sheilds, int index) {
        int cnt = 0;
        for (int[] sheild : sheilds) {
            if (sheild[0] > index) continue;
            int e = Math.min(sheild[1], index);
            cnt += (e - sheild[0]) / sheild[2] + 1;
        }
        return cnt;
    }
}

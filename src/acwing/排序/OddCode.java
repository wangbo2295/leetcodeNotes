package acwing.排序;

import java.util.Scanner;

/**
 * 奇数码问题
 * 有一个 n * n 的网格，里面有 n * n - 1 个数和一个空格，空格可以与相邻的数交换位置
 * n 为奇数
 * 给定两个局面，问能否相互转换
 */
public class OddCode {

    /**
     * 思路：
     * 如果把这些数按照每行行依次排成一行，那么空格与左右的交换不会改变顺序，
     * 与上下的交换会使得被交换的数与前/后 n - 1 个数交换位置，
     * 每次与相邻的数交换位置对逆序对的改变只能为 1 或 -1，由于 n - 1 为偶数，所以逆序对的改变一定为偶数
     * 因为偶数只能拆成两个奇数或两个偶数，无论哪种相减后都为偶数
     * 这样就可以通过计算两个局面的逆序对的奇偶性是否相同来判断能否互相转化了。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[][] matirx = new int[2][n * n - 1];
            int[] cnt = new int[2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n * n - 1; j++) {
                    int num = scanner.nextInt();
                    if (num == 0)   num = scanner.nextInt();
                    matirx[i][j] = num;
                }
            }
            mergeNCount(matirx, 0, 0, n * n - 1, cnt);
            mergeNCount(matirx, 1, 0, n * n - 1, cnt);
            System.out.println(cnt[0] == cnt[1] ? "TAK" : "NIE");
        }
    }

    public static void mergeNCount(int[][] matrix, int idx, int l, int r, int[] cnt) {
        if (l + 1 == r) return;
        int m = l + r >> 1;
        mergeNCount(matrix, idx, l, m, cnt);
        mergeNCount(matrix, idx, m, r, cnt);
        int n = r - l;
        int[] t = new int[n];
        for (int i = 0, p1 = l, p2 = m; i < n; i++) {
            if (p2 == r || (p1 < m && matrix[idx][p1] > matrix[idx][p2])) {
                t[i] = matrix[idx][p1++];
                cnt[idx] = (cnt[idx] + (r - p2)) & 1;
            } else {
                t[i] = matrix[idx][p2++];
            }
        }
        System.arraycopy(t, 0, matrix[idx], l, n);
    }
}

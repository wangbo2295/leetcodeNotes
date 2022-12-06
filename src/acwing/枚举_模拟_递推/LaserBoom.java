package acwing.枚举_模拟_递推;

import java.util.Scanner;

public class LaserBoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), r = scanner.nextInt();
        int[][] targets = new int[5001][5001];
        for (int i = 0; i < n; i++) {
            int xi = scanner.nextInt(), yi = scanner.nextInt(), wi = scanner.nextInt();
            targets[xi + 1][yi + 1] += wi;
        }
        for (int i = 1; i <= 5000; i++) {
            for (int j = 1; j <= 5000; j++) {
                targets[i][j] += targets[i - 1][j] + targets[i][j - 1] - targets[i - 1][j - 1];
            }
        }
        int max = 0;
        for (int i = r; i <= 5000; i++) {
            for (int j = r; j <= 5000; j++) {
                int li = Math.max(0, i - r), lj = Math.max(0, j - r);
                max = Math.max(max, targets[i][j] - targets[li][j] - targets[i][lj] + targets[li][lj]);
            }
        }
        System.out.println(max);
    }
}

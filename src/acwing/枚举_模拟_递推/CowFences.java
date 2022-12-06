package acwing.枚举_模拟_递推;

import java.util.Scanner;

public class CowFences {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int n = scanner.nextInt();
        int N = 10000;
        int[][] lands = new int[N + 1][N + 1];
        for (int i = 1; i <= n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            lands[x][y]++;
        }
        for (int i = 1; i <= 10000; i++) {
            for (int j = 1; j <= 10000; j++) {
                lands[i][j] += lands[i - 1][j] + lands[i][j - 1] - lands[i - 1][j - 1];
            }
        }
        for (int k = 1; k <= N; k++) {
            for (int i = k; i <= N; i++) {
                for (int j = k; j <= N; j++) {
                    if (lands[i][j] >= c) {
                        System.out.println(k);
                    }
                }
            }
        }
        String s = "sda";
        s.hashCode();
    }
}

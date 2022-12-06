package acwing.递归;

import java.util.Scanner;

public class FractalCity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            long N = Long.parseLong(scanner.next()), A = Long.parseLong(scanner.next()), B = Long.parseLong(scanner.next());
            long[] posA = fractalCity(N, A - 1);
            long[] posB = fractalCity(N, B - 1);
            long a = posA[0] - posB[0], b = posA[1] - posB[1];
            System.out.println(Math.round(Math.sqrt(a * a + b * b) * 10));
        }
    }

    public static long[] fractalCity(long N, long A) {
        if (N == 0) return new long[] {0, 0};
        long n = ((1L << 2 * (N - 1))), len = 1L << (N - 1);
        long[] oldPos = fractalCity(N - 1, A % n);
        long x = oldPos[0], y = oldPos[1];
        switch ((int) (A / n)) {
            case 0:
                return new long[] {y, x};
            case 1:
                return new long[] {x, len + y};
            case 2:
                return new long[] {len + x, len + y};
            case 3:
                return new long[] {2 * len - y - 1, len - x - 1};
            default:
                return new long[] {-1, -1};
        }
    }
}

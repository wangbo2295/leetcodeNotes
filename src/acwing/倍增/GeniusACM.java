package acwing.倍增;

import java.util.Arrays;
import java.util.Scanner;

public class GeniusACM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < k; i++) {
            String[] params = scanner.nextLine().split(" ");
            String[] nums = scanner.nextLine().split(" ");
            int n = Integer.parseInt(params[0]), m = Integer.parseInt(params[1]);
            long t = Long.parseLong(params[2]);
            int[] A = new int[n];
            for (int j = 0; j < nums.length; j++) {
                A[j] = Integer.parseInt(nums[j]);
            }
            int cnt = 0, l = 0;
            while (l < n) {
                int r = l, p = 1;
                while (p > 0) {
                    if (r + p < n && check(A, l, r + p, m, t)) {
                        r += p;
                        p <<= 1;
                    } else {
                        p >>= 1;
                    }
                }
                ++cnt;
                l = r + 1;
            }
            System.out.println(cnt);
        }
    }

    public static boolean check(int[] nums, int l, int r, int m, long t) {
        int n = r - l + 1;
        int[] tmp = new int[n];
        System.arraycopy(nums, l, tmp, 0, n);
        Arrays.sort(tmp);
        long sum = 0;
        for (int i = 0; i < Math.min(n >> 1, m); i++) {
            long diff = tmp[n - i - 1] - tmp[i];
            sum += diff * diff;
        }
        return sum <= t;
    }
}

package acwing.binarySearch;

import java.util.Scanner;

public class LongestPalindrome {

    public static int base = 131;
    public static int N = 2000010;
    public static long[] hl = new long[N];
    public static long[] hr = new long[N];
    public static long[] p = new long[N];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        int t = 1;
        while (!(str = scanner.next()).equals("END")) {
            int n = str.length() * 2;
            char[] strs = new char[n];
            p[0] = 1;
            for (int i = n - 1; i > 0; i -= 2) {
                strs[i] = str.charAt(i >> 1);
                strs[i - 1] = 'z' + 1;
            }
            for (int i = 1, j = n - 1; i < n; i++, j--) {
                hl[i] = hl[i - 1] * base + strs[i] - 'a' + 1;
                hr[i] = hr[i - 1] * base + strs[j] - 'a' + 1;
                p[i] = p[i - 1] * base;
            }
            int max = 0;
            for (int i = 1; i < n; i++) {
                int l = -1, r = Math.min(i - 1, n - i - 1) + 1, len;
                while (l + 1 < r) {
                    int m = l + r >> 1;
                    if (get(hl, i - m, i - 1) == get(hr, n - (i + m), n - (i + 1))) {
                        l = m;
                    } else {
                        r = m;
                    }
                }
                len = strs[i - l] <= 'z' ? l + 1 : l;
                max = Math.max(max, len);
            }
            System.out.println("Case " + t++ + ": " + max);
        }
    }
    public static long get(long[] h, int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}

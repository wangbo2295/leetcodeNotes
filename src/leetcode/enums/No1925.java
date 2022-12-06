package leetcode.enums;

public class No1925 {
    public int countTriples(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (i * i >= n) break;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (j * j >= n) break;
                if (isC(i * i + j * j, n)) ++cnt;
            }
        }
        return cnt;
    }
    boolean isC(int num, int n) {
        int l = 0, r = num + 1;
        while (l + 1 < r) {
            int m = l + (r - l >> 1);
            if (m * m <= num) {
                l = m;
            } else {
                r = m;
            }
        }
        return l * l == num && l <= n;
    }
}

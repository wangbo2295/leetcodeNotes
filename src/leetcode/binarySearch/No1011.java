package leetcode.binarySearch;

public class No1011 {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 5 * 10000 * 500 + 1;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (valid(weights, days, m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public boolean valid(int[] weights, int days, int max) {
        int sum = 0, cnt = 0;
        for (int i = 0; i < weights.length; i++) {
            if (sum + weights[i] <= max) {
                sum += weights[i];
            } else {
                sum = 0;
                ++cnt;
            }
        }
        return cnt <= days;
    }
}

package acwing.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class FindRightIntervals {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] res = new int[n];
        int[][] newIntervals = new int[n][3];
        for (int i = 0; i < intervals.length; i++) {
            System.arraycopy(intervals[i], 0, newIntervals[i], 0, 2);
            newIntervals[i][2] = i;
        }
        Arrays.sort(newIntervals, Comparator.comparingInt(o->o[0]));
        for (int i = 0; i < n; i++) {
            int target = newIntervals[i][1];
            int l = i, r = n;
            while (l + 1 < r) {
                int m = l + r >> 1;
                if (newIntervals[m][0] >= target) {
                    r = m;
                } else {
                    l = m;
                }
            }
            if (r == n) res[newIntervals[i][2]] = -1;
            else res[newIntervals[i][2]] = newIntervals[r][2];
        }
        return res;
    }
}

package leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class No57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)  return new int[][] {newInterval};
        List<int[]> res = new ArrayList<>();
        int l = -1, r = intervals.length, n = intervals.length;
        while (l + 1 < r) {
            int m = l + (r - l >> 1);
            if (intervals[m][0] <= newInterval[0]) {
                l = m;
            } else {
                r = m;
            }
        }
        for (int i = 0; i < l; i++) res.add(intervals[i]);
        int[] pre;
        if (l < 0 || l == n) {
            pre = newInterval;
        } else if (intervals[l][1] < newInterval[0]) {
            res.add(intervals[l]);
            pre = newInterval;
        } else {
            intervals[l][0] = Math.min(intervals[l][0], newInterval[0]);
            intervals[l][1] = Math.max(intervals[l][1], newInterval[1]);
            pre = intervals[l];
        }
        for (int i = l + 1; i < n; i++) {
            if (intervals[i][0] <= pre[1]) {
                intervals[i][0] = Math.min(intervals[i][0], pre[0]);
                intervals[i][1] = Math.max(intervals[i][1], pre[1]);
            } else {
                res.add(pre);
            }
            pre = intervals[i];
        }
        res.add(pre);
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}

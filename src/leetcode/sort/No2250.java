package leetcode.sort;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 给你一个二维整数数组rectangles，其中rectangles[i] = [li, hi]表示第i个矩形长为li高为hi。给你一个二维整数数组oints，其中points[j] = [xj, yj]是坐标为 (xj, yj) 的一个点。
 *
 * 第 i 个矩形的 左下角 在 (0, 0) 处，右上角 在 (li, hi) 。
 *
 * 请你返回一个整数数组 count ，长度为 points.length，其中 count[j]是 包含 第 j 个点的矩形数目。
 *
 * 如果 0 <= xj <= li 且 0 <= yj <= hi ，那么我们说第 i 个矩形包含第 j 个点。如果一个点刚好在矩形的 边上 ，这个点也被视为被矩形包含。
 */
public class No2250 {
    /**
     * 由于 y 的范围很小，可以将矩形和点按 y 从大到小排序
     * 从大到小遍历每个点，将所有 y 大于等于该点的矩形的 x 加入list，排序后二分可找出 x， y 均大于等于该点，即包含的数目
     * 由于是从大到小遍历，所以已放入list的矩形的 y 是一定大于等于之后遍历的点的 y 的，只需在有新的 y 大于当前点的 y 的矩形时加入其 x 即可
     * 如果遍历当前点时没有加入新的矩形，则不进行排序。
     */
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = points.length;
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(rectangles, Comparator.comparingInt((int[] o)->o[1]).reversed());
        Arrays.sort(ids, Comparator.comparingInt((Integer o)->points[o][1]).reversed());
        List<Integer> xs = new ArrayList<>();
        int i = 0;
        int[] ans = new int[n];
        for (Integer id : ids) {
            int s = i;
            while (i < rectangles.length && rectangles[i][1] >= points[id][1]) {
                xs.add(rectangles[i++][0]);
            }
            if (s < i)
                Collections.sort(xs);
            ans[id] = count(xs, points[id][0]);
        }
        return ans;
    }

    public int count(List<Integer> xs, int target) {
        int l = -1, r = xs.size();
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (xs.get(m) < target) {
                l = m;
            } else {
                r = m;
            }
        }
        return xs.size() - r;
    }
}

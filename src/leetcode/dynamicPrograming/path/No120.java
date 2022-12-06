package leetcode.dynamicPrograming.path;

import java.util.List;

/**
 * 三角形最小路径和
 * 思路： 自底向上
 */
public class No120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            List<Integer> cur = triangle.get(i);
            List<Integer> next = triangle.get(i + 1);
            for (int j = 0; j < cur.size(); j++) {
                int curMin = cur.get(j) + Math.min(next.get(j), next.get(j + 1));
                cur.set(j, curMin);
                min = Math.min(curMin, min);
            }
        }
        return triangle.get(0).get(0);
    }
}

package leetcode.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * m * n 的矩阵中每行选一个数求总和，要求第 k 小的和
 */
public class KthSmallest {
    /**
     * 思路：如果是求前 n 小的和，可以采用两两合并的策略
     * 具体做法是，将第一个数组排序，然后把第二个数组中的值和对应第一个数组的下标成对放入小顶堆中，按和排序
     * 每次取出堆顶元素，并将其对应的第一个数组的下标 + 1 再放入堆中，这样每次取出来的都是最小的和
     * 回到本题，求得是第 k 小的，k在 0 ～ m * n 之间，k 可能大于 n
     * 因此我们每次合并求出 min(k, n * n) 个最小和，因为两个数组最多有 n * n 种组合。
     * 所以考虑用 list 实现
     */
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        List<Integer> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i < n; i++) cur.add(mat[0][i]);
        for (int i = 1; i < m; i++) {
            ans.clear();
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[0] + cur.get(o[1])));
            for (int j = 0; j < n; j++) pq.offer(new int[] {mat[i][j], 0});
            for (int j = 0; j < Math.min(k, cur.size() * n); j++) {
                int[] ints = pq.poll();
                ans.add(ints[0] + cur.get(ints[1]));
                if (++ints[1] < cur.size()) {
                    pq.offer(ints);
                }
            }
            cur.clear();
            cur.addAll(ans);
        }
        return cur.get(k - 1);
    }
}

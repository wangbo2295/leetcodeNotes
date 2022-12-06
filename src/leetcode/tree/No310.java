package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含n个节点的树，标记为0到n - 1 。给定数字n和一个有 n - 1 条无向边的 edges列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 */
public class No310 {
    /**
     * 思路：求以某个节点为根节点时，到所有点距离最大值最小的节点
     * dijistra： 广度优先搜索
     * 此解法超时了
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] nextNode = new List[n];
        for (int i = 0; i < nextNode.length; i++) {
            nextNode[i] = new ArrayList<>();
        }
        int[] dists = new int[n];
        for (int[] edge : edges) {
            nextNode[edge[0]].add(edge[1]);
            nextNode[edge[1]].add(edge[0]);
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            int dist = 0;
            int[] status = new int[n];
            status[i] = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    int k = queue.poll();
                    for (Integer integer : nextNode[k]) {
                        if (status[integer] == 0) {
                            queue.add(integer);
                            status[integer] = 1;
                        }
                    }
                }
                dist++;
            }
            if (dist != 1) {
                minDist = Math.min(minDist, dist - 1);
            }
            dists[i] = dist - 1;
        }
        for (int i = 0; i < dists.length; i++) {
            if (dists[i] == minDist) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        No310 no310 = new No310();
        int[][] nums = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        no310.findMinHeightTrees(6, nums);
    }
}

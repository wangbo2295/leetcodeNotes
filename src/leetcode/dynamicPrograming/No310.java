package leetcode.dynamicPrograming;

import java.util.*;

/**
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含n个节点的树，标记为0到n - 1 。
 * 给定数字n和一个有 n - 1 条无向边的 edges列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 */
public class No310 {
    /**
     * 思路：
     * 求出无向图中所有点对路径，对于某个节点，以其作为根节点的树的高度为路径最大值
     * 对每个节点作为根节点的情况计算高度的最小值，将所有满足最小值的节点放入结果集
     *
     * 对每个节点进行广搜会超时，要上动态规划
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            List<Integer> list0;
            List<Integer> list1;
            if (map.containsKey(edges[i][0]))   list0 = map.get(edges[i][0]);
            else    list0 = new ArrayList<>();
            if (map.containsKey(edges[i][1]))   list1 = map.get(edges[i][1]);
            else    list1 = new ArrayList<>();
            list1.add(edges[i][0]);
            list0.add(edges[i][1]);
            map.put(edges[i][0], list0);
            map.put(edges[i][1], list1);
        }
        int minHeight = Integer.MAX_VALUE;
        int[] heights = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            boolean[] visted = new boolean[n];
            visted[i] = true;
            queue.offer(i);
            int height = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    Integer nexts = queue.poll();
                    List<Integer> list = map.get(nexts);
                    for (int k = 0; k < list.size(); k++) {
                        if (visted[list.get(k)])    continue;
                        queue.offer(list.get(k));
                        visted[list.get(k)] = true;
                    }
                }
                height++;
            }
            heights[i] = height;
            minHeight = Math.min(minHeight, height);
        }
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == minHeight) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * todo：
     * 动态规划：
     * 本题保证是一棵树，所以两点之间路径唯一
     * 求出所有点对最短距离，再一每个节点为根节点求高度的最小值
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        return res;
    }
}

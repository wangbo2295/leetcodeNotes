package leetcode.graph;

import java.util.*;

/**
 * 在树中添加一条边，要求找到一条可删除的边使得其变回一棵树
 * 删除的边要求是在 edges 中出现的最后一条边
 */
public class No684 {

    // TODO: 2022/9/2 并查集解法，不断合并两个点，直到被合并的两个点已经在一个集合中，说明此时产生了环 
    /**
     * 思路：添加一条边相当于在树中形成了一个环，关键就是要找到环中的每条边
     * 可以随机选择一个顶点进行dfs，并不断把每条边加入圈集合，在没找到圈时回溯
     * 由于随机选择的起点不一定是圈的某个点，因此可能存在一段不属于圈的边
     * 用一个双出队列保存加入的边，找到圈时，将队首不属于圈的边都删除
     */

    Deque<int[]> deque = new LinkedList<>();
    List<List<Integer>> eds = new ArrayList<>();
    List<int[]> circle = new ArrayList<>();
    boolean[] vis;
    int n;
    public int[] findRedundantConnection(int[][] edges) {
        n = edges.length;
        vis = new boolean[n + 1];
        for (int i = 0; i <= n; i++) eds.add(new ArrayList<>());
        for (int[] edge : edges) {
            eds.get(edge[0]).add(edge[1]);
            eds.get(edge[1]).add(edge[0]);
        }
        vis[1] = true;
        dfs(1, 0);
        for (int i = n - 1; i >= 0; i--) {
            int c = edges[i][0], d = edges[i][1];
            for (int[] ints : circle) {
                int a = ints[0], b = ints[1];
                if (a * b == c * d && a + b == c + d)   return edges[i];
            }
        }
        return circle.get(0);
    }

    public boolean dfs(int root, int pre) {
        List<Integer> verts = eds.get(root);
        for (int v : verts) {
            if (v == pre)   continue;
            if (!vis[v]) {
                vis[v] = true;
                deque.addLast(new int[]{root, v});
                if (dfs(v, root)) return true;
                deque.pollLast();
            } else {
                deque.addLast(new int[]{root, v});
                while (!deque.isEmpty() && deque.getFirst()[0] != v) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty()) {
                    int[] ints = deque.pollFirst();
                    circle.add(ints);
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.substring(2, s.length() - 2);
        String[] split = s.split("],\\[");
        int n = split.length;
        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] edge = split[i].split(",");
            edges[i][0] = Integer.parseInt(edge[0]);
            edges[i][1] = Integer.parseInt(edge[1]);
        }
        No684 no684 = new No684();
        no684.findRedundantConnection(edges);
    }
}

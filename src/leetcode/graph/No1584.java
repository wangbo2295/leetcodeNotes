package leetcode.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 */
public class No1584 {

    /**
     * Kruskal算法
     * 实现思路：
     * 1、以边为视角，每次选出最小的边连接起来
     * 2、是否选择该条边取决于加入这条边后树中会不会有圈
     * 3、可以将每个不连通的树看成不同的树，一起组成一个森林，当考虑某一条边时，如果两个端点在同一棵树内，则加入该条边会形成圈，破坏树的结构，
     * 只有两个端点不在同一棵树内时才加入该条边
     * 4、如何判断是否连通？
     * 并查集。
     * 初始时，每个节点在自己的集合内，每增加一条边，就将两个端点所在的集合合并，需要实现union/find方法
     * 具体实现方法可以用一个数组保存每个节点的父节点，初始时都是根节点。
     * find：数组每个下标存储的为当前下标节点的父节点下标，初始时均设为-1，表示根节点。如果要寻找根节点，一直迭代至当前值为负数即可
     * union：如果进行一次合并操作，就将两个节点的根节点找到，如果两个根节点不为同一节点才合并
     * 5、如何选取最小边？
     * 虽然该题是无向图，但如果以边的角度考虑问题，就只需要计算一次边的长度
     * 遍历点集，计算出任意两点间的曼哈顿距离，放入优先队列中，每次拿出的边就是最小边
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o->o.edge));
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int edge = manhattanDist(points[i], points[j]);
                edges.offer(new Edge(i, j, edge));
            }
        }
        int cnt = 0, minCost = 0;
        int[] set = new int[points.length];
        while (cnt < points.length - 1) {
            Edge poll = edges.poll();
            int uset = find(set, poll.u);
            int vset = find(set, poll.v);
            if (uset != vset) {
                union(set, uset, vset);
                cnt++;
                minCost += poll.edge;
            }
        }
        return minCost;
    }

    public int manhattanDist(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    class Edge {
        int u;
        int v;
        int edge;

        public Edge(int u, int v, int edge) {
            this.u = u;
            this.v = v;
            this.edge = edge;
        }
    }

    public void union(int[] set, int u, int v) {
        int uset = find(set, u);
        int vset = find(set, v);
        if (uset != vset) set[uset] = vset;
    }

    public int find(int[] set, int index) {
        while (set[index] > 0) {
            index = set[index];
        }
        return index;
    }
}


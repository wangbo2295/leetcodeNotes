package leetcode.graph;

import leetcode.deepFirstSearch.剑指offerIINo85;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class No1514 {

    int N = 40010;
    int[] head = new int[N >> 2];   //head 和 next 数组存储的是指向 ver 下标的指针
    int[] ver = new int[N];         //ver 存储的是节点编号
    int[] next = new int[N];
    double[] edge = new double[N];  //edge 存储的是边权
    int tot = 1;                    //tot 是每条边的编号
    boolean[] vis = new boolean[N >> 2];
    double[] cost = new double[N >> 2];

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0] + 1, edges[i][1] + 1, succProb[i]);
            addEdge(edges[i][1] + 1, edges[i][0] + 1, succProb[i]);
        }
        Arrays.fill(cost, 0);
        cost[start + 1] = 1.0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble((Integer o)->cost[o]).reversed());
        pq.offer(start + 1);
        while (!pq.isEmpty()) {
            Integer cur = pq.poll();
            vis[cur] = true;
            for (int i = head[cur]; i != 0; i = next[i]) {
                // i 是 ver 数组下标，ver[i] 为当前节点编号，ver[i ^ 1] 为起点编号
                // cost[ver[i]] 和 cost[ver[i ^ 1]] 分别为当前节点花费和起点节点花费, edge[i] 为当前边的边权值
                // 切记不能将 ver[i] 和 i 搞混了，i 是指针，ver[i] 是当前边的终点节点编号
                // ver[i] 并不是节点，而是某条边的到达节点
                // ver、edge 跟边对应，而 head、cost、vis 跟节点对应，要通过 ver 和 edge 拿到一条边的节点编号或边权值，才能操作节点的属性
                cost[ver[i]] = Math.max(cost[ver[i]], cost[ver[i ^ 1]] * edge[i]);
                if (!vis[ver[i]]) pq.offer(ver[i]);
            }
        }
        return cost[end + 1];
    }

    //对于无向图，每条边看成两条边，head 和 tot 指向 ver 下标的同时也代表一条边，所以用 tot 来记录
    //对于 2-3、 4-5 。。。 这样的存储方式，通过对下标 xor 1 即可得到当前边的反向边，如果 ver[i] 是第 i 条边的终点，那么 ver[i xor 1] 就是起点
    private void addEdge(int x, int y, double z) {
        ver[++tot] = y;
        edge[tot] = z;
        next[tot] = head[x];
        head[x] = tot;
    }

    public static void main(String[] args) {
        No1514 no1514 = new No1514();
        int[][] edges = {{0,1},{1,2},{0,2}};
        double[] succProb = {0.5,0.5,0.2};
        int start = 0, end = 2, n = 3;
        no1514.maxProbability(n, edges, succProb, start, end);
    }
}

package acwing.图.拓扑排序;

import java.util.*;

/**
 * 给定一张 N 个点 M 条边的有向无环图，分别统计从每个点出发能够到达的点的数量。
 * 输入格式
 * 第一行两个整数 N,M ，接下来 M 行每行两个整数 x,y ，表示从 x 到 y 的一条有向边。
 * 输出格式
 * 输出共 N 行，表示每个点能够到达的点的数量。
 * 数据范围
 * 1≤N,M≤30000
 */
public class AccessibleCount {
    public static Map<Integer, Node> graph = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        for (int i = 1; i <= n; i++)    graph.put(i, new Node(i, new ArrayList<>(), new BitSet(), 0));
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt(), to = scanner.nextInt();
            Node fr = graph.get(from);
            Node two = graph.get(to);
            fr.verts.add(two);
            two.degree++;
        }
        Queue<Node> queue = new LinkedList<>();
        List<Node> top = new ArrayList<>();
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            node.access.set(node.id);
            if (node.degree == 0)   queue.offer(node);
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            top.add(cur);
            for (Node next : cur.verts) {
                if (--next.degree == 0) queue.offer(next);
            }
        }
        for (int i = top.size() - 1; i >= 0; i--) {
            Node cur = top.get(i);
            for (Node next : cur.verts) {
                cur.access.or(next.access);
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(graph.get(i).access.stream().count());
        }
    }

    static class Node {
        int id;
        List<Node> verts;
        BitSet access;
        int degree;

        public Node() {
        }

        public Node(int id, List<Node> verts, BitSet access, int degree) {
            this.id = id;
            this.verts = verts;
            this.access = access;
            this.degree = degree;
        }
    }
}

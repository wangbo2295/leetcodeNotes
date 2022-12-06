package acwing.链表_邻接表;

import java.util.*;

public class QueryNeighborVal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] nodes = new Node[n + 1];
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            Node node = new Node(i, scanner.nextInt());
            nodes[i] = node;
            map.put(i, node);
        }
        Arrays.sort(nodes, Comparator.comparingInt(Node::getVal));
        for (int i = 1; i <= n; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }
        int[][] ans = new int[n + 1][2];
        for (int i = n; i > 1; i--) {
            Node cur = map.get(i);
            Node near;
            if (cur.prev == nodes[0]) near = cur.next;
            else if (cur.next == null) near = cur.prev;
            else  near = cur.val - cur.prev.val <= cur.next.val - cur.val ? cur.prev : cur.next;
            ans[i][0] = Math.abs(cur.val - near.val);
            ans[i][1] = near.id;
            cur.remove();
        }
        for (int i = 2; i <= n; i++) System.out.println(ans[i][0] + " " + ans[i][1]);
    }

    static class Node {
        int id;
        int val;
        Node prev;
        Node next;
        public Node(int id, int val, Node prev, Node next) {this.id = id;this.val = val;this.prev = prev;this.next = next;}
        public Node(int id, int val) {this(id, val, null, null);}
        public int getVal() {return val;}
        public void remove() {
            if (prev != null)
                this.prev.next = next;
            if (next != null)
                this.next.prev = prev;
        }
    }
}

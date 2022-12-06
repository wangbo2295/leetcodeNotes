package leetcode.graph;

import java.util.*;

public class No743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Vertex[] map = new Vertex[n + 1];
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.time));
        for (int i = 1; i <= n; i++) {
            if (i == k) map[i] = new Vertex(i, 0, false, new HashMap<>());
            else map[i] = new Vertex(i, Integer.MAX_VALUE / 2, false, new HashMap<>());
        }
        for (int[] time : times) {
            int from = time[0], to = time[1], cost = time[2];
            Vertex vertex = map[from];
            vertex.cost.put(map[to], cost);
        }
        pq.offer(map[k]);
        int t = 0;
        while (!pq.isEmpty()) {
            Vertex poll = pq.poll();
            poll.known = true;
            t = poll.time;
            for (Map.Entry<Vertex, Integer> entry : poll.cost.entrySet()) {
                Vertex key = entry.getKey();
                Integer value = entry.getValue();
                if (!key.known && poll.time + value < key.time) {
                    key.time = poll.time + value;
                    pq.offer(key);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!map[i].known) return -1;
        }
        return t;
    }
}

class Vertex {
    int id;
    int time;
    boolean known;
    Map<Vertex, Integer> cost;
    public Vertex(int id, int time, boolean known, Map<Vertex, Integer> cost) {
        this.id = id;
        this.time = time;
        this.known = known;
        this.cost = cost;
    }
}

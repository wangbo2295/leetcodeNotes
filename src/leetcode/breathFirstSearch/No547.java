package leetcode.breathFirstSearch;

import java.util.*;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */
public class No547 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visted = new boolean[n];
        int cnt = 0;
        //统计每个点能到达的点集
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    List<Integer> listi;
                    List<Integer> listj;
                    if (map.containsKey(i)) {
                        listi = map.get(i);
                    }else {
                        listi = new ArrayList<>();
                    }
                    if (map.containsKey(j)) {
                        listj = map.get(j);
                    }else {
                        listj = new ArrayList<>();
                    }
                    listi.add(j);
                    listj.add(i);
                    map.put(i, listi);
                    map.put(j, listj);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visted[i]) {
                bfs(visted, i, map);
                cnt++;  //每进行一次bfs，就统计完一个省份
            }
        }
        return cnt;
    }

    /**
     * 对于一个没访问过的城市，将与其和相连的所有城市标记为已访问
     * @param visited
     * @param i
     * @param map
     */
    public void bfs(boolean[] visited, int i, Map<Integer, List<Integer>> map) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i] = true;
        while (!queue.isEmpty()) {
            Integer city = queue.poll();
            List<Integer> cities = map.get(city);
            if (cities == null) return;
            for (Integer c: cities) {
                if (visited[c]) continue;
                queue.offer(c);
                visited[c] = true;
            }
        }
    }
}

/**
 * 并查集
 */
class Union{
    int[] cities;
    int provinces;

    public Union(int n) {
        cities = new int[n];
        for (int i = 0; i < n; i++) {
            cities[i] = -1;
        }
        provinces = n;
    }

    public void union(int from, int into) {
        int f = find(from);
        int i = find(into);
        if (f == i) return;
        cities[f] = i;
        provinces--;
    }

    public int find(int city) {
        while (cities[city] != -1) {
            city = cities[city];
        }
        return city;
    }

    public int getProvinces() {
        return this.provinces;
    }
}

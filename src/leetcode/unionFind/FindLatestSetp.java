package leetcode.unionFind;

import java.util.*;

public class FindLatestSetp {
    int[] fa;
    public int findLatestStep(int[] arr, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Arrays.fill(fa, -1);
        int latest = -1;
        for (int j = 0; j < arr.length; j++) {
            int i = arr[j];
            int pre = get(i - 1), next = get(i + 1);
            int pr = 0, ne = 0;
            if (set.contains(pre)) {
                pr = -fa[pre];
                map.put(pr, map.get(pr) - 1);
                merge(i, pre);
            }
            if (set.contains(next)) {
                ne = -fa[next];
                map.put(ne, map.get(ne) - 1);
                merge(i, next);
            }
            map.put(pr + 1 + ne, map.getOrDefault(pr + 1 + ne, 0) + 1);
            set.add(i);
            if (map.containsKey(m) && map.get(m) > 0)   latest = j + 1;
        }
        return latest;
    }

    public int get(int i) {
        if (fa[i] <= 0) return i;
        return fa[i] = get(fa[i]);
    }

    public void merge(int x, int y) {
        int fx = get(x), fy = get(y);
        int t = fa[fx];
        fa[fx] = fy;
        fa[fy] += t;
    }
}

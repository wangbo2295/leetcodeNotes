package leetcode.DC;

import java.util.*;

public class No91 {

    int N = 100010, ans = Integer.MIN_VALUE;
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] degree = new int[N];
    int[] steps = new int[N];
    boolean[] vis = new boolean[N];
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        Arrays.fill(steps, Integer.MAX_VALUE);
        vis[bob] = true;
        calcSteps(bob, 0);
        Arrays.fill(vis, false);
//        for (int i = 0; i < 10; i++) System.out.println(steps[i]);
        dfs(0, 0, 0, amount);
        return ans;
    }

    void dfs(int cur, int score, int st, int[] amount) {
        if (st < steps[cur]) score += amount[cur];
        else if (st == steps[cur]) score += amount[cur] / 2;
        if (degree[cur] == 2 && cur != 0) {
            System.out.println(cur);
            ans = Math.max(ans, score);
        }
        List<Integer> list = map.get(cur);
        for (int v : list) {
            if (!vis[v]) {
                vis[v] = true;
                dfs(v, score, st + 1, amount);
                vis[v] = false;
            }
        }
    }

    boolean calcSteps(int bob, int st) {
        steps[bob] = st;
        if (bob == 0) {
            return true;
        }
        List<Integer> list = map.get(bob);
        for (int next : list) {
            if (!vis[next]) {
                vis[next] = true;
                if (calcSteps(next, st + 1)) {
                    vis[next] = false;
                    return true;
                }
                vis[next] = false;
            }
        }
        steps[bob] = Integer.MAX_VALUE;
        return false;
    }

    void addEdge(int x, int y) {
        List<Integer> list = map.getOrDefault(x, new ArrayList<>());
        list.add(y);
        map.put(x, list);
    }
}

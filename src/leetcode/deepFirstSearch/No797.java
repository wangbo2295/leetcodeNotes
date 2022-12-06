package leetcode.deepFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class No797 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    boolean[] used;
    int n;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length;
        used = new boolean[n];
        list.add(0);
        dfs(graph, 0, list);
        return res;
    }

    public void dfs(int[][] graph, int num, List<Integer> list) {
        if (num == n - 1) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < graph[num].length; i++) {
            if (used[graph[num][i]])    continue;
            list.add(graph[num][i]);
            dfs(graph, graph[num][i], list);
            list.remove(list.size() - 1);
        }
    }
}

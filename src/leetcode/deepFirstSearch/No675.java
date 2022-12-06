package leetcode.deepFirstSearch;

import java.util.*;

/**
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个m x n 的矩阵表示， 在这个矩阵中：
 * 0 表示障碍，无法触碰
 * 1表示地面，可以行走
 * 比 1 大的数表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 */
public class No675 {
    /**
     * 思路：由于各个树的高度不相等，可以将其排序
     * 按照由低到高的顺序计算最短路径并求和
     * @param forest
     * @return
     */
    int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
    int m, n;
    public int cutOffTree(List<List<Integer>> forest) {
        m = forest.size(); n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[] {i, j});
                }
            }
        }
        Collections.sort(trees, Comparator.comparingInt(o->forest.get(o[0]).get(o[1])));
        int cx = 0;
        int cy = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); ++i) {
            int steps = bfs(forest, forest.get(cx).get(cy), trees.get(i)[0], trees.get(i)[1]);
            if (steps == -1) {
                return -1;
            }
            ans += steps;
            cx = trees.get(i)[0];
            cy = trees.get(i)[1];
        }
        return ans;
    }

    public int bfs(List<List<Integer>> forest, int target, int x, int y) {
        if (forest.get(x).get(y) == target) {
            return 0;
        }
        int[][] used = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        used[x][y] = 1;
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = cur[0] + dir[0][i];
                    int ny = cur[1] + dir[1][i];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && used[nx][ny] == 0 && forest.get(nx).get(ny) > 0) {
                        if (forest.get(cur[0]).get(cur[1]) == target) {
                            return steps;
                        }
                        used[nx][ny] = 1;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        return -1;
    }

}

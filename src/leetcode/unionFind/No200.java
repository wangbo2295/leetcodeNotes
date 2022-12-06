package leetcode.unionFind;

import java.util.HashSet;
import java.util.Set;

public class No200 {
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Islands islands = new Islands(m * n);
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '1')  continue;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dir[k][0], ny = j + dir[k][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == '1') {
                        islands.union(i * n + j, nx * n + ny);
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1')  set.add(islands.find(i * n + j));
            }
        }
        return set.size();
    }

    static class Islands {
        int[] islands;
        public Islands(int n) {
            islands = new int[n];
            for (int i = 0; i < n; i++) islands[i] = i;
        }

        public int find(int idx) {
            if (islands[idx] == idx)    return idx;
            return islands[idx] = find(islands[idx]);
        }

        public void union(int x, int y) {
            islands[find(x)] = find(y);
        }
    }
}

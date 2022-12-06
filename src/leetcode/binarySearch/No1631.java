package leetcode.binarySearch;

import java.util.LinkedList;
import java.util.Queue;

public class No1631 {
    int[][] heights, dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;

    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;
        int l = -1, r = 1000001;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (exists(m)) r = m;
            else l = m;
        }
        return r;
    }

    public boolean exists(int effort) {
        boolean[][] vis = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        vis[0][0] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            int val = heights[x][y];
            if (x == m - 1 && y == n - 1)    return true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0], ny = y + dir[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !vis[nx][ny] && Math.abs(val - heights[nx][ny]) <= effort) {
                    queue.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
        }
        return false;
    }

}

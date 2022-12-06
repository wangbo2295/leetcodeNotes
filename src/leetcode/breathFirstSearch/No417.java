package leetcode.breathFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。“太平洋”处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵heights，heights[r][c]表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回网格坐标 result的 2D 列表 ，其中result[i] = [ri, ci]表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 */
public class No417 {
    /**
     * 思路：
     * 从海洋边界开始搜寻，标记能到达的地方，被两个海洋标记的地方就是结果
     * 思路正确，就是有点慢了
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] pacific = new int[m][n];
        int[][] atlantic = new int[m][n];
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pacific[i][0] = 1;
            queue.offer(new int[] {i, 0});
        }
        for (int i = 1; i < n; i++) {
            pacific[0][i] = 1;
            queue.offer(new int[] {0, i});
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + dir[0][j];
                    int ny = poll[1] + dir[1][j];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && pacific[nx][ny] == 0
                            && heights[nx][ny] >= heights[poll[0]][poll[1]]) {
                        pacific[nx][ny] = 1;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            atlantic[i][n - 1] = 1;
            queue.offer(new int[] {i, n - 1});
            if (pacific[i][n - 1] == 1) {
                List<Integer> cor = new ArrayList<>();
                cor.add(i);cor.add(n - 1);
                res.add(cor);
            }
        }
        for (int i = 0; i < n - 1; i++) {
            atlantic[m - 1][i] = 1;
            queue.offer(new int[] {m - 1, i});
            if (pacific[m - 1][i] == 1) {
                List<Integer> cor = new ArrayList<>();
                cor.add(m - 1);cor.add(i);
                res.add(cor);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = poll[0] + dir[0][j];
                    int ny = poll[1] + dir[1][j];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && atlantic[nx][ny] == 0
                            && heights[nx][ny] >= heights[poll[0]][poll[1]]) {
                        atlantic[nx][ny] = 1;
                        queue.offer(new int[] {nx, ny});
                        if (pacific[nx][ny] == 1) {
                            List<Integer> cor = new ArrayList<>();
                            cor.add(nx);cor.add(ny);
                            res.add(cor);
                        }
                    }
                }
            }
        }
        return res;
    }
}

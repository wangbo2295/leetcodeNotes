package leetcode.unionFind;

import java.util.Scanner;

public class No695 {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fa[i * n + j]--;
                    max = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dir[k][0], nj = j + dir[k][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                        merge(i * n + j, ni * n + nj);
                    }
                }
            }
        }
        return max;
    }

    int N = 2510;
    int[] fa = new int[N];
    int max;
    int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int get(int x) {
        return fa[x] < 0 ? x : (fa[x] = get(fa[x]));
    }

    public void merge(int x, int y) {
        x = get(x);
        y = get(y);
        if (x == y) return;
        fa[y] += fa[x];
        fa[x] = y;
        max = Math.max(max, -fa[y]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.substring(2, s.length() - 2);
        String[] split = s.split("],\\[");
        int m = split.length;
        String[] row = split[0].split(",");
        int n = row.length;
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] r = split[i].split(",");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(r[j]);
            }
        }
        No695 no695 = new No695();
        no695.maxAreaOfIsland(grid);
    }
}

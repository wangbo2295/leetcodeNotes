package leetcode.binarySearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SwimInPool {
    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int[][] grid;
    int n;
    public int swimInWater(int[][] grid) {
        this.grid = grid;
        n = grid[0].length;
        int l = -1, r = n * n;
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (check(m)) {
                r = m;
            } else {
                l = m;
            }
        }
//        System.out.println(check(266));
        return r;
    }

    public boolean check(int line) {
        if (grid[0][0] > line || grid[n - 1][n - 1] > line)  return false;
        if (n == 1)    return true;
        boolean[][] vis = new boolean[n][n];
        boolean[][] vis1 = new boolean[n][n];
        boolean[][] both = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> queue1 = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        queue1.offer(new int[]{n - 1, n - 1});
        vis[0][0] = true;
        vis1[n - 1][n - 1] = true;
        both[0][0] = true;
        both[n - 1][n - 1] = true;
        while (!queue.isEmpty() || !queue1.isEmpty()) {
            if (!queue.isEmpty() && nextStep(queue, vis, line, both))   return true;
            if (!queue1.isEmpty() && nextStep(queue1, vis1, line, both))    return true;
        }
        return false;
    }

    public boolean nextStep(Queue<int[]> queue, boolean[][] vis, int line, boolean[][] both) {
        int[] ints = queue.poll();
        int x = ints[0], y = ints[1];
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0], ny = y + dir[i][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !vis[nx][ny] && grid[nx][ny] <= line) {
                if (both[nx][ny])   return true;
                queue.offer(new int[]{nx, ny});
                vis[nx][ny] = true;
                both[nx][ny] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        s = s.substring(2, s.length() - 2);
        String[] params = s.split("],\\[");
        int n = params.length;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] row = params[i].split(",");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        SwimInPool swimInPool = new SwimInPool();
        swimInPool.swimInWater(matrix);
    }
}

package leetcode.deepFirstSearch;


import java.util.*;

public class No864 {

    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][][] vis;

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int row = 0, col = 0, kys = 0;
        vis = new boolean[1 << 6][m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    row = i;
                    col = j;
                }
                if (isLower(c)) ++kys;
            }
        }
        queue.offer(new int[]{0, row, col});
        vis[0][row][col] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[1], y = poll[2], keys = poll[0];
                System.out.println(x + ", " + y + " : " + keys);
                if (check(keys, kys)) return ans;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dir[k][0], ny = y + dir[k][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        char c = grid[nx].charAt(ny);
                        if (isUpper(c) && hasKey(keys, c - 'A') && !vis[keys][nx][ny]) {
                            vis[keys][nx][ny] = true;
                            queue.offer(new int[]{keys | (1 << (c - 'A')), nx, ny});
                        } else if (isLower(c) && !vis[keys | (1 << (c - 'a'))][nx][ny]) {
                            vis[keys | (1 << (c - 'a'))][nx][ny] = true;
                            queue.offer(new int[]{keys | (1 << (c - 'a')), nx, ny});
                        } else if ((c == '.' || c == '@') && !vis[keys][nx][ny]) {
                            vis[keys][nx][ny] = true;
                            queue.offer(new int[]{keys, nx, ny});
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    boolean check(int keys, int tot) {return (keys ^ ((1 << tot) - 1)) == 0;}
    boolean hasKey(int keys, int i) {return (keys >> i & 1) == 1;}
    boolean isUpper(char c) {return c >= 'A' && c <= 'Z';}
    boolean isLower(char c) {return c >= 'a' && c <= 'z';}

    public static void main(String[] args) {
        No864 no864 = new No864();
        int res = no864.shortestPathAllKeys(new String[]{"@fedcbBCDEFaA"});
        System.out.println(res);
    }
}

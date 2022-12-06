package leetcode.WC;

import java.util.*;

public class WC295 {

    public int rearrangeCharacters(String s, String target) {
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map[chars[i] - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        int[] map2 = new int[26];
        for (char c : target.toCharArray()) {
            map2[c - 'a']++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map2[i - 'a'] != 0) {
                min = Math.min(min, map[i - 'a'] / map2[i - 'a']);
            }
        }
        return min;
    }

    public String discountPrices(String sentence, int discount) {
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith("$")) {
                try {
                    double price = Long.parseLong(words[i].substring(1));
                    price *= (double)(100 - discount) / 100;
                    words[i] = "$" + String.format("%.2f", price);
                }catch (Exception e) {
                    continue;
                }
            }
        }
        return String.join(" ", words);
    }

    public int totalSteps(int[] nums) {
        int max = 0;
        int pre = Integer.MIN_VALUE;
        int cnt = 0;
        int last = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] < last && nums[i] < nums[i - 1]) {
                if (nums[i] >= pre) {
                    cnt++;
                }
                pre = nums[i];
                max = Math.max(max, cnt);
            }else {
                pre = Integer.MIN_VALUE;
                cnt = 0;
                last = nums[i];
            }
        }
        return max;
    }

    /**
     * 到达角落需要移除障碍物的最小数目
     * Dijkstra算法
     * 1、将已计算路径的点放入队列，刚开始队列只有起点，队列要用优先队列，按照已计算出的路径排序，遵循贪心思想
     * 2、同一个点可能被计算两次，此时取较小值
     * 3、放入队列的点要标记为visited，避免重复计算（在无负值圈的图中，路径开销只会增大，所以不考虑一个点遍历两次的情况）
     * @param grid
     * @return
     */
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        int[][] steps = new int[m][n];
        for (int i = 0; i < steps.length; i++) {
            Arrays.fill(steps[i], Integer.MAX_VALUE);
        }
        steps[0][0] = 0;
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        Queue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o->steps[o[0]][o[1]])));
        queue.offer(new int[] {0, 0});
        while (!queue.isEmpty()) {
            int[] cor = queue.poll();
            int x = cor[0], y = cor[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[0][i];
                int ny = y + dir[1][i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (grid[nx][ny] == 1) {
                        steps[nx][ny] = Math.min(steps[nx][ny], steps[x][y] + 1);
                    }else {
                        steps[nx][ny] = Math.min(steps[nx][ny], steps[x][y]);
                    }
                    if (!vis[nx][ny]) {
                        vis[nx][ny] = true;
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        return steps[m - 1][n - 1];
    }

    public static void main(String[] args) {

    }
}

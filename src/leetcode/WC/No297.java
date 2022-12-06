package leetcode.WC;

import java.util.*;

public class No297 {
    public double calculateTax(int[][] brackets, int income) {
        double ans = 0;
        for (int i = 0; i < brackets.length && income > 0; i++) {
            double pre = i > 0 ? brackets[i - 1][0] : 0.0;
            ans += Math.min(income, brackets[i][0] - pre) * brackets[i][1] / 100.0;
            income -= brackets[i][0] - pre;
        }
        return ans;
    }

    /**
     * dp
     */
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] minCost = new int[m][n];
        for (int i = 0; i < n; i++) {
            minCost[0][i] = grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    minCost[i + 1][k] = Math.min(minCost[i + 1][k], grid[i + 1][k] + minCost[i][j] + moveCost[grid[i][j]][k]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, minCost[m - 1][i]);
        }
        return ans;
    }

    /**
     * 很好奇为什么不超时
     */
    int ans, k, n;
    int[] cookies, kids;
    public int distributeCookies(int[] cookies, int k) {
        this.k = k;
        this.n = cookies.length;
        this.cookies = cookies;
        this.kids = new int[k];
        this.ans = Integer.MAX_VALUE;
        dfs(0);
        return ans;
    }
    public void dfs(int d) {
        if (d == n) {
            int max = Integer.MIN_VALUE;
            for (int kid : kids) {
                max = Math.max(max, kid);
            }
            ans = Math.min(ans, max);
            return;
        }
        for (int i = 0; i < k; i++) {
            kids[i] += cookies[d];
            dfs(d + 1);
            kids[i] -= cookies[d];
        }
    }

    /**
     * 设ideaA的首字母为x，ideaB的首字母为y
     * 如果x -> y后的单词不存在，且y -> x之后的单词不存在，那么ideaA， ideaB构成一对
     * 由于只有小写字母，所以可以对每个单词枚举26个首字母，记录由x -> y不存在的有多个单词，并记录ideas[i]可以将首字母替换成j，即flag[i][j] = true
     * 再次遍历每个单词，枚举26个首字母，如果flag[i][j]，说明idea[i]替换首字母为j是可行的, 此时只要知道有多少个单词替换首字母为i是可行的，就能组成一对
     * 所以满足的idea个数为cnt[j][i]，即ans += cnt[j][i]
     *
     * 神的思路，一辈子想不到
     * 很像博弈的题，两个玩家玩游戏且都以最佳状态参加，实际模拟的过程中只有一个玩家---当前玩家
     * 应用到次题也是一样的道理，看似要满足两个条件，实际上只是一个条件对应的两个不同的对象，而当统计出每个对象所满足的条件后，就可以直接查找了
     */
    public long distinctNames(String[] ideas) {
        int n = ideas.length;
        Set<String> set = new HashSet<>();
        for (String idea : ideas) {
            set.add(idea);
        }
        long[][] cnt = new long[26][26];
        boolean[][] flag = new boolean[n][26];
        for (int j = 0; j < n; j++) {
            int y = ideas[j].charAt(0) - 'a';
            String suffix = ideas[j].substring(1);
            for (char i = 0; i < 26; i++) {
                char c = (char)('a' + i);
                if (!set.contains(c + suffix)) {
                    cnt[y][i]++;
                    flag[j][i] = true;
                }
            }
        }
        long ans = 0;
        for (int j = 0; j < n; j++) {
            int x = ideas[j].charAt(0) - 'a';
            for (char i = 0; i < 26; i++) {
                if (flag[j][i]) {
                    ans += cnt[i][x];
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        No297 no297 = new No297();
        int[][] grid = {{5,3},{4,0},{2,1}};
        int[][] moveCost = {{9,8},{1,5},{10,12},{18,6},{2,4},{14,3}};
        no297.minPathCost(grid, moveCost);
    }
}

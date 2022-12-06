package leetcode.WC;

import java.util.Stack;

public class Week292 {
    public String largestGoodInteger(String num) {
        char pattern = '0';
        String res = "";
        for (int i = 0; i < num.length(); i++) {
            int cnt = 0;
            char c = num.charAt(i);
            while (c == num.charAt(i)) {
                cnt++;
                i++;
            }
            if (cnt >= 3 && c >= pattern) {
                res = "" + c + c + c;
                pattern = c;
                System.out.println(res + "-" + pattern);
            }
        }
        return res;
    }


    /**
     * 6057. 统计值等于子树平均值的节点数
     */
    /**
     * Definition for a binary tree node
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int cnt;
    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return cnt;
    }
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[] {0,0};
        }
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);
        int sum = node.val + left[0] + right[0];
        int num = 1 + left[1] + right[1];
        if (sum / num == node.val) cnt++;
        return new int[] {sum, num};
    }

    /**
     * 模拟拼音打字
     * 对于连续的按键，就看其有几种可能的字母
     * 完全背包
     * @param pressedKeys
     * @return
     */
    public static final int MOD = 1000000007;
    public static final int[] ns = {0,0,3,3,3,3,3,4,3,4};
    public int countTexts(String pressedKeys) {
        int i = 0;
        long res = 1;
        while (i < pressedKeys.length()) {
            int count = 0;
            char c = pressedKeys.charAt(i);
            while (i < pressedKeys.length() && c == pressedKeys.charAt(i)) {
                count++; i++;
            }
            int num = count(ns[c - '0'], count);
            res = (res * num) % MOD;
        }
        return (int)res;
    }

    private int count(int n, int count) {
        int[] dp = new int[count + 1];
        dp[0] = 1;
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= n; j++) {
                if (i >= j) {
                    dp[i] = (dp[i] + dp[i - j]) % MOD;
                }
            }
        }
        return dp[count];
    }
    /**
     * 注意到物品只有3、4两种，且背包容量为包含关系时有很多重复计算
     * 所以直接计算最大容量的背包，存在dp数组中当作函数使用
     */
    public int countTexts2(String pressedKeys) {
        int i = 0;
        long res = 1;
        int n = pressedKeys.length();
        int[] f3 = new int[n + 1];
        int[] f4 = new int[n + 1];
        f3[0] = f4[0] = 1;
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= 3; k++) {
                if (j >= k) f3[j] = (f3[j] + f3[j - k]) % MOD;
            }
            for (int k = 1; k <= 4; k++) {
                if (j >= k) f4[j] = (f4[j] + f4[j - k]) % MOD;
            }
        }
        while (i < pressedKeys.length()) {
            int count = 0;
            char c = pressedKeys.charAt(i);
            while (i < pressedKeys.length() && c == pressedKeys.charAt(i)) {
                count++; i++;
            }
            int num;
            if (c-'0' == 7 || c - '0' == 9) {
                num = f4[count];
            }else {
                num = f3[count];
            }
            res = (res * num) % MOD;
        }
        return (int)res;
    }

    /**
     * 6059. 检查是否有合法括号字符串路径
     */

    /*
        dfs超时
     */
    Stack<Character> stack = new Stack<>();
    int[][] dir = {{0, 1}, {1, 0}};
    public boolean hasValidPath(char[][] grid) {
        return dfs(grid, 0, 0);
    }

    public boolean dfs(char[][] grid, int x, int y) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            if (stack.size() == 1 && grid[x][y] == ')' && stack.peek() == '(') {
                return true;
            }else {
                return false;
            }
        }
        boolean poped  =false;
        boolean pushed = false;
        if (!stack.empty() && grid[x][y] == ')' && stack.peek() == '(') {
            stack.pop();
            poped = true;
        }else {
            stack.push(grid[x][y]);
            pushed = true;
        }
        boolean right = false;
        if (x + dir[0][0] >=0 && x + dir[0][0] < grid.length && y + dir[1][0] >=0 && y + dir[1][0]  < grid[0].length) {
            right = dfs(grid, x + dir[0][0], y + dir[1][0]);
        }
        boolean down = false;
        if (x + dir[0][1] >=0 && x + dir[0][1] < grid.length && y + dir[1][1] >=0 && y + dir[1][1]  < grid[0].length) {
            down = dfs(grid, x + dir[0][1], y + dir[1][1]);
        }
        if (!(right || down)) {
            if (poped) {
                stack.push('(');
            }else if (pushed) {
                stack.pop();
            }
        }
        return right || down;
    }

    /**
     * dp：
     * dp[i][j][k] —— (i, j) 点是否存在val为k的括号组合（val为左括号和右括号数量之差）
     * dp[i][j][k] = dp[i - 1][j][k - c] || dp[i][j - 1][k - c]
     * 其中c —— 当grid[i][j] == '(' 时为1， 否则为-1
     * 遍历顺序：从左到右，从上到下
     */
    public boolean hasValidPath2(char[][] grid) {
        int m = grid.length, n = grid[0].length, l = m + n;
        if (((m + n) & 1) == 0)   return false;
        boolean[][][] dp = new boolean[m][n][l];
        if (grid[0][0] == '(')  dp[0][0][1] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                int c = grid[i][j] == '(' ? 1 : -1;
                for (int k = 0; k < l; k++) {
                    int kk = k - c;
                    if (kk < 0 || kk >= l)    continue;
                    if (i > 0) {
                        dp[i][j][k] |= dp[i - 1][j][kk];
                    }
                    if (j > 0) {
                        dp[i][j][k] |= dp[i][j - 1][kk];
                    }
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}

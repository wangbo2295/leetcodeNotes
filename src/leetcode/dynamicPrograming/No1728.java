package leetcode.dynamicPrograming;

/**
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 *
 * 它们所处的环境设定是一个rows x cols的方格 grid，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 *
 * 玩家由字符'C'（代表猫）和'M'（代表老鼠）表示。
 * 地板由字符'.'表示，玩家可以通过这个格子。
 * 墙用字符'#'表示，玩家不能通过这个格子。
 * 食物用字符'F'表示，玩家可以通过这个格子。
 * 字符'C'，'M'和'F'在grid中都只会出现一次。
 * 猫和老鼠按照如下规则移动：
 *
 * 老鼠 先移动，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出grid。
 * catJump 和mouseJump是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 * 游戏有 4 种方式会结束：
 *
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
 * 给你rows x cols的矩阵grid和两个整数catJump和mouseJump，双方都采取最优策略，如果老鼠获胜，那么请你返回true，否则返回 false。
 */
public class No1728 {

    /**
     * 思路：回溯 + 记忆化搜索
     * 穷举所有可能的走法，如果老鼠均不能获胜则返回false，否则返回true
     * 每得到一个结果，记录下来（记录啥？如何记录？）
     * 记录猫和老鼠的坐标，注意到1 <= row, col <= 8，刚好可以用一个int记录
     * @param grid
     * @param catJump
     * @param mouseJump
     * @return
     */
    String[] grid;
    int catJump;
    int mouseJump;
    Boolean[][] dp = new Boolean[64][64];
    int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
    int maxMove = 999;
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.grid = grid;
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int mouse = 0;
        int cat = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                if (grid[i].charAt(j) == 'M') {
                    mouse |= i << 3;
                    mouse |= j;
                }
                if (grid[i].charAt(j) == 'C') {
                    cat |= i << 3;
                    cat |= j;
                }
            }
        }
        return dfs(mouse, cat, 1, 1);
    }

    private boolean dfs(int mouse, int cat, int turn, int move) {
        if (dp[mouse][cat] != null) {
            return dp[mouse][cat];
        }
        if (move >= maxMove)    return false;
        if (mouse == cat && turn == -1) {
            dp[mouse][cat] = true;
            return true;
        }
        if (turn == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= mouseJump; j++) {
                    int nx = (mouse >> 3) + dir[0][i] * j;
                    int ny = (mouse & 7) + dir[1][i] * j;
                    if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length() || grid[nx].charAt(ny) == '#') break;
                    System.out.println("mouse at: " + nx + ", " + ny);
                    int nstate = (nx << 3) | ny;
                    if (grid[nx].charAt(ny) == 'F' || !dfs(nstate, cat, -turn, move)) {
                        dp[mouse][cat] = true;
                        return true;
                    }
                }
            }
        }else {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= catJump; j++) {
                    int nx = (cat >> 3) + dir[0][i] * j;
                    int ny = (cat & 7) + dir[1][i] * j;
                    if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length() || grid[nx].charAt(ny) == '#') break;
                    System.out.println("cat at: " + nx + ", " + ny);
                    int nstate = (nx << 3) | ny;
                    if (grid[nx].charAt(ny) == 'F' || nstate == mouse || !dfs(mouse, nstate, -turn, move + 1)) {
                        dp[mouse][cat] = true;
                        return true;
                    }
                }
            }
        }
        dp[mouse][cat] = false;
        return false;
    }
}

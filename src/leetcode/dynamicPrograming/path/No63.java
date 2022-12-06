package leetcode.dynamicPrograming.path;

/**
 * 不同路径II，带障碍物
 */
public class No63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)    return 0;
        obstacleGrid[0][0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (i == j && i ==0)    continue;
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                int left = j > 0 ? obstacleGrid[i][j - 1] : 0;
                int up = i > 0 ? obstacleGrid[i - 1][j] : 0;
                obstacleGrid[i][j] = left + up;
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }
}

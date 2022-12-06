package leetcode.unionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格0 变成1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的1 形成。
 */
public class No827 {
    /**
     * 思路：并查集
     * 先把所有相邻点合并，然后遍历每个 0 ，计算合并相邻岛屿之后的面积，取最大值即可
     * 细节 ：
     *     将二维数组压缩成一维，数组存储父节点和面积，0 和负数为岛屿面积，正数为父节点下标
     * 因此从 1 开始编号，对于原二维坐标 (i, j)， 其对应的一维数组下标为 i * n + j + 1
     */

    int[] fa = new int[250010];
    int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    int ans;

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fa[i * n + j + 1] = -1;
                    ans = Math.max(ans, 1);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dir[k][0], nj = j + dir[k][1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                            int merge = merge(i * n + j + 1, ni * n + nj + 1);
                            ans = Math.max(ans, merge);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int t = 1;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dir[k][0], nj = j + dir[k][1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                            set.add(get(ni * n + nj + 1));
                        }
                    }
                    for (Integer integer : set) t += -fa[integer];
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    public int get(int x) {
        return fa[x] <= 0 ? x : (fa[x] = get(fa[x]));
    }

    public int merge(int x, int y) {
        int fx = get(x);
        int fy = get(y);
        if (fx == fy)   return 0;
        fa[fy] += fa[fx];
        fa[fx] = fy;
        return -fa[fy];
    }
}

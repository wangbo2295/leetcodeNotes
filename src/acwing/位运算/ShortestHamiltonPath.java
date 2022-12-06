package acwing.位运算;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一张 n 个点的带权无向图，点从 0∼n−1 标号，求起点 0 到终点 n−1 的最短 Hamilton 路径。
 * Hamilton 路径的定义是从 0 到 n−1 不重不漏地经过每个点恰好一次。
 */
public class ShortestHamiltonPath {
    /**
     * 思路：dp[i][j] 表示已到达 i ，处于 j 点的最短路径，其中 i 为二进制表示的每个点是否到达的状态
     * i 可由未到达 j 的前一个状态推导，即dp[i][j] = min(dp[i][j], dp[i ^ 1 << j][k])， 其中 k 为到达 j 之前所处的点
     * 枚举所有状态并计算最短路径，dp[(1 << n) - 1][n - 1] 即为所求
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = scanner.nextInt();
            }
        }
        int[][] dp = new int[1 << n][n];
        //初始化为不可达
        for (int i = 0; i < 1 << n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[1][0] = 0;   //初始化起点
        for (int i = 1; i < 1 << n; i++) {  //i = 1 就代表以 0 为起点
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0)  continue;   //跳过未到达的点
                for (int k = 0; k < n; k++) {
                    if ((i ^ (1 << j & k)) == 0)  continue; //跳过前一个状态未到达的点
                    dp[i][j] = Math.min(dp[i][j], dp[i ^ 1 << j][k] + edges[k][j]);
                }
            }
        }
        System.out.println(dp[(1 << n) - 1][n - 1]);
    }
}

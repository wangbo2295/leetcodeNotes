package leetcode.WC;

import java.util.*;

public class No284 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<int[]> intervals = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                int l, r;
                for (l = i; l > 0 && i - l < k; l--);
                for (r = i; r < nums.length - 1 && r - i < k; r++);
                intervals.add(new int[] {l, r});
            }
        }
        for (int i = 1; i < intervals.size(); i++) {
            int[] interval = intervals.get(i);
            if (interval[0] <= intervals.get(i - 1)[1]) {
                interval[0] = intervals.get(i - 1)[1] + 1;
                intervals.set(i, interval);
            }
        }
        for (int[] interval : intervals) {
            for (int i = interval[0]; i <= interval[1]; i++) {
                res.add(i);
            }
        }
        return res;
    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[][] diged = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < dig.length; i++) {
            diged[dig[i][0]][dig[i][1]] = true;
        }
        for (int i = 0; i < artifacts.length; i++) {
            boolean find = true;
            for (int r = artifacts[i][0]; r <= artifacts[i][2]; r++) {
                for (int c = artifacts[i][1]; c <= artifacts[i][3]; c++) {
                    if (!diged[r][c]) {
                        find = false;
                        break;
                    }
                }
            }
            if (find) cnt++;
        }
        return cnt;
    }

    /**
     * 1、如果k <= n,
     *      如果 k < n, 那么最大的为前k - 1和第k + 1的最大值
     *      如果 k == n， 那么为前k - 1中最大值
     * 2、如果k > n,  那么已经拿到数组中所有的数
     *      如果 k - n 为奇数， 那么只需要先将最大元素入栈，然后一直重复弹出-入栈的操作
     *      如果 k - n 为偶数， 只需要先将不是最大的元素入栈，然后执行奇数的做法
     */
    public int maximumTop(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return nums[0];
        if (n == 1) {
            if ((k & 1) == 0)   return nums[0];
            else return -1;
        }
        int[] preMax = new int[n + 1];
        preMax[0] = -1;
        for (int i = 1; i < preMax.length; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i - 1]);
        }
        if (k < n) {
            return Math.max(preMax[k - 1], nums[k]);
        } else if (k == n) {
            return preMax[k - 1];
        } else {
            return preMax[n];
        }
    }

    /**
     * 思路：从src1的最短路 + src2的最短路
     * 两条路径一定有重合点，或是dst，或是src1，或是src2
     * 枚举重合点c，计算cost(src1, c) + cost(src2, c) + cost(c, dst)的最小值
     * 可以采用dp计算所有点对最小路径，然后枚举c即可
     * 算出所有点对最短路径很方便，但是超内存、超时
     */
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
        for (int i = 0; i < n; i++) dp[i][i] = 0;
        for (int[] edge : edges) {
            dp[edge[0]][edge[1]] = Math.min(edge[2], dp[edge[0]][edge[1]]);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] != Long.MAX_VALUE && dp[k][j] != Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[src1][i] == Long.MAX_VALUE || dp[src2][i] == Long.MAX_VALUE || dp[i][dest] == Long.MAX_VALUE)  continue;
            min = Math.min(min, dp[src1][i] + dp[src2][i] + dp[i][dest]);
        }
        return min == Long.MAX_VALUE ? -1 : min;
    }

    /**
     * 用Dijkstra算法，算出src1、src2到所有点的最短路径，然后将边反向，算出dst到所有点的最短路径，再枚举c就可以了
     * 相比于dp计算了n次最短路径，该方法只计算了3次最短路径
     * 好难写，不想写了
     */
    public long minimumWeight2(int n, int[][] edges, int src1, int src2, int dest) {
        Map<Integer, Map<Integer, Long>> map = new HashMap<>();
        Map<Integer, Map<Integer, Long>> reverseMap = new HashMap<>();
        for (int[] edge : edges) {
            Map<Integer, Long> orDefault = map.getOrDefault(edge[0], new HashMap<>());
            orDefault.put(edge[1], Math.min(orDefault.getOrDefault(edge[1], Long.MAX_VALUE), edge[2]));
            map.put(edge[0], orDefault);
            Map<Integer, Long> orDefault2 = reverseMap.getOrDefault(edge[1], new HashMap<>());
            orDefault2.put(edge[0], Math.min(orDefault2.getOrDefault(edge[0], Long.MAX_VALUE), edge[2]));
            map.put(edge[1], orDefault2);
        }
        return -1;
    }

    class Pair {
        int num;
        int cost;
        public Pair(int num, int cost){this.num = num;this.cost = cost;}
        public boolean equals(Object o) {
            return o instanceof Pair && this.num == ((Pair) o).num;
        }
    }

    public static void main(String[] args) {
        No284 no284 = new No284();
        int[] nums = {2,2,2,2,2};
//        no284.findKDistantIndices(nums, 2,2);
        int n = 5, src1 = 0, src2 = 4, dest = 1;
        int[][] edges = {{4,2,20},{4,3,46},{0,1,15},{0,1,43},{0,1,32},{3,1,13}};
        no284.minimumWeight(n, edges, src1, src2, dest);
    }
}

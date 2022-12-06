package leetcode.dynamicPrograming;

/**
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 *
 * perm[i] 能够被 i 整除
 * i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 *
 * 明明是dfs的题你给我分在动态规划里面。递归 + 备忘录也可以当作动态规划呢，有些题无法转化成非递归的写法。
 * 不过可以用位运算记录数的使用状态
 */
public class No526 {
    int count;
    public int countArrangement(int n) {
        boolean[] used = new boolean[n + 1];
//        dfs(used, 1);
        dfs2(0, 1, n);
        return count;
    }
    //使用数组存储状态的写法
    public void dfs(boolean[] used, int index) {
        if (index >= used.length) {
            count++;
        }
        for (int i = 1; i < used.length; i++) {
            if (!used[i] && (i % index == 0 || index % i == 0)) {
                used[i] = true;
                dfs(used, index + 1);
                used[i] = false;
            }
        }
    }

    //使用bitmap（位图）存储状态的写法
    public void dfs2(int used, int index, int n) {
        if (index > n) {
            count++;
        }
        for (int i = 1; i <= n; i++) {
            if (((1 << (i - 1)) & used) == 0 && (i % index == 0 || index % i == 0)) {
                dfs2(used | (1 << (i - 1)), index + 1, n);
            }
        }
    }

    public static void main(String[] args) {
        No526 no526 = new No526();
        int i = no526.countArrangement(15);
        System.out.println(i);
    }
}

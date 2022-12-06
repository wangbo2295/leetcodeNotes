package acwing.树状数组;

import java.util.Arrays;

/**
 * n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
 * 每 3 个士兵可以组成一个作战单位，分组规则如下：
 * 从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
 * 作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
 * 请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
 */
public class No1395 {
    /**
     * 解法一：树状数组，时间复杂度 O(nlogn), 每次修改树状数组和查询的时间复杂度为 logn，对每个元素都进行两次查询和一次修改
     * 利用树状数组求出每个数左右大于和小于自身的个数
     * 然后枚举三元组的中间数，将左边小于（大于）右边大于（小于）的个数的积累加到答案中
     */
    int N = 100010;
    int[] tree = new int[N];
    int[] ll, lh, rl, rh;
    public int numTeams(int[] rating) {
        int n = rating.length;
        ll = new int[n];    lh = new int[n];    rl = new int[n];    rh = new int[n];
        for (int i = 0; i < n; i++) {
            ll[i] = sum(rating[i] - 1) - sum(0);
            lh[i] = sum(N - 1) - sum(rating[i]);
            add(rating[i], 1);
        }
        Arrays.fill(tree, 0);
        for (int i = n - 1; i >= 0; i--) {
            rl[i] = sum(rating[i] - 1) - sum(0);
            rh[i] = sum(N - 1) - sum(rating[i]);
            add(rating[i], 1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += ll[i] * rh[i];
            ans += lh[i] * rl[i];
        }
        return ans;
    }

    public int sum(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += tree[i];
        }
        return sum;
    }

    public void add(int i, int x) {
        for (; i < N; i += i & -i) {
            tree[i] += x;
        }
    }

    /**
     * 解法二：归并排序求逆序对, 时间复杂度 O(nlogn)
     * 一次归并排序的过程即可分别求出每个元素左边和右边大于和小于其自身的元素个数
     * 需要注意的是本题说明了每个元素是唯一的，不需要处理重复元素的情况，否则用树状数组更方便
     */
    int[] LL, LH, RL, RH;
    int[] rate, idxs;
    public int numTeams2(int[] rating) {
        rate = rating;
        int n = rating.length;
        idxs = new int[n];
        for (int i = 0; i < n; i++) idxs[i] = i;
        LL = new int[n];LH = new int[n];RL = new int[n];RH = new int[n];
        merge(idxs, 0, n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += LL[i] * RH[i];
            ans += LH[i] * RL[i];
        }
        return ans;
    }

    private void merge(int[] idxs, int l, int r) {
        if (l + 1 == r) return;
        int m = l + r >> 1;
        merge(idxs, l, m);
        merge(idxs, m, r);
        int n = r - l;
        int[] t = new int[n];
        for (int i = 0, p1 = l, p2 = m; i < n; i++) {
            if (p2 == r || p1 < m && rate[idxs[p1]] < rate[idxs[p2]]) {
                RL[idxs[p1]] += p2 - m;
                RH[idxs[p1]] += r - p2;
                t[i] = idxs[p1++];
            } else {
                LL[idxs[p2]] += p1 - l;
                LH[idxs[p2]] += m - p1;
                t[i] = idxs[p2++];
            }
        }
        System.arraycopy(t, 0, idxs, l, n);
    }
}

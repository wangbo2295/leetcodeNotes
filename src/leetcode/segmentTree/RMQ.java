package leetcode.segmentTree;

import java.io.*;

public class RMQ {

    public static int n;
    public static Segment[] tree;
    public static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nq = br.readLine().split(" ");
        n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        nums = new int[n + 1];
        tree = new Segment[4 * n];
        String[] s = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) nums[i] = Integer.parseInt(s[i - 1]);
        for (int i = 1; i < 4 * n; i++) tree[i] = new Segment();
        build(1, 1, n);
        for (int i = 0; i < q; i++) {
            String[] param = br.readLine().split(" ");
            int l = Integer.parseInt(param[0]), r = Integer.parseInt(param[1]);
            bw.write(query(1, l, r) + "\n");
        }
        bw.flush();
    }

    public static void build(int p, int l, int r) {
        tree[p].l = l; tree[p].r = r;
        if (l == r) {
            tree[p].max = nums[l];
            return;
        }
        int m = l + r >> 1;
        build(p * 2, l, m);
        build(p * 2 + 1, m + 1, r);
        tree[p].max = Math.max(tree[p * 2].max, tree[p * 2 + 1].max);
    }

    public static int query(int p, int l, int r) {
        if (l <= tree[p].l && r >= tree[p].r) return tree[p].max;
        int m = tree[p].l + tree[p].r >> 1;
        int max = Integer.MIN_VALUE;
        if (l <= m) max = Math.max(max, query(p * 2, l, r));
        if (r > m) max = Math.max(max, query(p * 2 + 1, l, r));
        return max;
    }

    static class Segment {
        int l, r;
        int max;
    }
}

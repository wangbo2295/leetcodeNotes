package leetcode.segmentTree;

import java.io.*;

public class ASimpleProblemWithIntegers {
    static int N = 100010;
    static Node[] t = new Node[N * 4];
    static int[] v = new int[N * 4];
    static int[] A = new int[N];

    public static void build(int p, int l, int r) {
        t[p] = new Node(l, r, 0);
        if (l == r) {
            t[p].sum = A[l];
            return;
        }
        int m = t[p].l + t[p].r >> 1;
        build(p << 1, l, m);
        build(p << 1 | 1, m + 1, r);
        t[p].sum = t[p << 1].sum + t[p << 1 | 1].sum;
    }

    public static void spread(int p) {
        t[p << 1].sum += (long) (t[p << 1].r - t[p << 1].l + 1) * v[p];
        t[p << 1 | 1].sum += (long) (t[p << 1 | 1].r - t[p << 1 | 1].l + 1) * v[p];
        v[p << 1] += v[p];
        v[p << 1 | 1] += v[p];
        v[p] = 0;
    }

    public static void change(int p, int l, int r, int k) {
        int x = t[p].l, y = t[p].r;
        if (l <= x && y <= r) {
            t[p].sum += (long) k * (y - x + 1);
            v[p] += k;
            return;
        }
        spread(p);
        int m = x + y >> 1;
        if (l <= m) change(p << 1, l, r, k);
        if (r > m) change(p << 1 | 1, l, r, k);
        t[p].sum += t[p << 1].sum + t[p << 1 | 1].sum;
    }

    public static long ask(int p, int l, int r) {
        int x = t[p].l, y = t[p].r;
        if (l <= x && y <= r) return t[p].sum;
        spread(p);
        int m = x + y >> 1;
        long sum = 0;
        if (l <= m) sum += ask(p << 1, l, r);
        if (r > m) sum += ask(p << 1 | 1, l, r);
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        String[] nums = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) A[i] = Integer.parseInt(nums[i - 1]);
        build(1, 1, n);
        while (m-- > 0) {
            String[] command = br.readLine().split(" ");
            int x = Integer.parseInt(command[1]), y = Integer.parseInt(command[2]);
            if (command[0].equals("Q")) {
                bw.write(ask(1, x, y) + "\n");
            } else {
                change(1, x, y, Integer.parseInt(command[3]));
            }
        }
        bw.flush();
    }

    static class Node {
        int l, r;
        long sum;
        public Node(){}
        public Node(int l, int r, long sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
        }
    }
}

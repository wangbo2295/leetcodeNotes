package leetcode.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class AnswerQueries {

    public static int n;
    public static Segment[] t;
    public static int[] A;
    static class Segment {
        int l, r;
        int lmax, rmax, max, sum;
        public Segment(){}
        public Segment(int lmax, int rmax, int sum, int max) {
            this.max = max;
            this.lmax = lmax;
            this.rmax = rmax;
            this.sum = sum;
        }
        public Segment(int def) {this(def, def, def, def);}
    }

    public static void build(int p, int l, int r) {
        t[p] = new Segment();
        t[p].l = l; t[p].r = r;
        if (l == r) {
            t[p].lmax = A[l];
            t[p].rmax = A[r];
            t[p].max = A[l];
            t[p].sum = A[l];
            return;
        }
        int m = l + r >> 1;
        build(p * 2, l, m);
        build(p * 2 + 1, m + 1, r);
        pop_up(t[p], t[p * 2], t[p * 2 + 1]);
    }

    public static Segment ask(int p, int l, int r) {
        if (l <= t[p].l && r >= t[p].r) return t[p];
        int m = t[p].l + t[p].r >> 1;
        Segment res = new Segment(Integer.MIN_VALUE), left = null, right = null;
        if (l <= m) {
            left = ask(p << 1, l, r);
        }
        if (r > m) {
            right = ask(p << 1 | 1, l, r);
        }
        if (left != null && right != null)  {
            pop_up(res, left, right);
            return res;
        }
        else if (left != null) return left;
        else return right;
    }

    public static void change(int p, int x, int y) {
        if (t[p].l == t[p].r && t[p].l == x) {
            t[p].sum = t[p].lmax = t[p].rmax = t[p].max = y;
            return;
        }
        int m = t[p].l + t[p].r >> 1;
        if (x <= m) change(p << 1, x, y);
        else change(p << 1 | 1, x, y);
        pop_up(t[p], t[p << 1], t[p << 1 | 1]);
    }

    public static void pop_up(Segment root, Segment left, Segment right) {
        root.max = Math.max(Math.max(left.max, right.max), left.rmax + right.lmax);
        root.lmax = Math.max(left.lmax, left.sum + right.lmax);
        root.rmax = Math.max(right.rmax, right.sum + left.rmax);
        root.sum = left.sum + right.sum;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        t = new Segment[n * 4];
        A = new int[n + 1];
        String[] nums = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) A[i] = Integer.parseInt(nums[i - 1]);
        build(1, 1, n);
        while (m-- > 0) {
            String[] command = br.readLine().split(" ");
            int comm = Integer.parseInt(command[0]);
            if (comm == 1) {
                int x = Integer.parseInt(command[1]), y = Integer.parseInt(command[2]);
                if (x > y) {
                    int t = x;
                    x = y;
                    y = t;
                }
                Segment ask = ask(1, x, y);
                bw.write(ask.max + "\n");
            } else {
                change(1, Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            }
        }
        bw.flush();
    }
}

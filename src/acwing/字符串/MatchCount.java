package acwing.字符串;

import java.util.Scanner;

/**
 * 阿轩在纸上写了两个字符串，分别记为 A 和 B。
 * 利用在数据结构与算法课上学到的知识，他很容易地求出了“字符串 A 从任意位置开始的后缀子串”与“字符串 B”匹配的长度。
 * 不过阿轩是一个勤学好问的同学，他向你提出了 Q 个问题：
 * 在每个问题中，他给定你一个整数 x ，请你告诉他有多少个位置，满足“字符串 A 从该位置开始的后缀子串”与 B 匹配的长度恰好为 x。
 *
 * 例如：A=aabcde，B=ab，则 A有 aabcde、abcde、bcde、cde、de、e这 6个后缀子串，它们与 B=ab的匹配长度分别是 1、2、0、0、0、0。
 * 因此 A有 4个位置与 B的匹配长度恰好为 0，有 1个位置的匹配长度恰好为 1，有 1个位置的匹配长度恰好为 2。
 */

/**
 * 解法一：字符串哈希
 * 分别求出 A 和 B 的前缀哈希，可以O(1)计算任意子串的hash值
 * 由于 B 只需关注前缀字串，所以可以不写get方法
 * 枚举 A 的起点下标，然后二分匹配长度，计算 A 以 i 为起点的字串与 B 的最长匹配长度
 */
public class MatchCount {
    public static int N = 200010;
    public static long[] hashA = new long[N];
    public static long[] hashB = new long[N];
    public static int base = 13331;
    public static long[] p = new long[N];
    public static int[] cnt = new int[N];
    public static int n, m, Q;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); m = scanner.nextInt(); Q = scanner.nextInt();
        scanner.nextLine();
        String A = scanner.nextLine(), B = scanner.nextLine();
        p[0] = 1;
        for (int i = 1, k = 1; i <= n || i <= m; i++, k++) {
            if (i <= n)
                hashA[i] = hashA[i - 1] * base + A.charAt(i - 1) - 'a' + 1;
            if (i <= m)
                hashB[i] = hashB[i - 1] * base + B.charAt(i - 1) - 'a' + 1;
            p[k] = p[k - 1] * base;
        }
        for (int i = 0; i < n; i++) {
            cnt[countMath(i)]++;
        }
        for (int i = 0; i < Q; i++) {
            System.out.println(cnt[scanner.nextInt()]);
        }
    }

    public static long getA(int l, int r) {
        return hashA[r] - hashA[l - 1] * p[r - l + 1];
    }

    public static int countMath(int sa) {
        int l = 0, r = Math.min(n - sa, m) + 1;
        while (l + 1 < r) {
            int mid = l + r >> 1;
            if (getA(sa + 1, sa + mid) == hashB[mid]) l = mid;
            else r = mid;
        }
        return l;
    }
}

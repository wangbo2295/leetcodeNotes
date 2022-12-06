package acwing.并查集;

import java.util.*;

public class ParityGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();scanner.nextLine();
        for (int i = 1; i < N; i++) fa[i] = i;
        int[][] edges = new int[m][3];
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0; i < m; i++) {
            String[] params = scanner.nextLine().split(" ");
            edges[i][0] = Integer.parseInt(params[0]) - 1;  //注意这里是 l - 1
            edges[i][1] = Integer.parseInt(params[1]);
            edges[i][2] = params[2].equals("odd") ? 1 : 0;
            set.add(edges[i][0]);   set.add(edges[i][1]);
        }
        list = new ArrayList<>(set);
        Collections.sort(list);
        for (int i = 0; i < edges.length; i++) {
            int x = index(edges[i][0]), y = index(edges[i][1]);
            int p = find(x), q = find(y);
            if (p == q && (d[x] ^ d[y]) != edges[i][2]) {
                System.out.println(i);
                return;
            } else if (p != q){
                fa[p] = q;
                d[p] = d[x] ^ d[y] ^ edges[i][2];
            }
        }
        System.out.println(m);
    }

    public static int N = 5010;
    public static int[] fa = new int[N];
    public static int[] d = new int[N];
    public static List<Integer> list;

    public static int index(int x) {
        int l = 0, r = list.size();
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (list.get(m) <= x) l = m;
            else r = m;
        }
        return l;
    }

    public static int find(int x) {
        if (fa[x] == x) return x;
        int root = find(fa[x]);
        d[x] ^= d[fa[x]];
        return fa[x] = root;
    }
}

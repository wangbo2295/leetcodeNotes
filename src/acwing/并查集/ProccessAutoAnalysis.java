package acwing.并查集;

import java.util.*;

public class ProccessAutoAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            UnionFind unionFind = new UnionFind();
            Set<Integer> set = new HashSet<>();
            List<int[]> eqs = new ArrayList<>();
            List<int[]> ueq = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] pair = {scanner.nextInt(), scanner.nextInt()};
                if (scanner.nextInt() == 0) ueq.add(pair);
                else eqs.add(pair);
                set.add(pair[0]);
                set.add(pair[1]);
            }
            List<Integer> list = new ArrayList<>(set);
            list.add(0);
            Collections.sort(list);
            for (int[] rel : eqs) {
                unionFind.union(get(list, rel[0]), get(list, rel[1]));
            }
            boolean valid = true;
            for (int[] ints : ueq) {
                if (unionFind.get(get(list, ints[0])) == unionFind.get(get(list, ints[1]))) {
                    valid = false;
                    break;
                }
            }
            if (valid) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static int get(List<Integer> list, int x) {
        int l = -1, r = list.size();
        while (l + 1 < r) {
            int m = l + r >> 1;
            if (list.get(m) < x) l = m;
            else r = m;
        }
        return r;
    }

    static class UnionFind {
        int[] fa;
        public UnionFind() {
            fa = new int[200010];
            for (int i = 1; i < 200010; i++)    fa[i] = i;
        }

        public int get(int x) {
            if (fa[x] == x) return x;
            return fa[x] = get(fa[x]);
        }

        public void union(int x, int y) {fa[get(x)] = get(y);}
    }
}
